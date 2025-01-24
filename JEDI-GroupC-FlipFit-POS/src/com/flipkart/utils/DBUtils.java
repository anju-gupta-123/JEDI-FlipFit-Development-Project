package com.flipkart.utils;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
	
	
	public static Connection connect() throws Exception {
		Properties props = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/config.properties");
		//System.out.println(System.getProperty("user.dir")+"/src/config.properties");
		
		props.load(fs);
		
		String url = props.getProperty("DB_URL");
		String username = props.getProperty("DB_USERNAME");
		String password = props.getProperty("DB_PASSWORD");
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection(url, username, password);
		
		return con;
		
	}
	


}
