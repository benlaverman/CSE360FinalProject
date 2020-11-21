
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class World extends JFrame implements ActionListener{
    
    public World() {
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            JMenu menu1 = new JMenu("About");
            JMenuItem menuItem = new JMenuItem("Load a Roster");
            JMenuItem menuItem2 = new JMenuItem("Add Attendance");
            JMenuItem menuItem3 = new JMenuItem("Save");
            JMenuItem menuItem4 = new JMenuItem("Plot Data");
            
            menuItem.addActionListener(this); //Adding actionListener to Load Roster
            menuItem2.addActionListener(this); 
            menuItem3.addActionListener(this);
            menuItem4.addActionListener(this);
            
            menu.add(menuItem);
            menu.add(menuItem2);
            menu.add(menuItem3);
            menu.add(menuItem4);
            
            menuBar.add(menu);
            menuBar.add(menu1);
            
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
      JFileChooser chooser = new JFileChooser();
     
    int returnVal = chooser.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
       System.out.println("You chose to open this file: " +
       chooser.getSelectedFile().getName());
    }
  }
}