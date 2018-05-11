package servicedapartment.data;

import java.time.Instant;

import org.threeten.extra.*;

/**
 * DateOverlap class is a class that use to check time interval that two time intervals are overlap or not.
 * 	This class use extra library called ThreeTen-Extra which provides additional date-time classes that
 *  complement those date-time classes in Java SE 8.
 * @author Kunyaruk Katebunlu
 */
public class DateOverlap {
	
	/**
	 * Checks if one interval overlaps another interval.
	 * @param startA is first date of an interval.
	 * @param endA is last date of an interval.
	 * @param startB is first date of another interval.
	 * @param endB is last date of another interval.
	 * @return true if the time intervals are overlap.
	 */
	public boolean checkOverlap(String startA, String endA, String startB, String endB) {
		Instant instantStartA = Instant.parse(startA);
		Instant instantEndA = Instant.parse(endA);
		Interval A = Interval.of(instantStartA, instantEndA);
		
		Instant instantStartB = Instant.parse(startB);
		Instant instantEndB = Instant.parse(endB);
		Interval B = Interval.of(instantStartB, instantEndB);
		
		return A.overlaps(B) || A.encloses(B) || B.encloses(A) || A.equals(B);
	}
	
	/**
	 * Checks if this interval is before the specified interval.
	 * @param startA is first date of an interval.
	 * @param endA is last date of an interval.
	 * @param startB is first date of another interval.
	 * @param endB is last date of another interval.
	 * @return true if one instant is before the another instant.
	 */
	public boolean checkIsBefore(String startA, String endA, String startB, String endB) {
		Instant instantStartA = Instant.parse(startA);
		Instant instantEndA = Instant.parse(endA);
		Interval A = Interval.of(instantStartA, instantEndA);
		
		Instant instantStartB = Instant.parse(startB);
		Instant instantEndB = Instant.parse(endB);
		Interval B = Interval.of(instantStartB, instantEndB);
		
		return B.isBefore(A);
	}
	
}
