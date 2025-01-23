package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class CustomerOperations {
    private List<Integer> bookedSlots = new ArrayList<>(); // Stores booked slot IDs for simplicity

    /**
     * Displays all approved gyms (ID and name).
     */
    public boolean viewAvailableApprovedGyms() {
        List<Gym_Center> approvedGyms = Admin.getApprovedGymCenters();

        if (approvedGyms.isEmpty()) {
            System.out.println("No approved gyms available.");
            return false;
        }

        System.out.println("Gym ID | Gym Name");
        for (Gym_Center gym : approvedGyms) {
            System.out.println(gym.getCenter_id() + " | " + gym.getCenter_name());
        }
        return true;
    }

    /**
     * Displays all slots for a specific gym center.
     * @param centerId ID of the gym center
     */
    public boolean viewSlots(int centerId) {
        List<Slot> slots = SlotOperations.gymslot.get(centerId);

        if (slots == null || slots.isEmpty()) {
            System.out.println("No slots available for this gym center.");
            return false;
        }

        System.out.println("\n--- Available Slots ---");
        for (Slot slot : slots) {
            System.out.println("Slot ID: " + slot.getSlotId() + ", Time: " + slot.getStartTime() + " - " + slot.getEndTime() +
                    ", Seats Available: " + slot.getNumberofseats());
        }
        return true;
    }

    /**
     * Books a slot for a specific gym center and slot ID.
     * @param centerId Gym center ID
     * @param slotId   Slot ID
     * @return true if booking is successful, false otherwise
     */
    public boolean bookSlot(int userId, int centerId, int slotId) {
        List<Slot> slots = SlotOperations.gymslot.get(centerId);
        if (slots == null) {
            System.out.println("Invalid gym center.");
            return false;
        }

        for (Slot slot : slots) {
            if (slot.getSlotId() == slotId) {
                if (slot.getNumberofseats() > 0) {
                    slot.setNumberofseats(slot.getNumberofseats() - 1); // Decrease available seats

                    // Delegate to BookingOperations
                    Date cur= new Date();
                    BookingOperations.createBooking(userId, slotId, cur, "BOOKED");
                    System.out.println("Booking confirmed for Slot ID: " + slotId);
                    return true;
                } else {
                    System.out.println("Slot is fully booked.");
                    return false;
                }
            }
        }
        System.out.println("Invalid slot ID.");
        return false;
    }

    /**
     * Displays all booked slots for the customer.
     */
    public void viewAllBookedSlots() {
        if (bookedSlots.isEmpty()) {
            System.out.println("You have no booked slots.");
            return;
        }

        System.out.println("Booked Slot IDs: " + bookedSlots);
    }

    /**
     * Cancels a booked slot by booking ID.
     * @param bookingId Booking ID (Slot ID in this case)
     * @return true if cancellation is successful, false otherwise
     */
    public boolean cancelBookedSlot(int bookingId) {
        if (bookedSlots.contains(bookingId)) {
            bookedSlots.remove((Integer) bookingId); // Remove from booked slots
            // Restore the seat count for the slot
            for (List<Slot> slotList : SlotOperations.gymslot.values()) {
                for (Slot slot : slotList) {
                    if (slot.getSlotId() == bookingId) {
                        slot.setNumberofseats(slot.getNumberofseats() + 1);
                        return true;
                    }
                }
            }
        }
        System.out.println("Invalid booking ID.");
        return false;
    }
}
