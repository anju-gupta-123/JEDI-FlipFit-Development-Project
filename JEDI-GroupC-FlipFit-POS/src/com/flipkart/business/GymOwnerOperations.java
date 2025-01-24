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

    

    private static int centerIdCounter = 1;

    

    SlotOperations slotop = new SlotOperations();

    GymOwnerDAOImpl gymsDao = new GymOwnerDAOImpl(); 

    

    public Gym_Owner getGymOwnerDetails(String ownerEmail) {

    	Gym_Owner gymOwner = gymsDao.getGymOwnerDetails(ownerEmail);

        return gymOwner;

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


        gymsDao.registerGymCenter(gymCenter);
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

    

    public List<Gym_Center> getCenterDetails(int ownerId){

    	List<Gym_Center> centerDetails = gymsDao.getCenterDetails(ownerId);

    	return centerDetails;

    }

    

    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int capacity) {

    	if(slotop.addSlots(centerId, startTime, endTime, capacity)) {

    		System.out.println("Slot with center ID " + centerId  + " added succesfully");

        	return true;

    	}

    	return false;

    }

    

    public boolean isApprovedOwner(int ownerId) {

    	GymOwnerDAOImpl isApprove = new GymOwnerDAOImpl();

    	return isApprove.isApprovedOwner(ownerId);

    }

    

    public boolean isApprovedCenter(int centerId) {

    	GymOwnerDAOImpl isApprove = new GymOwnerDAOImpl();

    	return isApprove.isApprovedCenter(centerId);

    }

}