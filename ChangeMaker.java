package change;

import java.util.List;

public interface ChangeMaker 
{
	//post: i in [rv.size() - 1 ==> rv.get(i) > rv.get(i +1)
	//		From L(eft) to R(ight), int at given position must be > int at R
	//		Return list of ints, coinDenoms
	public List<Integer> getDenominations();
	
	
	//pre: getDenominations() list
	//		valueInCents > 0
	//post: rv returns true if change can be made
	public boolean canMakeExactChange(int valueInCents);
	
	//pre: canMakeExactChange(valueInCents)
	//post: calculateValueOfChangeList(rv) == valueInCents
	//post: i in [0, rv.size() - 1 ==> 
	//		rv.get(i + 1) * getDenomincations.get(i + 1) < getDenominations.get(i)
	public List<Integer> getExactChange(int valueInCents);
	
	
	//pre: changeList.size() == getDenominations().size()
	//pre: SIZE = changeList.size (NOTATION)
	//post: reverse getExactChange: start with list and return valueInCents-type int
	public int calculateValueOfChangeList(List<Integer> changeList);
	
	
}
