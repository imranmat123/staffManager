package uk.ac.ncl.CSC8014.Staff;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;
import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCard;
import uk.ac.ncl.CSC8014.Staff.StaffObject.StaffID;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//extends name (name carries firstname and last name)
abstract public class Persons implements Staff {
    private static final String LECTURER = "lecturer";
    private static final String RESEARCHER = "researcher";
    private static final Calendar DATE_OF_BIRTH = new GregorianCalendar();
    private static Calendar DateOfToday;
    protected static Map<StaffID, Staff> staffMember = new HashMap<StaffID, Staff>();
    protected Name staffName;
    //date of birth
    private Date dateOfBirth;
    //sets the getStaffEmploymentStatus of person Lecturer/Researcher
    private String staffRole;
    protected SmartCard smartCard;
    private StaffID staffId;
    private String employmentType;
    private static AtomicInteger randomDitget = new AtomicInteger(0);

    /**
     * The Persons class is an abstract class that is extended to the Lecturer and Researcher classes,
     * the constructor takes five parameters, forename, surname, birthDate, role and contractType.
     * These parameters are used to create a new instance of either a lecturer or researcher.
     * Addionally, the constructor checks that the contract type is either 'Permanent' or 'Fixed' and throws an exception if it is not.
     * Furthermore, the constructor also creates a new instance of the StaffID class
     * which is used to create a unique staff ID for each staff member.
     * the constructor also checks the age of the staff member and throws an exception if the stipulation is not met.
     *
     * @param forename
     * @param surname
     * @param birthDate
     * @param role
     * @param contractType
     */

    //constructor for persons class
    protected Persons(String forename, String surname, Date birthDate, String role, String contractType) {
        this.staffName = new Name(forename, surname);
        this.dateOfBirth = ageCheck(birthDate);
        this.staffRole = role;
        this.staffId = StaffID.getInstance(staffName.getLastName().charAt(0), randomDitget.incrementAndGet());

        if (!contractType.equals("permanent".toLowerCase()) && !contractType.equals("fixed".toLowerCase())) {
            this.employmentType = contractType;
        } else {
            throw new IllegalArgumentException("Invalid contract type, only 'Permanent' or 'Fixed' allowed");
        }
    }

    /**
     * The getInstance method is the most complicated method in the class and a singleton design pattern, this method is used to create a new instance of either a lecturer or researcher.
     * However, before a new instance is created, the method checks if the staff member already exists, if the staff member does exist, an exception is thrown.
     * the getInstance method is public and can be accessed from any class.
     * Additionally, the getInstance method also checks the role of the staff member and creates an instance of either based off the role.
     * The getInstance is designed to be an extension of the constructor.
     *
     * @return staffMember
     */

    //getInstance method, that returns a new instance of the class, depending on if the role is lecturer or researcher. This ensures that the staff ID is unique.
    public static Staff getInstance(String forename, String surname, Date birthDate, String role, String contractType) {
        Staff newStaff = null;

        //for each loop to check if the staff member already exists
        for (Staff existingStaff : getStaffMember().values()) {
            //if staff member is a lecturer
            if (existingStaff instanceof Lecturer) {
                //if the staff member already exists, throw an exception
                if (((Lecturer) existingStaff).getStaffName().getFirstName().equals(forename) &&
                        ((Lecturer) existingStaff).getStaffName().getLastName().equals(surname) &&
                        ((Lecturer) existingStaff).getDateOfBirth().equals(birthDate) &&
                        ((Lecturer) existingStaff).getStaffEmploymentStatus().equals(contractType)) {
                    throw new IllegalArgumentException("This is already a Staff member");
                }

                //if staff member is a researcher
            } else if (existingStaff instanceof Researcher) {
                //if the staff member already exists, throw an exception
                if (((Researcher) existingStaff).getStaffName().getFirstName().equals(forename) &&
                        ((Researcher) existingStaff).getStaffName().getLastName().equals(surname) &&
                        ((Researcher) existingStaff).getDateOfBirth().equals(birthDate) &&
                        ((Researcher) existingStaff).getStaffEmploymentStatus().equals(contractType)) {
                    // Staff member already exists in the system
                    throw new IllegalArgumentException("This is already a Staff member");
                }
            }
        }

        //if the staff member does not exist, create a new staff member
        if (newStaff == null) {

            //if role is lecturer, create a new lecturer
            if (role.equals(LECTURER)) {
                try {
                    newStaff = new Lecturer(forename, surname, birthDate, contractType);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                //if role is researcher, create a new researcher
            } else if (role.equals(RESEARCHER)) {
                try {
                    newStaff = new Researcher(forename, surname, birthDate, contractType);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException("Please choose a correct role");
            }
            //add the new staff member to the staffMember using the staffId as the key and the newStaff as the value.

            if (!staffMember.containsKey(newStaff.getStaffId())){
                staffMember.put(newStaff.getStaffId(), newStaff);
            }
            return newStaff;
            //if the loop didn't find a staff member, but the staff member already exists, throw an exception
        } else {
            throw new IllegalArgumentException("This is already a Staff member");
        }
    }

    /**
     * The ageCheck method is a private method that is used to check the age of the staff member, this is done by
     * taking the staff members date of birth and comparing it to the current date.
     * If the staff member is too young (younger than 22) or too old (older than 67), an exception is thrown.
     * @param dateOfBirth
     * @return
     */

    //method to check the age of the person, if they are too young or too old, throw an exception and print a message.
    private Date ageCheck(Date dateOfBirth){
        DATE_OF_BIRTH.setTime(dateOfBirth);
        int day = DATE_OF_BIRTH.get(Calendar.DAY_OF_MONTH);
        int month = DATE_OF_BIRTH.get(Calendar.MONTH);
        int year = DATE_OF_BIRTH.get(Calendar.YEAR);
        DATE_OF_BIRTH.set(day, month, year);

        DATE_OF_BIRTH.setTime(dateOfBirth);

        DateOfToday = Calendar.getInstance();
        int age = DateOfToday.get(Calendar.YEAR) - year;

        if (DateOfToday.get(Calendar.MONTH) < month) {
            age--;
        } else if (DateOfToday.get(Calendar.MONTH) == month && DateOfToday.get(Calendar.DAY_OF_MONTH) < day) {
            age--;
        }
        //if the person is too young or too old, throw an exception
        if (age < 22) {
            System.out.println("This person is too young to be a member of staff, staff must be at least 22");
            throw new IllegalArgumentException("Must be older than 22");

        } else if (age > 67) {
            System.out.println("This person is too old to be a member of staff. Staff member must be younger than 68");
            throw new IllegalArgumentException("Must be younger than 68");
        }
        return dateOfBirth;
    }


    //getters

    public static Map<StaffID, Staff> getStaffMember() { return staffMember;}

    @Override
    public String toString() {
        return "lecturer: " + "modules: "  + ", smartCard:" + "\n";
    }

    @Override
    public StaffID getStaffId() {
        return staffId;
    }

    @Override
    public SmartCard getSmartCard() {
        return smartCard;
    }

    @Override
    public String getStaffType() {
        return staffRole;
    }

    @Override
    public String getStaffEmploymentStatus() {
        return employmentType;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    public Name getStaffName() {
        return staffName;
    }


}
