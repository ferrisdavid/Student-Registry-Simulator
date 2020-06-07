//David Ferris(500969121)
import java.util.ArrayList;

/**
 * class student holds the instance variables and helper methods for student objects 
 * student objects hold the name and id of the student along with their list of credit courses
 */
public class Student implements Comparable<Student>
{
  //Instance Variables
  private String name;
  private String id;
  private ArrayList<CreditCourse> courses;
  
  /**
   * Student class constructor method initializes instance variables to arguments and creates a new CreditCourse Arraylist for the student.
   * @param name
   * @param id
   */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses = new ArrayList<CreditCourse>();
  }
  /**
   * Method to get the id instance variable of a student object.
   * @return String id
   */
  public String getId()
  {
	  return id;
  }
   /**
   * Method to get the name instance variable of a student object
   * @return String name
   */
  public String getName()
  {
	  return name;
  }
  
  /**
   * takenCourse method checks if a student has previously taken the specified course.
   * @param courseCode
   * @return Boolean
   */
  public boolean takenCourse(String courseCode)
  {
    for (int j = 0; j < courses.size(); j++)
    {
      if (courses.get(j).getCode().equalsIgnoreCase(courseCode))
        return true;
	}
    return false;
  }
    /**
   * Method creates a new CreditCourse object using the Arguments and sets it as active then finally adds it to the students courses list.
   * @param courseName
   * @param courseCode
   * @param descr
   * @param format
   * @param sem
   * @param grade
   */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse cc = new CreditCourse(courseName,courseCode,descr,format,sem, grade);
	  cc.setActive();
	  courses.add(cc);
  }
  
  /**
   * getGrade method searches through students list of courses for the course with given course code.
   * It returns the students grade in this course.
   * @param courseCode
   * @return
   */
  public double getGrade(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	  {
		if (courses.get(i).getCode().equals(courseCode))
		{
			return courses.get(i).grade; 
		}
	  }
	  return 0;
  }
  
  /**
   * setGrade method searches for the course with the given course code, sets the students grade in this course and sets the course inactive.
   * @param courseCode
   * @param grade
   */
  public void setGrade(String courseCode, double grade)
  {
    for (int k = 0; k < courses.size(); k++)
    {
	   if (courses.get(k).getCode().equalsIgnoreCase(courseCode))
	   {
		  courses.get(k).grade = grade;
		  courses.get(k).setInactive();
	   }
    }
  }
  /**
   * Method that prints a students grade in all completed CreditCourses along with a description of the course
   */
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++)
	  { 
		  if (!courses.get(i).active) 
			  System.out.println(courses.get(i).displayGrade());
	  }
  }
    /**
   * Method that prints all active courses in the students courses arraylist.
   */
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 } 
  }
  
  /**
   * Method that prints the inactive courses of a student.
   */
  public void printCompletedCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (!courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 }
  }
  
    /**
   * Method that removes a course that is active from a students arraylist of courses.
   * @param courseCode
   */
  public void removeActiveCourse(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) 
		 {
            courses.remove(i);
            return;
		 }
	 }
  }
    /**
   * This method overwrites the toString method and returns a string of the students id instance variable
   * and the students name instance variable
   */
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  public int compareTo(Student other)
  {
	  return this.name.compareTo(other.name);
  }
    /**
   * This method overwrites the equals method in the super class and compares two
   * Student objects by name and id to determine if they are the same object
   * returns a boolean value
   */
  public boolean equals(Object other)
  {
	  Student s = (Student) other;
	  return this.name.equals(s.name) && this.id.equals(s.id);
  }
  
}
