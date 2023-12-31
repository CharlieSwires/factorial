package factorial;

import java.math.BigInteger;
import java.time.LocalTime;

public class FactorialCalculator {
	public static void main(String[] args) {
		int n = 0;
		assert(calculateFactorial(n) == 1);
		assert(calculateFactorialBigInt(n).equals(new BigInteger("1")));
		assert(calculateFactorialNotRecursive(n).equals(new BigInteger("1")));
		assert(calculateFactorialTimeout(n, 1L).equals(new BigInteger("1")));
		n = 1;
		assert(calculateFactorial(n) == 1);
		assert(calculateFactorialBigInt(n).equals(new BigInteger("1")));
		assert(calculateFactorialNotRecursive(n).equals(new BigInteger("1")));
		assert(calculateFactorialTimeout(n, 1L).equals(new BigInteger("1")));
		n = 2;
		assert(calculateFactorial(n) == 2);
		assert(calculateFactorialBigInt(n).equals(new BigInteger("2")));
		assert(calculateFactorialNotRecursive(n).equals(new BigInteger("2")));
		assert(calculateFactorialTimeout(n, 1L).equals(new BigInteger("2")));
		n = 3;
		assert(calculateFactorial(n) == 6);
		assert(calculateFactorialBigInt(n).equals(new BigInteger("6")));
		assert(calculateFactorialNotRecursive(n).equals(new BigInteger("6")));
		assert(calculateFactorialTimeout(n, 1L).equals(new BigInteger("6")));
		n = 20; // Change this to the desired number
		long factorial = calculateFactorial(n);
		System.out.println("Factorial of " + n + " is: " + factorial);
		n = 100; // Change this to the desired number
		BigInteger factorialbi = calculateFactorialBigInt(n);
		System.out.println("Factorial of " + n + " is: " + factorialbi);
		n = 100; // Change this to the desired number
		BigInteger factorialnr = calculateFactorialNotRecursive(n);
		System.out.println("Factorial of " + n + " is: " + factorialnr);
		n = 1046911; // Change this to the desired number
		BigInteger factorialto = calculateFactorialTimeout(n, 5L*60L);
		System.out.println("Factorial of " + n + " is: " + factorialto);
	}

	public static long calculateFactorial(int n) {
		if (n < 0 || n > 20) throw new IllegalArgumentException("n can't be negative or over 20! is the largest long can store");
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

	public static BigInteger calculateFactorialTimeout(int n, long timeout) {
		if (n < 0 || timeout < 0) {
			throw new IllegalArgumentException("n can't be negative nor timeout negative");
		}
		FactorialCalculator f = null;
		MyTimer t = null;
		f = new FactorialCalculator();
		t = f.new MyTimer();

		BigInteger result = BigInteger.ONE;
		t.start();
		t.go(timeout);

		for (int i = 2; i <= n; i++) {
			t.iteration(i);
			result = result.multiply(BigInteger.valueOf(i));
		}
		t.stopt();

		return result;
	}

	class MyTimer extends Thread{
		boolean started = false;
		long s = 0;
		int i = 0;
		public void go(long seconds) {
			s = seconds;
			started = true;
		}
		public void iteration(int iter) {
			i = iter;
		}
		public void stopt() {
			started = false;
		}
		public boolean getStarted() {
			return started;
		}
		public void run() {
			while(!started) {
				try {
					MyTimer.sleep(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			LocalTime startTime = LocalTime.now();
			while(started) {
				try {
					if (LocalTime.now().minusSeconds(s).compareTo(startTime) == 1) {
						started = false;
						System.out.println("Took too long only "+s+" seconds allowed. It reached: "+i+" iterations.");
						System.exit(0);
					}
					MyTimer.sleep(10L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

