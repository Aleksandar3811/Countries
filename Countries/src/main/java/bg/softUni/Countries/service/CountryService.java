package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.entity.Picture;
import bg.softUni.Countries.entity.dto.AddCountryDto;
import bg.softUni.Countries.entity.dto.CountryDetailsCommentDTO;
import bg.softUni.Countries.entity.dto.CountryDetailsDTO;
import bg.softUni.Countries.entity.dto.CountryShortInfoDto;
import bg.softUni.Countries.exceptions.CountryNotFoundException;
import bg.softUni.Countries.repository.CountryRepository;
import bg.softUni.Countries.service.helper.UserHelperService;
import bg.softUni.Countries.util.YoutubeLinkConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final UserHelperService userHelperService;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper, UserHelperService userHelperService) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.userHelperService = userHelperService;
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
        Optional<Picture> imageUrl = country.getPictures().stream().findFirst();
        //dto.setImageUrl(imageUrl.get().getUrl());
        return dto;
    }

    public void add(AddCountryDto data, MultipartFile file) {
        Country toInsert = modelMapper.map(data, Country.class);
        toInsert.setVideoURL(YoutubeLinkConverter.convert(data.getVideoUrl()));
        toInsert.setAuthor(userHelperService.getUser());

        countryRepository.save(toInsert);
    }


        @Transactional(readOnly = true)
        public CountryDetailsDTO getDetails(Long id) {
            Country country = countryRepository.findById(id)
                    .orElseThrow(() -> new CountryNotFoundException("Route with id: " + id + " was not found"));

            CountryDetailsDTO dto = modelMapper.map(country, CountryDetailsDTO.class);
            dto.setVideoUrl("https://www.youtube.com/embed/" + dto.getVideoUrl());
            dto.setImageUrls(List.of("/images/pic4.jpg", "/images/pic1.jpg"));
            dto.setComments(country.getComments().stream()
                    .map(comment -> modelMapper.map(comment, CountryDetailsCommentDTO.class))
                    .toList());

            return dto;
        }


}
