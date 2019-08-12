package arrays_and_strings;

import java.util.*;
import java.io.*;

public class Monk_and_Rotation {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        BufferedOutputStream out = new BufferedOutputStream ( System.out );
        int t = in.nextInt();
        for(int x=0;x<t;x++){
            int n = in.nextInt();
            int k = in.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i]=in.nextInt();
            }

            k=k%n;
            int pos = n-k;
            // System.out.println(n+"  "+k+"  "+pos);
            for(int i=0;i<n;i++){
                if(pos == n){
                    pos = 0;
                }
                // System.out.print(arr[pos]+" ");
                out.write((arr[pos]+" ").getBytes());
                pos++;
            }
            out.write(("\n").getBytes());
        }

        out.close();
    }
}
