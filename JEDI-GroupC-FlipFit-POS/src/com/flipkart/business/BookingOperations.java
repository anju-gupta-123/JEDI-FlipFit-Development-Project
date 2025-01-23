package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Booking;
import java.util.Date;
public class BookingOperations {
    private static List<Booking> bookings = new ArrayList<>(); // Central list to store bookings
    private static int bookingIdCounter = 1;

    /**
     * Creates a booking and stores it in the system.
     *
     * @param userId  The user ID for the booking
     * @param slotId  The slot ID being booked
     * @param date    The booking date
     * @param status  The booking status
     */
    public static void createBooking(int userId, int slotId, Date date, String status) {
        Booking booking = new Booking();
        booking.setBooking_id(bookingIdCounter++);
        booking.setUser_id(userId);
        booking.setSlot_id(slotId);
        booking.setBooking_date(date);
        booking.setStatus(status);

        bookings.add(booking); // Add the booking to the list
        System.out.println("Booking created successfully! Booking ID: " + booking.getBooking_id());
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
