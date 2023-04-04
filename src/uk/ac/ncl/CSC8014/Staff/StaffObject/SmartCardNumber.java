package uk.ac.ncl.CSC8014.Staff.StaffObject;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

final public class SmartCardNumber{
    //create a static variable called CardNumber.
    private static final int CARD_NUMBER = new AtomicInteger().incrementAndGet();
    //create a static variable that will be used to generate the serial number of the smart card
    private static final Map<String, SmartCardNumber> SMART_CARD_NUMBERS = new HashMap<>();
    //create a static variable that will be used to generate the serial number of the smart card
    private static final Map<String, AtomicInteger> COUNTERS = new HashMap<String, AtomicInteger>();
    //create a first Name Initial variable.
    private String firstNameInitial;
    //create a last Name Initial variable.
    private String lastNameInitial;
    //create a serial number variable.
    private String serialNumber;
    //create a year of issue variable.
    private int yearOfIssue;
    //create a staff name variable.
    private Name staffName;


    /**
     * SmartCardNumber constructor, take in a Name object and sets the
     * firstNameInitial by finding the char at index 0, and converting it to a string,
     * the constructor also does this for the lastNameInitial.
     * The constructor also sets the serialNumber by using the String.format method, and the CARD_NUMBER variable
     * and this sets the yearOfIssue by using the Calendar class.
     * @param staffName
     */
    //creates a constructor for the SmartCardNumber class, using the Name class.
    private SmartCardNumber(Name staffName) {
        this.staffName = staffName;
        this.firstNameInitial = "" + staffName.getFirstName().toUpperCase().charAt(0);
        this.lastNameInitial = ""+ staffName.getLastName().toUpperCase().charAt(0);
        this.serialNumber = String.format("%02d", CARD_NUMBER);
        //get the current year, and set it to the yearOfIssue variable, using the Calendar class.
        Calendar createdOn = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        //get the current currentDate
        Date currentDate = new Date();
        //set the calendar to the currentDate, so that it can get the current year
        createdOn.setTime(currentDate);
        this.yearOfIssue = createdOn.get(Calendar.YEAR);
    }

    /**
     * The getInstance method is a Singleton design pattern and is designed to be an extension of the SmartCardNumber constructor.
     * The method will return a new SmartCardNumber object, if the staff member does not exist.
     * Additionally, the getInstance method will also generate a unique ID for the staff member,
     * using the first name initial, last name initial, serial number and year of issue.
     * The mothod will then use the unique ID to check if the staff member exists in the map,
     * if the staff member does not exist, it will create a new staff member and add it to the map.
     *
     * @param forename
     * @param surname
     * @return
     */

    public static SmartCardNumber getInstance(String forename, String surname) {
        //get the current year
        Calendar createdOn = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        //get the current currentDate
        Date currentDate = new Date();
        //set the calendar to the currentDate, so that it can get the current year
        createdOn.setTime(currentDate);
        //create a new staff member, using the Name class. (a class that contains the first name and last name of the staff member)
        Name staff = new Name(forename,surname);
        //get the atomicCounter for the staff member. If the staff member does not exist, it will return null
        AtomicInteger atomicCounter = COUNTERS.get(staff.getFullName());
        //if the atomicCounter is null, create a new atomicCounter for the staff member

        if (atomicCounter == null) {
            //create a new atomicCounter
            atomicCounter = new AtomicInteger(1);
            //add the atomicCounter to the map
            COUNTERS.put(staff.getFullName(), atomicCounter);
        }

        // uniqueID = firstNameInitial + lastNameInitial + "-" + serialNumber + "-" + yearOfIssue;
        String uniqueID = "" + staff.getFirstName().toUpperCase().charAt(0) + staff.getLastName().toUpperCase().charAt(0) + "-" + String.format("%02d", atomicCounter.getAndIncrement()) + "-" + createdOn.get(Calendar.YEAR);

        //get the staff member from the map using the uniqueID. If the staff member does not exist, it will return null
        SmartCardNumber employeeID = SMART_CARD_NUMBERS.get(uniqueID);
        if (employeeID == null) {
            //create a new staff member
            employeeID = new SmartCardNumber(staff);
            //add the staff member to the map
            SMART_CARD_NUMBERS.put(uniqueID, employeeID);
        }
        //return the staff member
        return employeeID;
    }
    //get the smart card number
    public String getSmartCardNumber() {
        return firstNameInitial + lastNameInitial + "-" + serialNumber + "-" + yearOfIssue;
    }

    //override the toString method, returning the smart card number.
    @Override
    public String toString() {
        return firstNameInitial + lastNameInitial + "-" + serialNumber + "-" + yearOfIssue;
    }

    //getters and setters

    public String getFirstNameInitial() {
        return firstNameInitial;
    }

    public String getLastNameInitial() {
        return lastNameInitial;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public Name getStaffName() {
        return staffName;
    }

}
