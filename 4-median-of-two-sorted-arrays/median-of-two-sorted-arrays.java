class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //We always want nums1 to be the smaller array to prevent the program from crashing when we calculate cuts later. If nums1 is accidentally the larger one, we just restart the function and swap their places.
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft =(m + n + 1) / 2; //This calculates exactly how many total numbers belong on the Left Half of our dividing line. Adding 1 before dividing by 2 is a math trick that ensures this single line works perfectly whether the total length is an odd or even number.

        int low =0; // tAKE ABsolutely nothing from nums1
        int high = nums1.length;//take every single item from nums 1.
        while(low <= high) {
            int cut1 = low +(high - low) / 2; // cut1 is our guess
            int cut2 = totalLeft - cut1;
            
            int maxLeft1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int minRight1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int maxLeft2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int minRight2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];
            //The Meaning: We need to look at the numbers directly to the left and right of our cut. However, if our cut is at the very beginning of the array (cut == 0), there is no number on the left. If we try to look for one, the program crashes.

           //To fix this, we fake it: if there is nothing on the left, we pretend it is negative infinity (Integer.MIN_VALUE). If the cut is at the very end and there is nothing on the right, we pretend it is positive infinity (Integer.MAX_VALUE). This allows our math checks to keep working safely.

         if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
            if ((m + n) % 2 == 0) {
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
    
        return 0.0;
    }
}