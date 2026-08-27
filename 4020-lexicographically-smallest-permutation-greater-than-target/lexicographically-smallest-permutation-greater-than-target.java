class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        String answer = "";
        for (int i = 0; i < n; i++) {
            int current = target.charAt(i) - 'a';
            for (int c = current + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder candidate = new StringBuilder();
                    candidate.append(target, 0, i);
                    candidate.append((char) ('a' + c));
                    int[] remaining = count.clone();
                    remaining[c]--;
                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            candidate.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }
                    answer = candidate.toString();
                    break;
                }
            }
            if (count[current] == 0) {
                break;
            }
            count[current]--;
        }
        return answer;
    }
}