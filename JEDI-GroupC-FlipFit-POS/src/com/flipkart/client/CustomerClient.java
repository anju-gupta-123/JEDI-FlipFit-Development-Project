package com.flipkart.client;

import com.flipkart.business.CustomerOperations;
import com.flipkart.business.NotificationsOperations;
import com.flipkart.business.WaitlistOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Slot;

public class CustomerClient {
    private final CustomerOperations customerOps = new CustomerOperations();
    private final WaitlistOperations waitlistOps = new WaitlistOperations();
    private final NotificationsOperations notifOps = new NotificationsOperations();

    public void customerPage(Scanner scanner, Customer customer) {
        System.out.println("\nWelcome, " + customer.getName() + "!");
        
        // Print current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Date & Time: " + now.format(formatter));

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View All Registered Gyms");
            System.out.println("2. View Slots In A Particular Gym");
            System.out.println("3. Book a Slot");
            System.out.println("4. View All Booked Slots");
            System.out.println("5. Cancel a Booked Slot");
            System.out.println("6. View Notifications");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAvailableApprovedGyms();
                case 2 -> viewAvailableSlots(scanner);
                case 3 -> bookSlot(scanner, customer.getId());
                case 4 -> viewAllBookedSlots(scanner, customer.getId());
                case 5 -> cancelBookedSlot(scanner, customer.getId());
                case 6 -> viewNotifications(customer.getId());
                case 7 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void viewAvailableApprovedGyms() {
        List<Gym_Center> gyms = customerOps.viewAvailableApprovedGyms();
        System.out.println("\n--- Approved Gyms ---");
        if (gyms.isEmpty()) {
            System.out.println("No approved gyms available.");
        } else {
            for (Gym_Center gym : gyms) {
                System.out.println("ID: " + gym.getCenter_id() + ", Name: " + gym.getCenter_name() + ", Address: " + gym.getAddress());
            }
        }
    }

    public void viewAvailableSlots(Scanner scanner) {
        System.out.print("Enter the Gym ID: ");
        int centerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Slot> slots = customerOps.viewSlots(centerId);
        System.out.println("\n--- Available Slots ---");
        if (slots == null || slots.isEmpty()) {
            System.out.println("No slots are available for this gym.");
        } else {
            for (Slot slot : slots) {
                System.out.println("ID: " + slot.getSlotId() + " | Start Time: " + slot.getStartTime() + ":00 | End Time: " + (slot.getStartTime() + 1) + ":00 | Capacity: " + slot.getNumberofseats());
            }
        }
    }

    public void bookSlot(Scanner scanner, int userId) {
        System.out.println("\n--- Book a Slot ---");
        Date selectedDate = getValidDateFromUser(scanner);
        
        viewAvailableApprovedGyms();
        System.out.print("Enter Gym Center ID: ");
        int centerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nAvailable Slots for Gym Center ID: " + centerId);
        List<Slot> slots = customerOps.viewSlots(centerId);
        for (Slot slot : slots) {
            System.out.println("ID: " + slot.getSlotId() + " | Start Time: " + slot.getStartTime() + ", End Time: " + (slot.getStartTime() + 1) + " | Seats: " + slot.getNumberofseats());
        }

        System.out.print("\nEnter Slot ID to Book: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();

        boolean success = customerOps.bookSlot(userId, centerId, slotId, selectedDate);
        if (success) {
            System.out.println("Slot booked successfully!");
        } else {
            System.out.println("Failed to book slot. Would you like to be added to the waitlist? (yes/no)");
            String option = scanner.next();
            scanner.nextLine();
            if (option.equalsIgnoreCase("yes")) {
                waitlistOps.addToWaitlist(userId, slotId, selectedDate);
                System.out.println("You have been added to the waitlist.");
            } else {
                System.out.println("You can continue booking for a different slot.");
            }
        }
    }

    public static Date getValidDateFromUser(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        while (true) {
            try {
                System.out.print("Enter the date (DD-MM-YYYY): ");
                String dateInput = scanner.nextLine();
                return sdf.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            }
        }
    }

    public void viewAllBookedSlots(Scanner scanner, int customerId) {
        System.out.println("\n--- Your Booked Slots ---");
        List<Booking> bookings = customerOps.viewAllBookedSlots(customerId);
        if (bookings.isEmpty()) {
            System.out.println("No booked slots found.");
        } else {
            for (Booking booking : bookings) {
                System.out.println("Booking ID: " + booking.getBooking_id() + " | Date: " + booking.getBooking_date() + " | Slot ID: " + booking.getSlot_id());
            }
        }
    }

    public void cancelBookedSlot(Scanner scanner, int customerId) {
        System.out.println("\n--- Cancel a Booked Slot ---");
        viewAllBookedSlots(scanner, customerId);
        System.out.print("Enter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        
        if (customerOps.cancelBookedSlot(bookingId)) {
            System.out.println("Slot canceled successfully!");
        } else {
            System.out.println("Failed to cancel the slot. Please try again.");
        }
    }

    public void viewNotifications(int customerId) {
        System.out.println("\n--- Your Notifications ---");
        List<String> notifications = notifOps.viewNotifications(customerId);
        if (notifications == null || notifications.isEmpty()) {
            System.out.println("No notifications available.");
        } else {
            notifications.forEach(System.out::println);
        }
    }
}
