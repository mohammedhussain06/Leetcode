class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : -1;

            if (c == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (c - '0');
            }
        }

        // If total '?' count is odd, Alice always wins
        // If 2 * (S1 - S2) != 9 * (Q2 - Q1), Alice wins
        return (sumDiff * 2 != -qDiff * 9);
    }
}