

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class AddAttendance extends Observable {
	protected ArrayList<Student> Roster;
	
	public AddAttendance()  {
		Roster = new ArrayList<Student>();
	}
	
	public void addAttendance(File file, ArrayList<Student> newRoster) throws IOException {
		/* Get date from user*/
		Roster = newRoster;
		
		String row;
		
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
	    		//System.out.print("TEST");
	    		int newTime = Roster.get(count).getTime() + Integer.parseInt(data[1]);
	    		Roster.get(count).setTime(newTime);
	    	}
		}
		
		setChanged(); 
        notifyObservers();
	}
	
	public ArrayList<Student> getRoster() {
		return Roster;
	}
}
