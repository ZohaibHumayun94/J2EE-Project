package com.smallworld.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Transaction {
	
	private long mtn;
    private double amount;
    private String senderFullName;
    private long senderAge;
    private String beneficiaryFullName;
    private long beneficiaryAge;
    private Long issueId;
    private boolean issueSolved;
    private String issueMessage;
	public long getMtn() {
		return mtn;
	}
	public void setMtn(long mtn) {
		this.mtn = mtn;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSenderFullName() {
		return senderFullName;
	}
	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}
	public long getSenderAge() {
		return senderAge;
	}
	public void setSenderAge(long senderAge) {
		this.senderAge = senderAge;
	}
	public String getBeneficiaryFullName() {
		return beneficiaryFullName;
	}
	public void setBeneficiaryFullName(String beneficiaryFullName) {
		this.beneficiaryFullName = beneficiaryFullName;
	}
	public long getBeneficiaryAge() {
		return beneficiaryAge;
	}
	public void setBeneficiaryAge(long beneficiaryAge) {
		this.beneficiaryAge = beneficiaryAge;
	}
	public Long getIssueId() {
		return issueId;
	}
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	public boolean isIssueSolved() {
		return issueSolved;
	}
	public void setIssueSolved(boolean issueSolved) {
		this.issueSolved = issueSolved;
	}
	public String getIssueMessage() {
		return issueMessage;
	}
	public void setIssueMessage(String issueMessage) {
		this.issueMessage = issueMessage;
	}
	  
	// Override of toString
    @Override
    public String toString() {
        return getBeneficiaryFullName() + " " + getAmount() +  " " + getBeneficiaryAge() +  " " + getIssueMessage() +  " "  + getSenderFullName();
    }  
	 
}
	