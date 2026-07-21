import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;
        while (i < n) {
            int lineStart = i;
            int lineLength = words[i].length();
            i++;
            while (i < n && lineLength + 1 + words[i].length() <= maxWidth) {
                lineLength += 1 + words[i].length(); 
                i++;
            }
            int lineEnd = i;
            int wordCount = lineEnd - lineStart;
            StringBuilder line = new StringBuilder();
            if (i == n || wordCount == 1) {
                for (int j = lineStart; j < lineEnd; j++) {
                    line.append(words[j]);
                    if (j < lineEnd - 1) {
                        line.append(" ");
                    }
                }
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } 
            else {
                int totalLetters = 0;
                for (int j = lineStart; j < lineEnd; j++) {
                    totalLetters += words[j].length();
                }
                int totalSpaces = maxWidth - totalLetters;
                int gaps = wordCount - 1;
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int j = lineStart; j < lineEnd; j++) {
                    line.append(words[j]);
                    if (j < lineEnd - 1) { 
                        for (int s = 0; s < baseSpaces; s++) {
                            line.append(" ");
                        }
                        if (extraSpaces > 0) {
                            line.append(" ");
                            extraSpaces--;
                        }
                    }
                }
            }
            result.add(line.toString());
        }
        return result;
    }
}