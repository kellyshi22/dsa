/******************************************************************************
 * FILE: DSALinkedList.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Linked List in the DSA Assignment (2017 S2)
 *          This file also contains a private inner class for Linked List Nodes
 *          (DSALinkedListNode) as well as a private class for a Linked List
 *          Iterator (DSALinkedListIterator). 
 *          All classes in this file implement generics.
 *          This file was previously used and submitted for Prac 4.
 *          The code is based on the pseudocode from the lecture slides.
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSALinkedList<E> implements Iterable<E>, Serializable
{
    public Iterator<E> iterator()
    {
        return new DSALinkedListIterator<E>(this);
    }
    
    
    private class DSALinkedListIterator<E> implements Iterator<E>
    {
        private DSALinkedList<E>.DSAListNode<E> iterNext;
        
        public DSALinkedListIterator(DSALinkedList<E> theList)
        {
            iterNext = theList.head;
        }
        
        // interface implementation
        
        // hasNext
        // import none
        // export boolean
        public boolean hasNext()
        {
            return (iterNext != null);
        }
        
        
        // next
        // import none
        // export Object
        // returns the next value in the list
        public E next()
        {
            E value;
            if (iterNext == null) // if list is empty i think
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();  // get value of the node
                iterNext = iterNext.getNext();  // move iterator?
            }
            return value;
        }
        
        
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }
    
    
    private class DSAListNode<E> implements Serializable
    {
        private E value;
        private DSAListNode<E> next;
        private DSAListNode<E> prev;
        
        
        /* Default constructor
         * Imports none
         * Exports none
         * Creates a new DSAListNode object and sets default values. */
        public DSAListNode(E inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }
        
        
        /* getValue
         * Import none
         * Export value (E)
         * Returns the value of the list node */
        public E getValue()
        {
            return value;
        }
        
        
        /* setValue
         * Import inValue (E)
         * Export none
         * Sets the value of the list node */
        public void setValue(E inValue)
        {
            value = inValue;
        }
        
        
        /* getNext
         * Import none
         * Export next (DSAListNode<E>)
         * Returns the next node */
        public DSAListNode<E> getNext()
        {
            return next;
        }
        
        
        /* setNext
         * Import newNext (DSAListNode<E>)
         * Export none
         * Sets the value of the next node */
        public void setNext(DSAListNode<E> newNext)
        {
            next = newNext;
        }
        
        
        /* getPrev
         * Import none
         * Export prev (DSAListNode<E>)
         * Returns the previous node */
        public DSAListNode<E> getPrev()
        {
            return prev;
        }
        
        
        /* setPrev
         * Import newPrev (DSAListNode<E>)
         * Export none
         * Sets the value of the previous node */
        public void setPrev(DSAListNode<E> newPrev)
        {
            prev = newPrev;
        }
    }
    
    private DSAListNode<E> head;
    private DSAListNode<E> tail;
    
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new Linked List object and sets default values. */
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }
    
    
    /* insertFirst
     * Imports newValue (E)
     * Exports none
     * Inserts a new DSALinkedListNode object to the start of the list */
    public void insertFirst(E newValue)
    {
        DSAListNode<E> newNd = new DSAListNode<E>(newValue);
        if (isEmpty()) // empty list
        {
            head = newNd; // point both head and tail to the new node
            tail = newNd;
        }
        else if (head.getNext() == null) // 1 item list
        {
            head.setPrev(newNd);//set the old first value's prev to the new node
            newNd.setNext(head); //set the next value of to be the old first val
            newNd.setPrev(null); // set new node's prev = null
            head = newNd; // make head point to the new node
        }
        else // multi item list (same as above)
        {
            newNd.setNext(head); // point to the old first value
            head.setPrev(newNd); // set the old head's prev to the new node
            newNd.setPrev(null); // set the new node's prev = null
            head = newNd; // point head to the new value
        }
    }
    
    
    /* insertLast
     * Imports newValue (E)
     * Exports none
     * Inserts a new DSALinkedListNode object to the end of the list */
    public void insertLast(E newValue)
    {
        DSAListNode<E> newNd = new DSAListNode<E>(newValue);
        if (isEmpty())
        {
            head = newNd;
            tail = newNd;
        }
        else if (head.getNext() == null) // 1 item list
        {
            newNd.setPrev(tail); //set the new node's prev to the old prev(tail)
            tail.setNext(newNd); //set the next of the last node to the new node
            tail = newNd; // change tail pointer to the new last
            newNd.setNext(null);
        }
        else // multi item list (same as above)
        {
            newNd.setPrev(tail); //set the new node's prev to the old prev(tail)
            tail.setNext(newNd); //set the next of the last node to the new node
            tail = newNd; // change tail pointer to the new last
            newNd.setNext(null);
        }
    }
    
    
    /* isEmpty
     * Imports none
     * Exports empty (boolean)
     * Returns true if the list is empty, or false if it isn't */
    public boolean isEmpty()
    {
        boolean empty = false;
        if (head == null)
        {
            empty = true;
        }
        return empty;
    }
    
    
    /* peekFirst
     * Imports none
     * Exports node (E)
     * Returns the first node in the list */
    public E peekFirst()
    {
        E nodeValue = null;
        if (isEmpty())
        {
            throw new IllegalArgumentException("empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }
    
    
    /* peekLast
     * Imports none
     * Exports node (E)
     * Returns the last node in the list */
    public E peekLast()
    {
        E nodeValue = null;
        if (isEmpty())
        {
            throw new IllegalArgumentException("empty");
        }
        else
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }
    
    
    /* removeFirst
     * Imports none
     * Exports node (E)
     * Removes a DSALinkedListNode object from the start of the list */
    public E removeFirst()
    {
        E nodeValue = null;
        if (isEmpty()) // empty
        {
            throw new IllegalArgumentException("empty");
        }
        else if (head.getNext() == null) // 1 item list
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else // multi item list
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        return nodeValue;
    }
    
    
    /* removeLast
     * Imports none
     * Exports node (E)
     * Removes a DSALinkedListNode object from the end of the list */
    public E removeLast()
    {
        E nodeValue = null;
        if (isEmpty())
        {
            throw new IllegalArgumentException("empty");
        }
        else if (head.getNext() == null) // if there is only 1 node in the list
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        return nodeValue;
    }
}
