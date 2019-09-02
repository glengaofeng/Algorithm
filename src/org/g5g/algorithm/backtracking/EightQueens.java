package org.g5g.algorithm.backtracking;

public class EightQueens {

   int[] x;//��ǰ��      
int N;//�ʺ����    
int sum = 0;//��ǰ���ҵ��Ŀ��з�����    
public int totalNQueens(int n) {    
   N = n;    
   x = new int[N+1];    
   backTrace(1);    
   return sum;    
}    
/**  
* col������㣬x[col]������㣬���Ѿ����ڵļ����ʺ��Ƿ����Ҫ�󣬷ŵ����λ���ϣ�  
* @param col  
* @return  
*/    
private boolean place(int col){    
   for(int i = 1; i < col; i++){    
       if(Math.abs(col - i)==Math.abs(x[col]-x[i])||x[col]==x[i]){    
           return false;    
       }    
   }    
   return true;    
}    
private void backTrace(int t) {    
   if(t>N){    
       sum++;    
   }else {    
       //��t�У��������еĽڵ�    
       for(int j = 1; j <= N; j++) {    
            x[t] = j ;    
            //�����j���ڵ���Է��»ʺ�    
           if(place(t)){    
               //���ŷ���һ��    
               backTrace(t+1);    
           }    
       }    
   }    
       
}    
public static void main(String[] args) {    
   EightQueens n = new EightQueens();    
   System.out.println(n.totalNQueens(8));    
}    
}    
