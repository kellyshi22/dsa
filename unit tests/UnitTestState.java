/******************************************************************************
 * FILE: UnitTestState.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for Country.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/


public class UnitTestState
{
    public static void main (String [] args)
    {
        // create country object for testing purposes
        Country country = new Country();
        
        try
        {
            // TEST CONSTRUCTORS (normal conditions)
            System.out.print("Test Constructors (Normal Conditions): ");
            State state1 = new State();
            State state2 = new State("state2");
            State state3 = new State("state3", country, "s3", 10, "km2", 100,
                                     "Census");
            System.out.println("Pass");
            
            //------------------------------------------------------------------
            // TEST GETTERS
            System.out.print("Test getName: ");
            if (state1.getName().equals("Default State"))
            {
                if (state2.getName().equals("state2"))
                {
                    if (state3.getName().equals("state3"))
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
            }
            else
            {
                System.out.println("Fail");
            }

            System.out.print("Test getCountry: ");
            if (state1.getCountry().getName().equals("Default Country"))
            {
                if (state2.getCountry().getName().equals("Unknown Country"))
                {
                    if (state3.getCountry().getName().equals("Default Country"))
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
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            System.out.print("Test getShortName: ");
            if (state1.getShortName().equals("Default Short Name"))
            {
                if (state2.getShortName().equals("Unknown Short Name"))
                {
                    if (state3.getShortName().equals("s3"))
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
            }
            else
            {
                System.out.println("Fail");
            }

            
            System.out.print("Test getArea: ");
            if (state1.getArea() == 1)
            {
                if (state2.getArea() == 1)
                {
                    if (state3.getArea() == 10)
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
            }
            else
            {
                System.out.println("Fail");
            }

            
            System.out.print("Test getAreaUnit: ");
            if (state1.getAreaUnit().equals("km2"))
            {
                if (state2.getAreaUnit().equals("km2"))
                {
                    if (state3.getAreaUnit().equals("km2"))
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
            }
            else
            {
                System.out.println("Fail");
            }

            
            System.out.print("Test getPopulation: ");
            if (state1.getPopulation() == 1)
            {
                if (state2.getPopulation() == 1)
                {
                    if (state3.getPopulation() == 100)
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
            }
            else
            {
                System.out.println("Fail");
            }

            
            System.out.print("Test getPopRef: ");
            if (state1.getPopRef().equals("Default Pop Ref"))
            {
                if (state2.getPopRef().equals("Unknown Pop Ref"))
                {
                    if (state3.getPopRef().equals("Census"))
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
            }
            else
            {
                System.out.println("Fail");
            }
            
            
            //------------------------------------------------------------------
            // TEST TOSTRING
            System.out.print("Test toString: ");
            String outString = state3.toString();
            if (outString.equals("Name: state3, Country: Default Country, Short name: s3, Area: 10, Population: 100, Pop ref: Census"))
            {
                System.out.println("Pass");
            }
            else
            {
                System.out.println("Fail");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        //---------------------------------------------------------------------
        // TEST ERROR CONDITIONS
        // testing that an exception is thrown when state name is null/empty
        try
        {
            System.out.print("Test Constructor (Error Condition) #1: ");
            State state4 = new State(null);
            System.out.println("Fail");
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                State state4 = new State("");
                System.out.println("Fail");
            }
            catch (IllegalArgumentException e2)
            {
                System.out.println("Pass");
            }
        }
        
        
        // testing that an exception is thrown when country name is null or
        // empty or area <= 0
        try
        {
            System.out.print("Test Constructor (Error Condition) #2: ");
            State state4 = new State(null, country, "shortName",
                                     0, "km2", 100, "census");
            System.out.println("Fail");
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                State state4 = new State("", country, "shortName",
                                         0, "km2", 100, "census");
                System.out.println("Fail");
            }
            catch (IllegalArgumentException e2)
            {
                try
                {
                    State state4 = new State("name", country, "shortName",
                                             0, "km2", 100, "census");
                    System.out.println("Fail");
                }
                catch (IllegalArgumentException e3)
                {
                    System.out.println("Pass");
                }
            }
        }
    }
}
