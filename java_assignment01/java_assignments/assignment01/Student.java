package java_assignments.assignment01;

import java.util.Scanner;

public class Student
{
    public String studentName;
    public String studentID;
    public String admissionDate;
    public boolean scoreEntryStatus;
    public boolean passStatus;
    public boolean supplyStatus;
    public int[] score;
    public int total;
    private Scanner scan = new Scanner(System.in);

    Student()
    {
        admissionDate = "";
        studentName = "";
        studentID = "";
        passStatus = true;
        supplyStatus = false;
        scoreEntryStatus = false;
        score = new int[5];
        total = 0;
    }

    public void TakeInput()
    {
        System.out.println("Enter details to the student ->");
        studentName = scan.nextLine();
    }
}
