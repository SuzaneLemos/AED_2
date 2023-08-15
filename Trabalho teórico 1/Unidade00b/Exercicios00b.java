import java.io.*;
import java.util.*;

public class Exercicios00b 
{
    public static String exercicio1()
    {
        // Comando da questao
        System.out.println("Faca um metodo que receba um array de inteiros e um numero inteiro x e retorne um valor booleano " + 
                           "informando se o elemento x contido no array." + "\n");

        // Pegando o tamanho do array
        System.out.print ("Digite quantos numeros voce quer? ");  
        Scanner scanner = new Scanner(System.in);
        int tamanho = scanner.nextInt(); 
        int [] numeros = new int[tamanho];

        // Pegando os numeros e salvando no array 
        for (int i = 0; i < tamanho; i++)
        {
            System.out.print ("\nDigite seu " + (i + 1) + "º numero: ");
            numeros [i] = scanner.nextInt();
        }  
        
        // Pegando o numero que o usuario quer procurar
        System.out.print ("\n Qual numero voce quer achar dentro deste grupo de numeros digitados anteriormente? ");
        int x = scanner.nextInt();

        int i = 0;

        while (i < tamanho)
        {
            if (x == numeros[i])
            {
                boolean booleano = true;
                System.out.println ("\n" + booleano);

                break;
            }

            else
            {
                boolean booleano = false;
                System.out.println ("\n" + booleano);

                i++;
            }
        }

        scanner.close();                   
        return null;
    }

    public static String exercicio2()
    {
        // Comando da questao
        System.out.println("Repita o exercicio acima considerando que os elementos do array estao ordenados de forma crescente. " +
                           "Uma sugestao seria começar a pesquisa pelo meio do array." + "\n");
        
        // Pegando o tamanho do array
        System.out.print ("Digite quantos numeros voce quer? ");  
        Scanner scanner = new Scanner(System.in);
        int tamanho = scanner.nextInt(); 
        int [] numeros = new int[tamanho];

        // Pegando os numeros e salvando no array 
        for (int i = 0; i < tamanho; i++)
        {
            System.out.print ("Digite seu " + (i + 1) + "º numero: ");
            numeros [i] = scanner.nextInt();
        }

        // Organizar o array
        Arrays.sort(numeros);

        System.out.print("\nNumeros organizados: ");

        for (int num : numeros) 
        {
            System.out.print(num + " ");
        }
        
        // Pegando o numero que o usuario quer procurar
        System.out.print ("\nQual numero voce quer achar dentro deste grupo de numeros digitados anteriormente? ");
        int x = scanner.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < tamanho; i++)
        {
            if (x == numeros[i])
            {
                encontrado = true;

                break;
            }
        }

        System.out.println ("\n" + encontrado);

        scanner.close();
        return null;
    }

    public static String exercicio3()
    {
        // Comando da questao
        System.out.println("Faca um metodo que receba um array de inteiros e mostre na tela o maior e o menor elementos do array." + "\n");

        // Pegando o tamanho do array
        System.out.print ("Digite quantos numeros voce quer? ");  
        Scanner scanner = new Scanner(System.in);
        int tamanho = scanner.nextInt(); 
        int [] numeros = new int[tamanho];

        // Pegando os numeros e salvando no array 
        for (int i = 0; i < tamanho; i++)
        {
            System.out.print ("\nDigite seu " + (i + 1) + "º numero: ");
            numeros [i] = scanner.nextInt();
        }

        Arrays.sort(numeros);

        System.out.println("\nMenor: " + numeros[0]);
        System.out.println("Maior: " + numeros[tamanho - 1]);

        scanner.close();
        return null;
    }

    public static String exercicio4()
    {
        System.out.println("Repita o exercicio acima considerando que os elementos do array estao ordenados de forma crescente. " +
                           "Uma sugestao seria começar a pesquisa pelo meio do array." + "\n");

        // Pegando o tamanho do array
        System.out.print ("Digite quantos numeros voce quer? ");  
        Scanner scanner = new Scanner(System.in);
        int tamanho = scanner.nextInt(); 
        int [] numeros = new int[tamanho];

        // Pegando os numeros e salvando no array 
        for (int i = 0; i < tamanho; i++)
        {
            System.out.print ("\nDigite seu " + (i + 1) + "º numero: ");
            numeros [i] = scanner.nextInt();
        }

        Arrays.sort(numeros);

        System.out.println("\nMenor: " + numeros[0]);
        System.out.println("Maior: " + numeros[tamanho - 1]);

        scanner.close();
        return null;
    }

    public static void main (String[] args)
    {
        System.out.println ("Digite o numero do exercicio que deseja: 1 até 10");
        Scanner scanner = new Scanner(System.in);
        int numero = scanner.nextInt();

        System.out.println ("\n\n");

        switch (numero)
        {
            case 1: 
                exercicio1();
                break;

            case 2: 
                exercicio2();
                break;

            case 3: 
                exercicio3();
                break;

            case 4: 
                exercicio4();
                break;

            default:
                System.out.println("Numero de exercício invalido.");
        }

        scanner.close();
    }
}