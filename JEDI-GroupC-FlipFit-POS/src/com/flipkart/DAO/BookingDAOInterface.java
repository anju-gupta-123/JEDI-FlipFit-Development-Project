package com.flipkart.DAO;

import java.util.Date;

import com.flipkart.bean.Booking;

public interface BookingDAOInterface {
	public boolean createbooking(Booking booking);
	public int checkSlots(Date date,int slotid);
}
