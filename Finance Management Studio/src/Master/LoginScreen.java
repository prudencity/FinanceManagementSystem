package Master;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;
import java.awt.Button;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.UIManager;


public class LoginScreen {

	static LoginScreen window;
	private JFrame frame;
	private JTextField username_text;
	private JTextField password_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginScreen();
					window.frame.setVisible(true);
					window.frame.setLocation(450, 200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//
		JPanel filler_panel_1 = new JPanel();
		filler_panel_1.setBackground(SystemColor.controlDkShadow);
		filler_panel_1.setBounds(0, 193, 434, 68);
		frame.getContentPane().add(filler_panel_1);

		JPanel filler_panel_2 = new JPanel();
		filler_panel_2.setBounds(0, 65, 434, 127);
		frame.getContentPane().add(filler_panel_2);

		JLabel username_label = new JLabel("Username");
		username_label.setBounds(184, 32, 69, 14);
		filler_panel_2.add(username_label);

		username_text = new JTextField();
		username_text.setBounds(263, 29, 86, 20);
		filler_panel_2.add(username_text);
		username_text.setColumns(10);

		JLabel passowrd_label = new JLabel("Password");
		passowrd_label.setBounds(184, 57, 69, 14);
		filler_panel_2.add(passowrd_label);

		password_text = new JTextField();
		password_text.setBounds(263, 54, 86, 20);
		filler_panel_2.add(password_text);
		password_text.setColumns(10);


		JButton login_button = new JButton("Login");
		login_button.setBounds(242, 93, 74, 23);
		login_button.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent ae) {

//				if(ae.getSource()==login_button)
//				{
					try {
						Connection conn;
						String dbuser = "root";
						String dbpassw = "mysql";
						String databasename = "fms";
						String url = "jdbc:mysql://localhost/" + databasename;
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection (url, dbuser, dbpassw);
						Statement st = conn.createStatement();
						String uname=username_text.getText().toString();

						String passw=password_text.getText().toString();
						ResultSet res = st.executeQuery("SELECT * FROM user_details where LoginName='"+uname+"' and Password ='"+passw+"'");
						if (res.next()) {
							MainScreen mainscreen = new MainScreen();
							mainscreen.setLocation(300,100);
							mainscreen.setVisible(true);
							window.frame.dispose();
							//JOptionPane.showMessageDialog(login_button, "Login Successful.");
						}
						else{
							JOptionPane.showMessageDialog(login_button,"Invalid User Name/Password");
						}

				
					}
					catch ( ClassNotFoundException e ) {
						JOptionPane.showMessageDialog(login_button,e.getMessage());
					}
					catch(SQLException e) {
						JOptionPane.showMessageDialog(login_button,"Please contact the Software Developer and notify that a SQL error occurred on login.");
					}

				}
			//}
		});
		filler_panel_2.setLayout(null);
		filler_panel_2.add(login_button);




		JLabel icon_label = new JLabel("New label");
		icon_label.setBackground(UIManager.getColor("Button.disabledForeground"));
		icon_label.setIcon(new ImageIcon(".\\resources\\images\\login_icon.gif"));
		icon_label.setBounds(10, 6, 164, 116);
		filler_panel_2.add(icon_label);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(0, 0, 434, 66);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel headerlogin_label = new JLabel("Login ");
		headerlogin_label.setBounds(190, 21, 53, 21);
		headerlogin_label.setForeground(Color.DARK_GRAY);
		headerlogin_label.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(headerlogin_label);
	}
}
