

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.*;
//
//import org.jdatepicker.impl.*;
//import org.jdatepicker.util.*;
//import org.jdatepicker.*;


import java.util.*;

public class AddAttendance extends Observable {
	protected ArrayList<Student> Roster;
	protected ArrayList<String[]> UnknownStudent;
	protected ArrayList<String[]> Attendance;
	protected String date;
	protected JFrame frame;
	
	public AddAttendance()  {
		Roster = new ArrayList<Student>();
		Attendance = new ArrayList<String[]>();
	}
	
	public void addAttendance(File file, ArrayList<Student> newRoster, JFrame newFrame, String Date) throws IOException {
		UnknownStudent = new ArrayList<String[]>();
		date = Date;
		Roster = newRoster;
		frame = newFrame;
		String row;
		
		
		// this will continue to get user input 
//		Scanner scan = new Scanner(System.in);
//		System.out.print("Input new date:\t");
//		date = scan.nextLine();
		
		
		for (int i = 0; i < Roster.size(); i++) {
			Roster.get(i).addTime(0);
		}
		

		int studentCount = 0;
		BufferedReader csvReader = new BufferedReader(new FileReader(file));		// buffer read the file
		while ((row = csvReader.readLine()) != null) {		// read each line in the file
		    String[] data = row.split(",");		// separate each line by commas
		    
		    int count = 0;
		    while(count < Roster.size() - 1 && !data[0].equals(Roster.get(count).getAsurite())) {		// search for matching asurite
		    	//System.out.println(data[0] + "\t\t" + Roster.get(count).getAsurite());
		    	count++;
		    }

		    // if asurite matches, set new time
	    	if (data[0].equals(Roster.get(count).getAsurite())) {
	    		if (Roster.get(count).getTime() == 0) {
		    		studentCount++;
	    		}
	    		int newTime = Roster.get(count).getTime() + Integer.parseInt(data[1]);
	    		Roster.get(count).setTime(newTime);
	    	}
	    	else {
	    		UnknownStudent.add(data);
	    	}
		}

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Data loaded for " + studentCount + " users in the roster",SwingConstants.CENTER);
		JLabel newLabel1 = new JLabel(UnknownStudent.size() + " aditional attendee was found:",SwingConstants.CENTER);
        panel.add(label); 
		panel.add(newLabel1);
        panel.setSize(250, 250);
        //panel.setVisible(true);
        
		for (int i = 0; i < UnknownStudent.size(); i++) {
			//System.out.println(UnknownStudent.get(i)[0] + "\t" + UnknownStudent.get(i)[1]);
			JLabel newLabel2 = new JLabel(UnknownStudent.get(i)[0] + ", connected for " + UnknownStudent.get(i)[1],SwingConstants.CENTER);
			panel.add(newLabel2);
		}
        
		JDialog dialog = new JDialog(frame, "Add Attendance");
		dialog.setSize(250,250);
		dialog.setLocationRelativeTo(null);  
		dialog.add(panel);
		dialog.setVisible(true);
		
		setChanged(); 
        notifyObservers();
	}
	
	public ArrayList<Student> getRoster() {
		return Roster;
	}
	
	public String getDate() {
		return date;
	}
}


