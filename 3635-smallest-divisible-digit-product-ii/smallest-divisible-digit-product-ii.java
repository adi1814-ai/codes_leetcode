class Solution {
    public String smallestNumber(String num, long t) {

        // Step 1: Prime factorize t into powers of 2, 3, 5, 7
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long temp = t;
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }

        if (temp > 1) return "-1"; // Prime factor > 7 impossible with single digits

        int n = num.length();
        int zeroIdx = num.indexOf('0');

        // Check if num itself is valid (only if it contains no zeros)
        if (zeroIdx == -1) {
            int[] req = new int[]{c2, c3, c5, c7};
            for (int i = 0; i < n; i++) {
                removeDigit(req, num.charAt(i) - '0');
            }
            if (req[0] == 0 && req[1] == 0 && req[2] == 0 && req[3] == 0) {
                return num;
            }
        }

        // Limit of digits we can keep from num as prefix
        int validPrefixLen = (zeroIdx != -1) ? zeroIdx : n;

        // Precompute requirements after keeping prefix up to index i-1
        int[][] prefixReq = new int[validPrefixLen + 1][4];
        prefixReq[0] = new int[]{c2, c3, c5, c7};
        for (int i = 0; i < validPrefixLen; i++) {
            prefixReq[i + 1] = prefixReq[i].clone();
            removeDigit(prefixReq[i + 1], num.charAt(i) - '0');
        }

        // Try replacing position i with digit d > num[i]
        // If zeroIdx exists, we can also replace the '0' at zeroIdx with digits 1..9
        int startPos = (zeroIdx != -1) ? zeroIdx : n - 1;

        for (int i = startPos; i >= 0; i--) {
            int currentDigit = (i == zeroIdx) ? 0 : (num.charAt(i) - '0');
            for (int d = currentDigit + 1; d <= 9; d++) {
                int[] curReq = prefixReq[i].clone();
                removeDigit(curReq, d);
                int remLen = n - 1 - i;

                if (getMinLen(curReq) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append(d);
                    sb.append(fillSmallestSuffix(curReq, remLen));
                    return sb.toString();
                }
            }
        }

        // If length n is not enough, expand length
        int minLen = getMinLen(new int[]{c2, c3, c5, c7});
        int targetLen = Math.max(n + 1, minLen);
        return fillSmallestSuffix(new int[]{c2, c3, c5, c7}, targetLen);
    }

    private void removeDigit(int[] req, int d) {
        if (d == 2) { req[0] = Math.max(0, req[0] - 1); }
        else if (d == 3) { req[1] = Math.max(0, req[1] - 1); }
        else if (d == 4) { req[0] = Math.max(0, req[0] - 2); }
        else if (d == 5) { req[2] = Math.max(0, req[2] - 1); }
        else if (d == 6) { req[0] = Math.max(0, req[0] - 1); req[1] = Math.max(0, req[1] - 1); }
        else if (d == 7) { req[3] = Math.max(0, req[3] - 1); }
        else if (d == 8) { req[0] = Math.max(0, req[0] - 3); }
        else if (d == 9) { req[1] = Math.max(0, req[1] - 2); }
    }

    private int getMinLen(int[] req) {
        int c2 = req[0], c3 = req[1], c5 = req[2], c7 = req[3];
        int num8 = c2 / 3; c2 %= 3;
        int num9 = c3 / 2; c3 %= 2;
        int num4 = c2 / 2; c2 %= 2;
        int num6 = (c2 > 0 && c3 > 0) ? 1 : 0;
        if (num6 > 0) { c2--; c3--; }
        return num8 + num9 + num4 + num6 + c2 + c3 + c5 + c7;
    }

    private String fillSmallestSuffix(int[] req, int len) {
        StringBuilder sb = new StringBuilder();
        int remainingLen = len;

        for (int i = 0; i < len; i++) {
            for (int d = 1; d <= 9; d++) {
                int[] nextReq = req.clone();
                removeDigit(nextReq, d);
                if (getMinLen(nextReq) <= remainingLen - 1) {
                    sb.append(d);
                    req = nextReq;
                    remainingLen--;
                    break;
                }
            }
        }
        return sb.toString();
    }
}