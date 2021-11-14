package baseline;

import java.util.regex.Pattern;

public class WarningChecker {



    public boolean checkName(String name)
    {
        //check for string length
        if(name.length() > 1 && name.length() < 256)
        {
            return true;
        }
        return false;
    }

    public boolean checkSerial(String serialNumber)
    {
        System.out.println("Checking serial");
        //checks if the serial number is in the A-XXX-XXX-XXX format
            String pattern = ("\\D-\\d\\d\\d-\\d\\d\\d-\\d\\d\\d");
            if(!Pattern.matches(pattern, serialNumber))
            {
                return false;
            }


        //for every item in the list, checks if the serial number is the same


        //if the serial number is correct and not matching, return true
        //else, returns false for an error
        return true;
    }

    public boolean checkValue(int value)
    {
        //checks if the money value is >= 0.
        if(value < 1)
        {
            return false;
        }

        //returns true if so, false if not to trigger error
        return true;
    }



}
