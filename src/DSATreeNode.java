/******************************************************************************
 * FILE: DSATreeNode.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a Tree Node in the DSA Assignment (2017 S2)
 *          This file was previously used and submitted for Prac 5.
 *          The code is based on the pseudocode from the lecture slides.
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSATreeNode<E> implements Serializable
{
    private String m_key;
    private E m_value;
    private DSATreeNode<E> m_leftChild, m_rightChild;
    
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new DSATreeNode object and sets default values. */
    public DSATreeNode(String inKey, E inValue)
    {
        if (inKey == null)
        {
            throw new IllegalArgumentException("Key cannot be null");
        }
        m_key = inKey;
        m_value = inValue;
        m_leftChild = null;
        m_rightChild = null;
    }
    
    
    /* getKey
     * Import none
     * Export key (String)
     * Returns the value of the tree node key */
    public String getKey()
    {
        return m_key;
    }
    
    
    /* getValue
     * Import none
     * Export value (E)
     * Returns the value of the tree  name */
    public E getValue()
    {
        return m_value;
    }
    
    
    /* getLeft
     * Import none
     * Export m_leftChild (DSATreeNode<E>)
     * Returns the value of the left child */
    public DSATreeNode<E> getLeft()
    {
        return m_leftChild;
    }
    
    
    /* setLeft
     * Import newLeft (DSATreeNode<E>)
     * Export none
     * Sets the value of the left child */
    public void setLeft(DSATreeNode<E> newLeft)
    {
        m_leftChild = newLeft;
    }
    
    
    /* getRight
     * Import none
     * Export m_rightChild (DSATreeNode<E>)
     * Returns the value of the right child */
    public DSATreeNode<E> getRight()
    {
        return m_rightChild;
    }
    
    
    /* setRight
     * Import newRight (DSATreeNode<E>)
     * Export none
     * Sets the value of the right child */
    public void setRight(DSATreeNode<E> newRight)
    {
        m_rightChild = newRight;
    }
}
