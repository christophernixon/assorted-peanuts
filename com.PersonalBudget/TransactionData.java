package com.personalbudget;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionData {
	private ArrayList<Transaction> transactionList;

	public TransactionData(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	//Specific to my data; searches for common transactions and tags them, such as payslips and groceries
	public void tagAllData(){
		//Incomes
		ArrayList<Transaction> allIncomes = sortByCredit();
		ArrayList<Transaction> avivaTransactions = sortByKeyword("Compass");
		ArrayList<Transaction> servelegalTransactions = sortByKeyword("serve legal");
		ArrayList<Transaction> allowance = sortByKeyword("allowance");
		//Groceries
		ArrayList<Transaction> supervaluGroceries = sortByKeyword("supervalu");
		ArrayList<Transaction> aldiGroceries = sortByKeyword("aldi");
		ArrayList<Transaction> lidlGroceries = sortByKeyword("lidl");
		ArrayList<Transaction> groceries = new ArrayList<Transaction>();
		groceries.addAll(supervaluGroceries);
		groceries.addAll(aldiGroceries);
		groceries.addAll(lidlGroceries);
		for(Transaction transaction: allIncomes){
			transaction.addTag("income");
		}
		for(Transaction transaction: avivaTransactions){
			transaction.addTag("work");
			transaction.addTag("aviva");
		}
		for(Transaction transaction: servelegalTransactions){
			transaction.addTag("work");
			transaction.addTag("servelegal");
		}
		for(Transaction transaction: allowance){
			transaction.addTag("allowance");
		}
		for(Transaction transaction: groceries){
			transaction.addTag("groceries");
		}
	}
	//Most of the transactions have no balance, so this finds and fills in the zero balances in the data.
	public void completeZeroBalances() {
		ArrayList<Transaction> dailyTransactions = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if (transaction.getBalance() == 0) {
				dailyTransactions = sortByDate(transaction.getDate());
				boolean balanceFound = false;
				float balance = 0;
				int i = 0;
				while (!balanceFound && i < dailyTransactions.size()) {
					if (dailyTransactions.get(i).getBalance() != 0) {
						balance = dailyTransactions.get(i).getBalance();
						balanceFound = true;
					}
					i++;
				}
				if (balanceFound) {
					for (Transaction currentTransaction : dailyTransactions) {
						if (currentTransaction.getBalance() == 0)
							currentTransaction.setBalance(balance);
					}
				} else
					System.out.println("Error: balance not found.");
			}
		}
	}
	//Returns the subset of transactions which are in the month of the specified localdate.
	public ArrayList<Transaction> sortByMonth(LocalDate dateOfMonth) {
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			int tempYear = transaction.getDate().getYear();
			if (tempYear == (dateOfMonth.getYear())) { // If is in the correct
														// year
				Month tempMonth = transaction.getDate().getMonth();
				if ((tempMonth.compareTo(dateOfMonth.getMonth())) == 0) { // If
																			// is
																			// in
																			// the
																			// correct
																			// month
																			// of
																			// that
																			// year
					returnList.add(transaction);
				}
			}
		}
		return returnList;
	}
	// Returns total debit in a list of transactions.
	public float getTotalDebit(ArrayList<Transaction> transactions) {
		float totalDebit = 0;
		for (Transaction transaction : transactions) {
			totalDebit += transaction.getDebit();
		}
		return totalDebit;
	}
	// Returns total credit in a list of transactions.
	public float getTotalCredit(ArrayList<Transaction> transactions) {
		float totalCredit = 0;
		for (Transaction transaction : transactions) {
			totalCredit += transaction.getCredit();
		}
		return totalCredit;
	}
	// Returns total debit between two dates
	public float getTotalDebit(LocalDate date1, LocalDate date2) {
		ArrayList<Transaction> relevantTransactions = sortByDate(date1, date2);
		float totalDebit = 0;
		if (relevantTransactions != null) {
			for (Transaction transaction : relevantTransactions) {
				totalDebit += transaction.getDebit();
			}
		}
		return totalDebit;
	}
	// Returns total credit between two dates
	public float getTotalCredit(LocalDate date1, LocalDate date2) {
		ArrayList<Transaction> relevantTransactions = sortByDate(date1, date2);
		float totalCredit = 0;
		if (relevantTransactions != null) {
			for (Transaction transaction : relevantTransactions) {
				totalCredit += transaction.getCredit();
			}
		}
		return totalCredit;
	}
	//Returns all transactions with credit(all incoming transactions)
	public ArrayList<Transaction> sortByCredit(){
			ArrayList<Transaction> returnList = new ArrayList<Transaction>();
			for(Transaction transaction : transactionList){
				if(transaction.getCredit()!=0) returnList.add(transaction);
			}
			return returnList;
		}
	//Returns all transactions with debt(all outgoing transactions)
	public ArrayList<Transaction> sortByDebit(){
			ArrayList<Transaction> returnList = new ArrayList<Transaction>();
			for(Transaction transaction : transactionList){
				if(transaction.getDebit()!=0) returnList.add(transaction);
			}
			return returnList;
		}
	//returns a subset of the main transaction list; those that contain the given keyword in their details
	public ArrayList<Transaction> sortByKeyword(String keyword) {
		keyword = keyword.toLowerCase();
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if (transaction.getDetails().toLowerCase().contains(keyword)) {
				returnList.add(transaction);
			}
		}
		return returnList;
	}
	//return a subset of the main transaction list; those that do not contain the given keyword in their details.
	public ArrayList<Transaction> sortByKeywordInverse(String keyword) {
		keyword = keyword.toLowerCase();
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if (!transaction.getDetails().toLowerCase().contains(keyword)) {
				returnList.add(transaction);
			}
		}
		return returnList;
	}
	//Sorts a specified arraylist of transactions by those inbetween two given dates.
	public ArrayList<Transaction> sortByDate(ArrayList<Transaction> transactions, LocalDate date1, LocalDate date2) {
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		if (!date1.isEqual(date2)) {
			// Ensuring that date1 is always before date2
			if (date1.isAfter(date2)) {
				LocalDate tempDate = date1;
				date1 = date2;
				date2 = tempDate;
			}
			for (Transaction transaction : transactions) {
				LocalDate tempDate = transaction.getDate();
				if ((tempDate.isAfter(date1) || tempDate.isEqual(date1))
						&& (tempDate.isBefore(date2) || tempDate.isEqual(date2))) {
					returnList.add(transaction);
				}
			}
		}
		return returnList;
	}
	public ArrayList<Transaction> sortByDate(LocalDate date1, LocalDate date2) {
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		if (!date1.isEqual(date2)) {
			// Ensuring that date1 is always before date2
			if (date1.isAfter(date2)) {
				LocalDate tempDate = date1;
				date1 = date2;
				date2 = tempDate;
			}
			for (Transaction transaction : transactionList) {
				LocalDate tempDate = transaction.getDate();
				if ((tempDate.isAfter(date1) || tempDate.isEqual(date1))
						&& (tempDate.isBefore(date2) || tempDate.isEqual(date2))) {
					returnList.add(transaction);
				}
			}
		}
		return returnList;
	}
	public ArrayList<Transaction> sortByDate(LocalDate date) {
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if (transaction.getDate().isEqual(date))
				returnList.add(transaction);
		}
		return returnList;
	}
	public void sortAllByMostRecent() {
		Collections.sort(transactionList, new DateComparator());
	}
	public void sortAllByLeastRecent(){
		sortAllByMostRecent();
		Collections.reverse(transactionList);
	}
	//Returns the subset of main arraylist that has the specified tag
	public ArrayList<Transaction> sortByTag(String tag){
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		for(Transaction transaction: transactionList){
			if(transaction.getTags()!=null){
				ArrayList<String> tags = transaction.getTags();
			if(tags.contains(tag)) returnList.add(transaction);
			}
		}
		return returnList;
	}
	//Returns an arraylist with each entry representing a unique monthly income in the form [MONTH: $$$$]
	public ArrayList<String> monthlyIncomes(){
		//Making sure data is sorted by date
		sortAllByLeastRecent();
		ArrayList<String> monthlyIncomes = new ArrayList<String>();
		for(int i=0; i<transactionList.size();i++){
			LocalDate tempDate = transactionList.get(i).getDate();
			ArrayList<Transaction> tempMonthTransactions = sortByMonth(tempDate);
			float tempMonthIncome = getTotalCredit(tempMonthTransactions);
			monthlyIncomes.add(tempDate.getMonth().toString()+": "+tempMonthIncome);
			//Advancing counter past all transactions in month just checked.
			i=i+tempMonthTransactions.size();
		}
		return monthlyIncomes;
	}
	//Returns a float of the average monthly income over the whole transaction list
	public float averageMonthlyIncome(){
		ArrayList<String> monthlyIncomes = monthlyIncomes();
		float totalIncome=0;
		for(String income: monthlyIncomes){
			float temp = Float.parseFloat(income.replaceAll("[a-zA-z]", "").replaceAll(": ", ""));
			totalIncome+=temp;
		}
		//Getting average from total and number of months.
		return totalIncome/monthlyIncomes.size();
	}
	public ArrayList<String> monthlyExpences(){
		//Making sure data is sorted by date
				sortAllByLeastRecent();
				ArrayList<String> monthlyExpences = new ArrayList<String>();
				for(int i=0; i<transactionList.size();i++){
					LocalDate tempDate = transactionList.get(i).getDate();
					ArrayList<Transaction> tempMonthTransactions = sortByMonth(tempDate);
					float tempMonthExpence = getTotalDebit(tempMonthTransactions);
					monthlyExpences.add(tempDate.getMonth().toString()+": "+tempMonthExpence);
					//Advancing counter past all transactions in month just checked.
					i=i+tempMonthTransactions.size();
				}
				return monthlyExpences;
	}
	public float averageMonthlyExpence(){
		ArrayList<String> monthlyExpences = monthlyExpences();
		float totalIncome=0;
		for(String income: monthlyExpences){
			float temp = Float.parseFloat(income.replaceAll("[a-zA-z]", "").replaceAll(": ", ""));
			totalIncome+=temp;
		}
		//Getting average from total and number of months.
		return totalIncome/monthlyExpences.size();
	}
	public void addTransactions(ArrayList<Transaction> extraTransactions) {
		transactionList.addAll(extraTransactions);
	}
	public void printTransactions() {
		System.out.println("\n\n");
		for (Transaction transaction : transactionList) {
			System.out.println(transaction.toString());
		}
	}
	//Prints the transactions of a given arraylist
	public void printTransactions(ArrayList<Transaction> transactions) {
		System.out.println("\n\n");
		for (Transaction transaction : transactions) {
			System.out.println(transaction.toString());
		}
	}
}
