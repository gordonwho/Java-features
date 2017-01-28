package com.samples.inner_classes.singleton;

public class Runner {
	public static void main(String[] args) {
		StaticHolderSingleton singleton = StaticHolderSingleton.getInstance();
		StaticHolderSingleton singleton2 = StaticHolderSingleton.getInstance();
		System.out.println("Are the two instances the same class? " + (singleton == singleton2) );
		
		//can you reach the holder instance from the outside? No.
		//System.out.println("Reach inner class instance" + InnerClass_static.StaticHolder.INSTANCE);

	}

}
