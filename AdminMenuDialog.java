package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * A class to display the admin menu options
 */
public class AdminMenuDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private RecordNewVaccineBatch rnb;
	private ViewVaccineBatchInfor vvbi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminMenuDialog dialog = new AdminMenuDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Admin Menu");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminMenuDialog() {
		setBounds(100, 100, 450, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{426, 0};
		gbl_contentPanel.rowHeights = new int[] {30, 0, 0, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton viewbatch = new JButton("View Vaccine Batch Info");
			viewbatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vvbi = new ViewVaccineBatchInfor(null);
					vvbi.setVisible(true);
					AdminMenuDialog.this.dispose();
				}
			});
			vvbi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			GridBagConstraints gbc_vvbi = new GridBagConstraints();
			gbc_vvbi.fill = GridBagConstraints.BOTH;
			gbc_vvbi.insets = new Insets(0, 0, 5, 0);
			gbc_vvbi.gridx = 0;
			gbc_vvbi.gridy = 0;
			contentPanel.add(vvbi, gbc_vvbi);
		}
		{
			JButton recordnewbatch = new JButton("Record New Vaccine Batch");
			recordnewbatch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rnb = new RecordNewVaccineBatch();
					rnb.setVisible(true);
					AdminMenuDialog.this.dispose();
				}
			});
			recordnewbatch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			GridBagConstraints gbc_recordnewbatch = new GridBagConstraints();
			gbc_recordnewbatch.fill = GridBagConstraints.BOTH;
			gbc_recordnewbatch.gridx = 0;
			gbc_recordnewbatch.gridy = 1;
			contentPanel.add(recordnewbatch, gbc_recordnewbatch);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
