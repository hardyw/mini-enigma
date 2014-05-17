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
					"SU"	}),
	
	//Less is published about Reflector-A, 
	//Below based on paper by P Marks and F Weierud
	REFLECTOR_A (new String[] {
					"AE", 
					"BJ",
					"CM",
					"DZ",
					"FL",
					"GY",
					"HX",
					"IV",
					"KW",
					"NR",
					"OQ",
					"PU",
					"ST"	});

	private String[] pairing;
	
	FixedMechanicReflector(String[] pairs) {
		this.pairing = pairs;
	}
	
	public String[] getPairing() {
		return pairing;
	}
	
}
