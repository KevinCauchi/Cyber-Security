


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cipher {

    public static void main(String args[]) throws IOException {

        //  Alphabet String for 26 Characters
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Scanner scan = new Scanner(System.in);

        // Encryption Substitution Cipher
        System.out.println("\nType in Plain-Text Message: ");
        String plaintext = scan.nextLine().toLowerCase();
        plaintext = plaintext.replaceAll("[^a-z]", "");
        String[] plaintextsub = plaintext.split("(?!^)");
        String[] ciphertext = new String[plaintextsub.length];

        // Translation Table for Cipher
        String[] trans_table_array = Arrays.stream(alphabet)
                .mapToInt(i -> (3 * Arrays.asList(alphabet).indexOf(i)) % 26)
                .mapToObj(i -> alphabet[i])
                .toArray(String[]::new);

        // Prints Cipher Table
        System.out.println("\n\nCipher Table Translation: ");
        System.out.println(Arrays.toString(alphabet));
        System.out.println(Arrays.toString(trans_table_array));

        // Encryption Step
        System.out.println("\n\nEncrypted Cipher-Text: ");
        ciphertext = Arrays.stream(plaintextsub)
                .map(i -> alphabet[(3 * Arrays.asList(alphabet).indexOf(i)) % 26])
                .peek(System.out::print)
                .toArray(String[]::new);
        System.out.println();
        System.out.println(Arrays.toString(ciphertext));

        // Decryption Substitution Cipher Step
        System.out.println("\n\nDecrypted Cipher-Text: ");
        plaintextsub = Arrays.stream(ciphertext)
                .map(i -> alphabet[Arrays.asList(trans_table_array).indexOf(i)])
                .peek(System.out::print)
                .toArray(String[]::new);
        System.out.println();
        System.out.println(Arrays.toString(plaintextsub));

        // Frequency Analysis
        System.out.println("\nFrequency Analysis:");

        // Reads the Corpus File of Great Gatsby by F. Scott Fitzgerald
        String corpus = new String(Files.readAllBytes(Paths.get("/Users/kevin/Desktop/VS/Code/corpus.txt")));

        // Computes frequency map for ciphertext
        Map<String, Long> frequencyMap = Arrays.stream(ciphertext)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Prints out Frequency Analysis of Cipher Text and also My Corpus.
        for (String letter : alphabet) {
            double corpusFrequency = (double) corpus.chars()
                    .filter(ch -> ch == letter.charAt(0))
                    .count() / corpus.length();
            double ciphertextFrequency = frequencyMap.getOrDefault(letter, 0L) / (double) ciphertext.length;
            System.out.printf("%s:\t%.2f%%\t%.2f%%\n", letter.toUpperCase(), ciphertextFrequency * 100, corpusFrequency * 100);
        }

        // Closes input function
        scan.close();
    }

}



/*

Output Code

Type in Plain-Text Message: 
Happiness can be found, even in the darkest of times, if one only remembers to turn on the light


Cipher Table Translation: 
[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]
[a, d, g, j, m, p, s, v, y, b, e, h, k, n, q, t, w, z, c, f, i, l, o, r, u, x]


Encrypted Cipher-Text: 
vattynmccgandmpqinjmlmnynfvmjazemcfqpfykmcypqnmqnhuzmkmkdmzcfqfiznqnfvmhysvf
[v, a, t, t, y, n, m, c, c, g, a, n, d, m, p, q, i, n, j, m, l, m, n, y, n, f, v, m, j, a, z, e, m, c, f, q, p, f, y, k, m, c, y, p, q, n, m, q, n, h, u, z, m, k, m, k, d, m, z, c, f, q, f, i, z, n, q, n, f, v, m, h, y, s, v, f]


Decrypted Cipher-Text: 
happinesscanbefoundeveninthedarkestoftimesifoneonlyrememberstoturnonthelight
[h, a, p, p, i, n, e, s, s, c, a, n, b, e, f, o, u, n, d, e, v, e, n, i, n, t, h, e, d, a, r, k, e, s, t, o, f, t, i, m, e, s, i, f, o, n, e, o, n, l, y, r, e, m, e, m, b, e, r, s, t, o, t, u, r, n, o, n, t, h, e, l, i, g, h, t]

Frequency Analysis:
A:      3.95%   6.23%
B:      0.00%   1.10%
C:      6.58%   1.62%
D:      2.63%   3.56%
E:      1.32%   9.25%
F:      9.21%   1.52%
G:      1.32%   1.67%
H:      2.63%   4.52%
I:      2.63%   4.63%
J:      2.63%   0.08%
K:      3.95%   0.71%
L:      1.32%   3.02%
M:      15.79%  1.93%
N:      11.84%  5.20%
O:      0.00%   5.82%
P:      3.95%   1.11%
Q:      7.89%   0.06%
R:      0.00%   4.19%
S:      1.32%   4.58%
T:      2.63%   6.69%
U:      1.32%   2.16%
V:      5.26%   0.70%
W:      0.00%   1.83%
X:      0.00%   0.11%
Y:      6.58%   1.61%
Z:      5.26%   0.05%

*/
