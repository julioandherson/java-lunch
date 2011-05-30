/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Lucas
 */
public class NomeInvalidoException extends Exception{
    String msg= "Nome invalido";
    public NomeInvalidoException(){
        
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
