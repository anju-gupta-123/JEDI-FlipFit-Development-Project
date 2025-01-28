package com.flipkart.business;
import com.flipkart.DAO.WaitlistDAOInterface;
import com.flipkart.DAO.WaitlistDAOImpl;
import com.flipkart.DAO.AdminDAOImpl;
import com.flipkart.DAO.CustomerDAOImpl;
import com.flipkart.DAO.CustomerDAOInterface;
import com.flipkart.DAO.AdminDAOInterface;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;
import java.util.Date;

import java.util.List;
import com.flipkart.bean.*;
public class CustomerOperations {
	AdminDAOInterface adminImpl = new AdminDAOImpl();
	CustomerDAOInterface customerImpl= new CustomerDAOImpl();
	WaitlistDAOInterface waitlistimpl=new WaitlistDAOImpl();
	BookingOperations bk= new BookingOperations();
    
    

    /**
     * Displays all approved gyms (ID and name).
     */
    public List<Gym_Center> viewAvailableApprovedGyms() {


        System.out.println("\n--- All Approved Gyms ---");
        return adminImpl.viewApprovedGymCenters();

        
    }

    /**
     * Displays all slots for a specific gym center.
     * @param centerId ID of the gym center
     */
    public List<Slot> viewSlots(int centerId) {
    	System.out.println("\n---Slots available ---");
    	List<Slot> x= customerImpl.viewAvailableSlots(centerId, null);
    	return x;
        
    }

    /**
     * Books a slot for a specific gym center and slot ID.
     * @param centerId Gym center ID
     * @param slotId   Slot ID
     * @return true if booking is successful, false otherwise
     */
    public boolean bookSlot(int userId, int centerId, int slotId, Date utilDate) {
        Booking booking= new Booking();
        booking.setCustomer_id(userId);
        booking.setSlot_id(slotId);
        booking.setBooking_date(utilDate);
        return BookingOperations.createBooking(booking);
        
    }

    /**
     * Displays all booked slots for the customer.
     */
    public List<Booking> viewAllBookedSlots(int customerid) {
        
    	return customerImpl.viewAllBookedSlots(customerid);
        
    }

    /**
     * Cancels a booked slot by booking ID.
     * @param bookingId Booking ID (Slot ID in this case)
     * @return true if cancellation is successful, false otherwise
     */
    public boolean cancelBookedSlot(int bookingId) {
    	
        Booking b= customerImpl.getBooking(bookingId);
        waitlistimpl.promoteWaitlist(b);
        
        
        return customerImpl.deleteBookedSlot(bookingId);
       
    }
}
