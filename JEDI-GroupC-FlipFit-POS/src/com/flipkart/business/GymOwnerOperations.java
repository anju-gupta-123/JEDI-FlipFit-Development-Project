package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.DAO.GymOwnerDAOImpl;
import java.util.*;
import java.time.LocalTime;

public class GymOwnerOperations {
    public List<Gym_Center> gymCenters = new ArrayList<>();
    public Map<Integer, List<Customer>> registeredCustomers = new HashMap<>();
    public List<Gym_Center> approvedgymCenters = new ArrayList<>();
    public static Map<Integer,List<Gym_Center>> ownedGyms= new HashMap<>();
    
    private static Map<Integer, Gym_Owner> gymOwners = new HashMap<>();
    private static int centerIdCounter = 1;
    
    SlotOperations slotop = new SlotOperations();
    
    public Gym_Owner getGymOwnerDetails(int ownerId) {
        return gymOwners.get(ownerId);
    }
    
    public Gym_Center registerGym(int ownerId, String gymName, String address, int numberOfSlots) {
        int centerId = centerIdCounter++;
        
        Gym_Center gymCenter = new Gym_Center();
        gymCenter.setCenter_id(centerId);
        gymCenter.setOwner_id(ownerId);
        gymCenter.setCenter_name(gymName);
        gymCenter.setAddress(address);
        gymCenter.setNo_of_slots(numberOfSlots);
        gymCenter.setApproved(false);

        Admin.setGymCenters(gymCenter);

        System.out.println("Generated Center ID: " + centerId);
        List<Gym_Center> al= ownedGyms.get(ownerId);
        if (al==null){
        	al=new ArrayList<Gym_Center>();
        }
        al.add(gymCenter);
        
        ownedGyms.put(ownerId,al);
        return gymCenter;
    }
    
    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int capacity) {
    	slotop.addSlots(centerId, startTime, endTime, capacity);
    	System.out.println("Slot with center ID " + centerId  + " added succesfully");
    	return true;
    }
    
    public boolean isApprovedOwner(int ownerId) {
    	GymOwnerDAOImpl isApprove = new GymOwnerDAOImpl();
    	return isApprove.isApprovedOwner(ownerId);
    }
    
    public boolean isApprovedCenter(int centerId) {
    	GymOwnerDAOImpl isApprove = new GymOwnerDAOImpl();
    	return isApprove.isApprovedCenter(centerId);
    }

    public void editGymDetails(int centerId, String centerName, String address, String slotsInput) {
        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();

        if (gymOptional.isPresent()) {
            Gym_Center gym = gymOptional.get();
            if (!centerName.isEmpty()) {
                gym.setCenter_name(centerName);
            }
            if (!address.isEmpty()) {
                gym.setAddress(address);
            }
            if (!slotsInput.isEmpty()) {
                int noOfSlots = Integer.parseInt(slotsInput);
                gym.setNo_of_slots(noOfSlots);
            }

            System.out.println("Gym details updated successfully!");
        } else {
            System.out.println("Gym Center not found!");
        }
    }

    public void registerCustomer(int centerId, int id, String name, String contact, String email, String password) {
        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();

        if (gymOptional.isPresent()) {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setContact(contact);
            customer.setEmail(email);
            customer.setName(name);
            customer.setPassword(password);
            registeredCustomers.computeIfAbsent(centerId, k -> new ArrayList<>()).add(customer);

            System.out.println("Customer registered successfully!");
        } else {
            System.out.println("Gym Center not found!");
        }
    }
}



