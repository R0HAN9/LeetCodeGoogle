class Solution {
    public String minWindow(String s, String t) {
        // If input strings are invalid or the length of `s` is less than `t`, return an empty string.
        if (s == null || t == null || s.length() == 0 || t.length() == 0 ||
                s.length() < t.length()) {
            return new String();
        }

        // Create a frequency map for characters in `t`.
        int[] map = new int[128]; // ASCII size (to cover all possible characters).
        int count = t.length(); // Number of characters in `t` to be matched.
        int start = 0, end = 0; // Pointers for the sliding window.
        int minLen = Integer.MAX_VALUE; // Minimum length of the substring found.
        int startIndex = 0; // Starting index of the minimum substring.

        // Populate the frequency map for characters in `t`.
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        // Convert string `s` to a character array for faster access.
        char[] chS = s.toCharArray();

        // Start sliding the window over `s`.
        while (end < chS.length) {
            // Decrease the count in the map for the current character. 
            // If the character was required (map value > 0), decrement the `count`.
            if (map[chS[end++]]-- > 0) {
                count--;
            }

            // When all characters are matched (count == 0), try to shrink the window.
            while (count == 0) {
                // Update the minimum length and starting index if a smaller window is found.
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                // Move the `start` pointer forward to shrink the window.
                // If the character at `start` was part of `t`, increment `count`.
                if (map[chS[start++]]++ == 0) {
                    count++;
                }
            }
        }

        // If no valid window is found, return an empty string. Otherwise, return the smallest window substring.
        return minLen == Integer.MAX_VALUE ? new String() :
                new String(chS, startIndex, minLen);
    }
}
