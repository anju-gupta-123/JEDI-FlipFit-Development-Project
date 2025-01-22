package com.flipkart.client;

import com.flipkart.business.*;
import java.util.*;

public class CustomerClient {
    CustomerOperations co = new CustomerOperations();
  //  PaymentOperations po = new PaymentOperations();
    GymOwnerOperations go = new GymOwnerOperations();
  //  UserOperations u0 = new UserOperations();

    public void customerPage(Scanner sc, String customerEmail) throws Exception {
    	boolean loop = true;
        while (loop) {
            System.out.println("------------------------------");
            System.out.println("1. Register");
            System.out.println("2. Book slot");
            System.out.println("3. Cancel Booked slot");
            System.out.println("4. View all booked Slots");
            System.out.println("5. View catalog");
            System.out.println("6. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: customerRegistration(sc); break;
                case 2: bookSlot(sc, customerEmail); break;
                case 3: cancelBookedSlot(sc, customerEmail); break;
                case 4: viewAllBookedSlots(customerEmail); break;
                case 5: viewCatalog(); break;
                case 6: loop = false; break;
                default: System.out.println("Incorrect Choice");
            }
        }
    }

    public void customerRegistration(Scanner sc) {
    	String name, contact, email, password;
        System.out.print("Enter your name: ");
        name = sc.next();
        
        System.out.print("Enter your mobile: ");
        contact = sc.next();
        
        System.out.print("Enter your email: ");
        email = sc.next();
        
        System.out.print("Enter your password: ");
        password = sc.next();
        
        co.registerCustomer();
    }

    public void bookSlot(Scanner sc, String customerEmail) {
        System.out.print("Enter Gym ID: ");
        int gymCentreId = sc.nextInt();
        System.out.print("Enter Slot ID: ");
        int slotId = sc.nextInt();
        System.out.print("Enter the date (DD-MM-YYYY): ");
        String date = sc.next();
        try {
            co.bookSlot(gymCentreId, slotId, date, customerEmail);
        } catch (Exception ne) {
            System.out.println(ne.getMessage());
        }
        System.out.print("Enter Card number: ");
        Long cardNumber = sc.nextLong();
        System.out.print("Enter CVV: ");
        int cvv = sc.nextInt();
       // po.makePayment(cardNumber, customerEmail);
        System.out.println("Slot Booked");
    }

    public void cancelBookedSlot(Scanner sc, String customerEmail) throws Exception {
        List<BookedSlot> registeredBookings = co.viewAllBookings(customerEmail);
        System.out.print("Enter the Booked Slot ID: ");
        int id = sc.nextInt();
        for (BookedSlot obj : registeredBookings) {
            if (obj.getId() == id) {
                try {
                    co.cancelSlot(id, customerEmail);
                } catch (Exception ne) {
                    System.out.println(ne.getMessage());
                }
            }
        }
    }

    public void viewAllBookedSlots(String customerEmail) {
        List<BookedSlot> bookedSlots = co.viewAllBookings(customerEmail);
        System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", "Id", "Slot Id", "GymCenterID", "Date", "Customer Email");
        for (BookedSlot bs : bookedSlots) {
            System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", bs.getId(), bs.getSlotId(), bs.getGymCenterId(), bs.getDate(), bs.getCustomerEmail());
        }
    }

    public void viewCatalog() {
        List<GymCenter> allApprovedGyms = co.viewAllGymCentres();
        System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n", "Id", "Name", "Location", "No of Seats");
        for (GymCenter gym : allApprovedGyms) {
            System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n", gym.getId(), gym.getName(), gym.getLocation(), gym.getNoOfSeats());
            List<Slot> allAvailableSlots = co.viewAllSlots(gym.getId());
            System.out.printf("%-20s\t%-20s\n", "Id", "Time");
            for (Slot slot : allAvailableSlots) {
                System.out.printf("%-20s\t%-20s\n", slot.getId(), slot.getTime());
            }
        }
    }
	
}
