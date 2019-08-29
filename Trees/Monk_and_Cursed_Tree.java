package Trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Monk_and_Cursed_Tree {

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

    static class node {
        int data;
        node leftnode;
        node rightnode;
        public node() {

        }
        public void displaynode(){
            System.out.println(""+data);
        }

    }

    static class tree {
        public int lDepth=0,rDepth=0;
        private node root;
        public tree() {
            root=null;
        }

        public void insert(int key){
            node newnode=new node();
            newnode.data=key;//System.out.println("ttt"+newnode.data);
            if(root==null){
                root=newnode;
            }
            else{
                node current=root;
                node parent;
                //System.out.println("ttt"+current.data);
                while(true){
                    parent=current;
                    if(key<=current.data){
                        current=current.leftnode;
                        if(current==null){
                            parent.leftnode=newnode; return;
                        }
                    }
                    else{
                        current=current.rightnode;
                        if(current==null){
                            parent.rightnode=newnode;return;
                        }
                    }
                }
            }
        }

        public int maxDepth(node Node)
        {
            if (Node == null)
                return 0;
            else
            {
                /* compute the depth of each subtree */
                int lDepth = maxDepth(Node.leftnode);
                int rDepth = maxDepth(Node.rightnode);

                /* use the larger one */
                if (lDepth > rDepth)
                    return (lDepth + 1);
                else
                    return (rDepth + 1);
            }
        }

        public node getRoot(){
            return root;
        }
    }

    static node findAncestor(node root, int x,int y){
        if(root == null) return root;

        if(root.data == x || root.data == y || ( root.data >= x && root.data <= y ) ){
            return root;
        }

        node leftNode = findAncestor(root.leftnode,x,y);
        node rightNode = findAncestor(root.rightnode,x,y);

        if(leftNode != null )
            return leftNode;
        else
            return rightNode;
    }

    public static void main(String[] args) throws Exception {
        int  i,n,len,r;
        Reader input=new Reader();
        n=input.nextInt();
        tree root=new tree();

        for(i=0;i<n;i++){
            r=input.nextInt();
            root.insert(r);
        }
        int x=input.nextInt();
        int y=input.nextInt();

        if(x>y){
            int temp = x;
            x = y;
            y = temp;
        }

        node ancestor = findAncestor(root.getRoot(),x,y);

        int maxVal = Integer.MIN_VALUE;

        node currentNode = ancestor;
        while(currentNode != null){
            if(currentNode == null) break;

            int val = currentNode.data;

            maxVal = Math.max(maxVal, val);

            if(y == val) break;
            if(y < val){
                currentNode = currentNode.leftnode;
            }
            else{
                currentNode = currentNode.rightnode;
            }


        }

        System.out.println(maxVal);

    }

}
