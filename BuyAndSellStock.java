class Solution {
    public int maxProfit(int[] prices) {
        
        int buyPrice = prices[0];
        int profit = 0;
        // Initialize buyPrice to the first price in the list and profit to 0.

        // Loop through each price starting from the second price (prices[1:]).
        for (int i = 1; i < prices.length; i++) {

            // If the current price(i) is less than the previously set buyPrice, update buyPrice to the current price. 
            // This ensures that we buy at the lowest price possible.
            if (buyPrice > prices[i]) {
                buyPrice = prices[i];
            }

            // Calculate the profit by subtracting the buyPrice from the current price(i). 
            // Update profit to the maximum value between the current profit and the calculated profit.
            // This ensures we keep track of the maximum profit throughout the iteration.

            profit = Math.max(profit, prices[i] - buyPrice);
        }

        // After the loop, return the final value of profit, 
        // which represents the maximum profit achievable from buying and selling stocks.
        return profit;
    }
}
