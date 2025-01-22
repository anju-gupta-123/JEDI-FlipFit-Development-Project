/**
 * 
 */
package com.flipkart.client;
import java.util.*;
public class GymAdminClient {
	public void approveGymOwnerRequest() {
		//Scanner sc=new Scanner(System.in); 
		System.out.println("Enter the gymOwner Id");
		
		System.out.println("Approve gym owner function");

	}
	
	
	public void approveGymRequest(){
	//	Scanner sc=new Scanner(System.in);
		System.out.println("Enter the gym Id");
		System.out.println("Approve gym request function");

	}
	
	
	public void viewPendingGymRequests() {

		System.out.printf("%-20s\t", "Id");
		System.out.printf("%-20s\t", "Name");
		System.out.printf("%-20s\t", "Location");
		System.out.printf("%-20s\t", "No of Seats");
		System.out.printf("%-20s\t", "GymOwnerEmail");
		System.out.printf("%-20s\t", "IsApproved");
		System.out.println();
		
		
		System.out.println("-------------------------------------");
	}
	
	
	public void viewPendingGymOwnerRequests() {

		System.out.printf("%-20s\t", "Id");
		System.out.printf("%-20s\t", "Address");
		System.out.printf("%-20s\t", "Email");
		System.out.printf("%-20s\t", "GST Number");
		System.out.printf("%-20s\t", "Name");
		System.out.printf("%-20s\t", "Phone");
		System.out.printf("%-20s\t", "IsApproved");
		System.out.println();
		
		System.out.println("-------------------------------------");
	}
	
	
	public void viewAllGymOwners() {
		
		System.out.printf("%-20s\t", "Id");
		System.out.printf("%-20s\t", "Address");
		System.out.printf("%-20s\t", "Email");
		System.out.printf("%-20s\t", "GST Number");
		System.out.printf("%-20s\t", "Name");
		System.out.printf("%-20s\t", "Phone");
		System.out.printf("%-20s\t", "IsApproved");
		System.out.println();
		
		System.out.println("-------------------------------------");
	}
	
	
	public void viewAllGymCenters() {
		
		System.out.printf("%-20s\t", "Id");
		System.out.printf("%-20s\t", "Name");
		System.out.printf("%-20s\t", "Location");
		System.out.printf("%-20s\t", "No of Seats");
		System.out.printf("%-20s\t", "GymOwnerEmail");
		System.out.printf("%-20s\t", "IsApproved");
		System.out.println();
		
		System.out.println("-------------------------------------");
	}
	
	
	
	
//method to show GymAdmin Page
	public void adminPage() throws Exception {
		
		System.out.println("Hello Admin");
		boolean loop=true;
		while(loop) {
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println(" 1. View All Gym Owners \n 2. View All Gyms \n 3. View Pending Gym Owner Requests \n 4. View Pending Gym Requests \n 5. Approve Gym Request \n 6. Approve Gym Owner Request \n 7. Exit \n ");
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("Enter the choice : - \n");
			
			Scanner sc=new Scanner(System.in); 
			int choice=sc.nextInt();
			switch (choice) {
			case 1:
				viewAllGymOwners();
				break;
			case 2:
				viewAllGymCenters();
				break;
			case 3:
				viewPendingGymOwnerRequests();
				break;
			case 4:
				viewPendingGymRequests();
				break;
			case 5:
				approveGymRequest();
				break;
			case 6:
				approveGymOwnerRequest();
				break;
			case 7:
				loop=false;
				break;

			default:
				System.out.println("Wrong Choice");
				
			}
		}
	}

}
