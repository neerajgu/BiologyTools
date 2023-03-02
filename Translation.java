import java.util.HashMap;
import java.util.ArrayList;

public class Translation {
	private static HashMap<String, String> correspCodons = new HashMap<String, String>();
	public static void createMap() {
		// Simplifying system allowed me to code for only 31 of the 64 codons that exist
		// For the following parts of a codon, it doesn't matter what the third base is
		correspCodons.put("AC","Threonine");
		correspCodons.put("CG","Arginine");
		correspCodons.put("CC","Proline");
		correspCodons.put("CU","Leucine");
		correspCodons.put("UC","Serine");
		correspCodons.put("GG","Glycine");
		correspCodons.put("GC","Alanine");
		correspCodons.put("GU","Valine");
		// Other codons
		correspCodons.put("AGG","Arginine");
		correspCodons.put("AGA","Arginine");
		correspCodons.put("AG","Serine"); // All other AG's are Serine
		correspCodons.put("AAG","Lysine");
		correspCodons.put("AAA","Lysine");
		correspCodons.put("AA","Asparagine"); // All other AA's are Asparagine
		correspCodons.put("AUG","Methionine");
		correspCodons.put("AU", "Isoleucine"); // All other AU's are Isoleucine
		correspCodons.put("CAG", "Glutamine");
		correspCodons.put("CAA", "Glutamine");
		correspCodons.put("CA", "Histidine"); // All other CA's are Histidine
		correspCodons.put("UGG", "Tryptophan");
		correspCodons.put("UGA", "STOP");
		correspCodons.put("UG", "Cysteine"); // All other UG's are Cysteine
		correspCodons.put("UAG", "STOP");
		correspCodons.put("UAA", "STOP");
		correspCodons.put("UA", "Tyrosine"); // All other UA's are Tyrosine
		correspCodons.put("UUA", "Leucine");
		correspCodons.put("UUG", "Leucine");
		correspCodons.put("UU", "Phenylalanine"); // All other UU's are Phenylalanine
		correspCodons.put("GAG", "Glutamic Acid");
		correspCodons.put("GAA", "Glutamic Acid");
		correspCodons.put("GA", "Aspartic Acid"); // All other GA's are Aspartic Acid
	}
  	// make sure to use this specific function, because it complies with the shorthand version of the map
	public static String codonToAA(String codon) {
		createMap();
		if (correspCodons.get(codon) == null) {
			return correspCodons.get(codon.substring(0, 2));
		} else {
			return correspCodons.get(codon);
		}
	}
	public static ArrayList<String> createProtein(String mRNA) {
		ArrayList<String> polypeptide = new ArrayList<String>();
		for (int i = findStart(mRNA); i < mRNA.length() - 3; i+= 3) {
			if (codonToAA(mRNA.substring(i, i+3)).equals("STOP")) {
				break;
			} else {
				polypeptide.add(codonToAA(mRNA.substring(i, i+3)));
			}
		}
		return polypeptide;
	}
	public static int findStart(String mRNA) {
		int s = 0;
		for (int i = 0; i < mRNA.length(); i++) {
			if (mRNA.substring(i, i+3).equals("AUG")) {
				s = i;
				break;
			}
		}
		return s;
	}
}
