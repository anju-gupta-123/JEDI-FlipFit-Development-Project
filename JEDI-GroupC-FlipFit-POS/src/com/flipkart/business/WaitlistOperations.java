package com.flipkart.business;

import java.util.Date;
import com.flipkart.DAO.WaitlistDAOInterface;
import com.flipkart.bean.Waitlist;
import com.flipkart.DAO.*;

public class WaitlistOperations {
	WaitlistDAOInterface waitlistimpl= new WaitlistDAOImpl();
	public boolean addToWaitlist(int customerId, int slotId, Date utilDate )
	{
		Waitlist waitlist= new Waitlist();
		waitlist.setBookingDate(utilDate);
		waitlist.setSlot_Id(slotId);
		waitlist.setUser_Id(customerId);
		return waitlistimpl.addToWaitlist(waitlist);
	}

}
