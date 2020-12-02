import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;

/**
 * CSE 360 Fall 2020 Final Project
 * @author Damian McGregor
 */
public class AddAttendance extends Observable {
	protected ArrayList<Student> Roster;
	protected ArrayList<String[]> UnknownStudent;
	protected ArrayList<String[]> Attendance;
	protected String date;
	protected JFrame frame;
	
	/**
	 * Default Constructor
	 */
	public AddAttendance()  {
		Roster = new ArrayList<Student>();
		Attendance = new ArrayList<String[]>();
	}
	
	/**
	 * Add new Attendance file
	 * @param file
	 * @param newRoster
	 * @param newFrame
	 * @param Date
	 * @throws IOException
	 */
	public void addAttendance(File file, ArrayList<Student> newRoster, JFrame newFrame, String Date) throws IOException {
		UnknownStudent = new ArrayList<String[]>();
		date = Date;
		Roster = newRoster;
		frame = newFrame;
		String row;
		
		// Add a new time slot for each student
		for (int i = 0; i < Roster.size(); i++) {
			Roster.get(i).addTime(0);
		}
		
		int studentCount = 0;
		BufferedReader csvReader = new BufferedReader(new FileReader(file));		// buffer read the file
		while ((row = csvReader.readLine()) != null) {		// read each line in the file
		    String[] data = row.split(",");		// separate each line by commas
		    
		    int count = 0;
		    while(count < Roster.size() - 1 && !data[0].equals(Roster.get(count).getAsurite())) {		// count stops at end of Roster or at matching ASURITE
		    	count++;
		    }

		    // if asurite matches, set new time
	    	if (data[0].equals(Roster.get(count).getAsurite())) {
	    		
	    		// if time has not been added to Student: increase studentCount
	    		if (Roster.get(count).getTime() == 0) {
		    		studentCount++;
	    		}
	    		
	    		int newTime = Roster.get(count).getTime() + Integer.parseInt(data[1]);		// add new time to existing time
	    		Roster.get(count).setTime(newTime);		// add new time to Student
	    	}
	    	else {		// if the Student is not in the Roster
	    		UnknownStudent.add(data);		// add Student to UnknownStudent
	    	}
		}

		// create new pop-up
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Data loaded for " + studentCount + " users in the roster",SwingConstants.CENTER);		// how many student attendance times were added
		JLabel newLabel1 = new JLabel(UnknownStudent.size() + " aditional attendee was found:",SwingConstants.CENTER);		// how many student times were not added
        panel.add(label);
		panel.add(newLabel1);
        panel.setSize(250, 250);
        
		for (int i = 0; i < UnknownStudent.size(); i++) {
			
			// display each student information that was not added
			JLabel newLabel2 = new JLabel(UnknownStudent.get(i)[0] + ", connected for " + UnknownStudent.get(i)[1],SwingConstants.CENTER);
			panel.add(newLabel2);
		}
        
		// create new dialog
		JDialog dialog = new JDialog(frame, "Add Attendance");
		dialog.setSize(250,250);
		dialog.setLocationRelativeTo(null);  
		dialog.add(panel);
		dialog.setVisible(true);
		
		setChanged(); 
        notifyObservers();
	}
	
	/**
	 * Get the latest Roster
	 * @return Roster
	 */
	public ArrayList<Student> getRoster() {
		return Roster;
	}
	
	/**
	 * Get the latest date
	 * @return date
	 */
	public String getDate() {
		return date;
	}
}


