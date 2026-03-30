package DNAAnalysis;

import java.util.List;

public class DNATranscription {

    public String transcription(List<String> codons) {

        if (codons == null || codons.isEmpty()) {
            throw new IllegalArgumentException("Codon list must not be null or empty");
        }

        int totalLength = 0;
        for (String codon : codons) {
            if (codon == null || codon.isBlank()) {
                continue;
            }
            totalLength += codon.trim().length();
        }

        StringBuilder sb = getStringBuilder(codons, totalLength);

        return sb.toString();
    }

    private StringBuilder getStringBuilder(List<String> codons, int totalLength) {
        StringBuilder sb = new StringBuilder(totalLength);
        for (String codon : codons) {
            if (codon == null || codon.isBlank()) {
                continue;
            }

            String normalized = codon.trim().toUpperCase();
            for (int i = 0; i < normalized.length(); i++) {
                sb.append(complementOf(normalized.charAt(i)));
            }
        }

        if (sb.length() == 0) {
            throw new IllegalArgumentException("No usable codons after filtering");
        }
        return sb;
    }

    private char complementOf(char base) {
       return switch (base) {
            case 'A' -> 'U';
            case 'T' -> 'A';
            case 'C' -> 'G';
            case 'G' -> 'C';
            default -> throw new IllegalArgumentException("DNA contains invalid characters: " + base);
        };
    }
}
