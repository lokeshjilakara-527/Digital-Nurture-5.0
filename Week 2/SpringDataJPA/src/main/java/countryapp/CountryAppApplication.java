package countryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryAppApplication implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(CountryAppApplication.class, args);
    }

    // this method runs automatically once the application starts
    // it is used here to quickly test our repository methods
    @Override
    public void run(String... args) throws Exception {

        System.out.println("---- Spring Data JPA Quick Example ----");

        // adding a few countries to start with
        countryRepository.save(new Country("IN", "India"));
        countryRepository.save(new Country("US", "United States"));
        countryRepository.save(new Country("JP", "Japan"));

        // finding a country using its code
        countryRepository.findByCountryCode("IN")
                .ifPresent(c -> System.out.println("Found by code: " + c));

        // printing every country stored in the database
        System.out.println("All countries in database:");
        for (Country c : countryRepository.findAll()) {
            System.out.println(c);
        }

        System.out.println("----------------------------------------");
    }

}
