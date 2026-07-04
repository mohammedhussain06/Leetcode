import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Step 1: Sort the array to unlock the Two-Pointer strategy
        Arrays.sort(nums);
        
        // Initialize our running champion with the sum of the first 3 numbers
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // Step 2: Outer Loop for the fixed base number
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            // Step 3: Inner Two-Pointer Scan
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If we hit an exact match, the distance is 0. Return it immediately!
                if (currentSum == target) {
                    return currentSum;
                }
                
                // Check if this new sum is closer to the target than our old champion
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Move pointers based on how our sum compares to the target
                if (currentSum < target) {
                    left++;  // Sum is too small, make it bigger by moving left inward
                } else {
                    right--; // Sum is too big, make it smaller by moving right inward
                }
            }
        }
        
        return closestSum;
    }
}