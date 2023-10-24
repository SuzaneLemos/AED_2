import java.util.*;
//import java.io.*;

public class Ciframento 
{
    public static String mudar(String frase)
    {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);

            if (letra != '�') {
                letra = (char) (letra + 3);
            }
            
            resultado.append(letra);
        }

        return resultado.toString();
    }

    public static void main (String[] args)
    {   
        Scanner sc = new Scanner (System.in);
        String palavra = "";

        // Para ler tudo que for inserido ate chegar na palavra FIM
        while (!palavra.equals("FIM"))
        {
            palavra = sc.nextLine();

            if (!palavra.equals("FIM"))
            {
                System.out.println(mudar(palavra));
            }

            else
            {
                break;
            }
        }

        sc.close();
    }
}