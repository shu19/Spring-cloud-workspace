package com.mm.transaction.transactionservice.dataset;

import java.util.List;

import org.springframework.hateoas.Link;

import com.mm.transaction.transactionservice.entity.Transaction;

public class DataSet {

	private List<Transaction> transaction;
	private Link nextLink;
	private Link previousLink;

	public DataSet(List<Transaction> transaction, Link nextLink, Link previousLink) {
		super();
		this.transaction = transaction;
		this.nextLink = nextLink;
		this.previousLink = previousLink;
	}

	public DataSet() {
		// TODO Auto-generated constructor stub
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public Link getNextLink() {
		return nextLink;
	}

	public void setNextLink(Link nextLink) {
		this.nextLink = nextLink;
	}

	public Link getPreviousLink() {
		return previousLink;
	}

	public void setPreviousLink(Link previousLink) {
		this.previousLink = previousLink;
	}

	@Override
	public String toString() {
		return "DataSet [transaction=" + transaction + ", nextLink=" + nextLink + ", previousLink=" + previousLink
				+ "]";
	}

}
