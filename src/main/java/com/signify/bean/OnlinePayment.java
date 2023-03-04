package com.signify.bean;

import java.io.Serializable;

public class OnlinePayment extends Payment implements Serializable {
	private int isCard;
	private String cardNumber, cardType, modeOfTransfer, accountNumber, ifsccode;

	public OnlinePayment(int isCard, String cardNumber, String cardType, String accountNumber, String ifscCode,
			String modeOfTransfer) {
		this.isCard = isCard;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.accountNumber = accountNumber;
		this.modeOfTransfer = modeOfTransfer;
		this.ifsccode = ifscCode;
	}

	public int isCard() {
		return isCard;
	}

	public void setCard(int isCard) {
		this.isCard = isCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getModeOfTransfer() {
		return modeOfTransfer;
	}

	public void setModeOfTransfer(String modeOfTransfer) {
		this.modeOfTransfer = modeOfTransfer;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

}
