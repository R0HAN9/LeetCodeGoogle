class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to minimize binary search iterations.
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // Get the lengths of both arrays.
        int len1 = nums1.length;
        int len2 = nums2.length;

        // Initialize binary search range for the smaller array.
        int left = 0;
        int right = len1;

        // Perform binary search to find the correct partition.
        while (left <= right) {
            // Partition index for nums1 and nums2.
            int partition1 = (left + right) / 2;
            int partition2 = (len1 + len2 + 1) / 2 - partition1;

            // Get max of left partition and min of right partition for nums1.
            int maxLeft1 = partition1 > 0 ? nums1[partition1 - 1] : Integer.MIN_VALUE;
            int minRight1 = partition1 < len1 ? nums1[partition1] : Integer.MAX_VALUE;

            // Get max of left partition and min of right partition for nums2.
            int maxLeft2 = partition2 > 0 ? nums2[partition2 - 1] : Integer.MIN_VALUE;
            int minRight2 = partition2 < len2 ? nums2[partition2] : Integer.MAX_VALUE;

            // Find the overall maximum of the left side and minimum of the right side.
            int maxLeft = Math.max(maxLeft1, maxLeft2);
            int minRight = Math.min(minRight1, minRight2);

            // Check if the partition is correct.
            if (maxLeft <= minRight) {
                // If total number of elements is even, return average of middle two.
                if ((len1 + len2) % 2 == 0) {
                    return (maxLeft + minRight) / 2.0;
                } 
                // If total number of elements is odd, return the middle element.
                else {
                    return maxLeft;
                }
            } 
            // If maxLeft1 is greater than minRight2, move partition1 to the left.
            else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } 
            // Otherwise, move partition1 to the right.
            else {
                left = partition1 + 1;
            }
        }

        // This line should never be reached for valid input.
        return 0.0;
    }
}
