class Solution {
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0 || t==null || t.length() == 0) {
            return "";
        }

        int [] count = new int[128];  
        for(char c :t.toCharArray()) {
            count[c]++;
        } 

        int left = 0; int right =0;
        int reqchar =t.length();
        int minlen = Integer.MAX_VALUE;
        int startindex =0;

 //EXPANDING THE WINDOW
        while(right <s.length()) {
            char rightchar = s.charAt(right);

            if(count[rightchar] > 0) {
                reqchar--; // we needed , so we decreased it
            }
            count[rightchar]--;

            right++;
        
 //SHRINKING THE WINDOW
        while(reqchar == 0) {
            if(right-left < minlen) {
                minlen = right - left; // saves it
                startindex = left; // saves the starting index also
            }

            // Removing the left character
            char leftchar =s.charAt(left);
            count[leftchar]++;

            if(count[leftchar] > 0) {
                reqchar++;
            }
            left++;
        }
        }
       return minlen == Integer.MAX_VALUE ? "" : s.substring(startindex, startindex + minlen);

    }
}