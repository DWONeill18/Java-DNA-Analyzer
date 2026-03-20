import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
class DNAHelpersTest {

    @Test
    void dnaToCodons_splitsIntoGroupsOfThree() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons("ACGTGACCT");

        assertEquals(List.of("ACG", "TGA", "CCT"), codons);
    }

    @Test
    void dnaToCodons_nonDivisibleByThree_throwsErrorMessage() {
        DNAHelpers helpers = new DNAHelpers();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> helpers.dnaToCodons("ACGTG")
        );

        assertEquals("DNA length must be divisible by 3", ex.getMessage());
    }

    @Test
    void dnaToCodons_nullInput_returnsEmptyList() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons(null);

        assertEquals(List.of(), codons);
    }

    @Test
    void dnaToCodons_blankInput_returnsEmptyList() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons("   ");

        assertEquals(List.of(), codons);
    }

    @Test
    void dnaToCodons_invalidCharacters_throwsErrorMessage() {
        DNAHelpers helpers = new DNAHelpers();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> helpers.dnaToCodons("ACGTX")
        );

        assertEquals("DNA contains invalid characters", ex.getMessage());
    }

    @Test
    void dnaToCodons_minimalLength_returnsSingleCodon() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons("ACG");

        assertEquals(List.of("ACG"), codons);
    }

    @Test
    void dnaToCodons_largeInput_returnsExpectedCount() {
        DNAHelpers helpers = new DNAHelpers();
        String dna = "ACG".repeat(1000);

        List<String> codons = helpers.dnaToCodons(dna);

        assertEquals(1000, codons.size());
        assertTrue(codons.stream().allMatch("ACG"::equals));
    }

    @Test
    void dnaToCodons_lowercaseInput_isNormalizedToUppercase() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons("acgtga");

        assertEquals(List.of("ACG", "TGA"), codons);
    }

    @Test
    void dnaToCodons_whitespaceIsTrimmed() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = helpers.dnaToCodons("  ACGTGA  ");

        assertEquals(List.of("ACG", "TGA"), codons);
    }

    @Test
    void dnaToCodons_invalidDigit_throwsErrorMessage() {
        DNAHelpers helpers = new DNAHelpers();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> helpers.dnaToCodons("ACG1TG")
        );

        assertEquals("DNA contains invalid characters", ex.getMessage());
    }

    @Test
    void countCodonOccurrences_countsExactMatches() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of("ACG", "TGA", "CCT", "ACG");
        ByteArrayInputStream in = new ByteArrayInputStream("ACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("Codon ACG appears 2 times."));
    }

    @Test
    void countCodonOccurrences_returnsZeroWhenNotFound() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of("ACG", "TGA", "CCT");
        ByteArrayInputStream in = new ByteArrayInputStream("AAA\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("Codon AAA appears 0 times."));
    }

    @Test
    void countCodonOccurrences_noCodonsAvailable_printsMessage() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of();
        ByteArrayInputStream in = new ByteArrayInputStream("ACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("No codons available."));
    }

    @Test
    void countCodonOccurrences_nullCodons_printsMessage() {
        DNAHelpers helpers = new DNAHelpers();

        ByteArrayInputStream in = new ByteArrayInputStream("ACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(null, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("No codons available."));
    }

    @Test
    void countCodonOccurrences_repromptsOnInvalidInput() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of("ACG", "TGA", "CCT");
        ByteArrayInputStream in = new ByteArrayInputStream("AX1\nACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Codon ACG appears 1 times."));
    }

    @Test
    void countCodonOccurrences_repromptsOnWrongLength() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of("ACG", "TGA", "CCT");
        ByteArrayInputStream in = new ByteArrayInputStream("AA\nACGT\nACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Codon ACG appears 1 times."));
    }

    @Test
    void countCodonOccurrences_repromptsOnBlankInput() {
        DNAHelpers helpers = new DNAHelpers();

        List<String> codons = List.of("ACG", "TGA", "CCT");
        ByteArrayInputStream in = new ByteArrayInputStream("\nACG\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        helpers.countCodonOccurrences(codons, new Scanner(in), new PrintStream(out));
        String output = out.toString();

        assertTrue(output.contains("Invalid codon. Please enter a 3-letter codon using A, C, G, T."));
        assertTrue(output.contains("Codon ACG appears 1 times."));
    }

    @Test
    void dnaToCodons_internalWhitespace_throwsErrorMessage() {
        DNAHelpers helpers = new DNAHelpers();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> helpers.dnaToCodons("ACG TGA")
        );

        assertEquals("DNA contains invalid characters", ex.getMessage());
    }

    @Test
    void dnaToCodons_newline_throwsErrorMessage() {
        DNAHelpers helpers = new DNAHelpers();

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> helpers.dnaToCodons("ACG\nTGA")
        );

        assertEquals("DNA contains invalid characters", ex.getMessage());
    }
}
