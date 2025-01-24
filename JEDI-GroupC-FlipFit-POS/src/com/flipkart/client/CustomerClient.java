package com.flipkart.client;


import com.flipkart.business.CustomerOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;


public class CustomerClient
{
	CustomerOperations customerOps= new CustomerOperations();
public void customerPage(Scanner scanner, Customer customer) {
	
    System.out.println("\nWelcome, " + customer.getName() + "!");

    while (true) {
        System.out.println("\n--- Customer Menu ---");
        System.out.println("1. View All Registered Gyms");
        System.out.println("2. View Slots In A Particular Gym");
        System.out.println("3. Book a Slot");
        System.out.println("4. View All Booked Slots");
        System.out.println("5. Cancel a Booked Slot");
        System.out.println("6. Logout");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewAvailableApprovedGyms();
                break;
            case 2:
            	viewAvailableSlots(scanner);
            	break;
            case 3:
                bookSlot(scanner,customer.getId());
                break;
            case 4:
                viewAllBookedSlots(scanner,customer.getId());
                break;
            case 5:
                cancelBookedSlot(scanner,customer.getId());
                break;
            case 6:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}


    public void viewAvailableApprovedGyms() {
    	List <Gym_Center> result2 = customerOps.viewAvailableApprovedGyms();
    	System.out.println("\n--- Apprioved Gyms ---");
    	for(Gym_Center x:result2)
    	{
    		System.out.println("ID: " + x.getCenter_id() + ", Name: " + x.getCenter_name() + ", Address: " + x.getAddress());
    	}
    	
    	if(result2.size()==0)
    	{
    		System.out.println("NO APPROVED GYMS");
    	}
        
    }
    
    public void viewAvailableSlots(Scanner scanner)
    {
    	System.out.println("Enter the gymid of the gym you want to view the slots in");
    	int centerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

    	List<Slot> result= customerOps.viewSlots(centerId);
    	if(result==null)
    	{
    		System.out.println("No slots are available for this gym");
    		return;
    	}
    	
    	for(Slot x: result)
    	{
    		int e=x.getStartTime()+1;  
    		System.out.println("ID: " + x.getSlotId() + "-------Starting Time:" + x.getStartTime()+":00" +"------Ending time:" + e+":00"+ "--------Maximum Capacity Of Slot" + x.getNumberofseats());
    	}
    	
    }

    /**
     * Book a slot for the customer.
     * @param scanner Scanner for user input
     */
    public void bookSlot(Scanner scanner,int userid) {
        System.out.println("\n--- Book a Slot ---");

        // Display gyms to choose from
        
        System.out.println("Select a date to choose from");
        Date utilDate = getValidDateFromUser(scanner);
        System.out.println("Displaying gyms to choose from:");
        List <Gym_Center> result2 = customerOps.viewAvailableApprovedGyms();
    	System.out.println("\n--- Approved Gyms ---");
    	for(Gym_Center x:result2)
    	{
    		System.out.println("ID: " + x.getCenter_id() + ", Name: " + x.getCenter_name() + ", Address: " + x.getAddress());
    	}
    	
    	if(result2.size()==0)
    	{
    		System.out.println("NO APPROVED GYMS");
    		return;
    	}
    	

        System.out.print("Enter Gym Center ID: ");
        int centerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        
       
        // Display slots for the chosen gym
        System.out.println("\nAvailable Slots for Gym Center ID: " + centerId);
        
        List<Slot> x=customerOps.viewSlots(centerId);
        for(Slot a:x)
        {
        	int e=a.getStartTime()+1;
        	System.out.println("ID: " + a.getSlotId() + " , Starttime:" + a.getStartTime()+",  Endtime: "+e+  ", Seat:" + a.getNumberofseats());
        	
        }
        

        System.out.print("\nEnter Slot ID to Book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = customerOps.bookSlot(userid,centerId, slotId,utilDate);
        if (success) {
            System.out.println("Slot booked successfully!");
        } else {
            System.out.println("Failed to book slot. ");
            System.out.println("Would you want to add yourself in the waitlist?");
            String option= scanner.next();
            scanner.nextLine();
            if(option.equalsIgnoreCase("yes"))
            {
            	
            }
            else if(option.equalsIgnoreCase("No"))
            {
            	System.out.println("Sorry no option as such");
            }
            else
            {
            	System.out.println("Thank you! you can continue booking for a different slot");
            }
        }
    }

    public static Date getValidDateFromUser(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Strict parsing

        Date validDate = null;
        while (validDate == null) {
            try {
                System.out.println("Enter the date (DD-MM-YYYY):");
                String dateInput = scanner.nextLine();

                // Parse the date
                validDate = sdf.parse(dateInput);

                // Validate day, month, and year
                String[] dateParts = dateInput.split("-");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                if (day > 31 || day < 1) {
                    throw new IllegalArgumentException("Invalid day. Day must be between 1 and 31.");
                }
                if (month > 12 || month < 1) {
                    throw new IllegalArgumentException("Invalid month. Month must be between 1 and 12.");
                }
                if (year < 2024) {
                    throw new IllegalArgumentException("Invalid year. Year must be greater than or equal to 2024.");
                }

            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return validDate;
    }
    public void viewAllBookedSlots(Scanner scanner,int customerid) {
        System.out.println("\n--- Your Booked Slots ---");
        List<Booking> x=customerOps.viewAllBookedSlots(customerid);
        System.out.println("BookDetails: " + "/n");
        for(Booking result:x)
        {
        	System.out.println("Booking id"+ result.getBooking_id()+ "----Booking Date"+ result.getBooking_date()+"-----SlotId"+result.getSlot_id());
        }
        
        
    }

    
    public void cancelBookedSlot(Scanner scanner,int customerid) 
    {
        System.out.println("\n--- Cancel a Booked Slot ---");

        // Display booked slots
        customerOps.viewAllBookedSlots(customerid);

        System.out.print("\nEnter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = customerOps.cancelBookedSlot(bookingId);
        if (success) {
            System.out.println("Slot canceled successfully!");
        } else {
            System.out.println("Failed to cancel the slot. Please try again.");
        }
    
    }



}
