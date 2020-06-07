//David Ferris (500969121)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
   //Instance Variables
   public  ArrayList<Student> students; // map id to name
   private String             semester;
   //Schedule Instance Variables
   private int lectureStart;
   private int lectureDuration;
   private String lectureDay;
   private int TotalLectureTime;
	
   /**
	* Constructor Method of the ActiveCourse class 
      that initializes the instance variables by making a call to the super class
      and by directly setting instance variables specific to the ActiveCourse class.
	* @param name
	* @param code
	* @param descr
	* @param fmt
	* @param semester
	* @param students
    */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   this.TotalLectureTime = 0;
   }
   /**
	* Method used by the scheduler class to set the schedule variables for an Active Course.
	* @param lectureStart
	* @param lectureDuration
	* @param lectureDay
    */
	public void setScheduled(int lectureStart, int lectureDuration, String lectureDay)
	{
		this.lectureStart = lectureStart;
		this.lectureDuration = lectureDuration;
		this.lectureDay = lectureDay;
	}

	public void setTotalLectureTime()
	{
		if(lectureDuration==0)
		{
			this.TotalLectureTime = 0;
		}
		else{
			this.TotalLectureTime += lectureDuration;
		}
	}
	/**
	 * Method that returns the total lecture duration of a course
	 * @return
	 */
	public int getTotalLectureTime()
	{
		return TotalLectureTime;
	}
	/**
	 * Method that returns the LectureDay Instance Variable of the Active Course
	 * @return String
	 */
	public String getDay()
	{
		return this.lectureDay;
	}
	/**
	 * Method that returns the LectureStart Instance Variable of an Active Course.
	 * @return String
	 */
	public int getStartTime()
	{
		return this.lectureStart;
	}
	/**
	 * Method that returns the LectureDuration Instance Variable of an Active Course.
	 * @return String
	 */
	public int getDuration()
	{
		return this.lectureDuration;
	}
	/**
    * Method to return the semester that the active course is being taken in.
    * @return String Semester (instance variable)
    */
   public String getSemester()
   {
	   return semester;
   }
    /**
    * Method used to print the student objects names and id's of an active course.
    */
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }
    /**
    * Method to print grades of all students in the active course.
      Gets students names and id from methods in student class
      and gets the grade from a method in the ActiveCourse class
    */
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }
    /**
    * Method that returns a string containing the description of the course, the semester, and the total number of students
      enrolled in the course.
    */
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }
   /**
	* Method that returns strictly the description Instance Variable.
	* @return String
    */
   public String getCourseDescription()
   {
	   return getDescr();
   }
   
   /**
    * Method to return grade of a student by accessing their list of credit courses
      and finding that which matches this active courses code and then returns the grade instance variable
      of the credit course object.
    * @param studentId
    * @return double grade
    */
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }
   /**
	* Method that checks if a student is currently enrolled in this Active Course.
	* @param studentId
	* @return Boolean
    */
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }
   
   /**
	* Method that finds a student in the courses list of students and removes that student from the class list.
	* @param id
    */
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }
   
    /**
    * Method that calls the collections.sort method on the students arraylist
      and sorts the list by name in alphabetical order utilizing the comparator interface.
    */  
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
    /**
    * NameComparator class implements the Comparator interface on Student objects 
      and holds the compare method definition.
    */
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }
   
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   /**
    * IdComparator class that implements the comparator interface on Student objects and holds the compare method definition.
    */
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }
}
