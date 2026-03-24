import DNAAnalysis.DNAReplication;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("unit")
class DNAReplicationTest {

    @Test
    void replication_multipleCodons_returnsComplementarySequence() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("ACG", "TGA", "CCT"));

        assertEquals("TGCACTGGA", result);
    }

    @Test
    void replication_singleCodon_returnsComplementarySequence() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("GTA"));

        assertEquals("CAT", result);
    }

    @Test
    void replication_emptyList_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of());

        assertEquals("No codons available", result);
    }

    @Test
    void replication_nullInput_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(null);

        assertEquals("No codons available", result);
    }

    @Test
    void replication_lowercaseCodons_areNormalized() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("acg", "tga"));

        assertEquals("TGCACT", result);
    }

    @Test
    void replication_blankCodons_returnsNoCodonsAvailableMessage() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("   ", ""));

        assertEquals("No codons available", result);
    }

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
