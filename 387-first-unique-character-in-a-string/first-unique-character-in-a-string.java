class Solution {
    public int firstUniqChar(String s) {
    //  Map<Character, Integer> map = new HashMap<>();
    //   for(char c : s.toCharArray())   {
    //     map.put(c , map.getOrDefault(c, 0) + 1);
       
    //   }
    //   for(int i =0; i < s.length(); i++) {
    //     char c = s.charAt(i);
    //     if(map.get(c) == 1) {
    //         return i;
    //     }
    //   }
    //   return -1;
    Queue<Integer> queue = new LinkedList<>();
    Map<Character, Integer> map = new HashMap<>();
    for(int i =0; i < s.length(); i++) {
        char c = s.charAt(i);
        map.put(c , map.getOrDefault(c,0) + 1);
        queue.offer(i);
    
      while(!queue.isEmpty() && map.get(s.charAt(queue.peek())) > 1) {
        queue.poll();
      }
    }
    // 4. After checking all characters, if the queue is empty, there are no unique characters.
    // If it's not empty, the index at the front of the queue is our winner.
    if(queue.isEmpty()) {
        return -1;
    } else {
        return queue.peek();
    }
        
}
}
