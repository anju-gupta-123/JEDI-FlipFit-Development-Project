package com.flipkart.business;

import com.flipkart.bean.Gym_Owner;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Customer;
import java.util.HashMap;
import java.util.Map;

public class UserOperations {
    private static Map<String, Gym_Owner> gymOwners = new HashMap<>();
    
    private static Map<String, Customer> customers = new HashMap<>();
    //private static Map<Integer, Gym_Owner> gymOwners = new HashMap<>();
    private static int customerIdCounter = 1;
    private static int gymOwnerIdCounter = 1;


    public Gym_Owner registerGymOwner(String name, String email, String contact, String password, String aadharCard, String gstNo) {
        // Create a new Gym_Owner object
        Gym_Owner newOwner = new Gym_Owner();
        newOwner.setId(gymOwnerIdCounter++);
        newOwner.setName(name);
        newOwner.setEmail(email);
        newOwner.setContact(contact);
        newOwner.setPassword(password);
        newOwner.setAadharCard(aadharCard);
        newOwner.setGSTNo(gstNo);
        newOwner.setApproved(false); // Mark as pending approval

        // Add the owner to the pending list for admin approval
        Admin.setGymOwners(newOwner);

        gymOwners.put(newOwner.getEmail(), newOwner); // Store in the main list for reference
        return newOwner;
    }

public Customer registerCustomer(String name, String email, String contact, String password) {
        Customer newCustomer = new Customer();
        newCustomer.setId(customerIdCounter++);
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setContact(contact);
        newCustomer.setPassword(password);

        customers.put(email, newCustomer);
        return newCustomer;
    }

    // Simulate some registered gym owners
    static {
        Gym_Owner owner1 = new Gym_Owner();
        owner1.setId(1);
        owner1.setName("John Doe");
        owner1.setEmail("john@example.com");
        owner1.setPassword("password123");
        owner1.setAadharCard("1234-5678-9101");
        owner1.setGSTNo("GST-001");
        gymOwners.put(owner1.getEmail(), owner1);
    }

    /**
     * Authenticates a gym owner using email and password.
     * @param email Email of the gym owner
     * @param password Password of the gym owner
     * @return The authenticated Gym_Owner object or null if authentication fails
     */
    public Gym_Owner authenticateGymOwner(String email, String password) {
        Gym_Owner owner = gymOwners.get(email);
        if (owner != null && owner.getPassword().equals(password)) {
            return owner;
        }
        return null;
    }
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customers.get(email);
        if (customer != null ) {
        	
            return customer;
        }
        return null;
    }
}

