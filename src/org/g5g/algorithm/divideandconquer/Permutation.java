package org.g5g.algorithm.divideandconquer;
public class Permutation {
	public static void perform(String str){
		recursiveCore(new StringBuffer(str),new StringBuffer(),str.length());
	}
	
	private static void recursiveCore(StringBuffer str, StringBuffer output, int length) {
		for (int i = 0; i < str.length(); i++) {
			StringBuffer out=new StringBuffer(output);
			char inital = str.charAt(i);
			out.append(inital);
			if (out.length() == length) {
				out.chars().forEach((o) -> System.out.print((char) o + ","));
				System.out.println();
				continue;
			}
			StringBuffer in = new StringBuffer();
			str.chars().filter((c) -> c != inital).forEach((c) -> in.append((char) c));
			recursiveCore(in, out, length);
		}
	}
		
	public static void main(String[] args){
		Permutation.perform("abcdefg");
	}
}
