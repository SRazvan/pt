package jdbcD.core;

public class Order {

	private int order_id;
	private String customer_name;
	private String prod_name;
	private int order_prod_quantity;
	private int prod_quantity;
	private int order_customer_id;
	private int order_product_id;
	
	private int customer_id;
	private int prod_id;
	
	public Order(int order_id,String customer_name, String prod_name, 
			int order_prod_quantity,int prod_quantity) 
	{
		super();
		this.order_id = order_id;
		this.customer_name = customer_name;
		this.prod_name = prod_name;
		this.order_prod_quantity = order_prod_quantity;
		this.prod_quantity=prod_quantity;

	}
	
	public Order(int order_id,int order_customer_id, int order_product_id, 
			int order_prod_quantity) 
	{
		super();
		this.order_id = order_id;
		this.order_customer_id= order_customer_id;
		this.order_product_id= order_product_id;
		this.order_prod_quantity = order_prod_quantity;
	}
	
	// order customer id
	public int getOrderCustomerId() {
		return order_customer_id;
	}

	public void setOrderCustomerId(int order_customer_id) {
		this.order_customer_id = order_customer_id;
	}
	
	// order_product_id
	
	public int getOrderProdId() {
		return order_product_id;
	}

	public void setOrderProdId(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	
	// order id
	public int getOrderId() {
		return order_id;
	}

	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}
	// customer name
	public String getCustomerName() {
		return customer_name;
	}

	public void setCustomerName(String customer_name) {
		this.customer_name = customer_name;
	}
	// prod name
	public String getProdName() {
		return prod_name;
	}
	public void setProdName(String prod_name) {
		this.prod_name = prod_name;
	}
	//order prod quantity
	
	public int getOrderProdQuantity() {
		return order_prod_quantity;
	}

	public void setOrderProdQuantity(int order_prod_quantity) {
		this.order_prod_quantity = order_prod_quantity;
	}
	// prod quantity
	public int getProdQuantity() {
		return prod_quantity;
	}

	public void setProdQuantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	// order customer id
	public int getOrderCustomerID() {
		return order_customer_id;
	}

	public void setOrderCustomerID(int order_customer_id) {
		this.order_customer_id = order_customer_id;
	}
	// order_product_id
	public int getOrderProductID() {
		return order_product_id;
	}

	public void setOrderProductID(int order_product_id) {
		this.order_product_id = order_product_id;
	}
	// customer id
	public int getCustomerID() {
		return customer_id;
	}

	public void setCustomerID(int customer_id) {
		this.customer_id= customer_id;
	}
	
	// prod_id
	
	public int getProductID() {
		return prod_id;
	}

	public void setProductID(int prod_id) {
		this.prod_id= prod_id;
	}
	
	@Override
	public String toString() {
		return String
				.format("Order [order_id=%s, customer_name=%s, prod_name=%s, order_prod_quantity=%s, prod_quantity=%s]\n",
						order_id, customer_name, prod_name , order_prod_quantity, prod_quantity);
	}
	
	
		
}