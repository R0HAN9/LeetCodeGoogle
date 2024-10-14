class Solution {
    public int[][] merge(int[][] intervals) {

        // Sort the intervals based on their start times. 
        // Sorting helps in easily identifying and merging overlapping intervals,
        // as overlapping intervals will be adjacent after sorting.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize an empty list merged to store the merged intervals.
        List<int[]> merged = new ArrayList<>();

        int[] prev = intervals[0];
        // Initialize prev with the first interval from the sorted list. 
        // This prev interval will be used to compare and merge subsequent intervals.

        for (int i = 1; i < intervals.length; i++) {
            // Iterate through the sorted intervals starting from the second interval (index 1) to the last interval.

            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
            // Check if the start time of the current interval (interval[0]) is less than or equal to the end time of the prev interval (prev[1]).
            // If true, this means the intervals overlap.

                prev[1] = Math.max(prev[1], interval[1]);
                // Merge the intervals by updating the end time of the prev interval to be the maximum end time between prev and the current interval.

            } else {
                merged.add(prev);
                // If the intervals do not overlap (the condition in step 5 is false), add the prev interval to the merged list as it is.

                prev = interval;
                // Update prev to be the current interval, as this interval does not overlap with the previous one and will be used for further comparisons.
            }
        }

        merged.add(prev);
        // After the loop ends, add the last prev interval to the merged list. 
        // This step ensures the final interval is included in the merged list.

        return merged.toArray(new int[merged.size()][]);    
        // Return the merged list containing all merged intervals.     
    }
}
