
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


public class World extends JFrame implements ActionListener{
	protected JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	protected ArrayList<Student> Roster;
	protected World world;
	protected JFrame frame;
	protected static LoadRoster newRoster;
	protected static AddAttendance newAttendance;
	protected JScrollPane ScrollPane;
    
    public World() {
    		frame = new JFrame();
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            JMenu menu1 = new JMenu("About");
            menuItem1 = new JMenuItem("Load a Roster");
            menuItem2 = new JMenuItem("Add Attendance");
            menuItem3 = new JMenuItem("Save");
            menuItem4 = new JMenuItem("Plot Data");
            
            menuItem1.addActionListener(this); //Adding actionListener to Load Roster
            menuItem2.addActionListener(this); 
            menuItem3.addActionListener(this);
            menuItem4.addActionListener(this);
            
            menu.add(menuItem1);
            menu.add(menuItem2);
            menu.add(menuItem3);
            menu.add(menuItem4);
            
            menuBar.add(menu);
            menuBar.add(menu1);
            
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
      

    if (event.getSource() == menuItem1) {
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("You chose to open this file: " +
        			chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
  	
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
    else if (event.getSource() == menuItem2) {
    	JFileChooser chooser = new JFileChooser();
        
        File newFile;
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("You chose to open this file: " +
        	chooser.getSelectedFile().getName());
        }

        newFile = chooser.getSelectedFile();
        
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
    else if (event.getSource() == menuItem4) {
		//frame.remove(ScrollPane);
		//PlotData plot = new PlotData();
    	
    	
    	SwingUtilities.invokeLater(() -> {  
        	PlotData example = new PlotData();  
          example.setSize(800, 400);  
          example.setLocationRelativeTo(null);  
          example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
          example.setVisible(true);  
        });
        
    }
  }
}