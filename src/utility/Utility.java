package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Utility {

	
	public static String FileToString(String filePath) {
		
		File file = new File(filePath);
		StringBuffer buf = new StringBuffer();
		try {
			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream(file);
			int content;
			while((content = fis.read()) != -1) {
				buf.append((char)content);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = buf.toString();
		return result;
	}
	
}
