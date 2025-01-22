package com.flipkart.client;
import java.util.*;

import com.flipkart.business.*;
import com.flipkart.bean.*;
import java.time.LocalTime;
/**
 * 
 */
public class GymOwnerClient {
	
	private GymOwnerOperations gymOwnerOps = new GymOwnerOperations();
	UserOperations uo = new UserOperations();


	public void getGymOwnerDetails(Gym_Owner gymOwner) {
        System.out.println("\n--- Gym Owner Details ---");
        // Retrieve details from the business layer
        Gym_Owner ownerDetails = gymOwnerOps.getGymOwnerDetails(gymOwner.getId());

        if (ownerDetails != null) {
            System.out.println("Owner ID: " + ownerDetails.getId());
            System.out.println("Name: " + ownerDetails.getName());
            System.out.println("Email: " + ownerDetails.getEmail());
            System.out.println("Contact: " + ownerDetails.getContact());
            System.out.println("Aadhar Card: " + ownerDetails.getAadharCard());
            System.out.println("GST Number: " + ownerDetails.getGSTNo());
            System.out.println("Approval Status: " + (ownerDetails.isApproved() ? "Approved" : "Pending Approval"));
        } else {
            System.out.println("Error: Could not retrieve details for Gym Owner ID: " + gymOwner.getId());
        }
    }
	
	public void registerGyms(Scanner scanner, int ownerId) {
        System.out.println("\n--- Register a New Gym ---");

        
        System.out.print("Enter Gym Name: ");
        String gymName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Number of Slots: ");
        int numberOfSlots = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Gym_Center gymCenter = gymOwnerOps.registerGym(ownerId, gymName, address, numberOfSlots);

        // Displaying feedback to the user
        if (gymCenter != null) {
            System.out.println("Gym '" + gymName + "' has been registered with Center ID: " 
                + gymCenter.getCenter_id() + " and is awaiting admin approval.");
        } else {
            System.out.println("Failed to register the gym. Please try again.");
        }
    }
	public void addSlot(Scanner sc, int ownerid) {
        System.out.println("\n--- Add Slots ---");

        // Prompt for start time
        System.out.print("Enter Start Time (1-12): ");
        int startHour = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Validate input
        if (startHour < 1 || startHour > 12) {
            System.out.println("Invalid start time. Please enter a value between 1 and 12.");
            return;
        }

        // Prompt for AM/PM
        System.out.print("Enter AM or PM: ");
        String amPm = sc.nextLine().toUpperCase();

        // Validate AM/PM
        if (!amPm.equals("AM") && !amPm.equals("PM")) {
            System.out.println("Invalid input. Please enter AM or PM.");
            return;
        }

        // Convert start time to 24-hour format
        int startTimeIn24Hr = (amPm.equals("PM") && startHour != 12) ? startHour + 12 : startHour;
        startTimeIn24Hr = (amPm.equals("AM") && startHour == 12) ? 0 : startTimeIn24Hr;

        // Calculate end time (1 hour slot)
        int endTimeIn24Hr = startTimeIn24Hr + 1;

        // Convert times to LocalTime
        LocalTime startTime = LocalTime.of(startTimeIn24Hr, 0);
        LocalTime endTime = LocalTime.of(endTimeIn24Hr, 0);

        // Call the business layer to add the slot
        boolean success = gymOwnerOps.addSlot(ownerid, startTime, endTime);

        // Display confirmation message
        if (success) {
            System.out.println("Thank you! We have added the time slot " + startHour + " " + amPm + " to " +
                    ((startHour % 12) + 1) + " " + amPm);
        } else {
            System.out.println("Failed to add the slot. Please try again.");
        }
    }
	
	
	/*
	public void getAllGymDetails() throws Exception{
		List<Gym_Center> allGyms = GymOwnerOperations.viewAllGymCenters(Gym_Owner.getEmail());
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Name");
		System.out.printf("%-15s\t", "Location");
		System.out.printf("%-15s\t", "No of Seats");
		System.out.println();
		for(Gym_Center gym : allGyms) {
			System.out.printf("%-15s\t", gym.getId());
			System.out.printf("%-15s\t", gym.getName());
			System.out.printf("%-15s\t", gym.getLocation());
			System.out.printf("%-15s\t", gym.getNoOfSeats());
		}
		System.out.println("-------------------------------------");
	}

	public void getAllApprovedGymDetails() throws Exception{
		List<Gym_Center> allApprovedGyms = GymOwnerOperations.viewAllApprovedGymCenters();
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Name");
		System.out.printf("%-15s\t", "Location");
		System.out.printf("%-15s\t", "No of Seats");
		System.out.println();
		for(Gym_Center gym : allApprovedGyms) {
			System.out.printf("%-15s\t", gym.getId());
			System.out.printf("%-15s\t", gym.getName());
			System.out.printf("%-15s\t", gym.getLocation());
			System.out.printf("%-15s\t", gym.getNoOfSeats());
			System.out.printf("%-15s\t", "Yes");
			System.out.println("");
		}
		System.out.println("-------------------------------------");
	}
	

	public void viewAllSlots(int Gym_CenterId) throws Exception{
		System.out.println("Listing all existing slots: ");
		List<Slot> allSlots = GymOwnerOperations.viewAllSlots(Gym_CenterId);
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Time");
		System.out.println();
		for(Slot slot : allSlots) {
			System.out.printf("%-15s\t", slot.getId());
			System.out.printf("%-15s\t", slot.getTime());
			System.out.println("");
		}
		System.out.println("-------------------------------------");
	}
	
	public void viewAllSlots(Scanner sc) throws Exception{
		getAllApprovedGymDetails();
		System.out.println("Enter the Gym_Center id for which you want to see all slots: ");
		viewAllSlots(sc.nextInt());
	}
	*/
	public void GymOwnerPage(Scanner scanner, Gym_Owner gymOwner) {
        System.out.println("\nWelcome, " + gymOwner.getName() + "!");

        while (true) {
            System.out.println("\n--- Gym Owner Menu ---");
            System.out.println("1. Register a Gym");
            System.out.println("2. View Gym Owner Details");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerGyms(scanner, gymOwner.getId());
                    break;
                case 2:
                	getGymOwnerDetails(gymOwner);
					break;
                    //gymOwnerOps.viewOwnedGyms(gymOwner.getId());
                case 3:
                	addSlot(scanner,gymOwner.getId());
                	break;
                    
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
