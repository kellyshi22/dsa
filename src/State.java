/******************************************************************************
 * FILE: State.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for State Objects in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;

public class State implements Serializable
{
    private String name;
    private Country country;
    private String shortName;
    private int area;
    private String areaUnit;
    private int population;
    private String popRef;
    
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new State object and sets default values. */
    public State()
    {
        name = "Default State";
        country = new Country();
        shortName = "Default Short Name";
        area = 1;
        areaUnit = "km2";
        population = 1;
        popRef = "Default Pop Ref";
    }
    
    
    /* Alternate contructor 1
     * Imports inName (String)
     * Exports none
     * This constructor is used if a Location is in a State that does not exist
     * in the locations csv file. All fields except for the state name are
     * unknown, so this method is used to initialise the fields. */
    public State(String inName)
    {
        if (inName == null || inName.equals(""))
        {
            throw new IllegalArgumentException("Name is invalid");
        }
        name = inName;
        country = new Country("Unknown Country");
        shortName = "Unknown Short Name";
        area = 1;
        areaUnit = "km2";
        population = 1;
        popRef = "Unknown Pop Ref";
    }
    
    
    /* Alternate constructor 2
     * Imports inName (String), inCountry (Country), inShortName (String),
     *         inArea (integer), inAreaUnit (String), inPopulation (int),
     *         inPopRef (String)
     * Exports none
     * Creates a new State object with all fields known. */
    public State(String inName, Country inCountry, String inShortName,
                 int inArea, String inAreaUnit, int inPopulation,
                 String inPopRef)
    {
        if (inName == null || inName.equals(""))
        {
            throw new IllegalArgumentException("Name is invalid");
        }
        if (inArea <= 0)
        {
            throw new IllegalArgumentException("Area cannot be <= 0");
        }
        name = inName;
        country = inCountry;
        shortName = inShortName;
        area = inArea;
        areaUnit = inAreaUnit;
        population = inPopulation;
        popRef = inPopRef;
    }
    
    
    /* getName
     * Import none
     * Export name (string)
     * Returns the value of the state's name */
    public String getName()
    {
        return name;
    }
    
    
    /* getCountry
     * Import none
     * Export country (Country)
     * Returns the country object that the state is in */
    public Country getCountry()
    {
        return country;
    }
    
    
    /* getShortName
     * Import none
     * Export shortName (string)
     * Returns the value of the state's short name */
    public String getShortName()
    {
        return shortName;
    }
    
    
    /* getArea
     * Import none
     * Export area (integer)
     * Returns the value of the state's area */
    public int getArea()
    {
        return area;
    }
    
    
    /* getAreaUnit
     * Import none
     * Export areaUnit (string)
     * Returns the value of the state's area unit */
    public String getAreaUnit()
    {
        return areaUnit;
    }
    
    
    /* getPopulation
     * Import none
     * Export population (integer)
     * Returns the value of the state's population */
    public int getPopulation()
    {
        return population;
    }
    
    
    /* getPopRef
     * Import none
     * Export popRef (string)
     * Returns the value of the state's population reference */
    public String getPopRef()
    {
        return popRef;
    }
    
    
    /* toString
     * Import none
     * Export outString (string)
     * Returns a string representation of all fields */
    public String toString()
    {
        String outString = ("Name: " + name + ", Country: " + country.getName()+
                            ", Short name: " + shortName + ", Area: " + area +
                            ", Population: " + population + ", Pop ref: " +
                            popRef);
        return outString;
    }
}
