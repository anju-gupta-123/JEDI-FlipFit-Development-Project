package com.flipkart.business;

import com.flipkart.DAO.GymOwnerDAOImpl;
import java.time.LocalTime;
import java.util.*;


import com.flipkart.bean.Slot;
public class SlotOperations {
	private static int slotIdCounter = 1;
	public static Map<Integer,List<Slot>> gymslot= new HashMap<>();
	
	public void addSlots(int centerId, LocalTime startTime, LocalTime endTime, int capacity){
		GymOwnerDAOImpl addSlot = new GymOwnerDAOImpl();
		Slot newSlot = new Slot();
		
        newSlot.setSlotId(slotIdCounter++);
        newSlot.setCenterId(centerId);
        newSlot.setStartTime(startTime);
        newSlot.setEndTime(endTime);
        newSlot.setNumberofseats(capacity);
        
        List<Slot> sl= gymslot.get(centerId);
        if (sl==null){
        	sl= new ArrayList<Slot>();
        }
        sl.add(newSlot);
        gymslot.put(centerId, sl);
        
        addSlot.addSlot(newSlot);
	}
}
