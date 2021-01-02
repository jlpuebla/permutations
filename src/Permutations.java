/**
 * Program that given two non-negative integers n and r, with 0 ≤ n ≤ 9
 * and 0 ≤ r ≤ n, will generate all permutations of length r (without repetitions)
 * of {1,2,...,n} and print them to the screen.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Permutations {

    public static void main(String[] args) {

        int n = getUserInput("Please enter value of n (where 0 < n < 10)", 1, 9);
        int r = getUserInput("Please enter value of r (where 0 ≤ r ≤ " + n + ")", 0, n);

        if (n > 0 && r >= 0) {
            String string = generateString(n);
            System.out.println("String = " + string);

            System.out.println("Generating all permutations with n=" + n + " and r=" + r + "...");
            ArrayList<String> permutations = generatePermutations(string, r);

            System.out.println("Total # of permutations = " + permutations.size());
            printPermutations(permutations);
        }

    }

    /**
     * Function generate permutations
     *
     * @param str string created from user input
     * @param r   cardinality
     * @return list of permutations
     */
    static ArrayList<String> generatePermutations(String str, int r) {
        ArrayList<String> permutations = new ArrayList<>();
        generatePermutations(permutations, r, str, "");
        return permutations;
    }

    /**
     * Recursive method that generates permutations and adds them to the list
     *
     * @param permutations pointer to permutations list
     * @param r            cardinality
     * @param source       string created from user input
     * @param permutation  permutation
     */
    public static void generatePermutations(ArrayList<String> permutations, int r, String source, String permutation) {
        // Base case: Source string is empty
        if (source.isEmpty())
            return;

        for (int i = 0; i < source.length(); i++) {
            // Build permutation string
            String temp = permutation + source.charAt(i);

            // Add to permutations list if permutation matches cardinality
            if (temp.length() == r && !permutations.contains(temp))
                permutations.add(temp);
            else {
                // Left/Right substrings with char at index i removed, recurse again
                String left = source.substring(0, i);
                String right = source.substring(i + 1);

                generatePermutations(permutations, r, left + right, temp);
            }
        }
    }

    /**
     * Generates string from user input n
     * @param n
     * @return
     */
    static String generateString(int n) {
        if (n == 1)
            return "1";

        return generateString(n - 1) + n;
    }

    /**
     * prints permutations
     * @param permutations
     */
    public static void printPermutations(ArrayList<String> permutations) {
        System.out.println("Permutations:");

        for (String string : permutations) {
            System.out.println(string);
        }
    }

    /**
     * Function asks the user for valid input
     *
     * @param msg
     * @param max
     * @return
     */
    public static int getUserInput(String msg, int min, int max) {
        Scanner scn = new Scanner(System.in);
        boolean quit = false;                   // Variable used for while loop exit

        do {
            System.out.println(msg + ", enter q to quit:");

            if (scn.hasNextInt()) {
                int num = scn.nextInt();

                if (num >= min && num <= max) {
                    return num;
                } else
                    System.out.println("Invalid number.");
            } else if (scn.hasNext("q") || scn.hasNext("Q")) {
                // User decided to quit and entered "q"/"Q"
                quit = true;
            } else {
                // User did not enter a number or "q"/"Q"
                System.out.println("Not valid input, try again.");
            }

            scn.nextLine();

        } while (quit == false);

        return -1;
    }
}
