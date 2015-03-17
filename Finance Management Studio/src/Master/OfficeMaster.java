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


public class OfficeMaster extends JFrame {

	private JPanel contentPane;
	public static JTextField officename_om_text;
	public static JTextField officeaddress_om_text;
	public static JTextField officecontact_om_text;
	public ResultSet resFind;

	public void clearScreenAgentMaster() {
		officename_om_text.setText(null);
		officecontact_om_text.setText(null);
		officeaddress_om_text.setText(null);
	}
	
	public void SetSearchResults(ResultSet rs) throws SQLException {
		
		resFind = rs;
		showRecord();

	}


	public void showRecord() throws SQLException {

		//cm.firmname_text.setText(cid);
		officename_om_text.setText(resFind.getString("OfficeName"));
		officeaddress_om_text.setText(resFind.getString("OfficeAddress"));
		officecontact_om_text.setText(resFind.getString("OfficeContact"));
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficeMaster frame = new OfficeMaster();
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
	public OfficeMaster() {
		setTitle("Office Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(17, 30, 56, 14);
		contentPane.add(label);
		
		officename_om_text = new JTextField();
		officename_om_text.setColumns(10);
		officename_om_text.setBounds(109, 30, 287, 20);
		contentPane.add(officename_om_text);
		
		JLabel label_1 = new JLabel("Address");
		label_1.setBounds(17, 80, 56, 14);
		contentPane.add(label_1);
		
		officeaddress_om_text = new JTextField();
		officeaddress_om_text.setColumns(10);
		officeaddress_om_text.setBounds(109, 80, 287, 20);
		contentPane.add(officeaddress_om_text);
		
		JLabel label_2 = new JLabel("Contact");
		label_2.setBounds(17, 130, 56, 14);
		contentPane.add(label_2);
		
		officecontact_om_text = new JTextField();
		officecontact_om_text.setColumns(10);
		officecontact_om_text.setBounds(109, 130, 287, 20);
		contentPane.add(officecontact_om_text);
		
		JButton btn_Save = new JButton("Save");
		btn_Save.addActionListener(new ActionListener() {
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
					ResultSet res=st.executeQuery("Select * from customer_master");	
					String name=officename_om_text.getText().toString();
					String address=officeaddress_om_text.getText().toString();
					String contact=officecontact_om_text.getText().toString();
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

					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_Save.setBounds(10, 190, 89, 23);
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

					String name=officename_om_text.getText().toString();
					if(name.equals(""))
						name=null;
					//					String customerid=customerid_find_text.getText().toString();
					//					if(customerid.equals(""))	
					//						customerid="87787";
					String mobileno=officecontact_om_text.getText().toString();
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
		btn_Find.setBounds(109, 190, 89, 23);
		contentPane.add(btn_Find);
		
		JButton btn_Clear = new JButton("Clear");
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearScreenAgentMaster();
			}
		});
		btn_Clear.setBounds(208, 190, 89, 23);
		contentPane.add(btn_Clear);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
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


					ResultSet res1=st.executeQuery("Select * from agent_master");
					if(res1.next()){
						int i=JOptionPane.showConfirmDialog(btn_delete, "Are you sure you want to delete this data?");
						if(i == JOptionPane.YES_OPTION){
							String nameofperson= officename_om_text.getText().toString();
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
				catch ( ClassNotFoundException e1 ) {
					JOptionPane.showMessageDialog(btn_delete,e1.getMessage());
				}
				catch(SQLException e1) {
					JOptionPane.showMessageDialog(btn_delete,"Please contact the Software Developer and notify that a SQL error occurred on login.");
				}
			}
		});
		btn_delete.setBounds(307, 190, 89, 23);
		contentPane.add(btn_delete);
	}

}
