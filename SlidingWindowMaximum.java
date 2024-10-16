class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];

            while (!deque.isEmpty() && deque.getLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);

            if (index >= k && nums[index - k] == deque.getFirst()) {
                deque.pollFirst();
            }

            if (index >= k - 1) {
                result.add(deque.getFirst());
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
