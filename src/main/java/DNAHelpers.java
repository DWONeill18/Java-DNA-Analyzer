import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DNAHelpers {
    private static final Pattern DNA_PATTERN = Pattern.compile("[ACGT]+");

    public List<String> dnaToCodons(String dna) {
        if (dna == null || dna.isBlank()) {
            return List.of();
        }
        String normalized = dna.trim().toUpperCase();
        if (!DNA_PATTERN.matcher(normalized).matches()) {
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

    public void countCodonOccurrences(List<String> codons, Scanner scanner, PrintStream out) {
        if (codons == null || codons.isEmpty()) {
            out.println("No codons available.");
            return;
        }

        String normalizedCodon;
        while (true) {
            out.print("Enter codon: ");
            String codon = scanner.nextLine();
            if (codon == null || codon.isBlank()) {
                out.println("Invalid codon. Please enter a 3-letter codon using A, C, G, T.");
                continue;
            }
            normalizedCodon = codon.trim().toUpperCase();
            if (normalizedCodon.length() != 3 || !DNA_PATTERN.matcher(normalizedCodon).matches()) {
                out.println("Invalid codon. Please enter a 3-letter codon using A, C, G, T.");
                continue;
            }
            break;
        }

        int count = 0;
        for (String item : codons) {
            if (normalizedCodon.equals(item)) {
                count++;
            }
        }

        out.println("Codon " + normalizedCodon + " appears " + count + " times.");
    }
}
