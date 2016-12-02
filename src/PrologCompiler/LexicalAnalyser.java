/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrologCompiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author programmer
 */
public class LexicalAnalyser {
    
    public static final String TAG = "LEXICAL ANALYSER";
    
    public static boolean startOfLine = true;
    public static boolean insideParents = false;
    
    public static boolean analyse() {
        if (SourceCode.LinesOfCode == null || SourceCode.LinesOfCode.size() <= 0) {
            Error.log(TAG, "Empty or null Array LinesOfCode", SourceCode.LinesOfCode);
            return false;
        }
        
        for (String line : SourceCode.LinesOfCode) {
            
            String temp = new String(line);
            
            startOfLine = true;
            
            while (temp.length() != 0) {
                String expression;
                
                if (getPredicate(temp) != null) {
                    startOfLine = false;
                    
                    expression = getPredicate(temp);
                    temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Predicate));
                }
            }
        }
        
        return true;
    }
    
    public static String getPredicate(String line){
        
        // return null of we are not in the start of line
        if (!startOfLine){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.PREDICATE);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getConstant(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || insideParents){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.CONSTANT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getVariable(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || insideParents){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.VARIABLE);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String matcher(String line, Pattern pattern) {
        
        // Match the pattern de the line of code
        Matcher match = pattern.matcher(line);
        
        // find first matching groupe 
        if (match.find()) {
            return match.group();
        }
        
        // return null if not matched
        return null;
    }
}
