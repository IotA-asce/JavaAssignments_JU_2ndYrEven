package assignment06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class DoctorList
{
    public ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();

    public ArrayList<String> stream = new ArrayList<>();
    public ArrayList<Integer> streamDocCount = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    static int count = 0;

    public void AddDoc()
    {
//         register new doc in database
        Doctor temp = new Doctor();
        Calendar calendar = Calendar.getInstance();
        String buff = "";

        System.out.println("\n_____________________________________________________________________");
        System.out.println("\n\t\tEnter details of the doctor below");
        System.out.println("_____________________________________________________________________");
        System.out.print("\n\t\t\tName           : ");
        temp.doctorName = scan.nextLine();
        System.out.print("\n\t\t\tSpecialization : ");
        temp.Specialization = scan.nextLine();

        // date of registration into system
        temp.registDate[0] = calendar.get(Calendar.DATE); // dd of dd/mm/yyyy
        temp.registDate[1] = calendar.get(Calendar.MONTH); // mm of dd/mm/yyyy
        temp.registDate[2] = calendar.get(Calendar.YEAR); // yyyy of dd/mm/yyyy

        temp.doctorID = String.valueOf(temp.Specialization.toUpperCase(Locale.ROOT).toCharArray(),0,3) + temp.registDate[2] + "-" + count;
        count++;
        doctors.add(temp);
        System.out.println("\n\n\tMESSAGE : registration of doctor in database successful");
        System.out.println("_____________________________________________________________________\n\n");
    }

    public void SetDocOnCallDates()
    {
        String id = "";
        String s = "";
        int index = -1;
        int date;

        if (doctors.isEmpty())
        {
            System.out.println("No doctors are registered");
        }else {
            System.out.println("\n_____________________________________________________________________");
            System.out.println("\t\t\tSet on call dates");
            System.out.print("\n\t\t\tDoc id : ");
            id = scan.nextLine();

            for (int i = 0; i < doctors.size(); i++) {
                if (id.equals(doctors.get(i).doctorID))
                {
                    index = i;
                    break;
                }
            }
            if (index == -1)
            {
                System.out.println("\n\n\tERROR : Matching id not found");
            }else
            {
                Calendar calendar = Calendar.getInstance();
                System.out.println("\n\t\tDoc Name       : " + doctors.get(index).doctorName);
                System.out.println("\n\t\tSpecialization : " + doctors.get(index).Specialization);
                System.out.println("\n\t\t__________________________________");
                System.out.println("\n\n\t\tEnter the on call dates for the month of " + months[calendar.get(Calendar.MONTH)] + "\n");
                for (int i = 0; i < 12; i++)                        // add an operation to detect duplicate on call date
                {
                    System.out.print("\t\t\tdate " + (i + 1) + " : ");
                    doctors.get(index).onCallDates[i] = scan.nextInt();
                    s = scan.nextLine();
                }
            }
            System.out.println("\n\n\tMESSAGE : On call dates set successfully");
        }
    }

    public void DocLeave()  // on call dates are mandatory
    {
        String searchID = "";
        int index = -1;

        System.out.println("Leave days");
        System.out.println();
        System.out.print("Enter doctor ID : ");
        searchID = scan.nextLine();

        if (doctors.isEmpty())
        {
            System.out.println("No doctors were registered");
        }else
        {
            for (int i = 0; i < doctors.size(); i++)
            {
                if (searchID.equals(doctors.get(i).doctorID))
                {
                    index = i;
                    break;
                }
            }

            if (index == -1)
            {
                System.out.println("ERR : Doc id not found");
            }else
            {
                int lev;
                int dat;
                String buff = "";
                System.out.println("Leaves available : " + doctors.get(index).leavesAvailable);
                System.out.print("Enter no of leaves opted for : ");
                lev = scan.nextInt();
                buff = scan.nextLine();

                if (lev > doctors.get(index).leavesAvailable)
                {
                    System.out.println("leaves not available");
                }else{
                    while (lev >= 0) {
                        System.out.print("Choose leave dates : ");
                        dat = scan.nextInt();
                        buff = scan.nextLine();
                        for (int i = 0; i < 12; )
                        {
                            if (dat == doctors.get(index).onCallDates[i])
                            {
                                System.out.println("Not available");
                            }else{
                                System.out.println("Date available");
                                doctors.get(index).leaveDays.add(dat);
                            }

                        }
                        lev--;
                    }

                }

            }

        }

    }

    public void AddNewPatient()
    {
        String s = "";
        String input = "";
        int index = -1;
        int tr = 0;
        boolean emergency = false;
        Patient tempPatient = new Patient();

        System.out.println("Register new Patient");
        System.out.print("Enter patient name : ");
        input = scan.nextLine();
        tempPatient.patientName = String.valueOf(input.toCharArray(),0,input.length());

        while (tr == 0) {
            System.out.print("Enter social security number : ");
            input = scan.nextLine();

            for (int i = 0; i < patients.size(); i++)
            {
                if (input.equals(patients.get(i).socialSecurityNumber))
                {
                    System.out.println("Social security number already exists");
                    System.out.print("want to re-enter social security number? (Y/n)");
                    s = scan.nextLine();

                    if (s.equalsIgnoreCase("n"))
                    {
                        System.out.println("Process exited successfully");
                        return;
                    }
                }
            }
            tr++;
        }
        tempPatient.patientName = String.valueOf(input.toCharArray(),0,input.length());

        System.out.print("Is emergency ? (Y/n) : ");
        s = scan.nextLine();
        if (s.equalsIgnoreCase("Y"))
        {
            tempPatient.emergency = true;
            System.out.print("Enter complication type : ");
            tempPatient.diseaseCategory = scan.nextLine();

            Calendar calendar = Calendar.getInstance();

            index = -1;
            int genPhy = -1;
            for (int i = 0; i < doctors.size(); i++)
            {
                if (doctors.get(i).Specialization.equalsIgnoreCase("general physician"))
                {
                    genPhy = i;
                }
                if (doctors.get(i).Specialization.equalsIgnoreCase(tempPatient.diseaseCategory))
                {
                    for (int j = 0; j < 12; j++)
                    {
                        if (doctors.get(i).onCallDates[j] == calendar.get(Calendar.DATE))
                        {
                            index = i;
                            i = doctors.size();
                            break;
                        }
                    }
                }
            }
            if (index == -1)
            {
                tempPatient.docAssignedId = String.valueOf(doctors.get(genPhy).doctorID.toCharArray(),0,doctors.get(genPhy).doctorID.length());
                tempPatient.admissionDate[0] = calendar.get(Calendar.DATE);
                tempPatient.admissionDate[0] = calendar.get(Calendar.MONTH);
                tempPatient.admissionDate[0] = calendar.get(Calendar.YEAR);

                doctors.get(genPhy).patientArrayList.add(tempPatient);
            }else
            {
                tempPatient.docAssignedId = String.valueOf(doctors.get(index).doctorID.toCharArray(),0,doctors.get(index).doctorID.length());
                tempPatient.admissionDate[0] = calendar.get(Calendar.DATE);
                tempPatient.admissionDate[0] = calendar.get(Calendar.MONTH);
                tempPatient.admissionDate[0] = calendar.get(Calendar.YEAR);
                doctors.get(index).patientArrayList.add(tempPatient);
            }

        }
        else if (s.equalsIgnoreCase("n"))
        {
            System.out.print("Enter disease category : ");
            tempPatient.diseaseCategory = scan.nextLine();
            System.out.print("Pre-referral Doc name : ");
            tempPatient.referalDocName = scan.nextLine();
            index = -1;

            for (int i = 0; i < doctors.size(); i++)
            {
                if (doctors.get(i).doctorName.equalsIgnoreCase(tempPatient.referalDocName))
                {
                    index = i;
                    break;
                }
            }

            if (index == -1)
            {
                Calendar calendar = Calendar.getInstance();
                String docId = "";

                System.out.println("Doctor does not practice at our hospital");
                System.out.println("Choose doctor form the list below");
                System.out.println();
                System.out.println("\tDoctor id\t\tDoctor Name\t\tExperience \t\t specialization");
                System.out.println();

                for (int i = 0; i < doctors.size(); i++)
                {
                    if (doctors.get(i).Specialization.equalsIgnoreCase(tempPatient.diseaseCategory))
                    {
                        System.out.printf("\t%20s\t%20s\t%5d\t%20s", doctors.get(i).doctorID, doctors.get(i).doctorName, (doctors.get(i).registDate[2] - calendar.get(Calendar.YEAR)), doctors.get(i).Specialization);
                    }
                }
                System.out.print("Enter doc code : ");
                docId = scan.nextLine();

                index = -1;
                for (int i = 0; i < doctors.size(); i++)
                {
                    if (docId.equals(doctors.get(i).doctorID))
                    {
                        index = i;
                        break;
                    }
                }
                tempPatient.docAssignedId = String.valueOf(doctors.get(index).doctorID.toCharArray(),0,doctors.get(index).doctorID.length());
                System.out.println("Choose OT date : ");
                System.out.print("day : ");
                tempPatient.otDate[0] = scan.nextInt();
                s = scan.nextLine();
                System.out.print("month : ");
                tempPatient.otDate[1] = scan.nextInt();
                s = scan.nextLine();
                System.out.print("year : ");
                tempPatient.otDate[2] = scan.nextInt();
                s = scan.nextLine();
                tempPatient.preReferal = true;

                doctors.get(index).patientArrayList.add(tempPatient);
            }else
            {
                // doctor from same hospital
                System.out.println("Choose OT date : ");
                System.out.print("day : ");
                tempPatient.otDate[0] = scan.nextInt();
                s = scan.nextLine();
                System.out.print("month : ");
                tempPatient.otDate[1] = scan.nextInt();
                s = scan.nextLine();
                System.out.print("year : ");
                tempPatient.otDate[2] = scan.nextInt();
                s = scan.nextLine();
                tempPatient.preReferal = true;
                tempPatient.docAssignedId = String.valueOf(doctors.get(index).doctorID.toCharArray(),0,doctors.get(index).doctorID.length());
                doctors.get(index).patientArrayList.add(tempPatient);
            }
        }
    }

    public void UpdateTestResults()
    {
        String ssn;
        System.out.print("Enter patient social security number : ");
        ssn = scan.nextLine();
        int indexp = -1;
        int indexd = -1;
        int indexdp = -1;
        String yn = "";
        ArrayList<String> t1 = new ArrayList<>();
        ArrayList<String> t2 = new ArrayList<>();
        String s1;
        String s2;
        String docId = "";

        for (int i = 0; i < patients.size(); i++)
        {
            if (ssn.equals(patients.get(i).socialSecurityNumber))
            {
                indexp = i;
                if (patients.get(i).tests.isEmpty())
                {
                    System.out.println("No previous data recorded");
                }
                docId = patients.get(i).docAssignedId;
                System.out.println("Enter updated test results below");
                System.out.println();

                while (yn.equalsIgnoreCase("y"))
                {
                    System.out.print("Enter test name : ");
                    s1 = scan.nextLine();
                    System.out.print("Enter test result : ");
                    s2 = scan.nextLine();
                    t1.add(s1);
                    t2.add(s2);

                    System.out.print("Do you wish to continue : (y/N) ");
                    yn = scan.nextLine();
                }
            }
        }
        if (indexp == -1)
        {
            System.out.println("Patient not found");
            return;
        }

        for (int i = 0; i < doctors.size(); i++)
        {
            if (docId.equals(doctors.get(i).doctorID))
            {
                indexd = i;
                for (int j = 0; j < doctors.get(indexd).patientArrayList.size(); j++)
                {
                    if (ssn.equals(patients.get(j).socialSecurityNumber))
                    {
                        indexdp = j;
                        break;
                    }
                }
                break;
            }
        }

        for (int i = 0; i < t1.size(); i++)
        {
            doctors.get(indexd).patientArrayList.get(indexdp).tests.add(t1.get(i));
            patients.get(indexp).tests.add(t1.get(i));

            doctors.get(indexd).patientArrayList.get(indexdp).testResults.add(t1.get(i));
            patients.get(indexp).testResults.add(t1.get(i));
        }
        System.out.println("Test result updated successfully");
    }

    public void CheckDischargeStatus()
    {
        Calendar calendar = Calendar.getInstance();
        String ssn = "";
        int indexP = -1;
        int indexD = -1;
        String docId = "";
        boolean logic;

        System.out.print("Enter social security number for patient : ");
        ssn = scan.nextLine();

        for (int i = 0; i < patients.size(); i++)
        {
            if (ssn.equals(patients.get(i).socialSecurityNumber))
            {
                indexP = i;
                docId = String.valueOf(patients.get(i).docAssignedId.toCharArray(),0,patients.get(i).docAssignedId.length());
                break;
            }
        }

        if ((calendar.get(Calendar.DATE) - patients.get(indexP).otDate[0]) > 5)
        {
            patients.get(indexP).dischargeCondition = true;
        }

        for (int i = 0; i < doctors.size(); i++)
        {
            if (docId.equals(doctors.get(i).doctorID))
            {
                indexD = i;
                for (int j = 0; j < doctors.get(indexD).patientArrayList.size(); j++)
                {
                    if (ssn.equals(patients.get(j).socialSecurityNumber))
                    {
                        patients.get(j).dischargeCondition = true;
                        System.out.println("Patient ready to be discharged");
                        break;
                    }
                }
                break;
            }
        }

    }

    public void ReleasePatient()
    {
        String ssn = "";
        int indexP = -1;
        int indexD = -1;
        int indexDP = -1;
        String docId = "";

        System.out.print("Enter patient social security number : ");
        ssn = scan.nextLine();

        for( int i = 0; i < patients.size(); i++ )
        {
            if (ssn.equals(patients.get(i).socialSecurityNumber))
            {
                if (patients.get(i).dischargeCondition)
                {
                    System.out.println("Patient ready to be discharged");
                    indexP = i;
                    docId = String.valueOf(patients.get(i).docAssignedId.toCharArray(),0,patients.get(i).docAssignedId.length());
                    break;
                }
                else
                {
                    System.out.println("Patient condition not ready for discharge");
                    return;
                }
            }
        }

        // not ready
        for (int i = 0; i < doctors.size(); i++)
        {
            if (docId.equals(doctors.get(i).doctorID))
            {
                indexD = i;
                for (int j = 0; j < doctors.get(indexD).patientArrayList.size(); j++)
                {
                    if (ssn.equals(patients.get(j).socialSecurityNumber))
                    {
                        doctors.get(i).patientArrayList.remove(j);
                    }
                }
                break;
            }
        }
        patients.remove(indexP);
        System.out.println("Patient removed successfully");
    }

    public void ShowDoctors()
    {
        if (doctors.isEmpty())
        {
            System.out.println("No Doctors were registered");
        }else {
            System.out.println("\n_____________________________________________________________________");
            System.out.println("|\t\tDoc id\t|\tDoc name\t|\tRegs date   |");
            System.out.println("|_______________________|_______________________|___________________|");
            System.out.println("|                       |                       |                   |");
            for (int i = 0; i < doctors.size(); i++)
            {
                System.out.printf("| %20s  | %20s  |  %d:%d:%d        |\n",doctors.get(i).doctorID,doctors.get(i).doctorName,doctors.get(i).registDate[0],doctors.get(i).registDate[1],doctors.get(i).registDate[2]);
            }
            System.out.println("|_______________________|_______________________|___________________|");
        }
    }

    public void ShowOnCallDates()
    {
        if (doctors.isEmpty())
        {
            System.out.println("No Doctors were registered");
        }else {
//            System.out.println("Doc id\t\tDoc name\t\tRegs date\n\n");
            for (int i = 0; i < doctors.size(); i++)
            {
                System.out.println("Doc name : " + doctors.get(i).doctorName);
                for (int j = 0; j < 12; j++)
                {
                    System.out.print(doctors.get(i).onCallDates[j] + "\t");
                }
            }
        }
    }

}
