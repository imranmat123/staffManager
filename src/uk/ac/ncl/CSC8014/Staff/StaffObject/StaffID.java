package uk.ac.ncl.CSC8014.Staff.StaffObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

final public class StaffID{
    private static final Map<String, StaffID> STAFF_MEMBERS = new HashMap<>();
    private static final Map<Character, AtomicInteger> COUNTERS = new HashMap<>();

    private char uniqueLetter;
    private String uniqueID;
    private int digit;

    /**
     * StaffID constructor, takes in three parameters, a char, an int and a String.
     * The constructor than sets the uniqueLetter and digit variables, and the uniqueID variable.
     * @param letter
     * @param number
     */
    //Constructor for the StaffID class.
    private StaffID(char letter, int number, String instanceID) {
        this.uniqueLetter = letter;
        this.digit = number;
        this.uniqueID = instanceID;
    }
    /**
     * The getInstance method is a Singleton design pattern and is designed to be an extension of the StaffID constructor.
     * The method will return a new StaffID object, if the staff member does not exist.
     * Additionally, the getInstance method will also generate a instanceID for the staff member,
     * using the unique letter, digit and instanceID.
     * The mothod will then use the instanceID to check if the staff ID exists in the map,
     * if the staff ID does not exist, it will create a new staff member and add it to the map.
     * Additionally, an AtomicInteger is used to ensure that the staff ID is unique, by incrementing the digit.
     * Furthermore, the getInstance method also ensures that the digit is always 3 digits long.
     * @param uniqueLetter
     * @param digit
     * @return
     */
    //create a static method that will be used to generate the staff ID of the staff member, this ensures that the staff ID is unique.
    //this is done by using a map, comparing the uniqueID to the staffMembers map, if the uniqueID does not exist, it will be added to the map.
    public static StaffID getInstance(char uniqueLetter, int digit) {
        //get the atomicCounter from the counters map.
        AtomicInteger atomicCounter = COUNTERS.get(uniqueLetter);
        //if the atomicCounter does not exist, create a new atomicCounter.
        if (atomicCounter == null) {
            //create a new atomicCounter.
            atomicCounter = new AtomicInteger(0);
            //add the atomicCounter to the counters map, using the unique letter as the key.
            //System.out.println(atomicCounter + "adhkajd");
            COUNTERS.put(uniqueLetter, atomicCounter);
        }
        //instanceID is the staff ID, it is a combination of the unique letter and the digit.
        //the instanceID is a String representation of the staff ID.
        String instanceID = uniqueLetter + "" + String.format("%03d", atomicCounter.incrementAndGet());
        //set employeeID equal to the instanceID from the staffMembers map.
        StaffID employeeID = STAFF_MEMBERS.get(instanceID);
        //if the staff ID does not exist, create a new staff ID.
        if (employeeID == null) {
            //create a new staff ID, using the unique letter, digit and instanceID.
            employeeID = new StaffID(uniqueLetter,digit, instanceID);
            //add staff ID to the staffMembers map, using the instanceID as the key.
            STAFF_MEMBERS.put(instanceID, employeeID);
        } else {
            //throw an exception if staff ID already exists.
            throw new IllegalArgumentException("Staff ID already exists");
        }
        //return staff ID.
        return employeeID;
    }


    @Override
    public String toString() {
        return "StaffID: " + uniqueID;
    }

    //Getters and Setters
    public char getUniqueLetter() {
        return uniqueLetter;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
