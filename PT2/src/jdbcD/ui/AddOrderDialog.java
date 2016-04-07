package jdbcD.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import java.util.List;

import jdbcD.core.Order;
import jdbcD.core.Product;
import jdbcD.dao.OrderDAO;
import jdbcD.dao.ProductDAO;

public class AddOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField ordertextField;
	private JTextField custNtextField_1;
	private JTextField prodNtextField_2;
	private JTextField orderQtextField_3;
	
private OrderDAO orderDAO;
	
	private OrderManagement orderSearchApp;
	
	private Order previousOrder=null;
	private boolean updateMode = false;
	
	public AddOrderDialog(OrderManagement theOrderSearchApp,
			OrderDAO theOrderDAO, Order thePreviousOrder, boolean theUpdateMode) 
	{
		this();
		orderDAO = theOrderDAO;
		orderSearchApp = theOrderSearchApp;

		previousOrder = thePreviousOrder;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Order");
			
			populateGui(previousOrder);
		}
	}
	
	public AddOrderDialog(OrderManagement theOrderSearchApp,OrderDAO theOrderDAO)
	{
		this();
		orderDAO = theOrderDAO;
		orderSearchApp=theOrderSearchApp;
	}
	
	private void populateGui(Order theOrder) {

		//ordertextField.setText(theOrder.getOrderId());
		//prodNtextField_2.setText(theOrder.getProdName());
		//eMailtextField.setText(theProduct.getProdQuantity());	
	}

	/**
	 * Create the dialog.
	 */
	public AddOrderDialog() {
		setTitle("Order");
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
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("OrderID");
			contentPanel.add(lblNewLabel, "4, 2");
		}
		{
			ordertextField = new JTextField();
			contentPanel.add(ordertextField, "8, 2, fill, default");
			ordertextField.setColumns(10);
		}
		{
			JLabel lblCustname = new JLabel("CustID");
			contentPanel.add(lblCustname, "4, 6");
		}
		{
			custNtextField_1 = new JTextField();
			contentPanel.add(custNtextField_1, "8, 6, fill, default");
			custNtextField_1.setColumns(10);
		}
		{
			JLabel lblProdname = new JLabel("ProdID");
			contentPanel.add(lblProdname, "4, 10");
		}
		{
			prodNtextField_2 = new JTextField();
			contentPanel.add(prodNtextField_2, "8, 10, fill, default");
			prodNtextField_2.setColumns(10);
		}
		{
			JLabel lblOrderquant = new JLabel("OrderProdQ");
			contentPanel.add(lblOrderquant, "4, 14");
		}
		{
			orderQtextField_3 = new JTextField();
			contentPanel.add(orderQtextField_3, "8, 14, fill, default");
			orderQtextField_3.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveOrder();
						} catch (MyException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
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
	protected void saveOrder() throws MyException {

		// get the order info from gui
		int id = Integer.parseInt(ordertextField.getText());
		int custid= Integer.parseInt(custNtextField_1.getText());
		int orderid = Integer.parseInt(prodNtextField_2.getText());
		int prodQuant = Integer.parseInt(orderQtextField_3.getText());
		
		ProductDAO dao = null;
		try {
			dao = new ProductDAO();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		List<Product> products = null;
		
		try {
			products = dao.searchProductID(orderid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Product temp : products) 
			{
			
				if(prodQuant>temp.getProdQuantity())
				{
					throw new MyException();
				}
				//System.out.println("prodQuant "+temp.getProdQuantity()+" works ");
			}
		
		
		Order tempOrder = null;

		if (updateMode) {
			tempOrder = previousOrder;
			
			tempOrder.setOrderId(id);
			tempOrder.setOrderCustomerId(custid);
			tempOrder.setOrderProdId(orderid);
			tempOrder.setOrderProdQuantity(prodQuant);
			
		} else {
			
			tempOrder = new Order(id, custid, orderid, prodQuant);
		
		}

		try {
			// save to the database
			if (updateMode) {
				orderDAO.updateOrder(tempOrder);
			} else {
				
				orderDAO.addOrder(tempOrder);
	
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			orderSearchApp.refreshCustomersView();

			// show success message
			JOptionPane.showMessageDialog(orderSearchApp,
					"Order saved succesfully.", "Order Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(orderSearchApp,
					"Error saving order: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	class MyException extends Exception {
		   public MyException()
		   { 
		      JOptionPane.showMessageDialog(orderSearchApp, "OVERSTOCK", "Error", JOptionPane.ERROR_MESSAGE);
		   }
		}

}
