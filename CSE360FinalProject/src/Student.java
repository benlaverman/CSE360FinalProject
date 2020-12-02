import java.util.ArrayList;

/**
 * CSE 360 Fall 2020 Final Project
 * @author Damian McGregor
 */
public class Student {
	protected String id;
	protected String firstName;
	protected String lastName;
	protected String program;
	protected String level;
	protected String asurite;
	protected ArrayList<Integer> time;
	
	/**
	 * Default Constructor
	 */
	public Student() {
		id = "";
		firstName = "";
		lastName = "";
		program = "";
		level = "";
		asurite = "";
		time = new ArrayList<Integer>();
	}
	
	/**
	 * Custom Constructor
	 * @param setID
	 * @param setFirstName
	 * @param setLastName
	 * @param setProgram
	 * @param setLevel
	 * @param setAsurite
	 */
	public Student(String setID, String setFirstName, String setLastName, String setProgram, String setLevel, String setAsurite) {
		id = setID;
		firstName = setFirstName;
		lastName = setLastName;
		program = setProgram;
		level = setLevel;
		asurite = setAsurite;
		time = new ArrayList<Integer>();
	}
	
	/**
	 * Get ID of Student
	 * @return Student ID
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Get First Name of Student
	 * @return Student First Name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Get Last Name of Student
	 * @return Student Last Name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Get Program of Student
	 * @return Student Program
	 */
	public String getProgram() {
		return program;
	}
	
	/**
	 * Get Level of Student
	 * @return Student Level
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Get ASURITE of Student
	 * @return Student ASURITE
	 */
	public String getAsurite() {
		return asurite;
	}
	
	/**
	 * Get Time of Student
	 * @return Student Time
	 */
	public int getTime() {
		return time.get(time.size() - 1);
	}
	
	/**
	 * Get Time at index of Student
	 * @param index
	 * @return Student Time at index
	 */
	public int getTimeIndex(int index) {
		return time.get(index);
	}
	
	/**
	 * Get the size of the Time list for Student
	 * @return size of Time list
	 */
	public int getTimeSize() {
		return time.size();
	}
	
	/**
	 * Set the ID for Student
	 * @param newID
	 */
	public void setID(String newID) {
		id = newID;
	}
	
	/**
	 * Set the First Name of Student
	 * @param newFirstName
	 */
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	
	/**
	 * Set the Last Name of Student
	 * @param newLastName
	 */
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	
	/**
	 * Set the Program of Student
	 * @param newProgram
	 */
	public void setProgram(String newProgram) {
		program = newProgram;
	}
	
	/**
	 * Set the Level of Student
	 * @param newLevel
	 */
	public void setLevel(String newLevel) {
		level = newLevel;
	}
	
	/**
	 * Set the ASURITE of Student
	 * @param newAsurite
	 */
	public void setAsurite(String newAsurite) {
		asurite = newAsurite;
	}
	
	/**
	 * Set the ASURITE of Student
	 * @param newTime
	 */
	public void setTime(int newTime) {
		time.set(time.size() - 1, newTime);
	}
	
	/**
	 * Set a new Time of Student
	 * @param newTime
	 */
	public void addTime(int newTime) {
		time.add(newTime);
	}
	
	/**
	 * @return String format of Student
	 */
	public String toString() {
		String output = id + "\t\t" + firstName + "\t\t" + lastName + "\t\t" + program + "\t\t" + level + "\t\t" + asurite;
		for (int i = 0; i < time.size(); i++) {
			output += "\t\t" + time.get(i);
		}
		return output;
	}
}
