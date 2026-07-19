class Solution {
    public long gcdSum(int[] nums) {
        int prefixGcd [] = new int[nums.length];
        int mx =0;
        for(int i =0; i <nums.length; i++) {
            mx = Math.max(mx , nums[i]);
            prefixGcd[i] = gcd(nums[i], mx); //knows the maximum number seen so far (mx), it calculates the Greatest Common Divisor between the current number (nums[i]) and that maximum (mx). It then saves this result into your new array prefixGcd at the exact same position
        }

        Arrays.sort(prefixGcd);
       
         int start = 0;
         int end = prefixGcd.length - 1;
         long sum =0;
         while (start < end) {
            sum = sum +(gcd(prefixGcd[start], prefixGcd[end]));
            start ++;
            end --;
         }
         return sum;
         }
         private int gcd(int a, int b) {
            while(b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
        }
      return a;
    }
}
