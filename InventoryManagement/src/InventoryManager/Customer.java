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
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;

public class Customer {

	private JFrame frame;
	private JTextField txtbLastName;
	private JTextField txtbFIrstName;
	private JTextField txtbState;
	private JTextField txtbPostalCode;
	private JTable table;
	private JTextField txtbSC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
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
	public Customer() {
		initialize();
		Connect();
		table_load();
	}
	
	//Initialize Connection
	Connection con ;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtbContactNum;
	private JTextField txtbCity; 
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sabtrendahan?useTimezone=true&serverTimezone=UTC", "root","");
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
			pst = con.prepareStatement("SELECT * FROM customer");
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
		frame.getContentPane().setBackground(new Color(255, 235, 205));
		frame.setBounds(100, 100, 1479, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 204, 475, 233);
		panel.setBackground(new Color(255, 160, 122));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 22, 168, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("First Name");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 57, 168, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Contact Number");
		lblNewLabel_1_3.setBackground(new Color(255, 165, 0));
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 92, 168, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("City");
		lblNewLabel_1_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_1.setBounds(10, 125, 122, 24);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("State");
		lblNewLabel_1_3_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_2.setBounds(10, 152, 168, 34);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Postal ID");
		lblNewLabel_1_3_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_3.setBounds(10, 184, 168, 37);
		panel.add(lblNewLabel_1_3_3);
		
		txtbLastName = new JTextField();
		txtbLastName.setColumns(10);
		txtbLastName.setBounds(199, 27, 266, 20);
		panel.add(txtbLastName);
		
		txtbFIrstName = new JTextField();
		txtbFIrstName.setColumns(10);
		txtbFIrstName.setBounds(199, 61, 266, 20);
		panel.add(txtbFIrstName);
		
		txtbState = new JTextField();
		txtbState.setColumns(10);
		txtbState.setBounds(199, 160, 266, 24);
		panel.add(txtbState);
		
		txtbPostalCode = new JTextField();
		txtbPostalCode.setColumns(10);
		txtbPostalCode.setBounds(199, 193, 266, 24);
		panel.add(txtbPostalCode);
		
		txtbContactNum = new JTextField();
		txtbContactNum.setColumns(10);
		txtbContactNum.setBounds(199, 97, 266, 20);
		panel.add(txtbContactNum);
		
		txtbCity = new JTextField();
		txtbCity.setColumns(10);
		txtbCity.setBounds(199, 130, 266, 20);
		panel.add(txtbCity);
		
		JButton btnSaveCustomer = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnSaveCustomer.setBounds(31, 450, 105, 42);
		btnSaveCustomer.setBackground(Color.LIGHT_GRAY);
		btnSaveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String c_bLastName, c_bFirstName, c_bContactNum, c_bState, c_bPostalCode, c_bCity;
				
				c_bLastName = txtbLastName.getText();
				c_bFirstName = txtbFIrstName.getText();
				c_bContactNum = txtbContactNum.getText();
				c_bCity = txtbCity.getText();
				c_bState = txtbState.getText();
				c_bPostalCode = txtbPostalCode.getText();
				
