import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;


public class Display extends JFrame{

	JPanel panel,panel_1,panel_2,panel_3;
	private JFrame frmThreadsApplication;
	private JLabel lblServer;
	private JLabel lblServer_1;
	private JLabel lblServer_2;
	private JLabel lblServer_3;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */


	/**
	 * Create the application.
	 */
	public Display() {
		frmThreadsApplication = new JFrame();
		frmThreadsApplication.setTitle("Threads Application");
		//frame.setVisible(true);
		frmThreadsApplication.setBounds(100, 100, 450, 300);
		//setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 panel = new JPanel();
		
		 panel_1 = new JPanel();
		
		 panel_2 = new JPanel();
		
		 panel_3 = new JPanel();
		
		lblServer = new JLabel("Server 1");
		
		lblServer_1 = new JLabel("Server 2");
		
		lblServer_2 = new JLabel("Server 3");
		
		lblServer_3 = new JLabel("Server 4");
		GroupLayout groupLayout = new GroupLayout(frmThreadsApplication.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServer))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServer_1))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServer_2))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblServer_3)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblServer)
						.addComponent(lblServer_1)
						.addComponent(lblServer_2)
						.addComponent(lblServer_3))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
					.addGap(114))
		);
		frmThreadsApplication.getContentPane().setLayout(groupLayout);
		frmThreadsApplication.setVisible(true);
	
	}
	
	void displayData(Task[] tasks)
	{
		panel.removeAll();
		panel.revalidate();
		JList<Task> list=new JList<Task>(tasks);
		JScrollPane sp=new JScrollPane(list);
		panel.add(sp);
		panel.repaint();
		panel.revalidate();
		panel.setVisible(true);
	}
	
	void displayData2(Task[] tasksx)
	{
		panel_1.removeAll();
		panel_1.revalidate();
		JList<Task> list2=new JList<Task>(tasksx);
		JScrollPane sp2=new JScrollPane(list2);
		panel_1.add(sp2);
		panel_1.repaint();
		panel_1.revalidate();	
		panel_1.setVisible(true);
	}
	
	void displayData3(Task[] tasksx)
	{
		panel_2.removeAll();
		panel_2.revalidate();
		JList<Task> list3=new JList<Task>(tasksx);
		JScrollPane sp3=new JScrollPane(list3);
		panel_2.add(sp3);
		panel_2.repaint();
		panel_2.revalidate();	
		panel_2.setVisible(true);
	}
	void displayData4(Task[] tasksx)
	{
		panel_3.removeAll();
		panel_3.revalidate();
		JList<Task> list4=new JList<Task>(tasksx);
		JScrollPane sp4=new JScrollPane(list4);
		panel_3.add(sp4);
		panel_3.repaint();
		panel_3.revalidate();	
		panel_3.setVisible(true);
	}
	
}
