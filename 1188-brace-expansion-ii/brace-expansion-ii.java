import java.util.*;

class Solution {
    private int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> resultSet = parseExpr(expression);
        
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Handles union separated by commas: term (',' term)*
    private Set<String> parseExpr(String s) {
        Set<String> res = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {
            Set<String> termSet = parseTerm(s);
            res.addAll(termSet);

            if (index < s.length() && s.charAt(index) == ',') {
                index++; // consume ','
            }
        }

        return res;
    }

    // Handles concatenation / multiplication: factor factor*
    private Set<String> parseTerm(String s) {
        // Multiplicative identity: set with an empty string
        Set<String> res = new HashSet<>();
        res.add("");

        while (index < s.length() && s.charAt(index) != '}' && s.charAt(index) != ',') {
            Set<String> factorSet = parseFactor(s);
            res = multiply(res, factorSet);
        }

        return res;
    }

    // Handles base units: letter string or '{' expr '}'
    private Set<String> parseFactor(String s) {
        Set<String> res = new HashSet<>();

        if (s.charAt(index) == '{') {
            index++; // consume '{'
            res = parseExpr(s);
            index++; // consume '}'
        } else {
            StringBuilder sb = new StringBuilder();
            while (index < s.length() && Character.isLowerCase(s.charAt(index))) {
                sb.append(s.charAt(index++));
            }
            res.add(sb.toString());
        }

        return res;
    }

    // Cartesian product concatenation: A x B
    private Set<String> multiply(Set<String> setA, Set<String> setB) {
        Set<String> combined = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                combined.add(a + b);
            }
        }
        return combined;
    }
}