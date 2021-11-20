/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */

package baseline;

import java.util.regex.Pattern;

public class WarningChecker {



    public boolean checkName(String name)
    {
        //check for string length
        return name.length() > 1 && name.length() < 256;
    }

    public boolean checkSerial(String serialNumber)
    {
        //checks if the serial number is in the A-XXX-XXX-XXX format
            String pattern = ("\\D-\\d\\d\\d-\\d\\d\\d-\\d\\d\\d");
        return Pattern.matches(pattern, serialNumber);


        //for every item in the list, checks if the serial number is the same


        //if the serial number is correct and not matching, return true
        //else, returns false for an error
    }

    public boolean checkValue(int value)
    {
        //checks if the money value is >= 0.
        //returns true if so, false if not to trigger error
        return value >= 0;


    }



}
