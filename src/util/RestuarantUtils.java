package util;

import model.*;
import model.constant.*;
import java.util.*;

public class RestuarantUtils {

    public static void print(Restaurant restaurant) {
        String border = "|------------------------------------------------------------------------------|";
        String emptyLine = "|                                                                              |";

        System.out.println(border); // Top border

        System.out.println(emptyLine);
        System.out.printf("|   %-74s   |\n", "Here are the details of the Parking Lot");
        System.out.println(emptyLine);
        System.out.println(border); // Divider border
        System.out.printf("|   %-74s   |\n", "Restaurant ID  : " + restaurant.getId());
        System.out.printf("|   %-74s   |\n", "Restaurant NAME: " + restaurant.getName());
        System.out.printf("|   %-74s   |\n", "Restaurant Address: " + restaurant.getAddress());
        System.out.printf("|   %-74s   |\n", "Restaurant Phone : " + restaurant.getPhone());
        System.out.println(border); // Divider border

        System.out.printf("|   %-74s   |\n", "Current menu items:");
        System.out.println(emptyLine);
        List<MenuItem> menuItemList = restaurant.getMenuItems();
        for(MenuItem menuItem : menuItemList) {
            System.out.printf("|   %-74s   |\n", menuItem.getId()+ "   " +menuItem.getName()+"   "+menuItem.getPrice());
        }
        System.out.println(border); // Divider border

        System.out.printf("|   %-74s   |\n", "Current attendants:");
        System.out.println(emptyLine);
        List<User> attendantList = restaurant.getAttendants();
        for(User user : attendantList) {
            System.out.printf("|   %-74s   |\n", user.getId()+"   "+ user.getName());
        }
        System.out.println(border); // Bottom border
    }
}