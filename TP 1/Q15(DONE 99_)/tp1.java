import java.io.BufferedReader;
import java.io.InputStreamReader;

public class tp1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = "";

        String x1;
        String x2;
        String x3;
        String x4;

        while (!word.equals("FIM")) {
            word = br.readLine();
            if (isConsonant(word, 0, true) == true) {
                x1 = "SIM";
            } else {
                x1 = "NAO";
            }
            if (isVogal(word, 0, true) == true) {
                x2 = "SIM";
            } else {
                x2 = "NAO";
            }
            if (isInteger(word) == true) {
                x3 = "SIM";
            } else {
                x3 = "NAO";
            }
            if (isFloat(word) == true) {
                x4 = "SIM";
            } else {
                x4 = "NAO";
            }
            System.out.println(x1 + " " + x2 + " " + x3 + " " + x4 + " ");
        }
    }

    public static boolean isConsonant(String word, int control, boolean result) {
        if (control < word.length()) {
            if (!(word.charAt(control) != 'a' && word.charAt(control) != 'e' && word.charAt(control) != 'i'
                    && word.charAt(control) != 'o' && word.charAt(control) != 'u'
                    && word.charAt(control) != 'A' && word.charAt(control) != 'E' && word.charAt(control) != 'I'
                    && word.charAt(control) != 'O' && word.charAt(control) != 'U')) {
                result = false;
            }
            // System.out.println(
            // "ISCONSONANT Letra = " + word.charAt(control) + "/result = " + result +
            // "/control = " + control);
            result = isConsonant(word, control + 1, result);

        }

        return result;
    }

    public static boolean isVogal(String word, int control, boolean result) {
        if (control < word.length()) {
            if (word.charAt(control) != 'a' && word.charAt(control) != 'e' && word.charAt(control) != 'i'
                    && word.charAt(control) != 'o' && word.charAt(control) != 'u'
                    && word.charAt(control) != 'A' && word.charAt(control) != 'E' && word.charAt(control) != 'I'
                    && word.charAt(control) != 'O' && word.charAt(control) != 'U') {
                result = false;
            }
            // System.out.println(
            // "ISVOGAL Letra = " + word.charAt(control) + "/result = " + result + "/control
            // = " + control);
            result = isVogal(word, control + 1, result);

        }
        return result;
    }

    public static boolean isInteger(String string) {
        int intValue;

        // System.out.println(String.format("Parsing string: \"%s\"", string));

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            // System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public static boolean isFloat(String string) {
        float intValue;

        // System.out.println(String.format("Parsing string: \"%s\"", string));

        try {
            intValue = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            // System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
