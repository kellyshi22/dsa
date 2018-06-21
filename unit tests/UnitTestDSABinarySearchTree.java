/******************************************************************************
 * FILE: UnitTestDSABinarySearchTree.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSABinarySearchTree.java in the DSA Assignment
 *          This file was previously submitted for Prac 6
 *          The code is based on the pseudocode from the lecture slides. 
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
public class UnitTestDSABinarySearchTree
{
    public static void main (String [] args)
    {
        DSABinarySearchTree<String> tree = null;
        String outputString = "";
        
        try
        {
            // TESTING CONSTRUCTOR
            System.out.print("Testing constructor: ");
            tree = new DSABinarySearchTree<String>();
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }
        
        
        try
        {
            // TESTING EXISTS
            System.out.print("Testing constructor: ");
            if (tree.exists("5"))
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
            // TESTING INSERT
            System.out.print("Testing insert: ");
            tree.insert("5", "5");
            tree.insert("3", "3");
            tree.insert("4", "4");
            tree.insert("8", "8");
            tree.insert("2", "2");
            tree.insert("6", "6");
            tree.insert("9", "9");
            
            DSAQueue<String> queue = tree.inOrder();
            Iterator<String> iter = queue.iterator();
            while (iter.hasNext())
            {
                outputString = (outputString + iter.next() + ", ");
            }
            if (outputString.equals("2, 3, 4, 5, 6, 8, 9, "))
            {
                tree.insert("9", "9"); // key already exists, throw exception
            }
            else
            {
                throw new Exception("Fail");
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Pass");
        }
        catch(Exception e)
        {
            System.out.println("Fail");
        }

        try
        {
            // TESTING FIND
            System.out.print("Testing find: ");
            String val = tree.find("5"); // key to find is root
            if (!(val.equals("5")))
            {
                throw new IllegalArgumentException("fail");
            }
            val = tree.find("9"); // key to find is child
            if (!(val.equals("9")))
            {
                throw new IllegalArgumentException("fail");
            }
            val = tree.find("10"); // key doesn't exist
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Pass");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Fail");
        }
        catch (Exception e)
        {
            System.out.println("Fail");
        }
            
        try
        {
            // TESTING DELETE
            System.out.print("Testing delete: ");
            tree.delete("10"); // doesn't exist
        }
        catch (NoSuchElementException e)
        {
            try
            {
                tree.delete("9"); // has no children
                DSAQueue<String> queue = tree.inOrder();
                Iterator<String> iter = queue.iterator();
                outputString = "";
                while (iter.hasNext())
                {
                    outputString = (outputString + iter.next() + ", ");
                }
                if (!(outputString.equals("2, 3, 4, 5, 6, 8, ")))
                {
                    throw new IllegalArgumentException("Fail");
                }
                
                tree.delete("8"); // has 1 child
                queue = tree.inOrder();
                iter = queue.iterator();
                outputString = "";
                while (iter.hasNext())
                {
                    outputString = (outputString + iter.next() + ", ");
                }
                if (!(outputString.equals("2, 3, 4, 5, 6, ")))
                {
                    throw new IllegalArgumentException("Fail");
                }
                
                tree.delete("3"); // has 2 children
                queue = tree.inOrder();
                iter = queue.iterator();
                outputString = "";
                while (iter.hasNext())
                {
                    outputString = (outputString + iter.next() + ", ");
                }
                if (!(outputString.equals("2, 4, 5, 6, ")))
                {
                    throw new IllegalArgumentException("Fail");
                }
                System.out.println("Pass");
            }
            catch (IllegalArgumentException e2)
            {
                System.out.println(e2.getMessage());
            }
            catch (Exception e2)
            {
                System.out.println("Fail");
            }
        }
                
        try
        {
            // TESTING HEIGHT
            System.out.print("Testing height: ");
            int height = tree.height();
            if (height != 2)
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Fail");
        }
                
        try
        {
            // TESTING IN ORDER
            System.out.print("Testing in order: ");
            DSAQueue<String> queue = tree.inOrder();
            Iterator<String> iter = queue.iterator();
            outputString = "";
            while (iter.hasNext())
            {
                outputString = (outputString + iter.next() + ", ");
            }
            if (!(outputString.equals("2, 4, 5, 6, ")))
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
            // TESTING PRE ORDER
            System.out.print("Testing pre order: ");
            DSAQueue<String> queue = tree.preOrder();
            Iterator<String> iter = queue.iterator();
            outputString = "";
            while (iter.hasNext())
            {
                outputString = (outputString + iter.next() + ", ");
            }
            if (!(outputString.equals("5, 4, 2, 6, ")))
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
            // TESTING POST ORDER
            System.out.print("Testing post order: ");
            DSAQueue<String> queue = tree.postOrder();
            Iterator<String> iter = queue.iterator();
            outputString = "";
            while (iter.hasNext())
            {
                outputString = (outputString + iter.next() + ", ");
            }
            if (!(outputString.equals("2, 4, 6, 5, ")))
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
