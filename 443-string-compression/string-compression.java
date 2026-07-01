class Solution {
    public int compress(char[] chars) {
        int write = 0; // Pointer to write the compressed characters
        int i = 0;     // Pointer to traverse the array

        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;

            // Count the consecutive occurrences of the current character
            while (i < chars.length && chars[i] == currentChar) {
                count++;
                i++;
            }

            // Step 1: Write the character itself
            chars[write++] = currentChar;

            // Step 2: If the group length is greater than 1, write the count
            if (count > 1) {
                // Convert count to string to handle multi-digit numbers (e.g., 12 -> '1', '2')
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }

        // Return the new length of the compressed array
        return write;
    }
}