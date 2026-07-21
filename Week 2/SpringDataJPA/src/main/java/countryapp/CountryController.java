package countryapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // GET http://localhost:8080/api/countries
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    // GET http://localhost:8080/api/countries/IN
    @GetMapping("/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        return countryService.getCountryByCode(code)
                .orElseThrow(() -> new RuntimeException("country not found with code " + code));
    }

    // POST http://localhost:8080/api/countries?code=US&name=United States
    @PostMapping
    public Country addCountry(@RequestParam String code, @RequestParam String name) {
        return countryService.addNewCountry(code, name);
    }

}
