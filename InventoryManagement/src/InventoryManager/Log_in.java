package InventoryManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Log_in extends JFrame {

	private JPanel contentPane;
	private JTextField txtbUsername;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JButton btnExit;
	protected JFrame delete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log_in frame = new Log_in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Log_in() {
		setTitle("Log in Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 314);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new LineBorder(new Color(255, 165, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtbUsername = new JTextField();
		txtbUsername.setColumns(10);
		txtbUsername.setBounds(193, 104, 266, 26);
		contentPane.add(txtbUsername);
		
		JLabel lblNewLabel_1_1 = new JLabel("Username");
		lblNewLabel_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(45, 102, 168, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		lblNewLabel_1_1_1.setBounds(45, 155, 168, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnLLogin = new JButton("Log in");
		btnLLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = txtbUsername.getText();
				String password = passwordField.getText();
				
				if (uname.equals("SABTrendahan") && password.equals("1234"))
				{
					JOptionPane.showMessageDialog(null, "User Logged In!");
					SabTrendahan.main(null);
					delete.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Mismatched!");
				}
			}
		});
		btnLLogin.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnLLogin.setBackground(Color.LIGHT_GRAY);
		btnLLogin.setBounds(293, 210, 105, 42);
		contentPane.add(btnLLogin);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 99, 71));
		panel.setBounds(0, 0, 508, 72);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("SAB Trendahan Inventory");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 30));
		lblNewLabel.setBackground(new Color(255, 248, 220));
		lblNewLabel.setBounds(111, 20, 305, 35);
		panel.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 155, 266, 25);
		contentPane.add(passwordField);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 19));
		btnExit.setBackground(new Color(205, 92, 92));
		btnExit.setBounds(45, 210, 105, 42);
		contentPane.add(btnExit);
	}
}
