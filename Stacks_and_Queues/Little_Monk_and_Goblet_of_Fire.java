package Stacks_and_Queues;

import java.util.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Little_Monk_and_Goblet_of_Fire {

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

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        int Q = in.nextInt();

        int[] used = new int[4];
        ArrayList<Queue<Integer>> list = new ArrayList<>();

        ArrayList<Integer> listNumbers = new ArrayList<>();

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int positionVal = 0;

        StringBuilder result = new StringBuilder();

        for(int q = 0; q < Q; q++){
            String[] line  = in.readLine().split(" ");
            char option = line[0].charAt(0);

            if(option == 'E'){
                int x = Integer.parseInt(line[1]);
                int y = Integer.parseInt(line[2]);
                if(used[x-1] == 0){
                    Queue<Integer> queue = new ArrayDeque();
                    queue.add(y);
                    list.add(queue);
                    used[x-1]=1;
                    hashMap.put(x-1,list.size()-1);
                    positionVal++;
                    listNumbers.add(x-1);
                }
                else{
                    int posInList = hashMap.get(x-1);
                    Queue queue = list.get(posInList);
                    queue.add(y);
                }
            }
            else{
                Queue<Integer> queue = list.get(0);
                int xmin1 = listNumbers.get(0);
                int yResult = queue.poll();

                result.append((xmin1+1)+" "+yResult);
                result.append("\n");

                if(queue.isEmpty()){
                    list.remove(queue);
                    listNumbers.remove(0);
                    Object[] keys = hashMap.keySet().toArray();
                    for(Object k : keys){
                        Integer key = (Integer) k;
                        if(hashMap.get(key) == 0){
                            hashMap.remove(key);
                            used[key] = 0;
                        }
                        else{
                            hashMap.put(key,hashMap.get(key) -1);
                        }
                    }

                }

            }


        }

        System.out.println(result);

    }

}