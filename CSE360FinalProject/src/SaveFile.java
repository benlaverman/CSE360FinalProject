import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 *this class save the data into a CSV file
 */
public class SaveFile extends JFrame {
	
	protected JFrame frame;
	
	/**
	 * 
	 * @param Roster
	 * @param Header
	 * @param newFile
	 * 
	 * this constructor will get the roster and Header data structure and the file where the data will be write,
	 * and write it to a csv file that look exactly in the table
	 */
	public SaveFile( ArrayList<Student> Roster,ArrayList<String>  Header, File newFile) {
		
		FileWriter csvWriter;
		
    	
        if(newFile != null) 
        {
        	try {
    			csvWriter = new FileWriter(newFile);
    			
    			// load the header into the csv file first
    			for(int i =0; i < Header.size(); i++) {
    				
					csvWriter.append(Header.get(i));
					csvWriter.append(",");
    				
    			}
    			
    			csvWriter.append("\n");
    			
    			//load the data in the roster into csv file
    			for(int j=0; j < Roster.size(); j++) {
    				
    				csvWriter.append(Roster.get(j).getID());
    				csvWriter.append(",");
    				csvWriter.append(Roster.get(j).getFirstName());
    				csvWriter.append(",");
    				csvWriter.append(Roster.get(j).getLastName());
    				csvWriter.append(",");
    				csvWriter.append(Roster.get(j).getProgram());
    				csvWriter.append(",");
    				csvWriter.append(Roster.get(j).getLevel());
    				csvWriter.append(",");
    				csvWriter.append(Roster.get(j).getAsurite());
    				csvWriter.append(",");
    				
    				//load the attendance time into the same row 
    				for(int num =0; num < Header.size() - 6; num++) {
    					
    					csvWriter.append(Integer.toString(Roster.get(j).getTimeIndex(num))); //convert the int to string
    					csvWriter.append(",");
    				}
    				
    				csvWriter.append("\n");			
    				
    			}

    	    	csvWriter.flush();
    	    	csvWriter.close();
    	    	
    	    	JOptionPane.showMessageDialog(frame, " Sucessfully write into CSV File!!");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
	}
}
