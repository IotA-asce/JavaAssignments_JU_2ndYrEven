package java_assignments.assignment01;

import java.util.*;

public class StudentList
{
    ArrayList<Department> departments;                      // all the students are stored here
    private Department temporaryDept;                       // a temporary object for obtaining the new entry
    private ArrayList<Department> failedStudentsList;                   // list of failed students which is to be filled up at the end of the SetScore() method
    private static int[] count;
    private String buff;
    private int supplyCount;
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Department> buffer;

    /*
    the ds below keeps tab on the departmental student count

    deptStudentCount can increase and decrease as it is the number of students present in a department
    deptStudentChronology can only increase as it maintains roll count for students to take new admission
    */
    public ArrayList<String> deptName;
    public ArrayList<Integer> deptStudentCount;
    public ArrayList<Integer> deptStudentChronology;
    int year;

    static { count = new int[2]; }

    StudentList()
    {
//      temporaryDept = new Department();
        departments = new ArrayList<>();
        deptName = new ArrayList<>();
        deptStudentCount = new ArrayList<>();
        deptStudentChronology = new ArrayList<>();
        supplyCount = 0;
        buff = "";
        failedStudentsList = new ArrayList<>();
        buffer = new ArrayList<>();
    }

    public void NewStudentEntry()       // new entry is treated as new Admission
    {
        count[0]++;
        count[1]++;
        Calendar calendar = Calendar.getInstance();
        temporaryDept = new Department();

        System.out.println("________________________________________________________________________________________________");
        temporaryDept.TakeInput();

        int index = -1;
        if (deptName.isEmpty())     // too many conditions introduced
        {
            index = -1;
        }else {
            for (int i = 0; i < deptName.size(); i++) {
                if (deptName.get(i) == null) {
                    break;
                } else {
                    if (temporaryDept.department.equalsIgnoreCase(deptName.get(i))) {
                        index = i;
                        int tc = deptStudentCount.get(i);
                        int tcr = deptStudentChronology.get(i);

                        deptStudentCount.remove(i);
                        deptStudentChronology.remove(i);

                        deptStudentCount.add(i,tc+1);
                        deptStudentChronology.add(i, tcr+1);
                        break;
                    }
                }
            }
        }
        if (index == -1)
        {
            deptName.add(temporaryDept.department);
            deptStudentCount.add(1);
            deptStudentChronology.add(1);
            index = deptName.size()-1;
        }

        year = calendar.get(Calendar.YEAR);

        if ( year >= 2000 )
        {
            year -= 2000;
        } else {
            year -= 1900;
        }
        if (count[0] < 10)
        {
            buff = "000";
        }else if (count[0] < 100) {
            buff = "00";
        }else if (count[0] < 1000)
        {
            buff = "0";
        }else {
            buff = "";
        }
        temporaryDept.admissionDate = calendar.get(Calendar.DATE) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
        temporaryDept.studentID = temporaryDept.department.toUpperCase(Locale.ENGLISH) + year + buff + deptStudentChronology.get(index);

        departments.add(temporaryDept);
        temporaryDept = null;
        System.gc();
    }

    void SetScore()
    {
//        score entered status
        String tempID = new String();
        System.out.print("\n\t\tEnter Roll of student whose score is to be registered : ");
        tempID = scan.nextLine();
        System.out.println("\n________________________________________________________________________________________________\n");
        System.out.println("\t\t\t\t\tROLL INPUT : " + tempID);
        int index = -1;

        for (int i = 0; i < departments.size(); i++)
        {
            if (tempID.equalsIgnoreCase(departments.get(i).studentID))
            {
                temporaryDept = new Department();
                index = i;
                System.out.println("\n\t\t\t\tEnter the score to the subjects below\n");
                for (int j = 0; j < 5; j++)
                {
                    System.out.print("\t\t\t\t  Enter marks to subject " + (j+1) + " : ");
                    departments.get(i).score[j] = scan.nextInt();
                    departments.get(i).total += departments.get(i).score[j];
                    if (departments.get(i).score[j] < 45)
                    {
                        supplyCount++;
                        departments.get(i).supplyStatus = true;
                    }
                }
                String s = scan.nextLine();
                if (supplyCount >= 4)
                {
                    departments.get(i).passStatus = false;
                    // remove form the main list and add into the failedStudentList
                    // failedStudentsList.add(departments.get(i));
                    for (int p = 0; p < deptName.size(); p++)
                    {
                        if(departments.get(i).department.equals(deptName.get(p)))
                        {
                            index = p;break;
                        }
                    }
                    int ct = deptStudentCount.get(index);
                    deptStudentCount.remove(index);
                    deptStudentCount.add(index,--ct);
                    // System.out.println("\n\n\t\tCurrent free memory : " + );
                    departments.remove(i);
                    break;
                }
                departments.get(i).scoreEntryStatus = true;
                break;
            }
        }
        if (index == -1)
        {
            System.out.println("\t\t\tERROR : Student with matching roll not found");
            // roll not found
        }

        tempID = null;
        System.gc();
    }

