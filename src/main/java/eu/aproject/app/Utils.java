package eu.aproject.app;

public class Utils {
	
	/**
	 * 
	 * @param value  Day value as a string
	 * @return       The day value as int, starting with sunday(1)
	 */
	public static int getDayOfWeek(String value){
	    int day = 0;
	    value = value.toLowerCase();
	    switch(value){
	    case "sunday":
	        day=1;
	        break;
	    case "monday":
	        day=2;
	        break;
	    case "tuesday":
	        day=3;
	        break;
	    case "wednesday":
	        day=4;
	        break;
	    case "thursday":
	        day=5;
	        break;
	    case "friday":
	        day=6;
	        break;
	    case "saturday":
	        day=7;
	        break;
	    }
	    return day;
	}

	/**
	 * 
	 * @param dateArray 
	 * @return if all string in a array are ints returns true, otherwise fase
	 */
	public static boolean isPositiveInts(String[] dateArray) {
		for (String s : dateArray) {
			if (s.equals("") || !isPositiveInt(s)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Checks if a string is an int
	 * @param string
	 * @return if string is int returns true.
	 */
	public static boolean isPositiveInt(String string) {
		
		return string.matches("\\d+");
	}
	
}
