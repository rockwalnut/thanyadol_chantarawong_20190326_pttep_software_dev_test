package com.example.demo.repository;

//import java.util.List;

import com.example.demo.model.Restaurant;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
@Qualifier(value = "RestaurantCrudRepository")
public interface RestaurantCrudRepository extends JpaRepository<Restaurant, String> {

}
