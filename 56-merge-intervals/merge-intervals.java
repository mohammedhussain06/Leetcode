import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merge = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merge.add(currentInterval);
        for (int i = 0; i < intervals.length; i++) {
            int currentEnd = currentInterval[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];
            if (nextStart <= currentEnd) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = intervals[i];
                merge.add(currentInterval);
            }
        }
        return merge.toArray(new int[merge.size()][]);
    }
}