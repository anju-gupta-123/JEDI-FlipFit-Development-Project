package com.flipkart.bean;
import java.util.Date;
public class Booking {
	private int booking_id;
	private int user_id;
	private int slot_id;
	private Date booking_date;
	private String status;
	/**
	 * @return the booking_id
	 */
	public int getBooking_id() {
		return booking_id;
	}
	/**
	 * @param booking_id the booking_id to set
	 */
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the slot_id
	 */
	public int getSlot_id() {
		return slot_id;
	}
	/**
	 * @param slot_id the slot_id to set
	 */
	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}
	/**
	 * @return the booking_date
	 */
	public Date getBooking_date() {
		return booking_date;
	}
	/**
	 * @param booking_date the booking_date to set
	 */
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
