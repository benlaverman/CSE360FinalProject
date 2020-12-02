
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * This class will handle the main UI frame and it is where the
 *  main function at
 */
public class World extends JFrame implements ActionListener{
	protected JMenuItem loadRosterMenu, addAttendanceMenu, saveMenu, plotDataMenu, aboutMenu;
	protected ArrayList<Student> Roster;
	protected ArrayList<String> Header;
	protected World world;
	protected JFrame frame;
	protected static LoadRoster newRoster;
	protected static AddAttendance newAttendance;
	protected static SaveFile SaveFile;
	protected JScrollPane ScrollPane;
    
	
	protected String Date;
	protected JDatePickerImpl datePicker;
	
    public World() {
    		frame = new JFrame();
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            aboutMenu = new JMenuItem("About");
            loadRosterMenu = new JMenuItem("Load a Roster");
            addAttendanceMenu = new JMenuItem("Add Attendance");
            saveMenu = new JMenuItem("Save");
            plotDataMenu = new JMenuItem("Plot Data");
            
            
            /**
             * adding action listener for each function
             */
            loadRosterMenu.addActionListener(this); 
            addAttendanceMenu.addActionListener(this); 
            saveMenu.addActionListener(this);
            plotDataMenu.addActionListener(this);
            aboutMenu.addActionListener(this);
            
            /**
             * disable some of the JMenu first, because it will be enable 
             * after load roster operation is perform
             */
            addAttendanceMenu.setEnabled(false);
            saveMenu.setEnabled(false);
            plotDataMenu.setEnabled(false);
            
            menu.add(loadRosterMenu);
            menu.add(addAttendanceMenu);
            menu.add(saveMenu);
            menu.add(plotDataMenu);
            
            menuBar.add(menu);
            menuBar.add(aboutMenu);
            
            /**
             * set the size and layout of the frame
             */
            frame.setLayout(new GridLayout());
            frame.setJMenuBar(menuBar);
            frame.setSize(800,700);
            frame.setLocationRelativeTo(null);  
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
    	// add observer for the addAttendace since LoadRoster will observe addAttendance function
    	newAttendance.addObserver(newRoster); 
    }
    
