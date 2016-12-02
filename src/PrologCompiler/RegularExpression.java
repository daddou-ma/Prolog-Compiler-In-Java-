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
public class RegularExpression {
    public static final String VARIABLE     = "(^[A-Z]+)";
    public static final String CONSTANT     = "(^[a-z]+)";
    public static final String INTEGER     = "(^[0-9]+)";
    public static final String FLOAT     = "(^([0-9]+)[.]([0-9]+))";
    public static final String PREDICATE    = "^[a-zA-Z]+";
    public static final String OPEN_PARENT  = "^[(]"; 
    public static final String CLOSE_PARENT = "^[)]";
    public static final String COMMA = "^[,]";
    public static final String POINT = "^[.]";
    public static final String WHITE_SPACE = "^\\s";
}
