package sorting;

import java.util.*;

public class Monk_and_Nice_Strings {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] arr = new String[n];
        for(int i=0;i<n;i++){
            arr[i]=in.next();
        }
        for(int i=0;i<n;i++){
            int count = 0;
            for(int j=i;j>-1;j--){
                if(arr[j].compareTo(arr[i]) < 0){
                    count++;
                }
            }
            System.out.println(count);
        }
    }

}
