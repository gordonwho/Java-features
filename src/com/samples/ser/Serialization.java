package com.samples.ser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Serialization {
	
	public static void main (String[] args){
		Map<String, String> testObj = new HashMap<>();
		testObj.put("First","Line 1");
		testObj.put("Second", "Line 2");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/testfile.dat"));
			oos.writeObject(testObj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} //finally clause not necessary b/c resources auto-close in Java 7
		
		//Read obj in from file
		try {
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/testfile.dat"));
			@SuppressWarnings("unchecked")
			Map<String,String> newTestObj = (HashMap<String,String>)ois.readObject();
			for (Map.Entry<String,String>line : newTestObj.entrySet() ){
				System.out.println(line.getKey() + " " + line.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} //finally clause not necessary b/c resources auto-close in Java 7
		
		
	}

}
