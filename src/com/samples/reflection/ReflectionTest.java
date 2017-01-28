package com.samples.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
	
	public String testMethod() {
		return "testMethod() has been called by reflection.";
	}
	
	public static void main (String[] args){
		ReflectionTest reflect = new ReflectionTest();
// Link below discusses the use of the wildcard parameter with Class.forName().
// http://docs.oracle.com/javase/tutorial/java/generics/wildcards.html
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.samples.reflection.ReflectionTest");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method method = null;
		try {
			method = clazz.getDeclaredMethod("testMethod", (Class[])null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			Object result = method.invoke(reflect,(Object[])null);
			System.out.println("Reflection test: " + result);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
