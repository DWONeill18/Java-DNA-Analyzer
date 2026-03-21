import DNAAnalysis.DNAReplication;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void replication_emptyList_returnsEmptyString() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of());

        assertEquals("", result);
    }

    @Test
    void replication_nullInput_returnsEmptyString() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(null);

        assertEquals("", result);
    }

    @Test
    void replication_lowercaseCodons_areNormalized() {
        DNAReplication replication = new DNAReplication();

        String result = replication.replication(List.of("acg", "tga"));

        assertEquals("TGCACT", result);
    }
}
