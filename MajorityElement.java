class Solution {
    public int majorityElement(int[] nums) {
        
        int result = 0;
        int majority = 0;
        // result and majority are initialized to 0.
        // result will store the majority element, while majority will store its count.

        for (int n : nums) {
            // The code iterates through each element n in the nums list.


            // If majority is 0, it means we have encountered a new element which might be the majority element.
            // So, we update result to the current element n.
            if (majority == 0) {
                result = n;
            }

            majority += n == result ? 1 : -1;
            // We update the majority count based on whether the current element n is equal to the current majority element result.
            // If n is equal to result, we increment the majority count by 1. Otherwise, we decrement it by 1.
        }

        return result;
        // Once all elements in nums have been processed, we return the result, which contains the majority element.
    }
}
