//David Ferris (500969121)
import java.util.TreeMap;

/**
 * The scheduler class is responsible for setting the various schedule related instance variables of an active course such as,
 * start time, duration, day, and total lecture time. This class also ensures that there are no collisions or invalid values inputted for these instance variables.
 * The scheduler class also creates the schedule for courses and is used to print a visual representation of this schedule.
 */
public class Scheduler 
{
	//Instance Variables
	private TreeMap<String,ActiveCourse> schedule;
	private ActiveCourse[][] VisualSchedule;
	
	/**
	 * Scheduler Constructor method that sets the Instance variables. schedle is set to the treemap of courses defined in registry
	 * and the visual schedule which is used to place courses at time slots is initialized to an empty 2D array.
	 */
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	  VisualSchedule = new ActiveCourse[9][5];
	}
	
	/**
	 * setDayAndTime method attempts to set the day, startTime, and duration instance variables of an active course.
	 * This method checks multiple restrictions on these values and throws exceptions according to the issue.
	 * @param courseCode
	 * @param day
	 * @param startTime
	 * @param duration
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		if(startTime < 800) //StartTime must be greater than or equal to 800 in order to be valid
		{
			InvalidScheduleException InvalidStartTimeException = new InvalidScheduleException("Invalid Lecture Start Time"); //throw an exception
			throw InvalidStartTimeException;

		}
		if(duration > 3 || duration < 1|| (startTime + (duration*100)) > 1700) //the duration of a course cannot be greater than 3 hours or less than 1 hour and must end by 5:00pm
		{
			InvalidScheduleException InvalidDurationException = new InvalidScheduleException("Invalid Lecture Duration");//throw an exception
			throw InvalidDurationException;
		}
		String upper = courseCode.toUpperCase();
		String lower = courseCode.toLowerCase();
		if(!schedule.containsKey(lower) && !schedule.containsKey(upper)){ //Check if the given course is in the map of courses
			InvalidScheduleException UnkownCourseException = new InvalidScheduleException("Unknown Course: " + courseCode); //if not throw an exception
			throw UnkownCourseException;
		}
		if(CheckCollisions(VisualSchedule, startTime, duration, day, courseCode))//check if the scheduling intersects with another courses scheduling
		{
			InvalidScheduleException timeCollisionException = new InvalidScheduleException("Lecture Time Collision"); //if it does throw an exception
			throw timeCollisionException;
		}
		if(day.equalsIgnoreCase("mon")||day.equalsIgnoreCase("tue")||day.equalsIgnoreCase("wed")||day.equalsIgnoreCase("thur")||day.equalsIgnoreCase("fri")) //ensure the day is a valid day
		{
		String code = courseCode.toUpperCase();
		ActiveCourse ac = schedule.get(code);//make a reference to the active course with this code
		if(ac.getTotalLectureTime() > 3 || ac.getTotalLectureTime()==3) //ensure that the course has not exceeded its max lecture time in the week
		{
			InvalidScheduleException LectureTimeBoundaryException = new InvalidScheduleException("Invalid Lecture Duration: Course is scheduled for more than 3 hours in the week");
			throw LectureTimeBoundaryException;
		}
		ac.setScheduled(startTime, duration, day);//call the setScheduled method in active course to set the instance variables
		ac.setTotalLectureTime();//update the total amount of lecture time this course has
		FillSchedule(ac);	// fill the visual schedule at the given time slots
		}
		else
		{
			InvalidScheduleException InvalidDayException = new InvalidScheduleException("Invalid Lecture Day"); //otherwise thorw an exception
			throw InvalidDayException;
		}
		
	}
	/**
	 * Helper method that is used to check if a courses schedule intersects with a course that has already been scheduled.
	 * @param schedule
	 * @param start
	 * @param duration
	 * @param day
	 * @param code
	 * @return Boolean
	 */
	private Boolean CheckCollisions(ActiveCourse[][] schedule, int start, int duration,String day,String code)
	{
		int dayoftheweek = 0; // these if statements and this variable are used to convert the string day into a corresponding integer value
		if(day.equalsIgnoreCase("mon")){
			dayoftheweek = 0;
		}
		else if(day.equalsIgnoreCase("tue")){
			dayoftheweek = 1;
		}
		else if(day.equalsIgnoreCase("wed")){
			dayoftheweek = 2;
		}
		else if(day.equalsIgnoreCase("thur")){
			dayoftheweek =3;
		}
		else{
			dayoftheweek=4;
		}
		for(int i =0; i < duration; i++)//loop through the positions where the course is attempting to be placed
		{
			if(schedule[(start/100)-8 + i][dayoftheweek] != null)//check if one of these positions is holding a course(something already scheduled)
			{
				return true;//return true 
			}
		}
		for(int j =0; j < schedule.length; j++)//loop through all positions in the day of the week where the course is attempting to be placed
		{
			if(schedule[j][dayoftheweek]!=null){//ensure there is a course at the current position
				if(schedule[j][dayoftheweek].getCode().equalsIgnoreCase(code))//if the course at this position is the same as the one being scheduled then there is collision
				{															  // I have chosen to not allow the same course to be scheduled multiple times in one day
					return true;
				}
			}	
		}
		return false; // if we get to the end of the array without finding a collision return false
	}

	/**
	 * Helper method that is used to fill the 2D array wtih the active course specified
	 * @param ac
	 */
	private void FillSchedule(ActiveCourse ac)
	{
		 if(ac.getDay() != null && !ac.getDay().equals(""))//check that the course has been scheduled correctly
		 {
			if(ac.getDay().equalsIgnoreCase("mon")) //These if statements check for which day the course is to be scheduled and places the course in the column accordingly.
			{
				VisualSchedule[(ac.getStartTime() - 800)/100][0] = ac;//place the course at the start time on the correct day (column)
				extendArray(VisualSchedule,ac, 0); //then the course is placed in the remaining required positions based on its duration using the extendArray method. This pattern follows below.
			}
			else if(ac.getDay().equalsIgnoreCase("tue"))
			{
				VisualSchedule[(ac.getStartTime() - 800)/100][1] = ac;
				extendArray(VisualSchedule,ac, 1);
			}
			else if(ac.getDay().equalsIgnoreCase("wed"))
			{
				VisualSchedule[(ac.getStartTime() - 800)/100][2] = ac;
				extendArray(VisualSchedule,ac, 2);
			}
			else if(ac.getDay().equalsIgnoreCase("thur"))
			{
				VisualSchedule[(ac.getStartTime() - 800)/100][3] = ac;
				extendArray(VisualSchedule,ac, 3);
			}
			else
			{
				VisualSchedule[(ac.getStartTime() - 800)/100][4] = ac;
				extendArray(VisualSchedule,ac, 4);
			}
		 }
	}
	
	/**
	 * the extendArray method fills the remaining specified spaces of the schedule array with the specified course.
	 * Uses the start time as a starting position and the duration to fill the remaining required spaces.
	 * @param ArraySchedule
	 * @param ac
	 * @param dayOfweek
	 */
	private void extendArray(ActiveCourse[][] ArraySchedule, ActiveCourse ac,int dayOfweek)
	{
		for(int i = 1; i < ac.getDuration();i++)
		{
			ArraySchedule[((ac.getStartTime()-800)/100)+i][dayOfweek] = ac;
		}
	}

	/**
	 * removeCourse method loops through the visual schedule and removes every course matching the given course code.
	 * @param coursecode
	 */
	private void removeCourse(String coursecode)
	{
		for (int i =0; i < VisualSchedule.length;i++)//loop through 2D array
		{
			for (int j =0; j < VisualSchedule[0].length;j++)
			{
				if(VisualSchedule[i][j] != null)//if the position does not hold a null value
				{
					if (VisualSchedule[i][j].getCode().equalsIgnoreCase(coursecode))//then check if the current positions course is the same as the specified course code
					{
						VisualSchedule[i][j] = null;//set the position to null(clear the position)
					}	
				}

			}
		}
	}

	/**
	 * clearSchedule method which removes a course from the schedule and resets that courses instance variables.
	 * @param courseCode
	 */
	public void clearSchedule(String courseCode)
	{
		String lower = courseCode.toLowerCase();//create two strings one for the lower case string and one for the upper case
		String upper = courseCode.toUpperCase();
		if(schedule.containsKey(lower))//check if the schedule contains either the lower or upper case course code
		{
		ActiveCourse ac = schedule.get(lower);//create a reference to the specified active course
		ac.setScheduled(0, 0, "");//reset the instance variables
		ac.setTotalLectureTime();//reset total lecture time of the course
		removeCourse(lower);//remove the course from the schedule
		}
		else if(schedule.containsKey(upper))
		{
		ActiveCourse ac = schedule.get(upper);
		ac.setScheduled(0, 0, "");
		ac.setTotalLectureTime();
		removeCourse(upper);
		}
		else
		{
			InvalidScheduleException UnkownCourseException = new InvalidScheduleException("Unknown Course: " + courseCode); // if the course cannot be found throw an exception
			throw UnkownCourseException;
		}
		
	}
	
	/**
	 * printSchedule method loops the visual schedule and prints out the active courses code at the locations they have been placed.
	 */
	public void printSchedule()
	{
		System.out.println( "\t" + "Mon\tTue\tWed\tThur\tFri");//formatting of schedule print out
		for(int i =0; i < VisualSchedule.length;i++)//loop through the visual schedule
		{
			System.out.print((8+i)*100 + "\t"); // print out the times at the side in 24hr clock format
			for (int j = 0; j < VisualSchedule[0].length;j++)
			{
				if(VisualSchedule[i][j] != null)//check that the position holds an active course
				{
					System.out.print(VisualSchedule[i][j].getCode()); // print the course code of the active course 
				}
			System.out.print("\t");	
			}
			System.out.println("");
		}
	}

	/**
	 * InvalidScheduleException is used when the various boundaries of scheduling a course are broken. This exception Extends RuntimeException.
	 */
	class InvalidScheduleException extends RuntimeException
	{
		public InvalidScheduleException(){}
		public InvalidScheduleException(String Message)
		{
			super(Message);
		}
	}
}

