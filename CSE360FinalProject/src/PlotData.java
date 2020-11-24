import java.awt.Color;  
import java.util.*;
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
  
public class PlotData extends JFrame {  
//  private static final long serialVersionUID = 6294689542092367723L;  
  
  public PlotData(String title) {  
    super(title);  
  
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
      
     
    // Create Panel  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private XYDataset createDataset() {  
    XYSeriesCollection dataset = new XYSeriesCollection();   
    
    String percentageArray[] = {"Nov 2", "Nov 5"};
    
    ArrayList<Integer>[] arrAttendance = new ArrayList[2];// 5 row of array
    
    // initializing 
    for (int i = 0; i < 2; i++) { 
    	arrAttendance[i] = new ArrayList<Integer>(); 
    } 
    
    arrAttendance[0].add(5); 
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);
    arrAttendance[0].add(5);    
    arrAttendance[0].add(5);
    arrAttendance[0].add(10);
    arrAttendance[0].add(10);
    arrAttendance[0].add(15); 
    arrAttendance[0].add(15); 
    arrAttendance[0].add(15); 
    arrAttendance[0].add(15); 
    arrAttendance[0].add(20); 
    arrAttendance[0].add(20);
    arrAttendance[0].add(20);
    arrAttendance[0].add(20);
    arrAttendance[0].add(20);
    arrAttendance[0].add(25); 
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(25);
    arrAttendance[0].add(30); 
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(30);
    arrAttendance[0].add(35); 
    arrAttendance[0].add(40); 
    arrAttendance[0].add(45); 
    arrAttendance[0].add(50); 
    arrAttendance[0].add(55); 
    arrAttendance[0].add(60); 
    arrAttendance[0].add(65); 
    arrAttendance[0].add(70); 
    arrAttendance[0].add(75); 
    
    arrAttendance[1].add(5); 
    arrAttendance[1].add(10);
    arrAttendance[1].add(15); 
    arrAttendance[1].add(20); 
    arrAttendance[1].add(25); 
    arrAttendance[1].add(30); 
    arrAttendance[1].add(35); 
    arrAttendance[1].add(40); 
    arrAttendance[1].add(45); 
    arrAttendance[1].add(50); 
    arrAttendance[1].add(55); 
    arrAttendance[1].add(60); 
    arrAttendance[1].add(65); 
    arrAttendance[1].add(70); 
    arrAttendance[1].add(75); 

    
    
	  for (int i = 0; i < 2; i++) { 
		  
		  int array[] = {0,0,0,0,0,0,0,0,0,0};
		  XYSeries series1 = new XYSeries(percentageArray[i]); 
		  
		  for (int j = 0; j < arrAttendance[i].size(); j++) {// size of the each row
			  
			  int minute = arrAttendance[i].get(j); // get the i-th index of the row 
			  
			  int percentage = ((minute*100)/75);
			  
			  System.out.print(percentage + " "); 
			  if(percentage>= 0 && percentage <=10) {
				  
				  array[0] = array[0]+1;
				  
			  }else if(percentage>= 11 && percentage <=20){
				  
				  array[1] =array[1]+1;

			  }else if(percentage>= 21 && percentage <=30){
				  
				  array[2] =array[2]+1;
				  
			  }else if(percentage>= 31 && percentage <=40){
				  
				  array[3] =array[3]+1;
				  
			  }else if(percentage>= 41 && percentage <=50){
				  
				  array[4] =array[4]+1;
				  
			  }else if(percentage>= 51 && percentage <=60){
				  
				  array[5] =array[5]+1;
				  
			  }else if(percentage>= 61 && percentage <=70){
				  
				  array[6] =array[6]+1;
				  
			  }else if(percentage>= 71 && percentage <=80){
				  
				  array[7] =array[7]+1;
				  
			  }else if(percentage>= 81 && percentage <=90){
				  
				  array[8] =array[8]+1;
				  
			  }else if(percentage>= 91 && percentage <=100){
				  
				  array[9] =array[9]+1;
				  
			  }
		  } 
		  
		  /**
		   * loop the content of the array and plot
		   */
		  for(int k=0; k < 10; k++) {
			  
			  series1.add(k+1, array[k]);
//			  System.out.print(array[k] + " "); 
		  }
		  dataset.addSeries(series1); 
	  }
    
    return dataset;  
  }  
  
  public static void main(String[] args) {  
	    
    SwingUtilities.invokeLater(() -> {  
    	PlotData example = new PlotData("Scatter Chart Example");  
      example.setSize(800, 400);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  } 
  
}  