package jdbcD.dao;


import java.util.*;
import java.sql.*;
import java.io.*;

import jdbcD.core.Product;

public class ProductDAO {

	private Connection myConn;
	
	public ProductDAO() throws Exception {
		
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
	
	public List<Product> getAllProduct() throws Exception {
		List<Product> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from product");
			
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
				//System.out.println(tempProduct+" ");
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Product> getAllProductQuantities() throws Exception {
		List<Product> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select prod_quantity from product");
			
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
				//System.out.println(tempProduct+" ");
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Product> searchProduct(String name) throws Exception {
		List<Product> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name += "%";
			myStmt = myConn.prepareStatement("select * from product where prod_name like ?");
			
			myStmt.setString(1, name);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Product> searchProductID(int i) throws Exception {
		List<Product> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			//name += "%";
			myStmt = myConn.prepareStatement("select * from product where prod_id like ?");
			
			myStmt.setInt(1, i);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Product tempProduct = convertRowToProduct(myRs);
				list.add(tempProduct);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Product convertRowToProduct(ResultSet myRs) throws SQLException {
		
		int prod_id = myRs.getInt("prod_id");
		String prod_name = myRs.getString("prod_name");
		//String firstName = myRs.getString("first_name");
		int prod_quantity = myRs.getInt("prod_quantity");
		
		Product tempProduct = new Product(prod_id, prod_name, prod_quantity);
		
		return tempProduct;
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
	
	public void addProduct(Product theProduct) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into product"
					+ " (prod_id, prod_name, prod_quantity)"
					+ " values (?, ?, ?)");
			
			// set params
			
			myStmt.setInt(1, theProduct.getId());
			myStmt.setString(2, theProduct.getProdName());
			myStmt.setInt(3, theProduct.getProdQuantity());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void updateProduct(Product theProduct) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update product"
					+ " set prod_name=?, prod_quantity=?"
					+ " where prod_id=?");
			
			// set params
		 	//myStmt.setInt(1, theCustomer.getId());
			myStmt.setString(1, theProduct.getProdName());
			myStmt.setInt(2, theProduct.getProdQuantity());
			myStmt.setInt(3, theProduct.getId());
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
		
	}
	
	public void deleteProduct(int productId) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from product where prod_id=?");
			
			// set param
			myStmt.setInt(1, productId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt,myRs);
		}
	}

	public static void main(String[] args) throws Exception {
		
		ProductDAO dao = new ProductDAO();
		//System.out.println(dao.searchProduct("TV"));
		//System.out.println(dao.searchProductID(2));
		List<Product> products = null;
		products = dao.searchProductID(2);
		
		for (Product temp : products) {
			System.out.println(temp.getProdQuantity()+" works ");
		}

		
		System.out.println(dao.getAllProduct());
		
	}
}
