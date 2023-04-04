package uk.ac.ncl.CSC8014.Main.Test;

import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Module;

public class ModuleTest {

    public static void main(String[] args) {
        testModuleConstructor();
        testModuleValueOf();
        testModuleEquals();
        testModuleGetCourseCode();
        testModuleSetCourseCode();
        testModuleGetCourseName();
        testModuleSetCourseName();
        testModuleGetCourseSemester();
        testModuleSetCourseSemester();
        testModuleGetModuleCredits();
        testModuleSetModuleCredits();
    }
    public static void testModuleConstructor() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //check that the module has been created
        Assertions.assertEquals("CSC8014", module.getCourseCode());
        Assertions.assertEquals("Software Engineering", module.getCourseName());
        Assertions.assertEquals(1, module.getCourseSemester());
        Assertions.assertEquals(20, module.getModuleCredits());
    }

    public static void testModuleValueOf() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //create a string of the module
        String module1 = "CSC8014, Software Engineering, 1, 20";
        //using the valueOf method, create a new module from the string
        Module testModule = Module.valueOf(module1);
        //check that the two modules are equal
        Assertions.assertEquals(module, testModule);
    }

    public static void testModuleEquals(){
        //create two modules
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        Module module2 = new Module("CSC8014", "Software Engineering", 1, 20);
        //check that the two modules are equal
        Assertions.assertEquals(true, module.equals(module2));
    }

    public static void testModuleGetCourseCode() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //use the getter method to get the course code, and check that it is correct
        Assertions.assertEquals("CSC8014", module.getCourseCode());
    }

    public static void testModuleSetCourseCode() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //use the setter method to set the course code.
        module.setCourseCode("CSC8015");
        //check that the course code has been changed
        Assertions.assertEquals("CSC8015", module.getCourseCode());
    }

    public static void testModuleGetCourseName() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //use the getter method to get the course name, and check that it is correct
        Assertions.assertEquals("Software Engineering", module.getCourseName());
    }

    public static void testModuleSetCourseName() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //use the setter method to set the course name.
        module.setCourseName("Software Engineering 2");
        //check that the course name has been changed
        Assertions.assertEquals("Software Engineering 2", module.getCourseName());
    }

    public static void testModuleGetCourseSemester() {
        //create a new module
        Module module = new Module("CSC8014", "Software Engineering", 1, 20);
        //use the getter method to get the course semester, and check that it is correct
        Assertions.assertEquals(1, module.getCourseSemester());
    }

    public static void testModuleSetCourseSemester(){
        //create a new module
        Module module = new Module("CSC8014","Software Engineering",1,20);
        //use the setter method to set the course semester
        module.setCourseSemester(2);
        //check that the course semester has been changed
        Assertions.assertEquals(2,module.getCourseSemester());
    }


    public static void testModuleGetModuleCredits(){
        //create a new module
        Module module = new Module("CSC8014","Software Engineering",1,20);
        //use the getter method to get the module credits, and check that it is correct
        Assertions.assertEquals(20,module.getModuleCredits());
    }

    public static void testModuleSetModuleCredits(){
        //create a new module
        Module module = new Module("CSC8014","Software Engineering",1,20);
        //use the setter method to set the module credits
        module.setModuleCredits(30);
        //check that the module credits have been changed
        Assertions.assertEquals(30,module.getModuleCredits());
    }

}
