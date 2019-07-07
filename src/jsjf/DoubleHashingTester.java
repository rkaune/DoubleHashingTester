/*
 * DoubleHashingTester.java
 *
 * Richard Kaune T00641439
 * COMP 2231_SW2 Assignment 5 Question 3
 * This is the client code for the implementation of a dynamically 
 * resizable hash table to store people’s names and Social Security numbers 
*/

package jsjf;

import java.util.Scanner;
/**
 *
 * @author richardkaune
 */
public class DoubleHashingTester 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // variables
        int number=0;
        String name="";
        String choice = "Y";
        
        // instantiate DoubleHashing objects
        DoubleHashing hashingObject = new DoubleHashing();
        // Scanner object
        Scanner input = new Scanner(System.in);
        
        // user input
        do
        {
            System.out.print("Enter a six digit SSN: ");
            number = input.nextInt();
            while (number <100000 || number >999999)
            {
                System.out.print("Enter a six digit SSN: ");
                number = input.nextInt();
            }
            System.out.print("Enter their name: ");
            name = input.next();
            Record sinRecord = new Record(number,name);
            
            // put record into hash table
            hashingObject.put(sinRecord.getKey(),sinRecord);
            
            // prompt user to continue
            System.out.print("Enter another record? ");
            choice = input.next();
            System.out.println();
            
        } while(choice.equalsIgnoreCase("Y"));
        
        System.out.println("Elements in hash table are: ");
        System.out.println(hashingObject.toString());
        
    }
    
}