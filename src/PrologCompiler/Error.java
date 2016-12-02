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
public class Error {
    
    /*
    * In case of empty array of SourceCode.linesOfCode or null value 
    */
    public static void log(String tag, String error, Object obj) {
        System.out.println(tag + " : \t" + error);
        System.out.println("\t" + obj.toString() + "\n");
    }
}
