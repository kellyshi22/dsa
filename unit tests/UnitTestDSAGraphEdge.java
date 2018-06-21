/******************************************************************************
 * FILE: UnitTestDSAGraphEdge.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSAGraphEdge.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestDSAGraphEdge
{
    public static void main (String [] args)
    {
        DSAGraphEdge<String> edge = null;
        DSAGraphNode<String> n1 = new DSAGraphNode<String>("n1", "v1");
        DSAGraphNode<String> n2 = new DSAGraphNode<String>("n2", "v2");
        
        
        try
        {
            // TESTING CONSTRUCTOR
            System.out.print("Testing constructor: ");
            edge = new DSAGraphEdge<String>("Label1",100,"car", 10, 20, n1, n2);
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
            if (!(edge.getLabel().equals("Label1")))
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
            // TESTING GET DISTANCE
            System.out.print("Testing get distance: ");
            if (!((edge.getDistance()-100)<0.1))
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
            // TESTING GET MODE
            System.out.print("Testing get mode: ");
            if (!(edge.getMode().equals("car")))
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
            // TESTING GET TIME
            System.out.print("Testing get time: ");
            if (!(edge.getTime() == 10))
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
            // TESTING GET PEAK TIME
            System.out.print("Testing get peak time: ");
            if (!(edge.getPeakTime() == 20))
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
            if (edge.getVisited())
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
            edge.setVisited();
            if (!(edge.getVisited()))
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
            edge.clearVisited();
            if (edge.getVisited())
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
            // TESTING GET FROM
            System.out.print("Testing get from: ");
            if (!(edge.getFrom().getLabel().equals("n1")))
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
            // TESTING GET TO
            System.out.print("Testing get to: ");
            if (!(edge.getTo().getLabel().equals("n2")))
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
            // TESTING SET TIME
            System.out.print("Testing set time: ");
            edge.setTime(20);
            if (!(edge.getTime() == 20))
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
            if(!(edge.toString().equals("Label: Label1, Visited: false, Distance: 100.0, Mode: car, Time: 20, Peak Time: 20, From: n1, To: n2")))
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
