import DNAAnalysis.DNATranscription;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DNATranscriptionTest {

    @Test
    void transcription_multipleCodons_returnsTranscribedSequence() {

        DNATranscription dnaTranscription = new DNATranscription();

        String result = dnaTranscription.transcription(List.of("ACG", "TGA", "CCT"));

        assertEquals("UGCACUGGA", result);
    }

    @Test
    void transcription_emptyList_throwsException() {
        DNATranscription dnaTranscription = new DNATranscription();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dnaTranscription.transcription(List.of())
        );

        assertEquals("Codon list must not be null or empty", exception.getMessage());
    }

    @Test
    void transcription_nullList_throwsException() {
        DNATranscription dnaTranscription = new DNATranscription();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dnaTranscription.transcription(null)
        );

        assertEquals("Codon list must not be null or empty", exception.getMessage());
    }

    @Test
    void transcription_allBlankCodons_throwsException() {
        DNATranscription dnaTranscription = new DNATranscription();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dnaTranscription.transcription(List.of("   ", "", "\t"))
        );

        assertEquals("No usable codons after filtering", exception.getMessage());
    }

    @Test
    void transcription_invalidBase_throwsException() {
        DNATranscription dnaTranscription = new DNATranscription();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dnaTranscription.transcription(List.of("ACX"))
        );

        assertEquals("DNA contains invalid characters: X", exception.getMessage());
    }

    @Test
    void transcription_lowercaseCodon_transcribesCorrectly() {
        DNATranscription dnaTranscription = new DNATranscription();

        String result = dnaTranscription.transcription(List.of("acg"));

        assertEquals("UGC", result);
    }
}
