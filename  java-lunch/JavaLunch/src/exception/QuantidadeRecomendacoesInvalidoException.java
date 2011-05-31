/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Lucas
 */
public class QuantidadeRecomendacoesInvalidoException extends Exception{
    String msg="Quantidade de recomendacoes inavalida";
    public QuantidadeRecomendacoesInvalidoException(){
        
    }
    @Override
    public String getMessage(){
        return msg;
    }
}
