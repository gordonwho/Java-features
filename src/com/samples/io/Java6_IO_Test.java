package com.samples.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
public class Java6_IO_Test {
	
	public void testIO_byteCopy() {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(
					new FileInputStream("files/input.jpg"));
			bos = new BufferedOutputStream(
					new FileOutputStream("files/input_copy.jpg"));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		

		int aByte = -1;
		try {
			while ((aByte = bis.read()) != -1) {
				bos.write(aByte);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	public void testIO_dataCopy() {
		final double[] nums = { 99.2, 34.00, 9.99, 15.6321, 4.23, 100.12345 };
		DataOutputStream dos = null, copyDos = null;
		DataInputStream dis = null;
		try {
			// create data file
			dos = new DataOutputStream(
					new BufferedOutputStream(
					   new FileOutputStream("files/data.dat")));
			for (double d : nums) {
				dos.writeDouble(d);
			}
			
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			
			// copy datafile
			dis = new DataInputStream(
					new BufferedInputStream(
					   new FileInputStream("files/data.dat")));
			copyDos = new DataOutputStream(
					new BufferedOutputStream(
					   new FileOutputStream("files/data_copy.dat")));
			
			//The below loop will throw an EOFException as the normal part of its function.
			//You can't use a double to control a loop, so you need to use
			//the exception to stop the loop.
			try {
				while (true) {
					double d = dis.readDouble();
					System.out.println(d);
					copyDos.writeDouble(d);
				}
			} catch (EOFException eof) {
				; // do nothing
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			if (dis != null) {
				try {
					dis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			
			if (copyDos != null) {
				try {
					copyDos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	

	}
	
	public void testIO_lineCopy(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(
					new FileReader("files/text_input.txt"));
			bw = new BufferedWriter(
					new FileWriter("files/text_copy.txt"));

			String line = null;
			while ((line =  br.readLine()) != null ){
				System.out.println(line.length());
				System.out.println(line);
				//line.separator: platform specific line separator
			    bw.write(line+ System.getProperty("line.separator"));
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main (String[] args){
		Java6_IO_Test iot = new Java6_IO_Test();
		//iot.testIO_byteCopy();
		//iot.testIO_dataCopy();
		iot.testIO_lineCopy();
		
		
		
	}

}
