package com.continental.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.continental.helpers.MySQLHelper;
import com.sun.rowset.CachedRowSetImpl;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=MySQLHelper.getMySQLConnection();
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CachedRowSet crowSet;
		try {
			crowSet = new CachedRowSetImpl();
			crowSet.setUsername("root");
			crowSet.setPassword("vignesh");
			crowSet.setUrl("jdbc:mysql://localhost:3306/continentaldb");
			crowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);

			String queryString = "SELECT * FROM customer";
			crowSet.setCommand(queryString);

			crowSet.execute();
			crowSet.addRowSetListener(new RowSetListenerDemo());
			crowSet.next();
			
			
			
			
			
			if(crowSet.getInt(1)==145)  
				crowSet.updateString("Customer_Name","Sujitha");
			crowSet.updateRow();
			crowSet.acceptChanges(con);

			crowSet.moveToCurrentRow();
			while (crowSet.next()) {
			System.out.println("Customer Id: " + crowSet.getInt(1)); 
			System.out.println("Name: " + crowSet.getString(2));
			} 
       con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
