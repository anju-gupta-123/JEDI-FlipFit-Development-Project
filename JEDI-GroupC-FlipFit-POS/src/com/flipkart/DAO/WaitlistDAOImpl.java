package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.flipkart.bean.Waitlist;
import com.flipkart.utils.DBUtils;

public class WaitlistDAOImpl implements WaitlistDAOInterface{
	

	@Override
	public boolean addToWaitlist(Waitlist waitlist) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into Waitlist(customerId, slotId, date) values(?,?,?)");  
			stmt.setInt(1, waitlist.getUser_Id()); 
			
			stmt.setInt(2, waitlist.getSlot_Id());
			java.sql.Date sqlDate = new java.sql.Date(waitlist.getBookingDate().getTime());
			//System.out.println(sqlDate);
			stmt.setDate(3, sqlDate);
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}	
		
	}
}  
		

