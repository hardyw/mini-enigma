package main;

import representation.*;
import utility.Utility;

public class Main {

	public static void main(String[] args) {
		
		
		/* OPERATION BARBAROSSA - use this to verify our implementation
		 * Plain text
		 *  AUFKL XABTE ILUNG XVONX KURTI NOWAX KURTI NOWAX NORDW ESTLX SEBEZ XSEBE ZXUAF FLIEG ERSTR ASZER IQTUN GXDUB 
		    ROWKI XDUBR OWKIX OPOTS CHKAX OPOTS CHKAX UMXEI NSAQT DREIN ULLXU HRANG ETRET ENXAN GRIFF XINFX RGTX
		 * Cipher text
		 *  EDPUD NRGYS ZRCXN UYTPO MRMBO FKTBZ REZKM LXLVE FGUEY SIOZV EQMIK UBPMM YLKLT TDEIS MDICA GYKUA CTCDO MOHWX 
		 *  MUUIA UBSTS LRNBZ SZWNR FXWFY SSXJZ VIJHI DISHP RKLKA YUPAD TXQSP INQMA TLPIF SVKDA SCTAC DPBOP VHJK
		 * Settings
		 * 	Message Key: BLA
			Order: II, IV, V
			Ring positions: 02 21 12
			Reflector: B
			Plug pairs: AV BS CG DL FU HZ IN KM OW RX
		 */

		
		Rotor rL = new Rotor(FixedMechanicRotor.ROTOR_II); 	//II has notch F
		Rotor rM = new Rotor(FixedMechanicRotor.ROTOR_IV);	//IV has notch K
		Rotor rR = new Rotor(FixedMechanicRotor.ROTOR_V);	//V  has notch A
		
		
		//Following WW2 Convention, it is Left-Mid-Right e.g. II IV V
		Rotor[] rotors = {rL, rM, rR};
	
		Reflector ref = new Reflector(FixedMechanicReflector.REFLECTOR_B);
		Plugboard plug = new Plugboard(new String[] {
				"AV", "BS", "CG", "DL", "FU", "HZ", "IN", "KM", "OW", "RX"}); //Barbarosa
		WindowSetting initialSetting = new WindowSetting('B', 'L', 'A');
		RingSetting ringPositions = new RingSetting(2, 21, 12);
		
		Enigma enigma = new Enigma(rotors, ref, plug, ringPositions, initialSetting);
		
		
		//Read in the cipher text file
		String in = Utility.FileToString("Resources/BarbarosaCiphertext.txt");
		
		//Let Enigma does its thing
		String result = enigma.encryptString(in);
		
		
		System.out.println(result);

		
	}

}
