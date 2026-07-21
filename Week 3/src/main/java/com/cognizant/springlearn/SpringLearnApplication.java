package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START main()");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = new SpringLearnApplication();
        app.displayDate();       // Hands on 2
        app.displayCountry();    // Hands on 4 + 5 (singleton scope)
        app.displayCountries();  // Hands on 6
        LOGGER.info("END main()");
    }

    /** Hands on 2 — load SimpleDateFormat from date-format.xml and parse a date. */
    public void displayDate() {
        LOGGER.info("START displayDate()");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("date-format.xml")) {
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", date);
        } catch (Exception e) {
            LOGGER.error("Error parsing date", e);
        }
        LOGGER.info("END displayDate()");
    }

    /**
     * Hands on 4 — load the single India Country bean from country.xml.
     * Hands on 5 — fetching the bean twice proves singleton scope: the
     * Country constructor is logged only once.
     */
    public void displayCountry() {
        LOGGER.info("START displayCountry()");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            Country anotherCountry = context.getBean("country", Country.class);
            LOGGER.debug("Country       : {}", country);
            LOGGER.debug("Same instance : {}", country == anotherCountry);
        }
        LOGGER.info("END displayCountry()");
    }

    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START displayCountries()");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            List<Country> countries = context.getBean("countryList", List.class);
            LOGGER.debug("Country List  : {}", countries);
        }
        LOGGER.info("END displayCountries()");
    }
}
