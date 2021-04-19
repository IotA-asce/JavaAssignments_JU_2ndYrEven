package assignment06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Doctor
{
    public String doctorName = "";
    public String doctorID = "";
    public String Specialization = "";

    public int[] registDate = new int[3];
    public boolean availability = false;            // this might not be required

    public int[] onCallDates = new int[12];
    public ArrayList<Integer> leaveDays = new ArrayList<>();
    public int leavesAvailable = 5;

    public ArrayList<Patient> patientArrayList = new ArrayList<>();

    Calendar calendar = new GregorianCalendar();
}
