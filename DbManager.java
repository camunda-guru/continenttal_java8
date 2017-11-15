package com.continental.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.continental.entities.Customer;
import com.continental.helpers.MySQLHelper;

public class DbManager {

	public static boolean testConnection()
	{
		if(MySQLHelper.getMySQLConnection()!=null)
			return true;
		else
			return false;
		
	}

	public static List<Customer> getCustomerList()
	{
		
		Connection conn = MySQLHelper.getMySQLConnection();
		String query="select * from customer";
		ResultSet rs=null;
		List<Customer> customerList=new ArrayList<Customer>();
		Customer customer=null;
		try {
			Statement stmt = conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				customer=new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customerList.add(customer);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerList;
	}
	
	
	public static void addCustomer(Customer customer)
	{
		int records=0;
		/*
		Connection conn = MySQLHelper.getMySQLConnection();
		try {
			PreparedStatement pre = conn.prepareStatement("insert into customer values(?,?,?)");
			pre.setInt(1, customer.getCustomerId());
			pre.setString(2, customer.getName());			
			Blob blob = conn.createBlob();
			blob.setBytes(1, customer.getPhoto());		
			pre.setBlob(3, blob);	
			records = pre.executeUpdate();
					
			System.out.println("Number of records updated--->"+records);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
	public static void addCustomers(List<Customer> customers)
	{
		
		Connection conn = MySQLHelper.getMySQLConnection();
		PreparedStatement pre;
		try {
			pre = conn.prepareStatement("insert into customer values(?,?,?)");
			for(Customer customer : customers)
			{
				try {
					
					pre.setInt(1, customer.getCustomerId());
					pre.setString(2, customer.getName());
					File file = new File(customer.getPhotoPath());
					FileInputStream inStream=null;
					
					inStream = new FileInputStream(file);
					pre.setBinaryStream(3, inStream,(int)file.length());
					pre.addBatch();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
			try {
				int[] records = pre.executeBatch();
				System.out.println(records);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		};
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //System.out.println( testConnection());
		//System.out.println(getCustomerList().size());
		
		List<Customer> customerList=new ArrayList<Customer>();
		customerList.add(new Customer(1100,"Arun","C:/Users/BALASUBRAMANIAM/Pictures/butterfly.jpg"));
		customerList.add(new Customer(1101,"Anand","C:/Users/BALASUBRAMANIAM/Pictures/balloon.jpg"));
		addCustomers(customerList);
		
/*
		Customer customer =new Customer();
		customer.setCustomerId(1001);
		customer.setName("Jyothi");
	
		Path path =Paths.get("C:/Users/BALASUBRAMANIAM/Pictures/facebook.jpg");
		     
		   try {
			customer.setPhoto(Files.readAllBytes(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   addCustomer(customer);
		  */ 
	}

}
