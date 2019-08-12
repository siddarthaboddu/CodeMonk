package arrays_and_strings;

import java.util.*;

public class Monk_and_Inversions {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int _t = 0; _t < t; _t++){
            int n = in.nextInt();
            int[][] arr = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    arr[i][j]=in.nextInt();
                }
            }
            int count = 0;
            for(int a=0;a<n;a++){
                for(int b=0;b<n;b++){
                    for(int i=a;i<n;i++){
                        for(int j=b;j<n;j++){
                            if(arr[a][b]>arr[i][j]){
                                count++;
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        }

    }
}
