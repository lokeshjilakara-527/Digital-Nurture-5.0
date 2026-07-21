package countryapp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// JpaRepository already gives us save, findAll, findById, delete etc
// we only need to write the extra queries we want
public interface CountryRepository extends JpaRepository<Country, Long> {

    // spring data jpa will automatically create the query based on this method name
    Optional<Country> findByCountryCode(String countryCode);

    // example of hql query (works on entity/field names, not table/column names)
    @Query("select c from Country c where c.countryName = :name")
    Optional<Country> getCountryByName(@Param("name") String name);

    // example of a native sql query (works directly on the actual table)
    @Query(value = "select * from country where country_code = :code", nativeQuery = true)
    Optional<Country> getCountryByCodeNative(@Param("code") String code);

}
