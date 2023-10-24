import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.Math;

public class Arquivo
{
    public static void main(String[] args) throws Exception 
    {
        // System.out.println("Read quantity");
        int quantity = MyIO.readInt();
        // System.out.println("R A S Initializing...");
        readAndStore(quantity);
        // System.out.println("R A S END");
        // System.out.println("S F F Initializing...");
        showFromFile(quantity);
    }

    public static void readAndStore(int control) throws Exception 
    {
        RandomAccessFile fileOUT = new RandomAccessFile("pubOUT.txt", "rw");

        int index = 0;
        float realNum;

        fileOUT.seek(index);
        while (index < control) 
        {
            // System.out.println("Reading File...");
            realNum = MyIO.readFloat();
            fileOUT.writeFloat(realNum);

            index++;
        }

        fileOUT.close();
    }

    public static void showFromFile(int quantity) throws IOException 
    {
        RandomAccessFile fileIN = new RandomAccessFile("pubOUT.txt", "r");
        float number;

        for (int index = 0; index < quantity; index++) 
        {
            fileIN.seek((quantity - index - 1) * 4);

            number = fileIN.readFloat();

            if (number % 1 == 0) 
            {
                System.out.println((int) number);
            } 
            
            else 
            {
                System.out.println((float) number * 1000 / 1000);
            }
        }

        fileIN.close();
    }
}
