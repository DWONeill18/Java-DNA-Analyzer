# Java-DNA-Analyzer

## Overview
Java-DNA-Analyzer is a console application that presents a menu of DNA lab workflows. You choose an option, follow the prompts, and the app reads and writes DNA-related data through files.

## Running the App
Build and run the `Main` class. The program opens an interactive menu and waits for input.

## Menu Options

### Option 1: DNA Match
Checks whether a suspect matches a DNA sample by counting codon occurrences. The option reads a DNA file, prompts for a codon, then writes the original DNA back out to a file.

Example:
```text
1. DNA Match
Enter option (1-9): 1
DNA Match
Enter file path: src/test/resources/sample-dna.txt
Enter codon to count (e.g., ACG): ACG
Codon count: 1
Enter file path to output file: out/dna-match.txt
```

### Option 2: DNA Replication
Replicates a DNA strand to produce its complementary strand (plus the original). The option reads a DNA file, replicates it, then writes the replicated output to a file.

Example:
```text
1. DNA Match
2. DNA Replication
Enter option (1-9): 2
DNA Replication
Enter file path: src/test/resources/sample-dna.txt
Enter file path to output file: out/dna-replication.txt
```

### Option 3: DNA Transcription
Currently prints the label "DNA Transcription" only (no prompts or file output yet).

Example:
```text
Enter option (1-9): 3
DNA Transcription
```

### Option 4: DNA Translation
Currently prints the label "DNA Translation" only (no prompts or file output yet).

Example:
```text
Enter option (1-9): 4
DNA Translation
```

### Option 5: Random DNA Generator
Currently prints the label "Random DNA Generator" only (no prompts or file output yet).

Example:
```text
Enter option (1-9): 5
Random DNA Generator
```

### Option 6: Random DNA Mutation
Currently prints the label "Mutation" only (no prompts or file output yet).

Example:
```text
Enter option (1-9): 6
Mutation
```

### Option 7: Reverse Transcription
Currently prints the label "Reverse Transcription" only (no prompts or file output yet).

Example:
```text
Enter option (1-9): 7
Reverse Transcription
```

### Option 8: Information
Prints a short description of each option.

Example:
```text
Enter option (1-9): 8
Option Information
1. DNA Match - Check whether suspect matches sample DNA
2. DNA Replication - Replicate a DNA strand to give both it's original and complemetary strands
3. DNA Transcription - Transcribe a DNA strand to its mRNA counterpart
4. mRNA Translation - Translate an mRNA strand to amino acids in a polypeptide chain
5. Random DNA Generator - Create a random DNA strand given a number of DNA bases
6. Random DNA Mutation - Insert a random DNA base into a DNA strand to cause a mutation
7. Reverse Transcription - Transcribe an RNA strand back to its DNA counterpart
```

### Option 9: Exit
Closes the application loop.

Example:
```text
Enter option (1-9): 9
Closing down the lab
```
