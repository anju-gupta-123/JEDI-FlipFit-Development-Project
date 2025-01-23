package com.flipkart.client;
import com.flipkart.bean.*;
import com.flipkart.business.UserOperations;
import java.util.Scanner;


public class GymApplicationClient {
	
	CustomerClient cc = new CustomerClient();
	GymAdminClient gac = new GymAdminClient();
	GymOwnerClient goc = new GymOwnerClient();
	UserOperations userOps= new UserOperations();
	public void registerGymOwner(Scanner sc) {
        System.out.println("\n--- Register as a Gym Owner ---");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Aadhar Card Number: ");
        String aadharCard = sc.nextLine();
        System.out.print("Enter GST Number: ");
        String gstNo = sc.nextLine();

        Gym_Owner newOwner = userOps.registerGymOwner(name, email, contact, password, aadharCard, gstNo);
        if (newOwner != null) {
            System.out.println("Gym Owner registered successfully with ID: " + newOwner.getId());
            System.out.println("Your application is pending admin approval.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
   }
	
	public static void main(String args[]) {
		GymApplicationClient gappc = new GymApplicationClient();
		Scanner sc = new Scanner(System.in);
		while(true) {
			gappc.showMenu();
			int option = sc.nextInt();
			gappc.handleUserInput(option);
		}
	}
	
	public void showMenu() {
		System.out.println("Welcome to FlipFit Application:--> ");
		System.out.println("1. Login");
		System.out.println("2. Registration of the Customer");
		System.out.println("3. Registration of the Gym Owner");
		System.out.println("4. Change Password");
		System.out.println("5. Exit");
	}
	
	
	public void handleUserInput(int option) {
		Scanner sc=new Scanner(System.in);
			switch(option) {
				case 1:
					handleUserLogin();
					break;
				case 2:
					System.out.println("Register Customer");
					registerCustomer(sc);
					break;
				case 3:
					System.out.println("Register Gym Owner");
					registerGymOwner(sc);
					break;
				case 4:
					System.out.println("Change Password");
					break;
				case 5:
					System.exit(1);
					break;
				default:
					System.out.println("Invalid Option. Choose among 1-5.");
					break;
			}
		
	}
	
	
	

}
