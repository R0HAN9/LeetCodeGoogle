class Solution {

    // Variables to store the dimensions of the board.
    private int rows;
    private int cols;

    // Set to keep track of visited cells during DFS.
    private Set<String> visited;

    public boolean exist(char[][] board, String word) {
        
        // Initialize the dimensions of the board.
        rows = board.length;
        cols = board[0].length;

        // Initialize the set for tracking visited cells.
        visited = new HashSet<>();

        // Count the frequency of characters in the word.
        Map<Character, Integer> count = new HashMap<>();
        for (char c : word.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Optimize by reversing the word if it starts with a less frequent character.
        if (count.getOrDefault(word.charAt(0), 0) > count.getOrDefault(word.charAt(word.length() - 1), 0)) {
            word = new StringBuilder(word).reverse().toString();
        }

        // Iterate over all cells in the board to find the starting point.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Start DFS from this cell if it matches the first character of the word.
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }

        // Return false if the word is not found.
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int k) {

        // If all characters of the word have been matched, return true.
        if (k == word.length()) return true;

        // Check for out-of-bounds, already visited cells, or character mismatch.
        if (r < 0 || r >= rows || c < 0 || c >= cols || visited.contains(r + "," + c) || board[r][c] != word.charAt(k)) {
            return false;
        }

        // Mark the current cell as visited.
        visited.add(r + "," + c);

        // Explore all 4 possible directions (up, down, left, right).
        boolean result = dfs(board, word, r + 1, c, k + 1) || // Down
                         dfs(board, word, r - 1, c, k + 1) || // Up
                         dfs(board, word, r, c + 1, k + 1) || // Right
                         dfs(board, word, r, c - 1, k + 1);   // Left

        // Backtrack: unmark the current cell as visited.
        visited.remove(r + "," + c);

        // Return whether any of the paths led to a solution.
        return result;
    }
}
