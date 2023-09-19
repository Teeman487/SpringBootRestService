package com.toheeb.demo.controller;

import com.toheeb.demo.beans.Country;
import com.toheeb.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CountryController {

   @Autowired
   CountryService countryService;

    @GetMapping("/getcountries") // http://localhost:8080/getcountries
    public ResponseEntity<List<Country>> getCountries()
    {
//    return   countryService.getAllCountries();

        try {
            List<Country> countries=countryService.getAllCountries();;
            return  new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND);       // contain headers, cookies and all
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       // return countryService.getAllCountries();

    }

    @GetMapping("/getcountries/{id}") // http://localhost:8080/getcountries/1
    public ResponseEntity<Country> getCountryByID(@PathVariable(value="id") int id)
    {
       // return   countryService.getCountryByID(id);
        try {
             Country country=countryService.getCountryByID(id);
        return  new ResponseEntity<Country>(country,HttpStatus.FOUND);       // contain headers, cookies and all
        }
        catch (Exception e)
        {
           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname") // http://localhost:8080/getcountries/countryname?name=india
    public ResponseEntity<Country> getCountrybyName(@RequestParam(value="name") String countryName)
    {
       // return   countryService.getCountrybyName(countryName);
        try {
            Country country=countryService.getCountrybyName(countryName);
            return  new ResponseEntity<Country>(country,HttpStatus.FOUND);       // contain headers, cookies and all
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/addcountry") // http://localhost:8080/addcountry
    public ResponseEntity<Country> addCountry(@RequestBody Country country)
    {
        //return   countryService.addCountry(country);
     try {
         country = countryService.addCountry(country);
         return new ResponseEntity<Country>(country,HttpStatus.CREATED);

     }
     catch(NoSuchElementException e)
     {
         return  new ResponseEntity<>(HttpStatus.CONFLICT);

     }
    }

    @PutMapping("/updatecountry/{id}") // http://localhost:8080/updatecountry/4
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country)
    {
       // return   countryService.updateCountry(country);

        try {
            Country existCountry = countryService.getCountryByID(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());

           Country updated_country = countryService.updateCountry(existCountry);
        return  new ResponseEntity<Country>(updated_country,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }

    }

    @DeleteMapping(path="/deletecountry/{id}")  //http://localhost:8080/deletecountry/4
     public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id)
     {
        // return countryService.deleteCountry(id);  /*countryrep.delete(country);*/
         Country country=null;
         try {
             country=countryService.getCountryByID(id); // 1 External dep-that returns then we can mock it

             countryService.deleteCountry(country); // not returning; we cant mock it

         }
         catch(NoSuchElementException e)
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         return  new ResponseEntity<Country>(country, HttpStatus.OK);
     }
}





/*package com.toheeb.demo.controller;

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
}*/