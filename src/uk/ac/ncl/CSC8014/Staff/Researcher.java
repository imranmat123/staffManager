package uk.ac.ncl.CSC8014.Staff;
import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;
import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCard;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public final class Researcher extends Persons {

    private Set<Name> listOfStudents = new HashSet<Name>();

    private static final int STUDENT_LIMIT = 9;

    /**
     * constructor for the Researcher class, this takes four parameters, forename, surname, birthdate and contract type.
     * the constructor then calls the super class, which is the persons class, and passes the parameters to the super class.
     * this constructor is protected, so that it can only be accessed by certain classes.
     * this constructor also creates a new smart card for the lecturer.
     * @param forename
     * @param surname
     * @param birthDate
     * @param contractType
     * @throws ParseException
     */

    //constructor for the researcher class
    protected Researcher (String forename, String surname, Date birthDate, String contractType) throws ParseException {
        //use super to call the constructor of the parent class
        //the super constructor is called first
        super(forename, surname, birthDate, "Researcher".toLowerCase(),contractType);
        this.smartCard = new SmartCard(staffName.getFirstName(),staffName.getLastName(), birthDate, "Researcher".toLowerCase(), contractType);
        this.listOfStudents = new HashSet<Name>();
    }

    /**
     * the addSetOfStudents method will take a set of students and add them to a researcher, at the same time, this method will also,
     * check how many students the researcher is supervising. if the researcher is teaching more than 10 students,
     * then the researcher will not be able to add any more students, due to supervising too many students.
     * @param student
     */
    //add students to the researcher, whilst checking if the researcher is supervising too many students.
    public void addSetOfStudents(Set<Name> student) {
        studentLimit(student);
    }

    /**
     * the addSingleStudent method will take a single student and add them to a researcher, at the same time, this method will also,
     * check how many students the researcher is supervising. if the researcher is teaching more than 10 students,
     * then the researcher will not be able to add any more students, due to supervising too many students.
     * @param forename
     * @param surname
     */
    //add a single student to the researcher, whilst checking if the researcher is supervising too many students.
    public void addSingleStudent(String forename, String surname) {
        Name student = Name.valueOf(forename + " " + surname);
        studentLimit(student);
    }
    /**
     * the studentLimit method will check if the researcher is supervising too many students and will throw an exception,
     * if the researcher is supervising more than 10 students, then the researcher will not be able to add any more students,
     * due to stipulations from the university.
     * if the researcher is supervising less than 10 students, then the researcher will be able to add more students.
     * Additionally, this method has been overloaded, so that it can take a set of students, or a single student.
     * @param student
     */
    //checks if the researcher is supervising too many students.
    public void studentLimit(Set<Name> student){

        for(int i = 0; i < student.size(); i++){
            //if the total number of students is greater than the student limit, throw an exception
            Set<Name> referencedStudents = new HashSet<>();
            referencedStudents.addAll(student);
            if (referencedStudents.size() > STUDENT_LIMIT) {
                throw new IllegalArgumentException("Researcher is supervising too many students.");
            } else {
                listOfStudents.addAll(student);
            }
        }
    }
    /**
     * the studentLimit method will check if the researcher is supervising too many students and will throw an exception,
     * if the researcher is supervising more than 10 students, then the researcher will not be able to add any more students,
     * due to stipulations from the university.
     * if the researcher is supervising less than 10 students, then the researcher will be able to add more students.
     * Additionally, this method has been overloaded, so that it can take a set of students, or a single student.
     * @param student
     */

    //checks if the researcher is supervising too many students.
    public void studentLimit(Name student){

        if (listOfStudents.size() > STUDENT_LIMIT) {
            throw new IllegalArgumentException("Researcher is supervising too many students.");
        } else {
            listOfStudents.add(student);
        }
    }

    /**
     * the supervisedEnough method will check if the researcher is supervising too many students and will return false,
     * this is done by checking the size of the set of students and comparing it to the student limit.
     * @return
     */
    //get the students the researcher is supervising, if the researcher is supervising more than 10 students, it will return false.
    public boolean supervisedEnough() {
        if (listOfStudents.size() > STUDENT_LIMIT) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "lecturer: " + getStaffName().getFirstName() + " " + getStaffName().getLastName() + " " +"modules: " + "smartCard: " + getSmartCard();
    }


    //getters

    public Set<Name> getListOfStudents() {
        return listOfStudents;
    }

    public int getTotalNumberOfStudents() {
        return listOfStudents.size();
    }


}
