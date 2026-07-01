class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            if (buyPrice < prices[i]) {
                int profit = prices[i] - buyPrice; // Today's price minus our lowest buy price
                maxProfit = Math.max(maxProfit, profit);
            } else {
                buyPrice = prices[i]; // Found a new lowest price to buy at
            }
        }
        
        return maxProfit;
    }
}