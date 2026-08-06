class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time =0;
        // loop through every single person in the queue exaclty once
        for(int i =0; i<tickets.length;i++) {
            if(i <= k) { // If the current person i is in front of or is the person k, they will buy tickets up to tickets[k] times. We use Math.min() because if person i wants fewer tickets than person k, they will simply leave early after buying all their tickets[i]. We add this amount to our total time.
                time += Math.min(tickets[i] , tickets[k]);
            } else { // If the current person i is behind person k, they get one less turn. Once person k buys their final ticket, the process immediately stops for them, and person i doesn't get to go again. So, we cap their maximum ticket purchases at tickets[k] - 1.
               time += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return time;
    }
}