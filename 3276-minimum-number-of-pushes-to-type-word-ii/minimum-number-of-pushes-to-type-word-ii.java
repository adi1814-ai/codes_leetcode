class Solution {
    public int minimumPushes(String word) {
        int [] freq = new int[26];

        for(char c: word.toCharArray()) {
            freq[c - 'a']++; //c - 'a' converts a character into a 0-based array index (e.g., 'a' - 'a' = 0, 'b' - 'a' = 1).
        }
            Arrays.sort(freq);

            int totalPushes = 0;
            int letters = 0;

            for(int i = 25; i>=0; i--) {
                if(freq[i] == 0) break;

                int pushCount = (letters / 8) + 1;
                totalPushes += freq[i] * pushCount;

                letters++; 
            }
         return totalPushes;
    }
}