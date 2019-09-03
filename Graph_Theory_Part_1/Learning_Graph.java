package Graph_Theory_Part_1;

import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Learning_Graph {

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

    static class Node{
        public int node;
        public int val;
        Node(int node,int val){
            this.node = node;
            this.val = val;
        }

    }
    static class Graph{

        ArrayList<Pair<Node,ArrayList<Node>>> list;
        int capacity ;
        Graph(int[] arr,int n){
            list = new ArrayList<Pair<Node,ArrayList<Node>>>();
            capacity = n;

            for(int i=0;i<n;i++){
                Pair<Node,ArrayList<Node>> pair = new Pair<Node,ArrayList<Node>>( new Node(i,arr[i]),new ArrayList<Node>());
                list.add(pair);
            }
        }

        ArrayList<Node> getAdjacentList(int nodePos){
            if(nodePos >= capacity) return null;
            return list.get(nodePos).getValue();
        }

        void addEdge(int x,int y){
            Node nodeX = list.get(x).getKey();
            Node nodeY = list.get(y).getKey();

            list.get(x).getValue().add(nodeY);
            list.get(y).getValue().add(nodeX);

        }
    }
    public static void main(String[] args) throws Exception {

        Reader in = new Reader();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }

        Graph graph = new Graph(arr,n);

        for(int K = 0; K< m ; K++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;

            graph.addEdge(x,y);
        }

        StringBuilder result = new StringBuilder();
        for(int i=0;i<n;i++){
            ArrayList<Node> list = graph.getAdjacentList(i);
            Collections.sort(list, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
//                    return o2.val - o1.val;
                    if(o2.val > o1.val){
                        return 1;
                    }
                    if(o2.val < o1.val){
                        return -1;
                    }
                    return o2.node - o1.node;
                }
            });
            if( (k-1) >= list.size()){
//                System.out.println(-1);
                result.append("-1\n");
            }
            else
                result.append(list.get(k-1).node+1+"\n");
//                System.out.println(list.get(k-1).node+1);

        }
        System.out.println(result);
    }

}
