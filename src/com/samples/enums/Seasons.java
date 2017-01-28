package com.samples.enums;

import java.util.EnumMap;
import java.util.Map;
import com.samples.enums.EnumTest.Months; //enum is an inner class

enum Seasons {
	SPRING, SUMMER, AUTUMN, WINTER;
	
	private static Map<Months, Seasons>  seasonsTable = new EnumMap<Months, Seasons>(Months.class);
	static{
		for (Months m : Months.values()){
			switch ((m.getOrder()/4) % 4 + 1){ //the crazy math is because Java doesn't have case statement with ranges.
			case 1: seasonsTable.put(m, Seasons.SPRING);
			break;
			case 2:seasonsTable.put(m, Seasons.SUMMER);
			break;
			case 3:seasonsTable.put(m, Seasons.AUTUMN);
			break;
			case 4:seasonsTable.put(m, Seasons.WINTER);
			}
		}

	}

	public static Seasons getSeasonOf(Months m){
		return seasonsTable.get(m);
	}
}
