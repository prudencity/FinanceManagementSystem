package Master;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AgentMaster extends JFrame {

	private JPanel contentPane;
	public static JTextField agentname_am_text;
	public static JTextField agentaddress_am_text;
	public static JTextField agentcontact_am_text;
	public ResultSet resFind;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentMaster frame = new AgentMaster();
					
					frame.setVisible(true);
					frame.setLocation(500,200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void clearScreenAgentMaster() {
		agentname_am_text.setText(null);
		agentcontact_am_text.setText(null);
		agentaddress_am_text.setText(null);
	}
	
	public void SetSearchResults(ResultSet rs) throws SQLException {
		
		resFind = rs;
		showRecord();

	}


	public void showRecord() throws SQLException {

		//cm.firmname_text.setText(cid);
		agentname_am_text.setText(resFind.getString("AgentName"));
		agentaddress_am_text.setText(resFind.getString("AgentAddress"));
		agentcontact_am_text.setText(resFind.getString("AgentContact"));
		
		

	}
	public AgentMaster() {
	
		setTitle("Agent Master");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(27, 30, 56, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setBounds(27, 80, 56, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact");
		lblNewLabel_2.setBounds(27, 130, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		agentname_am_text = new JTextField();
		agentname_am_text.setBounds(119, 30, 287, 20);
		contentPane.add(agentname_am_text);
		agentname_am_text.setColumns(10);
		
		agentaddress_am_text = new JTextField();
		agentaddress_am_text.setColumns(10);
		agentaddress_am_text.setBounds(119, 80, 287, 20);
		contentPane.add(agentaddress_am_text);
		
		agentcontact_am_text = new JTextField();
		agentcontact_am_text.setColumns(10);
		agentcontact_am_text.setBounds(119, 130, 287, 20);
		contentPane.add(agentcontact_am_text);
		
		JButton btn_Save = new JButton("Save");
		btn_Save.addActionListener(new ActionListener() {
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
					ResultSet res=st.executeQuery("Select * from customer_master");	
					String name=agentname_am_text.getText().toString();
					String address=agentaddress_am_text.getText().toString();
					String contact=agentcontact_am_text.getText().toString();
					ResultSet res1=st.executeQuery("SELECT * FROM agent_master");		
					if(res1.next())
					{
						st.executeUpdate("INSERT INTO `fms`.`agent_master` (`AgentName`, `AgentAddress`, `AgentContact`) VALUES ('"+name+"', '"+address+"', '"+contact+"')");
						JOptionPane.showMessageDialog(btn_Save, "Data Successfully Added");
						clearScreenAgentMaster();
					}
					else
					{
						st.executeUpdate("INSERT INTO `fms`.`agent_master` (`AgentId`, `AgentName`, `AgentAddress`, `AgentContact`) VALUES ('1', '"+ name+ "','"+ address+ "', '"+contact+"')");
						JOptionPane.showMessageDialog(btn_Save, "Data Successfully Added");
						clearScreenAgentMaster();
					}

					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_Save.setBounds(20, 190, 89, 23);
		contentPane.add(btn_Save);
		
		JButton btn_Find = new JButton("Find");
		btn_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn;
					String dbuser = "root";
					String dbpassw = "mysql";
					String databasename = "fms";
					String url = "jdbc:mysql://localhost/" + databasename;
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection (url, dbuser, dbpassw);
					Statement st = conn.createStatement();

					String name=agentname_am_text.getText().toString();
					if(name.equals(""))
						name=null;
					//					String customerid=customerid_find_text.getText().toString();
					//					if(customerid.equals(""))	
					//						customerid="87787";
					String mobileno=agentcontact_am_text.getText().toString();
					if (mobileno.equals(""))
						mobileno="";
					ResultSet resFind=st.executeQuery("SELECT * FROM agent_master where "
							+ "AgentName LIKE '%"+name+"%' OR AgentContact='"+mobileno+"'"	);

					if (!resFind.next() ) {
						JOptionPane.showMessageDialog(btn_Find, "No records found");
					} else {
						SetSearchResults(resFind);
					}
				}
				catch ( ClassNotFoundException e1 ) {
					JOptionPane.showMessageDialog(btn_Find,e1.getMessage());
				}
				catch(SQLException e1) {
					JOptionPane.showMessageDialog(btn_Find,"Please contact the Software Developer"
							+ " and notify that a SQL error occurred on login.");
					e1.printStackTrace();
				}
			}
		});
		btn_Find.setBounds(119, 190, 89, 23);
		contentPane.add(btn_Find);
		
		JButton btn_Clear = new JButton("Clear");
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearScreenAgentMaster();
			}

			
		});
		btn_Clear.setBounds(218, 190, 89, 23);
		contentPane.add(btn_Clear);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
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


					ResultSet res1=st.executeQuery("Select * from agent_master");
					if(res1.next()){
						int i=JOptionPane.showConfirmDialog(btn_delete, "Are you sure you want to delete this data?");
						if(i == JOptionPane.YES_OPTION){
							String nameofperson= agentname_am_text.getText().toString();
							int j=JOptionPane.showConfirmDialog(btn_delete, "Are you sure you want to delete agent "+nameofperson+"?");
							if(j==JOptionPane.YES_OPTION)
							{
								st.executeUpdate("DELETE FROM agent_master WHERE AgentName='"+nameofperson+"'");							
								JOptionPane.showMessageDialog(btn_delete, "Data Successfully Deleted");

							}
							clearScreenAgentMaster();
						}


						else{
							JOptionPane.showMessageDialog(btn_delete, "No Records to delete");
						}


					}
				}
				catch ( ClassNotFoundException e ) {
					JOptionPane.showMessageDialog(btn_delete,e.getMessage());
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(btn_delete,"Please contact the Software Developer and notify that a SQL error occurred on login.");
				}


			}
		});
		btn_delete.setBounds(317, 190, 89, 23);
		contentPane.add(btn_delete);
	}
}
