import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class UI{
	private JFrame frmPolynomialOperations;
	private JTextField textFieldNum1,textFieldNum2,textFieldAns;
	private JLabel lblNewLabel,lblPx,lblQx;
	private JButton btnNewButton2,btnNewButton3,btnNewButton4,btnNewButton5,btnNewButton6,btnNewButton7;

	public static Poly readPolyx(Poly p,Poly p1,String poly)
	{
		int toInt=0,toInt2=0;
		String[] str2,parts;
		String polyN = poly.replaceAll("-", "+-");  // replaces - with +- (-3x^2+5x^1+2 == +-3x^2+5x^1+2)
		System.out.println("poly= "+polyN);
		parts = polyN.split("\\+"); // parts={-3x^2,5x^1,2}
		for(int j=0; j<parts.length; j++)
		{
			
			str2 = parts[j].split("x\\^"); // we split it with x and ^ so we get the numbers
			if(j+1!=parts.length){
				toInt2=0;
				toInt = Integer.parseInt(str2[0]);
				if(str2[1]!=null) toInt2 = Integer.parseInt(str2[1]);
				System.out.println("to ints= "+toInt+" "+toInt2);
				p1 = new Poly(toInt,toInt2);  // p1 gets numbers from the split part of the polynomial
			}else{							  // this case is only for the lowest polynomial degree (x^0)
				toInt = 0;
				if(str2[1]!=null) toInt = Integer.parseInt(str2[1]);
				toInt2 = 0;
				System.out.println("to ints= "+toInt+" "+toInt2);
				p1 = new Poly(toInt,toInt2);
				}p=p.plus(p1); // we add p1 to p
		}
		return p; // return the polynomial (sum of p1's)
	}

	
	public static Poly readPoly(Poly p,Poly p1,String poly)
	{
		 
		int toInt=0,toInt2=0;
		String[] str2,parts;
		String polyN = poly.replaceAll("-", "+-"); 
		System.out.println("poly= "+polyN);
		parts = polyN.split("\\+"); 
		String f=parts[0]+1;
		String f2="1";
		if(parts[0].isEmpty()) System.out.println("everyk");
		//System.out.printf("parts 0 = %s \n",f);
		if(f==f2)
		{
			//System.out.println("null");
			for(int j=1; j<parts.length; j++)
			{
				//System.out.println("parts= "+parts[j]);
				str2 = parts[j].split("x\\^");
				//System.out.println("str2= "+str2[0]);
				if(j+1!=parts.length){
					toInt2=0;
					 toInt = Integer.parseInt(str2[0]);
					if(str2[1]!=null) toInt2 = Integer.parseInt(str2[1]);
					System.out.println("to ints= "+toInt+" "+toInt2);
					p1 = new Poly(toInt,toInt2);
				}else{							  
					toInt = 0;
					if(str2[0]!=null) toInt = Integer.parseInt(str2[0]);
					toInt2 = 0;
					System.out.println("to ints= "+toInt+" "+toInt2);
					p1 = new Poly(toInt,toInt2);
					}p=p.plus(p1);
			} return p;
			
		}
		else{
		for(int j=0; j<parts.length; j++)
		{
			//System.out.println("parts= "+parts[j]);
			str2 = parts[j].split("x\\^");
			//System.out.println("str2= "+str2[0]);
			if(j+1!=parts.length){
				toInt2=0;
				 toInt = Integer.parseInt(str2[0]);
				if(str2[1]!=null) toInt2 = Integer.parseInt(str2[1]);
				p1 = new Poly(toInt,toInt2);
				System.out.println("to ints= "+toInt+" "+toInt2);
			}else{							  
				toInt = 0;
				if(str2[0]!=null) toInt = Integer.parseInt(str2[0]);
				toInt2 = 0;
				p1 = new Poly(toInt,toInt2);
				System.out.println("to ints= "+toInt+" "+toInt2);
				}p=p.plus(p1);
		}
		return p;
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		//readPolyx("1x^1-3");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmPolynomialOperations.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPolynomialOperations = new JFrame();
		frmPolynomialOperations.setTitle("Polynomial Operations");
		frmPolynomialOperations.setBounds(100, 100, 715, 300);
		frmPolynomialOperations.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPolynomialOperations.getContentPane().setLayout(null);

		textFieldNum1 = new JTextField();
		textFieldNum1.setBounds(56, 35, 230, 32);
		frmPolynomialOperations.getContentPane().add(textFieldNum1);
		textFieldNum1.setColumns(10);

		textFieldNum2 = new JTextField();
		textFieldNum2.setBounds(360, 35, 230, 32);
		frmPolynomialOperations.getContentPane().add(textFieldNum2);
		textFieldNum2.setColumns(10);

		JButton btnNewButton = new JButton("Addition");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num1,num2;
					num1=textFieldNum1.getText();
					num2=textFieldNum2.getText();
					System.out.println("num1= "+num1);
					System.out.println("num2= "+num2);
					p=readPoly(p,p1,num1);
					//System.out.println("p ="+p);
					q=readPoly(q,q1,num2);
					//System.out.println("q ="+q);
					Poly a = p.plus(q);
					String x=a.toString();
					textFieldAns.setText(x);
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton.setBounds(24, 110, 112, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Substraction");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num1,num2;
					num1=textFieldNum1.getText();
					num2=textFieldNum2.getText();
					p=readPoly(p,p1,num1);
					q=readPoly(q,q1,num2);
					Poly b = p.minus(q);
					textFieldAns.setText(b.toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton_1.setBounds(24, 144, 112, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton_1);

		textFieldAns = new JTextField();
		textFieldAns.setBounds(338, 136, 311, 38);
		frmPolynomialOperations.getContentPane().add(textFieldAns);
		textFieldAns.setColumns(10);

		lblNewLabel = new JLabel("Result");
		lblNewLabel.setBounds(476, 110, 46, 23);
		frmPolynomialOperations.getContentPane().add(lblNewLabel);

		btnNewButton2 = new JButton("Division");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num1,num2;
					num1=textFieldNum1.getText();
					num2=textFieldNum2.getText();
					p=readPoly(p,p1,num1);
					q=readPoly(q,q1,num2);
					textFieldAns.setText("("+p.toString()+")"+"/("+q.toString()+")");}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton2.setBounds(24, 178, 112, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton2);

		btnNewButton3 = new JButton("Multiplication");
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num1,num2;
					num1=textFieldNum1.getText();
					num2=textFieldNum2.getText();
					p=readPoly(p,p1,num1);
					q=readPoly(q,q1,num2);
					Poly c = p.multiplies(q);
					textFieldAns.setText(c.toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton3.setBounds(24, 212, 112, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton3);

		lblPx = new JLabel("P(X)=");
		lblPx.setBounds(24, 44, 46, 14);
		frmPolynomialOperations.getContentPane().add(lblPx);

		lblQx = new JLabel("Q(X)=");
		lblQx.setBounds(323, 44, 46, 14);
		frmPolynomialOperations.getContentPane().add(lblQx);

		btnNewButton4 = new JButton("Derivative of P");
		btnNewButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					String num1;
					num1=textFieldNum1.getText();
					p=readPoly(p,p1,num1);
					System.out.println(p);
					textFieldAns.setText(p.derivative().toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton4.setBounds(146, 110, 154, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton4);

		btnNewButton5 = new JButton("Derivative of Q");
		btnNewButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num2;
					num2=textFieldNum2.getText();
					q=readPoly(q,q1,num2);
					textFieldAns.setText(q.derivative().toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton5.setBounds(146, 144, 154, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton5);

		btnNewButton6 = new JButton("Integration of P");
		btnNewButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly p1 = new Poly(0,0);
					Poly p = new Poly(0,0);
					String num1;
					num1=textFieldNum1.getText();
					p=readPoly(p,p1,num1);
					textFieldAns.setText(p.integrate().toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton6.setBounds(146, 178, 154, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton6);

		btnNewButton7 = new JButton("Integration of Q");
		btnNewButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Poly q1 = new Poly(0,0);
					Poly q = new Poly(0,0);
					String num2;
					num2=textFieldNum2.getText();
					q=readPoly(q,q1,num2);
					textFieldAns.setText(q.integrate().toString());}catch(Exception e1)
				{
						JOptionPane.showMessageDialog(null, "Try to enter a valid number!\n For example: 3x^2+2x^1+1");
				}
			}
		});
		btnNewButton7.setBounds(146, 212, 154, 23);
		frmPolynomialOperations.getContentPane().add(btnNewButton7);
	}
}
