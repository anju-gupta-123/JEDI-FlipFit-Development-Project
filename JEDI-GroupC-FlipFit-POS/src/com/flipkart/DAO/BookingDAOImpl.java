package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.flipkart.bean.Booking;
import com.flipkart.utils.DBUtils;

public class BookingDAOImpl implements BookingDAOInterface {

	@Override
	public boolean createbooking(Booking booking) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitBookedSlot (customerId, slotId, date,bookedSlotStatus) values(?,?,?,?)");  
			stmt.setInt(1, booking.getCustomer_id()); 
			
			stmt.setInt(2, booking.getSlot_id());
			java.sql.Date sqlDate = new java.sql.Date(booking.getBooking_date().getTime());
			//System.out.println(sqlDate);
			stmt.setDate(3, sqlDate);
			stmt.setInt(4,booking.getStatus());
			int i=stmt.executeUpdate();
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}	
		
	}
	
	public int checkSlots(Date date,int slotid)
	{
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select count(*) as count from flipfitBookedSlot where slotId=? and date=? and bookedSlotStatus=1");
			stmt.setInt(1, slotid);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			stmt.setDate(2, sqlDate);
			ResultSet rs = stmt.executeQuery();  
			int res = 0;
			while(rs.next()) {
				res= rs.getInt("count");
				
			}
			
			con.close(); 
			return res;
			
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return 0;
		}
	}
}
	

