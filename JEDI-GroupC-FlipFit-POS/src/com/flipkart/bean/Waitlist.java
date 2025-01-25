package com.flipkart.bean;

import java.util.Date;

public class Waitlist {
		private int user_Id;
		private int slot_Id;
		private Date bookingDate;
		/**
		 * @return the bookingDate
		 */
		public Date getBookingDate() {
			return bookingDate;
		}
		/**
		 * @param bookingDate the bookingDate to set
		 */
		public void setBookingDate(Date bookingDate) {
			this.bookingDate = bookingDate;
		}
		/**
		 * @return the user_Id
		 */
		public int getUser_Id() {
			return user_Id;
		}
		/**
		 * @param user_Id the user_Id to set
		 */
		public void setUser_Id(int user_Id) {
			this.user_Id = user_Id;
		}
		/**
		 * @return the slot_Id
		 */
		public int getSlot_Id() {
			return slot_Id;
		}
		/**
		 * @param slot_Id the slot_Id to set
		 */
		public void setSlot_Id(int slot_Id) {
			this.slot_Id = slot_Id;
		}
		
}
