class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(s);
        visited.add(s);
        
        boolean found = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                // If valid, this is a minimum-removal answer
                if (isValid(current)) {
                    result.add(current);
                    found = true;
                }
                
                // Don't generate next level once valid strings are found
                if (found) {
                    continue;
                }
                
                // Remove one parenthesis at every possible position
                for (int j = 0; j < current.length(); j++) {
                    if (current.charAt(j) != '(' && current.charAt(j) != ')') {
                        continue;
                    }
                    
                    String next = current.substring(0, j) 
                                + current.substring(j + 1);
                    
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            
            // We found all valid strings at the minimum-removal level
            if (found) {
                break;
            }
        }
        
        return result;
    }
    
    private boolean isValid(String s) {
        int balance = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {
                balance--;
                
                // More ')' than '(' at any point
                if (balance < 0) {
                    return false;
                }
            }
        }
        
        // All '(' must have a matching ')'
        return balance == 0;
    }
}