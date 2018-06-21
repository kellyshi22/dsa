/******************************************************************************
 * FILE: UnitTestDSAQueue.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSAQueue.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestDSAQueue
{
    public static void main (String [] args)
    {
        String testString = new String("test");
        String testString2 = new String ("test2");
        DSAQueue<String> queue = null;
        try
        {
            // --------------------------------------------------------
            // TEST NORMAL CONDITIONS:
            System.out.println("TESTING NORMAL CONDITIONS");
            
            // TEST CONSTRUCTOR
            queue = new DSAQueue<String>();
            System.out.println("Testing constructor: Pass");
            
            
            // TEST IS EMPTY
            System.out.print("Testing is empty: ");
            if (!(queue.isEmpty()))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
            
            
            // TEST ENQUEUE AND PEAK
            System.out.print("Testing enqueue and peek: ");
            queue.enqueue(testString);
            queue.enqueue(testString2);
            if (queue.peek().equals("test"))
            {
                System.out.println("Pass");
            }
            else
            {
                throw new IllegalArgumentException("Fail");
            }
            
            
            // TESTING DEQUEUE
            System.out.print("Testing dequeue: ");
            queue.dequeue();
            if (!(queue.peek().equals("test2")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
        }
        
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        
        // --------------------------------------------------------
        // TEST ERROR CONDITIONS:
        
        System.out.println("\nTESTING ERROR CONDITIONS");
        
        DSAQueue<String> queue2 = new DSAQueue<String>();
        
        try
        {
            // TESTING DEQUEUE ON EMPTY QUEUE
            System.out.print("Testing dequeue on empty queue: ");
            queue2.dequeue();
            System.out.println("Fail");
        }
        
        catch (IllegalArgumentException e)
        {
            System.out.println("Pass");
        }
        
        
        try
        {
            // TESTING PEEK ON EMPTY QUEUE
            System.out.print("Testing peek on empty queue: ");
            queue2.peek();
            System.out.println("Fail");
        }
        
        catch (IllegalArgumentException e)
        {
            System.out.println("Pass");
        }
    }
}
