package com.flipkart.business;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym_Owner;

import com.flipkart.bean.Admin;
import java.util.*;
import java.time.LocalTime;
public class GymOwnerOperations {
    public List<Gym_Center> gymCenters = new ArrayList<>();
    public Map<Integer, List<Customer>> registeredCustomers = new HashMap<>();
    public List<Gym_Center> approvedgymCenters = new ArrayList<>();
    public static Map<Integer,List<Gym_Center>> ownedGyms= new HashMap<>();
    
    private static Map<Integer, Gym_Owner> gymOwners = new HashMap<>();
    
    SlotOperations slotop= new SlotOperations();
 // Static counter for generating unique center IDs
    private static int centerIdCounter = 1;

    // Simulating pre-existing gym owners
    static {
        Gym_Owner owner1 = new Gym_Owner();
        owner1.setId(1);
        owner1.setName("John Doe");
        owner1.setEmail("john@example.com");
        owner1.setContact("9876543210");
        owner1.setAadharCard("1234-5678-9101");
        owner1.setGSTNo("GST-001");
        owner1.setApproved(true);

        Gym_Owner owner2 = new Gym_Owner();
        owner2.setId(2);
        owner2.setName("Jane Smith");
        owner2.setEmail("jane@example.com");
        owner2.setContact("8765432109");
        owner2.setAadharCard("5678-1234-9101");
        owner2.setGSTNo("GST-002");
        owner2.setApproved(false);

        gymOwners.put(owner1.getId(), owner1);
        gymOwners.put(owner2.getId(), owner2);
    }

    /**
     * Retrieves the details of a gym owner based on their ID.
     * @param ownerId The ID of the gym owner
     * @return The Gym_Owner object, or null if not found
     */
    public Gym_Owner getGymOwnerDetails(int ownerId) {
        return gymOwners.get(ownerId);
    }
    
 
    
    public Gym_Center registerGym(int ownerId, String gymName, String address, int numberOfSlots) {
        // Generating a new center ID
        int centerId = centerIdCounter++;
        
        // Creating a new Gym_Center object
        Gym_Center gymCenter = new Gym_Center();
        gymCenter.setCenter_id(centerId); // Assign generated center ID
        gymCenter.setOwner_id(ownerId);  // Assign provided owner ID
        gymCenter.setCenter_name(gymName);
        gymCenter.setAddress(address);
        gymCenter.setNo_of_slots(numberOfSlots);
        gymCenter.setApproved(false); // Initially, the gym is not approved

        // Adding the gym center to the admin's pending list for approval
        Admin.setGymCenters(gymCenter);

        System.out.println("Generated Center ID: " + centerId);
        List<Gym_Center> al= ownedGyms.get(ownerId);
        if (al==null)
        {
        	al=new ArrayList<Gym_Center>();
        }
        al.add(gymCenter);
        
        ownedGyms.put(ownerId,al);
        return gymCenter; // Returning the created object
    }
    
    public void registerGymOwner(int id, String contact, String email, String name, String password) {
        Gym_Owner gymowner  = new Gym_Owner();
        gymowner.setId(id);
        gymowner.setContact(contact);
        gymowner.setEmail(email);
        gymowner.setName(name);
        gymowner.setPassword(password);
        
    }
    

    public boolean addSlot(int ownerid, LocalTime startTime, LocalTime endTime) {
        // Check for overlap with existing slots
    	List<Gym_Center> gymcenterlist= ownedGyms.get(ownerid);
    	Gym_Center center=gymcenterlist.getFirst();
    	int centerid= center.getCenter_id();
    	int noofseats= center.getNo_of_slots();
    	slotop.addSlots(centerid, startTime, endTime, noofseats);
    	
    	
    	
    	return true;
    	
        
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

    
}

