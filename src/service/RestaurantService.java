package service;

import exception.RestaurantNotFoundException;
import model.*;
import repository.*;
import util.RestuarantUtils;

import java.util.*;

public class RestaurantService {

    private RestaurantRepository restuarantRepo;

    public RestaurantService(RestaurantRepository r) {
        this.restuarantRepo = r;
    }

    public Restaurant addRestuarant(String name, String address, String phone, List<MenuItem> menuItems, List<User> attendants) {
        //validate if needed
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        restaurant.setMenuItems(menuItems);
        restaurant.setAttendants(attendants);
        return restuarantRepo.save(restaurant);
    }

    public void printRestaurant(long restaurantId) {
        Optional<Restaurant> restaurantOpt = restuarantRepo.findById(restaurantId);
        if(restaurantOpt.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant with id = "+ restaurantId+" not found");
        }
        RestuarantUtils.print(restaurantOpt.get());
    }
}
