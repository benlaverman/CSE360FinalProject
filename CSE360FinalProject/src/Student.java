

import java.util.ArrayList;

public class Student {
	protected String id;
	protected String firstName;
	protected String lastName;
	protected String program;
	protected String level;
	protected String asurite;
	protected ArrayList<Integer> time;
	
	// Default constructor
	public Student() {
		id = "";
		firstName = "";
		lastName = "";
		program = "";
		level = "";
		asurite = "";
		time = new ArrayList<Integer>();
		//time.add(0);
	}
	
	// Custom constructor
	public Student(String setID, String setFirstName, String setLastName, String setProgram, String setLevel, String setAsurite) {
		id = setID;
		firstName = setFirstName;
		lastName = setLastName;
		program = setProgram;
		level = setLevel;
		asurite = setAsurite;
		time = new ArrayList<Integer>();
		//time.add(0);
	}
	
	// Get functions
	public String getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getProgram() {
		return program;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getAsurite() {
		return asurite;
	}
	
	public int getTime() {
		return time.get(time.size() - 1);
	}
	
	public int getTimeIndex(int index) {
		return time.get(index);
	}
	
	public int getTimeSize() {
		return time.size();
	}
	
	//Set functions
	public void setID(String newID) {
		id = newID;
	}
	
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	
	public void setProgram(String newProgram) {
		program = newProgram;
	}
	
	public void setLevel(String newLevel) {
		level = newLevel;
	}
	
	public void setAsurite(String newAsurite) {
		asurite = newAsurite;
	}
	
	public void setTime(int newTime) {
		time.set(time.size() - 1, newTime);
	}
	
	public void addTime(int newTime) {
		time.add(newTime);
	}
	
	public String toString() {
		String output = id + "\t\t" + firstName + "\t\t" + lastName + "\t\t" + program + "\t\t" + level + "\t\t" + asurite;
		for (int i = 0; i < time.size(); i++) {
			output += "\t\t" + time.get(i);
		}
		return output;
	}
}
