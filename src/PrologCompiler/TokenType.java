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
public enum TokenType {
    Variable, Constant, Predicate, Negation, OpenParent, CloseParent,
    Comma, Point, Integer, Float, String, Operator, EOL, EOT
}
