//---File/Class Header---
//Name:Nigel Doering 
//Email: nfdoerin@ucsd.edu
//cs8b account: cs8bwaat
//Student ID: A14445032
//This file contains the WordCountList class. The WordCountList class is used
//to read a document and find how many times a word is repeated. The class 
//also removes common words, prints the words into a new file, and can 
//recall the top most occurring words in the file.

import java.util.*;
import java.io.*;

public class WordCountList {
  // The ArrayList to store the words and their associated counts
  ArrayList<WordCount> list;
  // construct the list
  public WordCountList() {
    list = new ArrayList<WordCount>();
  }
  public WordCountList(ArrayList<WordCount> list) {
      this.list = list;
  }

  // This method will read all the words from the source file and properly
  // update the list
  // @param filename is the name of the file to be read
  // returns nothing
  public void getWordsFromFile( String fileName ) throws IOException {
   //Creating file for scanner to read
   File myFile = new File(fileName);
   //Creating a scanner to read a document 
   Scanner input = new Scanner(myFile);
   //Creating an ArrayListObject to hold wordCount objects
   ArrayList<WordCount> list = this.list;
   //Looping through each word in the filename
   while (input.hasNext()) {
      Boolean test = true;
      //Creating string word to hold the word in filename
      String word = input.next();
      //Looping through all the WordCount object in list
      for (int i = 0; i < list.size(); i++) {
         //If word is inside list array then increment the number
         if (list.get(i).getWord().equalsIgnoreCase(word)) {
            list.get(i).increment(); 
            test = false;
         }
      }
      //If the list does not contain the word then add the word
      if (test) {
         list.add(new WordCount(word));
      }
   }
  }

  // An accessor method for the tester file 
  public ArrayList<WordCount> getList(){
    return list;
  }
  
  // This method will remove any commonly used words from
  // the list. 
  // @param omitFilename is the name of the file with common words
  // that are to be removed
  // returns nothing
  public void removeCommon( String omitFilename ) throws IOException {
     //Creating file for scanner to read
     File myFile = new File(omitFilename);
     //Creating scanner for reading the file
     Scanner input = new Scanner(myFile);
     //Creating ArrayList to change values
     ArrayList<WordCount> list = this.getList();
     //While loop to loop through every word
     while (input.hasNext()) {
        //Creating string varaible to hold the current word
        String word = input.next();
        //Looping through the list
        for (int i = 0; i < list.size(); i++) {
           //If the word is in the list then remove it
           if (list.get(i).getWord().toLowerCase().equalsIgnoreCase(word)) {
              list.remove(i);
              i = 0;
           }
        }
     }
  }

  // This method finds the top n occurring words in the
  // list with lengths>= length and returns it as an ArrayList.  In the event
  // of a tie, use the first occurring
  // word with that count.
  // @param n is the top most words that occur
  // @return ArrayList<WordCount> returns an arrayList that holds
  // the top most occurring words
  public ArrayList<WordCount> topNWords(int n) {
    //Creating a new ArrayList to simplify commands
    ArrayList<WordCount> list = this.getList();
    //If statement checking if there are enough words for N top
    if (n > list.size()) {
       return list; 
    }
    //Creating new ArrayList to hold the topNWords
    ArrayList<WordCount> toReturn = new ArrayList<WordCount>();
    //Looping through n amount and the entire size length
    for (int i = 0; i < n; i++) {
       //Variable maxInd to hold the place of the highest occurring word
       int maxInd = 0;
       //max holds the highest count value
       int max = 0;
       //Looping through list
       for (int j = 0; j < list.size()- 1; j++) {
          //Finding the max value
          if (max < list.get(j).getCount()) {
             maxInd = j;
             max = list.get(j).getCount();
          }
          }
          //Adding the word at the maxInd to the toReturn arrayList
          toReturn.add(list.get(maxInd));
          //Making the index of highest value negative 
          list.get(maxInd).setCount(-(list.get(maxInd).getCount()));
       }
    //Calling method to make each count in list positive
    list = makePositive(list);
    //Returning the topNWords
    return toReturn;
  }

  // This method takes the WordCounts and outputs the words and their
  // counts as a String.
  // This method takes no parameters
  // @return String this returns a String of the words and their counts
  public String toString() {
    //getting the list of WordCount variables
    ArrayList<WordCount> list = this.getList();
    //Creating a new empty string to hold my values
    String newList = "";
    //Looping thrpugh each variable in newList
    for (int i = 0; i < list.size(); i++) {
       //new String to hold the word
       String temp = list.get(i).getWord();
       //Creating an int to hold count
       int num = list.get(i).getCount();
       //String to help formatting 
       String a = "(";
       //String to help formatting
       String b = ") ";
       //Creating new String to cast int to string
       String temp2 = Integer.toString(num);
       //Concatening a string to look the same as format
       String change = temp + a + temp2 + b;
       //Adding new String to newList
       newList = newList + change;
    }
    //Returning the new list
    return newList;
  }
  
  //This method takes in an ArrayList of WordCounts and a boolean
  //printToFile. If printToFile is true, it should output the 
  //arraylist of wordpair with their counts to a file named 
  //myOutput.out . If printToFile is false, it should print the 
  //arraylist of wordpair with their counts on the console
  //@param printToFile is a boolean to decide whether or not to print
  //@this method returns nothing.
  public void outputWords(boolean printToFile) throws IOException{
     String test = this.toString();
     System.out.println("wjhvuioiv");
  //If statement for if the boolean statement is false
  if (printToFile) {
     //Creating new PrintWriter to print to file
     PrintWriter myWriter = new PrintWriter("myOutput.out");
     //Printing the toString output to file
     myWriter.print(test);
     //Closing the PrintWriter
     myWriter.close();
  }
  else {
     System.out.println(test);
  }  
}
  //This is a static method that takes an ArrayList<WordCount> and makes
  //each count of every WordCount positive. It returns the positive list.
  public static ArrayList<WordCount> makePositive(ArrayList<WordCount> list) {
     //Looping through the list
     for (int i = 0; i < list.size(); i++) {
        //If the count is negative then make it positive
        if (list.get(i).getCount() < 0) {
           list.get(i).setCount(-(list.get(i).getCount())); 
        }
     }
     //Returning the all positive list
     return list;
  
  }

}

