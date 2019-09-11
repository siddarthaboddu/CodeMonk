//package Graph_Theory_Part_1;

import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Monk_and_Xor_Tree {

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

        ArrayList<Pair<Node, ArrayList<Node>>> list;
        int capacity ;

        Graph(int[] arr,int n){
            list = new ArrayList<Pair<Node,ArrayList<Node>>>();
            capacity = n;

            for(int i=0;i<n;i++){
                Pair<Node,ArrayList<Node>> pair = new Pair<Node,ArrayList<Node>>(new Node(i,arr[i]), new ArrayList<Node>());
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
//            list.get(y).getValue().add(nodeX);

        }

        int count = 0;
        boolean[] traversed;
        int[] arr;
        void calculateVal(int[] arr,int k){
            this.arr = arr;
            traversed = new boolean[capacity];
//            HashMap<Integer, HashMap<Integer,Integer>> values= new HashMap<Integer, HashMap<Integer, Integer>>();
            for(int i=0;i<capacity;i++){
                int currentPos = 10;
                int root = i;
                int currentValue = 0;
                finder(currentPos,root,currentValue,k);
            }

        }

        void finder(int current,int root,int currentValue,int k){
            if(current >= capacity || root >= capacity) return;
            if(traversed[current] == true) return;

//            System.out.println(current+"    "+root);
            currentValue = currentValue ^ arr[current];
            if(currentValue == k){
                count++;
            }
            traversed[current] = true;
            for(Node node : getAdjacentList(current)){
                if(traversed[node.node] == false){
                    finder(node.node,root,currentValue,k);
                }
            }
            traversed[current] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }

        Graph graph = new Graph(arr,n);
        for(int i=1;i<n;i++){
            int parent = in.nextInt() - 1;

            if(parent != i)
                graph.addEdge(parent, i);
        }

        graph.calculateVal(arr,k);

        System.out.println(graph.count);
    }

}
