package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Owner;

public interface UserDAOInterface {
	
	public boolean registerCustomer(Customer c);
	
	public boolean registerGymOwner(Gym_Owner gymowner);
	
	public boolean authenticateAdmin();
}
