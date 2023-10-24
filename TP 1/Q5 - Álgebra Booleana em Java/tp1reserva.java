import java.io.BufferedReader;
import java.io.InputStreamReader;

public class tp1reserva {

    public static void main(String[] args) {

        boolean A = false;
        boolean B = false;
        boolean C = false;

        or(or(and(not(and(A , B)) , not(C)) , and(not(A) , B , C) , and(A , B , C) , and(A , not(B) , not(C))) , and(A , not(B) , C));



        // lê a expressão
        // ex. a && b && c
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Digite a expressão booleana a ser processada: ");
        // expressao= br.readLine();

    }

    public static boolean not(boolean inverter) {
        if (inverter == true) {
            return inverter == false;
        } else {
            return inverter = true;
        }
    }

    public static boolean and(boolean A, boolean B) {

        return (A && B);
    }

    public static boolean and(boolean A, boolean B, boolean C) {

        return (A && B && C);
    }

    public static boolean and(boolean A, boolean B, boolean C, boolean D) {

        return (A && B && C && D);
    }

    public static boolean and(boolean A, boolean B, boolean C, boolean D, boolean E) {

        return (A && B && C && D && E);
    }

    public static boolean and(boolean A, boolean B, boolean C, boolean D, boolean E, boolean F) {

        return (A && B && C && D && E && F);
    }

    public static boolean or(boolean A, boolean B) {

        return (A || B);
    }

    public static boolean or(boolean A, boolean B, boolean C) {

        return (A || B || C);
    }

    public static boolean or(boolean A, boolean B, boolean C, boolean D) {

        return (A || B || C || D);
    }

    public static boolean or(boolean A, boolean B, boolean C, boolean D, boolean E) {

        return (A || B || C || D || E);
    }

    public static boolean or(boolean A, boolean B, boolean C, boolean D, boolean E, boolean F) {

        return (A || B || C || D || E || F);
    }
}