import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.table.*;

/**
 * CSE 360 Fall 2020 Final Project
 * @author Damian McGregor
 */
public class LoadRoster extends JFrame implements Observer{
	protected ArrayList<Student> Roster;
	protected ArrayList<String> Header;
	protected DefaultTableModel tableModel;
	protected JTable table;
	protected ArrayList<String> Dates;
	protected JFrame frame;
	protected JScrollPane ScrollPane;
	
	/**
	 * Default Constructor
	 */
	public LoadRoster() {
		Roster = new ArrayList<Student>();
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);

		// create Header for table
		Header = new ArrayList<String>();
		Header.add("ID");
		Header.add("First Name");
		Header.add("Last Name");
		Header.add("Program and Plan");
		Header.add("Academic Level");
		Header.add("ASURITE");
	}
	
	/**
	 * Add new Roster file
	 * @param file
	 * @param newFrame
	 * @throws IOException
	 */
	public void loadRoster(File file, JFrame newFrame) throws IOException {
		String row;
		frame = newFrame;
		
		BufferedReader csvReader = new BufferedReader(new FileReader(file));		// buffer read the file
		while ((row = csvReader.readLine()) != null) {		// read each line in the file
		    String[] data = row.split(",");		// separate each line by commas
		    Student newStudent = new Student(data[0], data[1], data[2], data[3], data[4], data[5]);		// create new Student
		    Roster.add(newStudent);		// add new Student to Roster
		}

		csvReader.close();		// close file reader
		
		displayTable();
	}
	
	/**
	 * Display the Roster as a JTable
	 */
	public void displayTable() {
		TableColumnModel columnModel = table.getColumnModel();
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setRowHeight(30);//set all row height 20
		table.setRowHeight(0, 40); //set the first row size 40
		
		// add Header to the table
		for (int i = 0; i < Header.size(); i++) {
			tableModel.addColumn(Header.get(i));
		}
		
		// set width of each new column in the table
		for (int i = 0; i < Header.size(); i++) {
			columnModel.getColumn(i).setPreferredWidth(125);
		}
		
		
		Object[] data = new Object[Header.size()];
		for (int i = 0; i < Roster.size(); i++) {
			
			// add constant header labels
			data[0] = Roster.get(i).getID();
			data[1] = Roster.get(i).getFirstName();
			data[2]	= Roster.get(i).getLastName();
			data[3] = Roster.get(i).getProgram();
			data[4] = Roster.get(i).getLevel();
			data[5] = Roster.get(i).getAsurite();
			
			// add dynamic header labels (each date in the Header list)
			for (int j = 6; j < Header.size(); j++) {
				data[j] = Roster.get(i).getTimeIndex(j-6);
			}
			
			tableModel.addRow(data);		// add the row to the table
		}

	    ScrollPane = new JScrollPane(table);		// update theScrollPane with the table
	}
	
	/**
	 * Get the Roster of students
	 * @return Roster
	 */
	public ArrayList<Student> getRoster() {
		return Roster;
	}
	
	/**
	 * Get the Header list
	 * @return Header
	 */
	public ArrayList<String> getHeader() {
		return Header;
	}
	
	/**
	 * Get the ScrollPane with the latest table
	 * @return ScrollPane
	 */
	public JScrollPane getScrollPane() {
		return ScrollPane;
	}
	
	/**
	 * Update the table when AddAttendance is changed
	 */
	public void update(Observable obj, Object arg) {
		Roster = ((AddAttendance)obj).getRoster();
		String date = ((AddAttendance)obj).getDate();
		
		Header.add(date);
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		displayTable();
	}
}
