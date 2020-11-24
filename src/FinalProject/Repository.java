package FinalProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Repository extends Observable {
	public static final String delimiter = ",";
	   public static List<Student> read(String csvFile) {
		   List<Student> studentList = new ArrayList();	   
		   
		   try {
	         File file = new File(csvFile);
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr);
	         String line = "";
	         String[] tempArr;
	         while((line = br.readLine()) != null) {
	            tempArr = line.split(delimiter);
	            for(String tempStr : tempArr) {
	               System.out.print(tempStr + " ");
	            }
	            System.out.println();
	            studentList.add(createStudent(tempArr));
	         }
	         br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	         }
		   return studentList;
	   } 
	 
		public static void write(String csvFile, List<Student> list2 ) { 
			try { 
	   FileWriter csvWriter = new FileWriter("new.csv");
		for(int i =0;i < list2.size();i++) {
			csvWriter.append(list2.get(i).getASURITE());
			csvWriter.append(",");
			csvWriter.append(list2.get(i).getfirstName());
			csvWriter.append(",");
			csvWriter.append(list2.get(i).getlastName());
			csvWriter.append(",");
			csvWriter.append(list2.get(i).getprogram());
			csvWriter.append(",");
			csvWriter.append(list2.get(i).getlevel());
			csvWriter.append(",");
			csvWriter.append(list2.get(i).getASURITE());
			csvWriter.append("/n");
					
			list2.get(i).getASURITE();
			list2.get(i).getfirstName();
			list2.get(i).getlastName();
			list2.get(i).getprogram();
			list2.get(i).getlevel();
			list2.get(i).getASURITE();
			
			csvWriter.flush();
			csvWriter.close();			
		}
	
		}
		catch(IOException ioe) {
            ioe.printStackTrace();
         }
			
		}
	  
	public static void main(String[]  args) {
		final JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int Value = jfc.showOpenDialog(null);
		List<Student> students = new ArrayList();
		
		if(Value == JFileChooser.APPROVE_OPTION) {
			File csvInput = jfc.getSelectedFile();
			students = Repository.read(csvInput.toString());			
			for(Student st:students) {
				System.out.println("ID: " + st.getID() + "First Name: " + st.getfirstName() + "Last Name: " + st.getlastName() 
				+"Program: " + st.getprogram()	+ " level: " +st.getlevel() + "ASURITE: " +st.getASURITE());
			}
		}
	}
	
	public static Student createStudent(String[] attributes) {
		int ID = Integer.parseInt(attributes[0]);
		String firstName = attributes[1];
		String lastName = attributes[2];
		String program = attributes[3];
		String level = attributes[4];
		String ASURITE = attributes[5];
		return new Student(ID,firstName,lastName,program,level,ASURITE);
	}
	
}

	class Student{
		private int ID;
		private String firstName;
		private String lastName;
		private String program;
		private String level;
		private String ASURITE;
		
		public Student(int ID, String firstName, String lastName,String program, String Level,String ASURITE) {
			this.ID=ID;
			this.firstName=firstName;
			this.lastName=lastName;
			this.program=program;
			this.level=level;
			this.ASURITE=ASURITE;
		}
		
		public int getID() {
			return this.ID;
		}
		public void setID(int ID) {
			this.ID=ID;
		}
		
		public String getfirstName() {
			return this.firstName;
		}
		public void setFirstName(String firstname) {
			this.firstName=firstName;
		}
		public String getlastName() {
			return this.lastName;
		}
		public void setlastName(String lastName) {
			this.lastName=lastName;
		}
		public String getprogram() {
			return this.program;
		}
		public void setprogram(String program) {
			this.program=program;
		}
		public String getlevel() {
			return this.level;
		}
		public void setlevel() {
			this.level=level;
		}
		public String getASURITE() {
			return this.ASURITE;
		}
		public void setASURITE() {
			this.ASURITE=ASURITE;
		}
	
	}
	
	
	
	
	
	
