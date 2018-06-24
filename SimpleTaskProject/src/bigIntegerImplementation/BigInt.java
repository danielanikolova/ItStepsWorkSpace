package bigIntegerImplementation;

import java.util.ArrayList;
import java.util.Collections;

public class BigInt {

    ArrayList<Integer> digits = new ArrayList<>();
    boolean isPositive;

    public BigInt(String number) {
	char firstChar = number.charAt(0);

	if (firstChar != '-') {
	    this.isPositive = true;
	} else {
	    this.isPositive = false;
	}

	if (firstChar == '-') {
	    setBigNum(number.substring(1, number.length()));
	} else {
	    setBigNum(number);
	}
    }

    public BigInt(boolean isPositiv, ArrayList<Integer> num) {
	this.isPositive = isPositiv;
	this.digits = num;
    }

    public BigInt add(BigInt number) {

	BigInt resultNum = null;

	boolean resultNumIsPositiv = true;
	boolean firstIsPositive = this.isPositive;
	boolean secondIsPositive = number.isPositive;

	/*
	 * find the bigger number by absolute value. Returns -1 if the first number has
	 * bigger absolute value, else - returns 1
	 */

	int biggerNumber = findBiggerNum(this.digits, number.digits);

	// The new BigNumber will accept the sign of the bigger number;
	if (biggerNumber == -1) 
	{
	    resultNumIsPositiv = firstIsPositive;
	} 
	else if (biggerNumber == 1)
	{
	    resultNumIsPositiv = secondIsPositive;
	}
	/*
	 * If the numbers are equal by absolute value and have different sign the new 
	 * BigInteger will be zero
	 */
	else {
	    if (firstIsPositive != secondIsPositive) {
		ArrayList<Integer> result = new ArrayList<>();
		result.add(0);
		return resultNum = new BigInt(resultNumIsPositiv,result);
	    }
	}

	/*
	 * Here we instantiate the new BigInteger result
	 */

	if (firstIsPositive && secondIsPositive) 
	{
	    return resultNum = new BigInt(resultNumIsPositiv, sumPositivNumbers(this, number, biggerNumber));
	}

	if (firstIsPositive && !secondIsPositive) 
	{
	    if (biggerNumber == 1) 
	    {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(number, this));
	    }else {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(this, number));
	    }
	}

	if (!firstIsPositive && secondIsPositive) 
	{
	    if (biggerNumber == 1) 
	    {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(number, this));
	    }else {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(this, number));
	    }
	}

	if (!firstIsPositive && !secondIsPositive) 
	{
	    return resultNum = new BigInt(resultNumIsPositiv, sumPositivNumbers(this, number, biggerNumber));
	}

	return resultNum;
	
	
    }

    public BigInt subtract(BigInt number) {

	BigInt resultNum = null;

	boolean resultNumIsPositiv = true;
	boolean firstIsPositive = this.isPositive;
	boolean secondIsPositive = number.isPositive;

	/*
	 * Finds the bigger number by absolute value. Returns -1 if the first number has
	 * bigger absolute value,if the second number is bigger - returns 1, else - returns 0
	 */

	int biggerNumber = findBiggerNum(this.digits, number.digits);

	/*
	 * If the number from witch we subtract the second number is bigger by absolute
	 * value the new BigInteger will accept its sign
	 */

	if (biggerNumber == -1) {
	    resultNumIsPositiv = firstIsPositive;
	}
	/*
	 * If the second number is bigger by absolute value the new BigInteger will
	 * accept the opposite sign of the second number
	 */
	else if (biggerNumber == 1){
	    resultNumIsPositiv = !secondIsPositive;
	}
	/*
	 * If the numbers are equal by absolute value and sign the new 
	 * BigInteger will be zero
	 */
	else {
	    if (firstIsPositive == secondIsPositive) {
		ArrayList<Integer> result = new ArrayList<>();
		result.add(0);
		return resultNum = new BigInt(resultNumIsPositiv,result);
	    }
	}

	/*
	 * Here we instantiate the new BigInteger result
	 */

	if (firstIsPositive && secondIsPositive) {
	    if (biggerNumber == 1) {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(number, this));
	    }else {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(this, number));
	    }
	    
	}

	if (firstIsPositive && !secondIsPositive) {
	    resultNum = new BigInt(resultNumIsPositiv, sumPositivNumbers(this, number, biggerNumber));
	}

	if (!firstIsPositive && secondIsPositive) {
	    if (biggerNumber == 0) {
		resultNumIsPositiv = false;
	    }
	    resultNum = new BigInt(resultNumIsPositiv, sumPositivNumbers(this, number, biggerNumber));
	}
	
	if (!firstIsPositive && !secondIsPositive) {
	    
	    if (biggerNumber == 1) {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(number, this));
	    }else {
		resultNum = new BigInt(resultNumIsPositiv, subtractPositivNumbers(this, number));
	    }
	}

	return resultNum;

    }

    private ArrayList<Integer> sumPositivNumbers(BigInt firstNum, BigInt secondNum, int biggerNum) {

	ArrayList<Integer> result = new ArrayList<>();

	int numToAdd = 0;
	int sum = 0;

	int n = 0;

	if (biggerNum == -1) {
	    n = firstNum.digits.size();
	} else {
	    n = secondNum.digits.size();
	}

	int first;
	int second;

	for (int i = 0; i < n; i++) {

	    if (i >= secondNum.digits.size()) {
		first = firstNum.digits.get(firstNum.digits.size() - 1 - i);
		second = 0;
	    } else if (i >= firstNum.digits.size()) {
		first = 0;
		second = secondNum.digits.get(secondNum.digits.size() - 1 - i);
	    } else {
		second = secondNum.digits.get(secondNum.digits.size() - 1 - i);
		first = firstNum.digits.get(firstNum.digits.size() - 1 - i);
	    }
	    
	    if ((first + second + numToAdd) >= 10) {
		sum = (first + second + numToAdd) % 10;
		numToAdd = ((first + second + numToAdd) / 10) % 10;
	    } else {
		sum = first + second + numToAdd;
		numToAdd = 0;
	    }

	    result.add(sum);

	}

	if (numToAdd != 0) {
	    result.add(sum);
	}

	Collections.reverse(result);

	while (result.get(0) == 0) {
	    
	    if (result.size() == 1) {
		break;
	    }
	    result.remove(0);
	}
	return result;

    }

    private ArrayList<Integer> subtractPositivNumbers(BigInt firstNum, BigInt secondNum) {
	ArrayList<Integer> resultList = new ArrayList<>();

	int result = 0;
	int toRemember = 0;

	int firstNumDigit;
	int secondNumDigit;
	int n = firstNum.digits.size();

	// Here we subtract the numbers` digits.
	for (int i = 0; i < n; i++) {

	    if (i >= secondNum.digits.size()) {
		firstNumDigit = firstNum.digits.get(firstNum.digits.size() - 1 - i);
		secondNumDigit = 0;
	    } else if (i >= firstNum.digits.size()) {
		firstNumDigit = 0;
		secondNumDigit = secondNum.digits.get(secondNum.digits.size() - 1 - i);
	    } else {
		secondNumDigit = secondNum.digits.get(secondNum.digits.size() - 1 - i);
		firstNumDigit = firstNum.digits.get(firstNum.digits.size() - 1 - i);
	    }

	    // Here we subtract the digits
  
	    if (firstNumDigit - toRemember - secondNumDigit >= 0) {
		result = firstNumDigit - toRemember - secondNumDigit;
		toRemember = 0;
	    } else {
		result = firstNumDigit + 10 - toRemember - secondNumDigit;
		toRemember = 1;
	    }
	    resultList.add(result);

	}

	Collections.reverse(resultList);

	// Here we remove the zeroes from the beginning in the result
	while (resultList.get(0) == 0) 
	{
	    if (resultList.size() == 1) 
	    {
		break;
	    }
	    
	    resultList.remove(0);
	}

	return resultList;

    }

    private int findBiggerNum(ArrayList<Integer> num, ArrayList<Integer> num2) {

	if (num.size() > num2.size()) {
	    return -1;
	} else if (num.size() < num2.size()) {
	    return 1;
	} else {

	    for (int i = 0; i < num.size(); i++) {
		if (num.get(i) > num2.get(i)) {
		    return -1;
		} else if (num.get(i) < num2.get(i)) {
		    return 1;
		}
	    }

	    return 0;
	}
    }

    private void setBigNum(String number) {

	for (int i = 0; i < number.length(); i++) 
	{	    
	    this.digits.add(Character.getNumericValue(number.charAt(i)));
	}

    }

    @Override
    public String toString() {

	StringBuilder sb = new StringBuilder();

	if (!isPositive) {
	    sb.append("-");
	}

	for (int i = 0; i < this.digits.size(); i++) {
	    sb.append(this.digits.get(i));
	}

	return sb.toString();
    }

}
