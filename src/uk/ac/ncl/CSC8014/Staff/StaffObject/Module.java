package uk.ac.ncl.CSC8014.Staff.StaffObject;

public final class Module {
    private String courseCode;
    private String courseName;
    private int courseSemester;
    private int moduleCredits;
    private static final int PRIME_MULTIPLIER = 37;

    /**
     * constructor for the module class. This takes four parameters, course code, course name, course semester and module credits, these parameters are used to create module.
     * @param courseCode
     * @param courseName
     * @param courseSemester
     * @param moduleCredits
     */
    //constructor for the module class.
    public Module (String courseCode, String courseName,int courseSemester,int moduleCredits){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.moduleCredits = moduleCredits;
    }

    /**
     * the valueOf method takes a string and converts it in to a module.
     * The method then takes each part of the string and assigns it to a variable assigning variables to a new module and returns the module.
     * @param string
     * @return
     */
    //method to convert the module to a strings, split by a comma, and then trim the spaces.
    public static Module valueOf(String string) {
        String[] parts = string.split(",");
        String courseCode = parts[0].trim();
        String courseName = parts[1].trim();
        int courseSemester = Integer.valueOf(parts[2].trim());
        int moduleCredits = Integer.valueOf(parts[3].trim());
        new Module(courseCode, courseName, courseSemester,moduleCredits);
        return new Module(courseCode, courseName, courseSemester,moduleCredits);
    }


    @Override
    public String toString() {
        return courseCode + "," + courseName + "," + courseSemester + "," + moduleCredits;
    }

    /**
     * the overriding equals method takes an object and checks if the object if the module is equal to another module.
     * if the object is the not same as the other module, the method returns true, if it is, it returns true.
     * both the equals and hashcode methods are overridden so that the module can be compared to other modules.
     * the equals method is used to check if the module is the same as another module.
     * @param modules
     * @return
     */
    //overriding the equals and hashcode methods, so that the module can be compared to other modules,
    //in order to check if they are the same and to ensure that the module is not added twice/uniqueness.
    @Override
    public boolean equals(Object modules) {
        if (this == modules) return true;
        else if (!(modules instanceof Module)) {return false;}

        Module otherModules = (Module) modules;

        boolean overRide = this.courseCode.hashCode() == otherModules.courseCode.hashCode() &&
                this.courseName.hashCode() == otherModules.courseName.hashCode() &&
                this.courseSemester == otherModules.courseSemester &&
                this.moduleCredits == otherModules.moduleCredits;

        return overRide;
    }

    /**
     * the hashcode method takes module variables, multiplies them by prime numbers. to ensure uniqueness.
     * the method then returns the hashcode.
     * both the equals and hashcode methods are overridden so that the module can be compared to other modules.
     * the hashcode method is used to ensure that the module is not added twice/uniqueness.
     *  @param
     * @return
     */
    @Override
    public int hashCode () {
        int hashCodePrime = 17;

        hashCodePrime = PRIME_MULTIPLIER * hashCodePrime
                + (courseCode == null ? 0 : courseCode.hashCode());
        hashCodePrime = PRIME_MULTIPLIER * hashCodePrime
                + (courseName == null ? 0 : courseName.hashCode());
        hashCodePrime = PRIME_MULTIPLIER * hashCodePrime
                + (courseSemester == 0 ? 0 : courseSemester);
        hashCodePrime = PRIME_MULTIPLIER * hashCodePrime
                + (moduleCredits == 0 ? 0 : moduleCredits);
        return hashCodePrime;
    }

    //Setters and Getters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(int courseSemester) {
        this.courseSemester = courseSemester;
    }

    public int getModuleCredits() {
        return moduleCredits;
    }

    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

}
