package Trees;

import java.util.*;

class node {
    int data;
    node leftnode;
    node rightnode;
    public node() {

    }
    public void displaynode(){
        System.out.println(""+data);
    }

}
class tree {
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
            int lDepth = maxDepth(Node.leftnode);
            int rDepth = maxDepth(Node.rightnode);

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


public class Monk_Watching_Fight {

    public static void main(String args[] ) throws Exception {
        int  i,n,len,r;
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        tree root=new tree();

        for(i=0;i<n;i++){
            r=input.nextInt();
            root.insert(r);
        }
        node top=root.getRoot();
        len=root.maxDepth(top);

        System.out.println(len);
    }

}
