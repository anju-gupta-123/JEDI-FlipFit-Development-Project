package com.flipkart.business;
import com.flipkart.DAO.BookingDAOInterface;
import com.flipkart.DAO.SlotDAOInterface;
import com.flipkart.DAO.SlotDAOImpl;
import com.flipkart.DAO.BookingDAOImpl;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Booking;
;
public class BookingOperations {
    private static List<Booking> bookings = new ArrayList<>(); // Central list to store bookings
    static BookingDAOInterface bookingimpl= new BookingDAOImpl();
    static SlotDAOInterface slotimpl= new SlotDAOImpl();
    
    

    /**
     * Creates a booking and stores it in the system.
     *
     * @param userId  The user ID for the booking
     * @param slotId  The slot ID being booked
     * @param date    The booking date
     * @param status  The booking status
     */
    public static boolean createBooking(Booking booking) {
         // Add the booking to the list
    	if(checkCapacity(booking))
    	{
    		booking.setStatus(1);
    		return bookingimpl.createbooking(booking);
    	}
    	else
    	{
    		booking.setStatus(0);
    		bookingimpl.createbooking(booking);
    		return false;
    		
    	}
		
    }

    /**
     * Retrieves all bookings.
     *
     * @return List of all bookings
     */
    public static List<Booking> getAllBookings() {
        return bookings;
    }

    /**
     * Cancels a booking by ID.
     *
     * @param bookingId The booking ID to cancel
     * @return true if successful, false otherwise
     */
    public static boolean cancelBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBooking_id() == bookingId) {
                bookings.remove(booking);
                System.out.println("Booking canceled successfully! Booking ID: " + bookingId);
                return true;
            }
        }
        System.out.println("Invalid booking ID.");
        return false;
    }
    
    // Function to check and tell if a particular slot has enough slots while booking
    public static boolean checkCapacity(Booking booking)
    {
		int cap= slotimpl.checkCapacity(booking.getSlot_id());
		int slots= bookingimpl.checkSlots(booking.getBooking_date(), booking.getSlot_id());
		//System.out.println("CAP"+cap+"       Slot"+slots);
		return !(slots==cap);
		
		
    	
    }
}
