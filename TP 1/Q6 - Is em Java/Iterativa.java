import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Iterativa 
{
    public static void main(String[] args) throws Exception 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = "";
        String x1;
        String x2;
        String x3;
        String x4;

        while (!word.equals("FIM")) 
        {
            word = br.readLine();
            if (isConsonant(word) == true) 
            {
                x1 = "SIM";
            } 
            
            else 
            {
                x1 = "NAO";
            }

            if (isVogal(word) == true) 
            {
                x2 = "SIM";
            } 
            
            else 
            {
                x2 = "NAO";
            }

            if (isInteger(word) == true) 
            {
                x3 = "SIM";
            } 
            
            else 
            {
                x3 = "NAO";
            }

            if (isFloat(word) == true) 
            {
                x4 = "SIM";
            } 
            
            else 
            {
                x4 = "NAO";
            }

            System.out.println(x1 + " " + x2 + " " + x3 + " " + x4 + " ");
        }
    }

    public static boolean isConsonant(String word) 
    {
        boolean result = true;

        for (int index = 0; index < word.length(); index++) 
        {
            if (!(word.charAt(index) != 'a' && word.charAt(index) != 'e' && word.charAt(index) != 'i'
                    && word.charAt(index) != 'o' && word.charAt(index) != 'u'
                    && word.charAt(index) != 'A' && word.charAt(index) != 'E' && word.charAt(index) != 'I'
                    && word.charAt(index) != 'O' && word.charAt(index) != 'U')) 
            {
                result = false;

            }
        }

        return result;
    }

    public static boolean isVogal(String word) 
    {
        boolean result = true;

        for (int index = 0; index < word.length(); index++) 
        {
            // char charat = word.charAt(index);
            if (word.charAt(index) != 'a' && word.charAt(index) != 'e' && word.charAt(index) != 'i'
                    && word.charAt(index) != 'o' && word.charAt(index) != 'u'
                    && word.charAt(index) != 'A' && word.charAt(index) != 'E' && word.charAt(index) != 'I'
                    && word.charAt(index) != 'O' && word.charAt(index) != 'U') 
            {
                result = false;
            }
        }

        return result;
    }

    public static boolean isInteger(String string) 
    {
        int intValue;

        //System.out.println(String.format("Parsing string: \"%s\"", string));

        try 
        {
            intValue = Integer.parseInt(string);
            return true;
        } 
        
        catch (NumberFormatException e) 
        {
            // System.out.println("Input String cannot be parsed to Integer.");
        }

        return false;
    }

    public static boolean isFloat(String string) 
    {
        float intValue;

        //System.out.println(String.format("Parsing string: \"%s\"", string));

        try 
        {
            intValue = Float.parseFloat(string);
            return true;
        } 
        
        catch (NumberFormatException e) 
        {
            // System.out.println("Input String cannot be parsed to Integer.");
        }
        
        return false;
    }
}
