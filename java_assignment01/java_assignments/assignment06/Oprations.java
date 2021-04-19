package assignment06;

import java.util.ArrayList;
import java.util.Scanner;

public class Oprations
{

    private DoctorList doctorList = new DoctorList();
    Scanner scan = new Scanner(System.in);

    void operate()
    {
        int choice;
        String s = "";
        int trace = 0;

        System.out.println("___________________________________________");
        System.out.println("|\t\tChoose options\t\t  |");
        System.out.println("|_________________________________________|");
        System.out.println("|\t\tAdd doc      : 1\t  |");
        System.out.println("|\t\tSow all docs : 2\t  |");
        System.out.println("|\t\tSet on-call  : 3\t  |");
        System.out.println("|\t\tShow on call : 4\t  |");
        System.out.println("|\t\tSet leave da : 5\t  |");
        System.out.println("|\t\tAdd new patient : 6\t  |");
        System.out.println("|\t\tUpdate test result : 7\t  |");
        System.out.println("|\t\tCheck discharge status : 8\t  |");
        System.out.println("|\t\tDischarge patient : 9\t  |");
        System.out.println("|_________________________________________|");

        while (trace == 0)
        {
            System.out.println("");                         // do you wish to continue Y/n
            System.out.print("\n---->Enter choice : ");
            choice = scan.nextInt();
            s = scan.nextLine();

            switch (choice)
            {
                case 1:
                    doctorList.AddDoc();
                    break;
                case 2:
                    doctorList.ShowDoctors();
                    break;
                case 3:
                    doctorList.SetDocOnCallDates();
                    break;
                case 4:
                    doctorList.ShowOnCallDates();
                    break;
                case 5:
                    doctorList.DocLeave();
                    break;
                case 6:
                    doctorList.AddNewPatient();
                    break;
                case 7:
                    doctorList.UpdateTestResults();
                    break;
                case 8:
                    doctorList.CheckDischargeStatus();
                    break;
                case 9:
                    doctorList.ReleasePatient();
                    break;
                case 10:
                    trace++;
                    break;
            }
        }
    }

}
