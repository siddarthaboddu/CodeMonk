  package Hashing;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Little_Jhool_and_the_matchmaker {

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100000]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    static class Person{
        String name;
        //        HashMap<Character,Integer> frequencyOfCharacters;
        int pos;
        int commonChars;

        Person(String name,int commonChars,int pos){
            this.name = name;
            this.commonChars = commonChars;
            this.pos = pos;
        }

        @Override
        public String toString(){
            return "["+name+" "+commonChars+" "+pos+"]";
        }

    }

    static HashMap<Character,Integer> calculateFrequenciesOfCharacters(String str){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(hashMap.containsKey(ch)){
                hashMap.put(ch,hashMap.get(ch) + 1);
            }
            else{
                hashMap.put(ch,1);
            }
        }
        return hashMap;
    }

    static int calculateCommonCharacters(HashMap<Character,Integer> littleJohn,HashMap<Character,Integer> girl){
        int count = 0;
        for(Character ch : girl.keySet()){
            int freqInGirl = girl.get(ch);
            int freqInLittleJohn = 0;
            if(littleJohn.containsKey(ch)){
                count += 1;
            }
//                freqInLittleJohn = littleJohn.get(ch);
//            count += Math.min(freqInGirl,freqInLittleJohn);
        }
        return count;
    }

    public static void main(String[] args) throws Exception{
        Reader in = new Reader();
        String littleJhool = "littlejhool";
        HashMap<Character,Integer> freqLittleJhool = calculateFrequenciesOfCharacters(littleJhool.toLowerCase());
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for(int t=0;t<T;t++){
            String nAndK = in.readLine();
            String[] NK = nAndK.split(" ");
            int n = Integer.parseInt(NK[0]);
            int k = Integer.parseInt(NK[1]);
//            System.out.println(T +" "+n+" "+k);
            String input = in.readLine();
            String[] words = input.split(" ");
//            System.out.println(Arrays.toString(words));
            PriorityQueue<Person> pq = new PriorityQueue<>(new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    if(o1.commonChars == o2.commonChars){
                        return o1.pos - o2.pos;
                    }
                    return o1.commonChars - o2.commonChars;
                }
            });

            // System.out.println("-----------");
            for(int pos = 0; pos < words.length; pos++){
                String word = words[pos];
                HashMap<Character,Integer> freqGirl = calculateFrequenciesOfCharacters(word.toLowerCase());
                int commonChars = calculateCommonCharacters(freqLittleJhool,freqGirl);
                Person person = new Person(word,commonChars,pos);
                pq.add(person);
                // System.out.println(person);
            }
            // System.out.println("-----------");

            for(int i=0;i<k;i++){
                //System.out.print(pq.poll().name+" ");
                result.append(pq.poll().name+" ");
            }
            // System.out.println();
            result.append("\n");
        }

        System.out.println(result);
    }

}
