class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
         
        for(int ast : asteroids) {
            boolean exploded = false;

            while(!stack.isEmpty() && stack.peek() > 0 && ast < 0) {
                   int top = stack.peek();
 //CASE 1: The current asteroid is LARGER than the top stack element
                if(Math.abs(ast) > top) {
                    stack.pop(); // popped out the destroyed asteroid.
                }
//CASE 2: Current asteroid is EQUAL in size 
                else if(Math.abs(ast) == top) {
                    stack.pop();
                    exploded = true; //Both asteroids destroyed each other , popped out the top asteroid.
                    break; // break out of the collision loop because the current asteroid (ast) is now destroyed.
                }
//CASE 3: The current asteroid is SMALLER than the top stack element
                else {
                    exploded = true;
                    break; // have to break out of the collision loop , as current asteroid (ast) is exploded
                }
            }
                if(!exploded) { // if none asteroid , the current asteroid destroyed
                    stack.push(ast); // pushing the survived asteroid into the stack 
                }
            }
            int[] result = new int[stack.size()];
            for(int i = result.length-1; i >=0; i--) {
                result[i] = stack.pop();  
            }          
        return result;
    }
}