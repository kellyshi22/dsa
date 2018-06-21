/******************************************************************************
 * FILE: UnitTestDSAGraphNode.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSAGraphNode.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestDSAGraphNode
{
    public static void main (String [] args)
    {
        DSAGraphNode<String> node = null;
        
        try
        {
            // TESTING CONSTRUCTOR
            System.out.print("Testing constructor: ");
            node = new DSAGraphNode<String>("Label1", "Val1");
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }

        
        try
        {
            // TESTING GET LABEL
            System.out.print("Testing get label: ");
            if (!(node.getLabel().equals("Label1")))
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
            // TESTING GET VALUE
            System.out.print("Testing get value: ");
            if (!(node.getValue().equals("Val1")))
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
            // TESTING GET VISITED
            System.out.print("Testing get visited: ");
            if (node.getVisited())
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        
        
        DSAGraphNode<String> n2 = new DSAGraphNode<String>("Lab2", "Val2");
        try
        {
            // TESTING ADD EDGE
            System.out.print("Testing add edge: ");
            node.addEdge("Label2", 10, "car", 10, 20, n2);
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }
        
        
        try
        {
            // TESTING GET ADJACENT
            System.out.print("Testing get adjacent: ");
            DSALinkedList<DSAGraphNode<String>> nodeList;
            nodeList = node.getAdjacent();
            if (!(nodeList.peekFirst().getLabel().equals("Lab2")))
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
            // TESTING GET ADJACENT EDGES
            System.out.print("Testing get adjacent edges: ");
            DSALinkedList<DSAGraphEdge<String>> edgeList;
            edgeList = node.getAdjacentEdges();
            if (!(edgeList.peekFirst().getLabel().equals("Label2")))
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
            // TESTING SET VISITED
            System.out.print("Testing set visited: ");
            node.setVisited();
            if (!(node.getVisited()))
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
            // TESTING CLEAR VISITED
            System.out.print("Testing clear visited: ");
            node.clearVisited();
            if (node.getVisited())
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
            // TESTING SET AND GET PREVIOUS
            System.out.print("Testing set and get previous: ");
            node.setPrevious(n2);
            if (!(node.getPrevious().getLabel().equals("Lab2")))
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
            // TESTING SET AND GET DISTANCE FROM SOURCE
            System.out.print("Testing set and get distance from source: ");
            node.setDistanceFromSource(100);
            if (!((node.getDistanceFromSource()-100)<0.1))
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
            // TESTING TO STRING
            System.out.print("Testing to string: ");
            if(!(node.toString().equals("Label: Label1, Value: Val1, List: Lab2, , Edges: Label2, , Visited: false, Previous: Lab2, Distance From Source: 100.0")))
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
