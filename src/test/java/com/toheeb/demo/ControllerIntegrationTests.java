package com.toheeb.demo;

import com.toheeb.demo.beans.Country;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Write Integrationntest cases for Contoller method using RestTemplate
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTests {

    @Test
    @Order(1)
    void getAllCountriesIntegrationTest() throws JSONException {  //http://localhost:8080/getcountries
          String expected="[{\"id\":1,\"countryName\":\"Nigeria\",\"countryCapital\":\"Abuja\"},{\"id\":2,\"countryName\":\"Washington\",\"countryCapital\":\"USA\"}]";


        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
  ResponseEntity<String>response=restTemplate.getForEntity("http://localhost:8080/getcountries", String.class); // through restTemplate im sending the get request
        System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }


    @Test
    @Order(2)
    void getAllCountriesByIDIntegrationTest() throws JSONException {

        String expected="{\"id\":1,\"countryName\":\"Nigeria\",\"countryCapital\":\"Abuja\"}";
        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
        ResponseEntity<String>response=restTemplate.getForEntity("http://localhost:8080/getcountries/1", String.class); // through restTemplate im sending the get request
        System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(3)
    void getAllCountriesByNameIntegrationTest() throws JSONException {

        String expected="{\"id\":1,\"countryName\":\"Nigeria\",\"countryCapital\":\"Abuja\"}";
        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
        ResponseEntity<String>response=restTemplate.getForEntity("http://localhost:8080/getcountries/countryname?name=Nigeria", String.class); // through restTemplate im sending the get request
        System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(4)
    void addCountryIntegrationTest() throws JSONException {

        Country country=new Country(3,"Germany","Berlin");
        String expected ="{\"id\":3,\"countryName\":\"Germany\",\"countryCapital\":\"Berlin\"}";

        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request=new HttpEntity<Country>(country,headers);


        ResponseEntity<String> response= restTemplate.postForEntity("http://localhost:8080/addcountry",request, String.class); // through restTemplate im sending the get request
        //System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(),false);

    }
    @Test
    @Order(5)
    void updateCountryIntegrationTest() throws JSONException {

        Country country=new Country(3,"Japan","Tokyo");
        String expected ="{\"id\":3,\"countryName\":\"Japan\",\"countryCapital\":\"Tokyo\"}";

        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request=new HttpEntity<Country>(country,headers);


        ResponseEntity<String> response= restTemplate.exchange("http://localhost:8080/updatecountry/3",HttpMethod.PUT,request, String.class); // through restTemplate im sending the get request
        //System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(),false);

    }
    @Test
    @Order(6)
    void deleteCountryIntegrationTest() throws JSONException {

        Country country=new Country(3,"Japan","Tokyo");
        String expected ="{\"id\":3,\"countryName\":\"Japan\",\"countryCapital\":\"Tokyo\"}";

        TestRestTemplate restTemplate=new TestRestTemplate(); // intend to get the data
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request=new HttpEntity<Country>(country,headers);


        ResponseEntity<String> response= restTemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE,request, String.class); // through restTemplate im sending the get request
        //System.out.println(response.getStatusCode());// will return Http status code
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(),false);

       // restTemplate.delete("http://localhost:8080/deletecountry/3");
    }
}
