import java.util.ArrayList;
import java.util.List;

public class DNAHelpers {

    public List<String> dnaToCodons(String dna) {
        if (dna == null || dna.isBlank()) {
            return List.of();
        }
        String normalized = dna.trim().toUpperCase();
        if (!normalized.matches("[ACGT]+")) {
            throw new IllegalArgumentException("DNA contains invalid characters");
        }
        if (normalized.length() % 3 != 0) {
            throw new IllegalArgumentException("DNA length must be divisible by 3");
        }

        List<String> codons = new ArrayList<>();
        for (int i = 0; i < normalized.length(); i += 3) {
            codons.add(normalized.substring(i, i + 3));
        }
        return List.copyOf(codons);
    }
}
