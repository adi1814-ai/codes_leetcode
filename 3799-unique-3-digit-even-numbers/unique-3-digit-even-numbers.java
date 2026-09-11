class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) count[d]++;
        
        int ans = 0;
        for (int i = 100; i < 1000; i += 2) {
            if (isValid(i, count)) ans++;
        }
        return ans;
    }
    
    private boolean isValid(int num, int[] count) {
        int[] temp = new int[10];
        while (num > 0) {
            int d = num % 10;
            if (++temp[d] > count[d]) return false;
            num /= 10;
        }
        return true;
    }
}