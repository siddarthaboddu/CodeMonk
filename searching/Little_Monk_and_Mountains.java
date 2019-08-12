package searching;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Little_Monk_and_Mountains {

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

    static long finder(long[] larr,long[] rarr,long[] distarr,int l,int r,long q) {
    	int m = (l+r)/2;
    	if(r<l) return -1;
    	if(q > distarr[m] ) {
    		return finder(larr,rarr,distarr,m+1,r,q);
    	}
    	else {
//    		if(q <= distarr[m]) {
    			if(q >= distarr[m]-(rarr[m]-larr[m]+1)) {
    				long temp = distarr[m]-q;
    				return rarr[m]-temp;
    			}
    			else {
    				return finder(larr,rarr,distarr,l,m-1,q);
    			}
//    		}
//    		else {
//    			return 
//    		}
    	}
    }
    public static void main(String[] args) throws Exception {

        Reader in = new Reader();
		StringBuilder result = new StringBuilder();

        int n = in.nextInt();
        int QQ = in.nextInt();
        
        long[] larr = new long[n];
        long[] rarr = new long[n];
        long[] distarr = new long[n];
        
        for(int i=0;i<n;i++) {
        	long l = in.nextLong();
        	long r = in.nextLong();
        	
        	larr[i] = l;
        	rarr[i] = r;
        	if(i==0) {
        		distarr[i] = r-l+1;
        	}
        	else {
            	distarr[i] = distarr[i-1] + (r-l+1);
        	}
        }
        
        for(int Q = 0; Q<QQ; Q++) {
        	long q = in.nextLong();
        	long pos = finder(larr,rarr,distarr,0,n-1,q);
//        	System.out.println(pos);
        	result.append(pos+" \n");
        }
        System.out.println(result);
    }

}
