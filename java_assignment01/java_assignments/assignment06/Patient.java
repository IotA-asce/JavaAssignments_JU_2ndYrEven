package assignment06;

import java.util.ArrayList;

public class Patient
{
    public String patientName = "";
    public String socialSecurityNumber = "";
    public String docAssignedId = "";
    public boolean emergency = false;
    public boolean preReferal = false;
//    public boolean checkUp = false;

    public String diseaseCategory = "";

    // for the OT type
    public String oTType = "";
    public boolean otCompletion = false;
    public String referalDocName = "";
//    public String assignedDocId = "";
    public int[] otDate = new int[3];           // day month year format
//    public int[] otTime = new int[2];           // hh/mm format int 24 hr system
    public ArrayList<String> postOtStatusRepost = new ArrayList<>();    // post ot records to be updated daily

    public int[] admissionDate = new int[3];

    public boolean dischargeCondition = true;

    public ArrayList<String> tests = new ArrayList<>();
    public ArrayList<String> testResults = new ArrayList<>();

}
