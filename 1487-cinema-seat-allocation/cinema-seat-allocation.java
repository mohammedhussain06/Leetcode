class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        java.util.HashMap<Integer, Integer> rows = new java.util.HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];
            if (s >= 2 && s <= 9) {
                int bit = 1 << (s - 2);
                rows.put(row, rows.getOrDefault(row, 0) | bit);
            }
        }
        long answer = 2L * (n - rows.size());
        int left = 0b00001111;
        int middle = 0b00111100;
        int right = 0b11110000;
        for (int mask : rows.values()) {
            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;
            if (canLeft && canRight) {
                answer += 2;
            } else if (canLeft || canMiddle || canRight) {
                answer += 1;
            }
        }
        return (int) answer;
    }
}