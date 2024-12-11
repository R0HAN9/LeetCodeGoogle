// Problem Description

/* Given a string A consisting of lowercase characters.
We need to tell minimum characters to be appended (insertion at end) to make the string A a palindrome. */

public class Solution {

    // Method to solve the problem: Find the minimum characters to be appended 
    // to make the string a palindrome.
    public int solve(String A) {
        // Step 1: Create a new string that combines the reversed input, a separator, and the original string.
        // The separator ensures no unintended overlap during LPS calculation.
        String modifiedString = new StringBuilder(A).reverse().toString() + "$" + A;

        // Step 2: Compute the LPS array for the modified string.
        int[] lps = createLPS(modifiedString);

        // Step 3: Use the last value in the LPS array to determine the minimum characters to append.
        // Explanation: The last value of the LPS array gives the length of the longest
        // suffix of the original string that matches a prefix of the reversed string.
        // Subtract this value from the total length of the string to get the minimum characters to add.
        return A.length() - lps[lps.length - 1];
    }

    // Helper method to compute the LPS (Longest Prefix Suffix) array.
    // The LPS array is used to find patterns in the string for matching.
    private int[] createLPS(String str) {
        int M = str.length(); // Length of the input string.
        int[] lps = new int[M]; // Initialize the LPS array to store the prefix-suffix matches.

        // 'len' keeps track of the length of the current longest prefix which is also a suffix.
        int len = 0; 
        int i = 1; // Start comparing from the second character.
        
        // LPS of the first character is always 0 since no proper prefix exists for a single character.
        lps[0] = 0;

        // Loop through the string to fill the LPS array.
        while (i < M) {
            // Case 1: Characters match.
            if (str.charAt(i) == str.charAt(len)) {
                len++; // Increment the length of the current prefix-suffix match.
                lps[i] = len; // Store the length in the LPS array.
                i++; // Move to the next character.
            } else {
                // Case 2: Characters do not match.

                // If 'len' is not zero, fall back to the last known prefix length.
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    // If no proper prefix exists, set LPS to 0 for this character.
                    lps[i] = 0;
                    i++; // Move to the next character.
                }
            }
        }

        // Return the computed LPS array.
        return lps;
    }
}
