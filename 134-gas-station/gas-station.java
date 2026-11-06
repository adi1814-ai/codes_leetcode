class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        
        // If total gas is less than total cost, completing the circuit is impossible
        if (totalGas < totalCost) {
            return -1;
        }
        
        int currentTank = 0;
        int startStation = 0;
        
        for (int i = 0; i < gas.length; i++) {
            currentTank += gas[i] - cost[i];
            
            // If tank drops below 0, we cannot reach the next station from our current start
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0;
            }
        }
        
        return startStation;
    }
}