package arrays_and_strings;

import java.util.*;

public class Monk_Teaches_Palindrome {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            String s = in.next();
            if(isPalindrome(s)){
                if(s.length()%2 == 0){
                    System.out.println("YES EVEN");
                }
                else{
                    System.out.println("YES ODD");
                }
            }
            else{
                System.out.println("NO");
            }
        }

    }

    private static boolean isPalindrome(String s){
        int n = s.length();

        for(int i=0;i<n/2;i++){
            if(s.charAt(i) != s.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }

}
