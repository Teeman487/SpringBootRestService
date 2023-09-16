package com.toheeb.demo.service;

import com.toheeb.demo.beans.Country;
import com.toheeb.demo.controller.AddResponse;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// How to develop Rest API & Microservice using SpringBoot
// developing service
@Component
public class CountryService {
    static HashMap<Integer, Country> countryIdMap;

    public CountryService() {
        countryIdMap=new HashMap<Integer,Country>();
        Country indiaCountry=new Country(1, "India", "Delhi");
        Country usaCountry=new Country(2, "USA", "Washington");
        Country ukCountry=new Country(3, "UK", "London");

        countryIdMap.put(1,indiaCountry);
        countryIdMap.put(2,usaCountry);
        countryIdMap.put(3,ukCountry);
    }

    public List getAllCountries()   // http://localhost:8080/getcountries
    {
      List countries=new ArrayList(countryIdMap.values());
      return countries;
    }

    public Country getCountryByID(int id)  // http://localhost:8080/getcountries/1
    {
       Country country=countryIdMap.get(id);
       return country;
    }

    public Country getCountrybyName(String countryName) // http://localhost:8080/getcountries/countryname?name=india
    {
        Country country = null;
        for(int i:countryIdMap.keySet())
        {
            if(countryIdMap.get(i).getCountryName().equals(countryName))
                country=countryIdMap.get(i);
        }
         return country;
    }

    public Country addCountry(Country country)   // http://localhost:8080/addcountry
    {
        country.setId(getMaxId());
        countryIdMap.put(country.getId(), country);
        return country;

    }
        // Utility method to get max id
    public static  int getMaxId()
    {
        int max=0;
        for(int id: countryIdMap.keySet())
            if(max<=id)
                max=id;
        return  max+1;

    }

    public Country updateCountry(Country country) // http://localhost:8080/updatecountry
    {
        if(country.getId()>0)
            countryIdMap.put(country.getId(), country);
        return country;
    }

     public AddResponse deleteCountry(int id)
     {
         countryIdMap.remove(id);
         AddResponse res=new AddResponse();
         res.setMsg("Country deleted...");
         res.setId(id);
         return res;  // Spring Boot auto convert java object to Json format

     }
}
