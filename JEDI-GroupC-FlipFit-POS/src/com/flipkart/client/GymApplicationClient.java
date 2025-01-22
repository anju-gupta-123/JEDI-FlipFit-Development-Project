package com.flipkart.client;

import java.util.GregorianCalendar;
import java.util.Scanner;

import com.flipkart.business.AdminOperations;
import com.flipkart.business.GymOwnerOperations;


public class GymApplicationClient {
	
	CustomerClient cc = new CustomerClient();
	GymAdminClient gac = new GymAdminClient();
	GymOwnerClient goc = new GymOwnerClient();
	
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
			switch(option) {
				case 1:
					handleUserLogin();
					break;
				case 2:
					System.out.println("Register Customer");
					break;
				case 3:
					System.out.println("Register Gym Owner");
					
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
	
	public void handleUserLogin() {
		Scanner sc = new Scanner(System.in);
		String email, password, role;
		System.out.println("Enter Email: ");
		email = sc.nextLine();
		System.out.println("Enter Password: ");
		password = sc.nextLine();
		System.out.println("Enter Role: ");
		role = sc.nextLine();
		
		if(role == "Admin" ) {
			try {
				gac.adminPage();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
				
		}
		else if(role == "Gym Owner") {
			try {
				goc.GymOwnerPage(sc, email);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}	
		else if(role == "Customer") {
			try {
				cc.customerPage(sc, email);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
		else {
			System.out.println("Invalid Credentials or role selected");
		}
	}
	

}
