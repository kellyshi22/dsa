/******************************************************************************
 * FILE: Location.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for Location Objects in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;

public class Location implements Serializable
{
    private String name;
    private State state;
    private Country country;
    private String coords;
    private String description;

    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new Location object and sets default values. */
    public Location()
    {
        name = "Default Location";
        state = new State();
        country = new Country();
        coords = "0.00, 0.00";
        description = "This is the default location";
    }
    
    
    /* Alternate constructor
     * Imports inName (String), inState (State), inCountry (Country),
     *         inCoords (String), inDescription (String)
     * Exports none
     * Creates a new Location object with all fields known. */
    public Location(String inName, State inState, Country inCountry,
                    String inCoords, String inDescription)
    {
        if (inName == null || inName.equals(""))
        {
            throw new IllegalArgumentException("Name is invalid");
        }
        name = inName;
        state = inState;
        country = inCountry;
        coords = inCoords;
        description = inDescription;
    }
    
    
    /* getName
     * Import none
     * Export name (string)
     * Returns the value of the location's name */
    public String getName()
    {
        return name;
    }
    
    /* getState
     * Import none
     * Export state (State)
     * Returns the state object that the location is in */
    public State getState()
    {
        return state;
    }
    
    
    /* getCountry
     * Import none
     * Export country (Country)
     * Returns the country object that the location is in */
    public Country getCountry()
    {
        return country;
    }
    
    
    /* getCoords
     * Import none
     * Export coords (String)
     * Returns the value of the location's coordinates */
    public String getCoords()
    {
        return coords;
    }
    
    
    /* getDescription
     * Import none
     * Export description (String)
     * Returns the value of the location's description */
    public String getDescription()
    {
        return description;
    }
    
    
    /* toString
     * Import none
     * Export outString (string)
     * Returns a string representation of all fields */
    public String toString()
    {
        String outString = ("Name: " + name + ", State: " + state.getName() +
                            ", Country: " + country.getName() + ", Coords: " +
                            coords + ", Description: " + description);
        return outString;
    }
}
