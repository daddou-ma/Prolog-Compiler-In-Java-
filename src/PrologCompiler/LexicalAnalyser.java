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
    public static int openParents = 0;
    public static boolean isLastEqualComma = false;
    public static boolean isLastEqualPoint = false;
    
    public static boolean analyse() {
        if (SourceCode.LinesOfCode == null || SourceCode.LinesOfCode.size() <= 0) {
            Error.log(TAG, "Empty or null Array LinesOfCode", SourceCode.LinesOfCode);
            return false;
        }
        
        for (String line : SourceCode.LinesOfCode) {
            
            String temp = new String(line);
            
            startOfLine = true;
            
            while (temp.length() > 0) {
                String expression;
                
                if (temp.length() == 0) {
                    //TODO: add endOfLine Token
                    if (!isLastEqualPoint)
                    {   
                        unexpectedExpression(temp, SourceCode.LinesOfCode.indexOf(line));
                        return false;
                    }
                    
                    SourceCode.tokens.add(new Token("", TokenType.EOF));
                    System.out.println("End Of Line");
                }
                else if (getWhiteSpace(temp) != null) {
                  
                    expression = getWhiteSpace(temp);
                    
                    temp = temp.substring(expression.length());
                }
                else if (getPredicate(temp) != null) {
                  
                    expression = getPredicate(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Predicate));
                    
                    startOfLine = false;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getVariable(temp) != null) {
                  
                    expression = getVariable(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Variable));
                    
                    startOfLine = false;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getConstant(temp) != null) {
                  
                    expression = getConstant(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Constant));
                    
                    startOfLine = false;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getFloat(temp) != null) {
                  
                    expression = getFloat(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Float));
                    
                    startOfLine = false;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getInteger(temp) != null) {
                  
                    expression = getInteger(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Integer));
                    
                    startOfLine = false;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getOpenParent(temp) != null) {
                  
                    expression = getOpenParent(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.OpenParent));
                    
                    openParents++;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getCloseParent(temp) != null) {
                  
                    expression = getCloseParent(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.CloseParent));
                    
                    openParents--;
                    isLastEqualComma = isLastEqualPoint = false;
                }
                else if (getComma(temp) != null) {
                  
                    expression = getComma(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Comma));
                    
                    isLastEqualComma = true;
                }
                else if (getPoint(temp) != null) {
                  
                    expression = getPoint(temp);
                    
                    temp = temp.substring(expression.length());
                    SourceCode.tokens.add(new Token(expression, TokenType.Point));
                    
                    isLastEqualPoint = true;
                }
                else {
                    unexpectedExpression(temp, SourceCode.LinesOfCode.indexOf(line));
                    return false;
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
        if (startOfLine || openParents <= 0){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.CONSTANT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getVariable(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || openParents <= 0){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.VARIABLE);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getInteger(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || openParents <= 0){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.INTEGER);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getFloat(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || openParents <= 0){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.FLOAT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getOpenParent(String line){
        
        // return null of we are not in the start of line
        if (startOfLine){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.OPEN_PARENT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getCloseParent(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || openParents == 0){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.CLOSE_PARENT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getComma(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || isLastEqualComma){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.COMMA);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    
    public static String getPoint(String line){
        
        // return null of we are not in the start of line
        if (startOfLine || isLastEqualComma){
            return null;
        }
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.POINT);
        
        // matching the pattern with line and return result
        return matcher(line, pattern);
    }
    public static String getWhiteSpace(String line){
        
        // Compile the variable pattern
        Pattern pattern = Pattern.compile(RegularExpression.WHITE_SPACE);
        
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
    
    public static void unexpectedExpression(String expression, int line){
        Error.log(TAG, "[Unexpected Caracter] : '" + expression + "' -> line (" + line + ")", SourceCode.LinesOfCode.get(line));
    }
}
