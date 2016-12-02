/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrologCompiler;

import static PrologCompiler.LexicalAnalyser.TAG;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author programmer
 */
public class SyntaxicalAnalyser {
    
    public static final String TAG = "SYNTAXICAL_ANALYSER";
    
    public static LinkedList<Token> tokens;
    
    public static Token lastToken;
    public static Token currentToken;
    
    public static boolean analyse() {
        
        tokens = new LinkedList(SourceCode.tokens);
        
        nextToken();
        
        if (currentToken.type == TokenType.Predicate) {
            fact();
            if (currentToken.type == TokenType.EOT) {

            }
            else {
                unexpectedExpression("[ End Of Code ]");
            }
        }
        else {
            unexpectedExpression("[ Predicate ]");
        }
        return true;
    }
    
    public static void fact() {
        if (currentToken.type == TokenType.Predicate) {
            nextToken();
            A();
            
            if (currentToken.type == TokenType.Predicate) {
                fact();
            }
            else if (currentToken.type == TokenType.Point) {
                nextToken();
                if (currentToken.type == TokenType.EOL) {
                    nextToken();
                }
                else {
                    unexpectedExpression("[ End Of Line ]");
                }
            }
            else {
                unexpectedExpression("[ Predicate || Point(.) ]");
            }
        }
        else {
            unexpectedExpression("[ Predicate ]");
        }
    }
    
    
    
    public static void A() {
        
        if (currentToken.type == TokenType.OpenParent) {
            
            nextToken();
            
            if (currentToken.type == TokenType.CloseParent) {
                //TODO: end of line
            }
            else if (currentToken.type == TokenType.Variable || currentToken.type == TokenType.Constant ||
                currentToken.type == TokenType.Integer || currentToken.type == TokenType.Float) {
                C();
                B();
            }
            else {
                unexpectedExpression("[Variable || Contant || Integer || Float || ) ]");
            }
        }
        else {
            unexpectedExpression("[ ( ]");
        }
    }
    public static void B() {
        if (currentToken.type == TokenType.Comma) {
            //TODO: end of line
            nextToken();
            if (currentToken.type == TokenType.Variable || currentToken.type == TokenType.Constant ||
                currentToken.type == TokenType.Integer || currentToken.type == TokenType.Float) {
                // TODO: read one of them
                C();
                B();
            }
            else {
                unexpectedExpression("[ Variable || Contant || Integer || Float ]");
            }
        }
        else if (currentToken.type == TokenType.CloseParent) {
            nextToken();
        }
        else {
            unexpectedExpression("[ Variable || Contant || Integer || Float || ) ]");
        }
    }
    public static void C() {
        if (currentToken.type == TokenType.Variable || currentToken.type == TokenType.Constant ||
            currentToken.type == TokenType.Integer || currentToken.type == TokenType.Float) {
            // TODO: read one of them
            nextToken();
        }
        else {
            unexpectedExpression("[ Variable || Contant || Integer || Float ]");
        }
    }
    
    public static void nextToken() {
        lastToken = currentToken;
        currentToken = tokens.pop();
    }
    
    public static void unexpectedExpression(String expected){
        Error.log(TAG, "[Unexpected Expression] : 'after " + lastToken.expression + "' -> '" + currentToken.expression+"'", expected);
    }
}
