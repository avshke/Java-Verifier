//import java.util.ArrayList;
//
///**
// * Created by avital on 12/06/2017.
// */
//public class Scope {
//    private void scopeMaker(){
//        int open = 0, close = 0;
//        int i = 0;
//        ArrayList<String> scopeLines = new ArrayList<>();
//        while (i < codeLines.size()) {
//            Matcher variable = variableP.matcher(codeLines.get(i));
//            Matcher comment = commentP.matcher(codeLines.get(i));
//            Matcher condition = conditionP.matcher(codeLines.get(i));
//            if (variable.matches()) {
//                Variable var = new Variable(codeLines.get(i));
//                variables.add(var);
//                i++;
//            } else if (comment.lookingAt() || codeLines.get(i) == null)
//                i++;
//            else if (condition.matches()) {
//                i = ifWhile(i);
//            }
//            else {
//                throw new Exception();
//            }
//        }
//
//            private int ifWhile (int i){
//                int open = 0, close = 0;
//                ArrayList<String> scopeLines = new ArrayList<>();
//                if (true) {
//                    open++;
//                    while (open > close) {
//                        if (codeLine.get(i).contains("}")) {
//                            close++;
//                        } else if (codeLine.get(i).contains("{")) {
//                            open++;
//                        }
//                        scopeLines.add(codeLine.get(i));
//                        i++;
//                    }
//                }
//                return i;
//            }
//}
//        }