package org.g5g.algorithm.backtracking;

public class NQueens {
	
	public static void perform(int n){
		System.out.println(recursiveCore(0, 0, 0, (1 << n) - 1,new int[n]));
	}
	
	private static int recursiveCore(int col, int ld, int rd, int queens,int[] board){
		if (col==queens){
			print(board);
			return 1;
		}
		int ret=0;
		for(int pos=queens &(~(col|ld|rd));pos!=0;pos-=(pos&(-pos))){
			board[count1(col)]=lowest1(pos);
			ret+=recursiveCore(col|(pos&(-pos)),(ld|(pos&(-pos)))<<1,(rd|(pos&(-pos)))>>1,queens,board);
		}
		return ret;
	}
	
	private static int count1(int i)  
	{  
	    int ret = 0;  
	    for(;i>0;i&= (i - 1)){ret++;}   
	    return ret;  
	}  
	
	private static int lowest1(int i){
		int ret= 0;
		for(;(i&1)==0;i=(i>>1)){ret++;}
		return ret;
	}
	
	private static void print(int[] board){
		for (int i=0; i<board.length;i++){
			for (int j=0; j<board.length;j++)
				System.out.print(j==board[i]?'X':'O');
			System.out.println();
		}
		System.out.println("------------------");
	}
	
	public static void main(String[] args) {
		perform(5);		
	}

}
