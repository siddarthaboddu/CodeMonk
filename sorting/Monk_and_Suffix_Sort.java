package sorting;

import java.util.*;

public class Monk_and_Suffix_Sort {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int k = in.nextInt();
        int n = s.length();

        List<String> list = new ArrayList<String>();

        for(int i=0;i<n;i++){
            String str = s.substring(i);
            list.add(str);
        }

        Collections.sort(list);
        // System.out.println(list);
        System.out.println(list.get(k-1));
    }

}
