/******************************************************************************
 * FILE: DSABinarySearchTree.java
 * AUTHOR: Kai Li Shi 19157364
 * PURPOSE: Container class for a BST in the DSA Assignment (2017 S2)
 *          This file uses generics and was previously submitted for Prac 5
*          The code is based on the pseudocode from the lecture slides. 
 * DATE: 19/10/17
 *****************************************************************************/

import java.util.*;
import java.io.*;
public class DSABinarySearchTree<E> implements Serializable
{
    private DSATreeNode<E> m_root;
    
    /* Default constructor
     * Imports none
     * Exports none
     * Creates a new Binary Search Tree object and sets default values. */
    public DSABinarySearchTree()
    {
        m_root = null;
    }
    
    
    /* find
     * Imports key (String)
     * Exports value E
     * Finds a tree node given it's key. This is the wrapper method for
     * the method findRecursive() */
    public E find (String key)
    {
        return findRecursive(key, m_root);
    }
    
    
    /* findRecursive
     * Imports key (String), currNode (DSATreeNode<E>)
     * Exports value E
     * Recursively finds a tree node given its key. */
    private E findRecursive(String key, DSATreeNode<E> currNode)
    {
        E val = null;
        
        if (currNode == null) // base case 1, key doesn't exist
        {
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if ( key.equals(currNode.getKey())) // base case 2, key found
        {
            val = currNode.getValue();
        }
        else if(key.compareTo(currNode.getKey()) < 0)
        {
            val = findRecursive(key, currNode.getLeft()); // go left
        }
        else
        {
            val = findRecursive(key, currNode.getRight()); // go right
        }
        return val;
    }
    
    
    /* exists
     * Imports key (String)
     * Exports exists (boolean)
     * Returns whether a key exists in the tree. This is the wrapper method for
     * the method existsRecursive() */
    public boolean exists(String key)
    {
        return existsRecursive(key, m_root, false);
    }
   
    
    /* existsRecursive
     * Imports key (String), currNode (DSATreeNode<E>), existsVal (boolean)
     * Exports existsVal (boolean)
     * Recursively finds a tree node given its key. Returns true if the node 
     * exists, and false otherwise */
    private boolean existsRecursive(String key, DSATreeNode<E> currNode,
                                    boolean existVal)
    {
        if (currNode == null) // base case 1, key doesn't exist
        {
            existVal = false;
        }
        else if (key.equals(currNode.getKey())) // base case 2, key found
        {
            existVal = true;
        }
        else if(key.compareTo(currNode.getKey()) < 0)
        {
            existVal = existsRecursive(key, currNode.getLeft(), false);//go left
        }
        else
        {
            existVal=existsRecursive(key, currNode.getRight(), false);//go right
        }
        return existVal;
    }
    
    
    /* insert
     * Imports key (String), value (E)
     * Exports none
     * Inserts a new node into the tree. This is the wrapper method for the 
     * method insertRecursive() */
    public void insert(String key, E value)
    {
        m_root = insertRecursive(key, value, m_root);
    }
    
    
    /* insertRecursive
     * Imports key (String), currNode (DSATreeNode<E>), cur (DSATreeNode<E>)
     * Exports existsVal (boolean)
     * Recursively finds a spot in the tree to insert the new node.  */
    private DSATreeNode<E> insertRecursive(String key, E value,
                                           DSATreeNode<E> cur)
    {
        DSATreeNode<E> updateNode = cur;
        if (cur == null)
        {
            DSATreeNode<E> newNode = new DSATreeNode<E>(key, value);
            updateNode = newNode;
        }
        else if (key.equals(cur.getKey()))
        {
            throw new IllegalArgumentException("Key already exists");
        }
        else if (key.compareTo(cur.getKey()) < 0)
        {
            cur.setLeft(insertRecursive(key, value, cur.getLeft()));
        }
        else
        {
            cur.setRight(insertRecursive(key, value, cur.getRight()));
        }
        return updateNode;
    }
    
    
    /* delete
     * Imports key (String)
     * Exports none
     * Removes a node from the tree. This is the wrapper method for the
     * method deleteRecursion() */
    public void delete (String key)
    {
        m_root = deleteRecursion(key, m_root);
    }
    
    
    /* deleteRecursion
     * Imports key (String), cur (DSATreeNode<E>)
     * Exports updateNode (DSATreeNode<E>)
     * Recursively finds the node and deletes it from the tree.  */
    private DSATreeNode<E> deleteRecursion(String key, DSATreeNode<E> cur)
    {
        DSATreeNode<E> updateNode = cur;
        if (cur == null)
        {
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if (key.equals(cur.getKey()))
        {
            updateNode = deleteNode(key, cur);
        }
        else if (key.compareTo(cur.getKey()) < 0)
        {
            cur.setLeft(deleteRecursion(key, cur.getLeft()));
        }
        else
        {
            cur.setRight(deleteRecursion(key, cur.getRight()));
        }
        return updateNode;
    }
    
    
    /* deleteNode
     * Imports key (String), delNode (DSATreeNode<E>)
     * Exports updateNode (DSATreeNode<E>)
     * Recursively finds the node and deletes it from the tree. Fixes up links
     * in the tree, maintaining the rule that the left child is smaller than
     * the right child */
    private DSATreeNode<E> deleteNode(String key, DSATreeNode<E> delNode)
    {
        DSATreeNode<E> updateNode = null;
        if (delNode.getLeft() == null && delNode.getRight() == null)
        {
            updateNode = null;
        }
        else if (delNode.getLeft() != null && delNode.getRight() == null)
        {
            updateNode = delNode.getLeft();
        }
        else if (delNode.getLeft() == null && delNode.getRight() != null)
        {
            updateNode = delNode.getRight();
        }
        else
        {
            updateNode = promoteSuccessor(delNode.getRight());
            if (!(updateNode.equals(delNode.getRight())))
            {
                updateNode.setRight(delNode.getRight());
            }
            updateNode.setLeft(delNode.getLeft());
        }
        return updateNode;
    }
    
    
    /* promoteSuccessor
     * Imports cur (DSATreeNode<E>)
     * Exports successor (DSATreeNode<E>)
     * Returns the successor, the left most child of the right subtree */
    private DSATreeNode<E> promoteSuccessor(DSATreeNode<E> cur)
    {
        DSATreeNode<E> successor = cur;
        if (cur.getLeft() != null)
        {
            successor = promoteSuccessor(cur.getLeft());
            if (successor == cur.getLeft())
            {
                cur.setLeft(successor.getRight());
            }
        }
        return successor;
    }
    
    
    /* height
     * Imports none
     * Exports height (integer)
     * Returns the height of the tree. This is the wrapper method for the 
     * method heightRec() */
    public int height()
    {
        return heightRec(m_root);
    }
    
    
    /* heightRec
     * Imports curNode (DSATreeNode<E>)
     * Exports height (integer)
     * Recursively calculates and returns the height of the tree. */
    private int heightRec(DSATreeNode<E> curNode)
    {
        int htSoFar, iLeftHt, iRightHt;
        if (curNode == null)
        {
            htSoFar = -1;
        }
        else
        {
            iLeftHt = heightRec(curNode.getLeft());
            iRightHt = heightRec(curNode.getRight());
            
            if (iLeftHt > iRightHt)
            {
                htSoFar = iLeftHt + 1;
            }
            else
            {
                htSoFar = iRightHt + 1;
            }
        }
        return htSoFar;
    }

    
    /* inOrder
     * Imports none
     * Exports queue (DSAQueue<E>)
     * Exports the nodes as a queue (in order). This is the wrapper method for
     * the method inOrderRec() */
    public DSAQueue<E> inOrder()
    {
        DSAQueue<E> queue = new DSAQueue<E>();
        return inOrderRec(m_root, queue);
    }
    
    
    /* inOrderRec
     * Imports curNode (DSATreeNode<E>), queue (DSAQueue<E>)
     * Exports queue (DSAQueue<E>)
     * Recursively stores the nodes in a queue (in order) */
    private DSAQueue<E> inOrderRec(DSATreeNode<E> curNode, DSAQueue<E> queue)
    {
        if (curNode != null)
        {
            inOrderRec(curNode.getLeft(), queue);
            queue.enqueue(curNode.getValue());
            inOrderRec(curNode.getRight(), queue);
        }
        return queue;
    }
    
    
    /* postOrder
     * Imports none
     * Exports queue (DSAQueue<E>)
     * Exports the nodes as a queue (post order). This is the wrapper method for
     * the method postOrderRec() */
    public DSAQueue<E> postOrder()
    {
        DSAQueue<E> queue = new DSAQueue<E>();
        return postOrderRec(m_root, queue);
    }
    
    
    /* postOrderRec
     * Imports curNode (DSATreeNode<E>), queue (DSAQueue<E>)
     * Exports queue (DSAQueue<E>)
     * Recursively stores the nodes in a queue (post order) */
    private DSAQueue<E> postOrderRec(DSATreeNode<E> curNode, DSAQueue<E> queue)
    {
        if (curNode != null)
        {
            postOrderRec(curNode.getLeft(), queue);
            postOrderRec(curNode.getRight(), queue);
            queue.enqueue(curNode.getValue());
        }
        return queue;
    }
                    
    
    /* preOrder
     * Imports none
     * Exports inOrderQueue (DSAQueue<E>)
     * Exports the nodes as a queue (pre order). This is the wrapper method for
     * the method preOrderRec() */
    public DSAQueue<E> preOrder()
    {
        DSAQueue<E> queue = new DSAQueue<E>();
        return preOrderRec(m_root, queue);
    }
    
    
    /* preOrderRec
     * Imports curNode (DSATreeNode<E>), queue (DSAQueue<E>)
     * Exports queue (DSAQueue<E>)
     * Recursively stores the nodes in a queue (pre order) */
    private DSAQueue<E> preOrderRec(DSATreeNode<E> curNode, DSAQueue<E> queue)
    {
        if (curNode != null)
        {
            queue.enqueue(curNode.getValue());
            preOrderRec(curNode.getLeft(), queue);
            preOrderRec(curNode.getRight(), queue);
        }
        return queue;
    }
}
