 package Stacks_and_Queues;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.*;


public class Monk_and_Order_of_Phoenix {
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

    private static boolean canHarryUseSpecialWand(TreeSet<Integer> firstList,ArrayList<ArrayList<Integer>> list){
        boolean canUse = false;

        int sidduHeight = Integer.MAX_VALUE;
//        ArrayList<Integer> legendaryFighters = list.get(0);
//        for(int i=0; i<legendaryFighters.size(); i++){
//            int legendaryFighterHeight = legendaryFighters.get(i);
        Iterator<Integer> iterator = firstList.iterator();

        while(iterator.hasNext()){

            int legendaryFighterHeight =  iterator.next();

            int minReqHeight = legendaryFighterHeight;
            if(sidduHeight <= legendaryFighterHeight) continue;
            for(int rowNum = 1;rowNum<list.size();rowNum++){
                ArrayList<Integer> row = list.get(rowNum);

                int newPos = Collections.binarySearch(row, minReqHeight);
                if(newPos < 0){
                    newPos = Math.abs(newPos);
                    newPos = newPos - 1;
                }
                if(newPos >= row.size()){
                    sidduHeight =  legendaryFighterHeight;
                    break;
                }
                minReqHeight = row.get(newPos);

                if(rowNum == list.size() - 1){
                    canUse = true;
                    return canUse;
                }
            }

        }
        return canUse;
//        return canUse;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        StringBuilder result = new StringBuilder();
        // BufferedOutputStream out = new BufferedOutputStream ( System.out );


        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(n-1);
        TreeSet<Integer> firstList = new TreeSet<>();
        for(int i=0;i<n;i++){
            int sizeOfrow = in.nextInt();
            if(i==0){
                for(int j=0;j<sizeOfrow;j++){
                    firstList.add(in.nextInt());
                }
            }
            else{
                ArrayList<Integer> row = new ArrayList<>();
                for(int j=0;j<sizeOfrow;j++){
                    row.add(in.nextInt());
                }
                list.add(row);
            }

        }

        int Q = in.nextInt();
        for(int q = 0;q<Q;q++){
            int v = in.nextInt();
            if(v == 0){
                int k = in.nextInt();
                int val = 0;
                if(k==1){
                    val = firstList.pollLast();
                }
                else{
                    ArrayList<Integer> tempList = list.get(k-2);
                    val = tempList.remove(tempList.size()-1);

                }
                // int val = tempList.remove(tempList.size()-1);
                // int val = list.get(k-1).remove(list.get(k-1).size()-1);
            }else {
                if(v==1){
                    int k = in.nextInt();
                    int h = in.nextInt();
                    if(k==1){
                        firstList.add(h);
                    }
                    else{
                        list.get(k-2).add(h);
                    }
                }
                else{
                    if(canHarryUseSpecialWand(firstList,list)){
                        // System.out.println("YES");
                        result.append("YES\n");
                        // out.write(("YES").getBytes());
                    }else{
                        // System.out.println("NO");
                        result.append("NO\n");
                        // out.write(("NO").getBytes());
                    }
                    // out.write(("\n").getBytes());
                }
            }
        }

        // out.close();
        System.out.println(result);
    }

}