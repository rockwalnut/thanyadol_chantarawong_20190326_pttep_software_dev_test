package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Restaurant;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//for custom query
@Qualifier(value = "RestaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    //public List<Restaurant> findTop1ByKeyOrderByUpdatedAtDesc(String postcode);
    
    @Query("select e from Restaurant e where concat(trim(outcode), trim(postcode)) like %:pincode%")
    List<Restaurant> findByPincode(@Param("pincode") String pincode);
}
