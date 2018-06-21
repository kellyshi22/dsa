/******************************************************************************
 * FILE: FileIO.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Class for all file input/output related methods. Includes
 *          functionality for reading, writing, loading and saving.
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;
public class FileIO implements Serializable
{
    /* getNumLines
     * Imports fileName (String)
     * Exports numLines (integer)
     * Returns the number of items in the file */
    public static int getNumLines(String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int numLines = 0;
        String line;
        
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            
            line = bufRdr.readLine();
            while (line != null)
            {
                numLines++;
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        
        catch (IOException e)
        {
            if (numLines == 0)
            {
                System.out.println("File is empty");
            }
            else if (fileStrm == null)
            {
                System.out.println("Invalid file");
            }
            else
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException e2)
                {
                }
            }
        }
        return numLines;
    }
    
    
    /* processCountry
     * Imports fileName (String), numLines (integer)
     * Exports countryTree (DSABinarySearchTree<Country>)
     * Creates country objects and stores them in a binary search tree to be 
     * returned. Loops through each line in the file. If the line represents 
     * a country then process each field and use them to create a new 
     * country object. Using a binary search tree since we can assume that 
     * all countries are unique */
    public static DSABinarySearchTree<Country> processCountry(String fileName,
                                                              int numLines)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, delim;
        String [] tokens;
        String [] values;
        Country country = null;
        String name = null;
        String shortName = null;
        String language = null;
        int area = 0;
        int population = 0;
        String popRef = null;
        DSABinarySearchTree<Country> countryTree;
        countryTree = new DSABinarySearchTree<Country>();
        
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            
            line = bufRdr.readLine();
            for (int i = 0; i < numLines; i++)
            {
                tokens = line.split(":");
                int tokenCount = tokens.length;
                if (tokens[0].equals("COUNTRY"))
                {
                    for (int j = 1; j < tokenCount; j++)
                    {
                        values = tokens[j].split("=");
                        if (j == 1)
                        {
                            name = values[1];
                        }
                        else if (j == 2)
                        {
                            shortName = values[1];
                        }
                        else if (j == 3)
                        {
                            language = values[1];
                        }
                        else if (j == 4)
                        {
                            area = Integer.parseInt(values[1]);
                        }
                        else if (j == 5)
                        {
                            population = Integer.parseInt(values[1]);
                        }
                        else if (j == 6)
                        {
                            popRef = values[1];
                        }
                    }
                    country = new Country(name, shortName, language, area,
                                          population, popRef);
                    countryTree.insert(name, country);
                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            try
            {
                fileStrm.close();
            }
            catch (IOException e2)
            {
            }
        }
        return countryTree;
    }
    
    
    /* processState
     * Imports fileName (String), numLines (integer), countryTree
     *         (DSABinarySearchTree<Country>)
     * Exports stateTree (DSABinarySearchTree<State>)
     * Creates state objects and stores them in a binary search tree to be
     * returned. Loops through each line in the file. If the line represents
     * a state then process each field and use them to create a new
     * state object. Using a binary search tree since we can assume that
     * all states are unique. */
    public static DSABinarySearchTree<State> processState(String fileName,
                                                          int numLines,
                                     DSABinarySearchTree<Country> countryTree)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String [] tokens;
        String [] values;
        State state = null;
        String name = null;
        Country country = null;
        String shortName = null;
        int area = 0;
        String areaUnit = null;
        int population = 0;
        String popRef = null;
        DSABinarySearchTree<State> stateTree = new DSABinarySearchTree<State>();
        
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            
            line = bufRdr.readLine();
            for (int i = 0; i < numLines; i++)
            {
                tokens = line.split(":");
                int tokenCount = tokens.length;
                if (tokens[0].equals("STATE"))
                {
                    for (int j = 1; j < tokenCount; j++)
                    {
                        values = tokens[j].split("=");
                        if (j == 1)
                        {
                            name = values[1];
                        }
                        else if (j == 2)
                        {
                            String countryName = values[1];
                            country = matchCountry(countryTree, countryName);
                            // match the country to an existing country
                        }
                        else if (j == 3)
                        {
                            shortName = values[1];
                        }
                        else if (j == 4)
                        {
                            area = Integer.parseInt(values[1]);
                        }
                        else if (j == 5)
                        {
                            areaUnit = values[1];
                        }
                        else if (j == 6)
                        {
                            population = Integer.parseInt(values[1]);
                        }
                        else if (j == 7)
                        {
                            popRef = values[1];
                        }
                    }
                    state = new State(name, country, shortName, area, areaUnit,
                                      population, popRef);
                    stateTree.insert(name, state);
                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        } // end try

        catch (IOException e)
        {
            System.out.println(e.getMessage());
            try
            {
                fileStrm.close();
            }
            catch (IOException e2)
            {
            }
        }
        return stateTree;
    }
    
    
    /* processLocation
     * Imports fileName (String), numLines (integer), 
     *         countryTree (DSABinarySearchTree<Country>),
     *         stateTree (DSABinarySearchTree<State>)
     * Exports locationTree (DSABinarySearchTree<Location>)
     * Creates location objects and stores them in a binary search tree to be
     * returned. Loops through each line in the file. If the line represents
     * a location then process each field and use them to create a new
     * location object. Using a binary search tree since we can assume that
     * all locations are unique. */
    public static DSABinarySearchTree<Location> processLocation(String fileName,
                                                                int numLines,
                                       DSABinarySearchTree<Country> countryTree,
                                           DSABinarySearchTree<State> stateTree)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, delim;
        String [] tokens;
        String [] values;
        Location location = null;
        String name = null;
        State state = null;
        Country country = null;
        String coords = null;
        String description = null;
        DSABinarySearchTree<Location> locationTree;
        locationTree = new DSABinarySearchTree<Location>();
        
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            for (int i = 0; i < numLines; i++)
            {
                tokens = line.split(":");
                int tokenCount = tokens.length;
                if (tokens[0].equals("LOCATION"))
                {
                    for (int j = 1; j < tokenCount; j++)
                    {
                        values = tokens[j].split("=");
                        if (j == 1)
                        {
                            name = values[1];
                        }
                        else if (j == 2)
                        {
                            String stateName = values[1];
                            state = matchState(stateTree, stateName);
                            // match the state to an existing state
                        }
                        else if (j == 3)
                        {
                            String countryName = values[1];
                            country = matchCountry(countryTree, countryName);
                            // match the country to an existing country
                        }
                        else if (j == 4)
                        {
                            coords = values[1];
                        }
                        else if (j == 5)
                        {
                            description = values[1];
                        }
                    }
                    location = new Location(name, state, country, coords,
                                            description);
                    locationTree.insert(name, location);
                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            try
            {
                fileStrm.close();
            }
            catch (IOException e2)
            {
            }
        }
        return locationTree;
    }
    
    
    /* processDistances
     * Imports fileName (String), locationGraph (DSAGraph), numLines (integer)
     * Exports locationGraph (DSAGraph)
     * Reads the distances file and creates edges between each location. Adds
     * the edge to the edge list of each location, and inserts the edge into
     * the graph. */
    public static DSAGraph processDistances(String fileName,
                                            DSAGraph locationGraph,
                                            int numLines)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String [] tokens;
        String [] values;
        String line, label, mode;
        int time, peakTime;
        DSAGraphNode<Location> vertex1 = null;
        DSAGraphNode<Location> vertex2 = null;
        double distance;
        
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            for (int i = 0; i < numLines; i++)
            {
                label = "";
                mode = "";
                time = 0;
                peakTime = 0;
                distance = 0.0;

                line = bufRdr.readLine();
                tokens = line.split(",");
                int tokenCount = tokens.length;
                vertex1 = locationGraph.getVertex(tokens[0]);
                vertex2 = locationGraph.getVertex(tokens[3]);
                mode = tokens[6];
                distance = Double.parseDouble(tokens[7]);
                time = convertToSeconds(tokens[8]);
                if (tokenCount >= 10) // if peak time exists
                {
                    String [] theTime = (tokens[9]).split("=");
                    peakTime = convertToSeconds(theTime[1]);
                }
                
                label = (tokens[0] + " to " + tokens[3]);
                
                if (vertex1 != null && vertex2 != null)
                {
                    DSAGraphEdge<Location> locationEdge;
                    locationEdge = new DSAGraphEdge<Location>(label, distance,
                                                              mode, time,
                                                              peakTime, vertex1,
                                                              vertex2);
                    locationGraph.addEdge(locationEdge);
                    vertex1.addEdge(label, distance, mode, time, peakTime,
                                    vertex2);
                    vertex2.addEdge(label, distance, mode, time, peakTime,
                                    vertex1);
                }
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            try
            {
                fileStrm.close();
            }
            catch (IOException e2)
            {
            }
        }
        return locationGraph;
    }
    
    
    /* writeReport
     * Imports fileName(String), header(String), result(String)
     * Exports none
     * Writes the reports. Writes out the header and result strings to a file
     * called fileName. Used to let users save the Travel Reports to a file */
    public static void writeReport(String fileName, String header,String result)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw = null;

        try
        {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            
            pw.println(header);
            pw.println(result);
            pw.close();
        }
        catch (IOException e)
        {
            pw.close();
        }
        catch (Exception e2)
        {}
    }
    
    
    /* save
     * Imports locationGraph (DSAGraph), fileName (String)
     * Exports none
     * Imports the location graph and serialises it */
    public static void save(DSAGraph locationGraph, String fileName)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        
        try
        {
            fileStrm = new FileOutputStream(fileName);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(locationGraph);
            
            objStrm.close();
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Unable to save");
        }
    }
    
    
    /* load
     * Imports fileName (String)
     * Exports locationGraph (DSAGraph)
     * Imports the name of the serialised file and deserialises it, returning
     * the location graph object */
    public static DSAGraph load(String fileName) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSAGraph locationGraph = null;
        
        try
        {
            fileStrm = new FileInputStream(fileName); 
            objStrm = new ObjectInputStream(fileStrm);
            locationGraph = (DSAGraph)objStrm.readObject();
            objStrm.close();
        }
        
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Country not found");
        }
        
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Unable to load object");
        }
        return locationGraph;
    }
    
    
    /* convertToSeconds
     * Imports time (String)
     * Exports time (int)
     * Converts the time read in from the distances file into an integer.
     * Time is converted from the format hh:mm:ss or hh:mm to seconds (int)
     * Returns the time in seconds to be stored as a class field in the
     * edge object*/
    private static int convertToSeconds(String time)
    {
        int hours, mins, secs, totalSecs = 0;
        String tokens[];
        tokens = time.split(":");
        if (tokens.length == 3)
        {
            hours = Integer.parseInt(tokens[0]);
            mins = Integer.parseInt(tokens[1]);
            secs = Integer.parseInt(tokens[2]);
            totalSecs = hours * 3600 + mins * 60 + secs;
        }
        else if (tokens.length == 2)
        {
            hours = Integer.parseInt(tokens[0]);
            mins = Integer.parseInt(tokens[1]);
            totalSecs = hours * 60 * 60 + mins * 60;
        }
        return totalSecs;
    }
    
    
    /* matchCountry
     * Import countryTree (DSABinarySearchTree<Country>), countryName (String)
     * Export matchingCountry (Country)
     * Finds the country object and returns it. This method is used in
     * processState() and processCountry() when reading the file and creating
     * the state and location objects. A country object is a class field of
     * both state and location objects. The name of the country is used to find
     * the corresponding country, since it is the key and all countries are
     * unique. If the country isn't found, then make a new country object with
     * the imported country name */
    private static Country matchCountry(DSABinarySearchTree<Country>
                                        countryTree, String countryName)
    {
        Country matchingCountry = null;
        boolean countryFound = countryTree.exists(countryName);
        boolean found = false;
        // assuming imported countryName is the country's full name
        
        if (countryFound)
        {
            matchingCountry = countryTree.find(countryName); // country found
        }
        else//if not found assume the imported countryName is the short name
        {
            // export all country names as a queue
            DSAQueue<Country> countryQueue = countryTree.inOrder();
            Iterator<Country> iter = countryQueue.iterator();
            Country currCountry;
            while (iter.hasNext() && !found) // iterate through queue
            {
                currCountry = iter.next();
                if(currCountry.getShortName().equals(countryName))
                {
                    matchingCountry = currCountry;//if found then assign country
                    found = true;
                }
            }
            if (!found) // if not found then make new country object
            {
                matchingCountry = new Country(countryName);
                // insert in countryTree
                countryTree.insert(countryName, matchingCountry);
            }
        }
        return matchingCountry;
    }
    
    
    /* matchState
     * Import stateTree (DSABinarySearchTree<State>), stateName (String)
     * Export matchingState (State)
     * Finds the state object and returns it. This method is used in
     * processCountry() when reading the file and creating location object.
     * A state object is a class field of location objects. The name of the
     * state is used to find the corresponding state, since it is the key and
     * all states are unique. If the state isn't found, then make a new state
     * object with the imported state name */
    private static State matchState(DSABinarySearchTree<State> stateTree,
                                    String stateName)
    {
        State matchingState = null;
        boolean stateFound = stateTree.exists(stateName);
        boolean found = false;
        // assuming imported stateName is the state's full name
        
        if (stateFound)
        {
            matchingState = stateTree.find(stateName); // country found
        }
        else // if not found assume the imported stateName is state's short name
        {
            // export all state names as a queue
            DSAQueue<State> stateQueue = stateTree.inOrder();
            Iterator<State> iter = stateQueue.iterator();//iterate through queue
            State currState;
            while (iter.hasNext() && !found)
            {
                currState = iter.next();
                if(currState.getShortName().equals(stateName))
                {
                    matchingState = currState; // if found then assign state
                    found = true;
                }
            }
            
            if (!found) // if not found then make new state object
            {
                matchingState = new State(stateName);
                stateTree.insert(stateName, matchingState);//insert in stateTree
            }
        }
        return matchingState;
    }
}
