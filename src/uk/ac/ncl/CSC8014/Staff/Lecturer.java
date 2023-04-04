package uk.ac.ncl.CSC8014.Staff;
import uk.ac.ncl.CSC8014.Assertions;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Module;
import uk.ac.ncl.CSC8014.Staff.StaffObject.Name;
import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCard;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class Lecturer extends Persons {

    //create a set of modules used by the lecturer to see what modules they are teaching.
    private Set<Module> listOfModules = new HashSet<Module>();
    private int moduleCredits;

    private final int MAX_CREDITS = 40;

    /**
     * constructor for the Lecturer class, this takes four parameters, forename, surname, birthdate and contract type.
     * the constructor then calls the super class, which is the persons class, and passes the parameters to the super class.
     * this constructor is protected, so that it can only be accessed by certain classes.
     * this constructor also creates a new smart card for the lecturer.
     * @param forename
     * @param surname
     * @param birthDate
     * @param contractType
     * @throws ParseException
     */
    //constructor for the lecturer class
    protected Lecturer(String forename, String surname, Date birthDate, String contractType) throws ParseException {
        super (forename, surname, birthDate, "Lecturer".toLowerCase(), contractType);
        this.smartCard = new SmartCard(staffName.getFirstName(),staffName.getLastName(),birthDate, "Lecturer".toLowerCase(), contractType);
    }

    /**
     * the isTeachingCreditsEnough method returns either true of false based on amount of credits that the lecturer is teaching, if the lecturer is teaching more than 40 credits
     * then the lecturer is teaching too many credits, it will return false. else it will return true.
     * @return
     */
    //get the modules that the lecturer is teaching and returns if the lecturer is teaching enough credits.
    public boolean isTeachingCreditsEnough() {
        int moduleCredits = 0;
        for (Module module : listOfModules) {
            moduleCredits = module.getModuleCredits() + moduleCredits;
        }
        if (moduleCredits < MAX_CREDITS) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * the addSetOfModules method will take a set of modules and add them to a lecturer, at the same time, this method will also,
     * check how many credits the lecturer is teaching. if the lecturer is teaching more than 40 credits,
     * then the lecturer will not be able to add any more modules, due to teaching too many credits.
     * @param module
     */
    //adds a set of modules to the lecturer, whilst checking if the lecturer is teaching too many credits.
    public void addSetOfModules(Set<Module> module) {
        moduleLimit(module);

    }

    /**
     * the addModule method will take a single module and add it to a lecturer, whilst checking how many credits the lecturer is teaching.
     * if the lecturer is teaching more than 40 credits, then the lecturer will not be able to add any more modules,
     * due to teaching too many credits.
     * @param module
     */
    //adds a single module to the lecturer, whilst checking if the lecturer is teaching too many credits.
    public void addModule(Module module) {
        moduleLimit(module);
    }

    /**
     * the moduleLimit method is integrated in to the addModule methods, this method will check how many credits the lecturer has
     * if the lecturer is teaching more than the stipulation, then the lecturer will not be able to add any more modules.
     * Additionally, this method has been overloaded to take sets and single modules.
     * @param module
     */
    //add modules to the lecturer, whilst checking if the lecturer is teaching too many credits.
    // (the module limit method has been overloaded to take sets and also single modules)
    public void moduleLimit(Set<Module> module){
        Set<Module> referencedModules = new HashSet<>();
        referencedModules.addAll(module);

        int totalCredits = 0;
        //for each loop to get the credits of each module and add them together.
        for (Module m : referencedModules) {
            moduleCredits = moduleCredits + m.getModuleCredits();
            totalCredits = totalCredits + m.getModuleCredits();
        }
        if (moduleCredits > MAX_CREDITS) {
            throw new IllegalArgumentException("Lecturer is teaching too many modules.");
        } else {
            totalCredits = moduleCredits + totalCredits;
            listOfModules.addAll(module);
        }
    }

    /**
     * the moduleLimit method is integrated in to the addModule methods, this method will check how many credits the lecturer has
     * if the lecturer is teaching more than the stipulation, then the lecturer will not be able to add any more modules.
     * Additionally, this method has been overloaded to take sets and single modules.
     * @param module
     */
    //adds a single module to the lecturer, whilst checking if the lecturer is teaching too many credits.
    // (the module limit method has been overloaded to take sets and also single modules)
    public void moduleLimit(Module module){
        moduleCredits = moduleCredits + module.getModuleCredits();
        int totalCredits = 0;

        if(moduleCredits > MAX_CREDITS){
            throw new IllegalArgumentException("Lecturer is teaching too many modules.");
        } else if (totalCredits > MAX_CREDITS) {
            throw new IllegalArgumentException("Lecturer is teaching too many modules.");
        } else {
            totalCredits = moduleCredits + totalCredits;
            listOfModules.add(module);
        }
    }



    @Override
    public String toString() {
        return "lecturer: " + getStaffName().getFirstName() + " " + getStaffName().getLastName() + " " + ", smartCard: " + getSmartCard()  + ", modules: " + listOfModules + "\n";
    }

    //getters

    public int getModuleCredits() {
        return moduleCredits;
    }

    public Set<Module> getListOfModules() {
        return listOfModules;
    }
}
