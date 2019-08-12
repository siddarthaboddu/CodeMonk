package sorting;

import java.util.*;

public class Monk_and_Modulo_Based_Sorting {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Integer[] arr = new Integer[n];
        for(int i=0;i<n;i++){
            arr[i]=in.nextInt();
        }
        Comparator<Integer> cmp = new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return a%k - b%k;
            }
        };
        Arrays.parallelSort(arr,cmp);

        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
