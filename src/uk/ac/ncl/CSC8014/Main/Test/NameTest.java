package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;

public class NameTest {
    public static void main(String[] args) {
        testNameConstructor();
        testNameValueOf();
        testNameEquals();
        testNameGetFirstName();
        testNameGetLastName();
        testNameGetFullName();
    }

    public static void testNameConstructor() {
        //create a new name object
        Name name = new Name("a", "b");
        //check if the object has been created
        Assertions.assertEquals("a", name.getFirstName());
        Assertions.assertEquals("b", name.getLastName());
        Assertions.assertEquals("a b", name.getFullName());
    }

    public static void testNameValueOf() {
        //create a new name object
        Name name = new Name("a", "b");
        //create a string
        String student = "a b";
        //use the valueOf method to create a new name object from the string
        Name testname = Name.valueOf(student);
        //check if the two objects are equal
        Assertions.assertEquals(name, testname);
    }

    public static void testNameEquals() {
        //create two name objects
        Name name = new Name("a", "b");
        //create a string
        String student = "a b";
        //use the valueOf method to create a new name object from the string
        Name testname = Name.valueOf(student);
        //check if the two objects are equal
        Assertions.assertEquals(true, name.equals(testname));
    }

    public static void testNameGetFirstName() {
        //create a new name object
        Name name = new Name("a", "b");
        //use the getter method to get the first name, and check that it is correct
        Assertions.assertEquals("a", name.getFirstName());

        try {
            //create a new name object with an invalid first name
            Name name1 = new Name("a1", "b");
            //check that an exception is thrown
        }  catch(Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        //test if alphanumerical characters only
       // Name name1 = new Name("a", "b");

    }

    public static void testNameGetLastName() {
        //create a new name object
        Name name = new Name("a", "b");
        //use the getter method to get the last name, and check that it is correct
        Assertions.assertEquals("b", name.getLastName());

        try {
            //create a new name object with an invalid first name
            Name name1 = new Name("a", "b1");
            //check that an exception is thrown
        }  catch(Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
    }

    public static void testNameGetFullName() {
        //create a new name object
        Name name = new Name("a", "b");
        //use the getter method to get the full name, and check that it is correct
        Assertions.assertEquals("a b", name.getFullName());


        try {
            //create a new name object with an invalid first name
            Name name1 = new Name("a1", "b1");
            //check that an exception is thrown
        }  catch(Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

    }

}
