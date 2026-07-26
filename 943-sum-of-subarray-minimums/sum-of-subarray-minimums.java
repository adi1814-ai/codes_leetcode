class Solution {
    public int sumSubarrayMins(int[] arr) {
      int MOD =1000000007;
      Stack<Integer> stack = new Stack<>();
      long totalSum =0;
      int n = arr.length;

      for(int i =0; i <= n; i++)   {
        while(!stack.isEmpty() && (i == n || arr[i] < arr[stack.peek()])) {

            int mid = stack.pop();
            int right = i;
            int left = stack.isEmpty()? -1 : stack.peek();

            long totalSubarrays = (mid - left) * (right - mid);
            long finale = totalSubarrays * arr[mid];
            totalSum = (totalSum + finale) % MOD;
        }

        stack.push(i);  
      }

      return (int) totalSum;    
    }  
}