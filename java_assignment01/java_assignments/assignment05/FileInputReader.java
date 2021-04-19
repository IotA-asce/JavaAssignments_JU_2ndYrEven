package java_assignments.assignment05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInputReader
{
    private File fr = new File("InputFile.txt");
    public ArrayList<String> fileStrings = new ArrayList<>();

    public void ReadFile()
    {
        try
        {
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
//                System.out.println("Line : " + line);
                fileStrings.add(line);
            }

            scan.close();
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("File not found");
        }
//        System.out.println("ArrayList size : " + fileStrings.size());
    }
}
