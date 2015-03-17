package Master;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class DocumentMaster extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentMaster frame = new DocumentMaster();
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
	public DocumentMaster() {
		setTitle("Document Master");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Name");
		label.setBounds(17, 30, 56, 14);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(109, 30, 287, 20);
		panel.add(textField);
		
		JButton button = new JButton("Save");
		button.setBounds(17, 140, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Find");
		button_1.setBounds(116, 140, 89, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Clear");
		button_2.setBounds(215, 140, 89, 23);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Delete");
		button_3.setBounds(314, 140, 89, 23);
		panel.add(button_3);
		
		JLabel lblNewLabel = new JLabel("Remark");
		lblNewLabel.setBounds(17, 80, 46, 14);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(109, 77, 287, 20);
		panel.add(textField_1);
	}
}
