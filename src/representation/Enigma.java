package representation;

public class Enigma {

	private Rotor rotL;
	private Rotor rotM;
	private Rotor rotR;
	
	private char[] rotorKnockers;
	
	private Reflector reflector;
	private Plugboard plugboard;
	private WindowSetting windowSetting;
	private RingSetting ringSetting;

	
	public Enigma(Rotor[] rotors, Reflector ref, Plugboard plug, RingSetting rings, WindowSetting initWindow) {
	

		//Following WW2 Convention, it is Left-Mid-Right
		this.rotL = rotors[0];
		this.rotM = rotors[1];
		this.rotR = rotors[2];
		this.rotorKnockers = new char[] {rotL.getKnocker(), rotM.getKnocker(), rotR.getKnocker() };
		
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

				if (!Character.isLetter(in)) { //if input is not between 'A' and 'Z'
					System.out.println("!!INVALID INPUT; NOT CHARACTER!!");
				}
				
				//Following WW2 Convention, rotors are specified Left-Mid-Right
				//But the actual encrypt/decrypt happens from Right->Mid->Left
				
				
				
				/* ***********************
				 * PLUGBOARD TRANSLATION
				 * ***********************/
				
				char plug_out = plugboard.translate(in);
				
				
				
				/* ***********************
				 * ROTOR PROCESSING
				 * ***********************/
				
				// TICK ROTOR BEFORE ENCRYPTING
				tick();

				// These are a few variables we will keep using during rotor stages
				int dispRingAboveSea;			//displacement of Ring with respect to global 'A' position
				int dispRotorInternalWrtRing;
				int dispRotorInternalAboveSea;	//always > 0, expressed in mod26
				int inputValueRotorInternal;	//always > 0, expressed in mod26
				
				// RIGHT ROTOR
				dispRingAboveSea = 'A' - windowSetting.getWindowValueRight(); //if window shows 'C', disp = -2
				dispRotorInternalWrtRing = ringSetting.rightOffset(); //offset = 4 means internal A is locked to ring's E
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;			
				inputValueRotorInternal = ((plug_out - 'A') - dispRotorInternalAboveSea + 26) % 26;
				
				char rotR_internalInput = (char) ('A' + inputValueRotorInternal);			
				char rotR_internalOutput = rotR.process(rotR_internalInput);
				char rotR_sealevelOutput = (char) ('A' + 
										((rotR_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				// MIDDLE ROTOR
				dispRingAboveSea = 'A' - windowSetting.getWindowValueMid();
				dispRotorInternalWrtRing = ringSetting.midOffset();
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;			
				inputValueRotorInternal = ((rotR_sealevelOutput - 'A') - dispRotorInternalAboveSea + 26) % 26;
				
				char rotM_internalInput = (char) ('A' + inputValueRotorInternal);
				char rotM_internalOutput = rotM.process(rotM_internalInput);
				char rotM_sealevelOutput = (char) ('A' + 
										((rotM_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				// LEFT ROTOR
				dispRingAboveSea = 'A' - windowSetting.getWindowValueLeft();
				dispRotorInternalWrtRing = ringSetting.leftOffset();
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;
				inputValueRotorInternal = ((rotM_sealevelOutput - 'A') - dispRotorInternalAboveSea + 26) % 26;
				
				char rotL_internalInput = (char) ('A' + inputValueRotorInternal);
				char rotL_internalOutput = rotL.process(rotL_internalInput);
				char rotL_sealevelOutput = (char) ('A' + 
						((rotL_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				
				

				/* ***********************
				 * REFLECTOR PROCESSING
				 * ***********************/
				
				char ref_out = reflector.reflect(rotL_sealevelOutput);

				
				/* ***********************
				 * ROTOR IN REVERSE
				 * ***********************/
				
				// LEFT ROTOR REVERSE
				dispRingAboveSea = 'A' - windowSetting.getWindowValueLeft();
				dispRotorInternalWrtRing = ringSetting.leftOffset();
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;
				inputValueRotorInternal = ((ref_out - 'A') - dispRotorInternalAboveSea + 26) % 26;
				char rev_rotL_internalInput = (char) ('A' + inputValueRotorInternal);
				char rev_rotL_internalOutput = rotL.revProcess(rev_rotL_internalInput);
				char rev_rotL_sealevelOutput = (char) ('A' + 
						((rev_rotL_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				// MIDDLE ROTOR REVERSE
				dispRingAboveSea = 'A' - windowSetting.getWindowValueMid();
				dispRotorInternalWrtRing = ringSetting.midOffset();
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;
				inputValueRotorInternal = ((rev_rotL_sealevelOutput - 'A') - dispRotorInternalAboveSea + 26) % 26;
				char rev_rotM_internalInput = (char) ('A' + inputValueRotorInternal);
				char rev_rotM_internalOutput = rotM.revProcess(rev_rotM_internalInput);
				char rev_rotM_sealevelOutput = (char) ('A' + 
						((rev_rotM_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				
				// RIGHT ROTOR REVERSE
				dispRingAboveSea = 'A' - windowSetting.getWindowValueRight();
				dispRotorInternalWrtRing = ringSetting.rightOffset();
				dispRotorInternalAboveSea = (dispRingAboveSea + dispRotorInternalWrtRing + 26) % 26;
				inputValueRotorInternal = ((rev_rotM_sealevelOutput - 'A') - dispRotorInternalAboveSea + 26) % 26;
				char rev_rotR_internalInput = (char) ('A' + inputValueRotorInternal);
				char rev_rotR_internalOutput = rotR.revProcess(rev_rotR_internalInput);
				char rev_rotR_sealevelOutput = (char) ('A' + 
						((rev_rotR_internalOutput - 'A') + dispRotorInternalAboveSea) % 26);
				
				
				/* ***********************
				 * PLUGBOARD TRANSLATION
				 * ***********************/
				
				char final_out = plugboard.translate(rev_rotR_sealevelOutput);

				buf.append(final_out);

			}
			
		}
		
		
		return buf.toString();
		
	}
	
	
}
