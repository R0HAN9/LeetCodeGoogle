class Solution {
    public boolean isAnagram(String s, String t) {
        
        // Create an array to store the frequency of each character (for lowercase English letters).
        int[] freq = new int[26];

        // Iterate through the first string `s` and increment the frequency count.
        for (char ch : s.toCharArray()) {
            freq[ch - 'a'] += 1; // Increment count for the corresponding character.
        }

        // Iterate through the second string `t` and decrement the frequency count.
        for (char ch : t.toCharArray()) {
            freq[ch - 'a'] -= 1; // Decrement count for the corresponding character.
        }

        // Check if all frequency counts are zero.
        // If any count is not zero, it means the two strings are not anagrams.
        for (int i : freq) {
            if (i != 0) {
                return false; // Return false if any character frequency mismatch is found.
            }
        }

        // If all counts are zero, the two strings are anagrams.
        return true;
    }
}
