 package Graph_Theory_Part_1;

import javafx.util.Pair;
import sun.misc.Queue;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Little_Monk_and_Edge_Count {

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
        int[] counts ;

        Graph(int[] arr,int n){
            list = new ArrayList<Pair<Node,ArrayList<Node>>>();
            capacity = n;
            counts = new int[n];

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
            if(x == y) return;
            Node nodeX = list.get(x).getKey();
            Node nodeY = list.get(y).getKey();

            list.get(x).getValue().add(nodeY);
            list.get(y).getValue().add(nodeX);

        }

        int countNodes(int pos,boolean[] traversed) throws Exception {
            Queue<Node> queue= new Queue<>();
            Node node = list.get(pos).getKey();
            queue.enqueue(node);
//            traversed[node.node] = true;
//            int edges = getAdjacentList(node.node).size();
            int nodeCount = 0;

            while(!queue.isEmpty()){
                Node temp = queue.dequeue();
                if(traversed[temp.node] == true) continue;
                traversed[temp.node] = true;
//                edgeCount += getAdjacentList(temp.node).size();
                nodeCount++;
                for(Node nextNode : getAdjacentList(temp.node)){
                    if(traversed[nextNode.node] == false){
                        queue.enqueue(nextNode);
                    }
                }

            }

            return nodeCount;
        }

        void countNumberOfChildNodes(){
            int parent = 1;
            boolean[] traversed = new boolean[capacity];
            finder(traversed,parent);
        }

        int finder(boolean[] traversed,int pos){
            if(traversed[pos] == true) return 0;
            int numberOfChildren = 1;
            traversed[pos] = true;
            for(Node node : getAdjacentList(pos)){
                if(traversed[node.node] == false)
                    numberOfChildren += finder(traversed,node.node);
            }
            //traversed[pos] = false;
            counts[pos] = numberOfChildren;
            return numberOfChildren;
        }

    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();

        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = new int[n];
        Graph graph = new Graph(arr, n);
        int[][] edges = new int[n-1][2];
        for(int i=0;i<n-1;i++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            // if(x==y) continue;
            edges[i][0] = x ;
            edges[i][1] = y;

            graph.addEdge(x,y);
        }

        graph.countNumberOfChildNodes();

        int[] counts = graph.counts;

        // System.out.println(Arrays.toString(graph.counts));

        StringBuilder result = new StringBuilder();

        for(int Q = 0; Q< q;Q++){
            int edgeNumber = in.nextInt();
            int[] edge = edges[edgeNumber -1];

            int x = edge[0];
            int y = edge[1];

//            boolean[] traversed = new boolean[n];
//            traversed[y] = true;
//            int xNodes = graph.countNodes(x,traversed);
////            traversed[y] = false;
////            int yNodes = graph.countNodes(y,traversed);
////            System.out.println(xNodes * (n-xNodes));
//            int paths = xNodes * (n-xNodes);
//            result.append((paths)+"\n");
            long resultVal = 0l;
            int countx = counts[x];
            int county = counts[y];
            if(countx < county){
                resultVal = ((long)countx) * ((long)(n - countx));
            }
            else{
                resultVal = ((long)county) * ((long)(n - county));
            }
            result.append(resultVal+"\n");
        }
        System.out.println(result);
    }

}
