package DNAAnalysis;

import java.util.List;

public class DNATranscription {

    public String transcription(List<String> codons) {

        if (codons == null || codons.isEmpty()) {
            return "No codons available";
        }

        StringBuilder sb = new StringBuilder();
        for (String codon : codons) {
            if (codon == null || codon.isBlank()) {
                continue;
            }

            String normalized = codon.trim().toUpperCase();
            for (int i = 0; i < normalized.length(); i++) {
                char base = normalized.charAt(i);
                if (!isValidBase(base)) {
                    throw new IllegalArgumentException("DNA contains invalid characters");
                }
                sb.append(complementOf(base));
            }
        }

        if (sb.length() == 0) {
            return "No codons available";
        }

        return sb.toString();
    }

    private char complementOf(char base) {
       return switch (base) {
            case 'A' -> 'U';
            case 'T' -> 'A';
            case 'C' -> 'G';
            case 'G' -> 'C';
            default -> throw new IllegalArgumentException("DNA contains invalid characters");
        };
    }

    private boolean isValidBase(char base) {
        return base == 'A' || base == 'C' || base == 'G' || base == 'T';
    }

}
