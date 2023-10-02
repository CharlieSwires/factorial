package factorial;

import java.math.BigInteger;

public class FactorialCalculator {
    public static void main(String[] args) {
        int n = 20; // Change this to the desired number
        long factorial = calculateFactorial(n);
        System.out.println("Factorial of " + n + " is: " + factorial);
        n = 100; // Change this to the desired number
        BigInteger factorialbi = calculateFactorialBigInt(n);
        System.out.println("Factorial of " + n + " is: " + factorialbi);
        n = 100; // Change this to the desired number
        BigInteger factorialnr = calculateFactorialNotRecursive(n);
        System.out.println("Factorial of " + n + " is: " + factorialnr);
    }

    public static long calculateFactorial(int n) {
    	if (n < 0 || n > 20) throw new IllegalArgumentException("n can't be negative and 20! is the largest long can store");
        if (n == 0) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
    
    public static BigInteger calculateFactorialBigInt(int n) {
    	if (n < 0) throw new IllegalArgumentException("n can't be negative");
        if (n == 0) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(n).multiply(calculateFactorialBigInt(n - 1));
        }
    }
    
    public static BigInteger calculateFactorialNotRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n can't be negative");
        }
        
        BigInteger result = BigInteger.ONE;
        
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        
        return result;
    }
}

