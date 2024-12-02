class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        // List to store the maximum values for each sliding window.
        List<Integer> result = new ArrayList<>();
        
        // Deque to store indices of elements in the current sliding window.
        // The deque helps efficiently track the maximum element in the window.
        Deque<Integer> deque = new LinkedList<>();

        // Iterate through the array.
        for (int index = 0; index < nums.length; index++) {
            int num = nums[index]; // Current element.

            // Remove elements from the back of the deque if they are less than the current element.
            // These elements cannot be the maximum in any future window.
            while (!deque.isEmpty() && deque.getLast() < num) {
                deque.pollLast();
            }

            // Add the current element to the back of the deque.
            deque.addLast(num);

            // If the sliding window exceeds size `k`, remove the element that is no longer in the window.
            if (index >= k && nums[index - k] == deque.getFirst()) {
                deque.pollFirst();
            }

            // Add the maximum element of the current window to the result.
            // The maximum is always at the front of the deque.
            if (index >= k - 1) {
                result.add(deque.getFirst());
            }
        }

        // Convert the result list to an array and return it.
        return result.stream().mapToInt(i -> i).toArray();
    }
}
