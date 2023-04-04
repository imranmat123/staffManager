package uk.ac.ncl.CSC8014.Staff.StaffObject;
import uk.ac.ncl.CSC8014.Assertions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

//extends person, carries: firstName, lastName, dateOfBirth and role
public final class SmartCard{
    //holds smartcard number
    private SmartCardNumber smartCardNumber;
    //issue date of smartcard
    private String dateOfIssue;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfExpiry;
    private String getStaffEmploymentStatus;
    private String employmentType;
    private Calendar currentDate;
    private static final String FIXED = "fixed";
    private static final String PERMANENT = "permanent";
    private static final SimpleDateFormat DAY_MONTH_YEAR = new SimpleDateFormat("dd/MM/yyyy");
    private static final int TWO_YEARS = 2;
    private static final int TEN_YEARS = 10;


    /**
     * The SmartCard constructor, takes four parameters to create a new SmartCard object.
     * Additionally, the constructor will also assign some of the parameters to an instance variables in order to
     * create a new SmartCardNumber object.
     * The constructor will also call the setExpiryDate method to set the expiry date of the smart card.
     * @param forename
     * @param surname
     * @param birthDate
     * @param role
     * @param contractType
     */
    //smart card constructor
    public SmartCard (String forename, String surname, Date birthDate, String role, String contractType) {
        this.firstName = forename;
        this.lastName = surname;
        this.getStaffEmploymentStatus = role;
        this.employmentType = contractType;
        this.smartCardNumber = SmartCardNumber.getInstance(forename,surname);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        this.dateOfIssue = DAY_MONTH_YEAR.format(calendar.getTime());
        setExpiryDate();
        this.dateOfBirth = DAY_MONTH_YEAR.format(birthDate);
    }

    /**
     * The setExpiryDate method will set the expiry date of the smart card.
     * If the employment type is fixed, the expiry date is 2 years from the date of issue.
     * If the employment type is permanent, the expiry date is 10 years from the date of issue.
     */
    //sets the expiry date of the smart card.
    // If the employment type is fixed, the expiry date is 2 years from the date of issue.
    // If the employment type is permanent, the expiry date is 10 years from the date of issue.
    private void setExpiryDate(){
        currentDate = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        currentDate.setTime(currentDate.getTime());
        int year = Calendar.YEAR;
        if (employmentType.equals(FIXED)){
            currentDate.add(year, TWO_YEARS);
        } else if (employmentType.equals(PERMANENT)){
            currentDate.add(year, TEN_YEARS);
        }
        this.dateOfExpiry = DAY_MONTH_YEAR.format(currentDate.getTime());

        Assertions.assertEquals(dateOfExpiry, DAY_MONTH_YEAR.format(currentDate.getTime()));
    }

    @Override
    public String toString() {
        return "Smart Card Number: " + smartCardNumber + ", Date Of Birth: " +getDateOfBirth() +", Date Of Issue: " + dateOfIssue
                + ", Date Of Expiry: " + dateOfExpiry;
    }

    //Getters and Setters

    public SmartCardNumber getSmartCardNumber() {
        Assertions.assertNotNull(smartCardNumber);
        return smartCardNumber;
    }

    public String getDateOfIssue() {
        Assertions.assertNotNull(dateOfIssue);
        return dateOfIssue;
    }

    public String getFirstName() {
        Assertions.assertNotNull(firstName);
        return firstName;
    }

    public String getLastName() {
        Assertions.assertNotNull(lastName);
        return lastName;
    }
    public String getDateOfBirth() {
        Assertions.assertNotNull(dateOfBirth);
        return dateOfBirth;
    }

    public String getGetStaffEmploymentStatus() {
        Assertions.assertNotNull(getStaffEmploymentStatus);
        return getStaffEmploymentStatus;
    }
    public String getExpiryDate() {
        Assertions.assertNotNull(dateOfExpiry);
        return dateOfExpiry;}

}
