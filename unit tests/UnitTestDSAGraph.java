/******************************************************************************
 * FILE: UnitTestDSAGraph.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSAGraph.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;
public class UnitTestDSAGraph
{
    public static void main (String [] args)
    {
        // creation of objects for testing purposes
        DSAGraph graph;
        State state = new State();
        Country country = new Country();
        
        Location l1 = new Location("n1", state, country, "0.00, 0.00","node 1");
        Location l2 = new Location("n2", state, country, "0.00, 0.00","node 2");
        
    
        DSAGraphNode<Location> node1 = new DSAGraphNode<Location>("n1", l1);
        DSAGraphNode<Location> node2 = new DSAGraphNode<Location>("n2", l2);
        node1.addEdge("n1 to n2", 10, "car", 10, 13, node2);
        node2.addEdge("n1 to n2", 10, "car", 10, 13, node1);

        
        
        
        try
        {
            // TESTING CONSTRUCTOR
            System.out.print("Testing constructor: ");
            graph = new DSAGraph();
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }
        
        graph = new DSAGraph();
        
        try
        {
            // TESTING ADD VERTEX
            System.out.print("Testing add vertex: ");
            graph.addVertex(node1);
            graph.addVertex(node2);
            graph.addVertex(node1); // should fail since label not unique
            System.out.println("Fail");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Pass");
        }

        
        
        try
        {
            // TESTING ADD EDGE
            System.out.print("Testing add edge: ");
            graph.addEdge("n1 to n2", 10, "car", 10, 13, node1,
                          node2);
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Fail");
        }
        
        
        try
        {
            // TESTING GET VERTICES
            System.out.print("Testing get vertices: ");
            DSALinkedList<DSAGraphNode<Location>> v = graph.getVertices();
            DSAGraphNode<Location> curr = null;
            Iterator<DSAGraphNode<Location>> iter = v.iterator();
            String result = "";
            while (iter.hasNext())
            {
                curr = iter.next();
                result = result + curr.getLabel() + " ";
            }
            if (!(result.equals("n1 n2 ")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING GET EDGES
            System.out.print("Testing get edges: ");
            DSALinkedList<DSAGraphEdge<Location>> e = graph.getEdges();
            Iterator<DSAGraphEdge<Location>> iter = e.iterator();
            DSAGraphEdge<Location> curr = null;
            String result = "";
            while (iter.hasNext())
            {
                curr = iter.next();
                result = result + curr.getLabel() + " ";
            }
            if (!(result.equals("n1 to n2 ")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        
        try
        {
            // TESTING GET VERTEX
            System.out.print("Testing get vertex: ");
            DSAGraphNode<Location> edge = graph.getVertex("n1");
            if (edge != null)
            {
                edge = graph.getVertex("n3"); // doesn't exist
                if (edge != null)
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING GET EDGE
            System.out.print("Testing get edge: ");
            DSAGraphEdge<Location> edge = graph.getEdge("n1 to n2");
            if (edge != null)
            {
                edge = graph.getEdge("n2 to n1"); // doesn't exist
                if (edge != null)
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING GET NEW EDGE
            System.out.print("Testing get new edge: ");
            DSAGraphEdge<Location> edge = graph.getNewEdge("n1 to n2");
            if (edge != null)
            {
                edge = graph.getNewEdge("n2 to n1"); // doesn't exist
                if (edge != null)
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING GET EDGE WITH MODE
            System.out.print("Testing get edge with mode: ");
            DSAGraphEdge<Location> edge = graph.getNewEdgeWithMode("n1 to n2",
                                                                "car");
            if (edge != null)
            {
                edge = graph.getNewEdgeWithMode("n2 to n1", "car");
                if (edge != null)
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING GET EDGE WITH DISTANCE
            System.out.print("Testing get edge with distance: ");
            DSAGraphEdge<Location> edge = graph.getEdgeWithDistance(node1,node2,
                                                                    10);
            if (edge != null)
            {
                edge = graph.getEdgeWithDistance(node2,node1, 10);
                if (edge == null)
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING ADJACENT
            System.out.print("Testing adjacent: ");
            DSALinkedList<DSAGraphNode<Location>> n = graph.adjacent(node1);
            Iterator<DSAGraphNode<Location>> iter = n.iterator();
            DSAGraphNode<Location> curr = null;
            String result = "";
            while (iter.hasNext())
            {
                curr = iter.next();
                result = result + curr.getLabel() + " ";
            }
            if (!(result.equals("n2 ")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING ADJACENTE
            System.out.print("Testing adjacentE: ");
            DSALinkedList<DSAGraphEdge<Location>> e = graph.adjacentE(node1);
            Iterator<DSAGraphEdge<Location>> iter = e.iterator();
            DSAGraphEdge<Location> curr = null;
            String result = "";
            while (iter.hasNext())
            {
                curr = iter.next();
                result = result + curr.getLabel() + " ";
            }
            if (!(result.equals("n1 to n2 ")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING HAS VALUE
            System.out.print("Testing has value: ");
            if (graph.hasValue("n1"))
            {
                if (graph.hasValue("n3"))
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING IS ADJACENT
            System.out.print("Testing is adjacent: ");
            if (!(graph.isAdjacent(node1, node2)))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING DFS TIME
            System.out.print("Testing DFS time: ");
            if (!(graph.dfsTime(node1, 20).equals("n2\n")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING DFS DISTANCE
            System.out.print("Testing DFS distance: ");
            if (!(graph.dfsDistance(node1, 20).equals("n2\n")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING SHORT PATH
            System.out.print("Testing short path: ");
            if (!(graph.shortPath(node1, node2, 1).equals("n1 to n2 (Total kms: 10.0, Total time: 0:0:12, Travel mode: car)\n")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
