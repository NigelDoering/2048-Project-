Unix/Linux Questions:
1. To make directories with other directories in it simply use the command
    mkdir -p dir1/dir2...
2. To use a wildcard command you can use something like the astrick to
    compile all java files at once such as javac *.java since the astrick
    represents all the different string variations it will compile all java
    files in that directory.
3. To open all java source code files in a directory you could use gvim -p
    javaFile1 javaFile2...
Java Question:
1. Static means that a method has no calling object such as this. A good example
   of a static method would be Math.abs(double a) which returns the absolute
   value of the double passed in. This method is static since it doesn't need to
   access a calling object. 
2. I would tell this high schooler that she can use polymorphism to make it easier
   to construct different shapes. By defining shape in one class then passing
   down the data of a shape to another class she can simplify how to make
   shapes. For example, ARectangle class could inherit from shape and in
   ARectangle she could create the instance variable int width and int height
   then she could make a subclass of ARectangle called square and square can use
   most of the method defined in ARectangle, simply passing in the same value
   for width and height. 
