package util;

import model.constant.DietaryRequirement;
import model.constant.ItemType;

import java.util.Scanner;

public class InputUtils {

    // Method to validate and collect an integer input
    public static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input); // Attempt to parse as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    // Method to validate and collect an long input
    public static long getValidLong(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Long.parseLong(input); // Attempt to parse as long
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid long.");
            }
        }
    }

    // Method to validate and collect a double input
    public static double getValidDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input); // Attempt to parse as double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid long.");
            }
        }
    }

    // Method to validate and collect a string input
    public static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine().trim(); // Trim whitespace
            if (!input.isEmpty()) {
                return input; // Accept non-empty input
            } else {
                System.out.println("Invalid input! Please enter a non-empty string.");
            }
        }
    }

    public static DietaryRequirement getValidDietaryRequirement(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return DietaryRequirement.valueOf(input); // Attempt to parse as DietaryRequirement
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid DietaryRequirement.");
            }
        }
    }

    public static ItemType getValidItemType(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return ItemType.valueOf(input); // Attempt to parse as ItemType
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid ItemType.");
            }
        }
    }
}
