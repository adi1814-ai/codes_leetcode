class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        
        // The problem reduces to finding the number of combinations: C(n + k - 1, 2 * k) % MOD
        int N = n + k - 1;
        int R = 2 * k;

        if (R > N) return 0;

        long num = 1;
        long den = 1;

        for (int i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        // Modular inverse of den using Fermat's Little Theorem
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long base, long exp) {
        return power(base, exp - 2, exp);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}