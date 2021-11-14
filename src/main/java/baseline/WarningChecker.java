package baseline;

public class WarningChecker {



    public boolean checkName(String name)
    {
        //will check if the name length is > 1 and < 257

        //returns true if so, false if not to trigger error
        return true;
    }

    public boolean checkSerial(String serialNumber)
    {
        //checks if the serial number is in the A-XXX-XXX-XXX format

        //for every item in the list, checks if the serial number is the same

        //if the serial number is correct and not matching, return true
        //else, returns false for an error
        return true;
    }

    public boolean value(int value)
    {
        //checks if the money value is >= 0.

        //returns true if so, false if not to trigger error
        return true;
    }



}
