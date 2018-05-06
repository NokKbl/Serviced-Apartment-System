package servicedapartment.data;

import java.time.Instant;

import org.threeten.extra.*;

public class DateOverlap {
	
	public boolean checkOverlap(String sA, String eA, String sB, String eB) {
		Instant ssA = Instant.parse(sA);
		Instant eeA = Instant.parse(eA);
		Interval A = Interval.of(ssA, eeA);
		
		Instant ssB = Instant.parse(sB);
		Instant eeB = Instant.parse(eB);
		Interval B = Interval.of(ssB, eeB);
		
		return A.overlaps(B) || A.encloses(B) || B.encloses(A) || A.equals(B);
	}
	
	public boolean checkIsBefore(String sA, String eA, String sB, String eB) {
		Instant ssA = Instant.parse(sA);
		Instant eeA = Instant.parse(eA);
		Interval A = Interval.of(ssA, eeA);
		
		Instant ssB = Instant.parse(sB);
		Instant eeB = Instant.parse(eB);
		Interval B = Interval.of(ssB, eeB);
		
		return B.isBefore(A);
	}
}
