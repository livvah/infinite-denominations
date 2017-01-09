package change;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class ChangeMakerImpl_Vahsen implements ChangeMaker {
	
	private Set<Integer> theDenominations;
	
	
	public ChangeMakerImpl_Vahsen(Set<Integer> denominations){
		
		assert denominations.size() > 0: "Must have elements in denominations";
		
		//setting private Set equal to parameter
		this.theDenominations = denominations;
		
		
	}
	
	//pre: getDenominations() list
	//		valueInCents > 0
	//post: rv returns true if change can be made
	public List<Integer> getDenominations() {

		
		List<Integer> denomList = new ArrayList<Integer>(); //construct list of denoms

		//add what was created in the constructor
	    denomList.addAll(theDenominations);
		
	    
		Collections.sort(denomList);//sorts smallest to largest L to R
		Collections.reverse(denomList);//is now largest to smallest
		
		return denomList;
		
	}

	//pre: getDenominations() list
	//		valueInCents > 0
	//post: rv returns true if change can be made
	public boolean canMakeExactChange(int valueInCents) {
		
		List<Integer> denomTemp = new ArrayList<Integer>();
		denomTemp = this.getDenominations();
		
		boolean rv = false;
		
		assert valueInCents > 0: "value must be greater than zero";
		
		//first element in list that gives mod > 0
		for(int i = 0; i < denomTemp.size(); i++){
			
			int temp = denomTemp.get(i);
			int numCoins = numberOfCoins(valueInCents, temp);
			while (valueInCents%temp > 0 && valueInCents > temp){
				
				//valueInCents = valueInCents/temp;
				valueInCents = valueInCents - (numCoins*temp);
			}
			//loop runs if valueInCents can still be divided by temp
			while(valueInCents >= temp && temp != 1){
				
				//valueInCents = valueInCents/temp;
				valueInCents = valueInCents - (numCoins*temp);
			}
			//has reached a 1-cent denomination, is divisible 
			if(temp == 1){
				
				rv = true;
			}
				
		 }
		
		if (valueInCents == 0){
			rv = true;
		}
		
		
		return rv;
	}
	
	//private helper to establish how many of each denomination are used
	//temp from getExactChange becomes coinValue
	private int numberOfCoins(int valueInCents, int coinValue){
		
		int numCoins = 0;
		int tempVal = valueInCents;
		
		if(coinValue == 1){
			numCoins += valueInCents;
		}
		
		else {
			
			while (valueInCents%coinValue > 0 && valueInCents > coinValue){
			
				tempVal = valueInCents/coinValue;
				numCoins += tempVal;
				valueInCents = valueInCents - (tempVal*coinValue);
			}
			
			while(valueInCents >= coinValue && coinValue != 1){
					
				tempVal = valueInCents/coinValue;
				numCoins += tempVal;
				valueInCents = valueInCents - (tempVal*coinValue);
			}
		}
	
		return numCoins;
			
	}

	//pre: canMakeExactChange(valueInCents)
	//post: calculateValueOfChangeList(rv) == valueInCents
	//post: i in [0, rv.size() - 1 ==> 
	//		rv.get(i + 1) * getDenomincations.get(i + 1) < getDenominations.get(i)5
	public List<Integer> getExactChange(int valueInCents) {
		
		assert valueInCents > 0: "Cents must be greater then zero";
		
		assert canMakeExactChange(valueInCents) : "Must be able to make change";
		
		List<Integer> denomTemp = new ArrayList<Integer>();
		denomTemp = this.getDenominations();
		
		List<Integer> rv = new ArrayList<Integer>();
		
		//operations with valueInCents and temp
		//numCoins uses numberOfCoins method to add to return list 
		//		and decrement ValueInCents
		for(int i = 0; i < denomTemp.size(); i++){
			
			int temp = denomTemp.get(i);
			int numCoins = numberOfCoins(valueInCents, temp);
			rv.add(numCoins);
			
			while (valueInCents%temp > 0 && valueInCents > temp){
				valueInCents = valueInCents - (numCoins*temp);
			}
			
			while(valueInCents >= temp && temp != 1){
				
				valueInCents = valueInCents - (numCoins*temp);
			}
				
		 }
			
		return rv;
	}

	//pre: changeList.size() == getDenominations().size()
	//pre: SIZE = changeList.size (NOTATION)
	//post: reverse getExactChange: start with list and return valueInCents-type int
	public int calculateValueOfChangeList(List<Integer> changeList) {
		
		assert changeList.size() > 0: "changeList must have elements";
	
		assert changeList.size() == getDenominations().size(): "changeList and theDenominations must be equal size";
		
		List<Integer> denomTemp = new ArrayList<Integer>();
		denomTemp = this.getDenominations();
		
		int changeCount = 0;
		
		//position-access within denomTemp and changeList used to multiply 
		//	and then add to changeCount
		for(int i = 0; i < denomTemp.size(); i++){
			
			int temp = denomTemp.get(i);
			int numCoins = changeList.get(i);
			
			changeCount += (temp * numCoins);
			
		}
		
		return changeCount;
	}
	

}
