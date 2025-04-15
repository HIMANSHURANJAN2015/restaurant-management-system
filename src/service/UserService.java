package service;

import exception.RestaurantNotFoundException;
import model.*;
import model.constant.UserType;
import repository.*;
import java.util.*;

public class UserService {

    private UserRepository userRepo;
    private RestaurantRepository restaurantRepo;

    public UserService(UserRepository u, RestaurantRepository r) {
        this.userRepo = u;
        this.restaurantRepo = r;
    }

    public User addUser(long restaurantId, String name, String password, String email, String phone, UserType userType) throws RestaurantNotFoundException  {
        //Check restaurant and fetch Restaurant object
        Optional<Restaurant> restaurantOpt = this.restaurantRepo.findById(restaurantId);
        if(restaurantOpt.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found");
        }
        Restaurant restaurant = restaurantOpt.get();

        //validate if needed
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUserType(userType);
        user = userRepo.save(user);

        //Updating Restaurant object
        List<User> attendants = restaurant.getAttendants();
        attendants.add(user);
        restaurant.setAttendants(attendants);
        this.restaurantRepo.save(restaurant);

        return user;
    }
}
