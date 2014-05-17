package representation;
/***
 * Simple Model of the Enigma Rotor
 * @author Hardy Wijaya
 * 
 * The Rotor class specifies the permutation (and consequently also the reverse permutation), as well
 * as the 'knocker' i.e. the position where carry to the left-side rotor happens
 * For example, if a rotor has knocker at 'D', then during its C to D transition, its left neighbour also ticks once
 * Illustration: AAA, AAB, AAC, ABD
 *
 * Note that the Ring Setting, as well as Window Setting, are specified separately by the RingSetting and WindowSetting classes
 */
public class Rotor {

	private char[] permutation;
	private char[] reverse;
	private char knocker;
	
	public Rotor(FixedMechanicRotor rot) {
		this.permutation = rot.getPermutation().toUpperCase().toCharArray();
		this.knocker = rot.getKnocker();
		
		this.reverse = new char[26];
		
		for(int i=0; i < permutation.length; i++) {
			int revIndex = permutation[i] - 'A';
			reverse[revIndex] = (char) ('A' + i);
		}
		
	}
	
	
	public char process(char in) {
		
		if (!(in >= 'A' && in <= 'Z')) {
			System.out.println("In ROTOR invalid input!!!");
		}
		
		int distance = (int) (in - 'A');
		return permutation[distance];
	}
	
	public char revProcess(char in) {
		
		if (!(in >= 'A' && in <= 'Z')) {
			System.out.println("In ROTOR invalid input!!!");
		}
		
		int distance = (int) (in - 'A');
		return reverse[distance];
	}
	
	
	public char getKnocker() {
		return knocker;
	}
	
}
