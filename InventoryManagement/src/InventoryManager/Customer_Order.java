package InventoryManager;
import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

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
import com.toedter.calendar.JDateChooser;

public class Customer_Order {

	protected static final DbUtils Payment = null;
	protected static final String ORDER_ID = null;
	private JTextField txtbProductCode;
	private JFrame frame;
	private JTextField txtbCustomerID;
	private JTextField txtbOrderQuantity;
	private JTable table;
	private JTextField txtbSC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Order window = new Customer_Order();
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
	public Customer_Order() {
		initialize();
		Connect();
		table_load();
	}
	
	//Initialize Connection
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtbOrderCost;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sabtrendahan?useTimezone=true&serverTimezone=UTC", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
	}
	//Load the table in the screen by linking the MySQL
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("SELECT * FROM customer_order");
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
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setBounds(100, 100, 1479, 591);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 209, 475, 227);
		panel.setBackground(new Color(255, 160, 122));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Customer ID");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 23, 168, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Code");
		lblNewLabel_1_3.setBackground(new Color(255, 165, 0));
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 58, 168, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Order Cost");
		lblNewLabel_1_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_1.setBounds(10, 93, 122, 24);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Order Size");
		lblNewLabel_1_3_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_2.setBounds(10, 120, 168, 34);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Order Quantity");
		lblNewLabel_1_3_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_3.setBounds(10, 151, 168, 37);
		panel.add(lblNewLabel_1_3_3);
		
		txtbCustomerID = new JTextField();
		txtbCustomerID.setColumns(10);
		txtbCustomerID.setBounds(199, 28, 266, 20);
		panel.add(txtbCustomerID);
		
		JTextField txtbOrderQuantity = new JTextField();
		txtbOrderQuantity.setColumns(10);
		txtbOrderQuantity.setBounds(199, 160, 266, 20);
		panel.add(txtbOrderQuantity);
		
		txtbProductCode = new JTextField();
		txtbProductCode.setColumns(10);
		txtbProductCode.setBounds(199, 63, 266, 20);
		panel.add(txtbProductCode);
		
		txtbOrderCost = new JTextField();
		txtbOrderCost.setColumns(10);
		txtbOrderCost.setBounds(199, 98, 266, 20);
		panel.add(txtbOrderCost);
		
		JLabel txtbDate = new JLabel("Order Date");
		txtbDate.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		txtbDate.setBounds(10, 184, 168, 37);
		panel.add(txtbDate);
		
		JDateChooser date_OrderDate = new JDateChooser();
		date_OrderDate.setBounds(199, 191, 266, 20);
		panel.add(date_OrderDate);
		
		JComboBox<String> comboBoxO_Size = new JComboBox<String>();
		comboBoxO_Size.addItem("");
		comboBoxO_Size.addItem("Extra Small");
		comboBoxO_Size.addItem("Small");
		comboBoxO_Size.addItem("Medium");
		comboBoxO_Size.addItem("Large");
		comboBoxO_Size.addItem("Extra Large");
		comboBoxO_Size.setBounds(199, 129, 266, 22);
		panel.add(comboBoxO_Size);
		
		JButton btnSaveCustomer = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnSaveCustomer.setBounds(30, 447, 105, 42);
		btnSaveCustomer.setBackground(Color.LIGHT_GRAY);
		btnSaveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String co_bCustomerID, co_bOrderCost, co_bProductCode, co_bOrderSize, co_bOrderQuantity, co_bOrderDate;
				co_bCustomerID = txtbCustomerID.getText();
				co_bProductCode = txtbProductCode.getText();
				co_bOrderCost = txtbOrderCost.getText();
				co_bOrderSize = (String) comboBoxO_Size.getSelectedItem();
				co_bOrderQuantity = txtbOrderQuantity.getText();
				co_bOrderDate = txtbDate.getText();
				try {
					pst = con.prepareStatement("insert into customer_order(CUSTOMER_ID, PRODUCT_CODE, ORDER_COST, ORDER_SIZE, ORDER_QUANTITY, ORDER_DATE)values(?,?,?,?,?,?)");
					pst.setString(1, co_bCustomerID);
					pst.setString(2, co_bProductCode);
					pst.setString(3, co_bOrderCost);
					pst.setString(4, co_bOrderSize);
					pst.setString(5, co_bOrderQuantity);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(date_OrderDate.getDate());
					pst.setString(6, date);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					txtbCustomerID.setText("");
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					comboBoxO_Size.setSelectedItem("");
					txtbOrderQuantity.setText("");
					date_OrderDate.setToolTipText("");
					txtbCustomerID.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnSaveCustomer);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBounds(374, 500, 105, 42);
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(259, 447, 105, 42);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbSC.setText("");
				txtbCustomerID.setText("");
				txtbProductCode.setText("");
				txtbOrderCost.setText("");
				comboBoxO_Size.setSelectedItem("");
				txtbOrderQuantity.setText("");
				date_OrderDate.setToolTipText("");
				txtbCustomerID.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 128, 936, 416);
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
					String ORDER_CODE = txtbSC.getText();
					//
					pst = con.prepareStatement("select CUSTOMER_ID, PRODUCT_CODE, ORDER_COST, ORDER_SIZE, ORDER_QUANTITY, ORDER_DATE from customer_order where ORDER_CODE = ?");
					pst.setString(1, ORDER_CODE);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String CUSTOMER_ID = rs.getString(1);
						String PRODUCT_CODE = rs.getString(2);
						String ORDER_COST = rs.getString(3);
						String ORDER_SIZE = rs.getString(4);
						String ORDER_QUANTITY = rs.getString(5);
						String ORDER_DATE = rs.getString(6);
						txtbSC.setText(ORDER_CODE);
						txtbCustomerID.setText(CUSTOMER_ID);
						txtbProductCode.setText(PRODUCT_CODE);
						txtbOrderCost.setText(ORDER_COST);
						comboBoxO_Size.setSelectedItem(ORDER_SIZE);
						txtbOrderQuantity.setText(ORDER_QUANTITY);
						date_OrderDate.setToolTipText(ORDER_DATE);
					}
					else
					{
						txtbSC.setText("");
						txtbCustomerID.setText("");
						txtbProductCode.setText("");
						txtbOrderCost.setText("");
						comboBoxO_Size.setSelectedItem("");
						txtbOrderQuantity.setText("");
						date_OrderDate.setToolTipText("");
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
		btnUpdateCustomer.setBounds(144, 447, 105, 42);
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String co_bSC, co_bCustomerID, co_bOrderCost, co_bProductCode, co_bOrderSize, co_bOrderQuantity, co_bOrderDate;
				
				co_bSC = txtbSC.getText();
				co_bCustomerID = txtbCustomerID.getText();
				co_bProductCode = txtbProductCode.getText();
				co_bOrderCost = txtbOrderCost.getText();
				co_bOrderSize = (String) comboBoxO_Size.getSelectedItem();
				co_bOrderQuantity = txtbOrderQuantity.getText();
				co_bOrderDate = txtbDate.getText();
				
				try {
					pst = con.prepareStatement("update customer_order set CUSTOMER_ID = ?, PRODUCT_CODE = ?, ORDER_COST = ?, ORDER_SIZE = ?, ORDER_QUANTITY = ?, ORDER_DATE = ?  where ORDER_CODE = ?");
					pst.setString(1, co_bCustomerID);
					pst.setString(2, co_bProductCode);
					pst.setString(3, co_bOrderCost);
					pst.setString(4, co_bOrderSize);
					pst.setString(5, co_bOrderQuantity);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(date_OrderDate.getDate());
					pst.setString(6, date);
					pst.setString(7,  co_bSC);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!");
					table_load();
					txtbSC.setText("");
					txtbCustomerID.setText("");
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					comboBoxO_Size.setSelectedItem("");;
					txtbOrderQuantity.setText("");
					date_OrderDate.setToolTipText("");
					txtbCustomerID.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdateCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnUpdateCustomer);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(374, 447, 105, 42);
		btnDelete.setBackground(new Color(250, 128, 114));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String order_code;
				
				order_code = txtbSC.getText();
				try {
					pst = con.prepareStatement("delete from customer_order where ORDER_CODE = ?");
					pst.setString(1, order_code);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Succesfully Deleted!");
					table_load();
					txtbCustomerID.setText("");
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					comboBoxO_Size.setSelectedItem("");
					txtbOrderQuantity.setText("");
					txtbCustomerID.requestFocus();
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
		
		JLabel lblCustomersOrderRecord = new JLabel("Customer's Order Record");
		lblCustomersOrderRecord.setForeground(Color.BLACK);
		lblCustomersOrderRecord.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblCustomersOrderRecord.setBackground(new Color(255, 248, 220));
		lblCustomersOrderRecord.setBounds(811, 82, 309, 35);
		frame.getContentPane().add(lblCustomersOrderRecord);
		
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
				DbUtils.main(null);
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
		btnSupplier_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 13));
		btnSupplier_1.setBackground(new Color(255, 222, 173));
		btnSupplier_1.setBounds(388, 13, 76, 42);
		panel_3.add(btnSupplier_1);
	}
}
