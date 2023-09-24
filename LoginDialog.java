package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
/*
 * A class to display the login dialog for existing users
 */
public class LoginDialog extends JDialog {

	private static User user;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfusername;
	private JTextField tfPassword;
	User u;
	PCVS pcvs;
	private PatientMenuDialog pmenu;
	private AdminMenuDialog amenu;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		
		setBounds(100, 100, 250, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel username = new JLabel("Username");
			username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(username);
		}
		{
			tfusername = new JTextField();
			tfusername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfusername);
			tfusername.setColumns(10);
		}
		{
			JLabel password = new JLabel("Password");
			password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(password);
		}
		{
			tfPassword = new JTextField();
			tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfPassword);
			tfPassword.setColumns(10);
		}
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Enter");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String username = tfusername.getText();
						String password = tfPassword.getText();
						
						if(username.isEmpty() || password.isEmpty()) {
							JOptionPane.showMessageDialog(LoginDialog.this, "Please fill in all fields to proceed!");
						} else {
							UserTM utm = new UserTM(pcvs);
							if(username.equalsIgnoreCase(username) && password.equalsIgnoreCase(password));{
								if(user instanceof Administrator) {
									AdminMenuDialog amenu = new AdminMenuDialog();
									amenu.setVisible(true);
									LoginDialog.this.dispose();
								
								}else {
									PatientMenuDialog pmenu = new PatientMenuDialog();
									pmenu.setVisible(true);
									LoginDialog.this.dispose();
									
								}
							}
						}
					}
				});
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}


