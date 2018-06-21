/******************************************************************************
 * FILE: DSAStack.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Stack in the DSA Assignment (2017 S2)
 *          The Stack is implemented using a Linked List 
 *          (from DSALinkedList.java) and uses generics.
 *          This file was previously used and submitted for Prac 4.
 *          The code is based on the pseudocode from the lecture slides. 
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSAStack<E> implements Iterable<E>, Serializable
{
    private DSALinkedList<E> stack;
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new DSAStack object. */
    public DSAStack()
    {
        stack = new DSALinkedList<E>();
    }
    
    
    /* isEmpty
     * Imports none
     * Exports empty (boolean)
     * Returns true if the stack is empty, or false if it isn't */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    
    /* push
     * Imports inValue (E)
     * Exports none
     * Adds a new value to the top of the stack */
    public void push(E inValue)
    {
        stack.insertFirst(inValue);
    }
    
    
    /* pop
     * Imports none
     * Exports topVal (E)
     * Removes the top value from the stack */
    public E pop()
    {
        E topVal = stack.removeFirst();
        return topVal;
    }
    
    
    /* top
     * Imports none
     * Exports topVal (E)
     * Returns the top value of the stack */
    public E top()
    {
        E topVal = stack.peekFirst();
        return topVal;
    }
    
    
    /* iterator
     * Imports none
     * Exports Iterator (E)
     * Returns an iterator */
    public Iterator<E> iterator()
    {
        return stack.iterator();
    }
}
