package bigIntegerImplementation;

import java.util.ArrayList;
import java.util.Collections;

public class MyBigInt {
   
    ArrayList<Integer> digits;
    boolean isPositive;
    
    public MyBigInt(String number) {
	char firstChar = number.charAt(0);
	
	if (firstChar != '-') {
	    this.isPositive = true;
	}else this.isPositive = false;
	
	if (firstChar == '-') {
	    setBigNum(number.substring(1, number.length() - 1));
	}else {
	    setBigNum(number);
	}
    }
    
    public MyBigInt(boolean isPositiv, ArrayList<Integer> num) {
	this.isPositive = isPositiv;
	this.digits = num;
    }
    
    public MyBigInt add(MyBigInt number) {
	
	MyBigInt resultNum = null;
	
	boolean resultNumIsPositiv = true;
	boolean firstIsPositive = this.isPositive;
	boolean secondIsPositive = number.isPositive;
	
	/*
	 * find the bigger number by absolute value. Returns -1 if the first number has bigger 
	 * absolute value, else - returns 1
	 */
	
	int biggerNumber = findBiggerNum(this.digits, number.digits);
	
	// The new BigNumber will accept the sign of the bigger number;
	if (biggerNumber == -1) {
	    resultNumIsPositiv = firstIsPositive;
	}else {
	    resultNumIsPositiv = secondIsPositive;
	}
	
	/*
	 * Here we instantiate the new BigInteger result
	 */
	
	if (firstIsPositive && secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    sumPositivNumbers(this, number, biggerNumber));
	}
	
