package stringMath;

import java.util.Scanner;

public class StringMath {

	String n = "1500";
	//String k = "21";
	int conv = -48; //the character number ID to integer value conversion
	
	public static void main(String[] a){
		StringMath classObj = new StringMath();
		classObj.go();
	}//main
	
	private void go(){
		long startTime = System.currentTimeMillis();
		
		System.out.println(factStringIntMulting(n));
		//System.out.println(factStringIntAdding(n));
		//System.out.println(addStringInts(n, k));
		//System.out.println(multStringInts(n, k));
		//System.out.println(addXTimes(n, k));

		System.out.println("\nTime (ms): " + (System.currentTimeMillis() - startTime));
	}//go
	
	private String addStringInts(String x, String y){
		String sum = "";

		int iters = 0;
		if(x.length() > y.length()){
			iters = x.length();
		}else{
			iters = y.length();
		}//assign iters
		
		int xNums[] = new int[iters + 1];
		int yNums[] = new int[iters + 1];
		
		for(int i = iters; i >= 0; i --){
			if((x.length()-1 - (iters - i)) >= 0){
				xNums[i] = x.charAt(x.length()-1 - (iters - i)) + conv;
			}//fill the arrays
			if((y.length()-1 - (iters - i)) >= 0){
				yNums[i] = y.charAt(y.length()-1 - (iters - i)) + conv;
			}//fill the arrays
		}//for

		int sumNums[] = new int[iters + 1];//the sum of numbers array
		
		int carry = 0;
		for(int i = iters; i >= 0; i --){//adds all the numbers assigning new values to the sumNums array
			if((yNums[i] + xNums[i] + carry) < 10){
				sumNums[i] = (yNums[i] + xNums[i] + carry);
				carry = 0;
			}else{
				sumNums[i] = ((yNums[i] + xNums[i] + carry) - 10);
				carry = 1;
			}//if else
		}//add them
		
		int newLength = sumNums.length - zerosToErase(sumNums);
		int[] realSumNums = new int[newLength];
		
		
		for(int i = 1; i <= newLength; i ++){
			realSumNums[newLength - i] = sumNums[sumNums.length - i]; 
		}
		
		for(int i = 0; i < newLength; i ++){
			sum = sum + realSumNums[i];
		}//for each number value, put them together
		
		return sum;
	}//addStringInts
	
	private String addXTimes(String x, String y){
		String subTotal = "0";
		for(int i = 0; i < Integer.parseInt(y); i ++){
			subTotal =  addStringInts(subTotal, x);
		}
		return subTotal;
	}
	
	private String multStringInts(String x, String y){
		String prod = "";

		int iters = (x.length()) + (y.length()) - 1;

		int xNums[] = new int[iters + 1];
		int yNums[] = new int[iters + 1];
		
		for(int i = iters; i >= 0; i --){
			if((x.length()-1 - (iters - i)) >= 0){
				xNums[i] = x.charAt(x.length()-1 - (iters - i)) + conv;
			}//fill the arrays
			if((y.length()-1 - (iters - i)) >= 0){
				yNums[i] = y.charAt(y.length()-1 - (iters - i)) + conv;
			}//fill the arrays
		}//for

		/*
		//PRINTS THEM, JUST FOR TESTING:
		for(int i = 0; i <= iters; i ++){
			System.out.print("" + xNums[i]);
		}//for each number value, print them
		System.out.println("");
		for(int i = 0; i <= iters; i ++){
			System.out.print("" + yNums[i]);
		}//for each number value, print them
		System.out.println("\n");
		*/
		
		int prodNums[] = new int[iters + 1];//the sum of numbers array

		int carry = 0;
		int subTotal = 0;
		for(int i = iters; i >= 0; i--){
			subTotal = subTotal + carry;
			for(int j = iters; j >= i; j --){
				subTotal = subTotal + (xNums[j] * yNums[i + (iters - j)]);
			}//for j
			if(subTotal < 10){
				prodNums[i] = subTotal;
				carry = 0;
			}else{
				prodNums[i] = subTotal % 10;
				carry = ((subTotal) - (subTotal % 10))/10;
			}
			subTotal = 0;
		}//for i
		
		int newLength = prodNums.length - zerosToErase(prodNums);
		int[] realProdNums = new int[newLength];
		
		
		for(int i = 1; i <= newLength; i ++){
			realProdNums[newLength - i] = prodNums[prodNums.length - i]; 
		}
		
		
		for(int i = 0; i < newLength; i ++){
			prod = prod + realProdNums[i];
		}//for each number value, put them together
		
		return prod;
	}//multStringInts
	
	private int zerosToErase(int[] a){
		int breakpoint = 0;
		for(int i = 0; i < a.length; i ++){
			if(a[i] == 0){
				breakpoint = breakpoint + 1;
			}else{
				return breakpoint;
			}
		}
		return breakpoint;
	}//zerosToErase
	
	private String factStringIntMulting(String x){
		String xFact = "1";
		int xValue = Integer.parseInt(x);
		/*
		for(int i = x.length()-1; i >= 0; i --){
			xValue = xValue + ((x.charAt(i) + conv) * (int) Math.pow(10, (x.length()-1) - i));
		}//assign value of string
		*/
		
		for(int i = xValue; i > 1; i --){
			xFact = multStringInts(xFact, Integer.toString(i));
		}
		
		return xFact;
	}//factStringInt
	
	private String factStringIntAdding(String x){
		String xFact = "1";
		int xValue = Integer.parseInt(x);
		
		for(int i = xValue; i > 1; i --){
			xFact = addXTimes(xFact, Integer.toString(i));
		}
		
		return xFact;
	}//factStringInt
}//class
