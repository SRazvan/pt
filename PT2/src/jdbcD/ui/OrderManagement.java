package jdbcD.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.SwingConstants;

import jdbcD.core.Customer;
import jdbcD.core.Order;
import jdbcD.core.Product;
import jdbcD.dao.CustomerDAO;
import jdbcD.dao.OrderDAO;
import jdbcD.dao.ProductDAO;

public class OrderManagement extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTable table;
	
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private OrderDAO orderDAO;
	
	private JTextField prodtextField;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderManagement frame = new OrderManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderManagement() {
		
		try{
			customerDAO = new CustomerDAO();
			productDAO = new ProductDAO();
			orderDAO = new OrderDAO();
		}catch(Exception exc)
		{
			
			JOptionPane.showMessageDialog(this, "Error: "+exc,"Error",JOptionPane.ERROR_MESSAGE);
		}
		setTitle("Order Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("602px:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("28px"),
				RowSpec.decode("328px:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "2, 2, fill, top");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("98px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("65px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		JLabel lblNewLabel = new JLabel("Enter name:");
		panel.add(lblNewLabel, "2, 2, left, center");
		
		nameTextField = new JTextField();
		panel.add(nameTextField, "3, 2, 5, 1, left, center");
		nameTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try {
					String name = nameTextField.getText();

					List<Customer> customers = null;

					if (name != null && name.trim().length() > 0) {
						customers = customerDAO.searchCustomer(name);
					} else {
						customers = customerDAO.getAllCustomer();
					}
					
					// create the model and update the "table"
					CustomerTableModel model = new CustomerTableModel(customers);
					
					table.setModel(model);
					
					/*
					for (Customer temp : customers) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}

				
			}
		});
		panel.add(btnNewButton, "8, 2, left, top");
		
		JLabel lblEnterProduct = new JLabel("Enter product:");
		panel.add(lblEnterProduct, "18, 2, right, default");
		
		prodtextField = new JTextField();
		panel.add(prodtextField, "20, 2, fill, default");
		prodtextField.setColumns(10);
		
		JButton btnSearchProd = new JButton("Search Prod");
		btnSearchProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get name
				
				// cal dao and get prods from last name
				
				// if prod is empty then get all prods
				
				// print out prods
				
				try {
					String name = prodtextField.getText();
					//System.out.println(name);
					List<Product> product = null;
					

					if (name != null && name.trim().length() > 0) {
						product = productDAO.searchProduct(name);
					} else {
						product = productDAO.getAllProduct();
					}
					
					// create the model and update the "table"
					ProductTableModel model2 = new ProductTableModel(product);
					
					table_1.setModel(model2);
					
					/*
					for (Product temp1 : product) {
						System.out.println(temp1);
					}*/
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}

				
			}
		});
		panel.add(btnSearchProd, "22, 2");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "2, 3, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(OrderManagement.this, 
								"You must select an customer", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							OrderManagement.this, "Delete this customer?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current customer
					Customer tempCustomer= (Customer) table.getValueAt(row, CustomerTableModel.OBJECT_COL);

					// delete the customer
					customerDAO.deleteCustomer(tempCustomer.getId());

					// refresh GUI
					refreshCustomersView();

					// show success message
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Customer deleted succesfully.", "Customer Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Error deleting customer: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}

		});
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(OrderManagement.this, "You must select a customer", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current customer
				Customer tempCustomer = (Customer) table.getValueAt(row, CustomerTableModel.OBJECT_COL);
				
				// create dialog
				AddCustomerDialog dialog = new AddCustomerDialog(OrderManagement.this, customerDAO, 
															tempCustomer, true);

				// show dialog
				dialog.setVisible(true);
			
			}

		});
		
		JButton btnViewCustomers = new JButton("View Customers");
		btnViewCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					List<Customer> customers = null;

					customers = customerDAO.getAllCustomer();
			
					// create the model and update the "table"
					CustomerTableModel model = new CustomerTableModel(customers);
			
					table.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel_1.add(btnViewCustomers, "2, 2");
		
		JButton btnViewProducts = new JButton("View Products");
		btnViewProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					List<Product> product = null;

					product = productDAO.getAllProduct();
			
					// create the model and update the "table"
					ProductTableModel model2 = new ProductTableModel(product);
			
					table_1.setModel(model2);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel_1.add(btnViewProducts, "4, 2");
		
		JButton btnNewButton_1 = new JButton("Add Customer");
		panel_1.add(btnNewButton_1, "2, 4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				AddCustomerDialog dialog = new AddCustomerDialog(OrderManagement.this, customerDAO);
				// show dialog
				dialog.setVisible(true);
			}
		});
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// create dialog
				AddProductDialog dialog1 = new AddProductDialog(OrderManagement.this, productDAO);
				// show dialog
				dialog1.setVisible(true);
			}
		});
		panel_1.add(btnAddProduct, "4, 4");
		panel_1.add(btnEditCustomer, "2, 6");
		
		JButton btnEditProduct = new JButton("Edit Product");
		btnEditProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get the selected item
				int row = table_1.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(OrderManagement.this, "You must select a product", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current product
				Product tempProduct = (Product) table_1.getValueAt(row, ProductTableModel.OBJECT_COL);
				
				// create dialog
				AddProductDialog dialog = new AddProductDialog(OrderManagement.this, productDAO, 
															tempProduct, true);

				// show dialog
				dialog.setVisible(true);
				
			}
		});
		panel_1.add(btnEditProduct, "4, 6");
		panel_1.add(btnDeleteCustomer, "2, 8");
		
		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get the selected row
					int row = table_1.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(OrderManagement.this, 
								"You must select a product", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							OrderManagement.this, "Delete this product?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current product
					Product tempProduct = (Product) table_1.getValueAt(row, CustomerTableModel.OBJECT_COL);

					// delete the product
					productDAO.deleteProduct(tempProduct.getId());

					// refresh GUI
					refreshCustomersView();

					// show success message
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Product deleted succesfully.", "Product Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Error deleting product: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel_1.add(btnDeleteProduct, "4, 8");
		
		
		// FIRST PANEL
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "2, 10, center, center");
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "4, 10, center, center");
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					List<Order> orders = null;

					orders = orderDAO.getAllOrder();
			
					// create the model and update the "table"
					OrderTableModel model3 = new OrderTableModel(orders);
			
					table_2.setModel(model3);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel_1.add(btnViewOrders, "2, 12");
		
		JButton btnEditOrder = new JButton("Edit Order");
		btnEditOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get the selected item
				int row = table_2.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(OrderManagement.this, "You must select an order", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current order
				Order tempOrder = (Order) table_2.getValueAt(row, OrderTableModel.OBJECT_COL);
				
				// create dialog
				AddOrderDialog dialog = new AddOrderDialog(OrderManagement.this, orderDAO, 
															tempOrder, true);

				// show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnEditOrder, "4, 12");
		
		JButton btnMakeOrder = new JButton("Make Order");
		btnMakeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// create dialog
				AddOrderDialog dialog2 = new AddOrderDialog(OrderManagement.this, orderDAO);
				// show dialog
				dialog2.setVisible(true);
				
			}
		});
		panel_1.add(btnMakeOrder, "2, 14");
		
		JButton btnDeleteOrder = new JButton("Delete Order");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// get the selected row
					int row = table_2.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(OrderManagement.this, 
								"You must select an order", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							OrderManagement.this, "Delete this order?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current order
					Order tempOrder = (Order) table_2.getValueAt(row, OrderTableModel.OBJECT_COL);

					// delete the order
					orderDAO.deleteOrder(tempOrder.getOrderId());

					// refresh GUI
					refreshCustomersView();

					// show success message
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Order deleted succesfully.", "Order Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(OrderManagement.this,
							"Error deleting order: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(btnDeleteOrder, "4, 14");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		contentPane.add(scrollPane_2, "2, 5, fill, fill");
		
		table_2 = new JTable();
		scrollPane_2.setColumnHeaderView(table_2);
		
		//table2 = new JTable();
		//scrollPane.setViewportView(table2);
	}
	public void refreshCustomersView() {

		try {
			List<Customer> customers = customerDAO.getAllCustomer();

			// create the model and update the "table"
			CustomerTableModel model = new CustomerTableModel(customers);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

}
