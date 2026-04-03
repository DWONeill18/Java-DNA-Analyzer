package DNAAnalysis;

import java.util.List;

/**
 * Transcribes DNA codons into their RNA complements.
 */
public class DNATranscription {

    /**
     * Builds an mRNA sequence by transcribing the provided DNA codons.
     *
     * @param codons list of codon strings; must not be {@code null} or empty
     * @return the transcribed RNA sequence
     * @throws IllegalArgumentException when codons are missing, unusable, or contain invalid bases
     */
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

        StringBuilder sb = buildTranscription(codons, totalLength);

        return sb.toString();
    }

    /**
     * Creates the transcribed sequence using a pre-sized buffer.
     *
     * @param codons      list of codons to transcribe
     * @param totalLength expected output length for preallocation
     * @return a populated {@link StringBuilder} containing the transcription
     * @throws IllegalArgumentException when no usable codons are present or bases are invalid
     */
    private StringBuilder buildTranscription(List<String> codons, int totalLength) {
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

    /**
     * Maps a DNA base to its RNA complement.
     *
     * @param base DNA base to convert
     * @return the RNA complement of the DNA base
     * @throws IllegalArgumentException when the base is not A, C, G, or T
     */
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
