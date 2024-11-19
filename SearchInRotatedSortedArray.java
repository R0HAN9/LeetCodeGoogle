class Solution {
    public int search(int[] nums, int target) {
        // Initialize pointers for binary search.
        int low = 0;                // Starting index of the search range.
        int high = nums.length - 1; // Ending index of the search range.

        // Perform binary search within the rotated sorted array.
        while (low <= high) {
            // Calculate the mid-point of the current range.
            int mid = (low + high) / 2;

            // Check if the target is found at the mid-point.
            if (nums[mid] == target) return mid;

            // Determine which part of the array is sorted.
            if (nums[low] <= nums[mid]) { // Left half is sorted.
                // Check if the target lies in the sorted left half.
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Narrow the search to the left half.
                } else {
                    low = mid + 1; // Narrow the search to the right half.
                }
            }
            // If the left half is not sorted, the right half must be sorted.
            else if (target > nums[mid] && target <= nums[high]) {
                low = mid + 1; // Narrow the search to the sorted right half.
            } else {
                high = mid - 1; // Narrow the search to the unsorted left half.
            }
        }

        // If the loop ends without finding the target, return -1.
        return -1;
    }
}
