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
  

public class PlotData extends LoadRoster{  

	//JFrame newFrame = new JFrame();
	  
	public PlotData()
	{  
	    //super(title);  
	  
	    // Create dataset  
		XYDataset dataset = createDataset();//call the method and return the data  
		  
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
	    /*
	    JDialog dialog = new JDialog(frame, "Plot Data");
	    dialog.setSize(800, 400);
	    dialog.add(panel);
	    dialog.setVisible(true);
	    */
	    setContentPane(panel);
		//newFrame.repaint();
	    
	  }  
	  
  
	
  private XYDataset createDataset() {  
	  
    XYSeriesCollection dataset = new XYSeriesCollection();   
    
    
    int numberOfDateAdded = Header.size() - 6;
    
    for(int i = 0; i < numberOfDateAdded; i ++) {		 
		  
		  int percentageArray[] = {0,0,0,0,0,0,0,0,0,0};//store the percentage
		  
		  XYSeries series1 = new XYSeries(Header.get(i+5)); //date of the attendance
		  

	      for(int j =0; j < Roster.size(); j ++){
			  
	    	  int minute = Roster.get(j).getTimeIndex(i); //get the student time array 
			  
			  int percentage = ((minute*100)/75);
			  
			  //process the percentage of minute

			  if(percentage>= 0 && percentage <=10) {
				  
				  percentageArray[0] = percentageArray[0]+1; //plus 1 into each array if the percentage is valid
				  
			  }else if(percentage>= 11 && percentage <=20){
				  
				  percentageArray[1] =percentageArray[1]+1;

			  }else if(percentage>= 21 && percentage <=30){
				  
				  percentageArray[2] =percentageArray[2]+1;
				  
			  }else if(percentage>= 31 && percentage <=40){
				  
				  percentageArray[3] =percentageArray[3]+1;
				  
			  }else if(percentage>= 41 && percentage <=50){
				  
				  percentageArray[4] =percentageArray[4]+1;
				  
			  }else if(percentage>= 51 && percentage <=60){
				  
				  percentageArray[5] =percentageArray[5]+1;
				  
			  }else if(percentage>= 61 && percentage <=70){
				  
				  percentageArray[6] =percentageArray[6]+1;
				  
			  }else if(percentage>= 71 && percentage <=80){
				  
				  percentageArray[7] =percentageArray[7]+1;
				  
			  }else if(percentage>= 81 && percentage <=90){
				  
				  percentageArray[8] =percentageArray[8]+1;
				  
			  }else if(percentage>= 91 && percentage <=100){
				  
				  percentageArray[9] =percentageArray[9]+1;
				  
			  }
		  } 
		  
		  /**
		   * loop the content of the array and plot
		   */
		  for(int k=0; k < 10; k++) {
			  
			  series1.add(k+1, percentageArray[k]);//.add(x,y)
		  }
		  
		  dataset.addSeries(series1); 
	  }
    
    return dataset;  
  }  
  
  /*
  public static void main(String[] args) {  
	    
    SwingUtilities.invokeLater(() -> {  
    	PlotData example = new PlotData("Scatter Chart Example");  
      example.setSize(800, 400);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  } 
  */
}  