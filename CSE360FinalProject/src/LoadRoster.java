

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.*;

public class LoadRoster extends JFrame implements Observer{
	protected ArrayList<Student> Roster;		// create ArrayList of Students
	protected ArrayList<String> Header;
	protected DefaultTableModel tableModel;
	protected JTable table;
	protected ArrayList<String> Dates;
	protected JFrame frame;
	protected JScrollPane ScrollPane;
	
	public LoadRoster() {
		Roster = new ArrayList<Student>();

		String tableHeader[] = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);

		Header = new ArrayList<String>();
		Header.add("ID");
		Header.add("First Name");
		Header.add("Last Name");
		Header.add("Program and Plan");
		Header.add("Academic Level");
		Header.add("ASURITE");
	}
	
	public void loadRoster(File file, JFrame newFrame) throws IOException {
		String row;
		frame = newFrame;
		
		BufferedReader csvReader = new BufferedReader(new FileReader(file));		// buffer read the file
		while ((row = csvReader.readLine()) != null) {		// read each line in the file
		    String[] data = row.split(",");		// separate each line by commas
		    Student newStudent = new Student(data[0], data[1], data[2], data[3], data[4], data[5]);
		    Roster.add(newStudent);
		}

		csvReader.close();		// close file reader
		
		/*
		frame = new JFrame();
	    jt.setBounds(30,40,200,300);
	    frame.setSize(300,400);    
	    frame.setVisible(true);
	    */
		
		displayTable();
	}
	
	public void displayTable() {
		
		TableColumnModel columnModel = table.getColumnModel();
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setRowHeight(30);//set all row height 20
		table.setRowHeight(0, 40); //set the first row size 40
		
		for (int i = 0; i < Header.size(); i++) {
			tableModel.addColumn(Header.get(i));
		}
		
		for (int i = 0; i < Header.size(); i++) {
			columnModel.getColumn(i).setPreferredWidth(125);
		}
		
//		System.out.println(Header.size());
		Object[] data = new Object[Header.size()];
		for (int i = 0; i < Roster.size(); i++) {
			data[0] = Roster.get(i).getID();
			data[1] = Roster.get(i).getFirstName();
			data[2]	= Roster.get(i).getLastName();
			data[3] = Roster.get(i).getProgram();
			data[4] = Roster.get(i).getLevel();
			data[5] = Roster.get(i).getAsurite();
			
			for (int j = 6; j < Header.size(); j++) {
//				for (int k = 0; k < Header.size() - 6; k++) {
					data[j] = Roster.get(i).getTimeIndex(j-6);
//				}
			}
			
			tableModel.addRow(data);
		}


	    ScrollPane = new JScrollPane(table);
	    //frame.add(ScrollPane);
	    //frame.setVisible(true);
	    
	}
	
	public ArrayList<Student> getRoster() {
		return Roster;
	}
	
	public ArrayList<String> getHeader() {
		return Header;
	}
	
	public JScrollPane getScrollPane() {
		return ScrollPane;
	}
	
	public void update(Observable obj, Object arg) {
		Roster = ((AddAttendance)obj).getRoster();
		String date = ((AddAttendance)obj).getDate();
		
		Header.add(date);
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		displayTable();
		
		/*
		for (int i = 0; i < Roster.size(); i++) {
			System.out.println(Roster.get(i));
		}
		*/
	}
}
