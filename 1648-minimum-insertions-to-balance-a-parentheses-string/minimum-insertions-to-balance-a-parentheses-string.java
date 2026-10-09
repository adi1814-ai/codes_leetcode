class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;
        int n = s.length();

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                open++;
            } else {
                // check if there is a consecutive ')'
                if(i + 1 < n && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                if(open > 0) {
                    open --; //Matched with an exsisting '('
                } else {
                    insertions++; // Missing '(' before '))'
                }
            }
        }
        // Each remaining unmatched '(' needs '))' (2 insertions)
      insertions += open *2;

      return insertions;
    }
}