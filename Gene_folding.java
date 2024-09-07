import java.util.Scanner;

public class Main {

    // Method to find the longest symmetric folding point
    public static int findFoldingPoint(String sequence) {
        int len = sequence.length();
        int bestPoint = -1;

        // Try every possible point to fold
        for (int i = 1; i < len; i++) {
            int left = i - 1;
            int right = i;
            boolean validFold = true;

            // Expand from the middle
            while (left >= 0 && right < len) {
                if (sequence.charAt(left) != sequence.charAt(right)) {
                    validFold = false;
                    break;
                }
                left--;
                right++;
            }

            // If valid folding point found
            if (validFold) {
                return i;
            }
        }

        return -1;
    }

    // Method to fold the sequence at the given point
    public static String foldSequence(String sequence, int point) {
        String leftPart = sequence.substring(0, point);
        String rightPart = sequence.substring(point);

        // Reverse the left part to check for folding
        String reversedLeftPart = new StringBuilder(leftPart).reverse().toString();

        // Fold the sequence
        StringBuilder folded = new StringBuilder();
        int minLen = Math.min(reversedLeftPart.length(), rightPart.length());

        for (int i = 0; i < minLen; i++) {
            if (reversedLeftPart.charAt(i) == rightPart.charAt(i)) {
                folded.append(reversedLeftPart.charAt(i));
            }
        }

        // Append the remaining part
        folded.append(reversedLeftPart.substring(minLen));
        folded.append(rightPart.substring(minLen));

        return folded.toString();
    }

    // Method to perform repeated GOOF and return the shortest sequence length
    public static int performGOOF(String sequence) {
        String currentSequence = sequence;
        String newSequence;
        int point;

        while (true) {
            point = findFoldingPoint(currentSequence);
            if (point == -1) {
                break;
            }
            newSequence = foldSequence(currentSequence, point);
            if (newSequence.length() == currentSequence.length()) {
                break;
            }
            currentSequence = newSequence;
        }

        return currentSequence.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the gene sequence: ");
        String sequence = scanner.nextLine();
        
        if (sequence.chars().allMatch(c -> "ACGT".indexOf(c) != -1)) {
            int result = performGOOF(sequence);
            System.out.println(result);
        } else {
            System.out.println("Invalid sequence. Only characters A, C, G, T are allowed.");
        }
        
        scanner.close();
    }
}
