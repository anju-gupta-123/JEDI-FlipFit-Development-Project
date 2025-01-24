package com.flipkart.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.flipkart.DAO.GymOwnerDAOImpl;
import com.flipkart.bean.Slot;
public class SlotOperations {
	private static int slotIdCounter = 1;
	public static Map<Integer,List<Slot>> gymslot= new HashMap<>();
<<<<<<< HEAD
	public void addSlots(int centerid,int startTime, int endTime,int noofseats)
=======
	GymOwnerDAOImpl gmDaoImpl = new GymOwnerDAOImpl();
	public boolean addSlots(int centerid, LocalTime startTime, LocalTime endTime, int noofseats)
>>>>>>> 7a9a387 (fixing gymOwner)
	{
		Slot newSlot = new Slot();
        newSlot.setSlotId(slotIdCounter++); // Auto-generate slot ID
        newSlot.setCenterId(centerid);
        newSlot.setStartTime(startTime);
        newSlot.setEndTime(endTime);
        List<Slot> sl= gymslot.get(centerid);
        if (sl==null)
        {
        	sl= new ArrayList<Slot>();
        }
        sl.add(newSlot);
        gymslot.put(centerid, sl);
        return gmDaoImpl.addSlot(newSlot);
	}
	

}
