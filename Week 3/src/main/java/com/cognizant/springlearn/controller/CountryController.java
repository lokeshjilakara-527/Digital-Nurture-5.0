package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

/**
 * Country REST endpoints (Doc 2):
 *   GET /country          -> India details (getCountryIndia)
 *   GET /countries        -> all four countries (getAllCountries)
 *   GET /country/{code}   -> one country by code, 404 if missing (getCountry)
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController constructor.");
    }

    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START getCountryIndia()");
        Country country;
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            country = context.getBean("country", Country.class);
        }
        LOGGER.debug("Country : {}", country);
        LOGGER.info("END getCountryIndia()");
        return country;
    }

    @GetMapping("/countries")
    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("START getAllCountries()");
        List<Country> countries;
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            countries = context.getBean("countryList", List.class);
        }
        LOGGER.info("END getAllCountries()");
        return countries;
    }

    @GetMapping("/country/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START getCountry()");
        Country country = countryService.getCountry(code);
        LOGGER.info("END getCountry()");
        return country;
    }
}
