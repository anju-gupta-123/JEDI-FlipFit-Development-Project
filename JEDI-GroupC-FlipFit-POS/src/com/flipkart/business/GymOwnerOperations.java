package com.flipkart.business;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Owner;

import java.util.*;

public class GymOwnerOperations {
    public List<Gym_Center> gymCenters = new ArrayList<>();
    public Map<Integer, List<Customer>> registeredCustomers = new HashMap<>();
    public List<Gym_Center> approvedgymCenters = new ArrayList<>();
    public void registerGymOwner(int id, String contact, String email, String name, String password) {
        Gym_Owner gymowner  = new Gym_Owner();
        gymowner.setId(id);
        gymowner.setContact(contact);
        gymowner.setEmail(email);
        gymowner.setName(name);
        gymowner.setPassword(password);
        
    }
    public void registerGym(int centerId, int ownerId, int noOfSlots, String centerName, String address) {
        Gym_Center gym = new Gym_Center();
        gym.setCenter_id(centerId);
        gym.setOwner_id(ownerId);
        gym.setCenter_name(centerName);
        gym.setAddress(address);
        gym.setNo_of_slots(noOfSlots);
        
//       Admin.setGym_Center(gym);
       
        
        System.out.println("Gym registered successfully!");
    }

    public void addSlots(int centerId, int additionalSlots) {

        Optional<Gym_Center> gymOptional = gymCenters.stream()
                .filter(g -> g.getCenter_id() == centerId)
                .findFirst();
        if (gymOptional.isPresent()) {
            Gym_Center gym = gymOptional.get();
            gym.setNo_of_slots(gym.getNo_of_slots() + additionalSlots);

            System.out.println("Slots added successfully! Total slots: " + gym.getNo_of_slots());
        } else {
            System.out.println("Gym Center not found!");
        }
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
