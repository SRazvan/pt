package jdbcD.dao;


import java.util.*;
import java.sql.*;
import java.io.*;

import jdbcD.core.Order;

public class OrderDAO {

	private Connection myConn;
	
	public OrderDAO() throws Exception {
		
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
	
	public List<Order> getAllOrder() throws Exception {
		List<Order> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT order_id, customer_name, prod_name, order_prod_quantity, prod_quantity "
					+ " FROM order1 "
					+ " INNER JOIN customer ON order1.order_customer_id=customer.customer_id "
					+ " INNER JOIN product  ON order1.order_product_id=product.prod_id ");
			
			while (myRs.next()) {
				Order tempOrder = convertRowToOrder(myRs);
				list.add(tempOrder);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	public List<Order> searchOrder(String  id) throws Exception {
		List<Order> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			id += "%";
			
			myStmt = myConn.prepareStatement("SELECT order_id, customer_name, prod_name, order_prod_quantity, prod_quantity "
					+ " FROM order1 "
					+ " INNER JOIN customer ON order1.order_customer_id=customer.customer_id "
					+ " INNER JOIN product  ON order1.order_product_id=product.prod_id where customer_name like ?");
			
			
			myStmt.setString(1, id);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Order tempOrder = convertRowToOrder(myRs);
				list.add(tempOrder);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Order convertRowToOrder(ResultSet myRs) throws SQLException {
		
	
		
		int order_id = myRs.getInt("order_id");
		String customer_name=myRs.getString("customer_name");
		String prod_name=myRs.getString("prod_name");
		int prod_quantity=myRs.getInt("prod_quantity");
		int order_prod_quantity= myRs.getInt("order_prod_quantity");
		
		Order tempOrder = new Order(order_id, customer_name, prod_name,order_prod_quantity,prod_quantity );
		
		return tempOrder;
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
	
	
	public void addOrder(Order theOrder) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into order1 "
					+ " (order_id, order_customer_id, order_product_id, order_prod_quantity) "
					+ " values (?, ?, ?, ?)");
			
			// set params
			
			myStmt.setInt(1, theOrder.getOrderId());
			myStmt.setInt(2, theOrder.getOrderCustomerId());
			myStmt.setInt(3, theOrder.getOrderProdId());
			myStmt.setInt(4, theOrder.getOrderProdQuantity());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void updateOrder(Order theOrder) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement(" update order1 "
					+ " set order_customer_id=?, order_product_id=?, order_prod_quantity=? "
					+ "where order_id=? ");
			
			// set params
		 	
			myStmt.setInt(1, theOrder.getOrderCustomerId());
			myStmt.setInt(2, theOrder.getOrderProdId());
			myStmt.setInt(3, theOrder.getOrderProdQuantity());
			myStmt.setInt(4, theOrder.getOrderId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void deleteOrder(int orderId) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from order1 where order_id=?");
			
			// set param
			myStmt.setInt(1, orderId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
	}

	public static void main(String[] args) throws Exception {
		
		OrderDAO dao = new OrderDAO();
		//System.out.println(dao.searchOrder("j"));
	}
}
