import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: The Sorting Shield
        Arrays.sort(nums);
        
        // Step 2: The Outer Loop (Fixing one number)
        for (int i = 0; i < nums.length - 2; i++) {
            // Your Golden Early Exit rule: If the lowest number is > 0, 
            // no three numbers can ever sum to 0. Stop completely!
            if (nums[i] > 0) {
                break;
            }
            
            // Skip duplicate base values for the outer loop
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Step 3: Initialize Two Pointers
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // Found a valid unique triplet!
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                    
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}