/**
 * Date time helper 
 */

package web.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateTime is an helper for manipulating Date Time 
 * 
 */
public final class DateTime {
	private DateTime() {
		
	}
	
	/**
	 * This method is intended to format date-time using the specified formatter
	 * 
	 * @param localDateTime the local date time
	 * @param pattern the formatter pattern to use
	 * 
	 */
	public static String format(final LocalDateTime localDateTime, final String pattern) {
	    return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	
	/**
	 * This method is intended to format date-time 
	 * using the specified SimpleDateFormat pattern
	 * 
	 * @param pattern the formatter pattern to use
	 * 
	 */
	public static String format(final Date date, final String pattern) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    return simpleDateFormat.format(date);
	}

}
