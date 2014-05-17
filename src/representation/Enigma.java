package representation;

import utility.Utility;

public class Enigma {

	//Constants to define the rotors
	private static final int LEFT = 0;
	private static final int MID = 1;
	private static final int RIGHT = 2;

	
	private Rotor[] rotors = new Rotor[3];	
	private char[] rotorKnockers;
	
	private Reflector reflector;
	private Plugboard plugboard;
	private WindowSetting windowSetting;
	private RingSetting ringSetting;

	
	
	
	public Enigma(Rotor[] rots, Reflector ref, Plugboard plug, RingSetting rings, WindowSetting initWindow) {
	

		//Following WW2 Convention, it is specified Left-Mid-Right
		this.rotors = rots;
		this.rotorKnockers = new char[] {rotors[LEFT].getKnocker(), rotors[MID].getKnocker(), rotors[RIGHT].getKnocker() };
		
		this.reflector = ref;
		this.plugboard = plug;
		this.windowSetting = initWindow;
		this.ringSetting = rings;
		
		
	}
	
	private void tick() {
		windowSetting.tick(rotorKnockers);
		
	}
	

	public String encryptString(String input) {
		
		StringBuffer buf = new StringBuffer();
		char[] plain = input.trim().toUpperCase().toCharArray();
		
		for (int i=0; i < plain.length; i++) {
			char in = plain[i];	
			

			
			if (!Character.isWhitespace(in)) {

				if (!Character.isLetter(in)) {
					System.out.println("!!INVALID INPUT; NOT CHARACTER!!");
				}
				
				//Following WW2 Convention, rotors are specified Left-Mid-Right
				//But the actual encrypt/decrypt happens from Right->Mid->Left
				
				
				//result holds the intermediary values as well as the final output
				char result;
				
				
				/* ***********************
				 * PLUGBOARD TRANSLATION
				 * ***********************/
				
				result = plugboard.translate(in);
				
				
				
				/* ***********************
				 * ROTOR PROCESSING
				 * ***********************/
				
				// TICK ROTOR BEFORE ENCRYPTING
				tick();

				// variables we will keep using during rotor stages
				int offsetRotInternalAboveSea;	//always > 0, expressed in mod26
				int inputValueRotorInternal;	//always > 0, expressed in mod26
				
				
				for (int rot : new int[]{RIGHT, MID, LEFT}) {
					
					offsetRotInternalAboveSea = Utility.GetOffsetRotorInternal(ringSetting, windowSetting, rot);
					inputValueRotorInternal = ((result - 'A') - offsetRotInternalAboveSea + 26) % 26;
					
					char currentRot_internalInput = (char) ('A' + inputValueRotorInternal);	
					char currentRot_internalOutput = rotors[rot].process(currentRot_internalInput);
					char currentRot_sealevelOutput = (char) ('A' + 
							((currentRot_internalOutput - 'A') + offsetRotInternalAboveSea) % 26);
					result = currentRot_sealevelOutput;
					
				}
				

				/* ***********************
				 * REFLECTOR PROCESSING
				 * ***********************/
				
				result = reflector.reflect(result);

				
				/* ***********************
				 * ROTOR IN REVERSE
				 * ***********************/
				
				
				for (int rot : new int[]{LEFT, MID, RIGHT}) {
					
					offsetRotInternalAboveSea = Utility.GetOffsetRotorInternal(ringSetting, windowSetting, rot);
					inputValueRotorInternal = ((result - 'A') - offsetRotInternalAboveSea + 26) % 26;
					
					char currentRot_internalInput = (char) ('A' + inputValueRotorInternal);	
					char currentRot_internalOutput = rotors[rot].revProcess(currentRot_internalInput);
					char currentRot_sealevelOutput = (char) ('A' + 
							((currentRot_internalOutput - 'A') + offsetRotInternalAboveSea) % 26);
					result = currentRot_sealevelOutput;
					
				}
				
				
				
				
				/* ***********************
				 * PLUGBOARD TRANSLATION
				 * ***********************/
				
				char final_out = plugboard.translate(result);

				buf.append(final_out);

			}
			
		}
		
		
		return buf.toString();
		
	}
	
	
}
