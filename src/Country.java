/******************************************************************************
 * FILE: Country.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for Country Objects in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;

public class Country implements Serializable
{
    private String name;
    private String shortName;
    private String language;
    private int area;
    private int population;
    private String popRef;
    
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new Country object and sets default values. */
    public Country()
    {
        name = "Default Country";
        shortName = "Default Short Name";
        language = "English";
        area = 1;
        population = 1;
        popRef = "Census2016";
    }
    
    
    /* Alternate contructor 1
     * Imports inName (String)
     * Exports none
     * This constructor is used if a State is in a Country that does not exist
     * in the locations csv file. All fields except for the country name are 
     * unknown, so this method is used to initialise the fields. */
    public Country(String inName)
    {
        if (inName == null || inName.equals(""))
        {
            throw new IllegalArgumentException("Name is invalid");
        }
        name = inName;
        shortName = "Unknown Short Name";
        language = "Unknown Language";
        area = 1;
        population = 1;
        popRef = "Unknown Pop Ref";
    }
    
    
    /* Alternate constructor 2
     * Imports inName (String), inShortName (String), inLanguage (String), 
     *         inArea (integer), inPopulation (integer), inPopRef (String), 
     * Exports none
     * Creates a new Country object with all fields known. */
    public Country(String inName, String inShortName, String inLanguage,
                   int inArea, int inPopulation, String inPopRef)
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
        shortName = inShortName;
        language = inLanguage;
        area = inArea;
        population = inPopulation;
        popRef = inPopRef;
    }
    
    
    /* getName 
     * Import none
     * Export name (string)
     * Returns the value of the country's name */
    public String getName()
    {
        return name;
    }
    
    
    /* getShortName
     * Import none
     * Export shortName (string)
     * Returns the value of the country's short name */
    public String getShortName()
    {
        return shortName;
    }
    
    
    /* getLanguage
     * Import none
     * Export language (string)
     * Returns the value of the country's language */
    public String getLanguage()
    {
        return language;
    }
    
    /* getArea
     * Import none
     * Export area (integer)
     * Returns the value of the country's area */
    public int getArea()
    {
        return area;
    }
    
    
    /* getPopulation
     * Import none
     * Export population (integer)
     * Returns the value of the country's population */
    public int getPopulation()
    {
        return population;
    }
    
    
    /* getPopRef
     * Import none
     * Export popRef (string)
     * Returns the value of the country's population reference */
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
        String outString = ("Name: " + name + ", Short name: " + shortName +
                            ", Area: " + area + ", Population: " + population +
                            ", Pop ref: " + popRef);
        return outString;
    }
}
