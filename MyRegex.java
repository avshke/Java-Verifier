package oop.ex6.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MyRegex class.
 * contains all the regex used and a method that created a matcher for a given string and regex pattern.
 */

public class MyRegex {
    //Analyzer regex:
    public static final String METHOD_REGEX = "\\s*(void)\\s+([a-zA-Z]{1}\\w*)\\s*\\((.*)\\)\\s*\\{\\s*";
    public static final String VARIABLE_REGEX = "^(final\\s+)*\\s*([A-Za-z]+)\\s+(\\_+\\w+|[a-zA-Z]\\_*\\w*)\\s*(\\=+" +
            "\\s*(\\S+\\s*\\S*))*\\s*\\;\\s*";
    public static final String VAR_INITIALIZE = "(final\\s+)*\\s*([A-Za-z]+)\\s+(\\_+\\w+|[a-zA-Z]\\_*\\w*)\\s*\\=+" +
            "(\\s*\\\"?\\'?\\s*\\w+\\.*\\w*\\s*\\\"?\\'?)\\s*;";
    public static final String MULTIPLE_VAR_REGEX = "\\s*(final\\s+)*(\\w+)\\s+(.+\\s*\\,\\s*[^\\,()]+\\s*)*\\;\\s*";
    public static final String COMMENTLINE_REGEX = "\\/\\/.*";
    public static final String METHOD_SUFFIX = "return;";

    // Variable regex:
    public static final String STRING_VAR_REGEX = "^\\\"{1}[^\\d\\\"]*\\\"{1}$";
    public static final String INT_VAR_REGEX = "\\-?\\d+";
    public static final String DOUBLE_VAR_REGEX = "\\-?[\\d]+[\\.{1}\\d+]*";
    public static final String BOOLEAN_VAR_REGEX = "\\s*true|false|\\-?\\d+\\.{1}\\d+|\\-?\\d+\\s*";
    public static final String CHAR_VAR_REGEX = "\\'[\\D]\\'";
    public static final String MULTIPLE_VAR_NAME_REGEX = "(\\_+\\w+|[a-zA-Z]\\_*\\w*)\\s*\\=*\\s*(\\w+\\.*\\w*)*\\;*";
    public static final String VAR_ASSIGNMENT = "\\s*(\\_+\\w+|[a-zA-Z]\\_*\\w*)\\s*\\=\\s*([^\\s].*)\\s*\\;\\s*";
    public static final String VAR_NAME_REGEX = "\\_+\\w+|[a-zA-Z]\\_*\\w*";
    public static final String VAR_PREFIX = "final";

    // Scope regex:
    public static final String WHILE_IF_REGEX = "\\s*(if|while)\\s*\\((.+)\\)\\s*\\{\\s*";
    public static final String CONDITION_REGEX = "\\s*(\\-?\\w+[\\.+\\d]*)\\s*((\\|\\||&&)+\\s*\\-?\\w+[\\.+\\d]*\\s*)*";
    public static final String CONDITION_BOOLEAN_REGEX = "\\s*true|false|\\-?\\d+\\.{1}\\d+|\\-?\\d+|\\_+\\w+|[a-zA-Z]" +
            "\\_*\\w*\\s*";
    public static final String AND_OR_REGEX = "\\|\\||&&";

    //Method regex:
    public static final String CALL_METHOD_REGEX = "\\s*(.*)\\s*\\((.*)\\)\\s*\\;\\s*";
    public static final String PARAMETER_REGEX = "\\s*(final\\s+)*\\s*([A-Za-z]+)\\s+(\\_+\\w+|[a-zA-Z]\\_*\\w*)\\s*";



    /**
     * A method that create a Matcher object by a given string and regex pattern.
     * @param str   - the string we want to match
     * @param regex - the regex pattern (for createPattern helper)
     * @return Matcher object.
     */
    public static Matcher createMatcher(String str, String regex) {
        Matcher m = Pattern.compile(regex).matcher(str);
        m.matches();
        return m;
    }
}
