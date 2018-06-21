/******************************************************************************
 * FILE: UnitTestDSAStack.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Test Harness for DSAStack.java in the DSA Assignment (2017 S2)
 * DATE: 19/10/17
 *****************************************************************************/

public class UnitTestDSAStack
{
    public static void main (String [] args)
    {
        String testString = new String("test");
        String testString2 = new String ("test2");
        DSAStack<String> stack = null;
        try
        {
            // --------------------------------------------------------
            // TEST NORMAL CONDITIONS:
            System.out.println("TESTING NORMAL CONDITIONS");
            
            // TEST CONSTRUCTOR
            stack = new DSAStack<String>();
            System.out.println("Testing constructor: Pass");
            

            // TEST IS EMPTY
            System.out.print("Testing is empty: ");
            if (!(stack.isEmpty()))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
            
            
            // TEST PUSH AND TOP
            System.out.print("Testing push and top: ");
            stack.push(testString);
            stack.push(testString2);
            if (!(stack.top().equals("test2")))
            {
                throw new IllegalArgumentException("Fail");
            }
            System.out.println("Pass");
            
            
            // TESTING POP
            System.out.print("Testing pop: ");
            stack.pop();
            if (!(stack.top().equals("test")))
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
        
        DSAStack<String> stack2 = new DSAStack<String>();
        
        try
        {
            // TESTING POP ON EMPTY STACK
            System.out.print("Testing pop on empty stack: ");
            stack2.pop();
            System.out.println("Fail");
        }
        
        catch (IllegalArgumentException e)
        {
            System.out.println("Pass");
        }
        
        
        try
        {
            // TESTING TOP ON EMPTY STACK
            System.out.print("Testing top on empty stack: ");
            stack2.top();
            System.out.println("Fail");
        }
        
        catch (IllegalArgumentException e)
        {
            System.out.println("Pass");
        }
    }
}
