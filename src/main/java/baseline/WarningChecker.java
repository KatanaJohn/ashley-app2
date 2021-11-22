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
        //if the length is greater than 1 and less than 257, returns true. Else returns false to trigger error
        return name.length() > 1 && name.length() < 257;
    }

    public boolean checkSerial(String serialNumber)
    {
        //checks if the serial number is in the A-XXX-XXX-XXX format by using a pattern/pattern.matches
        String pattern = ("\\p{Alpha}-\\w\\w\\w-\\w\\w\\w-\\w\\w\\w");
        //for every item in the list, checks if the serial number is the same
        //if the serial number is correct and not matching, return true. else, returns false for an error
        return Pattern.matches(pattern, serialNumber);
    }

    public boolean checkValue(int value)
    {
        //checks if the money value is >= 0.
        //returns true if so, false if not to trigger error
        return value >= 0;
    }



}