	if (!firstIsPositive && !secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    sumPositivNumbers(this, number, biggerNumber));
	}
	
	if (firstIsPositive && !secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    subtractPositivNumbers(this, number, biggerNumber));
	}
	
	if (!firstIsPositive && secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    subtractPositivNumbers(this, number, biggerNumber));
	}
	
	return resultNum;
	
    }
    
    public MyBigInt subtract(MyBigInt number) {
    	
	MyBigInt resultNum = null;
    	
    	boolean resultNumIsPositiv = true;
	boolean firstIsPositive = this.isPositive;
	boolean secondIsPositive = number.isPositive;
	
	/*
	 * find the bigger number by absolute value. Returns -1 if the first number has bigger 
	 * absolute value, else - returns 1
	 */
	
	int biggerNumber = findBiggerNum(this.digits, number.digits);
	
	// The new BigNumber will accept the sign of the bigger number;
	if (biggerNumber == -1) {
	    resultNumIsPositiv = firstIsPositive;
	}else {
	    resultNumIsPositiv = secondIsPositive;
	}
	
	/*
	 * Here we instantiate the new BigInteger result
	 */
	
	if (firstIsPositive && secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    subtractPositivNumbers(this, number, biggerNumber));
	}
	
	if (firstIsPositive && !secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    sumPositivNumbers(this, number, biggerNumber));
	}
	
	if (!firstIsPositive && secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    sumPositivNumbers(this, number, biggerNumber));
	}
	if (!firstIsPositive && !secondIsPositive) {
	    resultNum = new MyBigInt(resultNumIsPositiv, 
		    subtractPositivNumbers(this, number, biggerNumber));
	}
	
    	return resultNum;
    	
    }
    
    private ArrayList<Integer> sumPositivNumbers(MyBigInt firstNum, MyBigInt secondNum, int biggerNum) {
	
	ArrayList<Integer> result = new ArrayList<>();
	
	int numToAdd = 0;
        int sum = 0;

        int n = 0;
        
        if (biggerNum == -1) {
            n = firstNum.digits.size();
	}else {
	    n = secondNum.digits.size();
	}
	
        int first;
        int second;

        for (int i = 0; i < n; i++) 
        {

           if (i >= secondNum.digits.size())
           {
               first = firstNum.digits.size()-1-i;
               second = 0;
           }
           else if(i >= firstNum.digits.size()) 
           {
               first = 0;
               second = secondNum.digits.size()-1-i;
           }
           else 
           {
               second = secondNum.digits.size()-1-i;
               first = firstNum.digits.size()-1-i;
           }

           if ((first + second +numToAdd) >=10)
           {
               sum = (first + second + numToAdd) % 10;
               numToAdd = ((first+second+numToAdd) / 10) % 10;
           }
           else 
           {
               sum = first + second + numToAdd;
               numToAdd = 0;
           }

           result.add(sum);

        }
        
        if (numToAdd!=0)
        {
            result.add(sum);
        }
        
        Collections.reverse(result);

        while (result.get(result.get(0)) == 0)
        {
            result.remove(0);
            
        }  
	
	return result;
		
    }
    
    private ArrayList<Integer> subtractPositivNumbers(MyBigInt firstNum, MyBigInt secondNum, int biggerNum) {
   	ArrayList<Integer> resultList = new ArrayList<>();
   	
   	 int result = 0;
         int toRemember = 0;
         
         int firstNumDigit;
         int secondNumDigit;
         int n = 0;
         
         if (biggerNum == -1) {
             n = firstNum.digits.size();
 	}else {
 	     n = secondNum.digits.size();
 	}
         
         
         //Here we subtract the numbers` digits. 
         for (int i = n - 1; i >= 0; i--)          {
 			
         		if (i >= secondNum.digits.size())
         		{
         	            firstNumDigit = firstNum.digits.get(i) ;
         	            secondNumDigit = 0;
 	              
         		}
         		else if (i >= firstNum.digits.size()) 
         		{
         		   firstNumDigit = 0;
         		   secondNumDigit = secondNum.digits.get(i);
			}
         		else 
         		{
         		    firstNumDigit = firstNum.digits.get(i);
         		    secondNumDigit = secondNum.digits.get(i);
 	                
         		}
         	 
         	 
         	//Here we subtract the digits
         	 
         	 if (firstNumDigit - toRemember - secondNumDigit >= 0) 
         	 {
 			result = firstNumDigit - toRemember - secondNumDigit;
 			toRemember = 0;
         	 }
         	 else 
         	 {
 			result = firstNumDigit + 10 - toRemember - secondNumDigit;				
 			toRemember = 1; 				
         	 }
         	 resultList.add(result);
         	 
         	
 	}
         
         Collections.reverse(resultList);
         
         // Here we remove the zeroes from the beginning in the result 
         while (resultList.get(0)== 0)
         {
             resultList.remove(0);
         }
 		
   	
   	return resultList;
   		
       }
    
    private int findBiggerNum(ArrayList<Integer> num, ArrayList<Integer> num2) {
	
	if (num.size() > num2.size()) 
	{
	    return -1;
	}
	else if(num.size() < num2.size())
	{
	    return 1;
	}else {
	    
	    for (int i = 0; i < num.size(); i++) 
		{
			if (num.get(i) > num2.get(i)) 
			{
				return  -1;
			}
			else if(num.get(i) < num2.get(i)) 
			{
				return 1;
			}
		}
		
		return -1;
	}
    }
    
    private void setBigNum(String number) {
	
	for (int i = 0; i < number.length(); i++) {
	    if (!isPositive && i==0) {
		continue;
	    }
	    
	    this.digits.add(Character.getNumericValue(number.charAt(i)));
	}
	
    }
    
//    private void setPositive(String number) {
//
//	char firstChar = number.charAt(0);
//	
//	if (firstChar != '-') {
//	    this.isPositive = true;
//	}else this.isPositive = false;
//	
//    }
    
    @Override
    public String toString() {
	
        StringBuilder sb = new StringBuilder();
        
        for (int i = this.digits.size() - 1; i >= 0; i--) {
	    sb.append(this.digits.get(i));
	}
        
        return sb.toString();
    }
   
}
