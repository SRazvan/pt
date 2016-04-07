package jdbcD.core;

public class Product {

	private int prod_id;
	private String prod_name;
	private int prod_quantity;
	
	public Product(int prod_id, String prod_name, int prod_quantity) 
	{
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_quantity = prod_quantity;
	}

	public int getId() {
		return prod_id;
	}

	public void setId(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProdName() {
		return prod_name;
	}

	public void setProdName(String prod_name) {
		this.prod_name = prod_name;
	}
	
	public int getProdQuantity() {
		return prod_quantity;
	}

	public void setProdQuantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}


	@Override
	public String toString() {
		return String
				.format("Product [prod_id=%s, prod_name=%s, prod_quantity=%s]",
						prod_id, prod_name, prod_quantity);
	}
	
	
		
}