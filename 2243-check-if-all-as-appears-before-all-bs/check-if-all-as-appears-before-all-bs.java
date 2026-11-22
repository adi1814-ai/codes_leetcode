class Solution {
    public boolean checkString(String s) {
        //Intuition :- sorting on basis of ascii code
        
        char[] chars = s.toCharArray();
        Arrays.sort(chars); 
        String sorted = new String(chars); // converting it back
        return sorted.equals(s);
       
    }
}