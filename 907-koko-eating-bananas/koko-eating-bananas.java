class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //The absolute minimum speed Koko can eat is 1 banana per hour.

        //the maximum speed --> is the size of the largest single pile in the array.
      int start =0;
      int end =0;
      int ans =0;
      int n = piles.length;
       long sum =0;
      for(int i =0;i<n;i++) {
        sum += piles[i];
        end = Math.max(end,piles[i]);
      }
      start = (int)(sum / h);
      if(start == 0)
      start = 1;
      while (start <= end) {
        int mid = start +(end - start) / 2; // mid containes how many bananas is consuming per hour 
        long total_time =0;
        for(int i =0;i<n;i++) {
            total_time += piles[i] / mid; 
            if(piles[i] % mid !=0)  {
                total_time++;
            }
        }
            if(total_time > h) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        } 
         
      
      return ans;
    }
}