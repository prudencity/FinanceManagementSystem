package Master;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;


public class FindWindow extends JFrame  {
	private static JTextField name_find_text;
	private JTextField customerid_find_text;	
	private JLabel lblFidMobile;
	private JTextField mobileno_find_text;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindWindow frame = new FindWindow();
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
	public FindWindow() {
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 259);
		getContentPane().setLayout(null);

		JLabel lblFindName = new JLabel("Name");
		lblFindName.setBounds(40, 26, 46, 14);
		getContentPane().add(lblFindName);

		name_find_text = new JTextField();
		name_find_text.setBounds(134, 23, 178, 20);
		getContentPane().add(name_find_text);
		name_find_text.setColumns(10);

		JLabel lblFindCustomerId = new JLabel("Customer Id");
		lblFindCustomerId.setBounds(40, 66, 89, 14);
		getContentPane().add(lblFindCustomerId);

		customerid_find_text = new JTextField();
		customerid_find_text.setBounds(134, 63, 178, 20);
		getContentPane().add(customerid_find_text);
		customerid_find_text.setColumns(10);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn;
					String dbuser = "root";
					String dbpassw = "mysql";
					String databasename = "fms";
					String url = "jdbc:mysql://localhost/" + databasename;
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection (url, dbuser, dbpassw);
					Statement st = conn.createStatement();

					String name=name_find_text.getText().toString();
					if(name.equals(""))
						name=null;
					String customerid=customerid_find_text.getText().toString();
					if(customerid.equals(""))	
						customerid="87787";
					String mobileno=mobileno_find_text.getText().toString();
					if (mobileno.equals(""))
						mobileno="jbjhbhjb";
					ResultSet res=st.executeQuery("SELECT * FROM customer_master where NameOfPerson LIKE '%"+name+"%' OR CustomerId='"+customerid+"' OR Mobile1='"+mobileno+"'");
					
					if (!res.next() ) {
						JOptionPane.showMessageDialog(btnFind, "No records found");
					} else {

						CustomerMaster cm=new CustomerMaster();
						cm.setSize(750,550);
						cm.setAlwaysOnTop(true);
						cm.setLocation(200, 0);
						
						cm.setVisible(true);
						
						cm.SetSearchResults(res);
					}



				}
				catch ( ClassNotFoundException e ) {
					JOptionPane.showMessageDialog(btnFind,e.getMessage());
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(btnFind,"Please contact the Software Developer and notify that a SQL error occurred on login.");
					e.printStackTrace();
				}

			}
		});
		btnFind.setBounds(138, 160, 89, 23);
		getContentPane().add(btnFind);

		lblFidMobile = new JLabel("Mobile No");
		lblFidMobile.setBounds(40, 106, 89, 14);
		getContentPane().add(lblFidMobile);

		mobileno_find_text = new JTextField();
		mobileno_find_text.setColumns(10);
		mobileno_find_text.setBounds(134, 103, 178, 20);
		getContentPane().add(mobileno_find_text);


	}
}
