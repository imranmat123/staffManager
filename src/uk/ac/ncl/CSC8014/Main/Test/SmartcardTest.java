package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Main.StaffManager;
import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCard;
import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCardNumber;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SmartcardTest {
    private static StaffManager staffManager = StaffManager.getInstance();
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();

    public static void main(String[] args) {
        testSmartCardConstructor();
        testSmartCardGetSmartCardNumber();
        testSmartCardGetDateOfIssue();
        testSmartCardGetExpiryDate();
        testSmartCardGetStaffEmploymentStatus();
        testSmartCardGetFirstName();
        testSmartCardGetLastName();
        testSmartCardGetDateOfBirth();

        //testing the SmartCardNumber class
        testSmartCardNumberGetInstance();
        testSmartCardNumberGetFirstNameInitial();
        testSmartCardNumberGetLastNameInitial();
        testSmartCardNumberGetSerialNumber();
        testSmartCardNumberGetYeaOfIssue();
        testSmartCardNumberGetStaffName();

    }
    public static void testSmartCardConstructor() {
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking if the smart card is not null
        Assertions.assertNotNull(smartCard);
    }

    public static void  testSmartCardGetSmartCardNumber(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking if the smart card number is not null
        Assertions.assertNotNull(smartCard.getSmartCardNumber());
    }

    public static void testSmartCardGetDateOfIssue(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking if the date of issue is not null
        Assertions.assertNotNull(smartCard.getDateOfIssue());
    }

    public static void  testSmartCardGetExpiryDate(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking if the expiry date is not null
        Assertions.assertNotNull(smartCard.getExpiryDate());
    }

    public static void testSmartCardGetStaffEmploymentStatus(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking the staff employment status, by using the getter method
        Assertions.assertEquals("researcher", smartCard.getGetStaffEmploymentStatus());
    }

    public static void testSmartCardGetFirstName(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking the first name, by using the getter method
        Assertions.assertEquals("a", smartCard.getFirstName());
    }

    public static void testSmartCardGetLastName(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking the last name, by using the getter method
        Assertions.assertEquals("b", smartCard.getLastName());
    }

    public static void testSmartCardGetDateOfBirth(){
        //set date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new smart card
        SmartCard smartCard = new SmartCard("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //checking the date of birth, by using the getter method
        Assertions.assertEquals("01/01/1999", smartCard.getDateOfBirth());
    }

    public static void testSmartCardNumberGetInstance(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking if the smart card number is not null
        Assertions.assertNotNull(smartCard);
    }

    public static void testSmartCardNumberGetFirstNameInitial(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking the first name initial, by using the getter method
        Assertions.assertEquals("A", smartCard.getFirstNameInitial());
    }

    public static void testSmartCardNumberGetLastNameInitial(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking the last name initial, by using the getter method
        Assertions.assertEquals("B", smartCard.getLastNameInitial());
    }

    public static void  testSmartCardNumberGetSerialNumber(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking if the serial number is not null
        Assertions.assertNotNull(smartCard.getSerialNumber());
    }
    public static void testSmartCardNumberGetYeaOfIssue(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking if the year of issue is not null
        Assertions.assertNotNull(smartCard.getYearOfIssue());
    }

    public static void testSmartCardNumberGetStaffName(){
        //creating a new smart card number
        SmartCardNumber smartCard = SmartCardNumber.getInstance("a", "b");
        //checking if the staff name is not null
        Assertions.assertNotNull(smartCard.getStaffName());
    }
}
