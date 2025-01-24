package com.flipkart.bean;

public class Slot {
	private int slotId;
	private int centerId;
	private int startTime;
	private int endTime;
	private int numberofseats;
	private int availableSeats;
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	/**
	 * @return the slotId
	 */
	public int getSlotId() {
		return slotId;
	}
	/**
	 * @param slotId the slotId to set
	 */
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	/**
	 * @return the centerId
	 */
	public int getCenterId() {
		return centerId;
	}
	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}
	/**
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the numberofseats
	 */
	public int getNumberofseats() {
		return numberofseats;
	}
	/**
	 * @param numberofseats the numberofseats to set
	 */
	public void setNumberofseats(int numberofseats) {
		this.numberofseats = numberofseats;
	}
	

}
