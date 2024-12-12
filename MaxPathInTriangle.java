// Problem Description

/* Given a 2D integer array A of size N * N representing a triangle of numbers.
Find the maximum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

NOTE:
Adjacent cells to cell (i,j) are only (i+1,j) and (i+1,j+1)
Row i contains i integer and n-i zeroes for all i in [1,n] where zeroes represents empty cells.*/

/* Given triangle looks like:  3
                             7 4
                             2 4 6
                             8 5 9 3
        So max path is (3 + 7 + 4 + 9) = 23 */

public class Solution {
    public int solve(ArrayList<ArrayList<Integer>> A) {
        // Get the number of rows in the triangle
        int len = A.size();

        // Start from the second-to-last row and move upwards
        for (int i = len - 2; i >= 0; i--) {
            // Iterate over each element in the current row
            for (int j = 0; j <= i; j++) {
                // Find the maximum sum path by considering the two adjacent elements below
                int maxBelow = Math.max(A.get(i + 1).get(j), A.get(i + 1).get(j + 1));

                // Update the current element to include the maximum sum path
                A.get(i).set(j, A.get(i).get(j) + maxBelow);
            }
        }

        // The top element of the triangle now contains the maximum path sum
        return A.get(0).get(0);
    }
}
