package com.flipkart.DAO;

import com.flipkart.bean.Slot;

public interface GymOwnerDAOInterface {
	
	public boolean registerGymOwner();
	
	public void addSlot(int gymOwnerID, int GymCentre, Slot newSlot);
	
	public boolean isApproved(String gymOwnerEmail);
	
	public boolean isApproved(int gymCenterId);
	
}
