package java_assignments.assignment01;

import java.util.Scanner;

public class Department extends Student
{
    public String department;
    private Scanner scan = new Scanner(System.in);
    Department()
    {
        department = "";
    }

    public void TakeInput()
    {
        System.out.println("\n\t\t\t\tEnter details to the student ->");
        System.out.print("\n\t\t\t\tEnter name\t\t: ");
        studentName = scan.nextLine();
        System.out.print("\t\t\t\tEnter department\t: ");
        department = scan.nextLine();
    }
}
