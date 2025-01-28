package com.flipkart.business;

import java.util.List;


import com.flipkart.DAO.NotificationsDAOInterface;
import com.flipkart.DAO.NotificationsDAOImpl;

public class NotificationsOperations {
	NotificationsDAOInterface notifimpl = new NotificationsDAOImpl();
	public List<String> viewNotifications(int customerid)
	{
		return notifimpl.viewNotifications(customerid);
	}

}
