package controller;

import model.constant.DietaryRequirement;
import model.constant.ItemType;
import service.*;
import model.*;
import util.InputUtils;

import java.util.Scanner;


public class MenuItemController {

    private MenuItemService menuItemService;
    private Scanner scanner;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
        this.scanner = new Scanner(System.in);
    }

    public void startFileReadingMode(Scanner scanner) {
        this.scanner = scanner;
    }

    public void stopFileReadingMode() {
        this.scanner = new Scanner(System.in);
    }

    public void addMenuItem(){
        /*
            Like parking-lot-management system, instead of request and response DTO,
            I take input from user via command-line and give output via print to console.
         */

        try {
            long parkingLotId = InputUtils.getValidLong(scanner, "Enter Restaurant Id to which this menu item belong");
            long userId = InputUtils.getValidLong(scanner, "Enter user ID: ");
            String itemName = InputUtils.getValidString(scanner, "Enter item name: ");
            double price = InputUtils.getValidDouble(scanner, "Enter item price: ");
            DietaryRequirement dietaryRequirement = InputUtils.getValidDietaryRequirement(scanner, "Enter dietary requirement: ");
            ItemType itemType = InputUtils.getValidItemType(scanner, "Enter item type: ");
            String description = InputUtils.getValidString(scanner, "Enter item description: ");
            MenuItem item = this.menuItemService.addMenuItem(parkingLotId, userId, itemName, price, dietaryRequirement.toString(), itemType.toString(), description);
                //converting to string becasue I dont want to modify my service which accepts these as string and then checks.
                //Because in future if I want to create api, then I will just have to modify controller.
            System.out.println("Menu item added successfully. Item-id: "+item.getId());
        } catch (Exception e) {
            System.out.println("OOPS!! Failed to add menu item Error: "+ e.getMessage());
        }
    }
}
