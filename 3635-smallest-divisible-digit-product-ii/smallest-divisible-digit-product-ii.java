class Solution {

    // Prime exponent contribution of each digit:
    // {power of 2, power of 3, power of 5, power of 7}
    static final int[][] FACT = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    private int minDigits(int a, int b, int c, int d) {

        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);
        d = Math.max(d, 0);

        // 5 and 7 can only be supplied by digits 5 and 7.
        int ans = c + d;

        /*
         * For powers of 2 and 3:
         *
         * digits:
         * 8 -> 2^3
         * 9 -> 3^2
         * 6 -> 2 * 3
         *
         * We can try whether using a 6 is useful.
         *
         * Only 0 or 1 six is ever necessary in an optimal representation,
         * because 6*6 = 36 can be represented by 4*9 with same digit count.
         */

        int without6 =
                (a + 2) / 3 +
                (b + 1) / 2;

        int with6 = Integer.MAX_VALUE;

        if (a > 0 && b > 0) {
            with6 =
                    1 +
                    (Math.max(0, a - 1) + 2) / 3 +
                    (Math.max(0, b - 1) + 1) / 2;
        }

        ans += Math.min(without6, with6);

        return ans;
    }

    private boolean canFill(
            int slots,
            int r2,
            int r3,
            int r5,
            int r7
    ) {
        return minDigits(r2, r3, r5, r7) <= slots;
    }

    /*
     * Build lexicographically smallest suffix of given length
     * which supplies the required remaining prime factors.
     */
    private String buildSuffix(
            int len,
            int r2,
            int r3,
            int r5,
            int r7
    ) {

        StringBuilder sb = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            int remaining = len - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int n2 = Math.max(0, r2 - FACT[digit][0]);
                int n3 = Math.max(0, r3 - FACT[digit][1]);
                int n5 = Math.max(0, r5 - FACT[digit][2]);
                int n7 = Math.max(0, r7 - FACT[digit][3]);

                if (canFill(remaining, n2, n3, n5, n7)) {

                    sb.append((char) ('0' + digit));

                    r2 = n2;
                    r3 = n3;
                    r5 = n5;
                    r7 = n7;

                    break;
                }
            }
        }

        return sb.toString();
    }

    public String smallestNumber(String num, long t) {

        // ---------------------------------------------------
        // Step 1: factorize t into 2,3,5,7
        // ---------------------------------------------------

        int req2 = 0;
        int req3 = 0;
        int req5 = 0;
        int req7 = 0;

        while (t % 2 == 0) {
            req2++;
            t /= 2;
        }

        while (t % 3 == 0) {
            req3++;
            t /= 3;
        }

        while (t % 5 == 0) {
            req5++;
            t /= 5;
        }

        while (t % 7 == 0) {
            req7++;
            t /= 7;
        }

        // A product of decimal digits can contain
        // only prime factors 2,3,5,7.
        if (t != 1) {
            return "-1";
        }

        int n = num.length();

        // ---------------------------------------------------
        // Step 2: prefix prime-factor counts
        // ---------------------------------------------------

        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];

        // prefixValid[i] = first i digits contain no zero
        boolean[] prefixValid = new boolean[n + 1];
        prefixValid[0] = true;

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            p2[i + 1] = p2[i];
            p3[i + 1] = p3[i];
            p5[i + 1] = p5[i];
            p7[i + 1] = p7[i];

            prefixValid[i + 1] =
                    prefixValid[i] && digit != 0;

            if (digit != 0) {
                p2[i + 1] += FACT[digit][0];
                p3[i + 1] += FACT[digit][1];
                p5[i + 1] += FACT[digit][2];
                p7[i + 1] += FACT[digit][3];
            }
        }

        // ---------------------------------------------------
        // Step 3: num itself might already work
        // ---------------------------------------------------

        if (prefixValid[n]
                && p2[n] >= req2
                && p3[n] >= req3
                && p5[n] >= req5
                && p7[n] >= req7) {

            return num;
        }

        // ---------------------------------------------------
        // Step 4:
        // Find rightmost position that we can increase.
        //
        // Prefix before it stays exactly equal to num.
        // Once this digit is increased, suffix can be minimized.
        // ---------------------------------------------------

        for (int i = n - 1; i >= 0; i--) {

            // Prefix num[0 ... i-1] must already be zero-free.
            if (!prefixValid[i]) {
                continue;
            }

            int current = num.charAt(i) - '0';

            for (int digit = Math.max(1, current + 1);
                 digit <= 9;
                 digit++) {

                int have2 = p2[i] + FACT[digit][0];
                int have3 = p3[i] + FACT[digit][1];
                int have5 = p5[i] + FACT[digit][2];
                int have7 = p7[i] + FACT[digit][3];

                int r2 = Math.max(0, req2 - have2);
                int r3 = Math.max(0, req3 - have3);
                int r5 = Math.max(0, req5 - have5);
                int r7 = Math.max(0, req7 - have7);

                int suffixLength = n - i - 1;

                if (canFill(
                        suffixLength,
                        r2,
                        r3,
                        r5,
                        r7
                )) {

                    StringBuilder answer = new StringBuilder();

                    answer.append(num, 0, i);
                    answer.append((char) ('0' + digit));

                    answer.append(
                            buildSuffix(
                                    suffixLength,
                                    r2,
                                    r3,
                                    r5,
                                    r7
                            )
                    );

                    return answer.toString();
                }
            }
        }

        // ---------------------------------------------------
        // Step 5:
        // No answer of same length.
        //
        // Any (n+1)-digit zero-free number is automatically
        // greater than num.
        // ---------------------------------------------------

        int len = n + 1;

        // t <= 1e14, so this will only need a tiny number
        // of additional digits.
        while (!canFill(
                len,
                req2,
                req3,
                req5,
                req7
        )) {
            len++;
        }

        return buildSuffix(
                len,
                req2,
                req3,
                req5,
                req7
        );
    }
}