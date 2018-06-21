/******************************************************************************
 * FILE: DSAGraph.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Graph in the DSA Assignment (2017 S2)
 *          This file was previously used and submitted for Prac 6. It has
 *          since been modified to further suit the assignment.
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSAGraph implements Serializable
{
    private DSALinkedList<DSAGraphNode<Location>> vertices;
    private DSALinkedList<DSAGraphEdge<Location>> edges;
    
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new Graph object and sets default values. */
    public DSAGraph()
    {
        vertices = new DSALinkedList<DSAGraphNode<Location>>();
        edges = new DSALinkedList<DSAGraphEdge<Location>>();
    }
    
    
    /* getVertices
     * Imports none
     * Exports vertices (DSALinkedList<DSAGraphNode<Location>>)
     * Returns the vertices list */
    public DSALinkedList<DSAGraphNode<Location>> getVertices()
    {
        return vertices;
    }
    
    
    /* getEdges
     * Imports none
     * Exports edges (DSALinkedList<DSAGraphEdge<Location>>)
     * Returns the edges list */
    public DSALinkedList<DSAGraphEdge<Location>> getEdges()
    {
        return edges;
    }
    
    
    /* addVertex
     * Imports vertex (DSAGraphNode<Location>)
     * Exports none
     * Adds a vertex to the vertices list. If already exists then output error*/
    public void addVertex(DSAGraphNode<Location> vertex)
    {
        if (hasValue(vertex.getLabel()))
        {
            /* System.out.println("Label is not unique. " + vertex.getLabel() +
                               " was not added"); */
            throw new IllegalArgumentException("Label is not unique. "
                                               + vertex.getLabel() +
                                               " was not added");
        }
        else
        {
            vertices.insertLast(vertex);
        }
    }
    
    
    /* addEdge
     * Imports inLabel (String), inDistance (double), inMode (String), 
     *         inTime (integer), inPeakTime (integer), 
     *         inV1 (DSAGraphNode<Location>), inV2 (DSAGraphNode<Location>)
     * Exports none
     * Creates and adds an edge to the edge list. */
    public void addEdge(String inLabel, double inDistance, String inMode,
                        int inTime, int inPeakTime, DSAGraphNode<Location> inV1,
                        DSAGraphNode<Location> inV2)
    {
        DSAGraphEdge<Location> edge = new DSAGraphEdge<Location>(inLabel,
                                                                 inDistance,
                                                                 inMode, inTime,
                                                                 inPeakTime,
                                                                 inV1, inV2);
        edges.insertLast(edge);
    }
    
    
    /* addEdge
     * Imports newEdge (DSAGraphEdge<Location>)
     * Exports none
     * Adds an edge to the edge list. */
    public void addEdge(DSAGraphEdge<Location> newEdge)
    {
        edges.insertLast(newEdge);
    }
    
    
    /* getVertex
     * Imports label (String)
     * Exports node (DSAGraphNode<Location>)
     * Searches for the label in the vertices list and returns the node. */
    public DSAGraphNode<Location> getVertex(String label)
    {
        DSAGraphNode<Location> node = null; // the node to return
        DSAGraphNode<Location> curr = null; // the node we are at
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                node = curr;
                //if found then node = curr. if never found then node stays null
                found = true; // break out of loop
            }
        }
        return node;
    }
    
    
    /* getVertexCount
     * Import none
     * Export count (integer)
     * Iterates through list of vertices and exports the count */
    public int getVertexCount()
    {
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        int count = 0;
        while(iter.hasNext())
        {
            count++;
            iter.next();
        }
        return count;
    }
    
    
    /* getEdge
     * Imports label (String)
     * Exports edge (DSAGraphEdge<Location>)
     * Searches for the edge in the edges list and returns the edge. */
    public DSAGraphEdge<Location> getEdge(String label)
    {
        DSAGraphEdge<Location> edge = null; // the edge to return
        DSAGraphEdge<Location> curr = null; // the edge we are at
        Iterator<DSAGraphEdge<Location>> iter = edges.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                edge = curr;
                //if found then node = curr. if never found then node stays null
                found = true; // break out of loop
            }
        }
        return edge;
    }
    
    
    /* getNewEdge
     * Imports label (String)
     * Exports edge (DSAGraphEdge<Location>)
     * Searches for an unvisited edge given the edge's label name
     * and returns it. */
    public DSAGraphEdge<Location> getNewEdge(String label)
    {
        DSAGraphEdge<Location> edge = null; // the edge to return
        DSAGraphEdge<Location> curr = null; // the node we are at
        Iterator<DSAGraphEdge<Location>> iter = edges.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label) && !curr.getVisited())
            {
                edge = curr;
                //if found then node = curr. if never found then node stays null
                found = true; // break out of loop
            }
        }
        return edge;
    }
    
    
    /* getNewEdgeWithMode
     * Imports label (String), inMode (String)
     * Exports edge (DSAGraphEdge<Location>)
     * Searches for and returns an edge given the edge's label name and mode */
    public DSAGraphEdge<Location> getNewEdgeWithMode(String label, String mode)
    {
        DSAGraphEdge<Location> edge = null; // the edge to return
        DSAGraphEdge<Location> curr = null; // the edge we are at
        Iterator<DSAGraphEdge<Location>> iter = edges.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                if (curr.getMode().equals(mode))
                {
                    edge = curr;
                    found = true; // break out of loop
                }
            }
        }
        return edge;
    }
    
    
    /* getEdgeWithDistance
     * Imports n1 (DSAGraphNode<Location>), n2 (DSAGraphNode<Location>), 
     *         distance (double)
     * Exports edge (DSAGraphEdge<Location>)
     * Searches for and returns an edge given the start and end nodes, and
     * the distance between */
    public DSAGraphEdge<Location> getEdgeWithDistance(DSAGraphNode<Location> n1,
                                                      DSAGraphNode<Location> n2,
                                                      double distance)
    {
        DSAGraphEdge<Location> edge = null; // the edge to return
        DSAGraphEdge<Location> curr = null; // the edge we are at
        String label = (n1.getLabel() + " to " + n2.getLabel()); // try label
        Iterator<DSAGraphEdge<Location>> iter = edges.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                if ((curr.getDistance() - distance) < 0.1)
                {
                    edge = curr;
                    found = true; // break out of loop
                }
            }
        }
        
        // if edge is not found then change the order of the nodes and try again
        if (edge == null)
        {
            label = (n2.getLabel() + " to " + n1.getLabel());
        }
        
        Iterator<DSAGraphEdge<Location>> iter2 = edges.iterator();
        found = false;
        while(iter2.hasNext() && !found)
        {
            curr = iter2.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                if ((curr.getDistance() - distance) < 0.1)
                {
                    edge = curr;
                    found = true; // break out of loop
                }
            }
        }
        
        return edge;
    }
    
    
    /* adjacent
     * Imports vertex (DSAGraphNode<Location>)
     * Exports list (DSALinkedList<DSAGraphNode<Location>>)
     * Returns the adjacency list of a vertex in the graph */
    public DSALinkedList<DSAGraphNode<Location>> adjacent(
                                                  DSAGraphNode<Location> vertex)
    {
        return vertex.getAdjacent();
    }
    
    
    /* adjacentE
     * Imports vertex (DSAGraphNode<Location>)
     * Exports list (DSALinkedList<DSAGraphEdge<Location>>)
     * Returns the edges list of a vertex in the graph */
    public DSALinkedList<DSAGraphEdge<Location>> adjacentE(
                                                  DSAGraphNode<Location> vertex)
    {
        return vertex.getAdjacentEdges();
    }
    
    
    /* hasValue
     * Imports label (String)
     * Exports found (boolean)
     * Returns true if the node with the label exists, and false otherwise */
    public boolean hasValue(String label)
    {
        DSAGraphNode<Location> curr = null; // the node we are at
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        boolean found = false;
        while(iter.hasNext() && !found)
        {
            curr = iter.next(); // set cur to the next node
            if (curr.getLabel().equals(label))
            {
                found = true; // break out of loop
            }
        }
        return found;
    }
    
    
    /* isAdjacent
     * Imports vertex1 (DSAGraphNode<Location>), vertex2(DSAGraphNode<Location>)
     * Exports adjacent (boolean)
     * Returns true if the nodes are adjacent, and false otherwise */
    public boolean isAdjacent(DSAGraphNode<Location> vertex1,
                              DSAGraphNode<Location> vertex2)
    {
        DSALinkedList<DSAGraphNode<Location>> vertex1list=vertex1.getAdjacent();
        boolean adjacent = false;
        DSAGraphNode<Location> node = null;
        String label = vertex2.getLabel();
        Iterator<DSAGraphNode<Location>> iter = vertex1list.iterator();
        while(iter.hasNext())
        {
            node = iter.next();
            if (label.equals(node.getLabel()))
            {
                adjacent = true;
            }
        }
        return adjacent;
    }
    
    
    /* displayList 
     * Import none
     * Export none
     * Displays the adjacency list of all locations in the graph */
    public void displayList()
    {
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator(); // vertices iterator
        DSAGraphNode<Location> column = null; // column is col 0 of the adjacency list.
        for (int i = 0; i < getVertexCount(); i++)
        {
            column = iter.next();
            System.out.print(column.getLabel() + " | "); // print the col with |
            
            // get the adjacency list of the col
            DSALinkedList<DSAGraphNode<Location>> list = adjacent(column);
            // make another iterator for the adjacency list of the col
            Iterator<DSAGraphNode<Location>> iter2 = list.iterator();
            while(iter2.hasNext()) // while adjacency list of the col has next
            {
                DSAGraphNode<Location> row = iter2.next(); // print the links
                System.out.print(row.getLabel() + " ");
            }
            System.out.println();
        }
    }
    
    
    /* displayMatrix
     * Import none
     * Export none
     * Displays the adjacency matrix of all locations in the graph */
    public void displayMatrix()
    {
        // iterator for vertices linked list
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> theVertices = null;
        for (int i = 0; i < getVertexCount(); i++)
        {
            theVertices = iter.next(); // the next vertex in the list
            System.out.print("   " + theVertices.getLabel()); // print its label
        }
        System.out.println(); // first row of the matrix is done
        
        
        iter = vertices.iterator(); // iterator for vertices linked list
        
        for (int i = 0; i < getVertexCount(); i++) // row
        {
            theVertices = iter.next(); // print the first col
            System.out.print(theVertices.getLabel() + "  ");
            
            Iterator<DSAGraphNode<Location>> iter2 = vertices.iterator();
            DSAGraphNode <Location> theVertices2 = null;
            
            for (int j = 0; j < getVertexCount(); j++) // col
            {
                theVertices2 = iter2.next();
                if (isAdjacent(theVertices2, theVertices) ||
                    isAdjacent(theVertices, theVertices2))
                {
                    System.out.print("1   ");
                }
                else
                {
                    System.out.print("0   ");
                }
            }
            System.out.println();
        }
    }

    
    
    /* depthFirstSearch
     * Import none
     * Export outputString (string)
     * Nodes are traversed depth first, using a stack. A string of locations 
     * will be exported in the order that it was traversed */
    public String depthFirstSearch()
    {
        // mark all vertices new
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> v = null;
        while (iter.hasNext())
        {
            v = iter.next();
            v.clearVisited();
        }
        
        String outputString = ""; // make output string
        DSAStack<DSAGraphNode<Location>> stack;
        stack = new DSAStack<DSAGraphNode<Location>>();
        v = vertices.peekFirst(); // push first element in vertice list
        v.setVisited(); // mark as old
        stack.push(v);
        outputString = (v.getLabel() + " ");
        
        DSAGraphNode<Location> w = null;
        DSAGraphNode<Location> topVal = null;
        
        while (!stack.isEmpty())
        {
            topVal = stack.top();
            while(hasNewVertex(topVal))
            {
                // w is the new vertex in top val's list
                w = getNewVertex(topVal);
                outputString = (outputString + w.getLabel() + " ");
                
                w.setVisited();
                stack.push(w);
                topVal = stack.top();
            }
            stack.pop();
        }
        return outputString;
    }
    
    
    /* breadthFirstSearch
     * Import none
     * Export outputString (string)
     * Nodes are traversed breadth first, using a queue. A string of locations
     * will be exported in the order that it was traversed */
    public String breadthFirstSearch()
    {
        // mark all vertices new
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> v = null;
        while (iter.hasNext())
        {
            v = iter.next();
            v.clearVisited();
        }
        
        String outputString = ""; // make output string
        DSAQueue<DSAGraphNode<Location>> queue;
        queue = new DSAQueue<DSAGraphNode<Location>>();
        v = vertices.peekFirst(); // enqueue first element in vertices list
        v.setVisited(); // mark as old
        queue.enqueue(v);
        outputString = (v.getLabel() + " ");
        
        DSAGraphNode<Location> w = null;
        DSAGraphNode<Location> frontVal = null;
        
        while (!queue.isEmpty())
        {
            frontVal = queue.dequeue();
            DSALinkedList<DSAGraphNode<Location>> frontValList;
            frontValList = frontVal.getAdjacent();
            for (DSAGraphNode<Location> o : frontValList)
            {
                w = o;
                if (w.getVisited() == false)
                {
                    outputString = (outputString + w.getLabel() + " ");
                    w.setVisited();
                    queue.enqueue(w);
                }
            }
        }
        return outputString;
    }
    
    
    /* dfsTime
     * Imports startLocation (DSAGraphNode<Location>), time (integer)
     * Exports outputString (String)
     * Finds all locations that can be visited from a start location node within
     * a certain time frame (regardless of travel mode). All locations that 
     * can be visited are added to a string of location names and exported. 
     * This is a variation on depth first search.
     * This method is used for Nearby Search. */
    public String dfsTime(DSAGraphNode<Location> startLocation, int time)
    {
        // mark all locations as new
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> v = null;
        while (iter.hasNext())
        {
            v = iter.next();
            v.clearVisited();
        }
        
        // mark all edges as new
        Iterator<DSAGraphEdge<Location>> iter2 = edges.iterator();
        DSAGraphEdge<Location> e = null;
        while (iter2.hasNext())
        {
            e = iter2.next();
            e.clearVisited();
        }
        
        // start searching
        String outputString = "";
        DSAStack<DSAGraphNode<Location>> stack;
        stack = new DSAStack<DSAGraphNode<Location>>();
        
        startLocation.setVisited(); // mark start location as old
        stack.push(startLocation); // push start location to stack
        
        DSAGraphNode<Location> w = null; // w is the new vertex
        DSAGraphNode<Location> topVal = null;
        int totalTime = 0;
        DSAGraphEdge<Location> currentEdge = null;
        
        while (!stack.isEmpty())
        {
            topVal = stack.top();
            if(hasNewVertex(topVal)) // if topVal has an unvisited vertex
            {
                w = getNewVertex(topVal); // get the unvisited vertex
                
                // get the new edge connecting the 2 locations
                currentEdge = getNewEdge(topVal.getLabel() + " to "
                                         + w.getLabel());
                // get the edge connecting the topVal to the new vertex
                
                // if edge is null then try again with the names in diff order
                if (currentEdge == null)
                {
                    currentEdge = getNewEdge(w.getLabel()+ " to "
                                             + topVal.getLabel());
                }
                
                // if edge is still null at this point, this means that there
                // are no more unvisited edges between the two nodes.
                // we can mark the new node as visited
                if (currentEdge != null) // edge is still unvisited
                {
                    // if edge time is 0 then edge impairment is 100%.
                    // edge cannot be visited so we can mark as visited
                    if (currentEdge.getTime() != 0)
                    {
                        // if the node is still within the search boundary
                        if ((currentEdge.getTime() + totalTime) <  time )
                        {
                            // update time
                            totalTime = currentEdge.getTime() + totalTime;
                            // add to the string
                            outputString = (outputString + w.getLabel() + "\n");
                            w.setVisited(); // set visited
                            stack.push(w); // push to stack
                        }
                        else // node is outside search boundary
                        {
                            currentEdge.setVisited(); // set the edge as visited
                            if (getNewEdge(topVal.getLabel() + " to "
                                           + w.getLabel()) == null)
                            {
                                if (getNewEdge(w.getLabel()+ " to "
                                               + topVal.getLabel()) == null)
                                {
                                    w.setVisited();
                                    // if this is reached, then there are no
                                    // more unvisted edges between w and the
                                    // top val. we can set the node as visited
                                }
                            }
                        }
                    }
                    else // no more unvisited edges between the two nodes
                    {
                        currentEdge.setVisited(); // mark as visited
                    }
                }
                else // all edges between the 2 nodes have been looked at.
                {
                    w.setVisited(); // set the node as visited
                }
            }
            else // there is no new vertex in the list. pop stack
            {
                totalTime = totalTime - currentEdge.getTime(); // update time
                topVal = stack.top(); // set the top as visited
                topVal.setVisited();
                stack.pop(); // pop
            }
        }
        return outputString;
    }
    
    
    /* dfsDistance
     * Imports startLocation (DSAGraphNode<Location>), distance (double)
     * Exports outputString (String)
     * Finds all locations that can be reached from a start location node within
     * a certain distance (regardless of travel mode). All locations that
     * can be visited are added to a string of location names and exported.
     * This is a variation on depth first search.
     * This method is used for Nearby Search. */
    public String dfsDistance(DSAGraphNode<Location> startLocation,
                              double distance)
    {
        // mark all locations as new
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> v = null;
        while (iter.hasNext())
        {
            v = iter.next();
            v.clearVisited();
        }
        
        // mark all edges as new
        Iterator<DSAGraphEdge<Location>> iter2 = edges.iterator();
        DSAGraphEdge<Location> e = null;
        while (iter2.hasNext())
        {
            e = iter2.next();
            e.clearVisited();
        }
        
        // start searching
        String outputString = "";
        DSAStack<DSAGraphNode<Location>> stack;
        stack = new DSAStack<DSAGraphNode<Location>>();
        
        startLocation.setVisited(); // mark start location as old
        stack.push(startLocation); // push start location to stack
        
        DSAGraphNode<Location> w = null; // w is the new vertex
        DSAGraphNode<Location> topVal = null;
        double totalDist = 0.0;
        DSAGraphEdge<Location> currentEdge = null;
        
        while (!stack.isEmpty())
        {
            topVal = stack.top();
            if(hasNewVertex(topVal)) // if topVal has an unvisited vertex
            {
                w = getNewVertex(topVal); // get the unvisited vertex
                
                // get the new edge connecting the 2 locations
                currentEdge = getNewEdge(topVal.getLabel() + " to "
                                         + w.getLabel());
                
                // if edge is null then try again with the names in diff order
                if (currentEdge == null)
                {
                    currentEdge = getNewEdge(w.getLabel()+ " to "
                                             + topVal.getLabel());
                }

                // if edge is still null at this point, this means that there
                // are no more unvisited edges between the two nodes.
                // we can mark the new node as visited
                if (currentEdge != null) // edge is still unvisited
                {
                    // if edge time is 0 then edge impairment is 100%.
                    // edge cannot be visited so we can mark as visited
                    if (currentEdge.getTime() != 0)
                    {
                        // if the node is still within the search boundary
                        if ((currentEdge.getDistance() + totalDist)
                            <  distance)
                        {
                            // update distance
                            totalDist = currentEdge.getDistance() + totalDist;
                            // add to the string
                            outputString = (outputString + w.getLabel() + "\n");
                            w.setVisited(); // set visited
                            stack.push(w); // push
                        }
                        else // node is outside search boundary
                        {
                            currentEdge.setVisited(); // set the edge as visited
                            if (getNewEdge(topVal.getLabel() + " to "
                                           + w.getLabel()) == null)
                            {
                                if (getNewEdge(w.getLabel()+ " to "
                                               + topVal.getLabel()) == null)
                                {
                                    w.setVisited();
                                    // if this is reached, then there are no
                                    // more unvisted edges between w and the
                                    // top val. we can set the node as visited
                                }
                            }
                        }
                    }
                    else // no more unvisited edges between the two nodes
                    {
                        currentEdge.setVisited(); // mark as visited
                    }
                }
                else // all edges between the 2 nodes have been looked at.
                {
                    w.setVisited(); // set the node as visited
                }
            }
            else // there is no new vertex in the list. pop stack
            {
                totalDist = totalDist - currentEdge.getDistance(); //update dist
                topVal = stack.top(); // set the top as visited
                topVal.setVisited();
                stack.pop(); // pop
            }
        }
        return outputString;
    }

    
    /* shortPath
     * Imports source (DSAGraphNode<Location>), target (DSAGraphNode<Location>),
     *         option (integer). (if option is 1 then it is peak hour)
     * Exports outputString (String)
     * Finds the shortest path between two nodes. The edges in the shortest path
     * are added to a string which is exported. This method implements 
     * This algorithm is used for Travel Search.
     *
     * This algorithm was adapted from the Java code from:
     * Lafore, "Data Structures & Algorithms in Java" (2003)
     * and pseuocode from:
     * Wikipedia, https://en.wikipedia.org/w/index.php?title=Dijkstra%27s_algorithm&oldid=805572049 
     * (accessed October 16, 2017) */
    public String shortPath(DSAGraphNode<Location> source,
                           DSAGraphNode<Location> target, int option)
    {
        DSABinarySearchTree<DSAGraphNode<Location>> tree;
        tree = new DSABinarySearchTree<DSAGraphNode<Location>>(); // create tree
        
        // set all nodes' distances from source as infinity
        // set all nodes' previous as null
        // insert all nodes in the tree
        Iterator<DSAGraphNode<Location>> iter = vertices.iterator();
        DSAGraphNode<Location> v = null;
        while (iter.hasNext())
        {
            v = iter.next();
            v.setDistanceFromSource(Double.MAX_VALUE);
            v.setPrevious(null);
            tree.insert(v.getLabel(), v);
        }
        
        // set the source's distance from source as 0
        source.setDistanceFromSource(0.0);
        
        // u is the location with the minimum distance from source
        DSAGraphNode<Location> u = null;
        boolean stop = false;
        DSALinkedList<DSAGraphNode<Location>> neighbourList;
        double alt;
        
        // while tree isn't empty and end location not found
        while (tree.height() != 0 && !stop)
        {
            u = getVertexFromTreeWithMinDist(tree); // get vertex with min dist
            tree.delete(u.getLabel()); // delete the vertex from the tree
            
            // if vertex is the end location then stop.
            // otherwise add to the neightbour list
            if (!(u.getLabel().equals(target.getLabel())))
            {
                neighbourList = u.getAdjacent();
                Iterator<DSAGraphNode<Location>> it2 = neighbourList.iterator();
                while (it2.hasNext())
                {
                    v = it2.next();
                    // get the edge between
                    DSAGraphEdge<Location> edgeBetween = getEdge(u.getLabel() +
                                                                 " to " +
                                                                 v.getLabel());
                    // if edge between is null then try again with the node
                    // names in the other order
                    if (edgeBetween == null)
                    {
                        edgeBetween = getEdge(v.getLabel() + " to "
                                              + u.getLabel());
                    }
                    
                    // alt is alternative path
                    alt = u.getDistanceFromSource() + edgeBetween.getDistance();
                    
                    // if alt is smaller then a shorter path to the end location
                    // has been found
                    if (alt < v.getDistanceFromSource())
                    {
                        v.setDistanceFromSource(alt);
                        v.setPrevious(u);
                    }
                }
            }
            else
            {
                stop = true;
            }
        } // end while
        
        
        DSAStack<DSAGraphNode<Location>> resultStack;
        resultStack = new DSAStack<DSAGraphNode<Location>>();
        u = target; // get all nodes in reverse order
        while (u.getPrevious() != null)
        {
            resultStack.push(u); // push nodes on stack
            u = u.getPrevious();
        }
        resultStack.push(u);
        
        Iterator<DSAGraphNode<Location>> iter3 = resultStack.iterator();
        DSAGraphNode<Location> curr = null;
        DSAGraphNode<Location> prev = null;
        String results = "";
        int time = 0; // in seconds
        double dist;
        
        
        // iterate through the stack and add results to a string to export
        while (iter3.hasNext())
        {
            curr = iter3.next();
            // while the current location isn't the source
            if (!(curr.getLabel().equals(source.getLabel())))
            {
                // calculate distance between locations
                dist =curr.getDistanceFromSource()-prev.getDistanceFromSource();
                
                // get the corresponding edge
                // we can't get the corrosesponding edge only with the edge
                // name since edges can have the same name with different
                // transportation modes
                DSAGraphEdge<Location> edge = getEdgeWithDistance(curr, prev,
                                                                  dist);
                
                // if the travel mode is car then ask user if it is peak hour
                // used to calculate travel time
                if (edge.getMode().equals("car"))
                {
                    if (option == 1)
                    {
                        time = time + edge.getPeakTime();
                    }
                    else
                    {
                        time = time + edge.getTime();
                    }
                }
                else // travel mode isn't car, use normal time
                {
                    time = time + edge.getTime();
                }
                
                // time is in seconds. formatted time is in hh:mm:ss
                String formattedTime = convertFromSeconds(time);
                
                // add results to the string to be exported
                results = (results+prev.getLabel()+" to "+curr.getLabel());
                results = (results+" (Total kms: "+curr.getDistanceFromSource()
                           +", Total time: " + formattedTime + ", Travel mode: "
                           + edge.getMode() + ")\n");
            }
            prev = curr;
        }
        return results;
    }
    
    
    /* convertFromSeconds
     * Imports seconds (integer)
     * Exports formatString (String)
     * Converts the time in seconds to a formatted time string in the format
     * hh:mm:ss. This private method is used in shortPath() to report the time
     * taken to travel from one location to another */
    private String convertFromSeconds(int seconds)
    {
        int hh = seconds / 3600;
        double mm = (((double)seconds/(double)3600) - (double)hh) * 60;
        int mm1 = (int)mm/1;
        double ss = (mm - (double)mm1) * 60;
        int ss1 = (int)ss/1;
        return (hh + ":" + mm1 + ":" + ss1);
    }
    
    
    /* getVertexFromTreeWithMinDist
     * Imports tree (DSABinarySearchTree<DSAGraphNode<Location>>)
     * Exports nodeWithMinDist (DSAGraphNode<Location>)
     * Returns the location node with the minimum distance from source 
     * This private method is used in shortPath() */
    private DSAGraphNode<Location> getVertexFromTreeWithMinDist(
                               DSABinarySearchTree<DSAGraphNode<Location>> tree)
    {
        DSAQueue<DSAGraphNode<Location>> inOrderList = tree.inOrder();
        Iterator<DSAGraphNode<Location>> iter = inOrderList.iterator();
        DSAGraphNode<Location> nodeWithMinDist = inOrderList.peek();
        DSAGraphNode<Location> currentNode = null;
        
        while (iter.hasNext())
        {
            currentNode = iter.next();
            if (nodeWithMinDist.getDistanceFromSource() >
                currentNode.getDistanceFromSource())
            {
                nodeWithMinDist = currentNode;
            }
        }
        return nodeWithMinDist;
    }
    
    
    /* hasNewVertex
     * Imports topVal (DSAGraphNode<Location>)
     * Exports hasNew (boolean)
     * Returns true if the value has an unvisited node in it's adjacency list
     * and returns false otherwise. This method is used in dfsTime() and 
     * dfsDistance() */
    private boolean hasNewVertex(DSAGraphNode<Location> topVal)
    {
        boolean hasNew = false;
        DSAGraphNode<Location> w = null;
        DSALinkedList<DSAGraphNode<Location>> list = topVal.getAdjacent();
        Iterator<DSAGraphNode<Location>> iter = list.iterator();
        while (iter.hasNext() && hasNew == false)
        {
            w = iter.next();
            if (w.getVisited() == false)
            {
                hasNew = true;
            }
        }
        return hasNew;
    }
    
    
    /* getNewVertex
     * Imports topVal (DSAGraphNode<Location>)
     * Exports newVertex (DSAGraphNode<Location>)
     * Returns the new vertex (w) in the topVal's adjacency list. 
     * This method is used in dfsTime() and dfsDistance() */
    private DSAGraphNode<Location> getNewVertex(DSAGraphNode<Location> topVal)
    {
        DSALinkedList<DSAGraphNode<Location>> list = topVal.getAdjacent();
        Iterator<DSAGraphNode<Location>> iter = list.iterator();
        DSAGraphNode<Location> w = null;
        boolean found = false;
        while(iter.hasNext() && found == false)
        {
            w = iter.next();
            if(w.getVisited() == false)
            {
                found = true;
            }
        }
        return w;
    }
}
