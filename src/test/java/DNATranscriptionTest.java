import DNAAnalysis.DNAReplication;
import DNAAnalysis.DNATranscription;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DNATranscriptionTest {

    @Test
    void transcription_multipleCodons_returnsTranscribedSequence() {

        DNATranscription dnaTranscription = new DNATranscription();

        String result = dnaTranscription.transcription(List.of("ACG", "TGA", "CCT"));

        assertEquals("UGCACUGGA", result));
    }

    @Test
    void transcription_emptyList_returnsNoCodonsAvailableMessage() {
        DNATranscription dnaTranscription = new DNATranscription();

        String result = dnaTranscription.transcription(List.of());

        assertEquals("No codons available", result);
    }

}
