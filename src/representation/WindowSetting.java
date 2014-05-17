package representation;
/**
 * The WindowSetting class models the "window" in the Enigma Machine
 * @author ToshiA
 *
 * Because my implementation of rotor advancement uses the window setting as 
 * a reference point, the tick() function is delegated to this class
 * 
 * The ticking of the enigma is similar (but *not* identical) to an odometer ticking
 * Details of mechanical movements from http://users.telenet.be/d.rijmenants/en/enigmatech.htm
 *
 * NOTE: REFACTOR AND CUSTOM IMPLEMENT windows[X]++ IF HAVE TIME
 */
public class WindowSetting {

	private static final int LEFT = 0;
	private static final int MID = 1;
	private static final int RIGHT = 2;
	
	//The windows display what the Enigma operator sees
	private char[] windows = new char[3];
	
	//To model the mechanical idiosyncrasy, so-called double-stepping
	private boolean[] justStepped = {false, false, false};
	
	
	public WindowSetting(char l, char m, char r) {
	
		this.windows[LEFT] = l;
		this.windows[MID] = m;
		this.windows[RIGHT] = r;
		
	}
	
	
	public char getWindowValue(int slot) {
		return windows[slot];
	}
	

	
	
	public void tick(char[] knockers) {
		
		//System.out.println("window is " + windows[LEFT] + windows[MID] + windows[RIGHT]);
		
		
		boolean carryToMid = false;
		boolean carryToLeft = false;
		
		
		/* ***********************
		 * TICK RIGHT ROTOR
		 * ***********************/
		
		windows[RIGHT] = (char) ('A' + ((windows[RIGHT] - 'A' + 1) % 26));
		if (windows[RIGHT] == knockers[RIGHT]) {
			carryToMid = true;
		}
				
		
		/* ***********************
		 * TICK MID ROTOR (if any)
		 * ***********************/
		
		if (carryToMid) {		
			windows[MID] = (char) ('A' + ((windows[MID] - 'A' + 1) % 26));
			if (windows[MID] == knockers[MID]) carryToLeft = true;
			justStepped[MID] = true; 
		}
		else if (justStepped[MID]) {
			
			//has the potential to double-step
			//Double stepping occurs if MID's nextValue = knockers[MID]
			
			justStepped[MID] = false;
			char nextValue = (char) ('A' + ((windows[MID] - 'A' + 1) % 26));
			if (nextValue == knockers[MID]) {
				windows[MID] = (char) ('A' + ((windows[MID] - 'A' + 1) % 26));
				if (windows[MID] == knockers[MID]) carryToLeft = true;
			}
		}
		
		/* ***********************
		 * TICK LEFT ROTOR (if any)
		 * ***********************/
		if (carryToLeft) {		
			windows[LEFT] = (char) ('A' + ((windows[LEFT] - 'A' + 1) % 26));
			//justStepped[LEFT] = true; //TODO confirm whether left can also double-step 
		}
		else if (justStepped[LEFT]) {
			justStepped[LEFT] = false;
			windows[LEFT] = (char) ('A' + ((windows[LEFT] - 'A' + 1) % 26));
		}

		
	}
	
}
