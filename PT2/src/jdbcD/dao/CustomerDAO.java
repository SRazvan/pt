package jdbcD.dao;


import java.util.*;
import java.sql.*;
import java.io.*;

import jdbcD.core.Customer;

public class CustomerDAO {

	private Connection myConn;
	
	public CustomerDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("om_properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		//System.out.println("DB connection successful to: " + dburl);
	}
	
	
	public void addCustomer(Customer theCustomer) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into customer"
					+ " (customer_id, customer_name, customer_mail)"
					+ " values (?, ?, ?)");
			
			// set params
			myStmt.setInt(1, theCustomer.getId());
			myStmt.setString(2, theCustomer.getName());
			myStmt.setString(3, theCustomer.getEmail());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void updateCustomer(Customer theCustomer) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update customer"
					+ " set customer_name=?, customer_mail=?"
					+ " where customer_id=?");
			
			// set params
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getEmail());
			myStmt.setInt(3, theCustomer.getId());
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void deleteCustomer(int customerId) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from customer where customer_id=?");
			
			// set param
			myStmt.setInt(1, customerId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
	}



	
	public List<Customer> getAllCustomer() throws Exception {
		List<Customer> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from customer");
			
			while (myRs.next()) {
				Customer tempCustomer = convertRowToCustomer(myRs);
				list.add(tempCustomer);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Customer> searchCustomer(String name) throws Exception {
		List<Customer> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name += "%";
			myStmt = myConn.prepareStatement("select * from customer where customer_name like ?");
			
			myStmt.setString(1, name);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Customer tempCustomer = convertRowToCustomer(myRs);
				list.add(tempCustomer);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Customer convertRowToCustomer(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("customer_id");
		String name = myRs.getString("customer_name");
		String email = myRs.getString("customer_mail");
		
		Customer tempCustomer = new Customer(id, name, email);
		
		return tempCustomer;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		CustomerDAO dao = new CustomerDAO();
		System.out.println(dao.searchCustomer("Johnny"));

		System.out.println(dao.getAllCustomer());
		
	}
}
