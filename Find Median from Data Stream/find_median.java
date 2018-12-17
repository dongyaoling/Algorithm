/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
 * So the median is the mean of the two middle value.
 * For example,
 * [2,3,4], the median is 3
 * [2,3],   the median is (2 + 3) / 2 = 2.5
 */

class MedianFinder {
    PriorityQueue<Integer> l = new PriorityQueue<>(
    new Comparator<Integer>(){
        @Override
        public int compare(Integer a, Integer b){
            return b - a;
        }
    }
    );
    PriorityQueue<Integer> r = new PriorityQueue<>();
    double mid = 0.0;
    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        if (l.isEmpty() && r.isEmpty()){
            l.add(num);
            mid = (double)l.peek();
        }else if (l.size() - r.size() == 1){
            if (num < l.peek()){
                l.add(num);
                r.add(l.poll());
            }else r.add(num);
            mid = (double)(l.peek() + r.peek()) / 2;
        }else if (l.size() == r.size()){
            if ((double)num < mid) l.add(num);
            else{
                r.add(num);
                l.add(r.poll());
            }
            mid = (double)l.peek();
        }
    }
    
    public double findMedian() {
        return mid;
    }
}