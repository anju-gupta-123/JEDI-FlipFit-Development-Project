package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.flipkart.utils.DBUtils;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Owner;

public class UserDaoImpl implements UserDAOInterface {
	
	public boolean registerCustomer(Customer c) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitCustomer (customerName, email, contact, customerPassword) values(?,?,?,?)");  
			stmt.setString(1, c.getName()); 
			stmt.setString(2, c.getEmail());
			stmt.setString(3, c.getContact());
			stmt.setString(4, c.getPassword());
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}		  
	}  
	
	public boolean registerGymOwner(Gym_Owner gymowner) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitGymOwner (gymOwnerName, email, contact, gymOwnerPassword,aadharCard, gstNo) values(?,?,?,?,?,?)");  
			stmt.setString(1, gymowner.getName()); 
			stmt.setString(2, gymowner.getEmail());
			stmt.setString(3, gymowner.getContact());
			stmt.setString(4, gymowner.getPassword());
			stmt.setString(5, gymowner.getAadharCard());
			stmt.setString(6, gymowner.getGSTNo());
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}	
	}
	
	public boolean authenticateAdmin() {
		return false;
	}

	

}