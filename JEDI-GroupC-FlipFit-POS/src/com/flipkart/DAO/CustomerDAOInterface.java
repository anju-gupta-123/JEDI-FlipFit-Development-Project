package com.flipkart.DAO;

import java.sql.Date;
import java.util.List;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;

public interface CustomerDAOInterface {
	
	public Customer getCustomerDetails(String email);
	
	public List<Gym_Center> viewAllApprovedGymCenters();
	
	public List<Slot> viewAvailableSlots(int gym_centerID, Date currentdate);
	
	public List<Booking> viewAllBookedSlots(int customerID);
	
	public boolean deleteBookedSlot(int bookedSlotID);
	
}
