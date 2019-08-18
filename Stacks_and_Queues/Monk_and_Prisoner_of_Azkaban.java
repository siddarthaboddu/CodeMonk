package Stacks_and_Queues;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;


public class Monk_and_Prisoner_of_Azkaban {

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

//    11
//            5 3 2 10 6 8 1 4 12 7 4
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        int n = in.nextInt();

        long[] arr = new long[n];

        for(int i=0;i<n;i++){
            arr[i] = in.nextLong();
        }
        long[] rightarr = new long[n];
        long[] leftarr = new long[n];

        Stack<Long> stack = new Stack<Long>();

        //------------------------ right side next greatest

        for(int i=0;i<n;i++){

            long next = arr[i];
            if(i==0){
                stack.push((long)i);
                continue;
            }
            else{
                if(stack.isEmpty()){
                    stack.push((long)i);
                    continue;
                }
                else{
                  if(next < arr[(int)(long)stack.peek()]){
                      stack.push((long)i);
                  }
                  else{
                      while(stack.isEmpty()==false && next>arr[(int)(long)stack.peek()]){
                          int pop_pos = (int)(long)stack.pop();
                          rightarr[pop_pos] = i;
                      }
                      stack.push((long)i);
                  }
                }
            }
        }
//        System.out.println("------------------------");
//        System.out.println(stack);
//        System.out.println(stack.size());
//        System.out.println(stack.peek());
//        System.out.println("------------------------");
        while (!stack.isEmpty()){
//            System.out.println("siddu+" + stack.peek());
            int pop_pos = (int)(long)stack.pop();
            rightarr[pop_pos] = -1l;
//            System.out.println(arr[pop_pos] +"  "+rightarr[pop_pos]);

//            System.out.println("ssss " + stack.isEmpty()+ "   "+stack.size());
        }

//        System.out.println(Arrays.toString(rightarr));

        //------------------------ left side next greatest

//        System.out.println("______________________________");

//        System.out.println(Arrays.toString(arr));
        reverse(arr);

//        System.out.println(Arrays.toString(arr));

        stack = new Stack<Long>();

        for(int i=0;i<n;i++){

            long next = arr[i];
            if(i==0){
                stack.push((long)i);
                continue;
            }
            else{
                if(stack.isEmpty()){
                    stack.push((long)i);
                    continue;
                }
                else{
                    if(next < arr[(int)(long)stack.peek()]){
                        stack.push((long)i);
                    }
                    else{
                        while(stack.isEmpty()==false && next>arr[(int)(long)stack.peek()]){
                            int pop_pos = (int)(long) stack.pop();
                            leftarr[pop_pos] = i;
                        }
                        stack.push((long)i);
                    }
                }
            }
        }
//        System.out.println("------------------------");
//        System.out.println(stack);
//        System.out.println(stack.size());
//        System.out.println(stack.peek());
//        System.out.println("------------------------");
        while (!stack.isEmpty()){
//            System.out.println("siddu+" + stack.peek());
            int pop_pos = (int)(long)stack.pop();
            leftarr[pop_pos] = -1;
//            System.out.println(arr[pop_pos] +"  "+rightarr[pop_pos]);

//            System.out.println("ssss " + stack.isEmpty()+ "   "+stack.size());
        }

//        System.out.println("______________________________");
//        System.out.println(Arrays.toString(leftarr));

        for(int i=0;i<n;i++){
            if(leftarr[i]!=-1)
            leftarr[i]=n-leftarr[i];
        }
//        System.out.println(Arrays.toString(leftarr));



        StringBuilder str = new StringBuilder();
        for(int i=0,j=n-1;i<n;i++,j--){
            if(rightarr[i] != -1) rightarr[i]++;
            long x = leftarr[j] + rightarr[i];
//            System.out.print(x+" ");
            str.append(x+" ");
        }
        System.out.println(str);

    }

    public static void reverse(long[] arr){
        int n = arr.length;
        for(int i=0;i<n/2;i++){
            long temp = arr[i];
            arr[i]=arr[n-1-i];
            arr[n-1-i] = temp;
        }
    }
}
