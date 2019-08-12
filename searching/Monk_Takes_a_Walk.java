package searching;

import java.util.*;

public class Monk_Takes_a_Walk {

    public static void main(String args[] ) throws Exception {
        int f,i,j,count,t;
        Scanner input=new Scanner(System.in);
        t=input.nextInt();
        for(f=0;f<t;f++){
            count=0;
            String s;
            s=input.next();
            char[] a=s.toCharArray();
            for(i=0;i<s.length();i++){
                if(a[i]=='a' || a[i]=='e' || a[i]=='i' || a[i]=='o' || a[i]=='u' || a[i]=='A' || a[i]=='E' || a[i]=='I' ||
                        a[i]=='O' || a[i]=='U'){
                    count=count+1;
                }
            }
            System.out.println(count);
        }
    }

}
