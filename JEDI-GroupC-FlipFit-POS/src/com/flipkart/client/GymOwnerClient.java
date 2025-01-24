package com.flipkart.client;



import java.util.*;



import com.flipkart.business.*;

import com.flipkart.bean.*;

import java.time.LocalTime;



public class GymOwnerClient {

	private GymOwnerOperations gymOwnerOps = new GymOwnerOperations();

	UserOperations uo = new UserOperations();

	

	public void registerGyms(Scanner scanner, int ownerId) {

        System.out.println("\n--- Register a New Gym ---");



        System.out.print("Enter Gym Name: ");

        String gymName = scanner.nextLine();

        System.out.print("Enter Address: ");

        String address = scanner.nextLine();

        System.out.print("Enter Number of Slots: ");

        int numberOfSlots = scanner.nextInt();

        scanner.nextLine();



        Gym_Center gymCenter = gymOwnerOps.registerGym(ownerId, gymName, address, numberOfSlots);



        if (gymCenter != null) {

            System.out.println("Gym '" + gymName + "' has been registered with Center ID: " 

                + gymCenter.getCenter_id() + " and is awaiting admin approval.");

        } else {

            System.out.println("Failed to register the gym. Please try again.");

        }

    }



	public void getGymOwnerDetails(Gym_Owner gymOwner) {

        System.out.println("\n--- Gym Owner Details ---");

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

            System.out.println("Error: Could not retrieve details for Gym Owner ID: " + gymOwner.getId());

        }

    }

	

	public void addSlot(Scanner sc, int centerId) {

        System.out.println("\n--- Add Slots ---");



        System.out.print("Enter Start Time (1-12) without AM or PM: ");

        int startHour = sc.nextInt();

        sc.nextLine();

        if (startHour < 1 || startHour > 12) {

            System.out.println("Invalid start time. Please enter a value between 1 and 12.");

            return;

        }



        System.out.print("Enter AM or PM: ");

        String amPm = sc.nextLine().toUpperCase();

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

        

        System.out.print("Capacity: ");

        int capacity = sc.nextInt();

        sc.nextLine();

        if (capacity < 1) {

            System.out.println("Invalid capacity. Please enter a valid capacity.");

            return;

        }



        boolean success = gymOwnerOps.addSlot(centerId, startTime, endTime, capacity);

        if (success) {

            System.out.println("Thank you! We have added the time slot " + startHour + " " + amPm + " to " +

                    ((startHour % 12) + 1) + " " + amPm);

        } else {

            System.out.println("Failed to add the slot. Please try again.");

        }

    }

	

	public void getAllCenters(int ownerId) {

        System.out.println("\n--- All Centers Details ---");

        List<Gym_Center> centerDetails = gymOwnerOps.getCenterDetails(ownerId);



        if (centerDetails.size() != 0) {

        	for(Gym_Center center: centerDetails) {

        		System.out.println("************************************");

        		System.out.println("Center ID: " + center.getCenter_id());

                System.out.println("Name: " + center.getCenter_name());

                System.out.println("Address: " + center.getAddress());

                System.out.println("No of Slots: " + center.getNo_of_slots());

                System.out.println("Approval Status: " + (center.isApproved() ? "Approved" : "Pending Approval"));

        	}

        } else {

            System.out.println("Error: Could not retrieve Gym Center details for Gym Owner ID: " + ownerId);

        }

    }

	

	public void GymOwnerPage(Scanner scanner, Gym_Owner gymOwner) {

        System.out.println("\nWelcome, " + gymOwner.getName() + "!");



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

                    System.out.println("Logging out...");

                    return;

                default:

                    System.out.println("Invalid choice. Try again.");

            }

        }

    }

}

