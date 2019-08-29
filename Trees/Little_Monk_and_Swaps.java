package Trees;

import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Little_Monk_and_Swaps {

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

    static int pos = 0;
    private static void printInOrder(int[] arr,int[] inorder,int n, int i){
        if( i > n ) return;
        printInOrder(arr,inorder,n,2*i);
        inorder[pos++] = arr[i];
        printInOrder(arr,inorder,n,2*i+1);
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        int n = in.nextInt();
        int[] arr = new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i] = in.nextInt();
        }

        int[] inorder = new int[n];

        pos = 0;
        printInOrder(arr,inorder,n,1);

        // System.out.println(Arrays.toString(inorder));

        ArrayList<Pair<Integer,Integer>> arrpos = new ArrayList<>();
        for(int i=0;i<n;i++){
            arrpos.add(new Pair<>(inorder[i],i));
        }

        arrpos.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                // if (o1.getKey() > o2.getKey())
                //     return -1;

                // else if (o1.getKey().equals(o2.getKey()))
                //     return 0;

                // else
                //     return 1;
                return o1.getKey()-o2.getKey();
            }
        });

        // System.out.println(Arrays.toString(arrpos));
        // System.out.println(arrpos);

        boolean[] visited = new boolean[n];
        int ans = 0;
        for(int i=0;i<n;i++){
            if(visited[i] == true || arrpos.get(i).getValue() == i){
                continue;
            }
            int cycle_size = 0;
            int pos = i;
            while(visited[pos] == false){
                visited[pos] = true;
                pos = arrpos.get(pos).getValue();
                cycle_size ++;
            }
            if(cycle_size > 0){
                ans += (cycle_size - 1);
            }

        }

        System.out.println(ans);
    }

}
