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

public class SabTrendahan {

	private JFrame frame;
	private JTextField txtbName;
	private JTextField txtbCost;
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
			pst = con.prepareStatement("SELECT * FROM products_inventory");
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
		panel.setBounds(20, 202, 475, 233);
		panel.setBackground(new Color(255, 160, 122));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Name");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 22, 168, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Cost");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 57, 168, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Type");
		lblNewLabel_1_3.setBackground(new Color(255, 165, 0));
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 92, 168, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Product Size");
		lblNewLabel_1_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_1.setBounds(10, 125, 122, 24);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Product Quantity");
		lblNewLabel_1_3_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_2.setBounds(10, 152, 168, 34);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Product Limit Quantity");
		lblNewLabel_1_3_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_3.setBounds(10, 184, 168, 37);
		panel.add(lblNewLabel_1_3_3);
		
		txtbName = new JTextField();
		txtbName.setColumns(10);
		txtbName.setBounds(199, 27, 266, 20);
		panel.add(txtbName);
		
		txtbCost = new JTextField();
		txtbCost.setColumns(10);
		txtbCost.setBounds(199, 61, 266, 20);
		panel.add(txtbCost);
		
		txtbQuantity = new JTextField();
		txtbQuantity.setColumns(10);
		txtbQuantity.setBounds(199, 160, 266, 24);
		panel.add(txtbQuantity);
		
		txtbLimitQuantity = new JTextField();
		txtbLimitQuantity.setColumns(10);
		txtbLimitQuantity.setBounds(199, 193, 266, 24);
		panel.add(txtbLimitQuantity);
		
		JComboBox<String> comboBoxP_Type = new JComboBox<String>();
		comboBoxP_Type.addItem("");
		comboBoxP_Type.addItem("T-Shirt");
		comboBoxP_Type.addItem("Sando");
		comboBoxP_Type.addItem("Crop top");
		comboBoxP_Type.addItem("Dress");
		comboBoxP_Type.addItem("Hoodie");
		comboBoxP_Type.addItem("Jacket");
		comboBoxP_Type.addItem("Blazer");
		comboBoxP_Type.setBounds(199, 92, 266, 22);
		panel.add(comboBoxP_Type);
		
		JComboBox<String> comboBoxP_Size = new JComboBox<String>();
		comboBoxP_Size.addItem("");
		comboBoxP_Size.addItem("Extra Small");
		comboBoxP_Size.addItem("Small");
		comboBoxP_Size.addItem("Medium");
		comboBoxP_Size.addItem("Large");
		comboBoxP_Size.addItem("Extra Large");
		comboBoxP_Size.setBounds(199, 127, 266, 22);
		panel.add(comboBoxP_Size);
		
		JButton btnNewButton = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnNewButton.setBounds(31, 448, 105, 42);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pi_bName, pi_bCost, pi_bType, pi_bSize, pi_bQuantity, pi_bLQuantity;
				
				pi_bName = txtbName.getText();
				pi_bCost = txtbCost.getText();
				pi_bType = (String) comboBoxP_Type.getSelectedItem();
				pi_bSize = (String) comboBoxP_Size.getSelectedItem();
				pi_bQuantity = txtbQuantity.getText();
				pi_bLQuantity = txtbLimitQuantity.getText();
				
				try {
					pst = con.prepareStatement("insert into products_inventory(PRODUCT_NAME, PRODUCT_COST, PRODUCT_TYPE, PRODUCT_SIZE, PRODUCT_QUANTITY, PRODUCT_LIMITQUANTITY)values(?,?,?,?,?,?)");
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
					comboBoxP_Type.setSelectedItem(1);
					comboBoxP_Size.setSelectedItem(1);
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
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBounds(375, 501, 105, 42);
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(260, 448, 105, 42);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbName.setText("");
				txtbCost.setText("");
				comboBoxP_Type.setSelectedItem(1);
				comboBoxP_Size.setSelectedItem(1);
				txtbQuantity.setText("");
				txtbLimitQuantity.setText("");
				txtbName.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 128, 936, 415);
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
					String product_Code = txtbSC.getText();
					//
					pst = con.prepareStatement("select PRODUCT_NAME, PRODUCT_COST, PRODUCT_TYPE, PRODUCT_SIZE, PRODUCT_QUANTITY, PRODUCT_LIMITQUANTITY from products_inventory where PRODUCT_CODE = ?");
					pst.setString(1, product_Code);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String product_Name = rs.getString(1);
						String product_Cost = rs.getString(2);
						String product_Type = rs.getString(3);
						String product_Sizes = rs.getString(4);
						String product_Quantity = rs.getString(5);
						String product_LimitQuantity = rs.getString(6);
						txtbName.setText(product_Name);
						txtbCost.setText(product_Cost);
						comboBoxP_Type.setSelectedItem(product_Type);
						comboBoxP_Size.setSelectedItem(product_Sizes);
						txtbQuantity.setText(product_Quantity);
						txtbLimitQuantity.setText(product_LimitQuantity);
					}
					else
					{
						txtbSC.setText("");
						txtbName.setText("");
						txtbCost.setText("");
						comboBoxP_Type.setSelectedItem("");
						comboBoxP_Size.setSelectedItem("");
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
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setBounds(145, 448, 105, 42);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pi_bCode, pi_bName, pi_bCost, pi_bType, pi_bSize, pi_bQuantity, pi_bLQuantity;
				
				pi_bCode = txtbSC.getText();
				pi_bName = txtbName.getText();
				pi_bCost = txtbCost.getText();
				pi_bType = (String) comboBoxP_Type.getSelectedItem();
				pi_bSize = (String) comboBoxP_Size.getSelectedItem();
				pi_bQuantity = txtbQuantity.getText();
				pi_bLQuantity = txtbLimitQuantity.getText();
				
				try {
					pst = con.prepareStatement("update products_inventory set PRODUCT_NAME = ?, PRODUCT_COST = ?, PRODUCT_TYPE = ?, PRODUCT_SIZE = ?, PRODUCT_QUANTITY = ?, PRODUCT_LIMITQUANTITY = ? where PRODUCT_CODE = ?");
					pst.setString(1, pi_bName);
					pst.setString(2, pi_bCost);
					pst.setString(3, pi_bType);
					pst.setString(4, pi_bSize);
					pst.setString(5, pi_bQuantity);
					pst.setString(6, pi_bLQuantity);
					pst.setString(7, pi_bCode);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Update!");
					table_load();
					txtbSC.setText("");
					txtbName.setText("");
					txtbCost.setText("");
					comboBoxP_Type.setSelectedItem(1);
					comboBoxP_Size.setSelectedItem(1);
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
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(375, 448, 105, 42);
		btnNewButton_1.setBackground(new Color(250, 128, 114));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String product_Code;
				
				product_Code =  txtbSC.getText();
				
				try {
					pst = con.prepareStatement("delete from products_inventory where PRODUCT_CODE = ?");
					pst.setString(1, product_Code);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Succesfully Deleted!");
					table_load();
					txtbName.setText("");
					txtbCost.setText("");
					comboBoxP_Type.setSelectedItem("");
					comboBoxP_Size.setSelectedItem("");
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
		
		
		JLabel lblProductsInventory = new JLabel("Products Inventory");
		lblProductsInventory.setForeground(Color.BLACK);
		lblProductsInventory.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblProductsInventory.setBackground(new Color(255, 248, 220));
		lblProductsInventory.setBounds(891, 82, 309, 35);
		frame.getContentPane().add(lblProductsInventory);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Add record to:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 77, 474, 66);
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
