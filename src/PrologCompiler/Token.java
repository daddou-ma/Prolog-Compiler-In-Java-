/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrologCompiler;

/**
 *
 * @author programmer
 */
public class Token {
    public String       expression;
    public TokenType    type;
    
    public Token(String expression, TokenType type) {
        this.expression = expression;
        this.type       = type;
    }
}
