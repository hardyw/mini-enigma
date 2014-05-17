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

	private int left;
	private int mid;
	private int right;
	
	public RingSetting(int l, int m, int r) {
		this.left = l;
		this.mid = m; 
		this.right = r;
	}
	
	//If the ring setting is E (or 05), then offset from 'A' is +4
	public int leftOffset() {
		return this.left - 1;
	}
	public int midOffset() {
		return this.mid - 1;
	}
	public int rightOffset() {
		return this.right - 1;
	}
	
}
