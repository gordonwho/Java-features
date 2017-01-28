//This is an implementation of a static holder singleton using an inner class.
//The singleton instance is created when the class is loaded. (eager instantiation)
package com.samples.inner_classes.singleton;

public class StaticHolderSingleton {
	
private StaticHolderSingleton() {}

private static class StaticHolder{
	private static final StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
}

public static StaticHolderSingleton getInstance(){
	return StaticHolder.INSTANCE;
}

}
