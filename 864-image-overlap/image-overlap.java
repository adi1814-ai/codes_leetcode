
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        
        // Collect coordinates of all 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }
        
        // Map to count frequency of each translation vector
        Map<String, Integer> countMap = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int rowDiff = p2[0] - p1[0];
                int colDiff = p2[1] - p1[1];
                
                String translationKey = rowDiff + "," + colDiff;
                int currentCount = countMap.getOrDefault(translationKey, 0) + 1;
                countMap.put(translationKey, currentCount);
                
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }
        
        return maxOverlap;
    }
}