// Problem Description

/* Given an N x M matrix A of non-negative integers representing the height of each unit cell in a continent,
the "Blue lake" touches the left and top edges of the matrix and the "Red lake" touches the right and bottom edges.
Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the number of cells from where water can flow to both the Blue and Red lake.*/

 /* A = [
       [1, 2, 2, 3, 5]
       [3, 2, 3, 4, 4]
       [2, 4, 5, 3, 1]
       [6, 7, 1, 4, 5]
       [5, 1, 1, 2, 4]
     ]*/

import java.util.ArrayList;

public class Solution {
    public int solve(ArrayList<ArrayList<Integer>> A) {
        // Check if the matrix is empty
        if (A == null || A.isEmpty() || A.get(0).isEmpty()) return 0;

        int rows = A.size();
        int cols = A.get(0).size();

        // Directions for moving: right, down, left, up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Reachable matrices for both lakes
        boolean[][] blueReachable = new boolean[rows][cols];
        boolean[][] redReachable = new boolean[rows][cols];

        // Perform DFS for the Blue Lake (top and left edges)
        for (int i = 0; i < rows; i++) {
            dfs(A, blueReachable, i, 0, directions);
        }
        for (int j = 0; j < cols; j++) {
            dfs(A, blueReachable, 0, j, directions);
        }

        // Perform DFS for the Red Lake (bottom and right edges)
        for (int i = 0; i < rows; i++) {
            dfs(A, redReachable, i, cols - 1, directions);
        }
        for (int j = 0; j < cols; j++) {
            dfs(A, redReachable, rows - 1, j, directions);
        }

        // Count cells reachable by both lakes
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (blueReachable[i][j] && redReachable[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(ArrayList<ArrayList<Integer>> matrix, boolean[][] reachable, int x, int y, int[][] directions) {
        reachable[x][y] = true; // Mark current cell as reachable

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // Check boundaries and conditions for flowing water
            if (newX >= 0 && newX < matrix.size() && newY >= 0 && newY < matrix.get(0).size()
                    && !reachable[newX][newY] // Not visited yet
                    && matrix.get(newX).get(newY) >= matrix.get(x).get(y)) { // Water can flow
                dfs(matrix, reachable, newX, newY, directions);
            }
        }
    }
}
