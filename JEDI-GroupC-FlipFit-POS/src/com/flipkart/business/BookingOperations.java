package com.flipkart.business;
import com.flipkart.DAO.BookingDAOInterface;
import com.flipkart.DAO.BookingDAOImpl;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Booking;
;
public class BookingOperations {
    private static List<Booking> bookings = new ArrayList<>(); // Central list to store bookings
    static BookingDAOInterface bookingimpl= new BookingDAOImpl();

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
    	return bookingimpl.createbooking(booking);
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
}
