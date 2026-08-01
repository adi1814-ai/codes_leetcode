class StockSpanner {
      Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) { // day 1: price 100 , day 2: price 80, ........ 
        int span = 1; // start by assuming it's span is 1(whic represent today)
        while(!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});
        return span;  // [ [100, 1] ] , [ [80, 1] ] , ........  
        //(Bottom of stack is on the left, top is on the right)
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */