package repository;

import model.*;
import model.constant.DietaryRequirement;

import java.util.*;

public class RestaurantRepository {

    private Map<Long, Restaurant> restaurantMap = new HashMap<>();
    private static long id = 1;

    public Restaurant save(Restaurant menuItem) {
        if(menuItem.getId() == 0) {
            menuItem.setId(id++);
        }
        restaurantMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public Optional<Restaurant> findById(long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    public List<MenuItem> findMenuItemByDietaryRequirement(long restaurantId, DietaryRequirement dietaryRequirement) {
        Optional<Restaurant> restaurantOpt = findById(restaurantId);
        if(restaurantOpt.isEmpty()) {
            return Collections.emptyList();
        }
        Restaurant restaurant = restaurantOpt.get();
        List<MenuItem> allItems = restaurant.getMenuItems();
        //filter
        List<MenuItem> menuItems = new ArrayList<>();
        for(MenuItem menuItem : allItems) {
            if(menuItem.getDietaryRequirement() == dietaryRequirement) {
                menuItems.add(menuItem);
            }
        }
        return menuItems;
    }
}
