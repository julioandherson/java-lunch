/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Lucas
 */
public class EnderecoInvalidoException extends Exception{
    String msg="Endereco invalido";
    public EnderecoInvalidoException(){
        
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
