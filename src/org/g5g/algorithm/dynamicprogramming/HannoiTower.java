package org.g5g.algorithm.dynamicprogramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")

public class HannoiTower {

	public static final int DISK_NUMBER = 5;
	public static int[] f = new int[DISK_NUMBER + 1];
	public static int[] k = new int[DISK_NUMBER + 1];

	static {
		f[1] = 1; // f(n)=min<k=2..n-1>(2f(k)+2^(n-k)-1)
		k[1] = 0;
		for (int idx = 2; idx <= DISK_NUMBER; idx++) {
			int k = HannoiTower.k[idx - 1];
			long temp = 2 * f[k] + (1L << (idx - k));
			for (int i = k + 1; i < idx - 1; i++) {
				long newValue = 2 * f[i] + (1L << (idx - i));
				if (temp > newValue) {
					temp = newValue;
					k = i;
				}
			}
			HannoiTower.k[idx] = k;
			f[idx] = 2 * f[k] + (1 << (idx - k)) - 1;
		}
	}

	public static List<?>[] poles = new List<?>[] {	
		new LinkedList<Integer>(), new LinkedList<Integer>(),
		new LinkedList<Integer>(), new LinkedList<Integer>() };

	static {
		for (int i = DISK_NUMBER; i > 0; i--) {
			((List<Integer>) poles[0]).add(i);
		}
	}

	public static void doMove(char source, char target) {
		if (poles[source - 'A'].size() > 0) {
			Integer tmpDisk = (Integer) poles[source - 'A'].get(poles[source - 'A'].size() - 1);
			poles[source - 'A'].remove(poles[source - 'A'].size() - 1);
			((List<Integer>) poles[target - 'A']).add(tmpDisk);
			for (int i = 0; i < 4; i++) {
				System.out.println((char) ('A' + i) + ":" + Arrays.toString(poles[i].toArray()));
			}
			System.out.println("--------------------------");
		}
	}

	public static void resolve4Poles(int diskNumber, char source, char temp1, char temp2, char target) {
		if (diskNumber <= 1) {
			System.out.println(source + "->" + target);
			doMove(source, target);
		} else {
			int k = HannoiTower.k[diskNumber];
			resolve4Poles(k, source, target, temp2, temp1);
			resolve3Poles(diskNumber - k, source, temp2, target);
			resolve4Poles(k, temp1, source, temp2, target);
		}
	}

	public static void resolve3Poles(int diskNumber, char source, char temp, char target) {
		if (diskNumber == 1) {
			System.out.println(source + "->" + target);
			doMove(source, target);
		} else {
			resolve3Poles(diskNumber - 1, source, target, temp);
			System.out.println(source + "->" + target);
			doMove(source, target);
			resolve3Poles(diskNumber - 1, temp, source, target);
		}
	}

	public static void main(String[] args) {
		resolve4Poles(DISK_NUMBER, 'A', 'B', 'C', 'D');
	}
}
