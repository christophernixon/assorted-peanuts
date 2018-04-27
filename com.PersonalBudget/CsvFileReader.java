package com.personalbudget;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CsvFileReader {
	private static final String DELIMITER = ",";
	// Transaction details indices
	private static final int DATE_INDEX = 0;
	private static final int DETAILS_INDEX = 1;
	private static final int DEBIT_INDEX = 2;
	private static final int CREDIT_INDEX = 3;
	private static final int BALANCE_INDEX = 4;

	// Returns an ArrayList of type Transaction from a correctly formatted CSVfile
	public ArrayList<Transaction> readFromCsvFile(String filepath) throws IOException {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		FileReader filereader = new FileReader(filepath);
		BufferedReader bufferedReader = new BufferedReader(filereader);
		// Read header line
		bufferedReader.readLine();
		String line = "";
		Transaction transaction;
		while ((line = bufferedReader.readLine()) != null) {
			String[] transactionDetails = line.split(DELIMITER, -6);
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = null;
			String details = "";
			float credit = 0;
			float debit = 0;
			float balance = 0;
			if (!transactionDetails[DATE_INDEX].equals("") && !transactionDetails[DATE_INDEX].equals("Date")) {
				date = LocalDate.parse(transactionDetails[DATE_INDEX], dateFormatter);
			}
			if (!transactionDetails[DETAILS_INDEX].equals("") && !transactionDetails[DETAILS_INDEX].equals("Details")) {
				details = transactionDetails[DETAILS_INDEX];
			}
			if (!transactionDetails[DEBIT_INDEX].equals("") && !transactionDetails[DEBIT_INDEX].equals("Debit")) {
				debit = Float.parseFloat(transactionDetails[DEBIT_INDEX]);
			}
			if (!transactionDetails[CREDIT_INDEX].equals("") && !transactionDetails[CREDIT_INDEX].equals("Credit")) {
				credit = Float.parseFloat(transactionDetails[CREDIT_INDEX]);
			}
			if (!transactionDetails[BALANCE_INDEX].equals("") && !transactionDetails[BALANCE_INDEX].equals("Balance")) {
				balance = Float.parseFloat(transactionDetails[BALANCE_INDEX]);

			}
			transaction = new Transaction(date, details, debit, credit, balance);
			transactions.add(transaction);
		}
		bufferedReader.close();
		return transactions;
	}
}
