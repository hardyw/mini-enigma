package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import representation.RingSetting;
import representation.WindowSetting;


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
	
	/**
	 * Get the appropriate offset (always > 0) of the internal rotor core
	 * with respect to global 'A' position
	 * This offset is influenced by 
	 * 	i) the ring setting (which stays the same for a given ciphertext, and 
	 * 	ii) the window value (which updates every time the enigma ticks)
	 * @param ringSetting
	 * @param windowSetting
	 * @param slot
	 * @return
	 */
	public static int GetOffsetRotorInternal(RingSetting ringSetting, WindowSetting windowSetting, int slot) {
		
		//for easy intuition, we call the global 'A' position the sea level
		
		int dispRingAboveSea;			//displacement of Ring with respect to global 'A' position
		int dispRotorInternalWrtRing;
		int dispRotorInternalAboveSea;	//always > 0, expressed in mod26
		
		dispRingAboveSea = 'A' - windowSetting.getWindowValue(slot); 	//if window shows 'C', disp = -2
		dispRotorInternalWrtRing = ringSetting.ringOffset(slot); 		//offset = 4 means internal A is locked to ring's E
		dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;			
		
		
		
		return dispRotorInternalAboveSea;
	}
	
	
}
