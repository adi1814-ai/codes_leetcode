import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int multiplesCount = 0;
            for (int mult = f; mult <= maxFactor; mult += f) {
                multiplesCount += factorCount[mult];
            }
            // Exclude item i itself
            gain[i] = multiplesCount - 1;
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(items[a][1], items[b][1]));

        long remBudget = budget;
        long boostedCopies = 0;
        long ans = remBudget / minPrice;

        for (int idx : order) {
            long price = items[idx][1];
            long g = gain[idx];

            if (g > 0 && price <= remBudget) {
                long buyBoosted = Math.min(g, remBudget / price);
                remBudget -= buyBoosted * price;
                boostedCopies += buyBoosted;
            }

            long extraCopies = remBudget / minPrice;
            long total = 2 * boostedCopies + extraCopies;
            if (total > ans) {
                ans = total;
            }
        }

        return (int) ans;
    }
}