package searching;

import java.util.*;
import java.io.BufferedWriter;
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.OutputStreamWriter;

public class Monk_and_Search {

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
    
	public static void main(String[] args) throws Exception {
//		Scanner in = new Scanner(System.in);
		Reader in = new Reader();
		
		BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=in.nextInt();
		}
		Arrays.sort(arr);
		
		HashMap<Integer,Integer> hashMap = new LinkedHashMap<Integer,Integer>();
		
		for(int i: arr) {
			if(hashMap.containsKey(i)) {
				hashMap.put(i, hashMap.get(i)+1);
			}
			else {
				hashMap.put(i, 1);
			}
		}
		int uniqueCount = hashMap.size();
		Integer[] keys =  new Integer[uniqueCount];
		keys = hashMap.keySet().toArray(keys);
		Integer[] values = new Integer[uniqueCount];
		values = hashMap.values().toArray(values);
		
		for(int i=uniqueCount-1;i>-1;i--) {
			if(i==uniqueCount-1) continue;
			values[i]+=values[i+1];
		}
		
//		System.out.println(Arrays.toString(keys));
//		System.out.println(Arrays.toString(values));
		
		int q = in.nextInt(); 
		for(int Q = 0;Q<q;Q++) {
			int choice = in.nextInt();
			int x = in.nextInt();
			int pos = Arrays.binarySearch(keys, x);
//			System.out.println("pos "+pos+"  "+x);
			if(pos>-1) {
				if(choice == 0) {
//					System.out.println(values[pos]);
					buffer.write(values[pos]+"\n");
				}else {
					if(pos == uniqueCount -1) {
//						System.out.println(0);
						buffer.write(0+"\n");
					}
					else {
//						System.out.println(values[pos+1]);
						buffer.write(values[pos+1]+"\n");
					}
					
				}
			}
			else {
				pos = Math.abs(pos);
				pos = pos -1;
				if(pos >= uniqueCount) {
//					System.out.println(0);
					buffer.write(0+"\n");
					continue;
				}
				if(choice == 0) {
//					System.out.println(values[pos]);
					buffer.write(values[pos]+"\n");
				}
				else {
//					System.out.println(values[pos]);
					buffer.write(values[pos]+"\n");
				}
			}
			
			
		}
		buffer.close();
	}
	
}
