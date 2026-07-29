class Solution {
    public String removeDigit(String number, char digit) {
        

// For each character at index i:
//     Is this character equal to 'digit'?
//     ├─ YES:
//     │   ├─ Save 'i' as the last known position.
//     │   └─ Is the NEXT character strictly BIGGER than 'digit'?
//     │       ├─ YES: Cut this character out and RETURN immediately! (Best possible move)
//     │       └─ NO:  Keep scanning...
//     └─ NO:
//         Continue loop...

// If loop finishes without returning early:
//     Cut out the LAST occurrence saved earlier.

    int lastIndex= -1;
    int n = number.length();

    for(int i =0; i< n;i ++) {
        if(number.charAt(i) == digit) {
            lastIndex = i;

            if(i + 1 < n && number.charAt(i) < number.charAt(i+1)) {
                return number.substring(0, i) + number.substring(i+ 1);
            }
        }
    }
       return number.substring(0,lastIndex) + number.substring(lastIndex + 1);
    }
}