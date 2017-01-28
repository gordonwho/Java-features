package com.samples.enums;

//constant specific methods
enum MathOperationsEnum {
	MOD {double evaluateOp(double x, double y) {return x%y;}},
	POWER {double evaluateOp(double x, double y) {return java.lang.Math.pow(x,y);}};

	abstract double evaluateOp(double x, double y);
}

enum MathOperationsEnum2 {
	MOD, POWER;
	
	double evaluateOp(double x, double y){
		switch(this){
		case MOD:return x%y;
		case POWER:return java.lang.Math.pow(x,y);
		}
		//You could return 0 here which would suppress the error.
		//Throwing the exception does just was well.
		//This should never be reached was the operations are defined by the enum.
		//You could also add the default case in the switch statement.
		throw new AssertionError("Operation not recognized: " + this);
	}
}

			
			
public class MathOperations{
	
	public static void main (String[] args) {
		System.out.println("Constant specific methods version");
		System.out.println("5 MOD 3 " + MathOperationsEnum.MOD.evaluateOp(5d, 3d));
		System.out.println("2 MOD 3 " + MathOperationsEnum.MOD.evaluateOp(2d, 3d));
		System.out.println("2 POWER 2 " + MathOperationsEnum.POWER.evaluateOp(2d, 2d));
		
		System.out.println("Switch version");
		System.out.println("1 MOD 3 " + MathOperationsEnum2.MOD.evaluateOp(1d, 3d));
		System.out.println("7 MOD 3 " + MathOperationsEnum2.MOD.evaluateOp(7d, 3d));
		System.out.println("2 POWER 3 " + MathOperationsEnum2.POWER.evaluateOp(2d, 3d));
	}
}
