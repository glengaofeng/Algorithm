package org.g5g.algorithm.divideandconquer;
import java.util.Arrays;

public class Combination {
	public static void perform(int n, int k) {
		int a[] = new int[k];
		a[0] = k;
		recursiveCore(n, k, a);
	}

	private static void recursiveCore(int n, int k, int[] a) {
		for (int i = n; i >= k; i--) {
			a[k - 1] = i;
			if (k > 1) {
				recursiveCore(i - 1, k - 1, a);
			} else {
				System.out.println(Arrays.toString(a));
			}
		}
	}

	public static void main(String args[]) {
		perform(5, 3);
	}
}
