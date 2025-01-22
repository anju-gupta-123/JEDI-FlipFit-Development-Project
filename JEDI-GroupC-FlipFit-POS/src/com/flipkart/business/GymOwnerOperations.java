package com.flipkart.business;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Gym_Owner;
import java.util.*;

public class GymOwnerOperations {
    public List<Gym_Center> gymCenters = new ArrayList<>();
    public Map<Integer, List<Customer>> registeredCustomers = new HashMap<>();
    public List<Gym_Center> approvedgymCenters = new ArrayList<>();
    public void registerGymOwner() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter GymOwner ID:");
        int id = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter GymOwner Name:");
        String name = sc.nextLine();
        System.out.println("Enter Contact Number:");
        String contact=sc.nextLine();
        System.out.println("Enter Email Address:");
        String email=sc.nextLine();
        System.out.println("Enter Password:");
        String password=sc.nextLine();
        sc.close();
        Gym_Owner gymowner  = new Gym_Owner();
        gymowner.setId(id);
        gymowner.setContact(contact);
        gymowner.setEmail(email);
        gymowner.setName(name);
        gymowner.setPassword(password);
        
    }
    public void registerGym() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Gym Center ID:");
        int centerId = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter Owner ID:");
        int ownerId = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Enter Gym Center Name:");
        String centerName = sc.nextLine();
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Enter Number of Slots:");
        int noOfSlots = sc.nextInt();
        sc.close();
        Gym_Center gym = new Gym_Center();
        gym.setCenter_id(centerId);
        gym.setOwner_id(ownerId);
        gym.setCenter_name(centerName);
        gym.setAddress(address);
        gym.setNo_of_slots(noOfSlots);
        
/       Admin.setGym_Center(gym);
       
        
        System.out.println("Gym registered successfully!");
    }

    public void addSlots() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Gym Center ID to add slots:");
        int centerId = sc.nextInt();

        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();
        sc.close();
        if (gymOptional.isPresent()) {
            System.out.println("Enter number of slots to add:");
            int additionalSlots = sc.nextInt();

            Gym_Center gym = gymOptional.get();
            gym.setNo_of_slots(gym.getNo_of_slots() + additionalSlots);

            System.out.println("Slots added successfully! Total slots: " + gym.getNo_of_slots());
        } else {
            System.out.println("Gym Center not found!");
        }
    }

    public void editGymDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Gym Center ID to edit details:");
        int centerId = sc.nextInt();
        sc.nextLine(); 
        sc.close();
        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();

        if (gymOptional.isPresent()) {
            Gym_Center gym = gymOptional.get();

            System.out.println("Enter new Gym Center Name :");
            String centerName = sc.nextLine();
            if (!centerName.isEmpty()) {
                gym.setCenter_name(centerName);
            }

            System.out.println("Enter new Address :");
            String address = sc.nextLine();
            if (!address.isEmpty()) {
                gym.setAddress(address);
            }

            System.out.println("Enter new Number of Slots :");
            String slotsInput = sc.nextLine();
            if (!slotsInput.isEmpty()) {
                int noOfSlots = Integer.parseInt(slotsInput);
                gym.setNo_of_slots(noOfSlots);
            }

            System.out.println("Gym details updated successfully!");
        } else {
            System.out.println("Gym Center not found!");
        }
    }

    public void registerCustomer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Gym Center ID for customer registration:");
        int centerId = sc.nextInt();
        sc.nextLine(); 
        sc.close();
        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();

        if (gymOptional.isPresent()) {
            System.out.println("Enter Customer ID:");
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.println("Enter Customer Name:");
            String name = sc.nextLine();
            System.out.println("Enter Contact Number:");
            String contact=sc.nextLine();
            System.out.println("Enter Email Address:");
            String email=sc.nextLine();
            System.out.println("Enter Password:");
            String password=sc.nextLine();
            sc.close();
            Customer customer = new Customer();
            customer.setId(id);
            customer.setContact(contact);
            customer.setEmail(email);
            customer.setName(name);
            customer.setPassword(password);
            registeredCustomers.computeIfAbsent(centerId, k -> new ArrayList<>()).add(customer);

            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Gym Center not found!");
        }
    }
}
