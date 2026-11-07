class Solution {
    public int romanToInt(String s) {
        int total = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int currValue = getValue(s.charAt(i));
            
            // If there is a next character and current value is less than the next, subtract it
            if (i < s.length() - 1 && currValue < getValue(s.charAt(i + 1))) {
                total -= currValue; // Make sure this is minus!
            } else {
                total += currValue;
            }
        }
        
        return total;
    }
    
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}