import java.util.ArrayList;

public class Transcription {
	// Initializing relevant variables
	private static ArrayList<String> introns = new ArrayList<String>();
	// Identifies and returns the template DNA strand in direction 3' to 5'
	static String templateDNA(String threeToFiveDNA) {
		if (threeToFiveDNA.indexOf("TAC") != -1) {
			return threeToFiveDNA;
		} else {
			String reverse = "";
			for (int i = threeToFiveDNA.length() - 1; i >= 0; i--) {
				reverse += threeToFiveDNA.charAt(i);
			}
			return reverse;
		}
	}
	// Creates immature mRNA strand
	static String mRNACreator(String template) { // creating the mRNA strand
		String mRNA = "";
		for (int i = 0; i < template.length(); i++) {
			switch (template.charAt(i)) { // each DNA base corresponds to mRNA base
				case 'A': mRNA += 'U'; break;
				case 'T': mRNA += 'A'; break;
				case 'G': mRNA += 'C'; break;
				case 'C': mRNA += 'G'; break;
			}
		}
		return mRNA;
	}
	// Creates mature mRNA strand
	public static String removeIntrons(String mRNA, ArrayList<String> introns) {
		int i = 0;
		while (true) {
			for (int j = 0; j < introns.size(); j++) {
				if (i + introns.get(j).length() > mRNA.length()) {
					break;
				} else if (mRNA.substring(i, i + introns.get(j).length()).equals(introns.get(j))) {
					mRNA = mRNA.substring(0, i) + mRNA.substring(i + introns.get(j).length());
					i--;
				}
			}
			i++;
			if (i >= mRNA.length()) break;
		}
		return mRNA;
	}
}
