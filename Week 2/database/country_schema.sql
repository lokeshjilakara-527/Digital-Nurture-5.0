-- Country table schema
-- Note: when running the SpringDataJPA app, Hibernate creates this table
-- automatically (spring.jpa.hibernate.ddl-auto=update), so you do NOT need
-- to run this file to use the app. This script is provided in case you
-- want to inspect the schema or load the same data into MySQL/another DB.

CREATE TABLE IF NOT EXISTS country (
    country_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    country_code VARCHAR(5) NOT NULL,
    country_name VARCHAR(100) NOT NULL
);

INSERT INTO country (country_code, country_name) VALUES ('IN', 'India');
INSERT INTO country (country_code, country_name) VALUES ('US', 'United States');
INSERT INTO country (country_code, country_name) VALUES ('JP', 'Japan');
INSERT INTO country (country_code, country_name) VALUES ('DE', 'Germany');
INSERT INTO country (country_code, country_name) VALUES ('FR', 'France');

-- sample queries used in the assignment
SELECT * FROM country;
SELECT * FROM country WHERE country_code = 'IN';
