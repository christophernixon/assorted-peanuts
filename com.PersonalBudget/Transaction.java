package com.personalbudget;

import java.time.LocalDate;
import java.util.ArrayList;
//I believe this is what one would call a POJO
public class Transaction {
	private LocalDate date;
	private String details;
	private float debit;
	private float credit;
	private float balance;
	private ArrayList<String> tags;

	public Transaction(LocalDate date, String details, float debit, float credit, float balance) {
		super();
		this.date = date;
		this.details = details;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}

	public Transaction(LocalDate date, String details, float debit, float credit, float balance, ArrayList<String> tags) {
		this(date, details, debit, credit, balance);
		this.tags = tags;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public float getDebit() {
		return debit;
	}

	public void setDebit(float debit) {
		this.debit = debit;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag){
		if(tags!=null){
			tags.add(tag);
		}
		else{
			tags = new ArrayList<String>();
			tags.add(tag);
		}
	}
	
	public String toString() {
		return "Transaction: date=" + date + ", details=" + details + ", debit=" + debit + ", credit=" + credit
				+ ", balance=" + balance + (tags != null ? ", tags=" + tags : "");
	}
}
