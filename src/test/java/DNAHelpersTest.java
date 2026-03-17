import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
