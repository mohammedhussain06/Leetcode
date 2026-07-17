class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // freq[x] = frequency of x in nums
        long[] freq = new long[max + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // gcdCount[g] = number of pairs whose GCD is exactly g
        long[] gcdCount = new long[max + 1];

        // Process from larger gcd to smaller gcd
        for (int g = max; g >= 1; g--) {
            long count = 0;

            // Count numbers divisible by g
            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            // All pairs where both numbers are divisible by g
            gcdCount[g] = count * (count - 1) / 2;

            // Remove pairs whose GCD is a multiple of g greater than g
            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                gcdCount[g] -= gcdCount[multiple];
            }
        }

        // prefix[g] = number of pairs having GCD <= g
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + gcdCount[g];
        }

        int[] answer = new int[queries.length];

        // Find the GCD at each 0-based index query
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // convert to 1-based position

            int left = 1;
            int right = max;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefix[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
        }

        return answer;
    }
}