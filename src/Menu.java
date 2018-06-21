/******************************************************************************
 * FILE: Menu.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Contains the menu and all related methods. Builds and interacts
 *          with the graph based on user input.
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;
public class Menu implements Serializable
{
    /* menu 
     * Imports locationGraph (DSAGraph)
     * Export none
     * Outputs menu and asks for user input. Continues until user inputs 0 */
    public static void menu(DSAGraph locationGraph)
    {
        DSABinarySearchTree<Country> countryTree;
        DSABinarySearchTree<State> stateTree;
        DSABinarySearchTree<Location> locationTree = null;
        int option = 1;
        while (option != 0)
        {
            try
            {
                System.out.println("-----------------------------------------");
                System.out.println("Menu: ");
                System.out.println("(1)\t -Travel Search");
                System.out.println("(2)\t -Location Search");
                System.out.println("(3)\t -Nearby Search");
                System.out.println("(4)\t -Update Data");
                System.out.println("(5)\t -Load Data");
                System.out.println("(6)\t -Save Data");
                System.out.println("(0)\t -Quit");
                System.out.print("Choice:> ");
                
                option = validateInput(0, 6);
                System.out.println("-----------------------------------------");
                
                if (option == 1)
                {
                    travelSearch(locationGraph);
                }
                else if (option == 2)
                {
                    locationSearch(locationTree, locationGraph);
                }
                else if (option == 3)
                {
                    nearbySearch(locationGraph);
                }
                else if (option == 4)
                {
                    updateData(locationGraph);
                }
                else if (option == 5)
                {
                    System.out.println("Would you like to:");
                    System.out.println("1. Load from csv file");
                    System.out.println("2. Load from serialized file");
                    int selection = validateInput(1, 2);
                    if (selection == 1)
                    {
                        System.out.println("Enter the locations file name");
                        Scanner sc = new Scanner(System.in);
                        String locationFile = sc.nextLine();
                        
                        int lines = FileIO.getNumLines(locationFile);
                        countryTree = FileIO.processCountry(locationFile,lines);
                        stateTree = FileIO.processState(locationFile, lines,
                                                        countryTree);
                        locationTree = FileIO.processLocation(locationFile,
                                                              lines,countryTree,
                                                              stateTree);
                        locationGraph = loadDistances(locationGraph,
                                                      locationTree);
                    }
                    else
                    {
                        locationGraph = loadSer("locationGraph.ser");
                    }
                    
                    System.out.println("Data has been read in");
                }
                else if (option == 6)
                {
                    if (locationGraph == null)
                    {
                        System.out.println("Graph is empty. Unable to save");
                    }
                    else
                    {
                        saveSer(locationGraph, "locationGraph.ser");
                        System.out.println("Graph has been saved");
                    }
                }
            }
            
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input");
            }
            catch (NullPointerException e2)
            {
            }
            catch(IllegalArgumentException e3)
            {
            }
            catch (Exception e5)
            {
            }
        }
    }
    
    
    /* travelSearch
     * Imports locationGraph (DSAGraph)
     * Exports none
     * Gives user information on the travel path between two locations. User
     * enters the start and end locations, and whether it is peek hour or not.
     * The shortest path is outputted to the user, including travel times,
     * travel distances, and extra information on the locations (if travel
     * detail option is selected). User can also choose to save this information
     * to a file. */
    private static void travelSearch(DSAGraph locationGraph)
    {
        String locationName1 = "";
        String locationName2 = "";
        String fileName = "";
        String header, results;
        int peakHour = 1;
        int report = 1;
        if (locationGraph == null)
        {
            System.out.println("Graph is empty. Please load data first");
        }
        else
        {
            DSAGraphNode<Location> endVertex = null;
            DSAGraphNode<Location> startVertex = null;
            System.out.print("Please enter a start location: ");
            Scanner sc = new Scanner(System.in);
            locationName1 = sc.nextLine();
            boolean found = false;
            while (!found)
            {
                if (locationGraph.hasValue(locationName1)) // find location 1
                {
                    startVertex = locationGraph.getVertex(locationName1);
                    found = true;
                    System.out.print("Please enter an end location: ");
                    locationName2 = sc.nextLine();
                    boolean found2 = false;
                    while (!found2)
                    {
                        // find location 2
                        if (locationGraph.hasValue(locationName2))
                        {
                            endVertex = locationGraph.getVertex(locationName2);
                            found2 = true;
                            
                            // ask user if it is peak hour
                            System.out.println("Is it peak hour?");
                            System.out.println("1. Yes\n2. No");
                            peakHour = validateInput(1,2);
                            
                            // ask user for travel info wanted
                            System.out.println("Would you like: ");
                            System.out.println("1. Travel Search");
                            System.out.println("2. Travel Report");
                            report = validateInput(1,2);
                            
                        }
                        else
                        {
                            System.out.print("End location not found. ");
                            System.out.print("Please try again: ");
                            locationName2 = sc.nextLine();
                        }
                    }
                }
                else
                {
                    System.out.print("Start location not found. ");
                    System.out.print("Please try again: ");
                    locationName1 = sc.nextLine();
                }
            }
            
            
            header = ("Travel Information From " + startVertex.getLabel()
                      + " to "+ endVertex.getLabel());
            if (peakHour == 1)
            {
                header = (header + " during peak hour\n");
            }
            else
            {
                header = (header + "\n");
            }
            
            // find shortest path
            results = locationGraph.shortPath(startVertex, endVertex, peakHour);
            
            // if travel detail report selected, add the location information
            // to the results
            if (report == 2)
            {
                Location l1 = startVertex.getValue();
                Location l2 = endVertex.getValue();
                
                results = (results+"\n\nInformation on "+startVertex.getLabel()
                           +"\n");
                results = (results + l1.toString() + "\n");
                
                results = (results+"\nInformation on "+endVertex.getLabel()
                           +"\n");
                results = (results + l2.toString()+ "\n");
                
                if (!(l1.getState().equals(l2.getState())))
                {
                    results = (results+"\nYou may need a passport to travel\n");
                }
                fileName = "TravelReport.txt";
            }
            else
            {
                fileName = "TravelSearch.txt";
            }
            
            // print information
            System.out.println(header);
            System.out.println(results);
            
            // ask the user if they would like to write to file
            askWriteReport(fileName, header, results);
        }
    }
    
    
    /* locationSearch
     * Imports locationTree (DSABinarySearchTree<Location>), locationGraph
     *         (DSAGraph)
     * Exports none
     * Allows the user to search for locations in the graph. User may enter
     * the start of the location name and the program will output all
     * location names that start with it. User input is case insensitive.
     * User can also choose to save this information to a file.*/
    private static void locationSearch(DSABinarySearchTree<Location>
                                       locationTree, DSAGraph locationGraph)
    {
        if (locationGraph == null)
        {
            System.out.println("Graph is empty. Please load data first");
        }
        else
        {
            System.out.print("Please enter a location: ");
            Scanner sc = new Scanner(System.in);
            String substring = sc.nextLine();
            
            // get all location names in order
            DSAQueue<Location> locationQueue = locationTree.inOrder();
            Iterator<Location> iter = locationQueue.iterator();
            String header = ("Locations starting with '" + substring+ "': ");
            System.out.println(header);
            
            // traverse the queue and print matching locations
            int count = 0;
            String result = "";
            String matchesFound = "";
            while (iter.hasNext())
            {
                Location currLocation = iter.next();
                // convert string to lowercase to allow case insensitivity
                String substrLow = substring.toLowerCase();
                if (currLocation.getName().toLowerCase().startsWith(substrLow))
                {
                    result = (result + currLocation.getName() + "\n");
                    count++;
                }
            }
            
            if (count == 0)
            {
                matchesFound = "No matches were found";
            }
            else
            {
                matchesFound = ("(" + count + " matches found)");
            }
            
            // print results and number of matches found
            System.out.println(result + matchesFound);
            
            // ask the user if they would like to write to file
            askWriteReport("LocationSearch.txt", header, result+matchesFound);
        }
    }
    
    
    /* nearbySearch
     * Imports locationGraph (DSAGraph)
     * Exports none
     * Allows the user to search for locations that can be reached in a
     * specific time/distance. Users input the start location name and
     * the maximum distance or time. A list of locations within this range
     * is outputted to the user. The user can also choose to save this
     * information to a file. */
    private static void nearbySearch (DSAGraph locationGraph)
    {
        if (locationGraph == null)
        {
            System.out.println("Graph is empty. Please load data first");
        }
        else
        {
            System.out.print("Please input a start location name: ");
            Scanner sc = new Scanner(System.in);
            String locationName = sc.nextLine();
            boolean found = false;
            String result = "";
            String header = "";
            
            
            while (!found)
            {
                if (locationGraph.hasValue(locationName))
                {
                    DSAGraphNode<Location> locationNode;
                    locationNode = locationGraph.getVertex(locationName);
                    found = true;
                    System.out.println("Please choose from the following: ");
                    
                    // 4 print statements below to keep the lines < 80 chars
                    System.out.print("1. Find locations within a specific ");
                    System.out.println("distance");
                    System.out.print("2. Find locations within a specific ");
                    System.out.println("time");
                    int option = validateInput(1, 2);
                    if (option == 1) // distance selected
                    {
                        System.out.print("Enter the distance (in kms): ");
                        double distance = sc.nextDouble();
                        if (distance <= 0)
                        {
                            System.out.println("Error. Invalid distance");
                        }
                        else
                        {
                            result = locationGraph.dfsDistance(locationNode,
                                                               distance);
                            header = ("Locations within " + distance +
                                      " kms from " + locationNode.getLabel());
                        }
                    }
                    else // time selected
                    {
                        System.out.print("Enter time (in mins): ");
                        int time = sc.nextInt();
                        if (time <= 0)
                        {
                            System.out.println("Error. Invalid time");
                        }
                        else
                        {
                            // pass in time in seconds
                            result =locationGraph.dfsTime(locationNode,time*60);
                            header = ("Locations within " + time + " mins from "
                                      + locationNode.getLabel());
                        }
                    }
                    
                    // print results
                    System.out.println(header);
                    System.out.println(result);
                    
                    // ask the user if they would like to write to file
                    askWriteReport("NearbySearch.txt", header, result);
                }
                else
                {
                    System.out.print("Location not found. Please try again: ");
                    locationName = sc.nextLine();
                }
            }
        }
    }
    
    
    /* updateData 
     * Import locationGraph (DSAGraph)
     * Export none
     * Allows the user to update the normal travel time of a path between 2 
     * locations. Asks user to input 2 locations, the mode of transportation 
     * and impairment percentage. If impairment percentage is 100 then the 
     * travel time is set to 0 (it cannot be accessible) */
    private static void updateData(DSAGraph locationGraph)
    {
        String locationName1 = "";
        String locationName2 = "";
        if (locationGraph == null)
        {
            System.out.println("Graph is empty. Please load data first");
        }
        else
        {
            DSAGraphNode<Location> endVertex = null;
            DSAGraphNode<Location> startVertex = null;
            System.out.print("Please enter a start location: ");
            Scanner sc = new Scanner(System.in);
            locationName1 = sc.nextLine();
            boolean found = false;
            while (!found)
            {
                if (locationGraph.hasValue(locationName1)) // get location 1
                {
                    startVertex = locationGraph.getVertex(locationName1);
                    found = true;
                    System.out.print("Please enter an end location: ");
                    locationName2 = sc.nextLine();
                    boolean found2 = false;
                    while (!found2)
                    {
                        // get second location
                        if (locationGraph.hasValue(locationName2))
                        {
                            endVertex = locationGraph.getVertex(locationName2);
                            found2 = true;
                        }
                        else
                        {
                            System.out.print("End location not found. ");
                            System.out.print("Please try again: ");
                            locationName2 = sc.nextLine();
                        }
                    }
                }
                else
                {
                    System.out.print("Start location not found. ");
                    System.out.print("Please try again: ");
                    locationName1 = sc.nextLine();
                }
            }
            
            // if start and end locations are the same
            if (locationName1.equals(locationName2))
            {
                System.out.println("Start and end locations are the same");
            }
            else
            {
                System.out.println("Please select a mode of transportation: ");
                System.out.println("1. Car\n2. Cycle\n3. Plane");
                System.out.println("4. Public Transport\n5. Walk");
                String mode = "";
                String outputMode = "";
                // output mode is the mode but in a nice string.
                // eg mode 'pubtrans' is 'public transport' in output mode
                int option = validateInput(1,6);
                if (option == 1)
                {
                    mode = "car";
                    outputMode = "car";
                }
                else if (option == 2)
                {
                    mode = "cycle";
                    outputMode = "cycle";
                }
                else if (option == 3)
                {
                    mode = "plane";
                    outputMode = "plane";
                }
                else if (option == 4)
                {
                    mode = "pubtrans";
                    outputMode = "public transport";
                }
                else
                {
                    mode = "walk";
                    outputMode = "walk";
                }
                DSAGraphEdge<Location> edge = locationGraph.getNewEdgeWithMode(
                                                                locationName1 +
                                                                        " to " +
                                                        locationName2, mode);
                if (edge == null) // if edge is null then change order of labels
                {
                    edge = locationGraph.getNewEdgeWithMode(locationName2
                                                            + " to "
                                                            + locationName1,
                                                            mode);
                }
                
                if (edge == null)//if edge is still null then path doesn't exist
                {
                    System.out.println("Path with the transportation mode " +
                                       outputMode + " does not exist between " +
                                       locationName1 + " and " + locationName2);
                }
                else // ask user to enter % as an integer
                {
                    System.out.print("Please enter an impairment percentage: ");
                    double percentage = sc.nextDouble();
                    if (100-percentage < 0.1) // if user enters 100
                    {
                        edge.setTime(0); // set time to 0 (cannot be travelled)
                        System.out.println("The time for the path from " +
                                           edge.getLabel() + " via " +outputMode
                                           +" has been changed to impossible");
                    }
                    else // user doesn't enter 100
                    {
                        edge.setTime(edge.getTime() * (int)
                                     (100.0/(100-percentage)));
                        int timeInSec = edge.getTime();
                        double timeInMins = timeInSec/60;
                        int timeInMins2 = (int)timeInMins/1;
                        System.out.println("The time for the path from " +
                                           edge.getLabel() + " via "+outputMode+
                                           " has been changed to " +timeInMins2+
                                           " mins");
                    }
                }
            }
        }
    }
    
    
    /* loadDistances
     * Imports locationGraph (DSAGraph),
     *         locationTree (DSABinarySearchTree<Location>)
     * Exports locationGraph (DSAGraph)
     * Asks the user for the distances file name and builds the location graph.
     * Returns the graph to the menu to be used. */
    private static DSAGraph loadDistances(DSAGraph locationGraph,
                                          DSABinarySearchTree<Location>
                                          locationTree)
    {
        System.out.println("Enter the distances file name");
        Scanner sc = new Scanner(System.in);
        String distanceFile = sc.nextLine();
        
        locationGraph = new DSAGraph(); // make new graph
        
        // add each from the location tree to the location graph
        DSAQueue<Location> locationQueue = locationTree.inOrder();
        Iterator<Location> iter3 = locationQueue.iterator();
        Location currLocation;
        DSAGraphNode<Location> locationNode;
        while (iter3.hasNext())
        {
            currLocation = iter3.next();
            locationNode = new DSAGraphNode<Location>(currLocation.getName(),
                                                      currLocation);
            locationGraph.addVertex(locationNode);
        }
        
        // add edges between locations
        int numLines = FileIO.getNumLines(distanceFile);
        locationGraph = FileIO.processDistances(distanceFile,
                                                locationGraph, numLines);
        
        return locationGraph;
    }

    
    /* loadSer
     * Import fileName (String)
     * Export locationGraph (DSAGraph)
     * Loads graph from serialised file */
    private static DSAGraph loadSer(String fileName)
    {
        DSAGraph locationGraph = FileIO.load(fileName);
        return locationGraph;
    }
    
    
    /* saveSer
     * Import fileName (String)
     * Export none
     * Serialises graph */
    private static void saveSer(DSAGraph locationGraph, String fileName)
    {
        FileIO.save(locationGraph, fileName);
    }
    
    
    /* askWriteReport
     * Imports fileName (String), header (String), info (String)
     * Exports none
     * Asks the user if they would like the user to save the information to 
     * a file. Used for Travel Search, Location Search and Nearby Search */
    private static void askWriteReport(String fileName, String header,
                                       String info)
    {
        System.out.println("Would you like to save this info to a file?");
        System.out.print("1. Yes  or 2. No: ");
        int option = validateInput(1, 2);
        if (option == 1)
        {
            FileIO.writeReport(fileName, header, info);
            System.out.println("Information has been saved to " + fileName);
        }
    }
    
    
    /* validateInput
     * Import lower (integer), upper (integer)
     * Exports option (integer)
     * Validates user input. Imports the lower and upper bounds. Continuously
     * asks the user to input an option until it is valid. Returns the valid
     * option */
    private static int validateInput(int lower, int upper)
    {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        while (option < lower || option > upper)
        {
            System.out.print("Invalid option. Enter again: ");
            option = sc.nextInt();
        }
        return option;
    }
}
