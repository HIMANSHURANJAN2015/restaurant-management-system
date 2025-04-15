package controller;

import model.MenuItem;
import model.Restaurant;
import model.User;
import service.RestaurantService;
import util.InputUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantController {

    private RestaurantService restaurantService;
    private MenuItemController menuItemController;
    private UserController userController;
    Scanner sc;

    public RestaurantController(RestaurantService r, MenuItemController m, UserController u) {
        this.restaurantService = r;
        this.menuItemController = m;
        this.userController = u;
        sc = new Scanner(System.in);
    }

    public void startFileReadingMode(Scanner scanner) {
        this.sc = scanner;
    }

    public void stopFileReadingMode() {
        this.sc = new Scanner(System.in);
    }

    public void create() {
        /*
            Like parking-lot-management system, instead of request and response DTO,
            I take input from user via command-line and give output via print to console.
         */

        try {
            String name = InputUtils.getValidString(sc, "Enter restaurant name");
            String address = InputUtils.getValidString(sc, "Enter restaurant address");
            String phone = InputUtils.getValidString(sc, "Enter restaurant phone");
            int numberOfAttendants = InputUtils.getValidInt(sc, "Enter number of attendants. Note: Atleast 1 attendant must be added");
            if(numberOfAttendants< 1) {
                throw new IllegalArgumentException("Atleast 1 attendant must be added");
            }
            int numberOfMenuItems = InputUtils.getValidInt(sc, "Enter number of restaurant items you want to add. Atleast 1 menu item must be added");
            if(numberOfMenuItems < 1) {
                throw new IllegalArgumentException("Atleast 1 menu item must be added");
            }

            Restaurant restaurant = this.restaurantService.addRestuarant(name, address, phone, new ArrayList<MenuItem>(), new ArrayList<User>());
            System.out.println("Restaurant successfully registered. Id: " + restaurant.getId());

            //Now ask owner to add as many user as stated above.
            for(int i=1; i<=numberOfAttendants; i++) {
                this.userController.createAttendant();
            }

            //Now ask owner to add as many menuItem as stated above.
            for(int i=1; i<=numberOfMenuItems; i++) {
                this.menuItemController.addMenuItem();
            }

            //printing restaurant details
            System.out.println("Restaurant successfully registered. Id: " + restaurant.getId());
            this.restaurantService.printRestaurant(restaurant.getId());
        } catch (Exception e) {
            System.out.println("OOPS!! Failed to register restaurant. Error: "+e.getMessage());
        }
    }

    public void printRestaurant() {
        try {
            long parkingLotId = InputUtils.getValidLong(sc, "Enter parking lot id");
            this.restaurantService.printRestaurant(parkingLotId);
        } catch(Exception e) {
            System.out.println("OOPS!! Failed to print restuarant. Error: "+e.getMessage());
        }
    }
}