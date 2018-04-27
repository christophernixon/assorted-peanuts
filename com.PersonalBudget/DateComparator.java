package com.personalbudget;

import java.util.Comparator;

public class DateComparator implements Comparator<Transaction> {

	public int compare(Transaction transaction1, Transaction transaction2) {
		int returnValue = 0;
		if (transaction1 != null && transaction2 != null) {
			if (transaction1.getDate().isBefore(transaction2.getDate()))
				returnValue = 1;
			if (transaction1.getDate().equals(transaction2.getDate()))
				returnValue = 0;
			if (transaction1.getDate().isAfter(transaction2.getDate()))
				returnValue = -1;
		}
		return returnValue;
	}

}