    void ShowStudents()
    {
        for (Department d : departments)
        {
            System.out.println("_____________________________________________________________________________________________");
            System.out.println("\t\tName : " + d.studentName + "\t\tStudent ID : " + d.studentID );
        }
    }

    void ShowDeptCount()
    {
        System.out.println("\n_________________________________________________________________________________________________");
        for (String s : deptName)
        {
            System.out.print("\t\t" + s.toUpperCase(Locale.ENGLISH));
        }
        System.out.println();
        System.out.print("  ");
        for (Integer i : deptStudentCount)
        {
            System.out.print("\t\t" + i);
        }
        System.out.println();
    }

    void ShowNoOfStudentsThatTookAdmission()
    {
        System.out.println("\n\t\t\tNo of students that took admission : " + count[0]);
    }

    void PrepareMarksheet()
    {
        String ts = "";
        int index = -1;
        System.out.print("\n\t\t\tEnter student roll to obtain marksheet : ");
        ts = scan.nextLine();

        String pass_fail = "";

        for (int i = 0; i < departments.size(); i++)
        {
            if (ts.equalsIgnoreCase(departments.get(i).studentID))
            {
                index = i;
                if (!departments.get(index).scoreEntryStatus)
                {
                    System.out.println("\n\t\t\tERROR : Student score not set yet");
                    return;
                }
                break;
            }
        }
        if (index != -1)
        {
            if (!departments.get(index).scoreEntryStatus)
            {
                System.out.println("\n\t\tERROR : Score for this student is not set, unable to produce marksheet");
                return;
            }
            System.out.println("\n\n_______________________________________________________________________________________________________");
            System.out.println("_______________________________________________________________________________________________________");
            System.out.println("Name : " + departments.get(index).studentName + "\t\t\tRoll no : " + departments.get(index).studentID + "\t\t\tAdmission date : " + departments.get(index).admissionDate);
            System.out.println("_______________________________________________________________________________________________________");
            System.out.println("\n\tSubject name\t\t\t  marks obtained\t\t\t\t\tstatus");
            System.out.println("_______________________________________________________________________________________________________");

            for (int k = 0; k < 5; k++)
            {
                pass_fail = new String();
                if (departments.get(index).score[k] >= 45)
                {
                    pass_fail = "pass";
                }else {
                    pass_fail = "fail";
                }
                System.out.println("\tSubject " + (k+1) + "\t\t\t\t" + departments.get(index).score[k] + "\t\t\t\t\t" + pass_fail);
                System.out.println("_______________________________________________________________________________________________________");
                pass_fail = null;
            }

            if (departments.get(index).passStatus == true)
            {
                pass_fail = "promoted";
            }else {
                pass_fail = "detained";
            }
            System.out.println("\n\t\t\t\t\tVerdict : " + pass_fail);
            System.out.println("_______________________________________________________________________________________________________");
        // }else {

        //     /*
        //     search the existence of the roll in the failed students list
        //      */

        //     if (!failedStudentsList.get(index).scoreEntryStatus)
        //     {
        //         System.out.println("Score for this student is not set, unable to produce marksheet");
        //         return;
        //     }
        //     System.out.println("\n\n________________________________________________________________________________");
        //     System.out.println("________________________________________________________________________________");
        //     System.out.println("Name : " + failedStudentsList.get(index).studentName + "\t\tRoll no : " + failedStudentsList.get(index).studentID + "\tAdmission date : " + failedStudentsList.get(index).admissionDate);
        //     System.out.println("________________________________________________________________________________");
        //     System.out.println("\tSubject name\t\tmarks obtained\t\tstatus");

        //     for (int k = 0; k < 5; k++)
        //     {
        //         pass_fail = new String();
        //         if (failedStudentsList.get(index).score[k] >= 45)
        //         {
        //             pass_fail = "pass";
        //         }else {
        //             pass_fail = "fail";
        //         }
        //         System.out.println("\tSubject " + (k+1) + "\t\t\t" + failedStudentsList.get(index).score[k] + "\t\t\t\t\t" + pass_fail);
        //         System.out.println("________________________________________________________________________________");
        //         pass_fail = null;
        //     }

        //     if (failedStudentsList.get(index).passStatus == true)
        //     {
        //         pass_fail = "promoted";
        //     }else {
        //         pass_fail = "detained";
        //     }
        //     System.out.println("Verdict : " + pass_fail);
        //     System.out.println("________________________________________________________________________________");
        }
        if (index == -1){
            System.out.println("Student with matching roll not found in database");
            System.out.println("________________________________________________________________________________");
        }
        System.gc();
    }

