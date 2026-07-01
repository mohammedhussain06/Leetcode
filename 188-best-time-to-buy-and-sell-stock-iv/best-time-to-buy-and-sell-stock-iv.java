class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) {
            return 0;
        }

        // Optimization: If k is large enough to do a transaction every possible day,
        // the problem becomes equivalent to infinite transactions (Best Time to Buy & Sell Stock II).
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // buy[j] stores the max profit after making 'j' buy operations so far
        // sell[j] stores the max profit after making 'j' sell operations so far
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // Initialize buy states to negative infinity because buying costs money
        for (int j = 0; j <= k; j++) {
            buy[j] = Integer.MIN_VALUE;
            sell[j] = 0;
        }

        // Traverse through each day's stock price
        for (int price : prices) {
            for (int j = 1; j <= k; j++) {
                // Max profit if we decide to buy the j-th stock today vs previously bought
                buy[j] = Math.max(buy[j], sell[j - 1] - price);
                
                // Max profit if we decide to sell the j-th stock today vs previously sold
                sell[j] = Math.max(sell[j], buy[j] + price);
            }
        }

        return sell[k];
    }
}