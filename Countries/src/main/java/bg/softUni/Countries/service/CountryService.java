package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.entity.Picture;
import bg.softUni.Countries.entity.dto.AddCountryDto;
import bg.softUni.Countries.entity.dto.CountryShortInfoDto;
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
}
