package com.flipkart.DAO;

import java.util.List;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Center;

public interface CustomerDAOInterface {
	
	public boolean registerCustomer(Customer c);
	
	public boolean validateCustomer(String email, String password);
	
	public List<Gym_Center> viewAllGymCenters();
	
	public List<Booking> viewAllBookedSlots();
	
	public void deleteBooking();
	
}
