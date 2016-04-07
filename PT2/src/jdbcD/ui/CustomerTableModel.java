package jdbcD.ui;



import java.util.List;

import javax.swing.table.AbstractTableModel;

import jdbcD.core.Customer;



class CustomerTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int EMAIL_COL = 2;

	private String[] columnNames = { "CustomerID", "CustomerName", "CustomerEmail" };
	private List<Customer> customer;

	public CustomerTableModel(List<Customer> theCustomers) {
		customer = theCustomers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customer.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Customer tempCustomer = customer.get(row);

		switch (col) {
		case ID_COL:
			return tempCustomer.getId();
		case NAME_COL:
			return tempCustomer.getName();
		case EMAIL_COL:
			return tempCustomer.getEmail();
		case OBJECT_COL:
			return tempCustomer;
		default:
			return tempCustomer.getName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
