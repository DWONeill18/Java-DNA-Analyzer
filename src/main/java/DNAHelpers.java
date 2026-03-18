import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void countCodonOccurrencies(List<String> codons, Scanner scanner, PrintStream out) {
        out.print("Enter codon: ");
        String codon = scanner.nextLine();

        if (codons == null || codons.isEmpty() || codon == null || codon.isBlank()) {
            out.println("Codon " + codon + " appears 0 times.");
            return;
        }

        String normalizedCodon = codon.trim().toUpperCase();
        int count = 0;
        for (String item : codons) {
            if (normalizedCodon.equals(item)) {
                count++;
            }
        }

        out.println("Codon " + normalizedCodon + " appears " + count + " times.");
    }
}
