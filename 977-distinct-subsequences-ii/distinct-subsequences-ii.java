class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long total = 1;
        long[] lastAdded = new long[26]; 
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            long newAdded = (total - lastAdded[idx] + MOD) % MOD;
            total = (total + newAdded) % MOD;
            lastAdded[idx] = (lastAdded[idx] + newAdded) % MOD;
        }
        return (int) ((total - 1 + MOD) % MOD);
    }
}