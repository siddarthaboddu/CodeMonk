package sorting;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Monk_being_monitor {
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    public static void main(String args[] ) throws Exception {
        Reader in = new Reader();

        int t = in.nextInt();
        for(int testcase = 0 ; testcase < t; testcase++){
            int n = in.nextInt();
            int[] arr = new int[n];
            HashMap<Integer,Integer> hashMap = new HashMap<>();
            for(int i=0;i<n;i++){
                int val = in.nextInt();
                if(hashMap.containsKey(val)){
                    int count = hashMap.get(val);
                    hashMap.put(val,count+1);
                }
                else{
                    hashMap.put(val,1);
                }
                arr[i]=val;
            }

            int[] maxarr = new int[n];

            int maxkey = 0;
            for(int i=0;i<n;i++) {
                if(i==0) {
                    maxarr[i]=arr[maxkey];
                }
                else {
                    if(hashMap.get(arr[maxkey]) < hashMap.get(arr[i-1])) {
                        maxkey = i-1;
                    }
                    maxarr[i]=arr[maxkey];
                }

            }

            int maxdiff = 0;
            int diff = 0;
            for(int i=1;i<n;i++) {
                diff = hashMap.get(maxarr[i]) - hashMap.get(arr[i]);
                maxdiff = Math.max(maxdiff, diff);
            }

            if(maxdiff == 0) maxdiff = -1;
            System.out.println(maxdiff);
        }
    }
}
