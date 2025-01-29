package com.flipkart.client;

import java.util.*;
import com.flipkart.business.*;
import com.flipkart.bean.*;
import java.time.LocalTime;

public class GymOwnerClient {

    private GymOwnerOperations gymOwnerOps = new GymOwnerOperations();
    UserOperations uo = new UserOperations();

    // Helper method for colored output
    private void printWithColor(String message, String color) {
        String colorCode = switch (color.toLowerCase()) {
            case "green" -> "\u001B[32m";
            case "red" -> "\u001B[31m";
            case "blue" -> "\u001B[34m";
            case "yellow" -> "\u001B[33m";
            default -> "\u001B[0m"; // reset to default
        };
        System.out.println(colorCode + message + "\u001B[0m");
    }

    public void registerGyms(Scanner scanner, int ownerId) {
        printWithColor("\n--- Register a New Gym ---", "blue");

        System.out.print("Enter Gym Name: ");
        String gymName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Number of Slots: ");
        int numberOfSlots = scanner.nextInt();
        scanner.nextLine();

        Gym_Center gymCenter = gymOwnerOps.registerGym(ownerId, gymName, address, numberOfSlots);

        if (gymCenter != null) {
            printWithColor("Gym '" + gymName + "' has been registered and is awaiting admin approval.", "green");
        } else {
            printWithColor("Failed to register the gym. Please try again.", "red");
        }
    }

    public void getGymOwnerDetails(Gym_Owner gymOwner) {
        printWithColor("\n--- Gym Owner Details ---", "blue");
        
        Gym_Owner ownerDetails = gymOwnerOps.getGymOwnerDetails(gymOwner.getEmail());

        if (ownerDetails != null) {
            System.out.println("Owner ID: " + ownerDetails.getId());
            System.out.println("Name: " + ownerDetails.getName());
            System.out.println("Email: " + ownerDetails.getEmail());
            System.out.println("Contact: " + ownerDetails.getContact());
            System.out.println("Aadhar Card: " + ownerDetails.getAadharCard());
            System.out.println("GST Number: " + ownerDetails.getGSTNo());
            System.out.println("Approval Status: " + (ownerDetails.isApproved() ? "Approved" : "Pending Approval"));
        } else {
            printWithColor("Error: Could not retrieve details for Gym Owner ID: " + gymOwner.getId(), "red");
        }
    }

    public void addSlot(Scanner sc, int centerId) {
        printWithColor("\n--- Add Slots ---", "blue");

        System.out.print("Enter Start Time (1-12) without AM or PM: ");
        int startHour = sc.nextInt();
        sc.nextLine();
        if (startHour < 1 || startHour > 12) {
            printWithColor("Invalid start time. Please enter a value between 1 and 12.", "red");
            return;
        }

        System.out.print("Enter AM or PM: ");
        String amPm = sc.nextLine().toUpperCase();
        if (!amPm.equals("AM") && !amPm.equals("PM")) {
            printWithColor("Invalid input. Please enter AM or PM.", "red");
            return;
        }

        // Convert start time to 24-hour format
        int startTimeIn24Hr = (amPm.equals("PM") && startHour != 12) ? startHour + 12 : startHour;
        startTimeIn24Hr = (amPm.equals("AM") && startHour == 12) ? 0 : startTimeIn24Hr;

        // Calculate end time (1 hour slot)
        int endTimeIn24Hr = startTimeIn24Hr + 1;

        System.out.print("Please Enter The Maximum Capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        if (capacity < 1) {
            printWithColor("Invalid capacity. Please enter a valid capacity.", "red");
            return;
        }

        boolean success = gymOwnerOps.addSlot(centerId, startTimeIn24Hr, endTimeIn24Hr, capacity);
        if (success) {
            printWithColor("Thank you! We have added the time slot " + startHour + " " + amPm + " to " +
                            ((startHour % 12) + 1) + " " + amPm, "green");
        } else {
            printWithColor("Failed to add the slot. Please try again.", "red");
        }
    }

    public void getAllCenters(int ownerId) {
        printWithColor("\n--- All Centers Details ---", "blue");

        List<Gym_Center> centerDetails = gymOwnerOps.getCenterDetails(ownerId);

        if (centerDetails.size() != 0) {
            for (Gym_Center center : centerDetails) {
                System.out.println("************************************");
                System.out.println("Center ID: " + center.getCenter_id());
                System.out.println("Name: " + center.getCenter_name());
                System.out.println("Address: " + center.getAddress());
                System.out.println("No of Slots: " + center.getNo_of_slots());
                System.out.println("Approval Status: " + (center.isApproved() ? "Approved" : "Pending Approval"));
            }
        } else {
            printWithColor("Error: Could not retrieve Gym Center details for Gym Owner ID: " + ownerId, "red");
        }
    }

    public void GymOwnerPage(Scanner scanner, Gym_Owner gymOwner) {
        printWithColor("\nWelcome, " + gymOwner.getName() + "!", "yellow");

        while (true) {
            System.out.println("\n--- Gym Owner Menu ---");
            System.out.println("1. Register a Gym");
            System.out.println("2. View Gym Owner Details");
            System.out.println("3. View All Centers");
            System.out.println("4. Add Slot");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerGyms(scanner, gymOwner.getId());
                    break;
                case 2:
                    getGymOwnerDetails(gymOwner);
                    break;
                case 3:
                    getAllCenters(gymOwner.getId());
                    break;
                case 4:
                    System.out.print("Enter Center ID: ");
                    int centerId = scanner.nextInt();
                    addSlot(scanner, centerId);
                    break;
                case 5:
                    printWithColor("Logging out...", "green");
                    return;
                default:
                    printWithColor("Invalid choice. Try again.", "red");
            }
        }
    }
}
