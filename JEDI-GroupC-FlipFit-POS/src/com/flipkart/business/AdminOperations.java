package com.flipkart.business;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

import java.util.List;

import com.flipkart.DAO.*;

public class AdminOperations {
	AdminDAOInterface adminImpl = new AdminDAOImpl();

    public List<Gym_Owner> viewAllGymOwners() {
        return adminImpl.viewApprovedGymOwners();
        
        
        
    }

    public List<Gym_Center> viewAllGyms() {
        System.out.println("\n--- All Approved Gyms ---");
        return adminImpl.viewApprovedGymCenters();
    }

    public List<Gym_Owner> viewPendingGymOwnerRequests() {
        
        return adminImpl.viewPendinGymOwnerRequests();
    }

    public List<Gym_Center> viewPendingGymRequests() {
      
        return adminImpl.viewPendingGymCenters();
    }

    public boolean approveGymRequest(int gymId) {
    	return adminImpl.approveGymRegistration(gymId);
        
    }

    public boolean approveGymOwnerRequest(int ownerId) {
    	return adminImpl.approveGymOwnerRegistration(ownerId);
    	
    }
}