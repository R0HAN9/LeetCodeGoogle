import java.util.*;

// Class for the guessing game
class GuessWord {
    public void findSecretWord(String[] words, Master master) {

        // Convert the array of words into a list for easy manipulation
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
        }

        int trials = 0; // Counter for the number of guesses
        while (trials < 30) { // Maximum number of allowed guesses is 30
            Random random = new Random();
            int ind = random.nextInt(list.size()); // Randomly pick an index from the list
            String st = list.get(ind); // Get the word at the random index
            list.remove(ind); // Remove the guessed word from the list

            int count = master.guess(st); // Call the guess API to get the number of matching letters

            if (count == 6) { // If all 6 letters match, the secret word is found
                return;
            }

            // Update the list to keep only words that could potentially match the secret
            // word
            List<String> temp = update(list, st, count);
            list = temp;
            trials++; // Increment the number of attempts
        }
    }

    // Updates the list of potential words based on the number of matching
    // characters
    public List<String> update(List<String> list, String st, int count) {

        List<String> temp = new ArrayList<>();
        for (String s : list) {
            // Add a word to the new list if its number of matches with `st` equals `count`
            if (count == match(s, st)) {
                temp.add(s);
            }
        }
        return temp; // Return the filtered list
    }

    // Helper function to calculate the number of matching characters between two
    // strings
    public int match(String s1, String s2) {
        int matches = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) { // Check character by character
                matches++;
            }
        }

        return matches; // Return the number of matching characters
    }
}
