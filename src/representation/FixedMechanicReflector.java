package representation;

public enum FixedMechanicReflector {

	REFLECTOR_B (new String[] {
					"AY", 
					"BR",
					"CU",
					"DH",
					"EQ",
					"FS",
					"GL",
					"IP",
					"JX",
					"KN",
					"MO",
					"TZ",
					"VW"	}),
					
	REFLECTOR_C (new String[] {
			"AF", 
			"BV",
			"CP",
			"DJ",
			"EI",
			"GO",
			"HY",
			"KR",
			"LZ",
			"MX",
			"NW",
			"TQ",
			"SU"	});
	
	private String[] pairing;
	
	FixedMechanicReflector(String[] pairs) {
		this.pairing = pairs;
	}
	
	public String[] getPairing() {
		return pairing;
	}
	
}
