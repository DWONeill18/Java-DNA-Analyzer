package DNAAnalysis;

import java.util.List;

public class DNAReplication {

    public String replication(List<String> codons) {
        if (codons == null || codons.isEmpty()) {
            return "No codons available";
        }

        StringBuilder complementary = new StringBuilder();
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
                complementary.append(complementOf(base));
            }
        }

        if (complementary.length() == 0) {
            return "No codons available";
        }

        return complementary.toString();
    }

    private char complementOf(char base) {
        return switch (base) {
            case 'A' -> 'T';
            case 'T' -> 'A';
            case 'C' -> 'G';
            case 'G' -> 'C';
            default -> base;
        };
    }

    private boolean isValidBase(char base) {
        return base == 'A' || base == 'T' || base == 'C' || base == 'G';
    }
}
