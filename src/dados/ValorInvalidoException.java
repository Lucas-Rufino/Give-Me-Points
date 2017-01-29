package dados;

/**
 * Classe de excecao para tratamento de valores invalidos.
 * @author JOC, LAR, MRST e VRVS.
 */
public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = -4319634173509645222L;

	/**
	 * Metodo construtor da excecao de valor invalido.
	 * @param texto String - descricao da variavel que receberia o valor invalido.
	 */
	public ValorInvalidoException(String texto){
		super("Valor invalido para " + texto + "!");
	}
}
