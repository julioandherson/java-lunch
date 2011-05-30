/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Lucas
 */
public class TipoComidaInvalidoException extends Exception {
    String msg="Tipo de comida invalida";
    public TipoComidaInvalidoException(){
       
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
