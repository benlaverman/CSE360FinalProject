
import javax.swing.*;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;


public class World extends JFrame implements ActionListener{
    
	JMenu about = new JMenu("About");
	//JMenuItem aboutTeam = new JMenuItem("About Team");
	JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    
    JMenuItem loadRoster = new JMenuItem("Load a Roster");
    JMenuItem addAttendance = new JMenuItem("Add Attendance");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem plotData = new JMenuItem("Plot Data");
    
    public World() {
            
            
            //About Tab
            //about.add(aboutTeam);
            
            
            
            loadRoster.addActionListener(this); //Adding actionListener to Load Roster
            addAttendance.addActionListener(this); 
            save.addActionListener(this);
            plotData.addActionListener(this);
            //aboutTeam.addActionListener(this);
            about.addActionListener(this);
            
            file.add(loadRoster);
            file.add(addAttendance);
            file.add(save);
            file.add(plotData);
            
            menuBar.add(file);
            menuBar.add(about);
            
            setLayout(new GridLayout(4,1));
           
            setJMenuBar(menuBar);
              
        
    }
    /**
     * Creates a new World. Sets close operation on exit. Sets visible
     * @param args 
     */
    public static void main(String[] args) {
        World world = new World();
        world.setDefaultCloseOperation(EXIT_ON_CLOSE);
        world.setSize(1000,1000);
        world.setVisible(true);
    }
    
    /**
     * Calls source when run button is clicked
     */
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == loadRoster) {
    		JFileChooser chooser = new JFileChooser();
    	     
    	    int returnVal = chooser.showOpenDialog(null);
    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
    	       System.out.println("You chose to open this file: " +
    	       chooser.getSelectedFile().getName());
    	}
      
    }
    	if(e.getSource() == about) {
    		JOptionPane.showMessageDialog(about, "Ben Laverman");
    	}
   
    
  }
}