package com.toheeb.demo;

import com.toheeb.demo.beans.Country;
import com.toheeb.demo.controller.CountryController;
import com.toheeb.demo.service.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMockitoClass.class})
public class ControllerMockitoClass {
    @Mock
    CountryService countryService;
    @InjectMocks
    CountryController countryController;

    List<Country> mycountries;
    Country country;

    @Test
    @Order(1)
    public void test_getAllCountries()
    {
        mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1,"India","Delhi"));
        mycountries.add(new Country(2,"USA","Washington"));

        when(countryService.getAllCountries()).thenReturn(mycountries);// Mocking
        ResponseEntity<List<Country>> res=countryController.getCountries();
           // verify or validation on res and verify the number of records fethched(mycountries)
           assertEquals(HttpStatus.FOUND,res.getStatusCode()); // verify the response status
           assertEquals(2,res.getBody().size());  // no of records from res
        }
    @Test
    @Order(2)
    public void test_getCountryByID()
    {
        country=new Country(2,"USA","Washington");
        int countryID=2;
        when(countryService.getCountryByID(countryID)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getCountryByID(countryID);

        assertEquals(HttpStatus.FOUND,res.getStatusCode());
        assertEquals(countryID,res.getBody().getId()); // Verify id

    }

    /* @GetMapping("/getcountries/countryname") // http://localhost:8080/getcountries/countryname?name=india
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName)
    {
       // return   countryService.getCountrybyName(countryName);
        try {
            Country country=countryService.getCountrybyName(countryName);
            return  new ResponseEntity<Country>(country,HttpStatus.OK);       // contain headers, cookies and all
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @Test
    @Order(3)

    public void test_getCountryByName()
    {
        country=new Country(2,"USA","Washington");
        String countryName="USA";

        when(countryService.getCountrybyName(countryName)).thenReturn(country); // Mocking
        ResponseEntity<Country> res = countryController.getCountrybyName(countryName);

        assertEquals(HttpStatus.FOUND,res.getStatusCode());
        assertEquals(countryName,res.getBody().getCountryName()); // Verify

    }
    @Test
    @Order(4)
    public void test_addCountry()
    {
        country=new Country(3,"Germany","Berlin");

        when(countryService.addCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.addCountry(country);

        assertEquals(HttpStatus.CREATED,res.getStatusCode());
        assertEquals(country,res.getBody());
    }
    @Test
    @Order(5)
    public void test_updateCountry()
    {
        country=new Country(3,"Japan","Tokyo");
        int countryID=3;
        when(countryService.getCountryByID(countryID)).thenReturn(country);// used when the method is returning
        when(countryService.updateCountry(country)).thenReturn(country);// used when the method is returning

        ResponseEntity<Country> res=countryController.updateCountry(countryID,country);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(3,res.getBody().getId());
        assertEquals("Japan",res.getBody().getCountryName());
        assertEquals("Tokyo",res.getBody().getCountryCapital());

    }
    @Test
    @Order(6)
    public void test_deleteCountry()
    {
        country=new Country(3,"Japan","Tokyo");
        int countryID=3;

        when(countryService.getCountryByID(countryID)).thenReturn(country);
        ResponseEntity<Country> res=countryController.deleteCountry(countryID);
        assertEquals(HttpStatus.OK,res.getStatusCode());

    }
}
