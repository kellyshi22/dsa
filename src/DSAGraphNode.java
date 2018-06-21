/******************************************************************************
 * FILE: DSAGraphNode.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Graph Node in the DSA Assignment (2017 S2)
 *          This file was previously used and submitted for Prac 6. It has
 *          since been modified to further suit the assignment.
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSAGraphNode<E> implements Serializable
{
    private String label;
    private E value;
    private DSALinkedList<DSAGraphNode<E>> links;
    private boolean visited;
    private DSALinkedList<DSAGraphEdge<E>> edgeList;
    // class fields below are used for dijkstra's algorithm
    // to find the shortest path between 2 nodes
    private double distanceFromSource;
    private DSAGraphNode<E> previous;

    
    /* Default constructor
     * Imports inLabel (String), inValue (E)
     * Exports none
     * Creates a new Graph Node object and sets default values. */
    public DSAGraphNode(String inLabel, E inValue)
    {
        label = inLabel;
        value = inValue;
        links = new DSALinkedList<DSAGraphNode<E>>();
        visited = false;
        edgeList = new DSALinkedList<DSAGraphEdge<E>>();
        distanceFromSource = Double.MAX_VALUE;
        previous = null;
    }
    
    
    /* getLabel
     * Import none
     * Export label (String)
     * Returns the value of the label */
    public String getLabel()
    {
        return label;
    }
    
    
    /* getValue
     * Import none
     * Export value (E)
     * Returns the value data stored at the node */
    public E getValue()
    {
        return value;
    }
    
    
    /* getVisited
     * Import none
     * Export visited (boolean)
     * Returns true if the node is marked as visited, and false otherwise */
    public boolean getVisited()
    {
        return visited;
    }
    
    
    /* getAdjacent
     * Import none
     * Export links (DSALinkedList<DSAGraphNode<E>>)
     * Returns a linked list of the adjacent nodes */
    public DSALinkedList<DSAGraphNode<E>> getAdjacent()
    {
        return links;
    }
    
    
    /* getAdjacentEdges
     * Import none
     * Export links (DSALinkedList<DSAGraphEdge<E>>)
     * Returns a linked list of the adjacent edges */
    public DSALinkedList<DSAGraphEdge<E>> getAdjacentEdges()
    {
        return edgeList;
    }
    
    
    /* getDistanceFromSource
     * Import none
     * Export distanceFromSource (double)
     * Returns the value of the distanceFromSource */
    public double getDistanceFromSource()
    {
        return distanceFromSource;
    }
    
    
    /* getPrevious
     * Import none
     * Export previous (DSAGraphNode<E>)
     * Returns the value of the previous node that was traversed */
    public DSAGraphNode<E> getPrevious()
    {
        return previous;
    }
    
    
    /* addEdge
     * Import inLabel(String), inDistance(double), inMode (String),
     *        inTime (integer), inPeakTime (integer), inNode (DSAGraphNode<E>)
     * Export none
     * Creates a node and inserts it to the edge list. Also adds the node to
     * the adjacency list of nodes */
    public void addEdge(String inLabel, double inDistance, String inMode,
                        int inTime, int inPeakTime, DSAGraphNode<E> inNode)
    {
        links.insertLast(inNode);
        DSAGraphEdge<E> edge = new DSAGraphEdge<E>(inLabel, inDistance, inMode,
                                                   inTime, inPeakTime, this,
                                                   inNode);
        edgeList.insertLast(edge);
    }

    
    /* setPrevious
     * Import inPrevious (DSAGraphNode<E>)
     * Export void
     * Sets the previous node */
    public void setPrevious(DSAGraphNode<E> inPrevious)
    {
        previous = inPrevious;
    }
    
    
    /* setDistanceFromSource
     * Import inDistance (double)
     * Export void
     * Sets the distance from source */
    public void setDistanceFromSource(double inDistance)
    {
        distanceFromSource = inDistance;
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

    
    /* toString
     * Import none
     * Export outString (string)
     * Returns a string representation of all fields */
    public String toString()
    {
        return "Label: " + label + ", Value: " + value + ", List: "+linkString()
        + ", Edges: " + edgeString() + ", Visited: " + visited + ", Previous: "
        + previous.getLabel() + ", Distance From Source: " + distanceFromSource;
    }
    
    
    /* linkString
     * Import none
     * Export outString (string)
     * Returns a string representation of all adjacent nodes' labels 
     * This method is used by toString() to return a cleaner string */
    private String linkString()
    {
        Iterator<DSAGraphNode<E>> iter = links.iterator();
        String outputString = "";
        DSAGraphNode<E> node = null;
        while (iter.hasNext())
        {
            node = iter.next();
            outputString = (outputString + node.getLabel() + ", ");
        }
        return outputString;
    }
    
    
    /* edgeString
     * Import none
     * Export outString (string)
     * Returns a string representation of all adjacent edges' labels
     * This method is used by toString() to return a cleaner string */
    private String edgeString()
    {
        Iterator<DSAGraphEdge<E>> iter = edgeList.iterator();
        String outputString = "";
        DSAGraphEdge<E> edge = null;
        while (iter.hasNext())
        {
            edge = iter.next();
            outputString = (outputString + edge.getLabel() + ", ");
        }
        return outputString;
    }
}
