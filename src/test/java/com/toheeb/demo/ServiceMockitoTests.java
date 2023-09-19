package com.toheeb.demo;

import com.toheeb.demo.beans.Country;
import com.toheeb.demo.repository.CountryRepository;
import com.toheeb.demo.service.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {
@Mock
CountryRepository countryrep;
@InjectMocks
CountryService countryService;

public List<Country> mycountries;
//Unit Test Case
    @Test
    @Order(1)
 public void test_getAllCountries()
 {
     List<Country>mycountries=new ArrayList<Country>();
     mycountries.add(new Country(1,"India","Delhi"));
     mycountries.add(new Country(2,"USA","Washington"));
     when(countryrep.findAll()).thenReturn(mycountries); // Mocking
   assertEquals(2,countryService.getAllCountries().size());
 }

 @Test
 @Order(2)
public void test_getCountryByID()
{
    List<Country>mycountries=new ArrayList<Country>();
    mycountries.add(new Country(1,"India","Delhi"));
    mycountries.add(new Country(2,"USA","Washington"));
     int countryID=1;
     when(countryrep.findAll()).thenReturn(mycountries); // Mocking
    assertEquals(1,countryService.getCountryByID(countryID).getId());

}
    @Test
    @Order(3)

    public void test_getCountrybyName()
    {
        List<Country>mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1,"India","Delhi"));
        mycountries.add(new Country(2,"USA","Washington"));
        String countryName="India";
        when(countryrep.findAll()).thenReturn(mycountries); // Mocking
        assertEquals(countryName,countryService.getCountrybyName(countryName).getCountryName());

    }

    @Test
    @Order(4)
    public void test_addCountry()
    {
       Country country=new Country(3,"Germany","Berlin");

       when(countryrep.save(country)).thenReturn(country);
      assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    public void test_updateCountry()
    {
        Country country=new Country(3,"Germany","Berlin");

        when(countryrep.save(country)).thenReturn(country);// used when the method is returning
        assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    public void test_deleteCountry()
    {
        Country country=new Country(3,"Germany","Berlin");

       countryService.deleteCountry(country);
      verify(countryrep,times(1)).delete(country); // Mocking
    }
}
