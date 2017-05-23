package view;

import dbConnection.MyDataBase;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FarmerGUI {

	private JFrame frame;
	private JTable tableBuyer;
	private JScrollPane scrollPane;
	private JTable tableChicken;
	private JScrollPane scrollPane_1;
	private JButton btnChickenStock;
	private JTable tableTurkey;
	private JScrollPane scrollPane_2;
	private JButton btnTurkeyStock;
	private JTable tableTransaction;
	private JScrollPane scrollPane_3;
	private JButton btnNewButton;
	private JTextField textFieldSearch;
	private JTextField textFieldBuyerID;
	private JTextField textFieldBuyerName;
	private JTextField textFieldBuyerTelNo;
	private JTextField textFieldBuyerEmail;
	private JTextField textFieldBuyerAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmerGUI window = new FarmerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FarmerGUI() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		final Connection connection = MyDataBase.doConnection();
		frame = new JFrame("Farmer Information System");
		frame.setBackground(Color.PINK);
		frame.setBounds(200, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToFarmer = new JLabel("Welcome to Farmer Information System");
		lblWelcomeToFarmer.setBounds(253, 11, 308, 14);
		lblWelcomeToFarmer.setFont(new Font("Tahoma", Font.ITALIC, 16));
		frame.getContentPane().add(lblWelcomeToFarmer);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 87, 324, 209);
		frame.getContentPane().add(scrollPane);
		
		tableBuyer = new JTable();
		scrollPane.setViewportView(tableBuyer);
		
		JButton btnMyInformation = new JButton("My Buyers");
		btnMyInformation.setBounds(349, 53, 144, 23);
		btnMyInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						String query= "select * from buyer";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet res= preparedStatement.executeQuery();
						tableBuyer.setModel(DbUtils.resultSetToTableModel(res));
						res.close();
						preparedStatement.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			
		});
		frame.getContentPane().add(btnMyInformation);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(106, 415, 387, 219);
		frame.getContentPane().add(scrollPane_1);
		
		tableChicken = new JTable();
		scrollPane_1.setViewportView(tableChicken);
		
		btnChickenStock = new JButton("Chicken Stock");
		btnChickenStock.setBounds(217, 376, 125, 23);
		btnChickenStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from chicken";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet res= preparedStatement.executeQuery();
					tableChicken.setModel(DbUtils.resultSetToTableModel(res));
					res.close();
					preparedStatement.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(btnChickenStock);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(587, 415, 387, 219);
		frame.getContentPane().add(scrollPane_2);
		
		tableTurkey = new JTable();
		scrollPane_2.setViewportView(tableTurkey);
		
		btnTurkeyStock = new JButton("Turkey Stock");
		btnTurkeyStock.setBounds(725, 376, 136, 23);
		btnTurkeyStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from turkey";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet res= preparedStatement.executeQuery();
					tableTurkey.setModel(DbUtils.resultSetToTableModel(res));
					res.close();
					preparedStatement.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(btnTurkeyStock);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(593, 87, 381, 212);
		frame.getContentPane().add(scrollPane_3);
		
		tableTransaction= new JTable();
		scrollPane_3.setViewportView(tableTransaction);
		
		btnNewButton = new JButton("My Transactions");
		btnNewButton.setBounds(728, 53, 155, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from transaction";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet res= preparedStatement.executeQuery();
					tableTransaction.setModel(DbUtils.resultSetToTableModel(res));
					res.close();
					preparedStatement.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		textFieldSearch = new JTextField("Search By name");
		textFieldSearch.setBounds(364, 307, 210, 23);
		
		textFieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try{
					String query="select * from buyer  where buyerName=?";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs=pst.executeQuery();
					tableBuyer.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					
				} catch (Exception e1){
					e1.printStackTrace();
				}
				
				
			}
		});
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnSaveBuyer = new JButton("Save");
		btnSaveBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into buyer (buyerId,BuyerName,BuyerTelno,BuyerEmail,BuyerAddress) values (?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, textFieldBuyerID.getText());
					preparedStatement.setString(2, textFieldBuyerName.getText());
					preparedStatement.setString(3, textFieldBuyerTelNo.getText());
					preparedStatement.setString(4, textFieldBuyerEmail.getText());
					preparedStatement.setString(5, textFieldBuyerAddress.getText());

					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveBuyer.setBounds(62, 251, 89, 23);
		frame.getContentPane().add(btnSaveBuyer);
		
		JButton btnUpdateBuyer = new JButton("Update");
		btnUpdateBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query= "Update buyer set buyerId='"+textFieldBuyerID.getText()+"',BuyerName='"+textFieldBuyerName.getText()+"',BuyerTelno='"+textFieldBuyerTelNo.getText()+"',BuyerEmail='"+textFieldBuyerEmail.getText()+"',BuyerAddress='"+textFieldBuyerAddress.getText()+"'where buyerId='"+textFieldBuyerID.getText()+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data updated");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdateBuyer.setBounds(10, 275, 89, 23);
		frame.getContentPane().add(btnUpdateBuyer);
		
		JButton btnBuyer = new JButton("Delete");
		btnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="delete from buyer where BuyerId='"+textFieldBuyerID.getText()+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuyer.setBounds(106, 275, 89, 23);
		frame.getContentPane().add(btnBuyer);
		
		JLabel lblBuyerID = new JLabel("BuyerID");
		lblBuyerID.setBounds(10, 73, 54, 14);
		frame.getContentPane().add(lblBuyerID);
		
		JLabel lblBuyerName = new JLabel("BuyerName");
		lblBuyerName.setBounds(10, 110, 79, 14);
		frame.getContentPane().add(lblBuyerName);
		
		JLabel lblBuyerTelno = new JLabel("BuyerTelNo");
		lblBuyerTelno.setBounds(10, 147, 79, 14);
		frame.getContentPane().add(lblBuyerTelno);
		
		JLabel lblBuyerEmail = new JLabel("BuyerEmail");
		lblBuyerEmail.setBounds(10, 184, 79, 14);
		frame.getContentPane().add(lblBuyerEmail);
		
		JLabel lblBuyerAddress = new JLabel("BuyerAddress");
		lblBuyerAddress.setBounds(10, 218, 89, 14);
		frame.getContentPane().add(lblBuyerAddress);
		
		textFieldBuyerID = new JTextField();
		textFieldBuyerID.setBounds(116, 70, 127, 20);
		frame.getContentPane().add(textFieldBuyerID);
		textFieldBuyerID.setColumns(10);
		
		textFieldBuyerName = new JTextField();
		textFieldBuyerName.setText("");
		textFieldBuyerName.setBounds(116, 107, 127, 20);
		frame.getContentPane().add(textFieldBuyerName);
		textFieldBuyerName.setColumns(10);
		
		textFieldBuyerTelNo = new JTextField();
		textFieldBuyerTelNo.setBounds(116, 144, 127, 20);
		frame.getContentPane().add(textFieldBuyerTelNo);
		textFieldBuyerTelNo.setColumns(10);
		
		textFieldBuyerEmail = new JTextField();
		textFieldBuyerEmail.setBounds(116, 181, 127, 20);
		frame.getContentPane().add(textFieldBuyerEmail);
		textFieldBuyerEmail.setColumns(10);
		
		textFieldBuyerAddress = new JTextField();
		textFieldBuyerAddress.setText("");
		textFieldBuyerAddress.setBounds(116, 215, 127, 20);
		frame.getContentPane().add(textFieldBuyerAddress);
		textFieldBuyerAddress.setColumns(10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(75, 50, 24, -9);
		frame.getContentPane().add(layeredPane);
	}
}
