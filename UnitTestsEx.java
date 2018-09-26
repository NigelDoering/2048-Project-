
/* File Header
*/


/* Class header
*/

import java.util.*;
public class UnitTestsEx {
    // Instance Variables


    // Constructors


    /* Method header */
    public UnitTestsEx () {}




    // TODO Methods (Write your Unit Tests here.)



    /** exampleTestUsingPrinting

    Example test case. Uses println very often.

    May be useful if you prefer your unit test to 
    "speak" to you. 

    NOTE: The return type of this method is void. 
    All the results are printed to terminal. 
    

    */
    public void exampleTestUsingPrinting(){
        System.out.println("Unit Test Example. ");

        ExampleClass ec = new ExampleClass(); 

        String correctAnswer = "9y^2";
        String result = ec.derivative(3, 'y', 3);

        String correctAnswer2 = "5";
        String result2 = ec.derivative(5, 'x', 1);

        //The derivative of 5x^0 
        //The derivative of 5x
        //The answer should be 5. 

        //However, the function might return 0x^-1. That's not right. 
        //Fix that error in the method, and then rerun this tester.

    
        System.out.println(
            "The correct answer is:  " + correctAnswer + "\n" +
            "The answer received is: " + result + "\n"
            );

        System.out.println(
            "The correct answer is:  " + correctAnswer2 + "\n" +
            "The answer received is: " + result2 + "\n"
            );
    }



    /** exampleTestUsingExceptions

        If the program crashes due to the test case, an Exception
        will be thrown. However, here is the important difference:
        you may now edit your method and fix the code that causes
        the Exception. Then, when you run the test again, if the 
        Exception is not thrown anymore, then the code would be fixed. 

        Exceptions are also possibly useful in minimizing the amount
        of lines printed to the Terminal. 

        Note the return type is void. If a test case fails, then 
        an Exception will be thrown. Also note, that the example of
        throwing an Exception took in an argument: a String. 

        Run this method and see what the Exception prints out the Terminal. 
    */
    public void exampleTestUsingExceptions() throws Exception { 

        ExampleClass ec = new ExampleClass(); 

        String correctAnswer = "9y^2";
        String result = ec.derivative(3, 'y', 3);

        String correctAnswer2 = "5";
        String result2 = ec.derivative(5, 'x', 1);

        //The derivative of 5x^0 
        //The derivative of 5x
        //The answer should be 5. 

        //However, the function might return 0x^-1. That's not right. 
        //Fix that error in the method, and then rerun this tester.

        System.out.println("Running tests using Exception.");

        if ( !correctAnswer.equals(result) ){ 
            throw new Exception("First test failed.");
        }

        if ( !correctAnswer2.equals(result2) ) {
            throw new Exception("Second test failed.");
        }

    }


    /** Using print statements to debug. 
    
        There is one more way of testing your code in debugging.
        You can scatter print statements within your code.
        This allows you to see the values change as the code runs.
        This type of debugging is not a unit test, but it was worth
        mentioning that this technique exists.
        */





    // Main Method
    public static void main (String[] args) throws Exception {
        UnitTestsEx me = new UnitTestsEx();

        //Write your method calls below. 
        
        //me.exampleTestUsingPrinting();        //TODO Try me.
        //me.exampleTestUsingExceptions();      //TODO Try me.
    }
}



