package countryapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    // repository object is injected by spring, we do not create it ourselves
    @Autowired
    private CountryRepository countryRepository;

    // adds a new country to the database
    public Country addNewCountry(String code, String name) {
        Country newCountry = new Country(code, name);
        return countryRepository.save(newCountry);
    }

    // returns every country in the database
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // finds one country using its code
    public Optional<Country> getCountryByCode(String code) {
        return countryRepository.findByCountryCode(code);
    }

}
