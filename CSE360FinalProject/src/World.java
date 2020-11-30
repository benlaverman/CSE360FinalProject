
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class World extends JFrame implements ActionListener{
	protected JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
	protected ArrayList<Student> Roster;
	protected ArrayList<String> Header;
	protected World world;
	protected JFrame frame;
	protected static LoadRoster newRoster;
	protected static AddAttendance newAttendance;
	protected JScrollPane ScrollPane;
    
    public World() {
    		frame = new JFrame();
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuItem5 = new JMenuItem("About");
            menuItem1 = new JMenuItem("Load a Roster");
            menuItem2 = new JMenuItem("Add Attendance");
            menuItem3 = new JMenuItem("Save");
            menuItem4 = new JMenuItem("Plot Data");
            
            
            menuItem1.addActionListener(this); //Adding actionListener to Load Roster
            menuItem2.addActionListener(this); 
            menuItem3.addActionListener(this);
            menuItem4.addActionListener(this);
            menuItem5.addActionListener(this);
            
            menu.add(menuItem1);
            menu.add(menuItem2);
            menu.add(menuItem3);
            menu.add(menuItem4);
            
            menuBar.add(menu);
            menuBar.add(menuItem5);
            
            frame.setLayout(new GridLayout());
           
            frame.setJMenuBar(menuBar);
            frame.setSize(1000,1000);
            frame.setVisible(true);
              
        
    }
    /**
     * Creates a new World. Sets close operation on exit. Sets visible
     * @param args 
     */
    public static void main(String[] args) {
        World world = new World();
        world.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	newRoster = new LoadRoster();
    	newAttendance = new AddAttendance();
    	newAttendance.addObserver(newRoster);
        //world.setSize(1000,1000);
        //world.setVisible(true);
    }
    
    /**
     * Calls source when run button is clicked
     */
    public void actionPerformed(ActionEvent event) {
      

    if (event.getSource() == menuItem1) 
    {
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("You chose to open this file: " +
        			chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
  	
        if(newFile != null) {
		    try {
				newRoster.loadRoster(newFile, frame);
				Roster = newRoster.getRoster();
				
				ScrollPane = newRoster.getScrollPane();
				frame.add(ScrollPane);
				frame.setVisible(true);
				
			} catch (IOException e) {
				System.out.println("Could not load file.");
			}
        }
    }
    else if (event.getSource() == menuItem2) {
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("You chose to open this file: " +
        	chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
        
        if(newFile != null) {
        	
		    try {
	
				newAttendance.addAttendance(newFile, Roster, frame);
				Roster = newRoster.getRoster();
				
				frame.remove(ScrollPane);
				ScrollPane = newRoster.getScrollPane();
				//frame.removeAll();
				frame.repaint();
				frame.add(ScrollPane);
				//frame.revalidate();
				frame.setVisible(true);
				
			} catch (IOException e) {
				System.out.println("Could not add file.");
			}
	    
        }
        
        
    } else if (event.getSource() == menuItem3) {
    	
		//choose the file to write on the CSV 
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("You chose to open this file: " +
        	chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
        System.out.println(newFile);
    	
    	Roster = newRoster.getRoster();
		Header = newRoster.getHeader();
    	
    	FileWriter csvWriter;
    	int k = Header.size() -1;
    	int t = Roster.size() -1;
    	
		try {
			csvWriter = new FileWriter(newFile);
			
			// to load the header into the csv file
			for(int i =0; i < Header.size(); i++) {
				
				if(i == k ) {
					csvWriter.append(Header.get(i));
					csvWriter.append("\n");
				}else {
					csvWriter.append(Header.get(i));
					csvWriter.append(",");
				}
				
			}
			
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
				
				for(int num =0; num < Header.size() - 6; num++) {
					
					csvWriter.append(Integer.toString(Roster.get(j).getTimeIndex(num))); //convert the int to string
					csvWriter.append(",");
				}
				
				csvWriter.append("\n");			
				
			}

	    	csvWriter.flush();
	    	csvWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	
    	
    	
        
    }
    else if (event.getSource() == menuItem4) {
		//frame.remove(ScrollPane);
		//PlotData plot = new PlotData();
    	
    	
    	SwingUtilities.invokeLater(() -> {  
    		
    		Roster = newRoster.getRoster();
    		Header = newRoster.getHeader();
    		
        	PlotData example = new PlotData("plot example title", Roster, Header);  
          example.setSize(800, 400);  
          example.setLocationRelativeTo(null);  
          example.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
          example.setVisible(true);  
        });
        
    }else if(event.getSource()== menuItem5){ //About
        System.out.println("about");
        JDialog about = new JDialog();
        about.setTitle("About Group");
        JLabel about1 = new JLabel("<html>Zengkeat Giam - Worked on Plot, Add Attendance, and Load Roster<br/>Damian McGregor - Worked on Add Attendance, and Load Roster<br/>Frank Armijo - Worked on the GUI<br/>Benjamin Laverman - Worked on Documentaion<br/>Abid Hossain - Worked on Save Function</html>", SwingConstants.CENTER);
        about.add(about1);
        about.setSize(500, 500);
        about.setVisible(true);
        }
  }
}