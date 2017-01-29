package programa;

import dados.Forma;
import dados.ValorInvalidoException;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

/**
 * Classe que testa as classes do projeto.
 * @author JOC, LAR, MRST e VRVS.
 */
public class Programa {

	public static void main(String[] args) {
		try {
			Forma forma = new Forma();
			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    
		    System.out.println("Testes de equações da reta: ");
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(1, 1, 2, 2)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(1, -1, 2, -2)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(1, 2, 2, 3)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(2, 1, 7, 1)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(2, 1, 3, 7)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(1, -1, 2, 2)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(0, 1, 1, -1)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(0.5, 0.3, 0, 0)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(0, -0.33, -0.33, 0)));
			System.out.println("\t" + forma.getEquacaoReta(forma.getReta(-0.5, 0, 0, (double)engine.eval("-1/3"))));
			//System.out.println("\t" + forma.getEquacaoReta(forma.getReta(1, 1, 1, 1)));	//Caso para pontos não distintos.
			
			System.out.println("\nTestes de equações da circunferência: ");
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(2, 2, 1, 1, 3, 1)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(1, 0, 0, 1, 0, -1)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(2, 3, -2, 0, 0, -7)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(1, 7, -3, 5, 4, -2)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(1, 7, 6, 2, 4, 6)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(2, 1, 1, 4, -3, 2)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(-1, 0, 1, 0, -1, -1)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(-1, -1, -1, 0, 0, -1)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(0, 0, 0, (double)engine.eval("-1/3"), (double)engine.eval("-1/3"), 0)));
			System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(0, 0, 4, 1, 1, 4)));
			//System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(1, 1, 1, 1, 2, 2)));		//Pontos 1 e 2 não distintos.
			//System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(0, 0, 1, 1, 1, 1)));		//Pontos 2 e 3 não distintos.
			//System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(0, 0, 2, 1, 0, 0)));		//Pontos 1 e 3 não distintos.
			//System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(1, 1, 2, 2, 3, 3)));		//Pontos colineares.
			//System.out.println("\t" + forma.getEquacaoCircunferencia(forma.getCircunferencia(3, 3, 4, 4, 4, 4)));		//pontos 2 e 3 não distintos e colineares.
			
			System.out.println("\nTestes de equações da conicas: ");
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(1, 1, 1, 2, 1, 3, 5, 1, 5, 2)));
			
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(0, 0, 1, 1, 4, 4, 3, 3, 2, 2)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(1, 1, 2, 1.0/2, 3, 1.0/3, 4, 1.0/4, 5, 1.0/5)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(0, 0, 1, 1, 1, -1, 4, -2, 4, 2)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica( 0, 0, 1, -1, -1, -1, 2, -4, 4, -16)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(1, -1, 2, -2, 3, -4, 5, -2, -4, 0)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(0, 0, 1, 1, 2, 8, -1, 1, 2, -8)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(8.025, 8.310, 10.170, 6.355, 11.202, 3.212, 10.736, 0.375, 9.092, -2.267)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(0, 0, 0, -1, 2, 0, 2, -5, 4, -1)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(-1, 0, 0, 1, 0, -1, 1, 0, (double)engine.eval("Math.sqrt(2)/2"), (double)engine.eval("Math.sqrt(2)/2"))));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(0, 0, 1, 1, -1, 1, 2, 4, 4, 16)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(-2, 0, 2, 0, 0, Math.sqrt(2), 0, -Math.sqrt(2), 1, -Math.sqrt(3.0/2))));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(1, 1, 2, 1.0/2, 3, 1.0/3, 4, 1.0/4, 5, 1.0/5)));
			System.out.println("\t" + forma.getEquacaoConica(forma.getConica(1, 1, 2, 2, 3, 3, 2, 1, 3, 2)));
			
		} catch (ValorInvalidoException e) {
			System.out.println("\t" + e.getMessage());
		} catch (ScriptException e) {
			System.out.println("\tA expressão não é valida!");
		}
	}
}
