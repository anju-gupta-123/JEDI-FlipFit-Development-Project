package com.flipkart.DAO;

import java.util.List;

public interface NotificationsDAOInterface {
	public List<String> viewNotifications(int customerid);
	public boolean createNoti(int customerid, String message);
}
