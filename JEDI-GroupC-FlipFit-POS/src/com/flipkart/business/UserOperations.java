
package com.flipkart.business;

import com.flipkart.bean.Gym_Owner;
import com.flipkart.DAO.CustomerDAOImpl;
import com.flipkart.DAO.CustomerDAOInterface;
import com.flipkart.DAO.GymOwnerDAOInterface;
import com.flipkart.DAO.GymOwnerDAOImpl;

import com.flipkart.bean.Customer;
import java.util.HashMap;
import java.util.Map;
import com.flipkart.DAO.UserDaoImpl;
import com.flipkart.DAO.UserDAOInterface;

public class UserOperations {
	UserDAOInterface userimpl = new UserDaoImpl();
	CustomerDAOInterface customerimpl= new CustomerDAOImpl();
	GymOwnerDAOInterface gymownerimpl= new GymOwnerDAOImpl();

    private static Map<String, Gym_Owner> gymOwners = new HashMap<>();
    
    private static Map<String, Customer> customers = new HashMap<>();
    //private static Map<Integer, Gym_Owner> gymOwners = new HashMap<>();
    
    


    public Gym_Owner registerGymOwner(String name, String email, String contact, String password, String aadharCard, String gstNo) {
        // Create a new Gym_Owner object
        Gym_Owner newOwner = new Gym_Owner();
        newOwner.setName(name);
        newOwner.setEmail(email);
        newOwner.setContact(contact);
        newOwner.setPassword(password);
        newOwner.setAadharCard(aadharCard);
        newOwner.setGSTNo(gstNo);
        newOwner.setApproved(false); // Mark as pending approval

        // Add the owner to the pending list for admin approval
        //Admin.setGymOwners(newOwner);
        
        userimpl.registerGymOwner(newOwner);
        gymOwners.put(newOwner.getEmail(), newOwner); // Store in the main list for reference
        return newOwner;
    }

public Customer registerCustomer(String name, String email, String contact, String password) {
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setContact(contact);
        newCustomer.setPassword(password);

        userimpl.registerCustomer(newCustomer);
        return newCustomer;
    }

    
    public Gym_Owner authenticateGymOwner(String email, String password) {
        Gym_Owner owner = gymownerimpl.getGymOwnerDetails(email);
        if (owner != null ) {
        	
            return owner;
        }
        return null;
    }
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customerimpl.getCustomerDetails(email);
        
        if (customer != null ) {
        	
            return customer;
        }
        return null;
    }
}
