package com.revature.project1.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	
	public static Connection getConnection () {
		try {
		    Class.forName("org.postgresql.Driver");           
		    Connection connection = DriverManager.getConnection("jdbc:postgresql://hocuspocusdb.chk74ed1m4q2.us-east-2.rds.amazonaws.com:5432/postgres", "postgres", "8Ballpool!");
		    
		    //Connection connection = DriverManager.getConnection(details);
		    return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
			return null;
		}
	}

}
