package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * A class to for admin to record new vaccine batch
 */
public class RecordNewVaccineBatch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfBatchNum;
	private JTextField tfExpirydate;
	private JTextField tfqtyAvailable;
	
	private BatchTM btm;
	private Batch b;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecordNewVaccineBatch dialog = new RecordNewVaccineBatch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("Record New Vaccine Batch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordNewVaccineBatch() {
		setBounds(100, 100, 350, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel batchnum = new JLabel("Batch Number:");
			batchnum.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(batchnum);
		}
		{
			tfBatchNum = new JTextField();
			tfBatchNum.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfBatchNum);
			tfBatchNum.setColumns(10);
		}
		{
			JLabel expirydate = new JLabel("Expiry Date:");
			expirydate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(expirydate);
		}
		{
			tfExpirydate = new JTextField();
			tfExpirydate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfExpirydate);
			tfExpirydate.setColumns(10);
		}
		{
			JLabel qtyAvailable = new JLabel("Quanitty Available:");
			qtyAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(qtyAvailable);
		}
		{
			tfqtyAvailable = new JTextField();
			tfqtyAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(tfqtyAvailable);
			tfqtyAvailable.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Record");
				okButton.addActionListener(new ActionListener() {
					String batchnum;
					String expirydate;
					String qtyAvailable;
					
					public void actionPerformed(ActionEvent e) {
						if(batchnum.isEmpty() || expirydate.isEmpty() || qtyAvailable.isEmpty()) {
							JOptionPane.showMessageDialog(RecordNewVaccineBatch.this,"Please fill in all the fields!");
						} else{	
							tfBatchNum.setText("");
							tfExpirydate.setText("");
							tfqtyAvailable.setText("");
							tfBatchNum.requestFocus();
							
							//b = new Batch(batchnum, expirydate, qtyAvailable);
							JOptionPane.showMessageDialog(RecordNewVaccineBatch.this, "Batch Recorded Successfully");
							RecordNewVaccineBatch.this.dispose();
						}
					}
				});
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
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
						RecordNewVaccineBatch.this.dispose();
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
