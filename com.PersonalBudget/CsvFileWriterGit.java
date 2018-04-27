package com.personalbudget;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CsvFileWriterGit {
	private static final char DELIMITER = ',';
	private static final char NEWLINE = '\n';
	private static final String FILE_HEADER = "Date,Details,Debit,Credit,Balance";
	private static final String TRANSACTION_COPIES_FILEPATH = "WhereYouWantYourCopyOfFile/Filename.csv";

	// Clears all data from thr TransactionCopies csv file
	public void clearDataFromTransactionCopies() throws IOException {
		FileWriter filewriter = new FileWriter(TRANSACTION_COPIES_FILEPATH);
		filewriter.write("");
		filewriter.close();
	}
	// Writes the header to the TransactionCopies file(Generally will only be used the first time data is written to the file)
	public void writeHeaderToTransactionCopies() throws IOException {
		FileWriter filewriter = new FileWriter(TRANSACTION_COPIES_FILEPATH, true);
		filewriter.write(FILE_HEADER);
		filewriter.write(NEWLINE);
		filewriter.close();
	}
	// Writes a transaction to the TransactionCopies file
	public void writeToTransactionCopies(Transaction transaction) throws IOException {
		if (transaction != null) {
			FileWriter filewriter = new FileWriter(TRANSACTION_COPIES_FILEPATH, true);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			LocalDate date = transaction.getDate();
			String details = transaction.getDetails();
			float debit = transaction.getDebit();
			float credit = transaction.getCredit();
			float balance = transaction.getBalance();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = (date).format(formatter);
			bufferedwriter.write(formattedDate);
			bufferedwriter.write(DELIMITER);
			bufferedwriter.write(details);
			bufferedwriter.write(DELIMITER);
			bufferedwriter.write(String.valueOf(debit));
			bufferedwriter.write(DELIMITER);
			bufferedwriter.write(String.valueOf(credit));
			bufferedwriter.write(DELIMITER);
			bufferedwriter.write(String.valueOf(balance));
			bufferedwriter.write(NEWLINE);
			bufferedwriter.flush();
			bufferedwriter.close();
			filewriter.close();
		}
	}
	// Writes multiple transactions contained in an arraylist to the transactionCopies file
	public void writeToTransactionCopies(ArrayList<Transaction> transactions) throws IOException {
		FileWriter filewriter = new FileWriter(TRANSACTION_COPIES_FILEPATH, true);
		BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
		for (Transaction transaction : transactions) {
			if (transaction != null) {
				LocalDate date = transaction.getDate();
				String details = transaction.getDetails();
				float debit = transaction.getDebit();
				float credit = transaction.getCredit();
				float balance = transaction.getBalance();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String formattedDate = (date).format(formatter);
				bufferedwriter.write(formattedDate);
				bufferedwriter.write(DELIMITER);
				bufferedwriter.write(details);
				bufferedwriter.write(DELIMITER);
				bufferedwriter.write(String.valueOf(debit));
				bufferedwriter.write(DELIMITER);
				bufferedwriter.write(String.valueOf(credit));
				bufferedwriter.write(DELIMITER);
				bufferedwriter.write(String.valueOf(balance));
				bufferedwriter.write(NEWLINE);
			}
		}
		bufferedwriter.flush();
		bufferedwriter.close();
		filewriter.close();
	}
}
