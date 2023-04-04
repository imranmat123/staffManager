package uk.ac.ncl.CSC8014.Staff.StaffObject;
import uk.ac.ncl.CSC8014.Assertions;

public final class Name {
    private String firstName;
    private String lastName;
    private String fullName;

    private static final int PRIME_MULTIPLIER = 37;

    /**
     * Constructor for the Name class, which takes in a first name and and a second name. The constructor than checks if the
     * first name and last name are valid, if they aren't valid the constructor throws an illegal argument exception.
     * else, the constructor sets the first name, last name and full name.
     * @param forename
     * @param surname
     */
    //constructor for the name class.
    public Name(String forename, String surname) {

        if (forename != null && forename.matches("[a-zA-Z]+") && forename != null && surname.matches("[a-zA-Z]+")) {
            this.firstName = forename;
            this.lastName = surname;
            this.fullName = forename + " " + surname;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }

    }

    /**
     * This method takes in a string and splits it into two parts, the first name and the last name,
     * due to the valueOf method passing the String into the constructor,
     * the constructor will check if the first name and last name are valid.
     * if they are valid, the constructor will set the first name, last name and full name.
     * else, the constructor will throw an illegal argument exception.
     * @param student
     * @return
     */
    //method to get the first name, last name and full name.
    public static Name valueOf(String student) {
        String[] name = student.split(" ");
        String firstName = name[0];
        String lastName = name[1];
        Assertions.assertEquals(firstName, name[0]);
        Assertions.assertEquals(lastName, name[1]);
        Assertions.assertEquals(2, name.length);
        return new Name(firstName,lastName);
    }

    /**
     * the hashcode method takes the first name, last name and full name and multiplies them by prime numbers.
     * the method then adds the hashcode of the first name, last name and full name.
     * the method then returns the hashcode.
     * both the equals and hashcode methods are overridden so that the names can be compared to other names.
     * The hashcode method is consistent with the equals method.
     *  @param
     * @return
     */
    @Override
    public int hashCode () {
        int hashCode = 17;
        hashCode = PRIME_MULTIPLIER * hashCode
                + (firstName == null ? 0 : firstName.hashCode());
        hashCode = PRIME_MULTIPLIER * hashCode
                + (lastName == null ? 0 : lastName.hashCode());
        hashCode = PRIME_MULTIPLIER * hashCode
                + (fullName == null ? 0 : fullName.hashCode());
        return hashCode;
    }

    /**
     * the equals method takes in an object and checks if the object is an instance of the name class.
     * if the object is an instance of the name class, the method will then check if the first name, last name and full name are equal.
     * if the first name, last name and full name are equal, the method will return true.
     * else, the method will return false.
     * both the equals and hashcode methods are overridden so that the names can be compared to other names, whilst
     * having different hashcodes.
     * The hashcode method is consistent with the equals method.
     * @param studentname
     * @return
     */

    @Override
    public boolean equals(Object studentname) {
        if (this == studentname) return true;
        else if (!(studentname instanceof Name)) {return false;}

        Name otherName = (Name) studentname;

        boolean overRide = this.firstName.hashCode() == otherName.firstName.hashCode() &&
                this.lastName.hashCode() == otherName.lastName.hashCode() &&
                this.fullName.hashCode() == otherName.fullName.hashCode();
        return overRide;
    }

    @Override
    public String toString() {
        Assertions.assertEquals(fullName, firstName + " " + lastName);
        return fullName;
    }
    //getters and setters

    public String getFirstName() {
        Assertions.assertEquals(firstName, fullName.split(" ")[0]);
        return firstName;
    }


    public String getLastName() {
        Assertions.assertEquals(lastName, fullName.split(" ")[1]);
        return lastName;
    }

    public String getFullName() {
        Assertions.assertEquals(fullName, firstName + " " + lastName);
        return fullName;
    }

}