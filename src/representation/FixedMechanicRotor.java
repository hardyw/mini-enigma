package representation;


//Technical specifications obtained from
//http://www.codesandciphers.org.uk/enigma/rotorspec.htm


public enum FixedMechanicRotor {

	ROTOR_I ("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'R'),
	ROTOR_II ("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'F'),
	ROTOR_III ("BDFHJLCPRTXVZNYEIWGAKMUSQO",'W'),
	ROTOR_IV ("ESOVPZJAYQUIRHXLNFTGKDCMWB",'K'),
	ROTOR_V ("VZBRGITYUPSDNHLXAWMJQOFECK",'A');
	
	private String permutation;
	private char knocker;
	
	
	FixedMechanicRotor(String perm, char knock){
		this.permutation = perm;
		this.knocker = knock;
	}
	
	public String getPermutation() {
		return permutation;
	}
	public char getKnocker() {
		return knocker;
	}
	
	
}
