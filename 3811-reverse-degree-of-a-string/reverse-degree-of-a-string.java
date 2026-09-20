class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;

        for(int i = 0; i <s.length(); i++) {
            char ch = s.charAt(i);

            int reversePosition = 26 - (ch - 'a');

            int stringPos = i + 1;

            totalSum += reversePosition * stringPos;
        }

        return totalSum;
    }
}