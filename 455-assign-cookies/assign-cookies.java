class Solution {
    public int findContentChildren(int[] g, int[] s) {
       //------ GREEDY CHOICE-------
       //The greedy choice is: Always satisfy the easiest-to-please child using the smallest cookie that works.

    //To do this, you sort both arrays from smallest to largest. Then, you look at the first child (the one who wants the least) and the first cookie (the smallest one you have).

    //If the cookie satisfies them, give it to them.

    //If the cookie is too small, throw it away. It’s too small for this child, which means it will definitely be too small for every other child down the line.
    Arrays.sort(g);
    Arrays.sort(s);

    int childPointer =0; //Start a pointer for the easiest-to-please child, intiialising it as 0 , so it is pointing directly at the easiest child to safety.
    int cookiePointer =0; //Start a pointer for the smallest cookie,intiialising it as 0 , so it means we are looking at our smallest available cookie.

    while(childPointer < g.length && cookiePointer < s.length) {
        if(s[cookiePointer] >= g[childPointer]) {
            childPointer++; // child is successfully fed,Because this pointer only moves when a child is happy, *childPointer* naturally counts the total number of happy children.
        }
        cookiePointer ++;
    }
    return childPointer;
    }
}