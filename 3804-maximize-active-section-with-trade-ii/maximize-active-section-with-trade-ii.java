import java.util.*;

class Group {
    int start;
    int length;

    Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}

class Pair<K, V> {
    K key;
    V value;

    Pair(K k, V v) {
        key = k;
        value = v;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }
}

class SparseTable {
    private final int[][] st;

    SparseTable(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            st = new int[1][1];
            return;
        }

        st = new int[bitLength(n)][n];
        System.arraycopy(nums, 0, st[0], 0, n);

        for (int i = 1; i < st.length; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                st[i][j] = Math.max(
                        st[i - 1][j],
                        st[i - 1][j + (1 << (i - 1))]
                );
            }
        }
    }

    int query(int l, int r) {
        int len = r - l + 1;
        int k = bitLength(len) - 1;
        return Math.max(
                st[k][l],
                st[k][r - (1 << k) + 1]
        );
    }

    private int bitLength(int x) {
        return 32 - Integer.numberOfLeadingZeros(x);
    }
}

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int ones = 0;
        for (char c : s.toCharArray())
            if (c == '1')
                ones++;

        Pair<List<Group>, int[]> info = getZeroGroups(s);

        List<Group> zeroGroups = info.getKey();
        int[] zeroGroupIndex = info.getValue();

        List<Integer> ans = new ArrayList<>();

        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++)
                ans.add(ones);
            return ans;
        }

        SparseTable st = new SparseTable(getZeroMergeLengths(zeroGroups));

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int left =
                    zeroGroupIndex[l] == -1
                            ? -1
                            : zeroGroups.get(zeroGroupIndex[l]).length
                            - (l - zeroGroups.get(zeroGroupIndex[l]).start);

            int right =
                    zeroGroupIndex[r] == -1
                            ? -1
                            : r - zeroGroups.get(zeroGroupIndex[r]).start + 1;

            Pair<Integer, Integer> p =
                    mapToAdjacentGroupIndices(
                            zeroGroupIndex[l] + 1,
                            s.charAt(r) == '1'
                                    ? zeroGroupIndex[r]
                                    : zeroGroupIndex[r] - 1);

            int start = p.getKey();
            int end = p.getValue();

            int best = ones;

            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {

                best = Math.max(best, ones + left + right);

            } else if (start <= end) {

                best = Math.max(best, ones + st.query(start, end));
            }

            if (s.charAt(l) == '0'
                    && zeroGroupIndex[l] + 1
                    <= (s.charAt(r) == '1'
                    ? zeroGroupIndex[r]
                    : zeroGroupIndex[r] - 1)) {

                best = Math.max(
                        best,
                        ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0'
                    && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {

                best = Math.max(
                        best,
                        ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(best);
        }

        return ans;
    }

    private Pair<List<Group>, int[]> getZeroGroups(String s) {

        List<Group> groups = new ArrayList<>();
        int[] idx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '0') {

                if (i > 0 && s.charAt(i - 1) == '0')
                    groups.get(groups.size() - 1).length++;
                else
                    groups.add(new Group(i, 1));
            }

            idx[i] = groups.size() - 1;
        }

        return new Pair<>(groups, idx);
    }

    private int[] getZeroMergeLengths(List<Group> groups) {

        if (groups.size() <= 1)
            return new int[0];

        int[] res = new int[groups.size() - 1];

        for (int i = 0; i + 1 < groups.size(); i++) {
            res[i] = groups.get(i).length + groups.get(i + 1).length;
        }

        return res;
    }

    private Pair<Integer, Integer> mapToAdjacentGroupIndices(int l, int r) {
        return new Pair<>(l, r - 1);
    }
}