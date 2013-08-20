package com.me.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="PaymentInfo")
public class PaymentInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="paymentInfoId", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentInfoId;
	@Column(name = "creditCardNumber")
	private String creditCardNumber;
	@Column(name = "cvcNumber")
	private String cvcNumber;
	@Column(name = "expirationDate")
	private String expirationDate;
	@Column(name = "cardType")
	private String cardType;
	
	public PaymentInfo(){
	}
	
	public PaymentInfo(PaymentInfo paymentInfo){
		this.creditCardNumber = paymentInfo.creditCardNumber;
		this.cvcNumber = paymentInfo.cvcNumber;
		this.cardType = paymentInfo.cardType;
		this.expirationDate = paymentInfo.expirationDate;
		
	}

	public int getPaymentInfoId() {
		return paymentInfoId;
	}

	public void setPaymentInfoId(int paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCvcNumber() {
		return cvcNumber;
	}

	public void setCvcNumber(String cvcNumber) {
		this.cvcNumber = cvcNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
