//David Ferris(500969121)
/**
 * class CreditCourse holds information on the courses that students have taken or are currently taking. 
 * CreditCourse is a subclass of Course.
 */
public class CreditCourse extends Course 
{
	//Instance Variables
	private String  semester;
	public  double  grade;
	public  boolean active;
	
	/**
	 * Constructor method for the CreditCourse object.
	 * A CreditCourse is a course object with a grade assigned to it as well as a semester in which it was taken.
 	 * A CreditCourse can either be active(currently being taken) or inactive(already completed).
 	 * CreditCourse creditcourse = new CreditCourse(String name, String code, String descr, String fmt,String semester, Double grade).
	 * @param name
	 * @param code
	 * @param descr
	 * @param fmt
	 * @param semester
	 * @param grade
	 */
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		// redundant 
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade    = grade;
		active = false;
	}
	/**
	 * Method to set a CreditCourse to be active.
	 *  sets the CreditCourse objects active intance variable to true. 
	 */
	public void setActive()
	{
		active = true;
	}
	/**
	 * Method to set a CreditCourse to be inactive.
	 * sets the CreditCourse objects active intance variable to false. 
	 */
	public void setInactive()
	{
		active = false;
	}
	/**
	 * Method returns the Course Code and name through a call to a super method as well as the semester
	 * the class was taken along with the grade that was achieved.
	 * @return String Course Info, Semester, and Alphabetic grade.
	 */
	public String displayGrade()
	{
		return getCode() + " " + getName() + " " + semester + " Grade " + convertNumericGrade(grade); 
	}
	
}
