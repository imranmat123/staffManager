package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Main.StaffManager;
import uk.ac.ncl.CSC8014.Staff.Lecturer;
import uk.ac.ncl.CSC8014.Staff.Persons;
import uk.ac.ncl.CSC8014.Staff.Researcher;
import uk.ac.ncl.CSC8014.Staff.Staff;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Module;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StaffManagerTest {
    private static StaffManager staffManager = StaffManager.getInstance();
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    public static void main(String[] args) throws FileNotFoundException {
        testStaffManagerReadInModules();
        testStaffManagerReadInStudents();
        testStaffManagerGetInstances();
        testStaffManagerEmployStaff();
        testStaffManagerNoOfStaff();
        testStaffManagerTerminateStaff();
        testStaffManagerAddData();
        testStaffManagerGetAllStaff();
    }

    public static void testStaffManagerReadInModules() {
        //testing the readInModules method
        staffManager.readInModules("src/uk/ac/ncl/CSC8014/Staff/StaffObject/modules.txt");
    }

    public static void testStaffManagerReadInStudents() {
        //testing the readInStudents method
        staffManager.readInStudents("src/uk/ac/ncl/CSC8014/Staff/StaffObject/students.txt");
    }
    public static void testStaffManagerGetInstances() {
        //testing to make sure that the getInstance method works
        Assertions.assertNotNull(staffManager);
    }

    public static void testStaffManagerAddData() throws FileNotFoundException {
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

        //using a scanner to read in the names from a text file
        Scanner studentScanner = new Scanner(new File("src/uk/ac/ncl/CSC8014/Staff/StaffObject/students.txt"));
        //creating a new set of names
        Set<Name> testStudents = new HashSet<>();

        try {

            while (studentScanner.hasNextLine()) {

                String Values = studentScanner.nextLine();
                Name students = Name.valueOf(Values);
                testStudents.add(students);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in Names");
        }


        //creating a second scanner to read in a list of students that is less than 10
        Scanner studentScanner2 = new Scanner(new File("src/uk/ac/ncl/CSC8014/Staff/StaffObject/studentTest.txt"));
        //creating a new set of names
        Set<Name> testStudents9students = new HashSet<>();

        try {

            while (studentScanner2.hasNextLine()) {

                String Values = studentScanner2.nextLine();
                Name students = Name.valueOf(Values);
                testStudents9students.add(students);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading in Names");
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






        //testing the addData method
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        Staff two = staffManager.employStaff("c", "d", DATE_OF_BIRTH.getTime(), "lecturer", "Fixed");

        Researcher researcher = (Researcher) one;
        Lecturer lecturer = (Lecturer) two;


        //testing the addData, however, both researcher and lecturer should not be able to add data, due to the fact
        //of their stipulations. Researcher can only supervise upto 10 students and lecturer can only teach upto 40 credits.
        try {
            staffManager.addData(researcher.getStaffId(), testModules, testStudents);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
        try {
            staffManager.addData(lecturer.getStaffId(), testModules, testStudents);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        Staff three = staffManager.employStaff("e", "f", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        Staff four = staffManager.employStaff("g", "h", DATE_OF_BIRTH.getTime(), "lecturer", "Fixed");

        Researcher researcher1 = (Researcher) three;
        Lecturer lecturer1 = (Lecturer) four;

        //test addData to see if the lecturer and researcher return true when they are able to add data
        Assertions.assertEquals(true,staffManager.addData(researcher1.getStaffId(), testModulesWithLessCredits, testStudents9students));
        Assertions.assertEquals(true,staffManager.addData(lecturer1.getStaffId(), testModulesWithLessCredits, testStudents9students));


    }

    public static void testStaffManagerEmployStaff() {

        //testing the constructor of the employStaff method
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        Staff one = staffManager.employStaff("c", "b", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        Assertions.assertNotNull(one);

        //ensuring that a lecturer cannot be employed as a researcher and vice versa
        staffManager.employStaff("Fname", "Lname", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        try {
            staffManager.employStaff("Fname", "Lname", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        //testing age restrictions
        try {
            //mkaing the person too young
            DATE_OF_BIRTH.set(2005, Calendar.JANUARY, 1);
            staffManager.employStaff("Fname", "Lname", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        try {
            //making the person too old
            DATE_OF_BIRTH.set(1900, Calendar.JANUARY, 1);
            staffManager.employStaff("Fname", "Lname", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
    }

    public static void testStaffManagerNoOfStaff() {
        //testing the noOfStaff method
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //adding two staff members to the list
        staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        staffManager.employStaff("c", "d", DATE_OF_BIRTH.getTime(), "lecturer", "Fixed");
        int researcher = 0;
        //looping through the list and adding to the researcher variable.
        for(Staff abc :Persons.getStaffMember().values()){
            if(abc.getStaffType().equals("researcher")){
                researcher++;
            }
        }
        int lecturer = 0;
        //looping through the list and adding to the lecturer variable.
        for(Staff abc :Persons.getStaffMember().values()){
            if(abc.getStaffType().equals("lecturer")){
                lecturer++;
            }
        }

        Assertions.assertEquals(researcher, staffManager.noOfStaff("researcher"));
        Assertions.assertEquals(lecturer, staffManager.noOfStaff("lecturer"));

    }

    public static void testStaffManagerTerminateStaff() {
        //testing if the terminateStaff method works.
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);

        //getting the size of the staff member list before the termination of staff
        int size = Persons.getStaffMember().size();
        //adding two staff members to the list
        Staff one = staffManager.employStaff("p", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        Staff two = staffManager.employStaff("t", "d", DATE_OF_BIRTH.getTime(), "lecturer", "Permanent");
        //terminating said staff members
        staffManager.terminateStaff(one.getStaffId());
        staffManager.terminateStaff(two.getStaffId());

        Assertions.assertEquals(size, Persons.getStaffMember().size());
    }

    public static void testStaffManagerGetAllStaff(){
        Persons.getStaffMember().clear();
        //testing the getAllStaff method
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //adding two staff members to the list
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        Staff two = staffManager.employStaff("c", "d", DATE_OF_BIRTH.getTime(), "lecturer", "Fixed");
        //getting the size of the staff member list before the termination of staff
        int size = Persons.getStaffMember().size();
        //getting the size of the list returned by the getAllStaff method
        int size2 = staffManager.getAllStaff().size();
        Assertions.assertEquals(size, size2);
    }

}