    /**
     * perform operations when JMenu is clicked
     */
    public void actionPerformed(ActionEvent event) {
      

    if (event.getSource() == loadRosterMenu) 
    {
    	
    	/**
    	 * open the file choose dialog and let users choose a file
    	 */
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
        	System.out.println("You chose to open this file: " +
        			chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
  	
        /**
         * check did the users have selected a file or not
         */
        if(newFile != null) 
        {
		    try {
		    	
		    	//load the data from csv file to the data structure
				newRoster.loadRoster(newFile, frame);
				Roster = newRoster.getRoster();
				
				// get the scroll pane with the table inside
				ScrollPane = newRoster.getScrollPane();
				//add it to the frame
				frame.add(ScrollPane);
				frame.setVisible(true);
				
				// when the roster have been add, then addAttendace will be enable
				addAttendanceMenu.setEnabled(true);
				
			} 
		    catch (IOException e)
		    {
				System.out.println("Could not load file.");
			}
        }
    }
    else if (event.getSource() == addAttendanceMenu) 
    {
   		/**
   		 * initialize the date picker pop up dialog 
   		 */
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setSize(300,300);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		JDialog dialog = new JDialog(frame, "Choose Date");
		
		// when the date have been submit, the operation will be execute here
		JButton submit = new JButton( new AbstractAction("submit") { 
	        @Override
	        public void actionPerformed( ActionEvent event) {
	        	Date = datePicker.getJFormattedTextField().getText();
	        	
	        	//check did the users choose a date before submit
	        	// if not, show warning message
	        	if(Date.equals("")) 
	        	{
	        		
	        		JOptionPane.showMessageDialog(frame,
	        			    "Please select a date",
	        			    "No date warning",
	        			    JOptionPane.WARNING_MESSAGE);
	        	}
	        	else
	        	{
	        		//continue perform add attendance operations after we make sure date have been pick
		    		System.out.print(Date);
		    		dialog.dispose();
		    		
		    		//choose the csv file for add attendance
		        	JFileChooser chooser = new JFileChooser();
		            
		            File newFile;
		            int returnVal = chooser.showOpenDialog(null);
		            
		            if(returnVal == JFileChooser.APPROVE_OPTION) 
		            {
		            	System.out.println("You chose to open this file: " +
		            	chooser.getSelectedFile().getName());
		            }

		            newFile = chooser.getSelectedFile();
		            
		            // check is the file empty or not
		            if(newFile != null)
		            {
		            	
		    		    try
		    		    {
		    		    	
		    		    	// add the attendance to the roster
		    				newAttendance.addAttendance(newFile, Roster, frame,Date);
		    				
		    				//get the new update roster
		    				Roster = newRoster.getRoster();
		    				
		    				// remove the previous scroll pane that already inside the frame
		    				frame.remove(ScrollPane);
		    				
		    				// get the newly update one with attendace inside
		    				ScrollPane = newRoster.getScrollPane();
		    				frame.repaint();
		    				
		    				// add the newly update roster to the frame
		    				frame.add(ScrollPane);
	
		    				frame.setVisible(true);
		    				
		    				// enables the JMenu for save and plot data after attendance have been add
		    				saveMenu.setEnabled(true);
		    				plotDataMenu.setEnabled(true);
		    				
		    			}
		    		    catch (IOException e) 
		    		    {
		    				System.out.println("Could not add file.");
		    			}
		    	    
		            }		
	        	}
	               
	        }
	    });
		
	    submit.setVerticalTextPosition(AbstractButton.CENTER);
		panel.add(datePicker);
		panel.add(submit);
		panel.setVisible(true);
		
		dialog.setSize(300,300);
		dialog.setLocationRelativeTo(null);  
		dialog.add(panel);
		dialog.setVisible(true);
		
        
        
    }
    else if (event.getSource() == saveMenu)
    {
    	
		//choose the file to write on the CSV 
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
        	System.out.println("You chose to open this file: " +
        	chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();

        /**
         * get the roster and header data structure that need for saving the file
         */
    	Roster = newRoster.getRoster();
		Header = newRoster.getHeader();
    	
		// save all the data in the table to a csv file
		SaveFile = new SaveFile(Roster, Header, newFile);
    
        
    }
    else if (event.getSource() == plotDataMenu)
    {	
    	SwingUtilities.invokeLater(() -> {  
    		
    		// get the roster and header data structure for plot data
    		Roster = newRoster.getRoster();
    		Header = newRoster.getHeader();
    		
    		/**
    		 * the chart is an independent window,
    		 * it process the data by calling the method and deploy a independent
    		 * frame to display the chart
    		 */
        	PlotData plotData = new PlotData("plot example title", Roster, Header);  
          plotData.setSize(800, 400);  
          plotData.setLocationRelativeTo(null);  
          plotData.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
          plotData.setVisible(true);  
        });
        
    }
    else if(event.getSource()== aboutMenu)
    { 
    	/**
    	 * the program will pop up a frame to show the member information
    	 */
        System.out.println("about");
        JDialog about = new JDialog(frame, "Attendance Info");
        about.setTitle("About Group");
        JLabel about1 = new JLabel("<html>Zengkeat Giam - Worked on Plot, Add Attendance, "
        		+ "and Load Roster<br/>Damian McGregor - Worked on Add Attendance, "
        		+ "and Load Roster<br/>Frank Armijo - Worked on the GUI<br/>Benjamin Laverman "
        		+ "- Worked on Documentaion<br/>Abid Hossain - Worked on Save Function</html>", 
        		SwingConstants.CENTER);
        about.add(about1);
        about.setSize(500, 400);
        about.setLocationRelativeTo(null);  
        about.setVisible(true);
     }
  }
    
    
     
    
}