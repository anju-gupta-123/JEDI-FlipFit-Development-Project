package com.flipkart.DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;
import com.flipkart.utils.DBUtils;

public class CustomerDAOImpl implements CustomerDAOInterface{
	
	public Customer getCustomerDetails(String email) {
		Customer customer = new Customer();
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitCustomer where email=?");
			stmt.setString(1, email);
			
			  
			ResultSet rs = stmt.executeQuery();
			boolean check=false;
			while(rs.next()) {
				customer.setName(rs.getString("customerName"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("customerPassword"));
				customer.setContact(rs.getString("contact"));
				customer.setId(rs.getInt("customerId"));
				
				check=true;
				break;
			}
			con.close(); 
			if(check)
			return customer;
			else return null;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return null;
		}
	}
	
	public List<Gym_Center> viewAllApprovedGymCenters(){
		List<Gym_Center> approvedGymCenter = new ArrayList<>();
		
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitCustomer where isApproved=?");
			stmt.setBoolean(1, true);
			
			  
			ResultSet rs = stmt.executeQuery();  
			while(rs.next()) {
				Gym_Center gmCenter = new Gym_Center();
				gmCenter.setCenter_id(rs.getInt("centerId"));
				gmCenter.setAddress(rs.getString("address"));
				gmCenter.setCenter_name(rs.getString("centerName"));
				gmCenter.setNo_of_slots(rs.getInt("numOfSlots"));
				gmCenter.setApproved(true);
				gmCenter.setOwner_id(rs.getInt("ownerId"));
				
				approvedGymCenter.add(gmCenter);
			}
			con.close(); 
			return approvedGymCenter;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return null;
		}
	}
	
	public List<Slot> viewAvailableSlots(int gym_centerID, Date currentDate){
		List<Slot> viewAvailableSlots = new ArrayList<>();
		
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitSlot where centerID=?");
			stmt.setInt(1, gym_centerID);
			
			ResultSet rs = stmt.executeQuery();  
			while(rs.next()) {
				int availableSeats = rs.getInt("capacity") - getBookingsForASlot(rs.getInt("slotId"));
				
				Slot stSlot = new Slot();
				stSlot.setSlotId(rs.getInt("slotId"));
				stSlot.setCenterId(gym_centerID);
				stSlot.setAvailableSeats(availableSeats);
				stSlot.setStartTime(rs.getInt("startTime"));
				stSlot.setNumberofseats(rs.getInt("capacity"));
				viewAvailableSlots.add(stSlot);
				

			}
			con.close(); 
			return viewAvailableSlots;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return null;
		}
		
	}
	
	public List<Booking> viewAllBookedSlots(int customerID){
		List<Booking> bookedSlots = new ArrayList<>();
		
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitBookedSlot where customerId=? and bookedSlotStatus=?");
			stmt.setInt(1, customerID);
			stmt.setInt(2, 1);
			
			  
			ResultSet rs = stmt.executeQuery();  
			while(rs.next()) {
				Booking booking = new Booking();
				booking.setBooking_id(rs.getInt("bookedSlotId"));
				booking.setBooking_date(rs.getDate("date"));
				booking.setSlot_id(rs.getInt("slotId"));
				booking.setCustomer_id(rs.getInt("customerId"));
				booking.setStatus(rs.getInt("bookedSlotStatus"));
				
				bookedSlots.add(booking);
			}
			con.close(); 
			return bookedSlots;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return null;
		}
	}
	
	public boolean deleteBookedSlot(int bookedSlotID) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("update flipfitBookedSlot set bookedSlotStatus=0 where bookedSlotId=?");
			stmt.setInt(1, bookedSlotID);
			
			  
			int i = stmt.executeUpdate();
			con.close();
			if(i > 0)
				return true;
			else {
				return true;
			}
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				return false;
		}
	}
	
	private int getBookingsForASlot(int slot_id) {
		
		int noOfBookings = 0;
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select count(*) as countOfBookings from flipfitBookedSlot where slotId=?");
			stmt.setInt(1, slot_id);
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				noOfBookings = rs.getInt("countOfBookings");
				
			}
			con.close(); 
			return noOfBookings;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return 0;
		}
	}
		public Booking getBooking(int bookingid) {
			
			
			try {  
				
				Connection con = DBUtils.connect();
				Booking book=new Booking();
				PreparedStatement stmt=con.prepareStatement("select *  from flipfitBookedSlot where bookedSlotId=?");
				stmt.setInt(1, bookingid);
				ResultSet rs = stmt.executeQuery(); 
				
				while(rs.next()) {
					java.sql.Date sqlDate = rs.getDate("date");

			        // Convert to java.util.Date
			        java.util.Date utilDate = new Date(sqlDate.getTime());
					book.setBooking_date(utilDate);
					book.setSlot_id(rs.getInt("slotId"));
					return book;
					
					
				}
				con.close(); 
				
				 
				  
			}catch(Exception e){ 
					e.printStackTrace();			
					return null;
			}
			return null;
	}

	

	
	
	

}
