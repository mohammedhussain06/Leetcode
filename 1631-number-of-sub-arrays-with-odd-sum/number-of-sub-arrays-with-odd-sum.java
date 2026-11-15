
class Solution {
    public int numOfSubarrays(int[] arr) {

        int MOD = 1000000007;

        long odd = 0;
        long even = 1;

        int prefix = 0;
        long ans = 0;

        for (int num : arr) {

            prefix += num;

            if (prefix % 2 == 1) {
                ans = (ans + even) % MOD;
                odd++;
            } 
            else {
                ans = (ans + odd) % MOD;
                even++;
            }
        }

        return (int) ans;
    }
}
