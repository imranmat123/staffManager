package uk.ac.ncl.CSC8014.Staff;

import uk.ac.ncl.CSC8014.Staff.StaffObject.SmartCard;
import uk.ac.ncl.CSC8014.Staff.StaffObject.StaffID;
/**
 * The Staff interface is the parent interface for the Lecturer and Researcher interfaces.
 * It contains the methods that are common to both Lecturer and Researcher.
 * The Staff interface extends the Persons interface.
 *
 * Note: "//DO NOT remove or change the signature of any method." - this has been stated upon receiving this file, and thus,
 * I have not removed or changed the signature of any method. including the provided javaDoc comments. instead, I have adhered to the
 * comments provided.
 *
 * @see Persons
 * @see Lecturer
 * @see Researcher
 */

public interface Staff {

    /**
     * Returns the staff ID.
     * All staff must have an ID
     * @return the StaffID object
     */
    StaffID getStaffId();
    /**
     * Returns the smart card.
     * All staff must have a smart card
     * @return the SmartCard object
     */
    SmartCard getSmartCard();
    /**
     * Returns the Staff employment status.
     * a Staff can be either on Permanent or fixed contract
     * @return a string (Permanent or fixed)
     */
    String getStaffType();
    /**
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     * @return a string (Lecturer or Researcher)
     */
    String getStaffEmploymentStatus();

}
