package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Main.StaffManager;
import uk.ac.ncl.CSC8014.Staff.Lecturer;
import uk.ac.ncl.CSC8014.Staff.Persons;
import uk.ac.ncl.CSC8014.Staff.Staff;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Module;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LecturerTest {

    private static StaffManager staffManager = StaffManager.getInstance();
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    public static void main(String[] args) throws FileNotFoundException {

        testLecturerGetListOfModules();
        testLecturerAddModule();
        testLecturerIsTeachingCreditsEnough();
        testLecturerIsTeachingCreditsEnough();
        testLecturerAddSetOfModules();
        testLecturerModuleLimit();
        testLecturerGetModuleCredits();
    }

    //this is the test for the getModuleCredits method, which returns the total number of credits the lecturer is teaching,
    //by adding the credits of each module in the lecturer's list of modules.

    public static void testLecturerGetListOfModules() {
        Persons.getStaffMember().clear();
        //setting a date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) one;
        //creating a new module
        Module module = new Module("CSC8022", "Human Computer Interaction", 2, 10);
        //adding the module to said lecturer
        lecturer.addModule(module);

        //creating a new set of modules
        Set<Module> testModules = new HashSet<>();
        //adding the module to the set
        testModules.add(module);
        //asserting that tests if the testModules and the lecturer's list of modules are equal
        Assertions.assertEquals(testModules, lecturer.getListOfModules());
    }

    public static void testLecturerAddModule() {
        Persons.getStaffMember().clear();
        //setting a date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff one = staffManager.employStaff("e", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) one;
        //creating a new module
        Module module = new Module("CSC8022", "Human Computer Interaction", 2, 10);
        //adding the module to said lecturer
        lecturer.addModule(module);
        Set<Module> testModules = new HashSet<>();
        testModules.add(module);
        //asserting that tests if the testModules and the lecturer's list of modules are equal
        Assertions.assertEquals(testModules, lecturer.getListOfModules());
    }

    public static void testLecturerIsTeachingCreditsEnough() {
        Persons.getStaffMember().clear();
        //setting a date of birth
        DATE_OF_BIRTH.set(1995, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff three = staffManager.employStaff("noname", "lname", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        Staff two = staffManager.employStaff("g", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) three;
        //creating a new module
        Module module = new Module("CSC8022", "Human Computer Interaction", 2, 40);
        //adding the module to said lecturer
        ((Lecturer) three).addModule(module);
        //asserting that the lecturer is teaching the correct amount of credits. In this case 40
        Assertions.assertEquals(true, lecturer.isTeachingCreditsEnough());
        //creating a new module, this time with 20 credits
        Module module2 = new Module("CSC8022", "Human Computer Interaction", 2, 20);
        Lecturer lecturer2 = (Lecturer) two;
        //adding the module to said lecturer
        lecturer2.addModule(module2);
        //asserting that the lecturer is teaching the correct amount of credits. In this case 20
        Assertions.assertEquals(false, lecturer2.isTeachingCreditsEnough());


    }

    public static void testLecturerAddSetOfModules() throws FileNotFoundException {
        Persons.getStaffMember().clear();
        //creating a new scanner to read in the modules
        Scanner moduleScanner = new Scanner(new File("src/uk/ac/ncl/CSC8014/Staff/StaffObject/modules.txt"));
        //creating a test hashset to store the modules
        Set<Module> testModules = new HashSet<>();

        try {

            while (moduleScanner.hasNextLine()) {

                String Values = moduleScanner.nextLine();
                Module modules = Module.valueOf(Values);
                testModules.add(modules);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in modules");
        }

        //creating a new scanner to read in the modules that are less than 40 credits
        Scanner moduleScanner3 = new Scanner(new File("src/uk/ac/ncl/CSC8014/Staff/StaffObject/modulesTest.txt"));
        //creating a test hashset to store the modules
        Set<Module> testModulesWithLessCredits = new HashSet<>();

        try {

            while (moduleScanner3.hasNextLine()) {

                String Values = moduleScanner3.nextLine();
                Module modules = Module.valueOf(Values);
                testModulesWithLessCredits.add(modules);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in modules");
        }

        //setting a date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        Staff two = staffManager.employStaff("g", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) one;
        Lecturer lecturer2 = (Lecturer) two;

        //adding the set of modules to the lecturer
        try{
            lecturer.addSetOfModules(testModules);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        lecturer2.addSetOfModules(testModulesWithLessCredits);
        //asserting that the lecturer has the correct modules, by comparing the test hashset to the lecturer's hashset.
        Assertions.assertEquals(testModulesWithLessCredits, lecturer2.getListOfModules());


    }

    public static void testLecturerModuleLimit() {
        Persons.getStaffMember().clear();
        //setting a date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) one;
        //creating a new module
        Module module = new Module("CSC8022", "Human Computer Interaction", 2, 40);
        //adding the module to said lecturer
        lecturer.addModule(module);
        try {
            //adding the module again to create an exception
            lecturer.addModule(module);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
    }

    public static void testLecturerGetModuleCredits() {
        Persons.getStaffMember().clear();
        //setting a date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //employing a staff member using the employStaff method
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //casting the staff member to a lecturer
        Lecturer lecturer = (Lecturer) one;
        //creating a new module
        Module module = new Module("CSC8022", "Human Computer Interaction", 2, 40);
        //adding the module to said lecturer
        ((Lecturer) one).addModule(module);
        //asserting that the lecturer has the correct amount of credits
        Assertions.assertEquals(40, lecturer.getModuleCredits());
    }


}
