package oop.ex6.main;

import java.io.File;
import java.io.IOException;

/**
 * Sjavac class.
 * have only the main method that runs all the checkes.
 */
public class Sjavac {
    private static final int NUM_OF_ARGS = 1;
    private static final int LEGAL_CODE = 0, ILLEGAL_CODE = 1, IO_EXCEPTION = 2;
    private static final String WRONG_INPUT = "Wrong number of arguments.";
    private static final String INVALID_FILE = "File not found.";
    private static final String ILLEGAL_EXCEPTION_MSG = "The program encountered a problem and can not continue.";

    /**
     * the main method.
     * gets a file path and call the analyzer to read the file and checking it.
     * if the file is legal - it will print "0" to the screen.
     * in case of thrown exception it will catch it here.
     * if it's an IOException - it will print "2" to the screen and an informative message (wrong args \ file not found)
     * if it's an IllegalException - it will print "1" to the screen and a message that the file is not legal.
     * @param args - the file path.
     */
    public static void main(String[] args) {
        if (args.length != NUM_OF_ARGS) {
            System.out.println(IO_EXCEPTION);
            System.err.println(WRONG_INPUT);
        }
        try {
            File file = new File(args[0]);
            Analyzer analyzer = new Analyzer();
            analyzer.initStaticArrays();
            analyzer.splitFile(file);
            System.out.println(LEGAL_CODE);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
            System.err.println(INVALID_FILE);
        } catch (IllegalException e) {
            System.out.println(ILLEGAL_CODE);
            System.err.println(ILLEGAL_EXCEPTION_MSG);
        }
    }
}


