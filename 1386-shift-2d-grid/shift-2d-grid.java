class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m *n;
        k = k % total; //This line uses the modulo operator (%) to chop off any useless, full-circle shifts. For example, if total = 9 and k = 10, 10 % 9 = 1. We only actually need to shift 1 time.

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for(int i = 0; i < total; i++) { // loop to walk through every empty spot in our new grid, counting from 0 up to the total number of boxes. Think of i as the new spot we are trying to fill.
        int origIndex = (i - k + total) % total; // i - k: To find out, we look backward in time by subtracting the shift amount k.  + total is to prevent negative numbers in java when doing math modulo
        // putting the number in it's new home
        row.add(grid[origIndex / n][origIndex % n]);
       // line 11 meaning 
       //Now we know the 1D position (origIndex) of the number we need, but our original data is trapped in a 2D grid. We have to convert it!

       //origIndex / n: Dividing by the number of columns tells us exactly which row the number is in.

      //origIndex % n: The remainder tells us exactly which column the number is in.

      //row.add(...): We reach into the grid, grab the number at those coordinates, and drop it into our temporary row bucket

        //Starting  a new ROW
        if((i + 1) % n == 0) {
            result.add(row);
            row = new ArrayList<>();
        }
        //(i + 1) represents how many items we have processed so far.

       //% n == 0: We check if the number of items we've processed is perfectly divisible by the column size. If it is, it means our temporary row bucket is completely full!

      //result.add(row): We take the full row and lock it into our final master list.

     //row = new ArrayList<>(): We grab a brand new, empty bucket so the loop can start filling out the next row.

        }
        return result;
    }
}