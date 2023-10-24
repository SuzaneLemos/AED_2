import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Palindromo 
{
    // My Method to check
    public static boolean isPal(String s) 
    { 
        if (s.length() == 0 || s.length() == 1)
            return true;
        if (s.charAt(0) == s.charAt(s.length() - 1))
        {      
            return isPal(s.substring(1, s.length() - 1));
        }

        return false;
    }

    public static void main(String[] args) throws Exception 
    {
        // For capturing user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // System.out.println("Enter the String for check:");
        String string = "";

        while (!string.equals("FIM")) 
        {
            string = br.readLine();

            if (isPal(string))
            {
                System.out.println("SIM");
            }
            
            else
            {
                System.out.println("NAO");
            }
        }
    }
}