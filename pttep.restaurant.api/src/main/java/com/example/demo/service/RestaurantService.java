package com.example.demo.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantCrudRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.exception.RestaurantNotFoundException;

//import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

	private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);


    @Autowired 
    @Qualifier(value = "RestaurantRepository")
    private RestaurantRepository restaurantRepository;

    @Autowired // (required=true)
    @Qualifier(value = "RestaurantCrudRepository")
    private RestaurantCrudRepository restaurantCrudRepository;
    
    public Restaurant get(String id)
    {
        return restaurantCrudRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public List<Restaurant> fetch(String pincode)
    {
        //log.info("pincode : " + pincode);
        return restaurantRepository.findByPincode(pincode);
    }

    public Restaurant post(Restaurant rest)
    {
       return restaurantCrudRepository.save(rest);
    }

    public Restaurant put(Restaurant rest, String id)
    {    
		return restaurantCrudRepository.findById(id)
        .map(r -> {
            
            r.setId(id);
            r.setName(rest.getName());
            r.setAddress(rest.getAddress());
            r.setAltAddress(rest.getAltAddress());
            r.setOutcode(rest.getOutcode());
            r.setPostcode(rest.getPostcode());
            r.setRating(rest.getRating());
            r.setTypeOfFood(rest.getTypeOfFood());

            return restaurantCrudRepository.save(rest);
        })
        .orElseGet(() -> {
            rest.setId(id);
            return restaurantCrudRepository.save(rest);
        });
    }

}