package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
/*
 * A class for patient to request new vaccination appointment
 */
public class RequestVaccinationAppointment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfvaccinename;
	static PCVS pcvs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RequestVaccinationAppointment dialog = new RequestVaccinationAppointment();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("Request Vaccination Appointment");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RequestVaccinationAppointment() {
		pcvs = new PCVS();
		setBounds(100, 100, 300, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel vaccineName = new JLabel("Enter Vaccine Name:");
			vaccineName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(vaccineName);
		}
		{
			tfvaccinename = new JTextField();
			tfvaccinename.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfvaccinename);
			tfvaccinename.setColumns(10);
		}
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String vaccineName = tfvaccinename.getText();
						
						if(vaccineName.isEmpty()) {
							JOptionPane.showMessageDialog(RequestVaccinationAppointment.this, "Please fill in the blank to proceed");
						} else {
							tfvaccinename.setText("");
							pcvs.findVaccine(vaccineName);
							//todo
						}
						//dispose();
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PCVSGui pcvsGui = new PCVSGui();
						pcvsGui.setVisible(true);
						RequestVaccinationAppointment.this.dispose();
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
