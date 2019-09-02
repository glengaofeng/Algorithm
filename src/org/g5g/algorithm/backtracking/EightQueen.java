package org.g5g.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class EightQueen{
    private static final short K=16;     //ʹ�ó��������壬����֮���N�ʺ�����
    private static short N=0;

    public static void main(String[] args) throws Exception {
        for(N=16;N<=16;N++){
            long count=0;
            Date begin =new Date();
            /**
             * ��ʼ�����̣�ʹ��һά������������Ϣ
             * chess[n]=X:��ʾ��n��X����һ���ʺ�
             */

            List<short[]> chessList=new ArrayList<short[]>(N);
            for(short i=0;i<N;i++){
                short chess[]=new short[N];
                chess[0]=i;
                chessList.add(chess);
            }

            short taskSize =(short)( N/2+(N%2==1?1:0) );
            // ����һ���̳߳�
            ExecutorService pool = Executors.newFixedThreadPool(taskSize);
            // ��������з���ֵ������
            List<Future<Long>> futureList = new ArrayList<Future<Long>>(taskSize);
            for (int i = 0; i < taskSize; i++) {
                Callable<Long> c = new EightQueenThread(chessList.get(i));
                // ִ�����񲢻�ȡFuture����
                Future<Long> f = pool.submit(c);
                futureList.add(f);
            }
            // �ر��̳߳�
            pool.shutdown();

            for(short i=0; i<(short) (taskSize - (N%2==1?1:0)); i++){              
                count+=futureList.get(i).get();
            }
            count=count*2;
            if(N%2==1)
                count+=futureList.get(N/2).get();

            Date end =new Date();
            System.out.println("��� " +N+ "�ʺ����⣬��ʱ��" +String.valueOf(end.getTime()-begin.getTime())+ "���룬��������"+count);
        }
    }
}

class EightQueenThread implements Callable<Long>{
    private short[] chess;
    private short N;

    public EightQueenThread(short[] chess){
        this.chess=chess;
        this.N=(short) chess.length;
    }


    @Override
    public Long call() throws Exception {
        return putQueenAtRow(chess, (short)1) ;
    }


    private Long putQueenAtRow(short[] chess, short row) {
        if(row==N){
            return (long) 1;
        }

        short[] chessTemp=chess.clone();
        long sum=0;
        /**
         * ����һ�е�ÿһ��λ�ó����ŷŻʺ�
         * Ȼ����״̬�������ȫ�����ִ�еݹ麯���ڷ���һ�лʺ�
         */
        for(short i=0;i<N;i++){
            //�ڷ���һ�еĻʺ�
            chessTemp[row]=i;

            if( isSafety( chessTemp,row,i) ){
                sum+=putQueenAtRow(chessTemp,(short) (row+1));
            }
        }

        return sum;
    }

    private static boolean isSafety(short[] chess,short row,short col) {
        //�ж����ϡ����ϡ������Ƿ�ȫ
        short step=1;
        for(short i=(short) (row-1);i>=0;i--){
            if(chess[i]==col)   //����
                return false;
            if(chess[i]==col-step)  //����
                return false;
            if(chess[i]==col+step)  //����
                return false;

            step++;
        }

        return true;
    }
}
//��� 16�ʺ����⣬��ʱ��65927���룬��������14772512