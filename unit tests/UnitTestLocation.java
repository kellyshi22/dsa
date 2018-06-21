/******************************************************************************
 * FILE: UnitTestLocation.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for Location.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestLocation
{
    public static void main (String [] args)
    {
        // create country and state object for testing purposes
        Country country = new Country();
        State state = new State();
        
        try
        {
            // TEST CONSTRUCTORS (normal conditions)
            System.out.print("Test Constructors (Normal Conditions): ");
            Location location1 = new Location();
            Location location2 = new Location("location2", state, country,
                                              "100.0, 100.0", "Test location2");
            System.out.println("Pass");
            
            //------------------------------------------------------------------
            // TEST GETTERS
            System.out.print("Test getName: ");
            if (location1.getName().equals("Default Location"))
            {
                if (location2.getName().equals("location2"))
                {
                    System.out.println("Pass");
                }
                else
                {
                    System.out.println("Fail");
                }
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            System.out.print("Test getState: ");
            if (location1.getState().getName().equals("Default State"))
            {
                if (location2.getState().getName().equals("Default State"))
                {
                    System.out.println("Pass");
                }
                else
                {
                    System.out.println("Fail");
                }
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            System.out.print("Test getCountry: ");
            if (location1.getCountry().getName().equals("Default Country"))
            {
                if (location2.getCountry().getName().equals("Default Country"))
                {
                    System.out.println("Pass");
                }
                else
                {
                    System.out.println("Fail");
                }
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            System.out.print("Test getCoords: ");
            if (location1.getCoords().equals("0.00, 0.00"))
            {
                if (location2.getCoords().equals("100.0, 100.0"))
                {
                    System.out.println("Pass");
                }
                else
                {
                    System.out.println("Fail");
                }
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            System.out.print("Test getDescription: ");
            if(location1.getDescription().equals("This is the default location"))
            {
                if (location2.getDescription().equals("Test location2"))
                {
                    System.out.println("Pass");
                }
                else
                {
                    System.out.println("Fail");
                }
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            //------------------------------------------------------------------
            // TEST TOSTRING
            System.out.print("Test toString: ");
            String outString = location2.toString();
            if (outString.equals("Name: location2, State: Default State, Country: Default Country, Coords: 100.0, 100.0, Description: Test location2"))
            {
                System.out.println("Pass");
            }
            else
            {
                System.out.println("Fail");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        //---------------------------------------------------------------------
        // TEST ERROR CONDITION
        // testing that an exception is thrown when location name is null/empty
        try
        {
            System.out.print("Test Constructor (Error Condition) #1: ");
            Location location3 = new Location(null, state, country,
                                              "0.00, 0.00", "desc");
            System.out.println("Fail");
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                Location location3 = new Location("", state, country,
                                                  "0.00, 0.00", "desc");
                System.out.println("Fail");
            }
            catch (IllegalArgumentException e2)
            {
                System.out.println("Pass");
            }
        }
    }
}
