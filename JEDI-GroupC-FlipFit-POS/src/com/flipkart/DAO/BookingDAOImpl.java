package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.flipkart.bean.Booking;
import com.flipkart.utils.DBUtils;

public class BookingDAOImpl implements BookingDAOInterface {

	@Override
	public boolean createbooking(Booking booking) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitBookedSlot (customerId, slotId, date) values(?,?,?)");  
			stmt.setInt(1, booking.getCustomer_id()); 
			System.out.println("Hello"+booking.getCustomer_id());
			stmt.setInt(2, booking.getSlot_id());
			java.sql.Date sqlDate = new java.sql.Date(booking.getBooking_date().getTime());
			//System.out.println(sqlDate);
			stmt.setDate(3, sqlDate);
			
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
	
}
