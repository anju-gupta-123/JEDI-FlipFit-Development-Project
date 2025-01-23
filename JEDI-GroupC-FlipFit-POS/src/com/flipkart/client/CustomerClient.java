package com.flipkart.client;


import com.flipkart.business.CustomerOperations;
import java.util.*;
import com.flipkart.bean.Customer;


public class CustomerClient
{
	CustomerOperations customerOps= new CustomerOperations();
public void customerPage(Scanner scanner, Customer customer) {
	
    System.out.println("\nWelcome, " + customer.getName() + "!");

    while (true) {
        System.out.println("\n--- Customer Menu ---");
        System.out.println("1. View All Registered Gyms");
        System.out.println("2. Book a Slot");
        System.out.println("3. View All Booked Slots");
        System.out.println("4. Cancel a Booked Slot");
        System.out.println("5. Logout");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewAvailableApprovedGyms();
                break;
            case 2:
                bookSlot(scanner,customer.getId());
                break;
            case 3:
                viewAllBookedSlots(scanner);
                break;
            case 4:
                cancelBookedSlot(scanner);
                break;
            case 5:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}


    public void viewAvailableApprovedGyms() {
        System.out.println("\n--- Registered Gyms ---");
        customerOps.viewAvailableApprovedGyms();
    }

    /**
     * Book a slot for the customer.
     * @param scanner Scanner for user input
     */
    public void bookSlot(Scanner scanner,int userid) {
        System.out.println("\n--- Book a Slot ---");

        // Display gyms to choose from
        System.out.println("Select a Gym:");
        boolean x=customerOps.viewAvailableApprovedGyms();

        System.out.print("Enter Gym Center ID: ");
        int centerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Display slots for the chosen gym
        System.out.println("\nAvailable Slots for Gym Center ID: " + centerId);
        boolean y=customerOps.viewSlots(centerId);
        if(y==false) return;

        System.out.print("\nEnter Slot ID to Book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = customerOps.bookSlot(userid,centerId, slotId);
        if (success) {
            System.out.println("Slot booked successfully!");
        } else {
            System.out.println("Failed to book slot. Please try again.");
        }
    }

    
    public void viewAllBookedSlots(Scanner scanner) {
        System.out.println("\n--- Your Booked Slots ---");
        customerOps.viewAllBookedSlots();
    }

    
    public void cancelBookedSlot(Scanner scanner) 
    {
        System.out.println("\n--- Cancel a Booked Slot ---");

        // Display booked slots
        customerOps.viewAllBookedSlots();

        System.out.print("\nEnter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = customerOps.cancelBookedSlot(bookingId);
        if (success) {
            System.out.println("Slot canceled successfully!");
        } else {
            System.out.println("Failed to cancel the slot. Please try again.");
        }
    
    }



}
