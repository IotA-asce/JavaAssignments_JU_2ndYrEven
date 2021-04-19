package java_assignments.assignment01;

import java.util.Scanner;

public class Operations
{
    StudentList sl = new StudentList();
    private int choice;
    String s = "";
    private boolean trace;
    private Scanner scan = new Scanner(System.in);

    Operations()
    {
        trace = true;
    }

    public void PerformOperations()
    {
        while (trace)
        {
            System.out.println("_________________________________________________________________________________________________");
            System.out.println("|\t\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("|\t\t\t\t\tChoices available\t\t\t\t\t|");
            System.out.println("|\t\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("|_______________________________________________________________________________________________|");
            System.out.println("|\t\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("|\t\t\t\tRegister New Student       : 1\t\t\t\t\t|");
            System.out.println("|\t\t\t\tSet score                  : 2\t\t\t\t\t|");
            System.out.println("|\t\t\t\tPrepare marksheet          : 3\t\t\t\t\t|");
            System.out.println("|\t\t\t\tShow students (all)        : 4\t\t\t\t\t|");
            System.out.println("|\t\t\t\tShow no of students        : 5\t\t\t\t\t|");
            System.out.println("|\t\t\t\tDepartmental student cound : 6\t\t\t\t\t|");
            System.out.println("|\t\t\t\tShow sorted list           : 7\t\t\t\t\t|");
            System.out.println("|\t\t\t\tCheck Free memory          : 8\t\t\t\t\t|");
            System.out.println("|\t\t\t\tExit program               : 0\t\t\t\t\t|");
            System.out.println("|_______________________________________________________________________________________________|");

            System.out.print("\n\t\t\t\t\tEnter choice : ");
            choice = scan.nextInt();
            s = scan.nextLine();

            switch (choice)
            {
                case 1:
                    sl.NewStudentEntry();
                    break;
                case 2:
                    sl.SetScore();
                    break;
                case 3:
                    sl.PrepareMarksheet();
                    break;
                case 4:
                    sl.ShowStudents();
                    break;
                case 5:
                    sl.ShowNoOfStudentsThatTookAdmission();
                    break;
                case 6:
                    sl.ShowDeptCount();
                    break;
                case 7:
                    sl.ShowSortedListOfStudentsFromADepartment();
                    break;
                case 8: 
                    Runtime runtime = Runtime.getRuntime();
                    System.out.println("\n\n\t\tCurrent free memory : " + (double) runtime.freeMemory());
                    runtime.gc();
                    System.out.println("\n\t\tFree memory after calling gc : " + (double) runtime.freeMemory());

                    break;
                case 0:
                    trace = false;
                    break;
            }
        }
    }
}
