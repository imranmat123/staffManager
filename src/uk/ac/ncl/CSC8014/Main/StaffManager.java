package uk.ac.ncl.CSC8014.Main;
import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Module;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;
import uk.ac.ncl.CSC8014.Staff.Lecturer;
import uk.ac.ncl.CSC8014.Staff.Persons;
import uk.ac.ncl.CSC8014.Staff.Researcher;
import uk.ac.ncl.CSC8014.Staff.Staff;
import uk.ac.ncl.CSC8014.Staff.StaffObject.StaffID;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StaffManager {
    private Set<Module> listOfReadInModules = new HashSet<>();
    private Set<Name> listOfReadInStudents = new HashSet<>();
    private static StaffManager primaryInstance;



    /**
     * The StaffManager class is private and can only be accessed by the getInstance method.
     * This is to ensure that only one instance of the StaffManager is created.
     */
    //craete a private constructor to use a singleton pattern
    private StaffManager(){
        //empty constructor, to prevent the creation of multiple instances of the StaffManager.
    }
    /**
     * The getInstance method is used to return the instance of the StaffManager.
     * If there is no instance of the StaffManager, then one is created.
     * is a singleton class, which means that there can only be one instance of the class.
     * @return the instance of the StaffManager
     */
    public static StaffManager getInstance(){
        //return the instance of the StaffManager
        //if there is no instance of the StaffManager, create one
        if(primaryInstance == null){
            return primaryInstance = new StaffManager();
        } else {
            System.out.println("There already exists an instance of the StaffManager");
            Assertions.assertNotNull(primaryInstance);
            return primaryInstance;
        }
    }

    /**
     * The readInModules method is used to read in the modules from the file.
     * The method uses a scanner, reading in the file line by line.
     * The method then uses the valueOf method to convert the string into a Module object and add said objects to a set.
     * if the set already contains the object, then it is not added to the set.
     * Additionally, if the file is not found, then an error message is printed.
     * @param filePath the path of the file to be read in
     * @return the set of modules that have been read in
     */
    //method to read in the modules from the file
    public Set<Module> readInModules(String filePath){
        try {
            Scanner moduleScanner = new Scanner(new File(filePath));

            while (moduleScanner.hasNextLine()) {

                String Values = moduleScanner.nextLine();
                Module modules = Module.valueOf(Values);
                listOfReadInModules.add(modules);
                Assertions.assertEquals(true, listOfReadInModules.contains(modules));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file not found.");
        }
        return listOfReadInModules;
    }

    /**
     * The readInStudents method is used to read in the students from the file.
     * The method uses a scanner, reading in the file line by line.
     * The method then uses the valueOf method to convert the string into a Name object and add said objects to a set.
     * Additionally, if the file is not found, then an error message is printed.
     * @param path the path of the file to be read in
     * @return the set of students that have been read in
     */
    //method to read in the students from the file
    public Set<Name> readInStudents (String path){

        try {
            Scanner studentScanner = new Scanner(new File(path));
            while (studentScanner.hasNextLine()) {
                String Values = studentScanner.nextLine();
                Name students = Name.valueOf(Values);
                listOfReadInStudents.add(students);
                Assertions.assertEquals(true,listOfReadInStudents.contains(students));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file not found.");
        }
        return listOfReadInStudents;
    }
    /**
     * The employStaff method is used to employ a staff member, it takes four parameters.
     * The method uses the getInstance method from the Persons class to create a new staff member.
     * this is done to ensure that only one instance of the staff member is created.
     * The method then returns the new staff member.
     * @param firstName the first name of the staff member
     * @param lastName the last name of the staff member
     * @param dateOfBirth the date of birth of the staff member
     * @param staffType the staff type of the staff member
     * @param employmentStatus the employment status of the staff member
     * @return the new staff member
     */
    //method to employ staff, using the abstract Persons class
    public Staff employStaff(String firstName, String lastName, Date dateOfBirth, String staffType, String employmentStatus) {
        Staff newStaff = Persons.getInstance(firstName, lastName, dateOfBirth, staffType, employmentStatus);
        return newStaff;
    }

    /**
     * The noOfStaff method is used to return the number of staff members, by role.
     * The method takes a string as a parameter(staffType).
     * The method then iterates through the set of staff members, checking if the staffType is equal to another staffType.
     * If the staffType is equal to the another staffType, then the number is incremented.
     * @param staffType the staff role of the staff member
     * @return the number of staff members, specified by the staff role
     */
    //method to return the number of staff members, specified by the staff role
    public int noOfStaff(String staffType){
        int NumberOfStaffRole = 0;
        for (Staff employee : Persons.getStaffMember().values()) {

            if (employee.getStaffType().equals(staffType)) {
                NumberOfStaffRole = NumberOfStaffRole + 1;
            }
        }
        return NumberOfStaffRole;
    }
    /**
     * The addData method is used to add data to the staff members by using their StaffID.
     * The method takes three parameters, a staffID, a set of modules and a set of students.
     * The method then iterates through the set of staff members, checking if the staffID is equal to another staffID.
     * If the staffID is equal to the another staffID, then the staff member is assigned to the employee variable.
     * The method then checks if the employee is an instance of a researcher or lecturer.
     * If the employee is an instance of a researcher, then the students are added to the researcher.
     * else if the employee is an instance of a lecturer, then the modules are added to the lecturer.
     * However, due to the stipulations on both the researcher and lecturer classes, the classes will only accept the correct data.
     * if the sets are too big, then the lecturer and researcher will not accept the data.
     * thus, it is important to ensure that the sets are not too big.(upto 40 credits for lecturer and upto 10 students for researcher
     * @param id the staffID of the staff member
     * @param modules the set of modules that the staff member is teaching
     * @param students the set of students that the staff member is supervising
     * @return true if the data has been added to the staff member
     */
    //add data to the staff members,using the staffID, a set of modules and a set of Name objects
    public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
        Staff employee = null;

        for (Staff memberOfStaff : Persons.getStaffMember().values()) {
            if (memberOfStaff.getStaffId().equals(id)) {
                employee = memberOfStaff;
            }
        }
        if (employee != null) {

            //if the staff member is a researcher, add the students to the researcher using instance of.
            if (employee instanceof Researcher) {

                Set<Name> referencedStudents = new HashSet<>();
                for (Name student : students) {
                    if (listOfReadInStudents.contains(student)) {
                        referencedStudents.addAll(students);
                    }else{
                        throw new IllegalArgumentException("Student not found in original set");
                    }
                }
                Researcher researcher = (Researcher) employee;
                researcher.addSetOfStudents(referencedStudents);
                return true;
            }

            //if the staff member is a lecturer, add the modules to the lecturer using instance of.
            if (employee instanceof Lecturer) {
                if (modules != null && !modules.isEmpty()) {
                    Set<Module> referencedModules = new HashSet<>();
                    for (Module module : modules) {
                        if (listOfReadInModules.contains(module)) {
                            referencedModules.addAll(modules);
                        } else{
                            throw new IllegalArgumentException("Modules not found in original set");
                        }
                        Lecturer lecturer = (Lecturer) employee;
                        lecturer.addSetOfModules(referencedModules);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * The getAllStaff method is used to return a collection of all staff members.
     * The method then iterates through Persons.getStaffMember(), adds all the staff members to the numberOfStaff set.
     * The method then returns numberOfStaff.
     * @return a collection of all staff members
     */
    //method to return a all staff members.
    public Collection<Staff> getAllStaff() {
        Set<Staff> numberOfStaff = new HashSet<>();
        numberOfStaff.addAll(Persons.getStaffMember().values());
        return numberOfStaff;
    }

    //method to add a staff member to the staffMember map


    /**
     * The terminateStaff method is used to remove a staff, by using their StaffID.
     * The method takes a staffID as a parameter.
     * The method then iterates through the set of staff members, checking if the staffID is equal to another staffID.
     * If the staffID is equal to the another staffID, then the staff member is assigned to the employee variable.
     * The method then removes the employee from the staffMember map.
     * @param id the staffID of the staff member
     */
    //method to terminate a staff member, using the staffID.
    public void terminateStaff(StaffID id){
        Staff employee = null;
        for (Staff memberOfStaff : Persons.getStaffMember().values()) {
            if (memberOfStaff.getStaffId().equals(id)) {
                employee = memberOfStaff;
            }
        }
        Persons.getStaffMember().remove(employee.getStaffId(),employee);
        Assertions.assertEquals(false,Persons.getStaffMember().containsValue(id));
    }

}