				try {
					pst = con.prepareStatement("insert into customer(LAST_NAME, FIRST_NAME, PHONE_NUMBER, CITY, STATE, POSTAL_CODE)values(?,?,?,?,?,?)");
					pst.setString(1, c_bLastName);
					pst.setString(2, c_bFirstName);
					pst.setString(3, c_bContactNum);
					pst.setString(4, c_bCity);
					pst.setString(5, c_bState);
					pst.setString(6, c_bPostalCode);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					txtbLastName.setText("");
					txtbFIrstName.setText("");
					txtbContactNum.setText("");
					txtbCity.setText("");
					txtbState.setText("");
					txtbPostalCode.setText("");
					txtbLastName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnSaveCustomer);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBounds(375, 503, 105, 42);
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(260, 450, 105, 42);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbLastName.setText("");
				txtbFIrstName.setText("");
				txtbContactNum.setText("");
				txtbCity.setText("");
				txtbState.setText("");
				txtbPostalCode.setText("");
				txtbLastName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 128, 936, 412);
		frame.getContentPane().add(products_Inventory_Screen);
		
		table = new JTable();
		products_Inventory_Screen.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 156, 475, 42);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 160, 122));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Search Code");
		lblNewLabel_1_3_3_1.setBackground(new Color(255, 160, 122));
		lblNewLabel_1_3_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 24));
		lblNewLabel_1_3_3_1.setBounds(10, 11, 172, 24);
		panel_1.add(lblNewLabel_1_3_3_1);
		
		txtbSC = new JTextField();
		txtbSC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String CUSTOMER_ID = txtbSC.getText();
					//
					pst = con.prepareStatement("select LAST_NAME, FIRST_NAME, PHONE_NUMBER, CITY, STATE, POSTAL_CODE from customer where CUSTOMER_ID = ?");
					pst.setString(1, CUSTOMER_ID);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String last_Name = rs.getString(1);
						String first_Name = rs.getString(2);
						String phone = rs.getString(3);
						String city = rs.getString(4);
						String state = rs.getString(5);
						String postal_Code = rs.getString(6);
						txtbLastName.setText(last_Name);
						txtbFIrstName.setText(first_Name);
						txtbContactNum.setText(phone);
						txtbCity.setText(city);
						txtbState.setText(state);
						txtbPostalCode.setText(postal_Code);
					}
					else
					{
						txtbLastName.setText("");
						txtbFIrstName.setText("");
						txtbContactNum.setText("");
						txtbCity.setText("");
						txtbState.setText("");
						txtbPostalCode.setText("");
					}
				}
				catch (SQLException ex) {
					
				}
			}
		});
		txtbSC.setColumns(10);
		txtbSC.setBounds(192, 10, 273, 20);	
		panel_1.add(txtbSC);
		
		JButton btnUpdateCustomer = new JButton("Update");
		btnUpdateCustomer.setBackground(new Color(30, 144, 255));
		btnUpdateCustomer.setBounds(145, 450, 105, 42);
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_bCode, c_bLastName, c_bFirstName, c_bContactNum, c_bState, c_bPostalCode, c_bCity;
				
				c_bCode = txtbSC.getText();
				c_bLastName = txtbLastName.getText();
				c_bFirstName = txtbFIrstName.getText();
				c_bContactNum = txtbContactNum.getText();
				c_bCity = txtbCity.getText();
				c_bState = txtbState.getText();
				c_bPostalCode = txtbPostalCode.getText();
				
				try {
					pst = con.prepareStatement("update customer set LAST_NAME = ?, FIRST_NAME = ?, PHONE_NUMBER = ?, CITY = ?, STATE = ?, POSTAL_CODE = ?  where CUSTOMER_ID = ?");
					pst.setString(1, c_bLastName);
					pst.setString(2, c_bFirstName);
					pst.setString(3, c_bContactNum);
					pst.setString(4, c_bCity);
					pst.setString(5, c_bState);
					pst.setString(6, c_bPostalCode);
					pst.setString(7,  c_bCode);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!");
					table_load();
					txtbSC.setText("");
					txtbLastName.setText("");
					txtbFIrstName.setText("");
					txtbContactNum.setText("");
					txtbCity.setText("");
					txtbState.setText("");
					txtbPostalCode.setText("");
					txtbLastName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdateCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnUpdateCustomer);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(375, 450, 105, 42);
		btnDelete.setBackground(new Color(250, 128, 114));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CUSTOMER_ID;
				
				CUSTOMER_ID =  txtbSC.getText();
				
				try {
					pst = con.prepareStatement("delete from customer where CUSTOMER_ID = ?");
					pst.setString(1, CUSTOMER_ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Succesfully Deleted!");
					table_load();
					txtbLastName.setText("");
					txtbFIrstName.setText("");
					txtbContactNum.setText("");
					txtbCity.setText("");
					txtbState.setText("");
					txtbPostalCode.setText("");
					txtbLastName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_2 = new JButton(""); 
		btnNewButton_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 99, 71));
		panel_2.setBounds(0, 0, 1463, 66);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel = new JLabel("SAB Trendahan Inventory");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblNewLabel.setBackground(new Color(255, 248, 220));
		lblNewLabel.setBounds(111, 20, 305, 35);
		panel_2.add(lblNewLabel);
		
		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setForeground(Color.BLACK);
		lblCustomerInformation.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblCustomerInformation.setBackground(new Color(255, 248, 220));
		lblCustomerInformation.setBounds(840, 82, 309, 35);
		frame.getContentPane().add(lblCustomerInformation);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Add record to:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(21, 79, 474, 66);
		frame.getContentPane().add(panel_3);
		
		JButton btnCustomer = new JButton("Products");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SabTrendahan.main(null);
			}
		});
		btnCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnCustomer.setBackground(new Color(255, 222, 173));
		btnCustomer.setBounds(10, 13, 87, 42);
		panel_3.add(btnCustomer);
		
		JButton btnOrder = new JButton("Customer");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Customer.main(null);
			}
		});
		btnOrder.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnOrder.setBackground(new Color(255, 228, 181));
		btnOrder.setBounds(107, 13, 87, 42);
		panel_3.add(btnOrder);
		
		JButton btnPayment = new JButton("Order");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Customer_Order.main(null);
			}
		});
		btnPayment.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnPayment.setBackground(new Color(255, 222, 173));
		btnPayment.setBounds(204, 13, 87, 42);
		panel_3.add(btnPayment);
		
		JButton btnSupplier = new JButton("Payment");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Payment.main(null);
			}
		});
		btnSupplier.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 13));
		btnSupplier.setBackground(new Color(255, 222, 173));
		btnSupplier.setBounds(301, 13, 76, 42);
		panel_3.add(btnSupplier);
		
		JButton btnSupplier_1 = new JButton("Supplier");
		btnSupplier_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Supplier.main(null);
			}
		});
		btnSupplier_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnSupplier_1.setBackground(new Color(255, 222, 173));
		btnSupplier_1.setBounds(388, 13, 76, 42);
		panel_3.add(btnSupplier_1);
	}
}
