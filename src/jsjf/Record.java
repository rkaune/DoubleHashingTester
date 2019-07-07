/*
 * Record.java
 *
 * Richard Kaune T00641439
 * COMP 2231_SW2 Assignment 5 Question 3
 * This is the Record class for the implementation of a dynamically resizable 
 * hash table to store people’s names and Social Security numbers 
*/

package jsjf;

/**
 *
 * @author richardkaune
 */
class Record 
{
    private int ssn;
    private String name;
    private int key;
    
    /**
     * Constructor with number and name parameters
     */
    public Record (int ssnEntered, String nameEntered)
    {
        ssn = ssnEntered;
        name = nameEntered;
        key = ssnEntered;
    }
    
    /**
     * Gets the value of the ssn
     */
    public int getSSN()
    {
        return ssn;
    }
    
    /**
     * Sets the value of the ssn
     */
    public void setSSN(int ssnEntered)
    {
        ssn = ssnEntered;
        key = ssnEntered;
    }
    
    /**
     * Gets the value of the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the value of the name
     */
    public void setName(String nameEntered)
    {
        name = nameEntered;
    }
    
    /**
     * Gets the value of the key
     */
    public int getKey()
    {
        return key;
    }
    
    /**
     * Returns a string representation of the ssn and name
     */
    public String toString()
    {
        return ssn + "," + name;
    }
}
