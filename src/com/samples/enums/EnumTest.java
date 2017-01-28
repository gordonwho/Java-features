package com.samples.enums;
import java.util.EnumSet;

import com.samples.enums.Seasons;

public class EnumTest {
	public enum Months {JAN(1), FEB(2), MAR(3), APR(4), MAY(5), JUNz(6), JUL(7), AUG(8), 
		SEPT(9), OCT(10), NOV(11), DEC(12); //; required here
		
		private int order;
		Months (int i){
		this.order = i;
	}
		public int getOrder(){
			return order;
		}
		
	}; //; is optional here

	public static void main (String[] args){
		System.out.println("Test of for loop using values()");
		for (Seasons s: Seasons.values()){
			System.out.println(s);
		}	

		System.out.println("Test of for loop using EnumSet.range()");
		for (Seasons s: EnumSet.range(Seasons.SPRING, Seasons.AUTUMN)){
			System.out.println(s);
		}	

		System.out.println("Access test- Months " + Months.JAN);
		System.out.println("Access test- Seasons " + Seasons.SPRING);
		System.out.println("Constructor test- Months, get numerical value of MAY: " + Months.MAY.getOrder());
		System.out.println("EnumMap test- get season for January " + Seasons.getSeasonOf(Months.JAN));
	}
}
