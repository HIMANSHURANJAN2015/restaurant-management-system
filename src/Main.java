import controller.MenuItemController;
import controller.RestaurantController;
import controller.UserController;
import repository.MenuItemRepository;
import repository.RestaurantRepository;
import repository.UserRepository;
import service.MenuItemService;
import service.RestaurantService;
import service.UserService;
import util.InputUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Advaitya Restuarant Management system");

        //Creating all repository and service object, so that they remain singletop
        UserRepository userRepository = new UserRepository();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository();

        UserService userService = new UserService(userRepository, restaurantRepository);
        MenuItemService menuItemService = new MenuItemService(menuItemRepository, restaurantRepository, userRepository);
        RestaurantService restaurantService = new RestaurantService(restaurantRepository);

        UserController userController = new UserController(userService);
        MenuItemController menuItemController = new MenuItemController(menuItemService);
        RestaurantController restaurantController = new RestaurantController(restaurantService, menuItemController, userController);

        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        while(choice != 0) {
            choice = InputUtils.getValidInt(scanner, "Enter the choice code \n " +
                    "1 = Register a new Restaurant(with all details) \n " +
                    "2 = Print a restaurant \n " +
                    "3 = Add menu item \n " +
                    "0 = Exit");
            switch(choice) {
                case 1:
                    restaurantController.create();
                    break;
                case 2:
                    restaurantController.printRestaurant();
                    break;
                case 3:
                    menuItemController.addMenuItem();
                    break;
                case 0:
                    break;
                default:
                     throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}