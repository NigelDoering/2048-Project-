/* File Header & Class Header */ 
//Name: Nigel Doering 
//Email: nfdoerin.ucsd.edu
//cs8b account: cs8bwaat
//Student ID: A14445032
// This file is used as a tester for making sure that the methods written
// in WordCountList.java are accurate and correct. The class is used to call the
// methods created for testing.

import java.io.*;
import java.util.*;

public class WordCountListTester {

    /** Methods: */

    /* main method
        NOTE: If you plan on throw Exceptions, you need to change "IOException" to "Exception"
        IOExceptions are a specific set of Exceptions.
        */




    public static void main(String[] args) throws IOException {
        WordCountListTester wct = new WordCountListTester();


       //This tester uses the command line. That's why the args are passed to the method
        //Calling all the tester methods
        wct.providedTester(args);
        wct.testGetWordsFromFile();
        wct.testRemoveCommon();
        wct.testToString();
        wct.testTopNWords();
       //Testing for the outputWords method: it was successful
       // WordCountList wcl = new WordCountList();
       // wcl.getWordsFromFile("Tester.txt");
       // wcl.outputWords(true);

    } // End Main Method
    //Method to test if getWordsFromFile is correct 
    //takes no parameters and is void.
    public static void testGetWordsFromFile() {
       //Creating a word countlist object to call method on 
       WordCountList wcl = new WordCountList();
       //Creating variable for the file
       String sourceFile = "Tester.txt";
       //Calling method
       try { wcl.getWordsFromFile(sourceFile);
       }
       catch(IOException e) {
          System.out.println("An exception has occurred!");

       }

       //Creating variable to hold modified list
       ArrayList<WordCount> listCheck = wcl.getList();
       //Checking if the first word in the listCheck is correct
       if (listCheck.get(0).getWord().equals("hey") &&
          (listCheck.get(0).getCount() == 3)) {
          System.out.println("correct");
       }
       //Otherwise printing out that it is wrong
       else {
          System.out.println("1 is incorrect");
       }
       //Checking the second element in listCheck
       if (listCheck.get(1).getWord().equals("what") &&
          listCheck.get(1).getCount() == 2) {
         
          System.out.println("correct");
       }
       //Else statement if it is not correct
       else {
          System.out.println("2 is incorrect");
       }
       //Checking the third element in ListCheck 
       if (listCheck.get(2).getWord().equals("is") &&
          listCheck.get(2).getCount() == 3) {
          System.out.println("correct");
       }
       //Else statement if it is incorrect
       else {
          System.out.println("3 is incorrect");
       }
       //Checking if the fourth element is correct
       if (listCheck.get(3).getWord().equals("up") &&
          listCheck.get(3).getCount() == 1) {
          System.out.println("correct");
       }
       //Else statement if fourth element is incorrect
       else { 
          System.out.println("4 is incorrect");
       }
    }
       
    //Method to test removeCommon to make sure it is working properly
    //Takes no parameters and return is void
    public static void testRemoveCommon() {
       //Creating wordCountList to call method on 
       WordCountList list = new WordCountList();

       try {
          list.getWordsFromFile("Tester.txt"); 
          }
       catch (IOException e) {
          System.out.println("An exception has occurred!");
          }
       //Getting file to read words from
       String sourceFile = "CommonWords.txt";
       //Calling method to remove common words
       try {  list.removeCommon(sourceFile);
       }
       catch (IOException e) {
       }
       //Creating ArrayList
       ArrayList<WordCount> listCheck = list.getList();
       //If statements to check is words were removed
       if (listCheck.get(0).getWord().equals("hey")) {
          System.out.println("Correct");
       }
       else {
          System.out.println("incorrect" + listCheck.get(0));
       }
    }
   //Method to test toString and see output is correct
   //takes no parameters and return is void
   public static void testToString() {
      //Creating wordCountList object to call the method on
      WordCountList wcl = new WordCountList();
      //trying to call method
      try {
         wcl.getWordsFromFile("Tester.txt");
      }
      //Catching the IOException
      catch (IOException e) {
         System.out.println("An exception has occurred!");
      }
      String toTest = "hey(3) what(2) is(3) up(1) ";
      //Checking if toString is equal to original text
      if (wcl.toString().equals(toTest)) {
         System.out.println("correct!");
      }
      //Otherwise printing out the toString
      else {
         System.out.println("incorrect " + wcl.toString());
      }
 }
   //Method to test topNWords for correct output.
   //takes no parameters and is void
   public static void testTopNWords() {
      //Creating WordCountList to call the getWordsFromFile method 
      WordCountList wcl = new WordCountList();
      //trying to call the method and catching exception
      try { 
         wcl.getWordsFromFile("Tester.txt");
      }
      catch (IOException e) {
         System.out.println("An exception has occurred!");
      }
      //Calling toNWords and hold the new ArrayList
      ArrayList<WordCount> list = wcl.topNWords(1);
      //If statement to check if word and count are correct
      if ((list.get(0).getWord().equals("hey")) && 
         (list.get(0).getCount() == 3)) {
         System.out.println("Correcto!");
      }
      //else it is incorrect
      else {
         System.out.println(list);
      }
   }  

 
    /** Write more unit tests above this provided tester */ 
    public static void providedTester(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Skipping provided tester because no arguments were given!");
            return;
        }
        
        // Command line usage:
        // java WordCloudTester nameOfInputFile.txt numberOfTopNWords {console|file} {length|char} {value}

        // init the word clound
        WordCountList w = new WordCountList();
        System.out.println("Reading in File: " + args[0]);
        w.getWordsFromFile(args[0]);

        System.out.println("Removing common words");
        w.removeCommon("inputFiles_DoNotSubmit/commonWords.txt");

        // get the input number of words you want
        int numberOfTopNWords = Integer.parseInt(args[1]);
        
        // condition ? a : b => if (condition == true) then a else b
        // parse the second argument to check if it is printToFile or not
        boolean printToFile = args[2].charAt(0) == 'f' ? true : false;
        // parse the third argument to check if it is filter by lengh or not
        
        System.out.println("Printing the top " + numberOfTopNWords + " words " + (printToFile == true ?
                " in file a named myOutput.out" : " on console"));
        
        
        WordCountList w2 = new WordCountList(w.topNWords(numberOfTopNWords));
        w2.outputWords(printToFile);

    }
}
