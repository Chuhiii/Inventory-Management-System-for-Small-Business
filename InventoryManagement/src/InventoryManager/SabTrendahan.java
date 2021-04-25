package InventoryManager;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SabTrendahan {

	private JFrame frame;
	private JTextField txtbName;
	private JTextField txtbCost;
	private JTextField txtbType;
	private JTextField txtbSize;
	private JTextField txtbQuantity;
	private JTextField txtbLimitQuantity;
	private JTable table;
	private JTextField txtbSC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SabTrendahan window = new SabTrendahan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SabTrendahan() {
		initialize();
		Connect();
		table_load();
	}
	
	//Initialize Connection
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/sabtrendahan", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
		
	}
	//Load the table in the screen by linking the MySQL
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("select * from products_inventory");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1479, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                                                      SAB Trendahan Inventory");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 127, 80));
		lblNewLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblNewLabel.setBounds(172, 11, 1151, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 127, 80));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 116, 475, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Name");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 22, 122, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Cost");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 45, 122, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Type");
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 69, 122, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Product Size");
		lblNewLabel_1_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_1.setBounds(10, 94, 122, 24);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Product Quantity");
		lblNewLabel_1_3_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_2.setBounds(10, 118, 168, 24);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Product Limit Quantity");
		lblNewLabel_1_3_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_3.setBounds(10, 142, 168, 24);
		panel.add(lblNewLabel_1_3_3);
		
		txtbName = new JTextField();
		txtbName.setColumns(10);
		txtbName.setBounds(199, 27, 266, 20);
		panel.add(txtbName);
		
		txtbCost = new JTextField();
		txtbCost.setColumns(10);
		txtbCost.setBounds(199, 50, 266, 20);
		panel.add(txtbCost);
		
		txtbType = new JTextField();
		txtbType.setColumns(10);
		txtbType.setBounds(199, 74, 266, 20);
		panel.add(txtbType);
		
		txtbSize = new JTextField();
		txtbSize.setColumns(10);
		txtbSize.setBounds(199, 99, 266, 20);
		panel.add(txtbSize);
		
		txtbQuantity = new JTextField();
		txtbQuantity.setColumns(10);
		txtbQuantity.setBounds(199, 123, 266, 20);
		panel.add(txtbQuantity);
		
		txtbLimitQuantity = new JTextField();
		txtbLimitQuantity.setColumns(10);
		txtbLimitQuantity.setBounds(199, 147, 266, 20);
		panel.add(txtbLimitQuantity);
		
		JButton btnNewButton = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pi_bName, pi_bCost, pi_bType, pi_bSize, pi_bQuantity, pi_bLQuantity;
				
				pi_bName = txtbName.getText();
				pi_bCost = txtbCost.getText();
				pi_bType = txtbType.getText();
				pi_bSize = txtbSize.getText();
				pi_bQuantity = txtbQuantity.getText();
				pi_bLQuantity = txtbLimitQuantity.getText();
				
				try {
					pst = con.prepareStatement("insert into products_inventory(product_Name, product_Cost, product_Type, product_Sizes, product_Quantity, product_LimitQuantity)values(?,?,?,?,?,?)");
					pst.setString(1, pi_bName);
					pst.setString(2, pi_bCost);
					pst.setString(3, pi_bType);
					pst.setString(4, pi_bSize);
					pst.setString(5, pi_bQuantity);
					pst.setString(6, pi_bLQuantity);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					txtbName.setText("");
					txtbCost.setText("");
					txtbType.setText("");
					txtbSize.setText("");
					txtbQuantity.setText("");
					txtbLimitQuantity.setText("");
					txtbName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnNewButton.setBounds(30, 306, 105, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnExit.setBounds(1336, 405, 105, 42);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbName.setText("");
				txtbCost.setText("");
				txtbType.setText("");
				txtbSize.setText("");
				txtbQuantity.setText("");
				txtbLimitQuantity.setText("");
				txtbName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnClear.setBounds(260, 306, 105, 42);
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 63, 936, 285);
		frame.getContentPane().add(products_Inventory_Screen);
		
		table = new JTable();
		products_Inventory_Screen.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 160, 122));
		panel_1.setBounds(20, 63, 475, 42);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Search Code");
		lblNewLabel_1_3_3_1.setBackground(new Color(255, 160, 122));
		lblNewLabel_1_3_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 24));
		lblNewLabel_1_3_3_1.setBounds(10, 10, 172, 24);
		panel_1.add(lblNewLabel_1_3_3_1);
		
		txtbSC = new JTextField();
		txtbSC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String product_Code = txtbSC.getText();
					//
					pst = con.prepareStatement("select product_Name, product_Cost, product_Type, product_Sizes, product_Quantity, product_LimitQuantity from products_inventory where product_Code = ?");
					pst.setString(1, product_Code);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String product_Name = rs.getString(1);
						String product_Cost = rs.getString(2);
						String product_Type = rs.getString(3);
						String product_Sizes = rs.getString(4);
						String product_Quantity = rs.getString(6);
						String product_LimitQuantity = rs.getString(6);
						txtbName.setText(product_Name);
						txtbCost.setText(product_Cost);
						txtbType.setText(product_Type);
						txtbSize.setText(product_Sizes);
						txtbQuantity.setText(product_Quantity);
						txtbLimitQuantity.setText(product_LimitQuantity);
					}
					else
					{
						txtbName.setText("");
						txtbCost.setText("");
						txtbType.setText("");
						txtbSize.setText("");
						txtbQuantity.setText("");
						txtbLimitQuantity.setText("");
					}
				}
				catch (SQLException ex) {
					
				}
			}
		});
		txtbSC.setColumns(10);
		txtbSC.setBounds(192, 10, 273, 20);
		panel_1.add(txtbSC);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pi_bName, pi_bCost, pi_bType, pi_bSize, pi_bQuantity, pi_bLQuantity;
				
				pi_bName = txtbName.getText();
				pi_bCost = txtbCost.getText();
				pi_bType = txtbType.getText();
				pi_bSize = txtbSize.getText();
				pi_bQuantity = txtbQuantity.getText();
				pi_bLQuantity = txtbLimitQuantity.getText();
				
				try {
					pst = con.prepareStatement("update products_inventory set product_Name = ?, product_Cost = ?, product_Type = ?, product_Sizes = ?, product_Quantity = ?, product_LimitQuantity = ?");
					pst.setString(1, pi_bName);
					pst.setString(2, pi_bCost);
					pst.setString(3, pi_bType);
					pst.setString(4, pi_bSize);
					pst.setString(5, pi_bQuantity);
					pst.setString(6, pi_bLQuantity);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Update!");
					table_load();
					txtbName.setText("");
					txtbCost.setText("");
					txtbType.setText("");
					txtbSize.setText("");
					txtbQuantity.setText("");
					txtbLimitQuantity.setText("");
					txtbName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnUpdate.setBounds(145, 306, 105, 42);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(new Color(250, 128, 114));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String product_Code;
				
				product_Code =  txtbSC.getText();
				
				try {
					pst = con.prepareStatement("delete from products_inventory where product_Code = ?");
					pst.setString(1, product_Code);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Succesfully Deleted!");
					table_load();
					txtbName.setText("");
					txtbCost.setText("");
					txtbType.setText("");
					txtbSize.setText("");
					txtbQuantity.setText("");
					txtbLimitQuantity.setText("");
					txtbName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnNewButton_1.setBounds(375, 306, 105, 42);
		frame.getContentPane().add(btnNewButton_1);
	}
}
