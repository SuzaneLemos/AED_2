import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Aleatoria
{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String phrase = "";

        while (!phrase.equals("FIM")) {
            phrase = br.readLine();
            Random gerador = new Random();
            gerador.setSeed(4);
            System.out.println(subtituteIn(phrase, rndChar(), rndChar()));
        }
    }

    public static String subtituteIn(String word, char target, char toChar) {
        char charat;
        StringBuffer finalw = new StringBuffer(word);

        //System.out.println("Target = " + target + "/To = " + toChar);

        for (int index = 0; index < word.length(); index++) {

            // se o char neste local for igual ao meu alvo
            if (word.charAt(index) == target) {
                // setar variavel charat para o subtituto
                charat = (char) ((int) toChar);
                // Trocar char da string
                finalw.setCharAt(index, charat);
            } else {
                charat = word.charAt(index);
                finalw.setCharAt(index, charat);
            }

        }
        // System.out.println(finalw);
        return finalw.toString();
    }

    private static char rndChar() {
        int rnd = (int) (Math.random() * 52); // or use Random or whatever
        char base = (rnd < 26) ? 'a' : 'a';
        return (char) (base + rnd % 26);
    }
}
