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
public class PrologCompiler {

    /**
     * @param args the command line arguments
     */
    public static final String VARIABLE = "(^[A-Z]+)";
    public static final String CONSTANT = "(^[a-z]+)";
    public static final String PREDICATE = "^[a-zA-Z]+";
    
    public static void main(String[] args) {
        
        // Our line of code
        String line = "eat(ali, 18.00).";
        
        SourceCode.LinesOfCode.add(line);
        
        if (LexicalAnalyser.analyse())  {
            SyntaxicalAnalyser.analyse();
            SourceCode.printTokens();
        }
    }
    
}
