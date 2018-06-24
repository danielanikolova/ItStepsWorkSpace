package bigIntegerImplementation;


public class MyBigInteger {
	
	
	private String bigNum;
	
	
	public MyBigInteger(String number) {
		this.bigNum = number;
	}

	/*
	 * This method finds the larger number and performs 
	 * the operation of summing the numbers according to the sign in front of the big number
	 */
	public String sumBigNumbers(MyBigInteger mySecondNum)
	{
		String firstNum = this.bigNum;
		String secondNum = mySecondNum.getBigNum();
		
		String result = null;
		
		if (findTheBiggerNum(firstNum, secondNum) == 1) 
		{
			String temp = firstNum;
			firstNum = secondNum;
			secondNum = temp;
					
		}
		
		if (firstNum.charAt(0) != '-') 
		{
			if (secondNum.charAt(0) != '-') 
			{
				result = sum(firstNum, secondNum);
			}
			else 
			{
				result = subtract(firstNum, secondNum.substring(1, secondNum.length()));
			}
		}
		else 
		{
			if (secondNum.charAt(0) != '-') 
			{
				result = "-" + subtract(firstNum.substring(1, firstNum.length()), secondNum);
			}
			else 
			{
				
				result = "-" + sum(firstNum.substring(1, firstNum.length()), secondNum.substring(1, secondNum.length()));
			}
		}
				
		return result;
		
	}
	
	/*
	 * This method finds the larger number and performs 
	 * the operation of subtracting the numbers according to the sign in front of the big number
	 */
	public String subtractBigNumbers(MyBigInteger mySecondNum)
	{
		String firstNum = this.bigNum;
		String secondNum = mySecondNum.getBigNum();
		
		String result = null;
		int  chekBigger  = findTheBiggerNum(firstNum, secondNum);
		
		if (firstNum.equalsIgnoreCase(secondNum)) 
		{
			return "0";
		}		
		
		
		if (firstNum.charAt(0) != '-') 
		{
			if (secondNum.charAt(0) != '-') 
			{
				if (chekBigger == 1) {
					result = "-" + subtract(secondNum, firstNum);
				}else {
					result = subtract(firstNum, secondNum);
				}
								
			}
			else
			{
			    if (chekBigger == 1) {
				result = sum(secondNum.substring(1, secondNum.length()), firstNum);
			    }else {
				result = sum( firstNum, secondNum.substring(1, secondNum.length()));
			    }
				
				
			}
		}
		else 
		{
			if (secondNum.charAt(0) != '-') 
			{
			    if (chekBigger == 1) {
				result = "-" + sum( secondNum, firstNum.substring(1, firstNum.length()) );
			    }else {
				result = "-" + sum(firstNum.substring(1, firstNum.length()), secondNum );
			    }
				
				
			}
			else 
			{
				if (chekBigger == 1) {
					result = subtract(secondNum.substring(1, secondNum.length()), firstNum.substring(1, firstNum.length()));
				}else {
					result =  "-" + subtract(firstNum.substring(1, firstNum.length()), secondNum.substring(1, secondNum.length()));
				}
				
				
			}
		}
		
	
		return result;
		
	}
	
	private static int findTheBiggerNum(String firtsNum, String secNum)
	{
		if (firtsNum.charAt(0) == '-') 
		{
			firtsNum = firtsNum.substring(1, firtsNum.length());
		}
		
		if (secNum.charAt(0) == '-') 
		{
			secNum = secNum.substring(1, secNum.length());
		}
		
		if (firtsNum.length()> secNum.length()) 
		{
			return -1;
		}
		else if (firtsNum.length()< secNum.length())
		{
			return 1;
		}
		else {
			
			for (int i = 0; i < firtsNum.length(); i++) 
			{
				if (firtsNum.charAt(i)>secNum.charAt(i)) 
				{
					return  -1;
				}
				else if(firtsNum.charAt(i)<secNum.charAt(i)) 
				{
					return 1;
				}
			}
			
			return -1;
		}
		
		
		
	}
	
	/*
	 * This method sums the numbers digit by digit
	 */
	private static String sum(String biggerNum, String number)
	{
		StringBuilder sb = new StringBuilder();
		int numToAdd = 0;
        int sum;

        int n = biggerNum.length();
       
        int first;
        int second;

        for (int i = 0; i < n; i++) 
        {

           if (i >= number.length())
           {
               first = Integer.parseInt(String.valueOf(biggerNum.charAt(biggerNum.length()-1-i)));
               second = 0;
           }
           else 
           {
               second = Integer.parseInt(String.valueOf(number.charAt(number.length()-1-i)));
               first = Integer.parseInt(String.valueOf(biggerNum.charAt(biggerNum.length()-1-i)));
           }

           if ((first+second +numToAdd) >=10)
           {
               sum = (first+second+numToAdd)%10;
               numToAdd = ((first+second+numToAdd)/10)%10;
           }
           else 
           {
               sum = first+second+numToAdd;
               numToAdd=0;
           }

           sb.append(String.valueOf(sum));

        }
        
        if (numToAdd!=0)
        {
            sb.append(String.valueOf(numToAdd));

        }

        StringBuilder result = sb.reverse();

        while (sb.charAt(0)=='0')
        {
            sb.deleteCharAt(0);
        }    

	return result.toString();
		
	}
	
	/*
	 * This method subtracts the numbers digit by digit
	 */
	private static String subtract(String firstNum, String secondNum) {
		
		StringBuilder sb = new StringBuilder();
        int result = 0;
        int toRemember = 0;
        
        int first;
        int second;
        
        
        //Here we subtract the numbers` digits. 
        for (int i = 0; i < firstNum.length(); i++) 
        {
			
        		if (i >= secondNum.length())
        		{
        	               first = Integer.parseInt(String.valueOf(firstNum.charAt(firstNum.length()-1-i)));
        	               second = 0;
	              
        		}
        		else 
        		{
	               second = Integer.parseInt(String.valueOf(secondNum.charAt(secondNum.length()-1-i)));
	               first = Integer.parseInt(String.valueOf(firstNum.charAt(firstNum.length()-1-i)));
        		}
        	 
        	 
        	//Here we subtract the digits
        	 
        	 if (first - toRemember - second >= 0) 
        	 {
				result = first - toRemember - second;
				toRemember = 0;
			}
        	 else 
        	 {
				result = first + 10 - toRemember - second;				
				toRemember = 1;
				
			}

        	 sb.append(String.valueOf(result));
        	
		}
        
        sb.reverse();
        
        // Here we remove the zeroes from the beginning in the result 
        while (sb.charAt(0)=='0')
        {
            sb.deleteCharAt(0);
        }
		
		return sb.toString();
	}
	
	public String getBigNum() {
		return this.bigNum;
	}
	
	
	
}
