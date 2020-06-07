//David Ferris(500969121)
/**
 * class Course contains instance variables, constructor methods and various helper methods
 * for Course objects. Class Course is the super class of class CreditCourse and ActiveCourse.
 */
public class Course implements Comparable<Course>
{
	//Instance Variables
   private String code;
   private String name;
   private String description;
   private String format;
   
   	/**
	 * Constructor method for class Course.
	 * Takes no parameters and
	 * Initializes Instance variables to a default empty value.
	 */
   public Course()
   {
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
   }
   	/**
	 * Constructor method for class Course.
	 * Initializes instance variables to the passed Arguments.
	 * @param name
	 * @param code
	 * @param descr
	 * @param fmt
	 */  
   public Course(String name, String code, String descr, String fmt)
   {
	 this.code        = code;
	 this.name        = name;
	 this.description = descr;
	 this.format      = fmt;
   }
   	/**
	 * Method to return the course code of a Course.
	 * @return String code
	 */
   public String getCode()
   {
	   return code;
   }
   	/**
	 * Method to return the name of a course.
	 * @return String name
	 */
   public String getName()
   {
	   return name;
   }
   	/**
	 * Method that returns the format of a Course.
	 * Number of lectures and labs.
	 * @return String format
	 */
   public String getFormat()
   {
	   return format;
   }
   	/**
	 * Method that returns a description of a Course.
	 * This includes the code, name , description and format.
	 * @return String 
	 */
   public String getDescription()
   {
	   return code +" - " + name + "\n" + description + "\n" + format;
   }
   /**
	* Method that returns only the description string for the course.
	* @return String
    */
   public String getDescr()
   {
	   return description;
   }
   /**
	 * Method that returns the coures code and name of a course.
	 * @return String
	 */
   public String getInfo()
   {
	   return code +" - " + name;
   }

   /**
	* compareTo method implementation for the comparable interface.
    */
   public int compareTo(Course other)
   {
	   return this.code.compareTo(other.code);
   }
    /**
	  * Method to convert a numerical score for a course
	  to an alphabetic grade value. grade requirements are based on the Ryerson grade scale.
	  * @param score
	  * @return String AlphabeticGrade
	  */
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
   public static String convertNumericGrade(double score)
   {
 	  if (score >= 90)
 	    return "A+";
 	  else if (score >= 85)
 		return "A";
 	  else if (score >= 80)
 		return "A-";
 	  else if (score >= 77)
 		return "B+";
 	  else if (score >= 73)
 		return "B";
 	  else if (score >= 70)
 	    return "B-";
 	  else if (score >= 67)
 	    return "C+";
 	 else if (score >= 63)
  	    return "C";
 	else if (score >= 60)
 	    return "C-";
 	else if (score >= 57)
 	    return "D+";
 	else if (score >= 53)
 	    return "D";
 	else if (score >= 50)
 	    return "D-";
 	  else return "F";
   }
   
   
}
