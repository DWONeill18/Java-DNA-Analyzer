import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link DNAMutation}.
 */
@Tag("unit")
class DNAMutationTest {

    /**
     * Verifies mutation inserts a randomly generated base string into the codon list.
     */
    @Test
    void mutate_insertsRandomBasesIntoCodonList() {
        List<String> codons = List.of("ACG", "TGA", "CCT");
        int baseCount = 6;

        DNAMutation mutation = new DNAMutation();
        List<String> result = mutation.mutate(codons, baseCount);

        assertEquals(codons.size() + 1, result.size());
        assertTrue(containsElementOfLengthWithBases(result, baseCount));
        assertTrue(isSubsequence(codons, result));
    }

    /**
     * Verifies invalid mutation sizes (not multiples of 3) throw a helpful exception.
     */
    @Test
    void mutate_invalidBaseCount_throwsExceptionWithMessage() {
        List<String> codons = List.of("ACG", "TGA", "CCT");
        int baseCount = 4;

        DNAMutation mutation = new DNAMutation();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> mutation.mutate(codons, baseCount)
        );

        assertEquals("Mutation size must be divisible by 3", ex.getMessage());
    }

    /**
     * Verifies null input produces a list containing only the mutation.
     */
    @Test
    void mutate_nullCodons_returnsSingleMutation() {
        DNAMutation mutation = new DNAMutation();
        List<String> result = mutation.mutate(null, 3);

        assertEquals(1, result.size());
        assertTrue(containsElementOfLengthWithBases(result, 3));
    }

    private boolean containsElementOfLengthWithBases(List<String> result, int length) {
        // Detects whether the mutation string exists without relying on its random value.
        for (String item : result) {
            if (item.length() == length && item.matches("[ACGT]+")) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubsequence(List<String> original, List<String> mutated) {
        // Ensures the original codons remain in order after insertion.
        int i = 0;
        for (String item : mutated) {
            if (i < original.size() && original.get(i).equals(item)) {
                i++;
            }
        }
        return i == original.size();
    }
}