    public void ShowSortedListOfStudentsFromADepartment()
    {
        /*
        Operations to perform
            1. check for the existence of the department from the deptName
                mark index and display count
            2. Search the whole Departments for the students with matching departments
            3. Flush the students into the buffer arraylist
                (better to insert the students based on total score)
            4. loop through the arraylist buffer and display the students available
        */
        String tdep = "";
        int index = -1;
        int checkTotal = 0;
        int highestIndex = 0;

        System.out.println("________________________________________________________________________________________________________");
        System.out.println("|   To get the sorted list of the students of a particular department type in the necessary details    |");
        System.out.println("________________________________________________________________________________________________________");

        System.out.println();
        System.out.print("\n--------> Enter the Department code : ");
        tdep = scan.nextLine();

        for (int i = 0; i < deptName.size(); i++)
        {
            if (tdep.equalsIgnoreCase(deptName.get(i)))
            {
                index = i;
            }
        }
        if (index != -1)
        {
            System.out.println("--------> No of student in " + tdep.toUpperCase(Locale.ENGLISH) + " : " + deptStudentCount.get(index));

            /*
            Operation 1 confirmed here
            Operation 2 to commence
            */
            for (int k = 0; k < departments.size(); k++)
            {
                if (!departments.get(k).scoreEntryStatus)
                {
                    continue;
                }
                if (departments.get(k).department.equalsIgnoreCase(tdep))
                {
                    /* if it matches then the total is to be checked
                        if total is greater than checkTotal
                        it is inserted before the given index
                     */
                    int cot = buffer.size();
                    if (cot == 0)
                    {
                        cot = 1;
                    }
                    boolean found = false;
                    for (int l = 0; l < cot; l++)
                    {
                        if (departments.get(k).total > checkTotal)
                        {
                            checkTotal = departments.get(l).total;
                            buffer.add(l,departments.get(k));
                            found = true;
                        }
                    }
                    if (!found)
                    {
                        buffer.add(departments.get(k));
                    }

                }
            }

            System.out.println("\n--------> Ranked list of students in " + tdep.toUpperCase(Locale.ENGLISH) + " provided below\n");
            System.out.println("\tRoll No     Student Name     Sub 1   Sub 2   Sub 3   Sub 4   Sub 5       Total");
            System.out.println("_______________________________________________________________________________________________________\n");
            for (int k = 0; k < buffer.size(); k++)
            {
                System.out.print("\t" + buffer.get(k).studentID + "  " + buffer.get(k).studentName + "");

                for (int itt = 0; itt <= (15 - buffer.get(k).studentName.length()); itt++)
                {
                    System.out.print(" ");
                }
            
                System.out.print("  ");
                for (int itt = 0; itt < 5; itt++)
                {
                    System.out.print(buffer.get(k).score[itt] + "\t");
                }
                System.out.println("    " + buffer.get(k).total);
            }
        }else{
            System.out.println("\n\n\n--------> ERROR :\t\t\t\tDepartment with matching code not found");
            return;
        }
        buffer.clear();
    }

}