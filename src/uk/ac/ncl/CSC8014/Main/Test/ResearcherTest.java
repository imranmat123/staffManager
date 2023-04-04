package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Main.StaffManager;
import uk.ac.ncl.CSC8014.Staff.Persons;
import uk.ac.ncl.CSC8014.Staff.Researcher;
import uk.ac.ncl.CSC8014.Staff.Staff;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ResearcherTest {
    private static StaffManager staffManager = StaffManager.getInstance();
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    public static void main(String[] args) throws FileNotFoundException {
        testResearcherAddSetOfStudents();
        testResearcherAddSingleStudent();
        testResearcherStudentLimit();
        testResearcherSupervisedEnough();
        testResearcherGetTotalNumberOfStudents();
        testResearcherGetListOfStudents();

    }

    public static void testResearcherAddSetOfStudents() throws FileNotFoundException {
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



        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff onetwo = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        Staff onetwothree = staffManager.employStaff("t", "h", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) onetwo;
        Researcher researcher2 = (Researcher) onetwothree;
        try{
            //adding testset to the researcher
            researcher.addSetOfStudents(testStudents);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        //adding testStudents9students to the researcher
        researcher2.addSetOfStudents(testStudents9students);

        //asserting that the researcher has the correct set of students, by comparing the testStudents9students and the actual set
        Assertions.assertEquals(testStudents9students, researcher2.getListOfStudents());
    }

    public static void testResearcherAddSingleStudent() {
        Persons.getStaffMember().clear();
        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) one;
        //adding a single student to the researcher
        ((Researcher) one).addSingleStudent("a", "b");
        //creating a new student object
        Name student = new Name("a", "b");
        //asserting that the researcher has the correct student by checking if the student is in the list of students
        Assertions.assertNotNull(researcher.getListOfStudents().contains(student));
    }

    public static void testResearcherStudentLimit() throws FileNotFoundException {
        Persons.getStaffMember().clear();
        //using a scanner to read in the names from a text file
        Scanner studentScanner = new Scanner(new File("src/uk/ac/ncl/CSC8014/Staff/StaffObject/students.txt"));
        //creating a testset of names
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
        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) one;
        try {
            //adding the testset to the researcher again, which should throw an exception too many students
            researcher.addSetOfStudents(testStudents);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

    }

    public static void testResearcherSupervisedEnough() {
        Persons.getStaffMember().clear();
        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) one;
        //adding a single student to the researcher
        researcher.addSingleStudent("a", "b");
        //asserting that the researcher has supervised enough students, this should be true.
        Assertions.assertEquals(true, researcher.supervisedEnough());

        //creating a new researcher
        Staff two = staffManager.employStaff("number", "two", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");

        //casting the researcher to the staff member
        Researcher researcher123 = (Researcher) two;

        //adding 10 students to the researcher, this will make 11 in total and should return false, in addion to throwing an exception,
        //is it due to the student limit trowing an exception.
        try {
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
            researcher123.addSingleStudent("a", "b");
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
            Assertions.assertEquals(false, researcher123.supervisedEnough());
        }




    }

    public static void testResearcherGetTotalNumberOfStudents() {
        Persons.getStaffMember().clear();
        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) one;
        //adding a single student to the researcher
        researcher.addSingleStudent("a", "b");
        //asserting that the researcher has the correct number of students
        Assertions.assertEquals(1, researcher.getTotalNumberOfStudents());
    }

    public static void testResearcherGetListOfStudents() throws FileNotFoundException {
        Persons.getStaffMember().clear();

        //creating a  scanner to read in a list of students that is less than 10
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

        //setting the date of birth
        DATE_OF_BIRTH.set(1999, Calendar.JANUARY, 1);
        //creating a new researcher
        Staff one = staffManager.employStaff("a", "b", DATE_OF_BIRTH.getTime(), "researcher", "Permanent");
        //casting the researcher to the staff member
        Researcher researcher = (Researcher) one;

        //adding the testset to the researcher
        researcher.addSetOfStudents(testStudents9students);
        //asserting that the researcher has the correct set of students, by comparing the testset and the actual set
        Assertions.assertEquals(testStudents9students, researcher.getListOfStudents());
    }
}
