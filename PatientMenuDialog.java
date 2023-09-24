package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * A class to display the patient menu options
 */
public class PatientMenuDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private RequestVaccinationAppointment rva;
	private ViewVaccinationAppointmentStatus vvas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatientMenuDialog dialog = new PatientMenuDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Patient Menu");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PatientMenuDialog() {
		setBounds(100, 100, 450, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{426, 0};
		gbl_contentPanel.rowHeights = new int[] {30, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton requestApptsbtn = new JButton("Request New Vaccination Appointment");
			requestApptsbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rva = new RequestVaccinationAppointment();
					rva.setVisible(true);
					PatientMenuDialog.this.dispose();
				}
			});
			requestApptsbtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			GridBagConstraints gbc_requestApptsbtn = new GridBagConstraints();
			gbc_requestApptsbtn.fill = GridBagConstraints.BOTH;
			gbc_requestApptsbtn.insets = new Insets(0, 0, 5, 0);
			gbc_requestApptsbtn.gridx = 0;
			gbc_requestApptsbtn.gridy = 0;
			contentPanel.add(requestApptsbtn, gbc_requestApptsbtn);
		}
		{
			JButton viewVaxStatusBtn = new JButton("View Vaccination Appontment Status");
			viewVaxStatusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vvas = new ViewVaccinationAppointmentStatus();
					vvas.setVisible(true);
					PatientMenuDialog.this.dispose();
				}
			});
			viewVaxStatusBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			GridBagConstraints gbc_viewVaxStatusBtn = new GridBagConstraints();
			gbc_viewVaxStatusBtn.insets = new Insets(0, 0, 5, 0);
			gbc_viewVaxStatusBtn.fill = GridBagConstraints.BOTH;
			gbc_viewVaxStatusBtn.gridx = 0;
			gbc_viewVaxStatusBtn.gridy = 1;
			contentPanel.add(viewVaxStatusBtn, gbc_viewVaxStatusBtn);
		}
		{
			JButton btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PCVSGui pcvsGui = new PCVSGui();
					pcvsGui.setVisible(true);
					PatientMenuDialog.this.dispose();
				}
			});
			btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			GridBagConstraints gbc_btnLogout = new GridBagConstraints();
			gbc_btnLogout.insets = new Insets(0, 0, 5, 0);
			gbc_btnLogout.gridx = 0;
			gbc_btnLogout.gridy = 2;
			contentPanel.add(btnLogout, gbc_btnLogout);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Return");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PCVSGui pcvsGui = new PCVSGui();
						pcvsGui.setVisible(true);
						PatientMenuDialog.this.dispose();
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
