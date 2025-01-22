package com.flipkart.business;

import java.util.*;

import com.flipkart.bean.Customer;

class Pair<K, V> {
    private K key;
    private V value;

    // Constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getters
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

public class CustomerOperations {
	Customer customer = new Customer();
	public void registerCustomer(int id, String name, String contact, String email, String password){
		customer.setId(id);
		customer.setName(name);
		customer.setContact(contact);
		customer.setEmail(email);
		customer.setPassword(password);
		
		System.out.println("Customer Added Successfully");
	}
	
	public void bookSlot(String gym, String slotId){
		Map<String, List<Pair<String, Integer>>> gyms = new HashMap<>();
		
		List<Pair<String, Integer>> gym1 = new ArrayList<>();
		gym1.add(new Pair<>("6:00 AM", 10));
		gym1.add(new Pair<>("8:00 AM", 7));
		gym1.add(new Pair<>("9:00 AM", 5));
		
		List<Pair<String, Integer>> gym2 = new ArrayList<>();
		gym2.add(new Pair<>("5:00 AM", 8));
		gym2.add(new Pair<>("10:00 AM", 10));
		gym2.add(new Pair<>("11:00 AM", 11));
		
		gyms.put("Gym1", gym1);
		gyms.put("Gym2", gym2);
		
		List<Pair<String, Integer>> presentSlots = gyms.get(gym);
		
		int op = 1;
		for(Pair<String, Integer> slot:presentSlots) {
			System.out.println("Option " + op + "-> Slot: " + slot.getKey() + ", Capacity: " + slot.getValue());
			op++;
		}
		
		System.out.println("Choose any option: ");
		
		presentSlots.set(op-1, new Pair<>(presentSlots.get(op-1).getKey(), presentSlots.get(op-1).getValue() - 1));
		
		System.out.println("Slot at " + slotId  + " booked successfully");
	}
	public void cancelBooking()
	{
		//Method Definition
		System.out.println("Canceled Bookings");
	}
	public void viewAvailableSlots()
	{
		//Method Definition
		System.out.println("View Slots");
	}
	public void viewDayPlan()
	{
		//Method Definition
		System.out.println("View Day plans");
	}
}
