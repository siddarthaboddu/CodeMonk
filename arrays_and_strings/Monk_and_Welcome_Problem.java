package arrays_and_strings;

import java.util.*;

public class Monk_and_Welcome_Problem {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];

        for(int i=0;i<n;i++) a[i]=in.nextInt();
        for(int i=0;i<n;i++) b[i]=in.nextInt();

        for(int i=0;i<n;i++){
            System.out.print(a[i]+b[i]+" ");
        }

    }
}
