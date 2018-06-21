/******************************************************************************
 * FILE: DSAQueue.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Queue in the DSA Assignment (2017 S2)
 *          The Queue is implemented using a Linked List
 *          (from DSALinkedList.java) and uses generics.
 *          This file was previously used and submitted for Prac 4.
 *          The code is based on the pseudocode from the lecture slides. 
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSAQueue<E> implements Iterable<E>, Serializable
{
    private DSALinkedList<E> queue;
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new DSAQueue object. */
    public DSAQueue()
    {
        queue = new DSALinkedList<E>();
    }
    
    /* isEmpty
     * Imports none
     * Exports empty (boolean)
     * Returns true if the queue is empty, or false if it isn't */
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    /* enqueue
     * Imports inValue (E)
     * Exports none
     * Adds a new value to the end of the queue */
    public void enqueue(E inValue)
    {
        queue.insertLast(inValue);
    }
    
    /* dequeue
     * Imports none
     * Exports topVal (E)
     * Removes the front value from the queue */
    public E dequeue()
    {
        E frontVal = queue.removeFirst();
        return frontVal;
    }
    
    /* peek
     * Imports none
     * Exports topVal (E)
     * Returns the front value of the queue */
    public E peek()
    {
        E frontVal = queue.peekFirst();
        return frontVal;
    }
    
    /* iterator
     * Imports none
     * Exports Iterator (E)
     * Returns an iterator */
    public Iterator<E> iterator()
    {
        return queue.iterator();
    }
}
