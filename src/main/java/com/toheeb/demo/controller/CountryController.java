package com.toheeb.demo.controller;

import com.toheeb.demo.beans.Country;
import com.toheeb.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

   @Autowired
   CountryService countryService;

    @GetMapping("/getcountries") // http://localhost:8080/getcountries
    public List getCountries()
    {
    return   countryService.getAllCountries();
    }

    @GetMapping("/getcountries/{id}") // http://localhost:8080/getcountries/1
    public Country getCountryByID(@PathVariable(value="id") int id)
    {
        return   countryService.getCountryByID(id);
    }

    @GetMapping("/getcountries/countryname") // http://localhost:8080/getcountries/countryname?name=india
    public Country getCountryByName(@RequestParam(value="name") String countryName)
    {
        return   countryService.getCountrybyName(countryName);
    }


    @PostMapping("/addcountry") // http://localhost:8080/addcountry
    public Country addCountry(@RequestBody Country country)
    {
        return   countryService.addCountry(country);
    }

    @PutMapping("/updatecountry") // http://localhost:8080/updatecountry
    public Country updateCountry(@RequestBody Country country)
    {
        return   countryService.updateCountry(country);
    }

    @DeleteMapping("/deletecountry/{id}")  //http://localhost:8080/deletecountry/4
     public AddResponse deleteCountry(@PathVariable(value="id") int id)
     {
         return countryService.deleteCountry(id);

     }
}
