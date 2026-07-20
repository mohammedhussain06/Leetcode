import java.util.ArrayList;
import java.util.List;
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n];
        int fact = 1;
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
            factorial[i] = fact; 
            numbers.add(i);
        }
        numbers.add(n); 
        k = k - 1;
        StringBuilder result = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int blockSize = factorial[i]; 
            int index = k / blockSize;    
            result.append(numbers.get(index));
            numbers.remove(index);        
            k %= blockSize;               
        }
        return result.toString();
    }
}