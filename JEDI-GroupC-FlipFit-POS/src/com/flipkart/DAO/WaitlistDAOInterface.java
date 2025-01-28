package com.flipkart.DAO;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Waitlist;

public interface WaitlistDAOInterface {
		public boolean addToWaitlist(Waitlist waitlist);
		public boolean promoteWaitlist(Booking booking);
		
}
