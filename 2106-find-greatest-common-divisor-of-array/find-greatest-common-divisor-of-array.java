class Solution {
    public int findGCD(int[] nums) {
       
        
        int min = nums[0], max = nums[0];

        for(int num : nums) {
            if(num < min) {
                min = num;
            }
            if(num > max) {
                max = num;
            }
        }
        return gcd(min , max);
    }
       private int gcd(int a, int b) {
        while(b != 0){
            int temp = b;
            b = a % b;// this is finding the remainder (a mod b)
            a = temp; // this is "shift" step
        }
        return a; // when b becomes 0, 'a' holds the GCD
    }
    }

    