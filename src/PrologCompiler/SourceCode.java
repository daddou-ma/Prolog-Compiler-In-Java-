/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrologCompiler;

import java.util.ArrayList;

/**
 *
 * @author programmer
 */
public class SourceCode {
    
    public static final String TAG = "SOURCE_CODE";
    
    public static ArrayList<String> LinesOfCode = new ArrayList();
    public static ArrayList<Token> tokens = new ArrayList();
    
    public static void printTokens() {
        System.out.println(TAG + " : \t Print Tokens");
        
        for(Token token : tokens) {
            System.out.println(token.type + " : \t" + token.expression);
        }
        System.out.println();
    }
}
