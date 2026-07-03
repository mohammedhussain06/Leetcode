class Solution {
    public String intToRoman(int num) {
        char[] buf = new char[15];
        int pos = 0;
        int thousands = num / 1000;
        while (thousands-- > 0) {
            buf[pos++] = 'M';
        }
        int hundreds = (num % 1000) / 100;
        if (hundreds == 9) {
            buf[pos++] = 'C'; buf[pos++] = 'M';
        } else if (hundreds >= 5) {
            buf[pos++] = 'D';
            while (hundreds-- > 5) buf[pos++] = 'C';
        } else if (hundreds == 4) {
            buf[pos++] = 'C'; buf[pos++] = 'D';
        } else {
            while (hundreds-- > 0) buf[pos++] = 'C';
        }
        int tens = (num % 100) / 10;
        if (tens == 9) {
            buf[pos++] = 'X'; buf[pos++] = 'C';
        } else if (tens >= 5) {
            buf[pos++] = 'L';
            while (tens-- > 5) buf[pos++] = 'X';
        } else if (tens == 4) {
            buf[pos++] = 'X'; buf[pos++] = 'L';
        } else {
            while (tens-- > 0) buf[pos++] = 'X';
        }
        int ones = num % 10;
        if (ones == 9) {
            buf[pos++] = 'I'; buf[pos++] = 'X';
        } else if (ones >= 5) {
            buf[pos++] = 'V';
            while (ones-- > 5) buf[pos++] = 'I';
        } else if (ones == 4) {
            buf[pos++] = 'I'; buf[pos++] = 'V';
        } else {
            while (ones-- > 0) buf[pos++] = 'I';
        }
        return new String(buf, 0, pos);
    }
}