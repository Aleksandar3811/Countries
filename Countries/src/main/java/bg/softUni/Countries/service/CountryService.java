package bg.softUni.Countries.service;

import bg.softUni.Countries.entity.Country;
import bg.softUni.Countries.entity.Picture;
import bg.softUni.Countries.entity.dto.CountryShortInfoDto;
import bg.softUni.Countries.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
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
        dto.setImageUrl(imageUrl.get().getUrl());
        return dto;
    }
}
