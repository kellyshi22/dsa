/******************************************************************************
 * FILE: UnitTestDSATreeNode.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSATreeNode.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestDSATreeNode
{
    public static void main (String [] args)
    {
        DSATreeNode<String> node = null;
        
        try
        {
            // TESTING CONSTRUCTOR
            System.out.print("Testing constructor: ");
            node = new DSATreeNode<String>("Key1", "Val1");
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }
        
        
        try
        {
            // TESTING GET KEY
            System.out.print("Testing get key: ");
            if (!(node.getKey().equals("Key1")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch (IllegalArgumentException e)
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
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING SET AND GET LEFT
            DSATreeNode<String> left = new DSATreeNode<String>("Left",
                                                               "LeftVal");
            System.out.print("Testing set left and get left: ");
            node.setLeft(left);
            if (!(node.getLeft().getValue().equals("LeftVal")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        try
        {
            // TESTING SET AND GET RIGHT
            DSATreeNode<String> right = new DSATreeNode<String>("Right",
                                                                "RightVal");
            System.out.print("Testing set right and get right: ");
            node.setRight(right);
            if (!(node.getRight().getValue().equals("RightVal")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

