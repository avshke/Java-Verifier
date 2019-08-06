# Java-Verifier

File description:
That program is a s-Java verifier. Checking the valid of a given program according to some roles.
The program uses Regular Expression for recognize lines.
  If the given program pass the tests the verifier will print 0.
  If there is a problem with one of the arguments the verifier will print 2 and the problem.
  If there is a running time problem the verifier will print 1.
 The program contains the follow files:
   1. Sjavac()- contains the main method which run the program.
        that method catch all the exception that were throwing during running.
   2. Analezer()- get the file with the code lines that need to be check.
        spiltFile() method compare each line in the document to different regexes and create an obj according to that.
        The optional lines are:
        - initialization method;
            In that case we creating new Method obj.
        - initialization variable;
            In that case we creating new Variable obj.
        - commend line;
        - calling to exist method;
        - assignment of exist variable;
       In case there is a line that not fit to non of this options exception will be throw.
   3. Scope()- when new scope starting (if\while\method) we creating a new scope() with the relevant lines.
        There are 2 classes which inherit from it:
     a. Condition()- in case the program recognize condition (if\while). in that class we checking if the condition
      is a valid boolean statement.
     b. Method()- contains an array with all his parameters() and variables().
   4. Variables()- when there is an initialization of a new variable we create an obj which contain name, type, value,
     and boolean fields of global\local and final.
     a. Parameter()- inherit from variable. created when a new method create.
   5. MyRegex()- contains all the regexes that we used in the program.
   6. Check()- This class contains all checks are taken outside the creation of the object or
               checks that are common for some classes.
               for example- the method checkValue is used for both- variables and scopes.
   7. IllegalException()- In case there is a running time problem we create a new IllegalException.

Oop Design: 
Our program read a file and divide it into small objects.
we worked with an exceptions error handling by using 2 types of Exceptions.
In addition, we used in heritage mechanism and abstract class.
We had an abstract class of Scope - each scope object can be one of 2 option - a Method or a Condition (if\while).
We also have a Variable class and a Parameter class that extends from the variable -
we treat parameter as a theoretical variable, therfore it had all attributes as a Variable, but with some difference
in some methods (overriding e.g: checkFinal) and some additional check we had to take in case of parameter.

Additional design patterns we used -
abstract class - Scope
Overriding methods - such as checkFinal
Overloading methods - such as the constructor of Variable object.

The main idea of our design was to split the file into smaller problems (Modularity) , thus each "check-method" we had was
handling exactly one problem it was needed to check.

