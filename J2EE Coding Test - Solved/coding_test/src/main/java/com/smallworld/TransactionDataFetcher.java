package com.smallworld;

import com.smallworld.data.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TransactionDataFetcher {

	static Transaction t = new Transaction();
	static List<Transaction> list = new ArrayList<Transaction>();
	
	 public static void main(String args[])

     {
		 
                Object transaction = null;
				try {
					transaction = new JSONParser().parse(new FileReader("C:\\Users\\Zoh786\\Downloads\\J2EE Coding Test\\coding_test\\transactions.json"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				JSONArray transArray = (JSONArray) transaction;
			
                // iterate over each element
				transArray.forEach( trans -> parseEachObject( (JSONObject) trans ) );
				
				//test all methods here
				//getTotalTransactionAmount();
				//getMaxTransactionAmount();
				//getUnsolvedIssueIds();
     }
	 
	 private static void parseEachObject(JSONObject trans) 
	    {
	        //Get object within list
	        JSONObject eObject = (JSONObject) trans;
	        
	        t = new Transaction();
	       //Returns all transactions indexed by beneficiary name
	        t.setAmount((double) eObject.get("amount"));
	        t.setBeneficiaryAge((long) eObject.get("beneficiaryAge"));
	        t.setBeneficiaryFullName((String)eObject.get("beneficiaryFullName"));
	        t.setIssueId((Long) eObject.get("issueId"));
	        t.setIssueMessage((String) eObject.get("issueMessage"));
	        t.setIssueSolved((boolean) eObject.get("issueSolved"));
	        t.setMtn((long) eObject.get("mtn"));
	        t.setSenderAge((long) eObject.get("senderAge"));
	        t.setSenderFullName((String) eObject.get("senderFullName"));
	       
	        list.add(t);
	        
	        
	         
	    }
	 
    /**
     * Returns the sum of the amounts of all transactions
     */
    public static void getTotalTransactionAmount() {
    	double sum = list.stream().mapToDouble(Transaction::getAmount).sum();
        System.out.print("Total transction amount is: " + sum);
        
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public static void getTotalTransactionAmountSentBy(String senderFullName) {
    	
    	double sum = list.stream()
    			.filter(c -> c.getSenderFullName().equals(senderFullName))
    			.mapToDouble(Transaction::getAmount).sum();
    	
    	   System.out.print("Total transction amount sender is: " + sum);
       
    }

    /**
     * Returns the highest transaction amount
     */
    public static void getMaxTransactionAmount() {
    	Transaction highestTransaction = list.stream()
                .max(Comparator.comparing(Transaction::getAmount)).get();
    	
    	 System.out.print("Highest transaction: " + highestTransaction.getAmount());
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public static void countUniqueClients() {
    	// where sender and beneficiary is change
    	Stream<Transaction> uniqueClient = list.stream()
    			.distinct()
    			.filter(c -> c.getSenderFullName() != c.getBeneficiaryFullName());
    	
    	System.out.println(uniqueClient.count());
        
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public static void hasOpenComplianceIssues(String clientFullName) {
    	Stream<Transaction> openIssue = list.stream()
    			.filter(c -> c.getSenderFullName().equals(clientFullName) && c.isIssueSolved() == false );
    			
        System.out.println(openIssue.count());
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public static void getTransactionsByBeneficiaryName() {
    	Map<String, String> map = list.stream().distinct().collect(Collectors.toMap(c -> c.getBeneficiaryFullName(), c -> c.toString()));
    	
    	System.out.println(map);
    	
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public static void getUnsolvedIssueIds() {
    	List<Long> openIssue = list.stream()
    			.filter(c -> c.isIssueSolved() == false)
    			.map(Transaction::getIssueId)
    			 .collect(Collectors.toList());
    	
    	System.out.println(openIssue);
    	
    }

    /**
     * Returns a list of all solved issue messages
     */
    public static void getAllSolvedIssueMessages() {
    	
    	List<String> solvedIssueMessages = list.stream()
    			.filter(c -> c.isIssueSolved() == true)
    			.map(Transaction::getIssueMessage)
    			 .collect(Collectors.toList());
    	
    	System.out.println(solvedIssueMessages);
    	
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public static void  getTop3TransactionsByAmount(List<Transaction> list) {
    	
        List<Transaction> expensive = list.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .limit(3)
                .toList();
        System.out.println(expensive);
        
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public static void getTopSender() {
    	
    	Optional<Transaction> highest = list.stream()
                .max(Comparator.comparing(Transaction::getAmount));
    	
    	
        System.out.println(highest);
    }

}
