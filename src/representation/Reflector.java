package representation;

import java.util.HashMap;

/***
 * Simple Model of the Enigma Reflector
 * @author Hardy Wijaya
 * 
 * The Reflector class specifies the [exactly] 13 pairings, as a total of 26 hash map entries
 * e.g A<->R will be represented as (<key A>, <val R>) and (<key R>, <val A>)
 */

public class Reflector {

	private HashMap<Character, Character> map;
	
	public Reflector(FixedMechanicReflector ref) {
		
		map = new HashMap<Character, Character>(26);
		
		String[] pairing = ref.getPairing();
		for(String pair : pairing) {
			Character key = pair.charAt(0);
			Character val = pair.charAt(1);
			map.put(key, val);
			map.put(val, key);
		}
		
	}
	
	
	public char reflect(char in) {
		return map.get(new Character(in));
				
	}
	
}
