import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();

        for (int key : hm.keySet()) {
            if (hm.get(key) > nums.length / 3) {
                ans.add(key);
            }
        }

        return ans;
    }
}