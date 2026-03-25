import DNAAnalysis.DNAReplication;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link DNAAnalysis.DNAReplication}.
 */
@Tag("unit")
class DNAReplicationTest {

    /**
     * Verifies multiple codons are replicated into a single complementary string.
     */
    @Test
    void replication_multipleCodons_returnsComplementarySequence() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("ACG", "TGA", "CCT"));

        assertEquals("TGCACTGGA", result);
    }

    /**
     * Verifies a single codon is replicated correctly.
     */
    @Test
    void replication_singleCodon_returnsComplementarySequence() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("GTA"));

        assertEquals("CAT", result);
    }

    /**
     * Ensures an empty list returns the no-codons message.
     */
    @Test
    void replication_emptyList_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of());

        assertEquals("No codons available", result);
    }

    /**
     * Ensures null input returns the no-codons message.
     */
    @Test
    void replication_nullInput_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(null);

        assertEquals("No codons available", result);
    }

    /**
     * Ensures lowercase codons are normalized before replication.
     */
    @Test
    void replication_lowercaseCodons_areNormalized() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("acg", "tga"));

        assertEquals("TGCACT", result);
    }

    /**
     * Ensures blank codons do not produce output and return the no-codons message.
     */
    @Test
    void replication_blankCodons_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("   ", ""));

        assertEquals("No codons available", result);
    }

    /**
     * Ensures invalid bases trigger an {@link IllegalArgumentException}.
     */
    @Test
    void replication_invalidBase_throwsErrorMessage() {
        DNAReplication replication = new DNAReplication();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> replication.replication(List.of("ACX"))
        );

        assertEquals("DNA contains invalid characters", ex.getMessage());
    }
}
