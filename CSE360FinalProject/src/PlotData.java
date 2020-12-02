import java.awt.Color;  
import java.util.*;

import javax.swing.*;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
  
/**
 * 
 * @author zengkeatgiam
 * this class handle all the functionality of plot data
 *
 */
public class PlotData extends JFrame{  
	  
	/**
	 * 
	 * @param title
	 * @param Roster
	 * @param Header
	 * this method will take the data structure that store the data and perform some operation to plot it
	 */
	public PlotData(String title,ArrayList<Student> Roster, ArrayList<String> Header)
	{  
	    super(title);  
	  
	    // Create dataset  
	    //call the method and return the data set for plotting
		XYDataset dataset = createDataset(Roster, Header);
		  
		    // Create chart  
		JFreeChart chart = ChartFactory.createScatterPlot(  
		    "Attendance Plot by date",   
		    "Percentage", "Count", dataset, PlotOrientation.VERTICAL ,
		     true , true , false);  
		  
		      
		    //Changes background color  
		XYPlot plot = (XYPlot)chart.getPlot();  
		plot.setBackgroundPaint(new Color(255,228,196));  
		  
		 
		// Create the chart Panel  
	    ChartPanel panel = new ChartPanel(chart);  
	    setContentPane(panel);
	    
	  }  
	  
  
	
	/**
	 * 
	 * @param Roster
	 * @param Header
	 * @return
	 * this method return the dataset after process the data inside the data structure
	 */
  private XYDataset createDataset(ArrayList<Student> Roster,ArrayList<String> Header ) {  
	  
    XYSeriesCollection dataset = new XYSeriesCollection();   
    
    /**
     * subtract the original 6 field of the roster we know how many attendance file have been add
     */
    int numberOfDateAdded = Header.size() - 6; 
    
    /**
     * loop by the total number of attendance file that have been add
     */
    for(int i = 0; i < numberOfDateAdded; i ++) {		 
		  
		  int percentageArray[] = {0,0,0,0,0,0,0,0,0,0,0};//store the number of percentage
		  
		  XYSeries series1 = new XYSeries(Header.get(i+6)); //display the date of the attendance
		  
		  /**
		   * loop through each student and get their attendance time
		   */
	      for(int j =0; j < Roster.size(); j ++){
			  
	    	  int minute = Roster.get(j).getTimeIndex(i); //get the student attendance time by index 
	    	  
	    	  System.out.println("this is the minute of each student"+ j + " " + minute);
			  
	    	  //process the attendance time by percentage
			  int percentage = ((minute*100)/75);
			  
			/**
			 * if a student attendance percentage match the condition,
			 * then +1 into an array index. 
			 * E.x: 0 to 9 percentage will add 1 into index 0,
			 * 10 to 19 percent will add 1 into index 1 etc.
			 */
			  if(percentage>= 0 && percentage <=9) {
				  
				  percentageArray[0] = percentageArray[0]+1; 
				  
			  }else if(percentage>= 10 && percentage <=19){
				  
				  percentageArray[1] =percentageArray[1]+1;

			  }else if(percentage>= 20 && percentage <=29){
				  
				  percentageArray[2] =percentageArray[2]+1;
				  
			  }else if(percentage>= 30 && percentage <=39){
				  
				  percentageArray[3] =percentageArray[3]+1;
				  
			  }else if(percentage>= 40 && percentage <=49){
				  
				  percentageArray[4] =percentageArray[4]+1;
				  
			  }else if(percentage>= 50 && percentage <=59){
				  
				  percentageArray[5] =percentageArray[5]+1;
				  
			  }else if(percentage>= 60 && percentage <=69){
				  
				  percentageArray[6] =percentageArray[6]+1;
				  
			  }else if(percentage>= 70 && percentage <=79){
				  
				  percentageArray[7] =percentageArray[7]+1;
				  
			  }else if(percentage>= 80 && percentage <=89){
				  
				  percentageArray[8] =percentageArray[8]+1;
				  
			  }else if(percentage>= 90 && percentage <=99){
				  
				  percentageArray[9] =percentageArray[9]+1;
				  
			  }else if(percentage>= 100){
				  
				  percentageArray[10] =percentageArray[10]+1;
				  
			  }
		  } 
		  
		  /**
		   * loop the content of the array and plot
		   */
		  for(int k=0; k < 11; k++) {
			  
			  series1.add(k, percentageArray[k]);//.add(x,y)
		  }
		  //add to the data set
		  dataset.addSeries(series1); 
	  }
    
    return dataset;  
  }  
  
}  