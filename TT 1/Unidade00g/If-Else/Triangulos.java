import java.util.*;

public class Triangulos
{
    public static void main (String[] args)
    {
        System.out.println ("Digite 3 numeros reais");
        Scanner scanner = new Scanner(System.in);

        System.out.print ("1º: ");
        int x = scanner.nextInt(); 

        System.out.print ("2º: ");
        int y = scanner.nextInt();
        
        System.out.print ("3º: ");
        int z = scanner.nextInt();
        
        if (x == y && y == z)
        {
            System.out.println ("Equilatero.");
        }

        else if ((x == y && y != z) || (x == z && y != z) || (y == z && x != y))
        {
            System.out.println ("Isosceles.");
        }

        else
        {
            System.out.println ("Escaleno.");
        }

        scanner.close();
    }
}