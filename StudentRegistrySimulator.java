//David Ferris(500969121)
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * StudentRegistrySimulator class holds the main method for the program
 * and handles the different possible commands the user can enter.
 */
public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  try {//Start of try catch block that will catch FileExceptions thrown from registry
		Registry registry = new Registry();//create a new registry object
		Scheduler schedule = new Scheduler(registry.getCourses());//create a new scheduler object
	  	Scanner scanner = new Scanner(System.in);//create a new scanner object for user input
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())//loop while the user still wants to input
	  {
		  String inputLine = scanner.nextLine();//create a new scanner
		  if (inputLine == null || inputLine.equals("")) continue;//if nothing is inputted move to the next iteration
		  
		  Scanner commandLine = new Scanner(inputLine);//create a new scanner
		  String command = commandLine.next();//let this string hold the first word in the given command
		  
		  if (command == null || command.equals("")) continue;//if nothing is inputted, the following block of if statements are used to check what command to execute
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))// this command lists the students in the registry (students that are registered)
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))// this command quits the program stopping execution
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))//this command registers a new student into the registry
		  {
			 // get name and student id string
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())//the purpose of this line of code is to ensure the user has entered a neccessary value for the command to be executed
			  {
				 name = commandLine.next();//read the name of the student from the commandline
				 // check for all alphabetical
				 String lcase = name.toLowerCase();
				 if (!isStringOnlyAlphabet(lcase))
				 {
				   System.out.println("Invalid Characters in Name " + name);
				   continue;
				 }
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();//read the student id from the commandline
				 // check for all numeric
				 if (!isNumeric(id))
				 {
				   System.out.println("Invalid Characters in ID " + id);
				   continue;
				 }
				 if (!registry.addNewStudent(name,id))//check if the student is already registered
					 System.out.println("Student " + name + " already registered");
			  }
			 
		  }
		  else if (command.equalsIgnoreCase("DEL"))//this command removes a student from the registry
		  {
			  if (commandLine.hasNext())
			  {
				 String id = commandLine.next();//read the students id from the commandline
				 // check for all numeric
				 
				 if (!isNumeric(id))//ensure students id is strictly numeric
				   System.out.println("Invalid Characters in student id " + id);
				 registry.removeStudent(id);//remove the student
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC"))//this command is used to print all the active courses
		  {
			  registry.printActiveCourses();//call the printActiveCourses method defined in Registry class
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))//this command is used to print the list of students in an active course
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();// get course code string
			     registry.printClassList(courseCode);//call the printClassList method defined in the Registry class
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))//this command is used to print the grades of all students in enrolled in a specific ActiveCourse
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();// get course code string
			     registry.printGrades(courseCode);//call the printGrades method defined in class Registry
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC"))//this command adds a course to a students list of courses and the student to the list of students for the course
		  {
			  // add a student to an active course
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();//read the students id from the commandline
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();//read the students name from the commandline
				 registry.addCourse(id, courseCode);//call the add course method from the Registry class
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))//this command is used to drop a course from a students list of courses
		  {
			  // get student id and course code strings
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();//read the students id from the commandline
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();//read the course code from the commandline
				 if(isNumeric(id)){
					registry.dropCourse(id, courseCode);//call the drop course method defined in class Registry 
				 }
				 
			  } 
		  }
		  
		  else if (command.equalsIgnoreCase("PSC"))// this command is used to print the credit courses of a given student
		  {
			  // get student id string
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();//read the students id from the commandline
				 if (isNumeric(studentId))//ensure the id is strictly numeric
			  {
				registry.printStudentCourses(studentId);  //call the printStudentCourses method defined in the Registry class to print the students active credit courses
			  }
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))//this command is used to print a students transcript
		  {
			  // get student id string
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();//read the students id from the commandline
				 if(isNumeric(studentId)){
				registry.printStudentTranscript(studentId); // call the printStudentTranscript method defined in Registry class to print only inactive courses grades
				 }
			     
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))//this command is used to set the final grade of a student in a specific course
		  {
			  // get course code, student id, numeric grade
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();//read the course code from the commandline
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();//read the students id from the commandline
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();//read the grade from the commandline
				  if (!isNumeric(grade)) continue;
				  numGrade = Integer.parseInt(grade);//parse the string to retrieve the integer value
				  registry.setFinalGrade(courseCode, id, numGrade);//call the setFinalGrade method defined in the Registry class
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCN"))//this command is used to sort the list of students in a given course by name alphabetically
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();//read the course code from the commandline
				 // sort list of students in course by name (i.e. alphabetically)
			     registry.sortCourseByName(courseCode);//call the sortCourseByName in the Registry class
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI"))//this command is used to sort the list of students of a given course by id (ascending)
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();//read the course code from the commandline
				 // sort list of students in course by student id
				 registry.sortCourseById(courseCode);//call the sortCourseById method
			  }
		  }
		  else if(command.equalsIgnoreCase("SCH"))//this command is used to schedule a course at a given time, day, and for a specific duration
		  {
			String courseCode = null;
			String day = null;
			int startTime = 0;
			int duration =0;
			if(!commandLine.hasNext()){continue;}
			courseCode = commandLine.next();//read the course code from the commandline
			if(!commandLine.hasNext()){continue;}
			day = commandLine.next();//read the day from the commandline
			if(!commandLine.hasNextInt()){continue;}
			startTime = commandLine.nextInt();//read the start time from the commandline
			if(!commandLine.hasNextInt()){continue;}
			duration = commandLine.nextInt();//read the duration from the commandline
			try {//try catch block to attempt to schedule the course
			schedule.setDayAndTime(courseCode,day,startTime,duration);//call setDayAndTime in scheduler
			} catch (Exception InvalidSchedule) {//catch any exceptions thrown by this method and print the message to the user
				System.out.println(InvalidSchedule.getMessage());
			}
			
		  }
		  else if(command.equalsIgnoreCase("CSCH"))//this command is used to clear the schedule of a specific course and reset that courses schedule instance variables
		  {
			if(!commandLine.hasNext()){continue;}
			  String courseCode = commandLine.next();//read course code from the commandline
			  try { //try catch block to attempt to clear the specified course from schedule
				schedule.clearSchedule(courseCode); //call clearSchedule in class Scheduler
			  } catch (Exception NoCourseException) {//catch exceptions thrown by this method and print the message to the user
				  System.out.println(NoCourseException.getMessage());
			  }
			  
		  }
		  else if(command.equalsIgnoreCase("PSCH"))//this command is used to print the current schedule out to the user
		  {
			  schedule.printSchedule();//call the print schedule method
		  }
		  System.out.print("\n>");
	  }
	} 
	  catch (FileNotFoundException FileException) {//catch file exceptions generated by the registry class
		  System.out.println("File Not Found");
		  return;
	  }
	  
	  
  }
  
  /**
   * The isStringOnlyAlphabet takes a string and determines whether or not the string is contains only alphabetic
   * characters
   * @param str
   * @return boolean
   */
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); //make sure the string is not null or empty and return true or false depending on if the string contains any non-alphabetic characters
  } 
  
  /**
   * The isNumeric method takes a string and determines whether the string is entirely numeric or not.
   * @param str
   * @return boolean
   */
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())//loop through characters in str
      {
          if (!Character.isDigit(c)) return false;//return false if there is a character that is not a digit
      }
      return true;//return true if all characters are digits
  }
  
}