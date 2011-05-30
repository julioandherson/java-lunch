/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Lucas
 */
public class RestauranteInvalidoException extends Exception {
    String msg="Restaurante invalido";
    public RestauranteInvalidoException(){
        
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
