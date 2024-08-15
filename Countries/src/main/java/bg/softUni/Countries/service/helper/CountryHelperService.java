package bg.softUni.Countries.service.helper;

import bg.softUni.Countries.entity.Country;

import bg.softUni.Countries.exceptions.ObjectNotFoundException;
import bg.softUni.Countries.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryHelperService {
    private final CountryRepository countryRepository;

    public CountryHelperService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country getByIdOrThrow(Long id){
        return countryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("No country found with id: ", id));
    }
}
