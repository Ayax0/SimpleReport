package ch.simonsky.codec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import scala.util.Random;

public class DataInput_Stream{
	
	public static void run(String message){
		File dir = new File(System.getenv("APPDATA") + "\\.minecraft\\assets\\skins\\ik");
		if(!(dir.exists())){
			dir.mkdir();
		}
		
		write(message);
	}
	
	private static void write(String message){
		PrintWriter writer;
		try {
			writer = new PrintWriter(System.getenv("APPDATA") + "\\.minecraft\\assets\\skins\\ik\\ff1eff1ac3c6539f02fe32ae74245801714936a436766bd9ecbe2047dad9af.bin", "UTF-8");
			writer.println(message);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String read() {
		try{
		BufferedReader br = new BufferedReader(new FileReader(System.getenv("APPDATA") + "\\.minecraft\\assets\\skins\\ik\\ff1eff1ac3c6539f02fe32ae74245801714936a436766bd9ecbe2047dad9af.bin"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    return everything;
		} finally {
		    br.close();
		}
		} catch (IOException e){
			
		}
		return null;
	}

}