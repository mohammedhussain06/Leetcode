import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Calculate the square of each element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(squares);
        
        // Step 3: Subtract the smallest n/2 squares and add the rest
        long score = 0;
        int mid = n / 2;
        
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }
        
        return score;
    }
}