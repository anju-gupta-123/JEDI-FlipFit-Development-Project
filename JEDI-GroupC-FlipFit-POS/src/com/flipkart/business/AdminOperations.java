package com.flipkart.business;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

import java.util.List;

import com.flipkart.DAO.*;

public class AdminOperations {
	AdminDAOInterface adminImpl = new AdminDAOImpl();

	// To get all the approved gym owners
    public List<Gym_Owner> viewAllGymOwners() {
        return adminImpl.viewApprovedGymOwners();
        
        
        
    }

    // To view all the gyms
    public List<Gym_Center> viewAllGyms() {
        System.out.println("\n--- All Approved Gyms ---");
        return adminImpl.viewApprovedGymCenters();
    }

    // To view the pening gymowner requests
    public List<Gym_Owner> viewPendingGymOwnerRequests() {
        
        return adminImpl.viewPendinGymOwnerRequests();
    }
    
    // To view all the pending gym requests
    public List<Gym_Center> viewPendingGymRequests() {
      
        return adminImpl.viewPendingGymCenters();
    }

    // To approve gym requests
    public boolean approveGymRequest(int gymId) {
    	return adminImpl.approveGymRegistration(gymId);
        
    }

    // To approve gym owner requests
    public boolean approveGymOwnerRequest(int ownerId) {
    	return adminImpl.approveGymOwnerRegistration(ownerId);
    	
    }
}