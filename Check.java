package oop.ex6.main;

import java.util.regex.Matcher;
import static oop.ex6.main.MyRegex.*;
import static oop.ex6.main.Variable.*;
import static oop.ex6.main.Analyzer.*;


/**
 * Check class.
 * This class contains all checks are taken outside the creation of the object or checks that are common for
 * some classes.
 * all methods in this class are static.
 */
public class Check {

    /**
     * A method that check if a variable's value match to variable's type (itself or compared var).
     * if so - return, otherwise throws an exception.
     * if value is null - it means it's a declaration.
     * @param value -  a string of the value
     * @param type  - a string of the type.
     * @throws IllegalException - in case value does not much or not valid.
     */
     public static void checkValue(String value, String type, Scope scope) throws IllegalException {
        if (value == null)
            return;
        Matcher intm = createMatcher(value.trim(), INT_VAR_REGEX);
        Matcher doublem = createMatcher(value.trim(), DOUBLE_VAR_REGEX);
        Matcher strm = createMatcher(value.trim(), STRING_VAR_REGEX);
        Matcher charm = createMatcher(value.trim(), CHAR_VAR_REGEX);
        Matcher bolm = createMatcher(value.trim(), BOOLEAN_VAR_REGEX);
        Matcher namem = createMatcher(value.trim(), VAR_NAME_REGEX);
        if (type.equals(STRING) && strm.matches())
            return;
        if (type.equals(INT) && intm.matches())
            return;
        if (type.equals(DOUBLE) && doublem.matches())
            return;
        if (type.equals(BOOLEAN) && bolm.matches())
            return;
        if (type.equals(CHAR) && charm.matches())
            return;
        if (namem.matches()) checkInitializeWithOtherVar(value, type, scope);
        else throw new IllegalException();
    }

    /**
     * A method that check whether a variable exists with a given name in outer scopes,
     * @param nameVar - the variable's name we are looking for
     * @param scope   - the scope we start from searching outer
     * @return Variable if exists, null if no.
     */
    public static Variable findVarInOuterScopes(String nameVar, Scope scope) {
        if (scope != null) {
            Scope cur = scope;
            while (cur != null) {  //when father is null it means we've reached to the global scope.
                for (Variable var : cur.getVariables()) {
                    if (var.getName().equals(nameVar)) return var;
                }
                cur = cur.getFather();
            }
        }
        // check for global scope.
        for (Variable var : globalVarArray) {
            if (var.getName().equals(nameVar.trim())) return var;
        }
        return null;
    }

    /**
     * A method that check whether an assignment is valid.
     * it check if the variable was already declared , not final and check whether the value of the assignment
     * match the type ot the var.
     * works for patterns as: a = 7, a =b;
     * @param assign - the string of the assignment line.
     * @param scope  - the scope the assignment was done.
     * @throws IllegalException - if one of those checks is not valid.
     */
    public static void checkAssignment(String assign, Scope scope) throws IllegalException {
        Matcher assignM = createMatcher(assign, VAR_ASSIGNMENT);
        Parameter param = findMethodParameter(assignM.group(1).trim(), scope);
        if (param != null) {
            if (param.isFinal()) throw new IllegalException();
        } else {
            Variable var = findVarInOuterScopes(assignM.group(1).trim(), scope);
            if (var != null) {
                if (!var.isFinal()) {
                    if (var.isGlobal()) {
                        Variable newVar = new Variable(assign, var.getType(), null, scope);
                        newVar.setGlobal();
                        scope.setVariables(newVar);
                    }
                    if (createMatcher(assignM.group(2).trim(), VAR_NAME_REGEX).matches()) { // case a=b.
                        checkInitializeWithOtherVar(assignM.group(2).trim(), var.getType(), scope);
                    } else {
                        checkValue(assignM.group(2).trim(), var.getType(), scope);  // case a = 7;
                        return;
                    }
                }
            }
            throw new IllegalException();
        }
    }


    /**
     * A method that check whether an assignment of other variable as value is legal -
     * check whether the value-var exists and initialized, then check if the value match the type.
     * @param valueVar - a value var we want to check
     * @param type     - the variable we want to assign the value to.
     * @param scope    - the scope we are current in.
     * @throws IllegalException - in case the assignment is not valid.
     */
    public static void checkInitializeWithOtherVar(String valueVar, String type, Scope scope) throws IllegalException {
        Parameter findParam = findMethodParameter(valueVar, scope);
        if (findParam != null){
            if(type.equals(findParam.getType())) return;
        }
        Variable findVar = findVarInOuterScopes(valueVar, scope);
        if (findVar != null) {
            if (findVar.getValue() != null) {
                checkValue(findVar.getValue(), type, scope);
                return;
            }
        }
        throw new IllegalException();
    }

    /**
     * a method that checks whether a method parameter exists.
     * @param nameVar the name of the parameter we are looking for.
     * @param scope the scope we are currently in.
     * @return the parameter if it was found, null otherwise.
     */
    public static Parameter findMethodParameter(String nameVar, Scope scope) {
        if (scope != null) {
            Scope cur = scope;
            while (cur.getFather() != null) {  //when father is null it means we've reached to the global scope.
                cur = cur.getFather();
            }
            if (scope instanceof Method) {
                Method scopeMeth = (Method) scope;
                for (Parameter param : scopeMeth.getParameters()) {
                    if (param.getName().equals(nameVar)) {
                        return param;
                    }
                }
            }
        }return null;
    }


}