 package Hashing;

import java.util.Scanner;
import java.util.Arrays;

public class The_Monk_and_Kundan {

    static String initial = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int N = 0; N < n; N++){
            int hash = 0;
            String input = in.nextLine();
            String[] strs = input.split(" ");
            System.out.println(Arrays.toString(strs));
            for(int i=0;i<strs.length;i++){
                String str = strs[i];
                for(int pos = 0; pos<str.length();pos++){
                    char ch = str.charAt(pos);
                    hash += pos;
                    hash += str.indexOf(""+ch);
                }
            }
            hash = strs.length * hash;
            System.out.println(hash);
        }
    }

}
