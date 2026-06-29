class Solution {
    public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
      backtrack(result , "", 0 , 0, n);
      return result;
    }

    private void backtrack(List<String> result , String currentString , int openCount , int closeCount , int max) {// max is the number of pairs allowed , // currentString is the string which is being build up

    // base condition
    if(currentString.length() == max * 2) { //(max*2 = eg. 2 pairs means total 4 brackets)
        result.add(currentString); // if condition matches , it means the currentString is ready and we can  now add this in our result list.

        return; // backtracking ( it stops the recursive path and forces tghe program to  backtrack up the call stack to explore alternative branches)
    }

    //placing the opening parenthesis
    if(openCount < max) {
        // Recurse deeper: append '(' to the string, and increment the open counter by 1.
        backtrack(result , currentString +"(" , openCount + 1 , closeCount , max);
    }
    // placing the close parenthesis
    if(closeCount < openCount) {
       // we can only add a ')' if the current close count is strictly less than the open count
        backtrack(result , currentString +")" , openCount , closeCount + 1 , max);
    }
}
}