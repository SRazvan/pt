package jdbcD.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import jdbcD.core.Customer;
import jdbcD.dao.CustomerDAO;

public class AddCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField eMailtextField;
	private JTextField IDtextField;
	
	private CustomerDAO customerDAO;
	
	private OrderManagement OrderManagement;
	
	private Customer previousCustomer=null;
	private boolean updateMode = false;
	
	public AddCustomerDialog(OrderManagement theOrderManagement,
			CustomerDAO theCustomerDAO, Customer thePreviousCustomer, boolean theUpdateMode) 
	{
		this();
		customerDAO = theCustomerDAO;
		OrderManagement = theOrderManagement;

		previousCustomer = thePreviousCustomer;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Customer");
			
			populateGui(previousCustomer);
		}
	}
	
	private void populateGui(Customer theCustomer) {

		nameTextField.setText(theCustomer.getName());
		eMailtextField.setText(theCustomer.getEmail());	
	}


	
	public AddCustomerDialog(OrderManagement theOrderManagement,CustomerDAO theCustomerDAO)
	{
		this();
		customerDAO = theCustomerDAO;
		OrderManagement=theOrderManagement;
	}

	/**
	 * Create the dialog.
	 */
	public AddCustomerDialog() {
		setTitle("Add Customer");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblId = new JLabel("   ID");
			contentPanel.add(lblId, "2, 2");
		}
		{
			IDtextField = new JTextField();
			contentPanel.add(IDtextField, "6, 2, fill, default");
			IDtextField.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name");
			contentPanel.add(lblName, "2, 6, right, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "6, 6, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("E-Mail");
			contentPanel.add(lblEmail, "2, 10, right, default");
		}
		{
			eMailtextField = new JTextField();
			contentPanel.add(eMailtextField, "6, 10, fill, default");
			eMailtextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveCustomer();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}
	
protected void saveCustomer() {

	// get the customer info from gui
	int id = Integer.parseInt(IDtextField.getText());
	String name = nameTextField.getText();
	String email = eMailtextField.getText();

	Customer tempCustomer = null;

	if (updateMode) {
		tempCustomer = previousCustomer;
		
		tempCustomer.setId(id);
		tempCustomer.setName(name);
		tempCustomer.setEmail(email);
		
	} else {
		tempCustomer = new Customer(id, name, email);
	}

	try {
		// save to the database
		if (updateMode) {
			customerDAO.updateCustomer(tempCustomer);
		} else {
			customerDAO.addCustomer(tempCustomer);
		}

		// close dialog
		setVisible(false);
		dispose();

		// refresh gui list
		OrderManagement.refreshCustomersView();

		// show success message
		JOptionPane.showMessageDialog(OrderManagement,
				"Customer saved succesfully.", "Customer Saved",
				JOptionPane.INFORMATION_MESSAGE);
	} catch (Exception exc) {
		JOptionPane.showMessageDialog(OrderManagement,
				"Error saving customer: " + exc.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}

}
