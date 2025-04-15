package controller;

import model.User;
import model.constant.UserType;
import service.UserService;
import util.InputUtils;

import java.util.Scanner;

public class UserController {

    private UserService userService;
    private Scanner sc;

    public UserController(UserService userService) {
        this.userService = userService;
        sc = new Scanner(System.in);
    }

    public void createAttendant() {
        this.createUser(UserType.ADMIN);
    }

    public void createCustomer() {
        this.createUser(UserType.CUSTOMER);
    }

    private void createUser(UserType userType){
        /*
            Like parking-lot-management system, instead of request and response DTO,
            I take input from user via command-line and give output via print to console.
         */

        try {
            //Taking user inputs String name, String password, String email, String phone, UserType userType
            long restaurantId = InputUtils.getValidLong(sc, "Enter restuarant ID to which the attendant will work");
            String name = InputUtils.getValidString(sc, "Enter attendant's name");
            String password = InputUtils.getValidString(sc, "Enter attendant's password");
            String email = InputUtils.getValidString(sc, "Enter attendant's email");
            String phoneNumber = InputUtils.getValidString(sc, "Enter attendant's phone number");
            User user = this.userService.addUser(restaurantId, name, password, email, phoneNumber, userType);
            System.out.println("User successfully added. User id="+ user.getId());
        } catch (Exception e) {
            System.out.println("OOPS!! Failed to add new user. Error: "+ e.getMessage());
        }
    }
}
