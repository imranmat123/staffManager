package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.StaffID;

public class StaffIDTest {
    public static void main(String[] args) {
        testStaffIDConstructor();
        testStaffIDGetInstance();
        testStaffIDGetUniqueLetter();
        testStaffIDGetUniqueID();
    }

    public static void testStaffIDConstructor() {
        //create a new staffID object
        StaffID staffID = StaffID.getInstance('a', 1);
        //check if the object has been created
        Assertions.assertNotNull(staffID);
    }

    public static void testStaffIDGetInstance() {
        //create a new staffID object
        StaffID staffID = StaffID.getInstance('a', 1);
        //check if the ID has been created
        Assertions.assertNotNull(staffID);
    }

    public static void testStaffIDGetUniqueLetter() {
        //create a new staffID object
        StaffID staffID = StaffID.getInstance('a', 1);
        //check if the unique letter has been created
        Assertions.assertEquals('a', staffID.getUniqueLetter());
    }

    public static void testStaffIDGetUniqueID() {
        //create a new staffID object
        StaffID staffID = StaffID.getInstance('a', 1);
        //check if the unique ID has been created
        Assertions.assertNotNull(staffID.getUniqueID());
    }

}
