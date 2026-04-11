import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Inserts a randomly generated base sequence into a codon list.
 */
public class DNAMutation {

    private static final char[] BASES = {'A', 'C', 'G', 'T'};
    private final SecureRandom random = new SecureRandom();

    /**
     * Generates a random base sequence of the requested length and inserts it into the codon list.
     *
     * @param codons    list of codons to mutate
     * @param baseCount number of random bases to insert; must be a multiple of 3
     * @return a new list containing the mutation
     * @throws IllegalArgumentException when {@code baseCount} is not a multiple of 3
     */
    public List<String> mutate(List<String> codons, int baseCount) {
        if (baseCount % 3 != 0) {
            throw new IllegalArgumentException("Mutation size must be a multiple of 3");
        }

        List<String> result = codons == null ? new ArrayList<>() : new ArrayList<>(codons);
        String mutation = randomBases(baseCount);
        int insertIndex = result.isEmpty() ? 0 : random.nextInt(result.size() + 1);
        result.add(insertIndex, mutation);
        return List.copyOf(result);
    }

    private String randomBases(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(BASES[random.nextInt(BASES.length)]);
        }
        return builder.toString();
    }
}
