class Solution {
    public int findKthPositive(int[] arr, int k) {
        //Because the array is sorted, the count of missing numbers only grows as you move to the right.

    // ACTUAL VALUE - EXPECTED VALUE = 7 - 4 = 3
        int left =0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) /2;

            int missingNumber = arr[mid] -(mid+1);

            if(missingNumber < k) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return left + k;
    }
}
