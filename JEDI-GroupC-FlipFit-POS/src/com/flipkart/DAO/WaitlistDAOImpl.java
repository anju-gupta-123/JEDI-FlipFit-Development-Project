package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Waitlist;
import com.flipkart.utils.DBUtils;
import com.flipkart.DAO.NotificationsDAOImpl;

public class WaitlistDAOImpl implements WaitlistDAOInterface{
	NotificationsDAOInterface notifimpl= new NotificationsDAOImpl();
	

	@Override
	public boolean addToWaitlist(Waitlist waitlist) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitWaitlist(customerId, slotId, date) values(?,?,?)");  
			stmt.setInt(1, waitlist.getUser_Id()); 
			
			stmt.setInt(2, waitlist.getSlot_Id());
			java.sql.Date sqlDate = new java.sql.Date(waitlist.getBookingDate().getTime());
			//System.out.println(sqlDate);
			stmt.setDate(3, sqlDate);
			int i = stmt.executeUpdate();
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}	
		
	}
	
	public boolean promoteWaitlist(Booking booking)
	{
		try {
            // Connect to the database
            Connection con = DBUtils.connect();

            // Step 1: Retrieve the first matching record
            String selectQuery = "SELECT * FROM flipfitWaitlist WHERE slotId = ? AND date = ? ORDER BY waitlistId LIMIT 1";
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setInt(1, booking.getSlot_id()); 
            java.sql.Date sqlDate = new java.sql.Date(booking.getBooking_date().getTime());
            selectStmt.setDate(2, sqlDate); 

            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int waitlistId = rs.getInt("waitlistId"); 
                int customerid= rs.getInt("customerId");
                int slotid=rs.getInt("slotId");
                Date sqldate= rs.getDate("date");
                updateBooking(waitlistId,customerid,slotid,sqldate);

                
                String deleteQuery = "DELETE FROM flipfitWaitlist WHERE waitlistId = ?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, waitlistId);
                deleteStmt.executeUpdate();

                
            } else {
                return false;
            }

            
            rs.close();
            selectStmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;	
	}
	public void updateBooking(int waitlistid, int customerid, int slotid, Date sqldate)
	{
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("update flipfitBookedSlot set bookedSlotStatus=1 where slotId=? and date=? and customerid=?");
			stmt.setInt(1, slotid);
			
			stmt.setInt(3, customerid);
			stmt.setDate(2, sqldate);
			stmt.executeUpdate();
			
			con.close(); 
			notifimpl.createNoti(customerid,"Dear customer you are no longer in the waitlist, there is a vacancy and you have been promoted for the slotId:   "+slotid+"on the date:   "+sqldate);
			
			
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				
		}
	}
	
}  
		

