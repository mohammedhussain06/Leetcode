class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String currentStr = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder nextBuilder = new StringBuilder();
            int count = 1;
            for (int j = 0; j < currentStr.length(); j++) {
                if (j + 1 < currentStr.length() && currentStr.charAt(j) == currentStr.charAt(j + 1)) {
                    count++;
                } else {
                    nextBuilder.append(count);
                    nextBuilder.append(currentStr.charAt(j));
                    count = 1;
                }
            }
            currentStr = nextBuilder.toString();
        }
        return currentStr;
    }
}