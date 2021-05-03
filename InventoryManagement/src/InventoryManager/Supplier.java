package InventoryManager;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
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

public class Supplier {

	private JFrame frame;
	private JTextField txtbSupplierName;
	private JTextField txtbSupplierCost;
	private JTextField txtbProductCode;
	private JTable table;
	private JTextField txtbSC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supplier window = new Supplier();
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
	public Supplier() {
		initialize();
		Connect();
		table_load();
	}
	
	//Initialize Connection
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtbSupplierContact;
	
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
			pst = con.prepareStatement("SELECT * FROM supplier");
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
		frame.setBounds(100, 100, 1479, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 202, 475, 133);
		panel.setBackground(new Color(255, 160, 122));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Supplier Name");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 22, 168, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Supplier Contact");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 57, 168, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Code");
		lblNewLabel_1_3.setBackground(new Color(255, 165, 0));
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 92, 168, 24);
		panel.add(lblNewLabel_1_3);
		
		txtbSupplierName = new JTextField();
		txtbSupplierName.setColumns(10);
		txtbSupplierName.setBounds(199, 27, 266, 20);
		panel.add(txtbSupplierName);
		

		
		txtbProductCode = new JTextField();
		txtbProductCode.setColumns(10);
		txtbProductCode.setBounds(199, 95, 266, 24);
		panel.add(txtbProductCode);
		
		txtbSupplierContact = new JTextField();
		txtbSupplierContact.setColumns(10);
		txtbSupplierContact.setBounds(199, 57, 266, 24);
		panel.add(txtbSupplierContact);
		
		JButton btnNewButton = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnNewButton.setBounds(30, 346, 105, 42);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s_bName, s_bSupplierContact, s_bProductCode;
				
				
				s_bName = txtbSupplierName.getText();
				s_bSupplierContact = txtbSupplierContact.getText();
				s_bProductCode = txtbProductCode.getText();
				
				try {
					pst = con.prepareStatement("insert into supplier(SUPPLIER_NAME, SUPPLIER_CONTACT, PRODUCT_CODE)values(?,?,?)");
					pst.setString(1, s_bName);
					pst.setString(2, s_bSupplierContact);
					pst.setString(3, s_bProductCode);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					txtbSupplierName.setText("");
					txtbSupplierContact.setText("");
					txtbProductCode.setText("");
					txtbSupplierName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBounds(374, 399, 105, 42);
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(259, 346, 105, 42);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbSupplierName.setText("");
				txtbSupplierContact.setText("");
				txtbProductCode.setText("");
				txtbSupplierName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 128, 936, 311);
		frame.getContentPane().add(products_Inventory_Screen);
		
		table = new JTable();
		products_Inventory_Screen.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 152, 475, 42);
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
					String SUPPLIER_ID = txtbSC.getText();
					//
					pst = con.prepareStatement("select SUPPLIER_NAME, SUPPLIER_CONTACT, PRODUCT_CODE from supplier where SUPPLIER_ID = ?");
					pst.setString(1,SUPPLIER_ID);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String SUPPLIER_NAME = rs.getString(1);
						String SUPPLIER_CONTACT = rs.getString(2);
						String PRODUCT_CODE = rs.getString(3);
						txtbSupplierName.setText(SUPPLIER_NAME);
						txtbSupplierContact.setText(SUPPLIER_CONTACT);
						txtbProductCode.setText(PRODUCT_CODE);
					}
					else
					{
						txtbSC.setText("");
						txtbSupplierName.setText("");
						txtbSupplierContact.setText("");
						txtbProductCode.setText("");
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
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setBounds(144, 346, 105, 42);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s_bSC, s_bName, s_bSupplierContact, s_bProductCode;
				
				s_bSC = txtbSC.getText();
				s_bName = txtbSupplierName.getText();
				s_bSupplierContact = txtbSupplierContact.getText();
				s_bProductCode = txtbProductCode.getText();
				
				try {
					pst = con.prepareStatement("update supplier set SUPPLIER_NAME = ?, SUPPLIER_CONTACT = ?, PRODUCT_CODE = ? where SUPPLIER_ID = ?");
					pst.setString(1, s_bName);
					pst.setString(2, s_bSupplierContact);
					pst.setString(3, s_bProductCode);
					pst.setString(4, s_bSC);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Update!");
					table_load();
					txtbSC.setText("");
					txtbSupplierName.setText("");
					txtbSupplierContact.setText("");
					txtbProductCode.setText("");
					txtbSupplierName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(374, 346, 105, 42);
		btnNewButton_1.setBackground(new Color(250, 128, 114));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SUPPLIER_ID;
				
				SUPPLIER_ID =  txtbSC.getText();
				
				try {
					pst = con.prepareStatement("delete from supplier where SUPPLIER_ID = ?");
					pst.setString(1, SUPPLIER_ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Succesfully Deleted!");
					table_load();
					txtbSupplierName.setText("");
					txtbSupplierContact.setText("");
					txtbProductCode.setText("");
					txtbSupplierName.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnNewButton_1);
		
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
		
		
		JLabel lblProductsInventory = new JLabel("Suppplier Details");
		lblProductsInventory.setForeground(Color.BLACK);
		lblProductsInventory.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblProductsInventory.setBackground(new Color(255, 248, 220));
		lblProductsInventory.setBounds(891, 82, 309, 35);
		frame.getContentPane().add(lblProductsInventory);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Add record to:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 75, 474, 66);
		frame.getContentPane().add(panel_3);
		
		JButton btnCustomer = new JButton("Products");
		btnCustomer.setBounds(10, 13, 87, 42);
		panel_3.add(btnCustomer);
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Customer.main(null);
			}
		});
		btnCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnCustomer.setBackground(new Color(255, 222, 173));
		
		JButton btnOrder = new JButton("Customer");
		btnOrder.setBounds(107, 13, 87, 42);
		panel_3.add(btnOrder);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Customer_Order.main(null);
			}
		});
		btnOrder.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnOrder.setBackground(new Color(255, 228, 181));
		
		JButton btnPayment = new JButton("Order");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Customer.main(null);
			}
		});
		btnPayment.setBounds(204, 13, 87, 42);
		panel_3.add(btnPayment);
		btnPayment.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 14));
		btnPayment.setBackground(new Color(255, 222, 173));
		
		JButton btnSupplier = new JButton("Payment");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Payment.main(null);
			}
		});
		btnSupplier.setBounds(301, 13, 76, 42);
		panel_3.add(btnSupplier);
		btnSupplier.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 13));
		btnSupplier.setBackground(new Color(255, 222, 173));
		
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

	protected String txtbSupplierContact() {
		// TODO Auto-generated method stub
		return null;
	}
}
