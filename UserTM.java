package GUI;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/*
 * A class to get user details and display in table model
 */
public class UserTM extends AbstractTableModel{

	private PCVS pcvs;
	private List<User> user;
	private String[] title = {"No", "Type", "Username", "Password", "Email", "Full Name", "IC/Passport", "Centre Name"};
	
	public UserTM(List<User> list) {
		setUser(list);
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public UserTM(PCVS p) {
		pcvs = p;
	}
	
	public void add(User u) {
		pcvs.add(u);
	}
	
	public int getColumnCount() {
		return title.length;
		//add firetabledatachange to main
	}
	
	public int getRowCount() {
		return pcvs.numOfUsers();
	}
	
	public Object getValueAt(int row, int column) {
		User u = pcvs.getUsers().get(row);
		switch(column) {
		case 0:
			return row + 1;
		case 1:
			if(u instanceof Patient) {
				return "Patient";
			} else {
				return "Administrator";
			}
		case 2:
			return u.getUsername();
		case 3:
			return u.getPassword();
		case 4:
			return u.getEmail();
		case 5: 
			return u.getFullName();
		case 6:
			if(u instanceof Administrator) {
				return "N/A";
			} else {
				return ((Patient) u).getICPassport();
			}
		case 7: 
			if(u instanceof Patient) {
				return "N/A";
			} else {
				return ((Administrator) u).getHealthcareCentre();
			}
		default:
			return "";
		}
	}

	public String getColumnName(int column) {
		return title[column];
	}
	
	
}
