package com.flipkart.client;

import com.flipkart.business.AdminOperations;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

public class GymAdminClient {
    private AdminOperations adminOps = new AdminOperations();

    public void adminPage() throws Exception {
        System.out.println("======================================");
        System.out.println("        WELCOME, GYM ADMIN!         ");
        System.out.println("======================================");
        boolean loop = true;
        while (loop) {
            System.out.println("\n--------------------------------------------------------------------------------------");
            System.out.println("| 1. View All Approved Gym Owners | 2. View All Approved Gyms |");
            System.out.println("| 3. View Pending Gym Owner Requests | 4. View Pending Gym Requests |");
            System.out.println("| 5. Approve Gym Request | 6. Approve Gym Owner Request |");
            System.out.println("| 7. Exit |");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Gym_Owner> result = adminOps.viewAllGymOwners();
                    System.out.println("\n======================================");
                    System.out.println("       APPROVED GYM OWNERS        ");
                    System.out.println("======================================");
                    if (result.isEmpty()) {
                        System.out.println("NO APPROVED GYM OWNER REQUESTS");
                    } else {
                        for (Gym_Owner x : result) {
                            System.out.println("ID: " + x.getId() + " | Name: " + x.getName() + " | Email: " + x.getEmail());
                        }
                    }
                    System.out.println("======================================\n");
                    break;
                case 2:
                    List<Gym_Center> result2 = adminOps.viewAllGyms();
                    System.out.println("\n======================================");
                    System.out.println("          APPROVED GYMS         ");
                    System.out.println("======================================");
                    if (result2.isEmpty()) {
                        System.out.println("NO APPROVED GYMS");
                    } else {
                        for (Gym_Center x : result2) {
                            System.out.println("ID: " + x.getCenter_id() + " | Name: " + x.getCenter_name() + " | Address: " + x.getAddress());
                        }
                    }
                    System.out.println("======================================\n");
                    break;
                case 3:
                    List<Gym_Owner> gymownerreq = adminOps.viewPendingGymOwnerRequests();
                    System.out.println("\n======================================");
                    System.out.println("   PENDING GYM OWNER REQUESTS    ");
                    System.out.println("======================================");
                    if (gymownerreq.isEmpty()) {
                        System.out.println("NO PENDING GYM OWNER REQUESTS");
                    } else {
                        for (Gym_Owner x : gymownerreq) {
                            System.out.println("ID: " + x.getId() + " | Name: " + x.getName() + " | Email: " + x.getEmail());
                        }
                    }
                    System.out.println("======================================\n");
                    break;
                case 4:
                    List<Gym_Center> gymreq = adminOps.viewPendingGymRequests();
                    System.out.println("\n======================================");
                    System.out.println("      PENDING GYM REQUESTS       ");
                    System.out.println("======================================");
                    if (gymreq.isEmpty()) {
                        System.out.println("NO PENDING GYM REQUESTS");
                    } else {
                        for (Gym_Center x : gymreq) {
                            System.out.println("ID: " + x.getCenter_id() + " | Name: " + x.getCenter_name() + " | Address: " + x.getAddress());
                        }
                    }
                    System.out.println("======================================\n");
                    break;
                case 5:
                    System.out.print("Enter Gym ID to approve: ");
                    int gymId = sc.nextInt();
                    boolean success1 = adminOps.approveGymRequest(gymId);
                    System.out.println("\n--------------------------------------");
                    System.out.println(success1 ? "Gym ID " + gymId + " has been APPROVED!" : "Failed to approve gym.");
                    System.out.println("--------------------------------------\n");
                    break;
                case 6:
                    System.out.print("Enter Gym Owner ID to approve: ");
                    int ownerId = sc.nextInt();
                    boolean success2 = adminOps.approveGymOwnerRequest(ownerId);
                    System.out.println("\n--------------------------------------");
                    System.out.println(success2 ? "Gym Owner ID " + ownerId + " has been APPROVED!" : "Failed to approve gym owner.");
                    System.out.println("--------------------------------------\n");
                    break;
                case 7:
                    System.out.println("\n======================================");
                    System.out.println("   Exiting Admin Panel. Goodbye!   ");
                    System.out.println("======================================");
                    loop = false;
                    break;
                default:
                    System.out.println("\n--------------------------------------");
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN!");
                    System.out.println("--------------------------------------\n");
            }
        }
    }
}
