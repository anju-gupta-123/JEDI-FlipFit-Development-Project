package com.flipkart.business;
import com.flipkart.DAO.SlotDAOInterface;
import com.flipkart.DAO.SlotDAOImpl;
import com.flipkart.bean.Slot;
public class SlotOperations {
	SlotDAOInterface slotimpl= new SlotDAOImpl();
	


	public boolean addSlots(int centerid,int startTime, int endTime,int noofseats)
	{
		

		
		Slot newSlot = new Slot();
        newSlot.setCenterId(centerid);
        newSlot.setStartTime(startTime);
        newSlot.setEndTime(endTime);
        newSlot.setNumberofseats(noofseats);
        return slotimpl.addSlot(newSlot);
        
	}
	
	

}
