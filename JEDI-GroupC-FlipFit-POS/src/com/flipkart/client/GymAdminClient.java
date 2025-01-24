package com.flipkart.client;

import com.flipkart.business.AdminOperations;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

public class GymAdminClient {
    private AdminOperations adminOps = new AdminOperations();

    public void adminPage() throws Exception {
        System.out.println("Hello Admin");
        boolean loop = true;
        while (loop) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(" 1. View All Approved Gym Owners \n 2. View All Approved Gyms \n 3. View Pending Gym Owner Requests \n 4. View Pending Gym Requests \n 5. Approve Gym Request \n 6. Approve Gym Owner Request \n 7. Exit \n ");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Enter the choice: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    
                	List <Gym_Owner> result= adminOps.viewAllGymOwners();
                	System.out.println("\n--- Approved Gym Owner Requests ---");
                	for(Gym_Owner x:result)
                	{
                		System.out.println("ID: " + x.getId() + ", Name: " + x.getName() + ", Email: " + x.getEmail());
                	}
                	
                	if(result.size()==0)
                	{
                		System.out.println("NO APPROVED GYM OWNER REQUESTS");
                	}
                	
                	
                	
                    break; 
                case 2:
                	
                	
                	List <Gym_Center> result2 = adminOps.viewAllGyms();
                	System.out.println("\n--- Apprioved Gyms ---");
                	for(Gym_Center x:result2)
                	{
                		System.out.println("ID: " + x.getCenter_id() + ", Name: " + x.getCenter_name() + ", Address: " + x.getAddress());
                	}
                	
                	if(result2.size()==0)
                	{
                		System.out.println("NO APPROVED GYMS");
                	}
                    break;
                case 3:
                	List <Gym_Owner> gymownerreq =adminOps.viewPendingGymOwnerRequests();
                	System.out.println("\n--- Pending Gym Owner Requests ---");
                	for(Gym_Owner x:gymownerreq)
                	{
                		System.out.println("ID: " + x.getId() + ", Name: " + x.getName() + ", Email: " + x.getEmail());
                	}
                	
                	if(gymownerreq.size()==0)
                	{
                		System.out.println("NO PENDING GYM OWNER REQUESTS");
                	}
                    break;
                case 4:
                    
                    List <Gym_Center> gymreq = adminOps.viewPendingGymRequests();
                	System.out.println("\n--- Pending Gym Requests ---");
                	for(Gym_Center x:gymreq)
                	{
                		System.out.println("ID: " + x.getCenter_id() + ", Name: " + x.getCenter_name() + ", Address: " + x.getAddress());
                	}
                	
                	if(gymreq.size()==0)
                	{
                		System.out.println("NO PENDING GYM REQUESTS");
                	}
                    
                    break;
                case 5:
                    System.out.print("Enter Gym ID to approve: ");
                    int gymId = sc.nextInt();
                    boolean sucess1=adminOps.approveGymRequest(gymId);
                    if(sucess1)
                    {
                    	System.out.println("Gym Owner Id"+gymId+"Has Been Approved");
                    }
                    else
                    {
                    	System.out.println("Failed to approve gym owner");
                    }
                    break;
                case 6:
                    System.out.print("Enter Gym Owner ID to approve: ");
                    int ownerId = sc.nextInt();
                    boolean sucess2=adminOps.approveGymOwnerRequest(ownerId);
                    if(sucess2)
                    {
                    	System.out.println("Gym Owner Id"+ownerId+"Has Been Approved");
                    }
                    else
                    {
                    	System.out.println("Failed to approve gym owner");
                    }
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