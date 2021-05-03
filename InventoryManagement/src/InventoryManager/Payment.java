package InventoryManager;
import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;

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
import com.toedter.calendar.JDateChooser;

public class Payment {

	private JFrame frame;
	private JTextField txtbProductCode;
	private JTextField txtbOrderCost;
	private JTable table;
	private JTextField txtbSC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment();
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
	public Payment() {
		initialize();
		Connect();
		table_load();
	}
	
	//Initialize Connection
	Connection con ;
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
			pst = con.prepareStatement("SELECT * FROM payment");
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
		frame.setBounds(100, 100, 1479, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 204, 475, 197);
		panel.setBackground(new Color(255, 160, 122));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Code");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 22, 168, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Order Cost");
		lblNewLabel_1_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 57, 168, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Payment Mode");
		lblNewLabel_1_3.setBackground(new Color(255, 165, 0));
		lblNewLabel_1_3.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 92, 168, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Date Received");
		lblNewLabel_1_3_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_1.setBounds(10, 125, 122, 24);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Status");
		lblNewLabel_1_3_2.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_3_2.setBounds(10, 152, 168, 34);
		panel.add(lblNewLabel_1_3_2);
		
		txtbProductCode = new JTextField();
		txtbProductCode.setColumns(10);
		txtbProductCode.setBounds(199, 27, 266, 20);
		panel.add(txtbProductCode);
		
		txtbOrderCost = new JTextField();
		txtbOrderCost.setColumns(10);
		txtbOrderCost.setBounds(199, 61, 266, 20);
		panel.add(txtbOrderCost);
		
		JComboBox<String> txtbPaymentMode = new JComboBox<String>();
		txtbPaymentMode.addItem("");
		txtbPaymentMode.addItem("COD");
		txtbPaymentMode.addItem("GCASH");
		txtbPaymentMode.setBounds(199, 96, 266, 22);
		panel.add(txtbPaymentMode);
		
		JDateChooser date_ReceivedDate = new JDateChooser();
		date_ReceivedDate.setBounds(199, 129, 266, 20);
		panel.add(date_ReceivedDate);
		
		JComboBox<String> txtbStatus = new JComboBox<String>();
		txtbStatus.addItem("");
		txtbStatus.addItem("Paid");
		txtbStatus.addItem("Processing");
		txtbStatus.setBounds(199, 161, 266, 22);
		panel.add(txtbStatus);
		
		JButton btnSaveCustomer = new JButton("Save"); //FOR SAVE JBUTTON Manipulation
		btnSaveCustomer.setBounds(30, 412, 105, 42);
		btnSaveCustomer.setBackground(Color.LIGHT_GRAY);
		btnSaveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String p_bProductCode, p_bOrderCost, p_bPaymentMode, p_bDateReceived, p_bStatus;
				
				p_bProductCode = txtbProductCode.getText();
				p_bOrderCost = txtbOrderCost.getText();
				p_bPaymentMode = (String) txtbPaymentMode.getSelectedItem();
				p_bDateReceived = date_ReceivedDate.getToolTipText();
				p_bStatus = (String) txtbStatus.getSelectedItem();
				
				try {
					pst = con.prepareStatement("insert into payment(PRODUCT_CODE, ORDER_COST, PAYMENT_MODE, DATE_RECEIVED, STATUS)values(?,?,?,?,?)");
					pst.setString(1, p_bProductCode);
					pst.setString(2, p_bOrderCost);
					pst.setString(3, p_bPaymentMode);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(date_ReceivedDate.getDate());
					pst.setString(4, date);
					pst.setString(5, p_bStatus);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					txtbPaymentMode.setSelectedItem("");
					date_ReceivedDate.setToolTipText("");
					txtbStatus.setSelectedItem("");
					txtbProductCode.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnSaveCustomer);
		
		JButton btnExit = new JButton("Exit"); //EXIT JButton Manipulation
		btnExit.setBounds(374, 465, 105, 42);
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(259, 412, 105, 42);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbSC.setText("");
				txtbProductCode.setText("");
				txtbOrderCost.setText("");
				txtbPaymentMode.setSelectedItem("");
				date_ReceivedDate.setToolTipText("");
				txtbStatus.setSelectedItem("");
				txtbProductCode.requestFocus();
			}
		});
		btnClear.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnClear);
		
		JScrollPane products_Inventory_Screen = new JScrollPane();
		products_Inventory_Screen.setBounds(505, 128, 936, 379);
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
					String payment_ID = txtbSC.getText();
					//
					pst = con.prepareStatement("select PRODUCT_CODE, ORDER_COST, PAYMENT_MODE, DATE_RECEIVED, STATUS from payment where PAYMENT_ID = ?");
					pst.setString(1, payment_ID);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String PRODUCT_CODE = rs.getString(1);
						String ORDER_COST = rs.getString(2);
						String PAYMENT_MODE = rs.getString(3);
						String DATE_RECEIVED = rs.getString(4);
						String STATUS = rs.getString(5);
						txtbSC.setText(payment_ID);
						txtbProductCode.setText(PRODUCT_CODE);
						txtbOrderCost.setText(ORDER_COST);
						txtbPaymentMode.setSelectedItem(PAYMENT_MODE);
						date_ReceivedDate.setToolTipText(DATE_RECEIVED);
						txtbStatus.setSelectedItem(STATUS);
					}
					else
					{
						txtbSC.setText("");
						txtbProductCode.setText("");
						txtbOrderCost.setText("");
						txtbPaymentMode.setSelectedItem("");
						date_ReceivedDate.setToolTipText("");
						txtbStatus.setSelectedItem("");
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
		btnUpdateCustomer.setBounds(144, 412, 105, 42);
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p_bSC, p_bProductCode, p_bOrderCost, p_bPaymentMode, p_bDateReceived, p_bStatus;
				
				p_bSC = txtbSC.getText();
				p_bProductCode = txtbProductCode.getText();
				p_bOrderCost = txtbOrderCost.getText();
				p_bPaymentMode = (String) txtbPaymentMode.getSelectedItem();
				p_bDateReceived = date_ReceivedDate.getToolTipText();
				p_bStatus = (String) txtbStatus.getSelectedItem();
				
				try {
					pst = con.prepareStatement("update payment set PRODUCT_CODE = ?, ORDER_COST = ?, PAYMENT_MODE = ?, DATE_RECEIVED = ? , STATUS = ? where PAYMENT_ID - ?");
					pst.setString(1, p_bProductCode);
					pst.setString(2, p_bOrderCost);
					pst.setString(3, p_bPaymentMode);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(date_ReceivedDate.getDate());
					pst.setString(4, date);
					pst.setString(5, p_bStatus);
					pst.setString(6, p_bSC);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!");
					table_load();
					txtbSC.setText("");
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					txtbPaymentMode.setSelectedItem("");
					date_ReceivedDate.setToolTipText("");
					txtbStatus.setSelectedItem("");
					txtbProductCode.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdateCustomer.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		frame.getContentPane().add(btnUpdateCustomer);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(374, 412, 105, 42);
		btnDelete.setBackground(new Color(250, 128, 114));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String payment_ID;
				
				payment_ID =  txtbSC.getText();
				
				try {
					pst = con.prepareStatement("delete from payment where PAYMENT_ID = ?");
					pst.setString(1, payment_ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Succesfully Deleted!");
					table_load();
					txtbSC.setText("");
					txtbProductCode.setText("");
					txtbOrderCost.setText("");
					txtbPaymentMode.setSelectedItem("");
					date_ReceivedDate.setToolTipText("");
					txtbStatus.setSelectedItem("");
					txtbProductCode.requestFocus();
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
		
		JLabel lblCustomerInformation = new JLabel("Payments Record");
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
		btnSupplier_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 13));
		btnSupplier_1.setBackground(new Color(255, 222, 173));
		btnSupplier_1.setBounds(388, 13, 76, 42);
		panel_3.add(btnSupplier_1);
	}
}
