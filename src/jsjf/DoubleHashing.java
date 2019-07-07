/*
 * DoubleHashing.java
 *
 * Richard Kaune T00641439
 * COMP 2231_SW2 Assignment 5 Question 3
 * This is the implementation of a dynamically resizable hash
 * table to store people’s names and Social Security numbers 
*/

package jsjf;

/**
 *
 * @author richardkaune
 */
class DoubleHashing 
{
    // variables
    private final int TABLE_SIZE = 31;
    private final double LOAD_FACTOR = 0.8;
    
    // instantiate Record objects
    private Record hashTable[];
    private double loadFactor;
    private int arraySize;
    private int countRecords;
    private int hash;
    private int doubleHash;
    private int count;
    
    /**
     * default constructor
     */
    public DoubleHashing()
    {
        hashTable = new Record[TABLE_SIZE];
        loadFactor = LOAD_FACTOR;
        arraySize = TABLE_SIZE;
        countRecords = 0;
    }
    
    /**
     * constructor with parameters
     */
    public DoubleHashing (int newSize, double newLoadFactor)
    {
        if (newSize>0)
        {
            hashTable = new Record[newSize];
            arraySize = newSize;
        }
        else
        {
            hashTable = new Record[TABLE_SIZE];
            arraySize = TABLE_SIZE;
        }
        
        if (newLoadFactor>0)
            loadFactor = newLoadFactor;
        else
            loadFactor = LOAD_FACTOR;
            countRecords = 0;
        
    }
    
    /**
     * Inserts value into the appropriate position from calculated key
     * @param Integer key
     * @param Record hashTable record of ssn and Name
     */
    public void put (Integer key, Record hashTableRecord)
    {
        if (key==null || containsKey(key))
            return;
        if (countRecords>=loadFactor * arraySize) // threshold met
            rehash();
        
        // initial index by calling firstHashFunction
        hash = firstHashFunction(key);
        
        // second step index by calling secondHashFunction
        doubleHash = secondHashFunction(key);
        count = 1;
        
        // find available cell
        while(hashTable[hash]!=null)
        {
            hash=count*doubleHash;
            hash%=arraySize;
            count++;
        }
        hashTable[hash]= hashTableRecord;
    }
    
    /**
     * Removes value into the appropriate position from calculated key
     * @param Integer key
     * 
     */
    public Record remove (Integer key)
    {
        hash = firstHashFunction(key);
        doubleHash = secondHashFunction(key);
        count = 1;
        // loop to find key, or end found using double hashing
         while(hashTable[hash]!=null)
        {
            if (hashTable[hash].getKey()==key)
            {
                Record temp = hashTable[hash];
                hashTable[hash]=null;
                countRecords --;
                return temp;
            }
            hash=count*doubleHash;
            hash%=arraySize;
            count++;
        }
        return null;
    }
    
    /**
     * Searches for whether the specified key exists in the hash table
     * @param key
     * @return boolean true id it is present in the hash table
     */
    public boolean containsKey(Integer key)
    {
        hash = firstHashFunction(key);
        doubleHash = secondHashFunction(key);
        count=1;
        while(hashTable[hash]!=null)
        {
            if (hashTable[hash].getKey()==key)
                return true;
            hash=count*doubleHash;
            hash%=arraySize;
            count++;
        }
        return false;
    }
    
    /**
     * Gets the value of the element if the specified key is found.
     * @param key
     * @return Record from the hash table or null if not present
     */
    public Record getValue(Integer key)
    {
        hash = firstHashFunction(key);
        doubleHash = secondHashFunction(key);
        count=1;
        while(hashTable[hash]!=null)
        {
            if (hashTable[hash].getKey()==key)
                return hashTable[hash];
            hash=count*doubleHash;
            hash%=arraySize;
            count++;
        }
        return null;
    }
    
    /**
     * Returns the number of elements in the hash table
     * @return the number of elements
     */
    public int size()
    {
        return countRecords;
    }
    
    /**
     * Returns true if table has no elements.
     * @return 
     */
    public boolean isEmpty()
    {
        return (countRecords==0);
    }
    
    /**
     * Returns initial index.
     * @param newKey
     * @return int value of the first hashing function
     */
    public int firstHashFunction(Integer newKey)
    {
        int extractValueOne =newKey % 10000;
        int hashOne = extractValueOne % arraySize;
        return hashOne;
    }
    
    /**
     * Returns second index
     * @param newKey
     * @return int value of second hashing function
     */
    public int secondHashFunction(Integer newKey)
    {
        int extractValueTwo = newKey/1000;
        int hashTwo = extractValueTwo % arraySize;
        return hashTwo;
    }
    
    /**
     * Creates new hash table with double the size of the old one
     * and copies elements from old has table to new one
     */
    public void rehash()
    {
        Record[] newTable = new Record[arraySize*2];
        int oldSize = arraySize;
        arraySize = arraySize *2;
        
        for (int i=0;i<oldSize;i++)
        {
            Record record = hashTable[i];
            if (record!=null)
            {
                hash = firstHashFunction(hashTable[i].getKey());
                doubleHash = secondHashFunction(hashTable[i].getKey());
                count=1;
                while(newTable[hash]!=null)
                {
                    hash=count*doubleHash;
                    hash%=arraySize;
                    count++;
                }
                newTable[hash]=record;
            }
        }
        hashTable=newTable;
    }
    
    /**
     * Provides a string representation of the hash table contents
     * @return String value that accumulates the hash table
     */
    public String toString()
    {
        String result="";
        for(int i=0;i<hashTable.length;i++)
        {
            if(hashTable[i]!=null)
                result+=i+"-"+hashTable[i].toString() +"\n";
            else
                result+=i+"\n";
        }
        return result;
    }
}