import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DNA mutation behavior.
 */
@Tag("unit")
class DNAMutationTest {

    /**
     * Verifies mutation inserts a randomly generated base string into the codon list.
     */
    @Test
    void mutate_insertsRandomBasesIntoCodonList() throws Exception {
        List<String> codons = List.of("ACG", "TGA", "CCT");
        int baseCount = 6;

        List<String> result = (List<String>) invokeMutate(codons, baseCount);

        assertEquals(codons.size() + 1, result.size());
        assertTrue(result.contains("ACG"));
        assertTrue(result.contains("TGA"));
        assertTrue(result.contains("CCT"));
    }

    /**
     * Verifies invalid mutation sizes (not multiples of 3) throw a helpful exception.
     */
    @Test
    void mutate_invalidBaseCount_throwsExceptionWithMessage() throws Exception {
        List<String> codons = List.of("ACG", "TGA", "CCT");
        int baseCount = 4;

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> invokeMutate(codons, baseCount)
        );

        assertEquals("Number of bases added must be a multiple of 3",  ex.getMessage());
    }
}
