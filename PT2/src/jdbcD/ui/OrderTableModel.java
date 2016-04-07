package jdbcD.ui;



import java.util.List;

import javax.swing.table.AbstractTableModel;

import jdbcD.core.Order;



class OrderTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int CNAME_COL = 1;
	private static final int PRODN_COL = 2;
	private static final int OPRODQ_COL = 3;
	private static final int PRODQ_COL = 4;

	private String[] columnNames = { "OrderId", "CustomerName", "ProdName", "OrderProdQuantity", "ProdQuantity"};
	
	private List<Order> order;

	public OrderTableModel(List<Order> theOrder) {
		order = theOrder;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return order.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Order tempOrder = order.get(row);

		switch (col) {
		case ID_COL:
			return tempOrder.getOrderId();
		case CNAME_COL:
			return tempOrder.getCustomerName();
		case PRODN_COL :
			return tempOrder.getProdName();
		case OPRODQ_COL :
			return tempOrder.getOrderProdQuantity();
		case PRODQ_COL :
			return tempOrder.getProdQuantity();	
		case OBJECT_COL:
			return tempOrder;
		default:
			return tempOrder.getOrderId(); // getProdName
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
