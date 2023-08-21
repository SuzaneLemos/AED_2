import java.util.Scanner;

public class Exercicio1P30 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String filename = reader.nextLine();

        Arq.openWrite(filename);

        Arq.println(reader.nextLine());

        // Arq.close(filename);

        reader.close();
    }
}