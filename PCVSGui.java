package GUI;
/*
 * Yashika Khandelwal
 * B1902094
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Canvas;
import java.awt.Panel;

/*
 * PCVS System controller class
 */
public class PCVSGui extends JFrame {

	private static PCVS pcvs; 
	private static Patient patient;
	
	private JPanel contentPane;
	private JFileChooser fc;
	private SignUpDialog signUpDialog;
	private LoginDialog loginDialog;
	private DisplayAllUsers allUsersDialog;
	private DisplayAllAppointments allApptsDialog;
	

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PCVSGui frame = new PCVSGui();
					frame.setVisible(true);
					frame.setTitle("PCVS");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//read the pcvs file ?
				
	}
	

	/**
	 * Create the frame.
	 */
	public PCVSGui() {
		fc = new JFileChooser();
		pcvs = new PCVS();
		//patient = new Patient();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File"); //to allow user to read a file
		menuBar.add(file);
		JMenuItem load = new JMenuItem("Open");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = fc.showOpenDialog(PCVSGui.this);
				if(choice == JFileChooser.APPROVE_OPTION) {
					File openFile = fc.getSelectedFile();
					try {
						FileInputStream fis = new FileInputStream(openFile);
						ObjectInputStream ois = new ObjectInputStream(fis);
						pcvs = (PCVS) ois.readObject();
						JOptionPane.showMessageDialog(PCVSGui.this, "File Load Success");
						ois.close();
					} catch(IOException ioex) {
						ioex.printStackTrace();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		file.add(load);
		
		JMenuItem save = new JMenuItem("Save"); //to allow user to write a file
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = fc.showSaveDialog(PCVSGui.this);
				File file = fc.getSelectedFile();
				if(choice == JFileChooser.APPROVE_OPTION) {
					try {
						FileOutputStream fos = new FileOutputStream(file);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(pcvs);
						oos.flush();
						JOptionPane.showMessageDialog(PCVSGui.this, "File Save Success");
						oos.close();
					} catch(IOException ioex) {
						ioex.printStackTrace();
					}
				}
			}
		});
		file.add(save);
		
		JMenuItem sysExit = new JMenuItem("Exit");
		sysExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(sysExit);
		
		JMenu about = new JMenu("About");
		menuBar.add(about);
		
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		
		initialised(); 
	
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{526, 0};
		gbl_contentPane.rowHeights = new int[]{80, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel pcvsTitle = new JLabel("Private Covid-19 Vaccination System");
		//pcvsTitle.setIcon(new ImageIcon(PCVSGui.class.getResource("/Images/pcvsLogo.png")));
		pcvsTitle.setBackground(Color.WHITE);
		pcvsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pcvsTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_pcvsTitle = new GridBagConstraints();
		gbc_pcvsTitle.insets = new Insets(0, 0, 5, 0);
		gbc_pcvsTitle.fill = GridBagConstraints.BOTH;
		gbc_pcvsTitle.gridx = 0;
		gbc_pcvsTitle.gridy = 0;
		contentPane.add(pcvsTitle, gbc_pcvsTitle);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpDialog = new SignUpDialog(pcvs);
				signUpDialog.setVisible(true);
			}
		});
		
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSignUp.anchor = GridBagConstraints.NORTH;
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 0);
		gbc_btnSignUp.gridx = 0;
		gbc_btnSignUp.gridy = 1;
		contentPane.add(btnSignUp, gbc_btnSignUp);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDialog login = new LoginDialog();
				login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				login.setVisible(true);
			}
		});
		
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		JButton btnDispAllUsers = new JButton("Display All Users");
		btnDispAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allUsersDialog = new DisplayAllUsers(pcvs);
				allUsersDialog.setVisible(true);
			}
		});
		btnDispAllUsers.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnDispAllUsers = new GridBagConstraints();
		gbc_btnDispAllUsers.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDispAllUsers.insets = new Insets(0, 0, 5, 0);
		gbc_btnDispAllUsers.gridx = 0;
		gbc_btnDispAllUsers.gridy = 3;
		contentPane.add(btnDispAllUsers, gbc_btnDispAllUsers);
		
		JButton btnDispAllAppts = new JButton("Display All Appointments");
		btnDispAllAppts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allApptsDialog = new DisplayAllAppointments(patient);
				signUpDialog.setVisible(true);
			}
		});
		btnDispAllAppts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnDispAllAppts = new GridBagConstraints();
		gbc_btnDispAllAppts.insets = new Insets(0, 0, 5, 0);
		gbc_btnDispAllAppts.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDispAllAppts.gridx = 0;
		gbc_btnDispAllAppts.gridy = 4;
		contentPane.add(btnDispAllAppts, gbc_btnDispAllAppts);
		
		
	}
	
	public static void initialised() {
    	pcvs.add(new Vaccine("Serum Institute", "AstraZeneca"));
    	pcvs.add(new Vaccine("Sinovac Biotech", "CoronaVac"));
    	pcvs.add(new Vaccine("BioNTech SE", "Pfizer-BioNTech"));
    	
    	HealthcareCentre centre = new HealthcareCentre(
    		"ABC Medical Centre", "Kelana Jaya");
    	pcvs.add(centre);

    	Administrator admin = new Administrator("j", "j", "j", "j", centre);
    	centre.add(admin);
    	pcvs.add(admin);

    	pcvs.add(new Patient("f", "f", "f", "f", "A1236"));
    	pcvs.add(new Patient("a", "a", "a", "a", "A1234"));

    	centre = new HealthcareCentre("GT Hospital", 
    		"Green Lane, Penang");
    	pcvs.add(centre);
    	
    	admin = new Administrator("k", "k", "k", "k", centre);
    	centre.add(admin);
    	pcvs.add(admin);
    	
    	pcvs.add(new Patient("c", "c", "c", "c", "A1235"));

    	Vaccine vaccine = pcvs.findVaccine(1);
    	centre = admin.getHealthcareCentre();
	}

}
