package com.flipkart.client;

import com.flipkart.business.AdminOperations;

import java.util.Scanner;

public class GymAdminClient {
    private AdminOperations adminOps = new AdminOperations();

    public void adminPage() throws Exception {
        System.out.println("Hello Admin");
        boolean loop = true;
        while (loop) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" 1. View All Gym Owners \n 2. View All Gyms \n 3. View Pending Gym Owner Requests \n 4. View Pending Gym Requests \n 5. Approve Gym Request \n 6. Approve Gym Owner Request \n 7. Exit \n ");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Enter the choice: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminOps.viewAllGymOwners();
                    break;
                case 2:
                    adminOps.viewAllGyms();
                    break;
                case 3:
                    adminOps.viewPendingGymOwnerRequests();
                    break;
                case 4:
                    adminOps.viewPendingGymRequests();
                    break;
                case 5:
                    System.out.print("Enter Gym ID to approve: ");
                    int gymId = sc.nextInt();
                    adminOps.approveGymRequest(gymId);
                    break;
                case 6:
                    System.out.print("Enter Gym Owner ID to approve: ");
                    int ownerId = sc.nextInt();
                    adminOps.approveGymOwnerRequest(ownerId);
                    break;
                case 7:
                    loop = false;
                    break;
                default:
                    System.out.println("Wrong Choice");
            }
        }
    }
}