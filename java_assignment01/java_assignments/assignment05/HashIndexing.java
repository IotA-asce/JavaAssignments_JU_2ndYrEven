package java_assignments.assignment05;

import java.util.HashSet;

public class HashIndexing
{
    FileInputReader fir = new FileInputReader();
    HashSet<String> hashedIndex = new HashSet<>(2700,0.3f);

    public void Hash()
    {
        fir.ReadFile();
//        System.out.println("hash size : " + hashedIndex.size());
//        System.out.println("Arraylist size : " + fir.fileStrings.size());

        for (int i = 0; i < fir.fileStrings.size(); i++)
        {
            int lastindex = 0;
            String sp = " ";
            String nl = "\n";
            String temp = "";

            for(int j = 0; j < fir.fileStrings.get(i).length(); j++)
            {
                if (fir.fileStrings.get(i).charAt(j) == sp.charAt(0) || fir.fileStrings.get(i).charAt(j) == nl.charAt(0))
                {
                    lastindex = j;
                    break;
                }
            }
            temp = String.copyValueOf(fir.fileStrings.get(i).toCharArray(),0,lastindex);
//            System.out.println("temp : " + temp);
            hashedIndex.add(temp);
        }
    }
}
