package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.UserOperations;

import java.util.Scanner;
import com.flipkart.exceptions.*;

public class GymApplicationClient {
    CustomerClient cc = new CustomerClient();
    GymAdminClient gac = new GymAdminClient();
    GymOwnerClient goc = new GymOwnerClient();
    UserOperations userOps = new UserOperations();

    public void registerGymOwner(Scanner sc) {
        System.out.println("\n==============================");
        System.out.println("     Register as a Gym Owner     ");
        System.out.println("==============================");
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
            System.out.println("\n✅ Gym Owner registered successfully!");
            System.out.println("Your application is pending admin approval.");
        } else {
            System.out.println("\n❌ Registration failed. Please try again.");
        }
    }

    public void registerCustomer(Scanner sc) {
        System.out.println("\n==============================");
        System.out.println("     Register as a Customer     ");
        System.out.println("==============================");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User newCustomer = userOps.registerCustomer(name, email, contact, password);
        if (newCustomer != null) {
            System.out.println("\n✅ Customer registered successfully!");
        } else {
            System.out.println("\n❌ Registration failed. Please try again.");
        }
    }

    public static void main(String args[]) {
        GymApplicationClient gappc = new GymApplicationClient();
        Scanner sc = new Scanner(System.in);
        while (true) {
            gappc.showMenu();
            System.out.print("\nEnter your choice: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline
            gappc.handleUserInput(option);
        }
    }

    public void showMenu() {
        System.out.println("\n==============================");
        System.out.println("  Welcome to FlipFit Application  ");
        System.out.println("==============================");
        System.out.println("1️⃣  Login");
        System.out.println("2️⃣  Register as a Customer");
        System.out.println("3️⃣  Register as a Gym Owner");
        System.out.println("4️⃣  Change Password");
        System.out.println("5️⃣  Exit");
    }

    public void handleUserInput(int option) {
        Scanner sc = new Scanner(System.in);
        switch (option) {
            case 1:
                handleUserLogin();
                break;
            case 2:
                registerCustomer(sc);
                break;
            case 3:
                registerGymOwner(sc);
                break;
            case 4:
                System.out.println("\n🔑 Change Password (Feature Coming Soon!)");
                break;
            case 5:
                System.out.println("\n👋 Exiting application. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("\n❌ Invalid Option. Choose a number between 1-5.");
        }
    }

    public void handleUserLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n==============================");
        System.out.println("          User Login          ");
        System.out.println("==============================");
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Role (Admin/Gym Owner/Customer): ");
        String role = sc.nextLine();

        if (role.equalsIgnoreCase("Admin")) {
            try {
                gac.adminPage();
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        } else if (role.equalsIgnoreCase("Gym Owner")) {
            try {
                Gym_Owner gymOwner = userOps.authenticateGymOwner(email, password);
                if (gymOwner == null) {
                    System.out.println("\n❌ No Gym Owner found with this email.");
                } else if (!gymOwner.getPassword().equals(password)) {
                    System.out.println("\n❌ Incorrect Password.");
                } else {
                    goc.GymOwnerPage(sc, gymOwner);
                }
            } catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
        } else if (role.equalsIgnoreCase("Customer")) {
            try {
                Customer customer = userOps.authenticateCustomer(email, password);
                if (customer == null) {
                    System.out.println("\n❌ No Customer found with this email.");
                } else if (!customer.getPassword().equals(password)) {
                    System.out.println("\n❌ Incorrect Password.");
                } else {
                    cc.customerPage(sc, customer);
                }
            }catch(UserNotFoundException ue)
            {
            	System.out.println( ue.getMessage());
    			
            } 
            catch (Exception e) {
                System.out.println("\n❌ Error: " + e.getMessage());
            }
            
        } else {
            System.out.println("\n❌ Invalid Role Selected.");
        }
    }
}