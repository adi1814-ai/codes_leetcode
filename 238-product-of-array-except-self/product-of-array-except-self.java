class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int [] answer = new int[n];

   // Calculate left products
        answer[0] = 1;
        for(int i = 1; i < n; i ++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
     // calculate the right products and multiply it with the left products
     int right = 1;
     for(int i = n - 1; i >= 0 ;i--) {
        answer[i] = answer[i] * right;
        right *= nums[i]; // updates the right product for the next element to the left
     }  
     return answer;
    }
}