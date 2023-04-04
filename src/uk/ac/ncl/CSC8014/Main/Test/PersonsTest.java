package uk.ac.ncl.CSC8014.Main.Test;
import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Main.StaffManager;
import uk.ac.ncl.CSC8014.Staff.Persons;
import uk.ac.ncl.CSC8014.Staff.Staff;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonsTest {
    private static StaffManager staffManager = StaffManager.getInstance();
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    public static void main(String[] args) {
        testPersonsGetInstance();
        testPersonsGetStaffMember();
    }

    public static void testPersonsGetInstance() {
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //testing the constructor of the employStaff method, located in the StaffManager class
        Staff one = staffManager.employStaff("ab", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //testing the getInstance method, by checking if the staff member is in the hashmap.
        Assertions.assertEquals(true, Persons.getStaffMember().containsValue(one));
    }

    public static void testPersonsGetStaffMember() {
        Persons.getStaffMember().clear();
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //testing the employStaff method
        Staff one = staffManager.employStaff("ab", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking if the staff member is in the hashmap.
        Assertions.assertEquals(true, Persons.getStaffMember().containsValue(one));
    }
}
