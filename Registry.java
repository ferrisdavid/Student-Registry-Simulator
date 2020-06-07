//David Ferris (500969121)
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Registry
{
   private TreeMap<String , Student>      students = new TreeMap<String, Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();
   
   public Registry() throws FileNotFoundException
   {
	   // Add some students from a file
	   File StudentsList = new File("students.txt");//Create a new file object referencing file students.txt if the file does not exist a filenotfound exception will be thrown
	   Scanner studentScanner = new Scanner(StudentsList);//create a scanner object to go through the file
	   try {
		readStudents(studentScanner);//call the readStudents method to add the students in the file to the students treemap  
	   } catch (Exception badFormaException) {//catch any errors within the file
		   System.out.println(badFormaException.getMessage());
		   System.exit(0);
	   }
	  
	   	
	   ArrayList<Student> list = new ArrayList<Student>();
	   
	   // Add some active courses with students
	   //CPS209
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   list.add(students.get("38467")); list.add(students.get("98345")); list.add(students.get("57643"));
	   ActiveCourse ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
	   courses.put(courseCode,ac);
	   // Add course to student list of credit courses
	   students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("98345").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	  
	   // CPS511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   list.add(students.get("34562")); list.add(students.get("25347")); list.add(students.get("46532"));
	   ac = new ActiveCourse(courseName,courseCode,descr,format,"F2020",list);
	   courses.put(courseCode,ac);
	   students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   // CPS643
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   list.add(students.get("34562")); list.add(students.get("38467")); list.add(students.get("57643")); list.add(students.get("46532"));
	   ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
	   courses.put(courseCode,ac);
	   students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("38467").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
		// CPS706
		list.clear();
		courseName = "Computer Networks";
		courseCode = "CPS706";
		descr = "Learn about Computer Networking";
		format = "3Lec 1Lab";
		list.add(students.get("57643")); list.add(students.get("46532"));list.add(students.get("98345"));
		courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);
		students.get("98345").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		// CPS616
		list.clear();
		courseName = "Algorithms";
		courseCode = "CPS616";
		descr = "Learn about Algorithms";
		format = "3Lec 1Lab";
		list.add(students.get("34562")); list.add(students.get("25347")); list.add(students.get("46532"));list.add(students.get("57643"));
		courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
		students.get("57643").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		students.get("46532").addCourse(courseName,courseCode,descr,format,"W2020", 0);
		students.get("34562").addCourse(courseName,courseCode,descr,format,"W2020", 0);
		students.get("25347").addCourse(courseName,courseCode,descr,format,"W2020", 0); 
   }
   
    /**
	* 	addNewStudent method that creates a new student object with the given name and id
		and attempts to add them to the registry first checking if there is an identical student
		 object already in the treemap of students.
	* @param name
	* @param id
	* @return boolean
    */ 
   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) != null) return false;//attempt to find the student
	    Student newStudent = new Student(name, id);
	   students.put(id, newStudent);
	   return true;
   }

   /**
	* getCourses method returns the courses instance variable of class registry.
	* @return TreeMap<String,ActiveCourse>
    */
   public TreeMap<String, ActiveCourse> getCourses()
   {
	   return courses;
   }
   
    /**
	* removeStudent method attempts to find a student based on id in the student treemap of registry.
		   if the student is found then they are removed from the treemap and the method returns true, otherwise
		   return false.
	* @param studentId
	* @return Boolean
    */ 
   public boolean removeStudent(String studentId)
   {
	if (students.containsKey(studentId))
	{
		students.remove(studentId);
		return true;
	}
	return false;
   }
   
    /**
	* The printAllStudents method simply loops through the keys of the student treemap in registry
	and prints the names and id of each individual student.
    */
   public void printAllStudents()
   {
	   Set<String> studentIds = students.keySet();
	for (String id : studentIds) {
		System.out.println("Name: " + students.get(id).getName() + " ID: " + id);
	}
   }
   
   /**
	* The findStudent method is a simple helper method that returns the student specified by the id if it is contained
	in the students treemap, otherwise returns null
	* @param id
	* @return
    */
   private Student findStudent(String id)
   {
	if(students.containsKey(id))
	{
		return students.get(id);
	}
     return null;
   }
   
   /**
	* the findCourse method is a simple helper method which returns the active course specified by 
	the course code if it is contained in the courses treemap, otherwise it returns null
	* @param code
	* @return
    */
   private ActiveCourse findCourse(String code)
   {
	String lower = code.toLowerCase();
	String upper = code.toUpperCase();
	if(courses.containsKey(lower))
	{
		return courses.get(lower);
	}
	else if(courses.containsKey(upper))
	{
		return courses.get(upper);
	}
     return null;
   }

    /**
	* addCourse method attempts to add an active course to the list of courses a student has while also adding that student to the list of students of
	an active course. it searches for the indicated student object in the student treemap in class registry and then checks if the student has already taken the indicated course.
	   If not the method then checks to if the student is already currently enrolled in the course and finally if that is not true it adds the student to the active courses list
	   of students and adds the active course to the list of the students courses.
	* @param studentId
	* @param courseCode
    */
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
			   
	   ac.students.add(s);
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
   }
   
   /**
	* dropCourse method attempts to remove a student from an active course and remove that active course
	from the students list of courses.
	* @param studentId
	* @param courseCode
    */
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId);
	   s.removeActiveCourse(courseCode);
   }
   
    /**
	* printActiveCourse method that prints all the courses in the courses treemap in registry.
    */
   public void printActiveCourses()
   {
	   Set<String> courseCodes = courses.keySet();
	  for (String code : courseCodes) 
	  {
		System.out.println(courses.get(code).getDescription());  
	  }     
   }
   
    /**
	* The printClassList method prints the list of students of a given ActiveCourse
	* @param courseCode
    */
   public void printClassList(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();
   }
   
    /**
	* the sortCourseByName method sorts the class list of an ActiveCourse in alphabetical order
	by making a call to the sortByName method defined in the ActiveCourse class.
	* @param courseCode
    */
   public void sortCourseByName(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
   }
   
    /**
	* The sortCourseById method sorts the students list of an ActiveCourse in ascending order of Id number.
	* @param courseCode
    */
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
   }
   
    /**
	* The printGrades method finds a given course and prints the grades of the students in the course.
	* @param courseCode
    */
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }
   
    /**
	* The printStudentCourses method prints the active courses in a specified students courses list.
	* @param studentId
    */
   public void printStudentCourses(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }
   
    /**
	* The printStudentTranscript method prints the grades of non Active courses for a specific student
	* @param studentId
    */
   public void printStudentTranscript(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
   }
   
    /**
	* The setFinalGrade method finds a specified active course in the courses treemap in registry
	and then finds a specified student in the student list of that course and then sets the grade for the student in the specified course
	and sets this course to be inactive
	* @param courseCode
	* @param studentId
	* @param grade
    */
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
   }

   /**
	* the readStudents method is a helper method that accepts a scanner and reads students names and ids from a file. If the data is of correct form
	a new student is created and put in the students treemap otherwise a BadFileException is thrown.
	* @param studentFile
	* @throws BadfileException
    */
   public void readStudents(Scanner studentFile) throws BadfileException
   {
	   
	   while(studentFile.hasNextLine())//loop through the contents of the file
	   {
		 String CurrentLine = studentFile.nextLine();
		 Scanner Line = new Scanner(CurrentLine);
		 String StudentName = Line.next();
		if(!Line.hasNext())//ensure the user has included a student id and a student name
		{
			BadfileException BadData = new BadfileException("Bad File Format: students.txt");//if they havent throw the BadFileException
			throw BadData;
		}
		 String StudentId = Line.next();
		 if(!isStringOnlyAlphabet(StudentName)||!isNumeric(StudentId))//ensure the two pieces of student data are of correct form or through an exception
		 {
			 BadfileException BadData = new BadfileException("Invalid Data Format in Students File");
			 throw BadData;
		 }
		 Student NewStudent = new Student(StudentName, StudentId);
		 students.put(StudentId, NewStudent);
	   }
   }

   /**
	* Helper method to check if a given string consists of strictly alphabetic characters.
	* @param str
	* @return Boolean
    */
   private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  } 
  
  /**
   * Helper method to check if a given string consists of strictly numeric characters.
   * @param str
   * @return
   */
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }

  /**
   * BadfileException is a created exception that extends IOException and is thrown when bad
   * data is detected in a file.
   */
  class BadfileException extends IOException
  {
	  public BadfileException(){}
	  public BadfileException(String Message)
	  {
		  super(Message);
	  }
  }
}
