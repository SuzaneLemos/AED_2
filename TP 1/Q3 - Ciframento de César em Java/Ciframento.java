import java.util.*;

class Ciframento 
{
    public static void main(String[] args) throws Exception 
    {
        char[] entradaChar = new char[1000];
        int tam;
        boolean notFim;
        String entradaString = new String();

        while (!entradaString.equals("FIM")) 
        {
            entradaString = MyIO.readLine();

            if (!entradaString.equals("FIM")) 
            {
                str2char(entradaString, entradaChar);
                tam = entradaString.length();

                encriptar(entradaChar, tam);
            }
        }

    }

    public static void encriptar(char[] entradaChar, int tam) 
    {
        for (int i = 0; i < tam; i++) 
        {
            MyIO.print((char) (entradaChar[i] + 3 + 0x0));
        }

        MyIO.println("");
        return;
    }

    public static void str2char(String entradaString, char[] entradaChar) 
    {
        for (int i = 0; i < entradaString.length(); i++) 
        {
            entradaChar[i] = entradaString.charAt(i);
        }
    }
}