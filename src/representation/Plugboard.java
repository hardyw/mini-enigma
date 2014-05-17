package representation;
import java.util.HashMap;

/***
 * Simple Model of the Enigma Plugboard
 * @author Hardy Wijaya
 * 
 * The Plugboard class specifies the pairings
 * This class has a hashmap to hold the plugged pairs 
 * e.g A<->R will be represented as (<key A>, <val R>) and (<key R>, <val A>)
 */

public class Plugboard {

	private HashMap<Character, Character> map;
	
	
	public Plugboard(String[] pairing) {
	
		//pairing is of format {"AV", "BS", "CG", "DL", "FU", "HZ", "IN", "KM", "OW", "RX"}
		
		//Number of plug pairs are generally less than 26, but we allow 26
		map = new HashMap<Character, Character>(26);
		
		for(String pair : pairing) {
			Character key = pair.charAt(0);
			Character val = pair.charAt(1);
			map.put(key, val);
			map.put(val, key);
		}
		
	}
	
	
	/*
	 * If input character is among the swapped pairs, return its paired partner
	 * Otherwise, just return the input itself
	 */
	public char translate(char in) {
		
		Character key = new Character(in);
		
		if (map.containsKey(key)) {
			return map.get(key);
		}
		else {
			return in;
		}
		
	}

}
