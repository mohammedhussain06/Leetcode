class Solution {
    static final long LIMIT = 1_000_000L;
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) freq[ch - 'a']++;
        int[] half = new int[26];
        char mid = 0;
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1) mid = (char) ('a' + i);
        }
        if (countWays(half, halfLen) < k) return "";
        StringBuilder left = new StringBuilder();
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                long ways = countWays(half, halfLen - pos - 1);
                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if (mid != 0) ans.append(mid);
        ans.append(new StringBuilder(left).reverse());
        return ans.toString();
    }
    private long countWays(int[] cnt, int total) {
        long ways = 1;
        int rem = total;
        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (c == 0) continue;
            long choose = combLimited(rem, c);
            ways = mulLimited(ways, choose);
            if (ways >= LIMIT) return LIMIT;
            rem -= c;
        }
        return ways;
    }
    private long combLimited(int n, int r) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res >= LIMIT) return LIMIT;
        }
        return res;
    }
    private long mulLimited(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (a >= LIMIT || b >= LIMIT) return LIMIT;
        if (a > LIMIT / b) return LIMIT;
        long x = a * b;
        return Math.min(x, LIMIT);
    }
}