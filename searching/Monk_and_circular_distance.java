package searching;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Monk_and_circular_distance {

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
	
	private static double distance(double x,double y) {
		double val = 0;
		val = Math.sqrt((x*x)+(y*y));
		return val;
	}
	
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		
		int n = in.nextInt();
		int[][] arr = new int[n][2];
		
		for(int i=0;i<n;i++) {
			arr[i][0]=in.nextInt();
			arr[i][1]=in.nextInt();
		}
		
		double[] dist = new double[n];
		for(int i=0;i<n;i++) {
			double maxDist = distance(arr[i][0],arr[i][1]);
			dist[i]=maxDist;
		}
		Arrays.sort(dist);
	
		StringBuilder result = new StringBuilder();
		
		BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(System.out));

		int q = in.nextInt();
		for(int Q = 0;Q<q;Q++) {
			double r =  in.nextDouble();
			int pos = Arrays.binarySearch(dist, r);
			if(pos < 0 ) {
				pos = Math.abs(pos);
				pos = pos - 1;
				if(pos<0)
				for(int i = pos;i<n;i++) {
					if(dist[i]>r) {
						pos = i;
						break;
					}
					if(dist[i]<=r && i==n-1) {
						pos = n;
						break;
					}
				}
			}
			else {
				for(int i=pos;i<n;i++) {
					if(dist[i]>r) {
						pos = i;
						break;
					}
					if(dist[i]<=r && i==n-1) {
						pos=n;
						break;
					}
				}
			}
//			System.out.println(pos);
//			result.append(pos+" \n");
			buffer.write(pos+" \n");
		}
//		System.out.print(result);
		buffer.close();
	}
	
}
