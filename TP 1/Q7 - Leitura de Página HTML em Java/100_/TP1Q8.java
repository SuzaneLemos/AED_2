import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class TP1Q8 {

    public static boolean noCapVogal(char s) {
        return (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u');

    }

    public static boolean END(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean table(String s, int i) {
        return (s.charAt(i) == '<' && s.charAt(i + 1) == 't' && s.charAt(i + 2) == 'a' && s.charAt(i + 3) == 'b'
                && s.charAt(i + 4) == 'l' && s.charAt(i + 5) == 'e' && s.charAt(i + 6) == '>');
    }

    public static boolean br(String s, int i) {
        return (s.charAt(i) == '<' && s.charAt(i + 1) == 'b' && s.charAt(i + 2) == 'r' && s.charAt(i + 3) == '>');
    }

    public static void sum(String s, String tittle) throws UnsupportedEncodingException {
        int[] array = new int[22];
        // characters
        int[] character = { 97, 101, 105, 111, 117, 225, 233, 237, 243, 250, 224, 232, 236, 242, 249, 227, 245, 226,
                234,
                238, 244, 251 };
        int cont = 0;
        int tablecont = 0;
        int brcont = 0;

        PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; character.length > j; j++) {
                if (s.charAt(i) == character[j]) {
                    array[j]++;
                }

            }

            if (((s.charAt(i) >= 'b' && s.charAt(i) <= 'z') && !noCapVogal(s.charAt(i)))) {
                cont++;
            }

            if (s.charAt(i) == '<') {

                if (table(s, i)) {
                    i += 7;
                    tablecont++;

                } else if (br(s, i)) {
                    i += 4;
                    brcont++;
                }
            }
        }
        // imprimir os resultados de todas as somas
        ps.println("a(" + array[0] + ") e(" + array[1] + ") i(" + array[2] + ") o(" + array[3] + ") u(" + array[4]
                + ") á(" + array[5] + ") é(" + array[6] + ") í(" + array[7] + ") ó(" + array[8] + ") ú(" + array[9]
                + ") à(" + array[10] + ") è(" + array[11] + ") ì(" + array[12] + ") ò(" + array[13] + ") ù(" + array[14]
                + ") ã(" + array[15] + ") õ(" + array[16] + ") â(" + array[17] + ") ê(" + array[18] + ") î(" + array[19]
                + ") ô(" + array[20] + ") û(" + array[21] + ") consoante(" + (cont - 3) + ") <br>(" + brcont
                + ") <table>(" + tablecont + ") " + tittle);
    }

    public static void counter(String tittle, String url) {

        try {
            URL link = new URL(url);

            String s = "";
            String s1 = "";

            BufferedReader br = new BufferedReader(new InputStreamReader(link.openStream(), Charset.forName("UTF-8")));

            do {
                s1 = br.readLine();
                s += s1;
            } while (s1 != null);

            sum(s, tittle);
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        String s = "";
        String s1 = "";
        String s2 = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        while (!END(s)) {

            s1 = s;
            s = br.readLine();

            if (s.charAt(0) == 'h') {
                s2 = s;
                counter(s1, s2);
            }

        }
        br.close();

    }

}