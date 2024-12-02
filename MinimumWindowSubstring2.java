class Solution {
    public String minWindow(String s, String t) {
        // If `s` is shorter than `t`, it's impossible to form the window, return empty string.
        if (s.length() < t.length()) {
            return "";
        }

        // Create a map to store the frequency of characters in `t`.
        Map<Character, Integer> charCount = new HashMap<>();
        for (char ch : t.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        // The number of target characters still to be matched.
        int targetCharsRemaining = t.length();

        // Initialize variables for tracking the minimum window.
        int[] minWindow = {0, Integer.MAX_VALUE}; // Stores start and end indices of the minimum window.
        int startIndex = 0; // Start index of the current window.

        // Iterate over the string `s` using the end pointer.
        for (int endIndex = 0; endIndex < s.length(); endIndex++) {
            char ch = s.charAt(endIndex);

            // If the current character is in `t` and still needed, decrement the remaining count.
            if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
                targetCharsRemaining--;
            }

            // Update the frequency of the current character in the map (reduce by 1).
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

            // Once all characters are matched, try to shrink the window.
            if (targetCharsRemaining == 0) {
                while (true) {
                    char charAtStart = s.charAt(startIndex);

                    // Stop shrinking if removing `charAtStart` breaks the valid window condition.
                    if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
                        break;
                    }

                    // Otherwise, increment the frequency of `charAtStart` in the map.
                    charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
                    startIndex++; // Shrink the window from the left.
                }

                // Update the minimum window if the current one is smaller.
                if (endIndex - startIndex < minWindow[1] - minWindow[0]) {
                    minWindow[0] = startIndex;
                    minWindow[1] = endIndex;
                }

                // Adjust the window to find new potential matches.
                charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
                targetCharsRemaining++; // A character is now unmatched.
                startIndex++;
            }
        }

        // If no valid window is found, return an empty string.
        // Otherwise, return the substring corresponding to the smallest window.
        return minWindow[1] >= s.length() ? "" : s.substring(minWindow[0], minWindow[1] + 1);
    }
}
