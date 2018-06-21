/******************************************************************************
 * FILE: UnitTestDSALinkedList.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSALinkedList.java in the DSA Assignment (2017 S2)
 *          This test harness was previously submitted for Prac 4
 * DATE: 19/10/17
 *****************************************************************************/

import java.io.*;
import java.util.*;
public class UnitTestDSALinkedList
{
    public static void main (String [] args)
    {
        DSALinkedList <String> list = null;
        String testval = "hello";
        String result = null;
        
        // TEST CONSTRUCTOR
        try
        {
            System.out.print("Testing constructor: ");
            list = new DSALinkedList<String>();
            if (list.isEmpty() == false)
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        
        // TEST INSERT FIRST
        try
        {
            System.out.print("Testing insert first: ");
            list.insertFirst("c");
            list.insertFirst("b");
            list.insertFirst("a");
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Fail");
        }
        
        
        // TEST PEEK FIRST
        try
        {
            System.out.print("Testing peek first: ");
            result = list.peekFirst();
            if (!(result.equals("a")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // TEST INSERT LAST
        try
        {
            System.out.print("Testing insert last: ");
            list.insertLast("x");
            list.insertLast("y");
            list.insertLast("z");
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Fail");
        }
        
        
        // TEST PEEK LAST
        try
        {
            System.out.print("Testing peek last: ");
            result = list.peekLast();
            if (!(result.equals("z")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // TEST REMOVE FIRST
        try
        {
            System.out.print("Testing remove first: ");
            result = list.removeFirst();
            if (!(result.equals("a")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // TEST REMOVE LAST
        try
        {
            System.out.print("Testing remove last: ");
            result = list.removeLast();
            if (!(result.equals("z")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // TEST IS EMPTY
        try
        {
            System.out.print("Testing is empty: ");
            if (list.isEmpty())
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // TEST ITERATOR
        try
        {
            System.out.print("Testing iterator: ");
            Iterator<String> iter = list.iterator();
            String iterString = "";
            while (iter.hasNext())
            {
                iterString = iterString + iter.next();
            }
            if (!(iterString.equals("bcxy")))
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
