package com.personalbudget;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class CsvWriteReadGitTest {
	private static ArrayList<Transaction> readInTransactions;
	public static void main(String[] args) {
		CsvFileReader csvFileReader = new CsvFileReader();
		try {
			//Read in data from a file
			readInTransactions = csvFileReader.readFromCsvFile("EXAMPLEFILEPATH");
			TransactionData transactionData = new TransactionData(readInTransactions);
			/*
			 * Use some of the functions in TransactionData to do something useful with the data...
			 * E.G
			 * transactionData.completeZeroBalances();
			 * transactionData.tagAllData();
			 * transactionData.sortAllByLeastRecent();
			 */
			//Write the adjusted data back to a new (updateable) csv file
			CsvFileWriter csvFileWriter = new CsvFileWriter();
			csvFileWriter.clearDataFromTransactionCopies();
			csvFileWriter.writeHeaderToTransactionCopies();
			csvFileWriter.writeToTransactionCopies(readInTransactions);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
