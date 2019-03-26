package com.example.demo.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import java.util.Date;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
//import com.example.demo.uitility.Envi;
//import com.example.demo.uitility.Token;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantController {

   // private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

	//@Autowired
	//private Envi enviromentProperty;
    
	@Autowired
    private RestaurantService restaurantService;
    

    @GetMapping("/cors-config")
    @RequestMapping("/restaurant/get")
    public Restaurant get(@RequestParam(name = "id", required = false) 
                          String id)
    {
        return restaurantService.get(id);
    }

    //query restaurant by pincode (outcode+postcode)
    @GetMapping("/cors-config")
    @RequestMapping("/search_restaurants")
    public List<Restaurant> fetch(@RequestParam(name = "query", required = false) 
                          String query)
    {
        return restaurantService.fetch(query);
    }

    @PostMapping("/cors-config")
    @RequestMapping("/restaurant/post")
    public Restaurant post(@RequestBody Restaurant rest) 
    {
        return restaurantService.post(rest);
    }

    @PutMapping("/cors-config")
    @RequestMapping("/restaurant/put/{id}")
    public Restaurant put(@RequestBody Restaurant rest,  
                          @RequestParam(name = "id", required = false) 
                          String id) 
    {
        return restaurantService.put(rest, id);
    }

    //after app is start
	/*@PostConstruct
	public void initialize() throws IOException {
		log.info(">>>>> Initialize <<<<<");
		boolean localStorageExists = Files.exists(Paths.get(property.getStoragePath()));
		log.info(">>>>> Storage :{} exists :{}", property.getStoragePath(), localStorageExists);
		if (!localStorageExists) {
			Files.createDirectory(Paths.get(property.getStoragePath()));
		}
		log.info(">>>>> Local Storage :{}", Paths.get(property.getStoragePath()).toAbsolutePath());
	}*/

}