 package Trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

 public class Monk_and_Tree_Counting {
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


    public static void populateChildren(int[] parent,HashSet<Integer>[] children,int n,int pos){
        int child = pos;

        while(pos!=-1){
            if(child != pos){
                children[pos].add(child);
            }

            pos = parent[pos];
        }

    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        int n = in.nextInt();
        long K = in.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = in.nextLong();
        }
        int[] parent = new int[n];
        parent[0] = -1;
        for(int i=1;i<n;i++){
            parent[i] = in.nextInt() - 1;
        }

        HashSet<Integer>[] allChildren = new HashSet[n];

        for(int i=0;i<n;i++){
            allChildren[i] = new HashSet<Integer>();
        }

        for(int i=0;i<n;i++){
            populateChildren(parent,allChildren,n,i);
        }

        int count = 0;

        for(int j=0;j<n-1;j++){
            for(int k=j+1;k<n;k++){
                for(int i=0;i<n;i++){

                    if(i==j || i==k) continue;
                    if(arr[i] + arr[j] + arr[k] < K) continue;
                    if(allChildren[i].contains(j) == false) continue;
                    if(allChildren[i].contains(k) == false) continue;

                    count++;

                }
            }
        }

        System.out.println(count);
    }

}