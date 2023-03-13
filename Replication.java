public class Replication {
  static String replicate(String DNA) {
    String repDNA = "";
    for (int i = 0; i < DNA.length(); i++) {
      switch(DNA.charAt(i)) {
        case 'A': repDNA += 'T'; break;
				case 'T': repDNA += 'A'; break;
				case 'G': repDNA += 'C'; break;
				case 'C': repDNA += 'G'; break;
      }
    }
    return repDNA;
  }
  static String reverse(String s) {
    String rev = "";
    for (int i = s.length() - 1; i >= 0; i--) {
      rev += s.charAt(i);
    }
    return rev;
  }
}
