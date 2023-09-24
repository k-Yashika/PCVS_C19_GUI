package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

/*
 * A class to display the sign up dialog for new users
 */
public class SignUpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	static PCVS pcvs;
	
	//to assign user to either admin or patient type
	private ButtonGroup usertype; 
	
	private JTextField tfusername;
	private JTextField tfpassword;
	private JTextField tfemail;
	private JTextField tffullname;
	private JLabel icpassport;
	private JTextField tficpass;
	private JLabel centrename;
	private JTextField tfcentrename;
	
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnPatient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignUpDialog dialog = new SignUpDialog(pcvs);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("Sign Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignUpDialog(PCVS pcvs) {
		usertype = new ButtonGroup();
		
		setModal(true);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel typeOfuser = new JLabel("Type of User:");
			typeOfuser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(typeOfuser);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				rdbtnAdmin = new JRadioButton("Administrator");
				rdbtnAdmin.setSelected(true);
				rdbtnAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				panel.add(rdbtnAdmin);
				usertype.add(rdbtnAdmin);			
			
				rdbtnPatient = new JRadioButton("Patient");
				usertype.add(rdbtnPatient);
				rdbtnPatient.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				panel.add(rdbtnPatient);
				
			}
		}
		{
			JLabel username = new JLabel("Username:");
			username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(username);
		
		
			tfusername = new JTextField();
			tfusername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfusername);
			tfusername.setColumns(10);
		
		
			JLabel password = new JLabel("Password:");
			password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(password);
		
		
			tfpassword = new JTextField();
			tfpassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfpassword);
			tfpassword.setColumns(10);
		
		
			JLabel email = new JLabel("Email:");
			email.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(email);
		
		
			tfemail = new JTextField();
			tfemail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfemail);
			tfemail.setColumns(10);
		
		
			JLabel fullname = new JLabel("Full Name:");
			fullname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(fullname);
		
		
			tffullname = new JTextField();
			tffullname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tffullname);
			tffullname.setColumns(10);
		
		
			JLabel icpassport = new JLabel("IC / Passport: ");
			icpassport.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(icpassport);
		
		
			tficpass = new JTextField();
			tficpass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tficpass);
			tficpass.setColumns(10);
		
			//intialize the window with both fields hidden until user chooses user type
		icpassport.setEnabled(false); 
		tficpass.setEnabled(false);
		
		
			JLabel centrename = new JLabel("Centre Name:");
			centrename.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(centrename);
		
		
			tfcentrename = new JTextField();
			tfcentrename.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfcentrename);
			tfcentrename.setColumns(10);
		
		centrename.setEnabled(false);
		centrename.setEnabled(false);
				
		//if user is patient
		rdbtnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centrename.setEnabled(false);
				tfcentrename.setEnabled(false);
				icpassport.setEnabled(true);
				tficpass.setEnabled(true);
			}
		});
		
		//if user is admin
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				icpassport.setEnabled(false);
				tficpass.setEnabled(false);
				centrename.setEnabled(true);
				tfcentrename.setEnabled(true);
			}
		});
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Register");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = tfusername.getText();
						String password = tfpassword.getText();
						String email = tfemail.getText();
						String fullname = tffullname.getText();
						
						if(username.isEmpty() || password.isEmpty() || email.isEmpty() || fullname.isEmpty()) {
							JOptionPane.showMessageDialog(SignUpDialog.this, "Please fill in all the fields to proceed!");
						}else {
							/*if(rdbtnAdmin.isSelected()) {
								String centrename = tfcentrename.getText();
								User user = new User(username, password, email, fullname);
								pcvs.add(user);
								
								JOptionPane.showMessageDialog(SignUpDialog.this, "You have been registered succefully!");
								
								SignUpDialog.this.dispose();
							}else {*/
								String icpassport = tficpass.getText();
									
								if(icpassport.isEmpty()) {
									JOptionPane.showMessageDialog(SignUpDialog.this, "Please enter ic or passport details!");
								}
								User user = new Patient(username, password, email, fullname, icpassport);
								pcvs.add(user);
									
								JOptionPane.showMessageDialog(SignUpDialog.this, "You have been registered successfully! Proceed to login");
									
								SignUpDialog.this.dispose(); //to close the sign up window
							
						}
					}
				});
				
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SignUpDialog.this.dispose();
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
