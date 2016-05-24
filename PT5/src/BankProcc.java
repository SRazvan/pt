import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;


public class BankProcc {

	private JFrame frmBankApplication;
	private static JTable table;
	private JLabel lblPerson;
	private JTextField textField;
	
	static Bank bank=new Bank();
	private JLabel lblSum;
	private JTextField textSum;
	private JLabel lblAccountid;
	private JTextField textAccoundID;
	private JButton btnRemoveAccount;
	private JButton btnRemovePerson;
	private JLabel lblAccid;
	private JLabel lblPerson_1;
	private JLabel lblMoney;
	private JTextField accID;
	private JTextField personC;
	private JTextField moneyT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		Person p1=new Person("Tim");		// these accounts are put instead of serialization
		Person p2=new Person("Bill");
		Person p3=new Person("Billess");
		
		Account a=new Account(1,514);
		Account b=new Account(2,7742);
		Account c=new Account(3,4431);
		Account d=new Account(4,442);
		Account e=new Account(5,64341);
		Account f=new Account(6,9432);
		
		bank.addAccount(p1, a);
		bank.addAccount(p1, b);
		bank.addAccount(p1, f);
		
		bank.addAccount(p2, c);
		bank.addAccount(p2, d);
		
		bank.addAccount(p3, e);
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankProcc window = new BankProcc();
					window.frmBankApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankProcc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankApplication = new JFrame();
		frmBankApplication.setTitle("Bank Application");
		frmBankApplication.setBounds(100, 100, 901, 483);
		frmBankApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblPerson = new JLabel("Person: ");
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(bank.searchPerson("Name: "+ textField.getText())!=null)
				{
					addRowToJTable(textField.getText());
				}
				//System.out.println(textField.getText());
			}
		});
		textField.setColumns(10);
		
		lblSum = new JLabel("Sum: ");
		
		textSum = new JTextField();
		textSum.setColumns(10);
		
		lblAccountid = new JLabel("AccountID: ");
		
		textAccoundID = new JTextField();
		textAccoundID.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			 	Person p=bank.searchReturnPerson("Name: "+textField.getText());
			 	
			 	
				bank.depositMoney(Double.parseDouble(textSum.getText()), Integer.parseInt(textAccoundID.getText()), p);
				
			}
		});
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				Person p=bank.searchReturnPerson("Name: "+textField.getText());
			 	
			 	
				bank.withdrawMoney(Double.parseDouble(textSum.getText()), Integer.parseInt(textAccoundID.getText()), p);
				
				
			}
		});
		
		btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Person p=bank.searchReturnPerson("Name: "+textField.getText());
				
				bank.removeAccount(p, Integer.parseInt(textAccoundID.getText()));
			}
		});
		
		btnRemovePerson = new JButton("Remove Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person p=bank.searchReturnPerson("Name: "+textField.getText());
				
				bank.removePerson(p);
				
			}
		});
		
		JLabel lblCreateAssign = new JLabel("Create & Assign Account");
		
		lblAccid = new JLabel("AccID:");
		
		lblPerson_1 = new JLabel("Person:");
		
		lblMoney = new JLabel("Money:");
		
		accID = new JTextField();
		accID.setColumns(10);
		
		personC = new JTextField();
		personC.setColumns(10);
		
		moneyT = new JTextField();
		moneyT.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Account a=new Account(Integer.parseInt(accID.getText()),Double.parseDouble(moneyT.getText()));
				
				System.out.println("Created account="+a);
				
				Person joy=bank.searchReturnPerson("Name: "+personC.getText());
				
				if(joy!=null)
				{
					bank.addAccount(joy, a);
				}
				else
				{
					Person p=new Person(personC.getText());
					bank.addAccount(p, a);
					//System.out.println("The person which you want to add the account doesn't exit! Create it!");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmBankApplication.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(90)
							.addComponent(lblCreateAssign))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMoney)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCreateAccount)
										.addComponent(moneyT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblAccid)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(accID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblPerson_1)
												.addGap(18)
												.addComponent(personC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
									.addGap(39)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSum)
										.addComponent(lblPerson)
										.addComponent(lblAccountid))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textAccoundID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textSum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(33)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnRemovePerson)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnWithdraw)
											.addGap(41)
											.addComponent(btnDeposit))
										.addComponent(btnRemoveAccount))))))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPerson)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemovePerson))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSum)
								.addComponent(textSum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnWithdraw)
								.addComponent(btnDeposit))
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAccountid)
								.addComponent(textAccoundID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveAccount)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
					.addGap(49)
					.addComponent(lblCreateAssign)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccid)
						.addComponent(accID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerson_1)
						.addComponent(personC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoney)
						.addComponent(moneyT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnCreateAccount)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AccountID", "Money"
			}
		));
		scrollPane.setViewportView(table);
		frmBankApplication.getContentPane().setLayout(groupLayout);
		
		
	}
	public static void addRowToJTable(String name)			// adding accounts to the JTable
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1; i>=0; i--)			// clear the table first
		{
			model.removeRow(i);
		}
		
		ArrayList <Account> list = new ArrayList<Account>();	// create an arraylist
		
		Set<Account> set = null;
		set=(bank.map.get(bank.searchPerson("Name: "+ name)));		// get the person with we need to display the accounts for
		
		
		for(Account teem: set)		// add every account of that person to the list
		{
			list.add(teem);
		}
		
		Object rowData[]=new Object[2];
		for(int i=0; i<list.size(); i++)		// put in row data accountId and money of every account
		{				
			rowData[0]=list.get(i).accountID;
			rowData[1]=list.get(i).accountMoney;	// then add it to the model view
			model.addRow(rowData);
		}
	}
}
