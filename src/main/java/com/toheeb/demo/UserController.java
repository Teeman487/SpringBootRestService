package com.toheeb.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController { // this class works for RestFul services we are sending

    @GetMapping
    public String getUsers(){
        return "http GET request was sent";
    }

    @PostMapping
    public String createUsers(){
        return "http POST request was sent";
    }


    @PutMapping
    public String updateUsers(){
        return "http PUT request was sent";
    }

    @DeleteMapping
    public String deleteUsers(){
        return "http DELETE request was sent";
    }
}
