/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Lucas
 */
public class TipoPreferenciaInvalidoException extends Exception {
    String msg="Preferencia invalida";
    public TipoPreferenciaInvalidoException(){
        
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
