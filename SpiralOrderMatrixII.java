// Problem Description

// Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order and return the generated square matrix.



import java.util.ArrayList;

public class Solution {

    // Method to generate a square matrix of size A x A filled in spiral order
    public ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        // Initialize the result as a 2D ArrayList
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (A <= 0) return result; // If the input size is zero or negative, return an empty matrix

        // Step 1: Create an empty matrix filled with zeros
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < A; j++) {
                row.add(0); // Fill each cell with an initial value of 0
            }
            result.add(row); // Add the row to the result matrix
        }

        // Step 2: Initialize variables to control the filling process
        int num = 1; // This keeps track of the current number to fill in the matrix
        int top = 0; // The starting row index
        int bottom = A - 1; // The ending row index
        int left = 0; // The starting column index
        int right = A - 1; // The ending column index

        // Step 3: Loop to fill the matrix in a spiral order
        while (top <= bottom && left <= right) {
            // Fill the top row from left to right
            for (int j = left; j <= right; j++) {
                result.get(top).set(j, num++); // Set the number and increment it
            }
            top++; // Move the top boundary down

            // Fill the right column from top to bottom
            for (int i = top; i <= bottom; i++) {
                result.get(i).set(right, num++); // Set the number and increment it
            }
            right--; // Move the right boundary left

            // Fill the bottom row from right to left (only if top boundary has not crossed bottom)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.get(bottom).set(j, num++); // Set the number and increment it
                }
                bottom--; // Move the bottom boundary up
            }

            // Fill the left column from bottom to top (only if left boundary has not crossed right)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.get(i).set(left, num++); // Set the number and increment it
                }
                left++; // Move the left boundary right
            }
        }

        // Step 4: Return the fully filled matrix
        return result;
    }
}
