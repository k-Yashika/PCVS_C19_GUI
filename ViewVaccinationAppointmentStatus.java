package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Vaccination.Status;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
/*
 * A class for patient to view appointment status
 */
public class ViewVaccinationAppointmentStatus extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Patient patient;
	private Vaccination vaccination;
	private Status tfstatus;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewVaccinationAppointmentStatus dialog = new ViewVaccinationAppointmentStatus();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewVaccinationAppointmentStatus() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		patient.allVaccinations();
		{
			JLabel status = new JLabel("Status of Last Appointment:");
			status.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(status);
		}
		{
			
		}
		{
		}
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
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
						PCVSGui pcvsGUi = new PCVSGui();
						pcvsGUi.setVisible(true);
						ViewVaccinationAppointmentStatus.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
