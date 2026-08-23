class Solution {
    public boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();
        String []ascii = new String[26];
        for(int i=0;i<26;i++) {
            int ch = 'a' + i;
            StringBuilder temp = new StringBuilder();
            int count = 0;
            while(count < 8) {
                temp.append(ch%2);
                count++;
                ch /= 2;
            }
            ascii[i] = temp.reverse().toString();
        }
        for(char ch : s.toCharArray()) {
            int idx = ch-'a';
            sb.append(ascii[idx]);
        }
        String s1 = sb.toString();
        String s2 = sb.reverse().toString();
        return s1.equals(s2);
    }
}
    
