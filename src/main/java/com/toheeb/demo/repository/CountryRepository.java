package com.toheeb.demo.repository;

import com.toheeb.demo.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

// establish communication between database and bean class
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
