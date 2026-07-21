package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

/**
 * Country service (Doc 2 - Get country based on country code).
 * Loads the country list from country.xml and returns a case-insensitive match.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @SuppressWarnings("unchecked")
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START getCountry()");
        LOGGER.debug("Requested country code: {}", code);

        List<Country> countries;
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            countries = context.getBean("countryList", List.class);
        }

        // Case-insensitive match using a lambda (as suggested in the hands-on)
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);

        LOGGER.info("END getCountry()");
        return country;
    }
}
