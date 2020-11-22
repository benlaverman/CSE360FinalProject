import java.io.*;
import java.util.ArrayList;

public class AddAttendance {
	
	protected ArrayList AddAttendance(ArrayList<String[]> list, File file) throws IOException {
		ArrayList<String[]> newList = new ArrayList<String[]>();		// create ArrayList of String array
		String row;
		
		BufferedReader csvReader = new BufferedReader(new FileReader(file));		// buffer read the file
		while ((row = csvReader.readLine()) != null) {		// read each line in the file
		    String[] data = row.split(",");		// separate each line by commas 
		    newList.add(data);		// add separated data to LinedList
		}
		csvReader.close();		// close file reader
		
		/*
		 * Compare for matching data in file!
		 */
		
		// add all of the new data to the list
		for (int i = 0; i < newList.size() - 1; i++) {
			list.add(newList.get(i));
		}
		
		return list;
	}
}
