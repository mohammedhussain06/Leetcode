class Solution {
    public int romanToInt(String s) {
        int total = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int currentVal = getVal(s.charAt(i));
            
            // Look ahead: If there is a next character, and its value is larger than 
            // our current character's value, we are dealing with a subtractive case (like IV or XC).
            if (i + 1 < n && currentVal < getVal(s.charAt(i + 1))) {
                total -= currentVal; // Subtract instead of add
            } else {
                total += currentVal; // Normal case: add it to the running sum
            }
        }
        
        return total;
    }

    // A simple, raw switch-statement helper that executes completely on stack memory
    private int getVal(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}