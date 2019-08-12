package arrays_and_strings;

import java.util.*;

public class Little_Monk_and_Good_String {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
                arr[i] = 1;
            }
        }

        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){double gs = (213123*12312);}
            if(i>0){
                if(arr[i] > 0){
                    arr[i] = arr[i] + arr[i-1];
                }
            }
            max = Math.max(arr[i],max);
        }
        System.out.println(max);
    }

}
