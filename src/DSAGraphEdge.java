/******************************************************************************
 * FILE: DSAGraphEdge.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Graph Edge in the DSA Assignment (2017 S2)
 *          This file was previously used and submitted for Prac 6. It has
 *          since been modified to further suit the assignment.
 * DATE: 19/10/17
 *****************************************************************************/


import java.util.*;
import java.io.*;
public class DSAGraphEdge<E> implements Serializable
{
    private String label;
    private double distance;
    private String mode;
    private int time; // time and peak time are in seconds
    private int peakTime;
    private DSAGraphNode<E> vertex1;
    private DSAGraphNode<E> vertex2;
    private boolean visited;

    
    /* Default constructor
     * Imports inLabel (String), inDistance (double), inMode (String),
     *         inTime (integer), inPeakTime (integer), 
     *         inVertex1 (DSAGraphNode<E>), inVertex2 (DSAGraphNode<E>)
     * Exports none
     * Creates a new Graph Edge object and sets default values. */
    public DSAGraphEdge(String inLabel, double inDistance, String inMode,
                        int inTime, int inPeakTime, DSAGraphNode<E> inVertex1,
                        DSAGraphNode<E> inVertex2)
    {
        label = inLabel;
        distance = inDistance;
        mode = inMode;
        time = inTime;
        peakTime = inPeakTime;
        vertex1 = inVertex1;
        vertex2 = inVertex2;
        visited = false;
    }

    
    /* getLabel
     * Import none
     * Export label (String)
     * Returns the value of the label */
    public String getLabel()
    {
        return label;
    }

    
    /* getDistance
     * Import none
     * Export distance (double)
     * Returns the value of the distance */
    public double getDistance()
    {
        return distance;
    }
    
    
    /* getMode
     * Import none
     * Export mode (String)
     * Returns the value of the travel mode */
    public String getMode()
    {
        return mode;
    }
    
    
    /* getTime
     * Import none
     * Export time (integer)
     * Returns the value of the time in seconds */
    public int getTime()
    {
        return time;
    }
    
    
    /* getPeakTime
     * Import none
     * Export peakTime (integer)
     * Returns the value of the peakTime in seconds */
    public int getPeakTime()
    {
        return peakTime;
    }
    

    /* getFrom
     * Import none
     * Export vertex1 (DSAGraphNode<E>)
     * Returns the node that the edge starts from */
    public DSAGraphNode<E> getFrom()
    {
        return vertex1;
    }

    
    /* getTo
     * Import none
     * Export vertex2 (DSAGraphNode<E>)
     * Returns the node that the edge goes to */
    public DSAGraphNode<E> getTo()
    {
        return vertex2;
    }
    
    
    /* getVisited
     * Import none
     * Export visited (boolean)
     * Returns true if the edge is marked as visited, and false otherwise */
    public boolean getVisited()
    {
        return visited;
    }
    
    
    /* setVisited
     * Import none
     * Export none
     * Sets visited to true */
    public void setVisited()
    {
        visited = true;
    }
    
    
    /* clearVisited
     * Import none
     * Export none
     * Sets visited to false */
    public void clearVisited()
    {
        visited = false;
    }

    
    /* setTime
     * Import inTime (integer)
     * Export none
     * Sets the time */
    public void setTime(int inTime)
    {
        time = inTime;
    }

    
    /* toString
     * Import none
     * Export outString (string)
     * Returns a string representation of all fields */
    public String toString()
    {
        String outString = ("Label: " + label + ", Visited: " + visited +
                            ", Distance: " + distance + ", Mode: " + mode +
                            ", Time: " + time + ", Peak Time: " + peakTime +
                            ", From: " + vertex1.getLabel() + ", To: "
                            + vertex2.getLabel());
        return outString;
    }
}

