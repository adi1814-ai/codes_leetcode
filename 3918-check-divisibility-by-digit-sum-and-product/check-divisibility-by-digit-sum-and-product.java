class Solution {
    public boolean checkDivisibility(int n) {
        
       int n1 = n;
       int s= 0;
       int p =1;
       int r = 0;
        while(n != 0) {
             r = n %10;
             s = s + r;
             p = p * r;
            n = n / 10;
        }
        if(n1 % (s + p) == 0) {
            return true;
        } 
          return false;
    }
}