/*******************************************************************************
 *   Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *        Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.examples.kwiee.erp.module.financial.accounting.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ACCT_TXN")
@NamedQuery(name = "Transaction.findAllSince", query = "SELECT t FROM Transaction t WHERE t.account = :account AND t.txTime >= :dateArg")
public class Transaction {
	@Id
	@GeneratedValue
	int id;

	@ManyToOne
	Account account;

	@Column(name = "OP")
	TxOperation operation;

	double amount;

	@Temporal(TemporalType.TIME)
	Date txTime;

	/* Constructors */
	public Transaction() {
		super();
	}

	public Transaction(Account account, TxOperation operation, double amount) {
		super();
		this.account = account;
		account.getTxns().add(this);
		this.operation = operation;
		this.amount = amount;
		this.txTime = Calendar.getInstance().getTime();
	}

	/* Getters and setters */
	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TxOperation getOperation() {
		return operation;
	}

	public void setOperation(TxOperation operation) {
		this.operation = operation;
	}

	public Date getTxTime() {
		return txTime;
	}

	public void setTxTime(Date txTime) {
		this.txTime = txTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String toString() {
		return "(" + txTime + " - " + "Acct#: " + account.getId() + " "
				+ operation.toString() + ": " + amount + ")";
	}
}
