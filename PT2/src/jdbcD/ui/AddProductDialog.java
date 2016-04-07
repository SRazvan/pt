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

import jdbcD.core.Product;
import jdbcD.dao.ProductDAO;




public class AddProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField eMailtextField;
	private JTextField IDtextField;
	
	private ProductDAO productDAO;
	
	private OrderManagement productSearchApp;
	
	private Product previousProduct=null;
	private boolean updateMode = false;
	
	public AddProductDialog(OrderManagement theProductSearchApp,
			ProductDAO theProductDAO, Product thePreviousProduct, boolean theUpdateMode) 
	{
		this();
		productDAO = theProductDAO;
		productSearchApp = theProductSearchApp;

		previousProduct = thePreviousProduct;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Product");
			
			populateGui(previousProduct);
		}
	}
	
	private void populateGui(Product theProduct) {

		nameTextField.setText(theProduct.getProdName());
		//eMailtextField.setText(theProduct.getProdQuantity());	
	}


	
	public AddProductDialog(OrderManagement theProductSearchApp,ProductDAO theProductDAO)
	{
		this();
		productDAO = theProductDAO;
		productSearchApp=theProductSearchApp;
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AddProductDialog() {
		setTitle("Add Product");
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
			JLabel lblName = new JLabel("Prod Name");
			contentPanel.add(lblName, "2, 6, right, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "6, 6, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Quantity");
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
						saveProduct();
						
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
		
	protected void saveProduct() {

	// get the product info from gui
	int id = Integer.parseInt(IDtextField.getText());
	String name = nameTextField.getText();
	int quantity = Integer.parseInt(eMailtextField.getText());

	Product tempProduct = null;

	if (updateMode) {
		tempProduct = previousProduct;
		
		tempProduct.setId(id);
		tempProduct.setProdName(name);
		tempProduct.setProdQuantity(quantity);
		
	} else {
		tempProduct = new Product(id, name, quantity);
	}

	try {
		// save to the database
		if (updateMode) {
			productDAO.updateProduct(tempProduct);
		} else {
			productDAO.addProduct(tempProduct);
		}

		// close dialog
		setVisible(false);
		dispose();

		// refresh gui list
		productSearchApp.refreshCustomersView();

		// show success message
		JOptionPane.showMessageDialog(productSearchApp,
				"Product saved succesfully.", "Product Saved",
				JOptionPane.INFORMATION_MESSAGE);
	} catch (Exception exc) {
		JOptionPane.showMessageDialog(productSearchApp,
				"Error saving product: " + exc.getMessage(), "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}

}
