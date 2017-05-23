package view;

import java.awt.EventQueue;
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

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// Salam Alaikum I am new in Github
public class FarmerSoft {

	private JFrame frame;
	private JTable tableBuyer;
	private JTextField textFieldBuyerID;
	private JTextField textFieldBuyerName;
	private JTextField textFieldBuyerTelNo;
	private JTextField textFieldBuyerEmail;
	private JTextField textFieldBuyerAddress;
	private JTextField textFieldSearch;
	private JTable table;
	private JTable tableTransaction;
	private JTextField textFieldtransactionID;
	private JTextField textFieldbuyerID;
	private JTextField textFieldtransactionDate;
	private JTextField textFieldtransactionTime;
	private JTextField textFieldquantityChicken;
	private JTextField textFieldquantityTurkey;
	private JTable tableChicken;
	private JTextField textFieldchickenID;
	private JTextField textFieldtype;
	private JTextField textFieldpriceBuy;
	private JTextField textFieldconsumptionPerDay;
	private JTextField textFieldquantity;
	private JTextField textFielddateEntry;
	private JTable tableTurkey;
	private JTextField textFieldturkeyID;
	private JTextField textFieldpriceBuyT;
	private JTextField textFieldconsumptionPerDayT;
	private JTextField textFieldquantityT;
	private JTextField textFielddateEntryT;
	private JTextField Income;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmerSoft window = new FarmerSoft();
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
	public float getSpentTurkey(){
		int rowsCount= tableTurkey.getRowCount();
		
		float sum=0;
		for (int i=0; i<rowsCount;i++){
			sum=sum+Float.parseFloat(tableTurkey.getValueAt(i, 1).toString())*Float.parseFloat(tableTurkey.getValueAt(i, 3).toString())+Float.parseFloat(tableTurkey.getValueAt(i, 2).toString())*30;
			
		}
		return (float) (1.2*sum);
	}
	public float getSpentChicken(){
		int rowsCount= tableChicken.getRowCount();
		
		float sum=0;
		for (int i=0; i<rowsCount;i++){
			sum=sum+Float.parseFloat(tableChicken.getValueAt(i, 2).toString())*Float.parseFloat(tableChicken.getValueAt(i, 4).toString())+Float.parseFloat(tableChicken.getValueAt(i, 3).toString())*30;
			
		}
		return (float) (1.2*sum);
	}
	public void refreshTableBuyer(){
		try {
			final Connection connection = MyDataBase.doConnection();
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
	
	public void refreshTableTransaction(){
		try {
			final Connection connection = MyDataBase.doConnection();
			String query= "select transactionID,buyerID,buyerName,transactionDate,transactionTime,quantityChicken,quantityTurkey  from buyer natural join transaction";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet res= preparedStatement.executeQuery();
			tableTransaction.setModel(DbUtils.resultSetToTableModel(res));
			res.close();
			preparedStatement.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	public void refreshTableTurkey(){
		try {
			final Connection connection = MyDataBase.doConnection();
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
	public void refreshTableChicken(){
		try {
			final Connection connection = MyDataBase.doConnection();
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
	
	public FarmerSoft() throws ClassNotFoundException, SQLException {
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
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(47, 66, 903, 484);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelTransaction = new JPanel();
		tabbedPane.addTab("Transaction", null, panelTransaction, null);
		panelTransaction.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(347, 50, 523, 304);
		panelTransaction.add(scrollPane_3);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_3.setColumnHeaderView(scrollPane_1);
		
		tableTransaction = new JTable();
		scrollPane_1.setViewportView(tableTransaction);
		
		JButton btnNewButton = new JButton("Load Data for transaction");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						String query= "select transactionID,buyerID,buyerName,transactionDate,transactionTime,quantityChicken,quantityTurkey  from buyer natural join transaction";
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
		btnNewButton.setBounds(503, 54, 250, 23);
		panelTransaction.add(btnNewButton);
		
		JLabel lblTransactionid = new JLabel("transactionID");
		lblTransactionid.setBounds(29, 123, 101, 14);
		panelTransaction.add(lblTransactionid);
		
		JLabel lblBuyerid_1 = new JLabel("buyerID");
		lblBuyerid_1.setBounds(29, 160, 46, 14);
		panelTransaction.add(lblBuyerid_1);
		
		JLabel lblTransactiondate = new JLabel("transactionDate");
		lblTransactiondate.setBounds(29, 200, 101, 14);
		panelTransaction.add(lblTransactiondate);
		
		JLabel lblTransactiontime = new JLabel("transactionTime");
		lblTransactiontime.setBounds(29, 240, 101, 14);
		panelTransaction.add(lblTransactiontime);
		
		JLabel lblQuantitychicken = new JLabel("quantityChicken");
		lblQuantitychicken.setBounds(29, 280, 101, 14);
		panelTransaction.add(lblQuantitychicken);
		
		JLabel lblQuantityturkey = new JLabel("quantityTurkey");
		lblQuantityturkey.setBounds(29, 320, 101, 14);
		panelTransaction.add(lblQuantityturkey);
		
		textFieldtransactionID = new JTextField();
		textFieldtransactionID.setBounds(204, 120, 111, 20);
		panelTransaction.add(textFieldtransactionID);
		textFieldtransactionID.setColumns(10);
		
		textFieldbuyerID = new JTextField();
		textFieldbuyerID.setBounds(204, 150, 111, 20);
		panelTransaction.add(textFieldbuyerID);
		textFieldbuyerID.setColumns(10);
		
		textFieldtransactionDate = new JTextField();
		textFieldtransactionDate.setBounds(204, 190, 111, 20);
		panelTransaction.add(textFieldtransactionDate);
		textFieldtransactionDate.setColumns(10);
		
		textFieldtransactionTime = new JTextField();
		textFieldtransactionTime.setBounds(204, 237, 111, 20);
		panelTransaction.add(textFieldtransactionTime);
		textFieldtransactionTime.setColumns(10);
		
		textFieldquantityChicken = new JTextField();
		textFieldquantityChicken.setBounds(204, 277, 111, 20);
		panelTransaction.add(textFieldquantityChicken);
		textFieldquantityChicken.setColumns(10);
		
		textFieldquantityTurkey = new JTextField();
		textFieldquantityTurkey.setBounds(204, 317, 111, 20);
		panelTransaction.add(textFieldquantityTurkey);
		textFieldquantityTurkey.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into transaction (transactionID,buyerID,transactionDate,transactionTime,quantityChicken,quantityTurkey) values (?,?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, textFieldtransactionID.getText());
					preparedStatement.setString(2, textFieldbuyerID.getText());
					preparedStatement.setString(3, textFieldtransactionDate.getText());
					preparedStatement.setString(4, textFieldtransactionTime.getText());
					preparedStatement.setString(5, textFieldquantityChicken.getText());
					preparedStatement.setString(6, textFieldquantityTurkey.getText());



					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableTransaction();
				
				
			}
		});
		btnNewButton_1.setBounds(61, 380, 89, 23);
		panelTransaction.add(btnNewButton_1);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String query= "Update transaction set transactionDate='"+textFieldtransactionDate.getText()+"',transactionTime='"+textFieldtransactionTime.getText()+"',quantityChicken='"+textFieldquantityChicken.getText()+"',quantityTurkey='"+textFieldquantityTurkey.getText()+"'where transactionID='"+textFieldtransactionID.getText()+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data updated");
					

					//transactionID='"+textFieldtransactionID.getText()+"',farmerID='"+textFieldfarmerID.getText()+"',buyerID='"+textFieldBuyerID.getText()+"',

					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableTransaction();
			}
		});
		btnUpdate_1.setBounds(183, 380, 89, 23);
		panelTransaction.add(btnUpdate_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldtransactionID.setText(null);
				textFieldtransactionDate.setText(null);
				textFieldbuyerID.setText(null);
				textFieldtransactionTime.setText(null);
				textFieldquantityTurkey.setText(null);
				textFieldquantityChicken.setText(null);
				
			}
		});
		btnReset.setBounds(132, 414, 89, 23);
		panelTransaction.add(btnReset);
		
		JPanel panelChicken = new JPanel();
		tabbedPane.addTab("Chicken", null, panelChicken, null);
		panelChicken.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(291, 83, 581, 302);
		panelChicken.add(scrollPane_5);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_5.setViewportView(scrollPane_4);
		
		tableChicken = new JTable();
		scrollPane_4.setViewportView(tableChicken);
		
		JLabel lblChickenid = new JLabel("chickenID");
		lblChickenid.setBounds(22, 68, 46, 14);
		panelChicken.add(lblChickenid);
		
		JLabel lblType = new JLabel("type");
		lblType.setBounds(22, 108, 46, 14);
		panelChicken.add(lblType);
		
		JLabel lblPricebuy = new JLabel("priceBuy");
		lblPricebuy.setBounds(22, 161, 46, 14);
		panelChicken.add(lblPricebuy);
		
		JLabel lblConsumptionperday = new JLabel("consumptionPerDay");
		lblConsumptionperday.setBounds(22, 212, 103, 14);
		panelChicken.add(lblConsumptionperday);
		
		JLabel lblQuantity = new JLabel("quantity");
		lblQuantity.setBounds(22, 258, 46, 14);
		panelChicken.add(lblQuantity);
		
		JLabel lblDateentry = new JLabel("dateEntry");
		lblDateentry.setBounds(22, 313, 79, 14);
		panelChicken.add(lblDateentry);
		
		textFieldchickenID = new JTextField();
		textFieldchickenID.setBounds(133, 65, 114, 20);
		panelChicken.add(textFieldchickenID);
		textFieldchickenID.setColumns(10);
		
		textFieldtype = new JTextField();
		textFieldtype.setText("");
		textFieldtype.setBounds(133, 105, 114, 20);
		panelChicken.add(textFieldtype);
		textFieldtype.setColumns(10);
		
		textFieldpriceBuy = new JTextField();
		textFieldpriceBuy.setText("");
		textFieldpriceBuy.setBounds(133, 158, 114, 20);
		panelChicken.add(textFieldpriceBuy);
		textFieldpriceBuy.setColumns(10);
		
		textFieldconsumptionPerDay = new JTextField();
		textFieldconsumptionPerDay.setText("");
		textFieldconsumptionPerDay.setBounds(135, 209, 112, 20);
		panelChicken.add(textFieldconsumptionPerDay);
		textFieldconsumptionPerDay.setColumns(10);
		
		textFieldquantity = new JTextField();
		textFieldquantity.setText("");
		textFieldquantity.setBounds(133, 255, 114, 20);
		panelChicken.add(textFieldquantity);
		textFieldquantity.setColumns(10);
		
		textFielddateEntry = new JTextField();
		textFielddateEntry.setBounds(133, 310, 114, 20);
		panelChicken.add(textFielddateEntry);
		textFielddateEntry.setColumns(10);
		
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into chicken (chickenID,type,priceBuy,consumptionPerDay,quantity,dateEntry) values (?,?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, textFieldchickenID.getText());
					preparedStatement.setString(2, textFieldtype.getText());
					preparedStatement.setString(3, textFieldpriceBuy.getText());
					preparedStatement.setString(4, textFieldconsumptionPerDay.getText());
					preparedStatement.setString(5, textFieldquantity.getText());
					preparedStatement.setString(6, textFielddateEntry.getText());

					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableChicken();
			}
		});
		btnSave_1.setBounds(38, 364, 89, 23);
		panelChicken.add(btnSave_1);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "Update chicken set chickenID='"+textFieldchickenID.getText()+"',type='"+textFieldtype.getText()+"',priceBuy='"+textFieldpriceBuy.getText()+"',consumptionPerDay='"+textFieldconsumptionPerDay.getText()+"',quantity='"+textFieldquantity.getText()+"',dateEntry='"+textFielddateEntry.getText()+"'where chickenID='"+textFieldchickenID.getText()+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data updated");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableChicken();

			}
		});
		btnUpdate_2.setBounds(154, 364, 89, 23);
		panelChicken.add(btnUpdate_2);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from chicken where chickenID='"+textFieldchickenID.getText()+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableChicken();

			}
		});
		btnDelete_2.setBounds(154, 398, 89, 23);
		panelChicken.add(btnDelete_2);
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 textFieldchickenID.setText(null);
				 textFieldtype.setText(null);
				 textFieldpriceBuy.setText(null);
				 textFieldconsumptionPerDay.setText(null);
				 textFieldquantity.setText(null);
				 textFielddateEntry.setText(null);

				
			}
		});
		btnNewButton_2.setBounds(36, 398, 89, 23);
		panelChicken.add(btnNewButton_2);
		
		JPanel panelTurkey = new JPanel();
		tabbedPane.addTab("Turkey", null, panelTurkey, null);
		panelTurkey.setLayout(null);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(367, 139, 492, 273);
		panelTurkey.add(scrollPane_7);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_7.setViewportView(scrollPane_6);
		
		tableTurkey = new JTable();
		scrollPane_6.setViewportView(tableTurkey);
		
		JLabel lblTurkeyid = new JLabel("turkeyID");
		lblTurkeyid.setBounds(37, 106, 46, 14);
		panelTurkey.add(lblTurkeyid);
		
		JLabel lblPricebuy_1 = new JLabel("priceBuy");
		lblPricebuy_1.setBounds(37, 150, 46, 14);
		panelTurkey.add(lblPricebuy_1);
		
		JLabel lblConsumptionperday_1 = new JLabel("consumptionPerDay");
		lblConsumptionperday_1.setBounds(37, 199, 101, 14);
		panelTurkey.add(lblConsumptionperday_1);
		
		JLabel lblQuantity_1 = new JLabel("quantity");
		lblQuantity_1.setBounds(37, 256, 46, 14);
		panelTurkey.add(lblQuantity_1);
		
		JLabel lblDateentry_1 = new JLabel("dateEntry");
		lblDateentry_1.setBounds(37, 302, 78, 14);
		panelTurkey.add(lblDateentry_1);
		
		textFieldturkeyID = new JTextField();
		textFieldturkeyID.setBounds(194, 103, 122, 20);
		panelTurkey.add(textFieldturkeyID);
		textFieldturkeyID.setColumns(10);
		
		textFieldpriceBuyT = new JTextField();
		textFieldpriceBuyT.setText("");
		textFieldpriceBuyT.setBounds(194, 147, 122, 20);
		panelTurkey.add(textFieldpriceBuyT);
		textFieldpriceBuyT.setColumns(10);
		
		textFieldconsumptionPerDayT = new JTextField();
		textFieldconsumptionPerDayT.setBounds(194, 196, 122, 20);
		panelTurkey.add(textFieldconsumptionPerDayT);
		textFieldconsumptionPerDayT.setColumns(10);
		
		textFieldquantityT = new JTextField();
		textFieldquantityT.setBounds(194, 253, 122, 20);
		panelTurkey.add(textFieldquantityT);
		textFieldquantityT.setColumns(10);
		
		textFielddateEntryT = new JTextField();
		textFielddateEntryT.setBounds(194, 299, 122, 20);
		panelTurkey.add(textFielddateEntryT);
		textFielddateEntryT.setColumns(10);
		
		JButton btnSave_2 = new JButton("Save");
		btnSave_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into turkey (turkeyID,priceBuy,consumptionPerDay,quantity,dateEntry) values (?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, textFieldturkeyID.getText());
					preparedStatement.setString(2, textFieldpriceBuyT.getText());
					preparedStatement.setString(3, textFieldconsumptionPerDayT.getText());
					preparedStatement.setString(4, textFieldquantityT.getText());
					preparedStatement.setString(5, textFielddateEntryT.getText());

					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableTurkey();
			}
		});
		btnSave_2.setBounds(84, 370, 89, 23);
		panelTurkey.add(btnSave_2);
		
		JButton btnUpdate_3 = new JButton("Update");
		btnUpdate_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "Update turkey set turkeyID='"+textFieldturkeyID.getText()+"',priceBuy='"+textFieldpriceBuyT.getText()+"',consumptionPerDay='"+textFieldconsumptionPerDayT.getText()+"',quantity='"+textFieldquantityT.getText()+"',dateEntry='"+textFielddateEntryT.getText()+"'where turkeyID='"+textFieldturkeyID.getText()+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data updated");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableTurkey();
			}
		});
		btnUpdate_3.setBounds(227, 370, 89, 23);
		panelTurkey.add(btnUpdate_3);
		
		JButton btnDelete_3 = new JButton("Delete");
		btnDelete_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from turkey where turkeyID='"+textFieldturkeyID.getText()+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					



					
					
					preparedStatement.close();
					//res.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTableTurkey();
			}
		});
		btnDelete_3.setBounds(227, 404, 89, 23);
		panelTurkey.add(btnDelete_3);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 textFieldturkeyID.setText(null);
			     textFieldpriceBuyT.setText(null);
				 textFieldconsumptionPerDayT.setText(null);
				 textFieldquantityT.setText(null);
				 textFielddateEntryT.setText(null);
			}
		});
		btnReset_1.setBounds(84, 404, 89, 23);
		panelTurkey.add(btnReset_1);
		
		JPanel panelBuyer = new JPanel();
		tabbedPane.addTab("Buyer", null, panelBuyer, null);
		panelBuyer.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(300, 63, 531, 305);
		panelBuyer.add(scrollPane_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_2.setViewportView(scrollPane);
		
		tableBuyer = new JTable();
		scrollPane.setViewportView(tableBuyer);
		
		JLabel lblBuyerid = new JLabel("BuyerID");
		lblBuyerid.setBounds(29, 102, 63, 14);
		panelBuyer.add(lblBuyerid);
		
		JLabel lblBuyername = new JLabel("BuyerName");
		lblBuyername.setBounds(29, 142, 75, 14);
		panelBuyer.add(lblBuyername);
		
		JLabel lblNewLabel = new JLabel("BuyerTelNo");
		lblNewLabel.setBounds(29, 182, 75, 14);
		panelBuyer.add(lblNewLabel);
		
		JLabel lblBuyeremail = new JLabel("BuyerEmail");
		lblBuyeremail.setBounds(29, 225, 75, 14);
		panelBuyer.add(lblBuyeremail);
		
		JLabel lblBuyeraddress = new JLabel("BuyerAddress");
		lblBuyeraddress.setBounds(29, 280, 75, 14);
		panelBuyer.add(lblBuyeraddress);
		
		textFieldBuyerID = new JTextField();
		textFieldBuyerID.setBounds(152, 99, 121, 20);
		panelBuyer.add(textFieldBuyerID);
		textFieldBuyerID.setColumns(10);
		
		textFieldBuyerName = new JTextField();
		textFieldBuyerName.setBounds(152, 139, 121, 20);
		textFieldBuyerName.setText("");
		panelBuyer.add(textFieldBuyerName);
		textFieldBuyerName.setColumns(10);
		
		textFieldBuyerTelNo = new JTextField();
		textFieldBuyerTelNo.setBounds(152, 179, 121, 20);
		textFieldBuyerTelNo.setText("");
		panelBuyer.add(textFieldBuyerTelNo);
		textFieldBuyerTelNo.setColumns(10);
		
		textFieldBuyerEmail = new JTextField();
		textFieldBuyerEmail.setBounds(152, 222, 121, 20);
		textFieldBuyerEmail.setText("");
		panelBuyer.add(textFieldBuyerEmail);
		textFieldBuyerEmail.setColumns(10);
		
		textFieldBuyerAddress = new JTextField();
		textFieldBuyerAddress.setBounds(152, 277, 121, 20);
		panelBuyer.add(textFieldBuyerAddress);
		textFieldBuyerAddress.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(52, 345, 89, 23);
		btnSave.addActionListener(new ActionListener() {
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
				refreshTableBuyer();
			}
		});
		panelBuyer.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(162, 345, 89, 23);
		btnUpdate.addActionListener(new ActionListener() {
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
				refreshTableBuyer();
			}
		});
		panelBuyer.add(btnUpdate);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(405, 398, 293, 20);
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
		panelBuyer.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnReset_2 = new JButton("Reset");
		btnReset_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 textFieldBuyerID.setText(null);
				 textFieldBuyerName.setText(null);
				 textFieldBuyerTelNo.setText(null);
				 textFieldBuyerEmail.setText(null);
				 textFieldBuyerAddress.setText(null);

				
			}
		});
		btnReset_2.setBounds(114, 379, 89, 23);
		panelBuyer.add(btnReset_2);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to The Farmer Information System");
		lblWelcomeToThe.setBounds(217, 11, 464, 30);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		frame.getContentPane().add(lblWelcomeToThe);
		
		JButton btnMyIncome = new JButton("My Income");
		
		btnMyIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Income.setText(Float.toString(getSpentChicken()+getSpentTurkey()));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnMyIncome.setBounds(584, 586, 97, 32);
		frame.getContentPane().add(btnMyIncome);
		
		Income = new JTextField();
		
		Income.setBounds(749, 586, 86, 20);
		frame.getContentPane().add(Income);
		Income.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewProject = new JMenu("New Project");
		mnNewMenu_1.add(mnNewProject);
		
		JMenuItem mntmOpenNewProject = new JMenuItem("Open New project");
		mnNewProject.add(mntmOpenNewProject);
		refreshTableBuyer();
		refreshTableTransaction();
		refreshTableTurkey();
		refreshTableChicken();
	}
}
