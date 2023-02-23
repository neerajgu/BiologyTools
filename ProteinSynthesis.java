// Importing useful data structures
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class ProteinSynthesis {
	// Initializing relevant variables
	private static String DNA = "";
	private static String mRNA = "";
	private static HashMap<String, String> correspCodons = new HashMap<String, String>();
	private static ArrayList<String> introns = new ArrayList<String>();
	private static ArrayList<String> aminoAcids = new ArrayList<String>();
	// Creates a coding system for codon to amino acid
	static void createMap() {
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
	// Creates immature mRNA strand
	static void transcription() { // creating the mRNA strand
		for (int i = 0; i < DNA.length(); i++) {
			switch (DNA.charAt(i)) { // each DNA base corresponds to mRNA base
				case 'A': mRNA += 'U'; break;
				case 'T': mRNA += 'A'; break;
				case 'G': mRNA += 'C'; break;
				case 'C': mRNA += 'G'; break;
			}
		}
	}
	// Creates mature mRNA strand
	static void removeIntrons() {
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
	}
	// Forms protein
	static void createProtein() {
		// finding the start codon
		int startIndex = 0;
		for (int i = 0; i < mRNA.length(); i++) {
			if (mRNA.substring(i, i+3).equals("AUG")) {
				startIndex = i;
				break;
			}
		}
		// getting each individual codon
		for (int i = startIndex; i < mRNA.length() - 3; i+= 3) {
			if (codonToAA(mRNA.substring(i, i+3)).equals("STOP")) {
				break;
			} else {
				aminoAcids.add(codonToAA(mRNA.substring(i, i+3)));
			}
		}
	}
	// Specific to simplified coding system
	static String codonToAA(String codon) {
		if (correspCodons.get(codon) == null) {
			return correspCodons.get(codon.substring(0, 2));
		} else {
			return correspCodons.get(codon);
		}
	}
	public static void main(String[] args) {
		createMap();
		Scanner scan = new Scanner(System.in);
		DNA = scan.next(); // reading the DNA
		transcription(); // creating the mRNA strand
		while (scan.hasNext()) {
			introns.add(scan.next()); // reading introns
		}
		System.out.println("mRNA Strand (Intron and Exons): " + mRNA);
		removeIntrons();
		System.out.println("mRNA Strand (Exons Only): " + mRNA);
		createProtein();
		System.out.print("Polypeptide: " + aminoAcids);
		scan.close();
	}
}