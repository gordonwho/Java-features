package com.samples.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

//http://tutorials.jenkov.com/java-exception-handling/try-with-resources.html

public class Java7_IO_Test {
	
	public void testIO_directByteCopy(){
		Path path = FileSystems.getDefault().getPath(".", "files","input.jpg");
		Path target = FileSystems.getDefault().getPath(".", "files","input_copy.jpg");
		try (InputStream is = Files.newInputStream(path)) {
			Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}//try-with-resources closes resources automatically. No
		//need to close resources inside a finally clause.
	}
	
	public void testIO_bufByteCopy(){
		Path path = FileSystems.getDefault().getPath(".", "files","input.jpg");
		Path target = FileSystems.getDefault().getPath(".", "files","input_copy.jpg");
		try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
			//Calling newOutputStream(path) with no output options, will create or overwrite target file.
			BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(target))
			){			
			int aByte = -1;
			while ((aByte = bis.read()) != -1){
				bos.write(aByte);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}//try-with-resources closes resources automatically. No
		//need to close resources inside a finally clause.
	}
	
	public void testIO_byteCopy_one_line(){
		Path path = FileSystems.getDefault().getPath(".", "files","input.jpg");
		Path target = FileSystems.getDefault().getPath(".", "files","input_copy.jpg");

		try {
			//No openoptions param means file will be created or overwritten.
			Files.write(target, Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}//The Files methods close the source and target files when reading and writing is done.
		
		
	}

	public void testIO_bufCharCopy(){

		try (BufferedReader br = new BufferedReader(new FileReader("files/text_input.txt"));
			 BufferedWriter bw = new BufferedWriter(new FileWriter("files/text_copy2.txt"))
			){
			System.out.println("Copying text file.");
			
			int ch = 0;
			//read() returns characters as integers, presumably to allow
			//control of the while loop. Therefore, you need to cast back
			//to char for the write.
			while ((ch = br.read()) != -1){
				bw.write((char)ch);
				System.out.print((char)ch);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}//try-with-resources closes resources automatically. No
	//need to close resources inside a finally clause.
	
	public void testIO_lineCopy(){
		Path path = FileSystems.getDefault().getPath(".", "files","text_input.txt");
		Path target = FileSystems.getDefault().getPath(".", "files","text_copy.txt");
		
		try {
			List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
			// No openoptions param means file will be created or overwritten.
			Files.write(target, lines, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}//The Files methods close the source and target files when reading and writing is done.
		
	}
	
	//Adds the numbers for each line and writes the result to the target file.
		public void testIO_bufLineCopy(){
			Path path = FileSystems.getDefault().getPath(".", "files","text_floats.txt");
			Path target = FileSystems.getDefault().getPath(".", "files","text_sums.txt");
			
			// No openoptions argument means file will be created or overwritten.
			try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset());
			      BufferedWriter bw = Files.newBufferedWriter(target, Charset.defaultCharset())
				){
				
				System.out.println("Summing each line of floats.");
				String line = null;
				while ((line = br.readLine()) != null){
					Scanner sc = new Scanner(line);
					sc.useDelimiter(" +");
					float f = 0;
					while(sc.hasNext()){
						f += sc.nextFloat();
					}
					System.out.println(f);
					bw.write(String.valueOf(f));
					//Checks if buffer is empty. Suppresses subsequent
					//newLine() on last string.
					if (br.ready())
						bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} //try-with-resources closes resources automatically. No
			//need to close resources inside a finally clause.
		
	}
	
	public static void main (String[] args){
		Java7_IO_Test ioTest = new Java7_IO_Test();
		ioTest.testIO_directByteCopy();
		ioTest.testIO_bufByteCopy();
		ioTest.testIO_byteCopy_one_line();
		ioTest.testIO_lineCopy();
		ioTest.testIO_bufLineCopy();
		ioTest.testIO_bufCharCopy();
	}

}
