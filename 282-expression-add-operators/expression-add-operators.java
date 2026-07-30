class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        dfs(num, target, 0, "", 0, 0);
        return ans;
    }
    private void dfs(String num, int target,
                     int index,
                     String expr,
                     long currValue,
                     long prevOperand) {
        if (index == num.length()) {
            if (currValue == target)
                ans.add(expr);
            return;
        }
        long currNum = 0;
        for (int i = index; i < num.length(); i++) {
            if (i > index && num.charAt(index) == '0')
                break;
            currNum = currNum * 10 + (num.charAt(i) - '0');
            String currStr = num.substring(index, i + 1);
            if (index == 0) {
                dfs(num, target,
                        i + 1,
                        currStr,
                        currNum,
                        currNum);
            } else {
                dfs(num, target,
                        i + 1,
                        expr + "+" + currStr,
                        currValue + currNum,
                        currNum);
                dfs(num, target,
                        i + 1,
                        expr + "-" + currStr,
                        currValue - currNum,
                        -currNum);
                dfs(num, target,
                        i + 1,
                        expr + "*" + currStr,
                        currValue - prevOperand + prevOperand * currNum,
                        prevOperand * currNum);
            }
        }
    }
}