package DNAAnalysis;

import java.util.List;

/**
 * Replicates DNA codons into their complementary bases.
 */
public class DNAReplication {

    /**
     * Builds a complementary DNA string from a list of codons.
     *
     * @param codons list of codon strings; may be {@code null} or contain blanks
     * @return the complementary DNA string, or {@code "No codons available"} when no bases are provided
     * @throws IllegalArgumentException when any codon contains invalid DNA bases
     */
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

    /**
     * Maps a single base to its complementary base.
     *
     * @param base the DNA base to convert
     * @return the complementary base
     * @throws IllegalArgumentException when the base is not A, C, G, or T
     */
    private char complementOf(char base) {
        return switch (base) {
            case 'A' -> 'T';
            case 'T' -> 'A';
            case 'C' -> 'G';
            case 'G' -> 'C';
            default -> throw new IllegalArgumentException("DNA contains invalid characters");
        };
    }

    /**
     * Checks whether a character is a valid DNA base.
     *
     * @param base character to validate
     * @return {@code true} when the base is A, C, G, or T
     */
    private boolean isValidBase(char base) {
        return base == 'A' || base == 'T' || base == 'C' || base == 'G';
    }
}
