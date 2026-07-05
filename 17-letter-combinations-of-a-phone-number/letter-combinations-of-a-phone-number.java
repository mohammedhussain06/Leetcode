class Solution {
    private final String[] phoneMap = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        generateCombination(0, digits, new StringBuilder(), result);
        return result;
    }
    private void generateCombination(int index, String digits, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        int digitValue = digits.charAt(index) - '0';
        String letters = phoneMap[digitValue];
        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));
            generateCombination(index + 1, digits, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}