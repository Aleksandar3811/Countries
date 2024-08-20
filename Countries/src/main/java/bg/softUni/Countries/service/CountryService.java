package bg.softUni.Countries.service;

import bg.softUni.Countries.config.Time;
import bg.softUni.Countries.entity.Category;
import bg.softUni.Countries.entity.CategoryType;
import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.entity.Picture;
import bg.softUni.Countries.entity.dto.*;
import bg.softUni.Countries.exceptions.ObjectNotFoundException;
import bg.softUni.Countries.repository.CategoryRepository;
import bg.softUni.Countries.repository.CountryRepository;
import bg.softUni.Countries.repository.PictureRepository;
import bg.softUni.Countries.service.helper.UserHelperService;
import bg.softUni.Countries.util.YoutubeLinkConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final UserHelperService userHelperService;
    private final PictureRepository pictureRepository;
    private final Time time;
    private final CategoryRepository categoryRepository;


    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper, UserHelperService userHelperService, PictureRepository pictureRepository, Time time, CategoryRepository categoryRepository) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.userHelperService = userHelperService;
        this.pictureRepository = pictureRepository;
        this.time = time;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<CountryShortInfoDto> getAll() {
        return countryRepository.findAll()
                .stream()
                .map(this::mapShortInfoDto)
                .toList();
    }

    private CountryShortInfoDto mapShortInfoDto(Country country) {
        CountryShortInfoDto dto = modelMapper.map(country, CountryShortInfoDto.class);
        Optional<Picture> first = country.getPictures().stream().findFirst();
        first.ifPresent(picture -> dto.setImageUrl(first.get().getImageUrl()));
        return dto;
    }

    public void add(AddCountryDto data) {

         Category category=categoryRepository.findByName(data.getCategoryType());
        Country toInsert = modelMapper.map(data, Country.class);
        toInsert.setVideoURL(YoutubeLinkConverter.convert(data.getVideoUrl()));
        toInsert.setAuthor(userHelperService.getUser());
        toInsert.setCategories(Set.of(category));

        countryRepository.save(toInsert);
    }


    @Transactional(readOnly = true)
    public CountryDetailsDTO getDetails(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found",id));

        CountryDetailsDTO dto = modelMapper.map(country, CountryDetailsDTO.class);
        dto.setVideoUrl("https://www.youtube.com/embed/" + dto.getVideoUrl());
        dto.setImageUrls(List.of("/images/pic4.jpg", "/images/pic1.jpg"));
        dto.setComments(country.getComments().stream()
                .map(comment -> modelMapper.map(comment, CountryDetailsCommentDTO.class))
                .toList());

        return dto;
    }


    @Transactional(readOnly = true)
    public List<CountryCategoryDTO> getCountryByCategory(CategoryType category) {
        List<Country> allByCategoryName = countryRepository.findAllByCategories_Name(category);

        return allByCategoryName.stream()
                .map(route -> {
                    CountryCategoryDTO dto = modelMapper.map(route, CountryCategoryDTO.class);
                    dto.setImageUrl(pictureRepository.findFirstByCountry_Id(dto.getId()).getImageUrl());

                    return dto;
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public CountryCategoryDTO getMostCommentedCountry() {
        if (countryRepository.count()==0){
            return null;
        }


        Country mostCommentedCountry = countryRepository.findAll().stream()
                .max(Comparator.comparingInt(route -> route.getComments().size()))
                .orElse(null);

        CountryCategoryDTO countryDto = modelMapper.map(mostCommentedCountry, CountryCategoryDTO.class);
        if (mostCommentedCountry.getPictures()==null || mostCommentedCountry.getPictures().isEmpty()){
            countryDto.setImageUrl("resources/static/images/noImg.png");
        }
        else {

            countryDto.setImageUrl(mostCommentedCountry.getPictures().stream().findFirst().get().getImageUrl());
        }



        return countryDto;
    }
    public String getCurrentTime() {
        return time.getCurrentTime();
    }

    public String getView(CategoryType category) {
        String view = "";
        switch (category){
            case CAR -> view ="car";
            case SHIP -> view ="ship";
            case PLANE -> view ="plane";
            case MOTORCYCLE -> view ="motorcycle";
        }

        return  view;
    }
}
