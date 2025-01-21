package com.flipkart.bean;
import java.time.LocalTime;
public class Slot {
	private int slotId;
	private int centerId;
	private LocalTime startTime;
	private LocalTime endTime;
	private int numberofseats;
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
	public LocalTime getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
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
