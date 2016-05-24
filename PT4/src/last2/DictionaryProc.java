package last2;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;

public class DictionaryProc {

	private JFrame frmAgriculturalThesaurusAnd;
	private JTextField searchWord;
	static Dictionary namesDictionary = Dictionary.getInstance();
	private JScrollPane scrollPane;
	private static JTable table;
	private JTextField wordText;
	private JTextField meaningText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryProc window = new DictionaryProc();
					window.frmAgriculturalThesaurusAnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryProc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgriculturalThesaurusAnd = new JFrame();
		frmAgriculturalThesaurusAnd.setTitle("Agricultural Thesaurus 2016");
		frmAgriculturalThesaurusAnd.setBounds(100, 100, 888, 528);
		frmAgriculturalThesaurusAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSearch = new JLabel("Search: ");
		
		searchWord = new JTextField();
		searchWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					 addRowToJTable(searchWord.getText());
			}
		});
		searchWord.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Add Word");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				namesDictionary.addWord(wordText.getText(), meaningText.getText());
			}
		});
		
		JLabel lblWord = new JLabel("Word: ");
		
		JLabel lblMeaning = new JLabel("Meaning: ");
		
		wordText = new JTextField();
		wordText.setColumns(10);
		
		meaningText = new JTextField();
		meaningText.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Remove Word");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Word w=namesDictionary.searchReturnWord(wordText.getText());
			      
			     namesDictionary.removeWord(w);

			}
		});
		
		JButton btnNewButton_2 = new JButton("Export JSON");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					namesDictionary.saveJSON();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmAgriculturalThesaurusAnd.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 817, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSearch)
									.addGap(18)
									.addComponent(searchWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(57)
									.addComponent(btnNewButton))
								.addComponent(btnNewButton_2))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMeaning)
								.addComponent(lblWord))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(wordText, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(btnNewButton_1))
								.addComponent(meaningText, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearch)
						.addComponent(searchWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(lblWord)
						.addComponent(btnNewButton_1)
						.addComponent(wordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMeaning)
							.addComponent(meaningText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_2))
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Word", "Meaning"
			}
		));
		scrollPane.setViewportView(table);
		frmAgriculturalThesaurusAnd.getContentPane().setLayout(groupLayout);
	}
	
	public static void addRowToJTable(String word)			// adding accounts to the JTable
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		
		for(int i=rowCount-1; i>=0; i--)			// clear the table first
		{
			model.removeRow(i);
		}
		
		 ArrayList <Word> listW = new  ArrayList<Word>();	// create an arraylists
		 ArrayList <String> listS = new  ArrayList<String>();
		 
		 for(Iterator iter = namesDictionary.getIterator(); iter.hasNext();)
	      {
	    	  Map.Entry pair = (Map.Entry)iter.next();

	    	  if(pair.getKey().toString().regionMatches(0, word, 0, word.length()))
				{
					listW.add( (Word) pair.getKey());
					listS.add((String) pair.getValue());
				}
	      } 
		 
		Object rowData[]=new Object[2];
		for(int i=0; i<listW.size(); i++)		// put in row data accountId and money of every account
		{				
			rowData[0]=listW.get(i);
			rowData[1]=listS.get(i);	// then add it to the model view
			model.addRow(rowData);
		}
	}
}

