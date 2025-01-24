package com.flipkart.DAO;

import com.flipkart.bean.Gym_Owner;
import com.flipkart.bean.Slot;

public interface GymOwnerDAOInterface {
	
	public void addSlot(Slot newSlot);
	
	public boolean isApprovedOwner(int gymOwnerId);
	
	public boolean isApprovedCenter(int gymCenterId);
	
}
