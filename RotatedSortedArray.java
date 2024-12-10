// Problem Description

/* Given a sorted array of integers A of size N and an integer B, 
where array A is rotated at some pivot unknown beforehand.

For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].

Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.
You can assume that no duplicates exist in the array.

NOTE: You are expected to solve this problem with a time complexity of O(log(N)). */

public class Solution {
    public int search(final List<Integer> A, int B) {
        // Initialize the search boundaries
        int low = 0;
        int high = A.size() - 1;

        // Perform binary search
        while (low <= high) {
            // Calculate the mid-point to divide the search space
            int mid = low + (high - low) / 2;

            // Check if the mid-point is the target
            if (A.get(mid) == B) return mid;

            // Determine which half of the array is sorted
            if (A.get(low) <= A.get(mid)) {
                // Left half is sorted
                // Check if the target lies within the sorted left half
                if (B >= A.get(low) && B < A.get(mid)) {
                    high = mid - 1; // Narrow search to the left half
                } else {
                    low = mid + 1; // Target is in the right half
                }
            } 
            // Otherwise, the right half must be sorted
            else if (B > A.get(mid) && B <= A.get(high)) {
                low = mid + 1; // Narrow search to the right half
            } else {
                high = mid - 1; // Target is in the left half
            }
        }

        // If the loop exits, the target is not present
        return -1;
    }
}
