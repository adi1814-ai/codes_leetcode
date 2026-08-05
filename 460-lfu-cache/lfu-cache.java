class LFUCache {
    HashMap<Integer, Integer> vals; // This is our standard map. It connects the key directly to the value (like finding the contents of a book).
    HashMap<Integer, Integer> counts; // This map connects a key to its current frequency (how many times the book was read).
    HashMap<Integer, LinkedHashSet<Integer>> lists; // This is the bucket system. The key is the frequency, and the value is a LinkedHashSet of all cache keys that currently have that frequency. We use a LinkedHashSet because it maintains the order elements are added, allowing us to find the oldest one instantly for the LRU tie-breaker.
    int cap; // Stores the maximum number of items the cache can hold
    int min; // Constantly tracks the lowest frequency currently in the cache, so that we get to know which bucket to look at when it's time to evict an item.
    public LFUCache(int capacity) {
        cap = capacity;
        min = -1;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>()); // create the bucket for "Frequency 1", because we know every brand new item will start there.
    }
    
    public int get(int key) { // ( Reading a Value )
        // check the vals map , if the key doesnt exist
        if(!vals.containsKey(key)) {
            return -1; // returning -1 as requested by the problem
        }
        int count = counts.get(key); // loop up how many times this key has been used so far.
        counts.put(key, count + 1); // update the frequency by adding 1

        lists.get(count).remove(key); // it is no longer belongs to it's old freq bucket , we remove the key from the LinkedHashSet of it's old count

        if(count ==  min && lists.get(count).isEmpty()) {
            min ++; // if the condition is true , then it means the new minimum frequency for the entire cache has gone up by 1. So, we increase min.
        }
            if(!lists.containsKey(count + 1)) {
                lists.put(count + 1, new LinkedHashSet<>());
            }
            lists.get(count + 1).add(key);
        
        return vals.get(key);
    }
    
    public void put(int key, int value) {
        if(cap <= 0) { // Edge case
            return;
        }
        if(vals.containsKey(key)) {
            // update 
            vals.put(key, value);
            get(key); // calling the function Because updating an item counts as using - increase the frequency and move the item to the correct bucket. 
            return;
        }

        if(vals.size() >= cap) {
            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict); 
            // lists.get(min) -->  goes straight to the bucket with the lowest frequency.
            // .iterator().next() --> asks the LinkedHashSet for its very first item. Because of how LinkedHashSet works, the first item is always the oldest one added (the Least Recently Used). 
            vals.remove(evict);
            counts.remove(evict); //removing from all three , to free up space
        }

        //Insertion case
        //Now there is space , we can add a brand  new item
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */