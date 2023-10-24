import java.io.*;
import java.util.*;

public class TP1Q4 {

    public static boolean END(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String radom(String s, Random generator) {
        char[] c = new char[s.length()];

        char k1 = ((char) ('a' + (Math.abs(generator.nextInt() % 26))));
        char k2 = ((char) ('a' + (Math.abs(generator.nextInt() % 26))));
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == k1) {
                c[i] = k2;
            } else {
                c[i] = s.charAt(i);
            }
        }
        return new String(c);
    }

    public static void main(String[] args) throws Exception {

        Random generator = new Random();
        String[] name = new String[1000];
        int inputInt = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        generator.setSeed(4);

        do {
            name[inputInt] = br.readLine();

        } while (END(name[inputInt++]) == false);
        inputInt--;

        for (int i = 0; i < inputInt; i++) {
            System.out.println(radom(name[i], generator));
        }

    }

}