package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Gym_Center;

public class AdminOperations {
    

    Scanner scanner = new Scanner(System.in);
    
    /**
     * Approves all pending gym registrations.
     */
    public void approveGymRegistrations() {
        List<Gym_Center> pendingGyms = Admin.getGymCenters();

        if (pendingGyms.isEmpty()) {
            System.out.println("No pending gym registrations.");
            return;
        }

        System.out.println("Pending Gym Registrations:");
        for (int i = 0; i < pendingGyms.size(); i++) {
            System.out.println((i + 1) + ". " + pendingGyms.get(i));
        }

        System.out.println("Enter the number of the gym to approve (or 0 to exit):");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        while (choice != 0) {
            if (choice > 0 && choice <= pendingGyms.size()) {
                Gym_Center selectedGym = pendingGyms.get(choice - 1);
                
                
                System.out.println("Approved gym: " + selectedGym.getCenter_name());
                Admin.removeGymCenters(selectedGym);
                Admin.setApprovedGymCenters(selectedGym);
            } else {
                System.out.println("Invalid choice. Try again.");
            }

            System.out.println("Enter the number of the next gym to approve (or 0 to exit):");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
        }
    }

    /**
     * Displays all registered gyms.
     */
    public void viewRegisteredGyms() {
    	List<Gym_Center> approvedgym = Admin.getGymCenters();
        if (approvedgym.isEmpty()) {
            System.out.println("No gyms registered yet.");
            return;
        }
        System.out.println("Registered Gyms:");
        for (Gym_Center gym : approvedgym) {
            System.out.println("- " + gym.getCenter_name());
        }
    }

    /**
     * Removes a specific gym by name.
     * @param gymName Name of the gym to remove.
     */
    public void removeGymCenter() {
        // Fetch the list of approved gyms
        List<Gym_Center> approvedGyms = Admin.getApprovedGymCenters();

        // Check if there are any gyms to remove
        if (approvedGyms.isEmpty()) {
            System.out.println("No approved gyms available to remove.");
            return;
        }

        // Display the list of approved gyms
        System.out.println("Approved Gyms:");
        for (int i = 0; i < approvedGyms.size(); i++) {
            System.out.println((i + 1) + ". " + approvedGyms.get(i).getCenter_name());
        }

        // Ask the user to choose a gym to remove
        System.out.println("Enter the number of the gym to remove (or 0 to exit):");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        while (choice != 0) {
            if (choice > 0 && choice <= approvedGyms.size()) {
                Gym_Center selectedGym = approvedGyms.get(choice - 1);
                 // Remove from gymCenters list
                Admin.removeGymCenters(selectedGym); // Remove from approvedGymCenters list
                System.out.println("Removed gym: " + selectedGym.getCenter_name());
            } else {
                System.out.println("Invalid choice. Try again.");
            }

            // Ask the user for the next gym to remove or to exit
            System.out.println("Enter the number of the next gym to remove (or 0 to exit):");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        }

        System.out.println("Gym removal process completed.");
    }
}