class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length ;

        int left =0;
        int right = n-1;

        while(left <= right) {
            int mid = left +(right - left) / 2;

            // finding the maximum element in the current middle coloumn
            int maxRow = 0;
            for(int i =0;i <m; i++) {
                if(mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }
           // Get values of left and right neighbors
           int leftNeighbor = (mid - 1 >= 0) ? mat[maxRow][mid - 1] : -1;
           int rightNeighbor =(mid + 1 < n) ? mat[maxRow][mid + 1] : -1;

           if(mat[maxRow][mid] > leftNeighbor && mat[maxRow][mid] > rightNeighbor) {
                return new int[]{maxRow, mid};
            }

            else if(mat[maxRow][mid] < leftNeighbor) {
              right = mid - 1;
            } else {
                left = mid +1;
                }
            }
            return new int[]{-1, -1};
    }
}