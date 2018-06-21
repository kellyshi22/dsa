/******************************************************************************
 * FILE: LocationGraph.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Contains the main method which calls the menu. Most exceptions are 
 *          caught and handles here.
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;

public class LocationGraph implements Serializable
{
    public static void main (String [] args)
    {
        DSAGraph locationGraph = null;
        try
        {
            Menu.menu(locationGraph);
        }
        catch (InputMismatchException ex)
        {
            System.out.println(ex.getMessage());
            Menu.menu(locationGraph);
        }
        catch(IllegalArgumentException e2)
        {
            System.out.println(e2.getMessage());
            Menu.menu(locationGraph);
        }
        catch(NullPointerException e3)
        {
            System.out.println(e3.getMessage());
            Menu.menu(locationGraph);
        }
        catch (ArrayIndexOutOfBoundsException e4)
        {
            System.out.println(e4.getMessage());
            Menu.menu(locationGraph);
        }
        catch (Exception e5)
        {
            System.out.println(e5.getMessage());
        }
    }
}
