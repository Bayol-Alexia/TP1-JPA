package monprojet.dao;

import java.util.List;

import monprojet.dto.PopulationPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(
            value = "Select SUM(CITY.population)"
                   + "From CITY"
                   + "Where CITY.country_id = :id",
            nativeQuery = true)
    public Integer populationPays(int id);

    @Query(
            value = "Select COUNTRY.name as 'Pays', SUM(CITY.population) as 'Population'"
            +"From city"
            +"Inner join COUNTRY on CITY.country_id = :COUNTRY.id"
            +"Group by COUNTRY.name",
            nativeQuery = true)
    public List<PopulationPays> populationPays();
}
