class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize the binary search range O(log(min(m, n)))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int start = 0;
        int end = m;

        while (start <= end) {
            // Partition nums1 and nums2
            int i = start + (end - start) / 2;
            int j = ((m + n + 1) / 2) - i;

            // Edge cases: handling boundaries if partitions fall at index 0 or array ends
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Check if we found the perfect partition
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // If total length is odd, the median is the maximum of the left side
                if ((m + n) % 2 != 0) {
                    return Math.max(maxLeft1, maxLeft2);
                } 
                // If total length is even, it's the average of the two middle elements
                else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } 
            // We are too far right in nums1, move left
            else if (maxLeft1 > minRight2) {
                end = i - 1;
            } 
            // We are too far left in nums1, move right
            else {
                start = i + 1;
            }
        }

        return 0.0;
    }
}