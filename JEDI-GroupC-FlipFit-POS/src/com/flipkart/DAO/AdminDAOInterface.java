package com.flipkart.DAO;

import java.util.List;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

public interface AdminDAOInterface {
		
	
		public List<Gym_Owner> viewPendinGymOwnerRequests();
		
		public List<Gym_Center> viewPendingGymCenters();
		
		public boolean approveGymRegistration(int GymCenterID);
		
		public boolean approveGymOwnerRegistration(int GymOwnerID);
		
		public List<Gym_Owner> viewApprovedGymOwners();
		
		public List<Gym_Center> viewApprovedGymCenters();
		
}
 