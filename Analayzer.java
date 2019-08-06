//package oop.ex6.main;
//import oop.ex6.main.Variable.Variable;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.regex.*;
//
//
//public class Analayzer {
//    public static final String METHOD_REGEX = "(void)\\s+([a-zA-Z]{1}[a-zA-Z0-9]+)\\s*\\((.*)\\)\\s*\\{";
//    public static final String VARIABLE_REGEX = "^(final\\s)([a-z]+)\\s+([\\w]+)\\s\\=\\s(\\w+\\.\\w)*\\;";
//    public static final String COMMENTLINE_REGEX = "^\\\\.*";
//    public static final String OPEN = "{", CLOSE= "}";
//    public static Pattern methodP = Pattern.compile(METHOD_REGEX);
//    public static Pattern variableP = Pattern.compile(VARIABLE_REGEX);
//    public static Pattern commentP = Pattern.compile(COMMENTLINE_REGEX);
//
//    ArrayList<Method> methodsArray = new ArrayList<>();
//    ArrayList<Variable> globalVarArray = new ArrayList<>();
////    ArrayList<String> commentLine = new ArrayList<>();
//
//    /**
//     * method that read a given file and split if into objects
//     */
//    private void splitFile(File file) throws IOException, IllegalException {
//        FileReader commandsFile = new FileReader(file);
//        BufferedReader reader = new BufferedReader(commandsFile);
//        String line = reader.readLine();
//        while (line != null ) {
//            Matcher method = methodP.matcher(line);
//            Matcher variable = variableP.matcher(line);
//            Matcher comment = commentP.matcher(line);
//            if(method.matches()){
//                ArrayList<String> codeLines = methodMaker(reader, line);
//                Method meth = new Method(codeLines);
//                methodsArray.add(meth);
//                line = reader.readLine();
//            }else if(variable.matches()){
//                Variable var = new Variable(line);
//                var.setGlobal();
//                globalVarArray.add(var);
//                line = reader.readLine();
//            }else if (comment.lookingAt() || (line.isEmpty())){
//                line = reader.readLine();
//            }
//            else throw new IllegalException();
//        }
//    }
//
//    /**
//     * A method that
//     * @param reader
//     * @param line
//     * @return
//     * @throws IOException
//     */
//    private ArrayList<String > methodMaker(BufferedReader reader, String line) throws IOException, IllegalException {
//        ArrayList<String> codeLines = new ArrayList<>();
//        int open = 0, close = 0;
//        codeLines.add(line); // the signature of the method.
//        open++;
//        line = reader.readLine();
//        while (open > close && line != null ){
//            codeLines.add(line);
//            if (line.contains(OPEN)) {
//                open++;
//            } else if(line.contains(CLOSE)){
//                close++;
//            }
//            line = reader.readLine();
//        }
//        if(open > close){
//            throw new IllegalException();
//        }
//        // in case the method is not valid. not having return ending.
//        if(!codeLines.get(codeLines.size()- 2).equals("return;"))
//            throw new IllegalException();
//        return codeLines;
//    }
//}