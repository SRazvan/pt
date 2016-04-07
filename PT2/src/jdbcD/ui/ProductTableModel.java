package jdbcD.ui;



import java.util.List;

import javax.swing.table.AbstractTableModel;

import jdbcD.core.Product;



class ProductTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int QUANTITY_COL = 2;

	private String[] columnNames = { "ProdID", "ProdName", "ProdQuantity" };
	private List<Product> product;

	public ProductTableModel(List<Product> theProduct) {
		product = theProduct;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return product.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Product tempProduct = product.get(row);

		switch (col) {
		case ID_COL:
			return tempProduct.getId();
		case NAME_COL:
			return tempProduct.getProdName();
		case QUANTITY_COL:
			return tempProduct.getProdQuantity();
		case OBJECT_COL:
			return tempProduct;
		default:
			return tempProduct.getProdName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
