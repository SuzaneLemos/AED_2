import java.util.*;

public class Aprovado 
{
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println ("Digite sua nota: ");
        int nota = scanner.nextInt();

        if (nota  >= 80)
        {
            System.out.println ("Parabens, muito bom.");
        }

        else if (nota >= 70 && nota < 80)
        {
            System.out.println ("Parabens, aprovado.");
        }

        else 
        {
            System.out.println ("Infelizmente, reprovado");
        }

        scanner.close();
    }
    
}
