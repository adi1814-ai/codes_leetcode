class Solution {
    public int splitArray(int[] nums, int k) {
       // by taking an example 
       //----The Fair Manager----
       
       //The Array (nums): This is a list of chores that need to be done in a specific sequence. The numbers represent how many hours each chore takes.

       //The Integer (k): This is the number of workers you have available for the day.

       //"Largest sum of any subarray" = The workload of the worker who was assigned the most hours.

       //"Minimized" = Distributing the tasks so that this specific worker's burden is as light as possible.  

      // example : chores(nums): [7, 2, 5, 10,8]
     //            workers (k): 2
     //            Worker 1 gets -- [7] = 7 hours
     //            Worker 2 gets --[2, 5, 10, 8] = 25 hours
     //The "Largest Sum": Worker 2 is overloaded with 25 hours of work, can do better.

     //The better one is : Worker 1 gets -- [7, 2, 5] = 14 hours 
     //                    Worker 2 gets -- [10, 8] = 18 hours   
     //    The "Largest Sum": The heaviest workload here is 18 hours.
     int maxChore =0;
     int totalChoreSum =0;
     
        //setting our boundaries. The correct answer has to be somewhere between the biggest single chore and the sum of all chores.    
     
     for(int chore:nums) {
        maxChore =Math.max(maxChore, chore);
        totalChoreSum += chore;
     }
      int low = maxChore;
     int high = totalChoreSum; 
     int bestAnswer = totalChoreSum; 

     while(low <= high) {
        int mid = low + (high - low) / 2;
        if(canDivide(nums , k , mid))  {//if I tell my workers they cannot work more than mid hours, can we still finish the project with k workers?"
            bestAnswer = mid; // storing the ebst answer so far
            high = mid -1; // we force our boundaries to see for other even lower workload
        } else {
            low = mid +1; //If the answer is No (meaning it took too many workers to finish the job under that strict limit), our guess was too small. We adjust our boundary to guess higher numbers next time.
        }
     }
     return bestAnswer;
    }
    private boolean canDivide(int [] nums , int workers , int maxLoad) {
        int currentWorkerLoad = 0;
        int workersUsed = 1;
        for (int chore : nums) {
            if(currentWorkerLoad + chore > maxLoad) { //We check: "If I give this next chore to the current worker, will they exceed the allowed limit?"
                      workersUsed++;// if yes then the current worker is full. we have to call in the enxt worker
                      currentWorkerLoad = chore; //We assign this chore to the brand new worker instead.
            } else {
                currentWorkerLoad += chore; //if now , (the current worker still handle more work, we just add the chore for him)
            }
         }
         return workersUsed <= workers; //At the end of the day, did we use more workers than we actually have? If we used fewer or exactly k workers, this returns true (our guess is possible!). If it took too many workers, it returns false (our guess limit was too strict).
    }
}