/******************************************************************************
 * FILE: UnitTestCountry.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for Country.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestCountry
{
    public static void main (String [] args)
    {
        try
        {
            // TEST CONSTRUCTORS (normal conditions)
            System.out.print("Test Constructors (Normal Conditions): ");
            Country country1 = new Country();
            Country country2 = new Country("country2");
            Country country3 = new Country("country3", "c3", "english",100,1000,
                                           "Census");
            System.out.println("Pass");
            
            //------------------------------------------------------------------
            // TEST GETTERS
            System.out.print("Test getName: ");
            if (country1.getName().equals("Default Country"))
            {
                if (country2.getName().equals("country2"))
                {
                    if (country3.getName().equals("country3"))
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
            if (country1.getShortName().equals("Default Short Name"))
            {
                if (country2.getShortName().equals("Unknown Short Name"))
                {
                    if (country3.getShortName().equals("c3"))
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
            
            System.out.print("Test getLanguage: ");
            if (country1.getLanguage().equals("English"))
            {
                if (country2.getLanguage().equals("Unknown Language"))
                {
                    if (country3.getLanguage().equals("english"))
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
            if (country1.getArea() == 1)
            {
                if (country2.getArea() == 1)
                {
                    if (country3.getArea() == 100)
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
            if (country1.getPopulation() == 1)
            {
                if (country2.getPopulation() == 1)
                {
                    if (country3.getPopulation() == 1000)
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
            if (country1.getPopRef().equals("Census2016"))
            {
                if (country2.getPopRef().equals("Unknown Pop Ref"))
                {
                    if (country3.getPopRef().equals("Census"))
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
            String outString = country3.toString();
            if (outString.equals("Name: country3, Short name: c3, Area: 100, Population: 1000, Pop ref: Census"))
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
        // TEST ERROR CONDITIONS
        // testing that an exception is thrown when country name is null/empty
        try
        {
            System.out.print("Test Constructor (Error Condition) #1: ");
            Country country4 = new Country(null);
            System.out.println("Fail");
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                Country country4 = new Country("");
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
            Country country4 = new Country(null, "shortName", "language",
                                           10, 100, "census");
            System.out.println("Fail");
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                Country country4 = new Country("", "shortName", "language",
                                               10, 100, "census");
                System.out.println("Fail");
            }
            catch (IllegalArgumentException e2)
            {
                try
                {
                    Country country4 = new Country("name", "shortName",
                                                   "language",0, 100, "census");
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
