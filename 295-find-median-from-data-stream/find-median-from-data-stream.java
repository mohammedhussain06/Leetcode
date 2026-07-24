import java.util.*;

class MedianFinder {

    PriorityQueue<Integer> left;   // Max Heap
    PriorityQueue<Integer> right;  // Min Heap

    public MedianFinder() {

        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }

        // Balance heaps
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        }

        if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {

        if (left.size() == right.size()) {
            return ((double) left.peek() + right.peek()) / 2.0;
        }

        return left.peek();
    }
}