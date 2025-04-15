package service;

import model.*;
import model.constant.*;
import repository.*;
import exception.*;
import java.util.*;

public class MenuItemService {

    private MenuItemRepository menuItemRepo;
    private RestaurantRepository restRepo;
    private UserRepository userRepo;

    public MenuItemService(MenuItemRepository m, RestaurantRepository r, UserRepository u) {
        this.menuItemRepo = m;
        this.restRepo = r;
        this.userRepo = u;
    }


    public MenuItem addMenuItem(long restaurantId, long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws RestaurantNotFoundException, UserNotFoundException, UnAuthorizedAccess {
        //Check restaurant and fetch Restaurant object
        Optional<Restaurant> restaurantOpt = this.restRepo.findById(restaurantId);
        if(restaurantOpt.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found");
        }
        Restaurant restaurant = restaurantOpt.get();

        //check user and his privilege
        Optional<User> userOpt = this.userRepo.findById(userId);
        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOpt.get();
        if(user.getUserType() != UserType.ADMIN) {
            throw new UnAuthorizedAccess("Access Denied");
        }

        //Checking ItemType and DietaryRequirement
        DietaryRequirement dietType = null;
        ItemType itemSpecialityType = null;
        try {
            dietType = DietaryRequirement.valueOf(dietaryRequirement);
            itemSpecialityType = ItemType.valueOf(itemType);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        //Now creating menu item object
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        item.setDietaryRequirement(dietType);
        item.setItemType(itemSpecialityType);
        item = this.menuItemRepo.save(item);

        //Updating Restaurant object
        List<MenuItem> menuItems = restaurant.getMenuItems();
        menuItems.add(item);
        restaurant.setMenuItems(menuItems);
        this.restRepo.save(restaurant);

        return item;
    }
}
