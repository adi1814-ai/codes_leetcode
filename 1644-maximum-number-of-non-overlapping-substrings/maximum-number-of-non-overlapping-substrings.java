import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> validIntervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;

            int l = first[c];
            int r = last[c];
            boolean isValid = true;

            for (int i = l; i <= r; i++) {
                int currChar = s.charAt(i) - 'a';
                if (first[currChar] < l) {
                    isValid = false;
                    break;
                }
                r = Math.max(r, last[currChar]);
            }

            if (isValid) {
                validIntervals.add(new int[]{l, r});
            }
        }

        // Sort intervals by their right endpoint to greedily select non-overlapping ones
        validIntervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : validIntervals) {
            int l = interval[0];
            int r = interval[1];

            if (l > prevEnd) {
                result.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return result;
    }
}