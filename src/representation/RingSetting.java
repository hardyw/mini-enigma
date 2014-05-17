package representation;

/***
 * Simple Model of the Ring Setting
 * @author Hardy Wijaya
 * 
 * Following WW2 German convention, a setting of 05 (or E) means that 'E' on the ring is 
 * aligned with the rotor's internal 'A'
 * 
 * This means that the offset is one less than the ring setting (e.g. offset of 'E' from 'A' is +4)
 * For details on Enigma ring settings, see http://users.telenet.be/d.rijmenants/en/enigmatech.htm
 * 
 */

public class RingSetting {

	private static final int LEFT = 0;
	private static final int MID = 1;
	private static final int RIGHT = 2;
	
	private int[] rings = new int[3];

	
	public RingSetting(int l, int m, int r) {
		
		this.rings[LEFT] = l;
		this.rings[MID] = m;
		this.rings[RIGHT] = r;
		
	}
	
	//If the ring setting is E (or 05), then offset from 'A' is +4
	public int ringOffset(int slot) {
		return rings[slot] - 1;
	}
	
	
	

	
}
