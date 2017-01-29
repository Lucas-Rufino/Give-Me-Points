package dados;

/**
 * Classe que define o funcionamento do objeto Forma.
 * @author JOC, LAR, MRST e VRVS.
 */
public class Forma {
	
	/**
	 * Metodo que recebe 2 pontos do plano xy e retorna os coeficientes da equacao geral da reta
	 * que comtem esses pontos 
	 * @param x1 double - coordenada x do primeiro ponto
	 * @param y1 double - coordenada y do primeiro ponto
	 * @param x2 double - coordenada x do segundo ponto
	 * @param y2 double - coordenada y do segundo ponto
	 * @return double[] - conjunto de coeficientes da equação geral da reta.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public double[] getReta(double x1, double y1, double x2, double y2) throws ValorInvalidoException{
		double[][] pts = {{x1, y1}, {x2, y2}};		//coloca os pontos em um array
		double[] consts = new double[3];			//inicia os valores da saida
		
		//teste para pontos distintos.
		if(pts[0][0] == pts[1][0] && pts[0][1] == pts[1][1]){
			throw new ValorInvalidoException("os pontos 1 e 2. Não são distintos");
		}
		
		//Excecucão do algoritmo
		consts[0] = pts[1][1] - pts[0][1];
		consts[1] = pts[0][0] - pts[1][0];
		consts[2] = pts[0][1]*pts[1][0] - pts[1][1]*pts[0][0];
		
		//Normalizacao dos valores da saida.
		if(consts[0] != 0){
			if(consts[0] != 1){
				consts[2] /= consts[0];
				consts[1] /= consts[0];
				consts[0] /= consts[0];
			}
		} else {
			if(consts[1] != 1){
				consts[2] /= consts[1];
				consts[0] /= consts[1];
				consts[1] /= consts[1];
			}
		}
		
		//retirada de valores decimais por valores multiplos, com aproximacao de 3 decimais.
		for(int i=1 ; i<3 ; i++){
			int multi = 1;
			double aux = consts[i];
			while((aux - (int)aux < -0.0000000000001 || aux - (int)aux > 0.0000000000001) && multi != 1000){
				aux = consts[i]*++multi;
			}
			consts[0] *= multi;
			consts[1] *= multi;
			consts[2] *= multi;
			if(multi == 1000){
				for(int z=0 ; z<3 ; z++){
					consts[z] = (double)Math.round(consts[z])/1000;
				}
			}
		}
		
		//Arredondamento dos valores normalizados
		for(int i=0 ; i<3 ; i++){
			consts[i] = Math.round(consts[i]);
		}
		
		return consts;
	}
	
	/**
	 * Metodo que recebe as constantes da equacao da reta e converte para uma texto formatado.
	 * @param consts double[] - constantes da equação da reta.
	 * @return String - equação da reta formatada.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public String getEquacaoReta(double[] consts) throws ValorInvalidoException {
		String str = "";	//Inicia a string da formula
		
		//Restrição para teste de das constantes
		if(consts.length != 3 || (consts[0] == 0 && consts[1] == 0)){
			throw new ValorInvalidoException("o conjunto de constantes. Não se refere a uma reta");
		}
		
		//Inicio do processo de formatação
		if(consts[0] != 0){
			if(consts[0] < 0){
				str = str + "- ";
			}
			if(consts[0] == 1 || consts[0] == -1){
				str = str + "x ";
			} else {
				str = str + (int)Math.abs(consts[0]) + "x ";
			}
		}
		if(consts[1] != 0){
			if(consts[1] < 0){
				str = str + "- ";
			} else {
				if(consts[0] != 0){
					str = str + "+ ";
				}
			}
			if(consts[1] == 1 || consts[1] == -1){
				str = str + "y ";
			} else {
				str = str + (int)Math.abs(consts[1]) + "y ";
			}
		}
		if(consts[2] != 0){
			if(consts[2] < 0){
				str = str + "- ";
			} else {
				str = str + "+ ";
			}
			str = str + (int)Math.abs(consts[2]) + " ";
		}
		str = str + "= 0";
		
		return str;
	}
	
	/**
	 * Metodo que recebe 3 pontos do plano xy e retorna os coeficientes da equacao geral da circunferencia
	 * que comtem esses pontos
	 * @param x1 double - coordenada x do primeiro ponto
	 * @param y1 double - coordenada y do primeiro ponto
	 * @param x2 double - coordenada x do segundo ponto
	 * @param y2 double - coordenada y do segundo ponto
	 * @param x3 double - coordenada x do terceiro ponto
	 * @param y3 double - coordenada y do terceiro ponto
	 * @return double[] - conjunto de coeficientes da equação geral da reta.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public double[] getCircunferencia(double x1, double y1, double x2, double y2, double x3, double y3) throws ValorInvalidoException{
		double[][] pts = {{x1, y1},{x2, y2},{x3, y3}};		//coloca os pontos em um array
		double[] consts = new double[4];					//Inicio os valores da saida
		
		//Teste para valores não distintos
		for(int i=0 ; i<3 ; i++){
			for(int j=i+1 ; j<3 ; j++){
				if(pts[i][0] == pts[j][0] && pts[i][1] == pts[j][1]){
					throw new ValorInvalidoException("os pontos " + (i+1) + " e " + (j+1) + ". Não são distintos");
				}
			}
		}
		
		//Teste de pontos colineares
		double[] auxConsts = this.getReta(pts[0][0], pts[0][1], pts[1][0], pts[1][1]);
		if(auxConsts[0]*pts[2][0] + auxConsts[1]*pts[2][1] + auxConsts[2] == 0){
			throw new ValorInvalidoException("os pontos 1, 2 e 3. São colineares");
		}
		
		//Limitacao para pontos pertecente a mesma assintota.
		if(pts[1][1] == pts[0][1]){
			double[] aux = pts[1];
			pts[1] = pts[2];
			pts[2] = aux;
		}
		
		//Etapa Inicial.
		double c1 = Math.pow(pts[0][0], 2) + Math.pow(pts[0][1], 2);
		double c2 = Math.pow(pts[1][0], 2) + Math.pow(pts[1][1], 2);
		double c3 = Math.pow(pts[2][0], 2) + Math.pow(pts[2][1], 2);
		
		//Etapa segunda linha.
		double d = pts[1][1] - pts[0][1];
		double e = pts[1][0] - pts[0][0];
		double f = c2 - c1;
		
		//Etapa terceira linha.
		double g = d*(pts[2][0] - pts[0][0]) - e*(pts[2][1] - pts[0][1]);
		double h = d*(c3 - c1) - f*(pts[2][1] - pts[0][1]);
		
		//Obtenção das constantes.
		consts[0] = d*g;
		consts[1] = -d*h;
		consts[2] = e*h - f*g;
		consts[3] = (pts[0][0]*d - pts[0][1]*e)*h + (pts[0][1]*f - c1*d)*g;
		
		//Normalização de valores
		consts[3] /= consts[0];
		consts[2] /= consts[0];
		consts[1] /= consts[0];
		consts[0] /= consts[0];
		
		//Retirada de decimais por multiplos
		for(int i=1 ; i<4 ; i++){
			int multi = 1;
			double aux = consts[i];
			while((aux - (int)aux < -0.0000000000001 || aux - (int)aux > 0.0000000000001) && multi != 1000){
				aux = consts[i]*++multi;
			}
			consts[0] *= multi;
			consts[1] *= multi;
			consts[2] *= multi;
			consts[3] *= multi;
			if(multi == 1000){
				for(int z=0 ; z<4 ; z++){
					consts[z] = (double)Math.round(consts[z])/1000;
				}
			}
		}
		
		//Arredondamento de valores decimais
		for(int i=0 ; i<4 ; i++){
			consts[i] = Math.round(consts[i]);
		}
		
		return consts;
	}
	
	/**
	 * Metodo que recebe as constantes da equacao da circunferencia e converte para uma texto formatado.
	 * @param consts double[] - constantes da equação da circunferencia.
	 * @return String - equação da circunferencia formatada.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public String getEquacaoCircunferencia(double[] consts) throws ValorInvalidoException {
		String str = "";	//Inicia a string da formula
		
		//Restrição para teste das constantes
		if(consts.length != 4 || consts[0] == 0){
			throw new ValorInvalidoException("o conjunto de constantes. Não se refere a uma reta");
		}
		
		//Inicio do processo de formatação
		if(consts[0] < 0){
			str = str + "- ";
		}
		if(consts[0] == 1 || consts[0] == -1){
			str = str + "xe2 ";
		} else {
			str = str + (int)Math.abs(consts[0]) + "xe2 ";
		}
		if(consts[0] < 0){
			str = str + "- ";
		} else {
			str = str + "+ ";
		}
		if(consts[0] == 1 || consts[0] == -1){
			str = str + "ye2 ";
		} else {
			str = str + (int)Math.abs(consts[0]) + "ye2 ";
		}
		if(consts[1] != 0){
			if(consts[1] < 0){
				str = str + "- ";
			} else {
				str = str + "+ ";
			}
			if(consts[1] == 1 || consts[1] == -1){
				str = str + "x ";
			} else {
				str = str + (int)Math.abs(consts[1]) + "x ";
			}
		}
		if(consts[2] != 0){
			if(consts[2] < 0){
				str = str + "- ";
			} else {
				str = str + "+ ";
			}
			if(consts[2] == 1 || consts[2] == -1){
				str = str + "y ";
			} else {
				str = str + (int)Math.abs(consts[2]) + "y ";
			}
		}
		if(consts[3] != 0){
			if(consts[3] < 0){
				str = str + "- ";
			} else {
				str = str + "+ ";
			}
			str = str + (int)Math.abs(consts[3]) + " ";
		}
		str = str + "= 0";
		
		return str;
	}
	
	/**
	 * Metodo que recebe 5 pontos do plano xy e retorna os coeficientes da equacao geral da conica
	 * que comtem esses pontos
	 * @param x1 double - coordenada x do primeiro ponto
	 * @param y1 double - coordenada y do primeiro ponto
	 * @param x2 double - coordenada x do segundo ponto
	 * @param y2 double - coordenada y do segundo ponto
	 * @param x3 double - coordenada x do terceiro ponto
	 * @param y3 double - coordenada y do terceiro ponto
	 * @param x4 double - coordenada x do quarto ponto
	 * @param y4 double - coordenada y do quarto ponto
	 * @param x5 double - coordenada x do quinto ponto
	 * @param y5 double - coordenada y do quinto ponto
	 * @return double[] - constantes da equacao geral da conica.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public double[] getConica(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double x5, double y5) throws ValorInvalidoException{
		double[][] ptsBase = {{x1, y1},{x2, y2},{x3, y3},{x4, y4},{x5, y5}};	//recebe os valores dos pontos
		double[] consts = new double[6];										//inicia as constantes
		
		//teste para pontos distintos.
		for(int i=0 ; i<5 ; i++){
			for(int j=i+1 ; j<5 ; j++){
				if(ptsBase[i][0] == ptsBase[j][0] && ptsBase[i][1] == ptsBase[j][1]){
					throw new ValorInvalidoException("os pontos " + (i+1) + " e " + (j+1) + ". Não são distintos");
				}
			}
		}
		
		//teste para pontos colineares.
		boolean reta = false;
		int count = 0;
		for(int i=0; i<5 && !reta; i++){
			for(int j=i+1 ; j<5 && !reta; j++){
				double[] auxConsts = this.getReta(ptsBase[i][0], ptsBase[i][1], ptsBase[j][0], ptsBase[j][1]);
				for(int k = j+1 ; k<5 ; k++){
					if(auxConsts[0]*ptsBase[k][0] + auxConsts[1]*ptsBase[k][1] + auxConsts[2] == 0){
						count++;
					}
				}
				if(count == 2){			//se tem quatro colineares 
					throw new ValorInvalidoException("os pontos, podem ser obtidas infinitas cônicas");
				} else if(count == 3){	//se todos os pontos são colineares é uma reta.
					reta = true;
				}
				count = 0;
			}
		}
		
		
		double f = 0;
		double k = 0;
		double o = 0;
		
		//Execução do algoritmo de reta.
		if(reta){
			double[] retaConst = this.getReta(ptsBase[0][0], ptsBase[0][1], ptsBase[1][0], ptsBase[1][1]);
			consts[3] = retaConst[0];
			consts[4] = retaConst[1];
			consts[5] = retaConst[2];
			f = 1;
			k = 1;
			o = 1;
		}
		
		int[] pt = {0,1,2,3,4};
		double[][] pts = new double[5][2];
		boolean convert = false;
		
		//Algoritmo de obtenção de conicas.
		while(f*k*o == 0){
			
			//Aplica os valores iniciais
			pts[0] = ptsBase[pt[0]];
			pts[1] = ptsBase[pt[1]];
			pts[2] = ptsBase[pt[2]];
			pts[3] = ptsBase[pt[3]];
			pts[4] = ptsBase[pt[4]];
		
			//realiza a permitação dos pontos para a proxima rodada caso f*k*o = 0
			boolean invert = false;
			boolean again = false;
			do{
				//incrementos da permutação
				pt[4]++;
				for(int i = 4 ; i>0 ; i--){
					if(pt[i] == 5){
						pt[i-1]++;
						pt[i] = 0;
					}
				}
				//Caso limite das permitacão, realiza a inversão
				if(pt[0] == 4){
					for(int i = 0 ; i<5 ; i++){
						pt[i] = i;
					}
					invert = true;
				}
				again = false;
				//detemina se o incremento da permutação eh valido.
				for(int i = 0 ; i<5 && !again ; i++){
					for(int j = i+1; j<5 ; j++){
						if(pt[i] == pt[j]){
							again = true;
						}
					}
				}
			}while(again);
			
			//realiza a inversão da equação para retirada pela inversa.
			if(invert){
				for(int i=0 ; i<5 ; i++){
					double valor = ptsBase[i][0];
					ptsBase[i][0] = ptsBase[i][1];
					ptsBase[i][1] = valor;
				}
				invert = false;
				convert = true;
			}
		
			//Etapa Inicial.
			double c1 = Math.pow(pts[0][1], 2);
			double c2 = Math.pow(pts[1][1], 2);
			double c3 = Math.pow(pts[2][1], 2);
			double c4 = Math.pow(pts[3][1], 2);
			double c5 = Math.pow(pts[4][1], 2);
		
			double d1 = pts[0][1]*pts[0][0];
			double d2 = pts[1][1]*pts[1][0];
			double d3 = pts[2][1]*pts[2][0];
			double d4 = pts[3][1]*pts[3][0];
			double d5 = pts[4][1]*pts[4][0];
		
			double e1 = Math.pow(pts[0][0], 2);
			double e2 = Math.pow(pts[1][0], 2);
			double e3 = Math.pow(pts[2][0], 2);
			double e4 = Math.pow(pts[3][0], 2);
			double e5 = Math.pow(pts[4][0], 2);
		
			//Etapa segunda linha.
			f = pts[1][1] - pts[0][1];
			if(f == 0) continue;
			double g = pts[1][0] - pts[0][0];
			double h = c2 - c1;
			double i = d2 - d1;
			double j = e2 - e1;
		
			//Etapa terceira linha.
			k = f*(pts[2][0] - pts[0][0]) - g*(pts[2][1] - pts[0][1]);
			if(k == 0) continue;
			double l = f*(c3 - c1) - h*(pts[2][1] - pts[0][1]);
			double m = f*(d3 - d1) - i*(pts[2][1] - pts[0][1]);
			double n = f*(e3 - e1) - j*(pts[2][1] - pts[0][1]);

			//Etapa quarta linha.
			o = k*(f*(c4 - c1) - h*(pts[3][1] - pts[0][1])) - l*(f*(pts[3][0] - pts[0][0]) - g*(pts[3][1] - pts[0][1]));
			if(o == 0) continue;
			double p = k*(f*(d4 - d1) - i*(pts[3][1] - pts[0][1])) - m*(f*(pts[3][0] - pts[0][0]) - g*(pts[3][1] - pts[0][1]));
			double q = k*(f*(e4 - e1) - j*(pts[3][1] - pts[0][1])) - n*(f*(pts[3][0] - pts[0][0]) - g*(pts[3][1] - pts[0][1]));

			//Etapa quinta linha.
			double r = o*(k*(f*(d5 - d1) - i*(pts[4][1] - pts[0][1])) - m*(f*(pts[4][0] - pts[0][0]) - g*(pts[4][1] - pts[0][1]))) - p*(k*(f*(c5 - c1) - h*(pts[4][1] - pts[0][1])) - l*(f*(pts[4][0] - pts[0][0]) - g*(pts[4][1] - pts[0][1])));
			double s = o*(k*(f*(e5 - e1) - j*(pts[4][1] - pts[0][1])) - n*(f*(pts[4][0] - pts[0][0]) - g*(pts[4][1] - pts[0][1]))) - q*(k*(f*(c5 - c1) - h*(pts[4][1] - pts[0][1])) - l*(f*(pts[4][0] - pts[0][0]) - g*(pts[4][1] - pts[0][1])));

			//Obtenção das constantes.
			consts[0] = f*k*o*r;
			consts[1] = -f*k*o*s;
			consts[2] = f*k*p*s - f*k*q*r;
			consts[3] = (f*m*o - f*l*p)*s + (f*l*q - f*n*o)*r;
			consts[4] = ((i*k - g*m)*o + (g*l - h*k)*p)*s + ((g*n - j*k)*o + (h*k - g*l)*q)*r;
			consts[5] = (((c1*f - pts[0][1]*h)*k + (pts[0][1]*g - pts[0][0]*f)*l)*q + ((pts[0][1]*j - e1*f)*k + (pts[0][0]*f - pts[0][1]*g)*n)*o)*r + (((pts[0][1]*h - c1*f)*k + (pts[0][0]*f - pts[0][1]*g)*l)*p + ((d1*f - pts[0][1]*i)*k + (pts[0][1]*g - pts[0][0]*f)*m)*o)*s;
		
			//realiza a conversão dos valores da inversa para a original.
			if(convert){
				double valor = consts[0];
				consts[0] = consts[2];
				consts[2] = valor;
				valor = consts[3];
				consts[3] = consts[4];
				consts[4] = valor;
			}
		}
		
		//realiza a normalização dos valores obtidos.
		boolean run = true;
		int[] ordem = {0,2,1,3,4};
		for(int i = 0 ; i<5 && run ; i++){
			if(consts[ordem[i]] != 0){
				if(consts[ordem[i]] != 1){
					double aux = consts[ordem[i]];
					for(int j=0 ; j<6 ; j++){
						consts[j] /= aux;
					}
				}
				run = false;
			}
		}
		
		//retirada de valores decimais com multiplos, aproximação em 3 digitos
		for(int z=1 ; z<6 ; z++){
			int multi = 1;
			double aux = consts[z];
			while((aux - (int)aux < -0.0000000000001 || aux - (int)aux > 0.0000000000001) && multi != 1000){
				aux = consts[z]*++multi;
			}
			consts[0] *= multi;
			consts[1] *= multi;
			consts[2] *= multi;
			consts[3] *= multi;
			consts[4] *= multi;
			consts[5] *= multi;
			if(multi==1000){
				for(int w=0 ; w<6 ; w++){
					consts[w] = (double)Math.round(consts[w])/1000;
				}
			}
		}
		
		//arredondamento de valores normalizados
		for(int z=0 ; z<6 ; z++){
			consts[z] = Math.round(consts[z]);
		}
		
		return consts;
	}
	
	/**
	 * Metodo que recebe as constantes da equação da conica e converte para uma texto formatado.
	 * @param consts double[] - constantes da equação da conica.
	 * @return String - equação da conica formatada.
	 * @throws ValorInvalidoException excecao para tratamento de valores invalidos.
	 */
	public String getEquacaoConica(double[] consts) throws ValorInvalidoException {
		String str = "";	//inicio da string da formula.
		
		//Restrição para teste das constantes
		if(consts.length != 6){
			throw new ValorInvalidoException("o conjunto de constantes. Não se refere a uma conica");
		}
		
		//Inicio do processo de formatação
		if(consts[0] != 0){
			if(consts[0] < 0){
				str = str + "- ";
			}
			if(consts[0] == 1 || consts[0] == -1){
				str = str + "xe2 ";
			} else {
				str = str + (int)Math.abs(consts[0]) + "xe2 ";
			}
		}
		
		if(consts[2] != 0){
			if(consts[2] < 0){
				str = str + "- ";
			} else if(consts[0] != 0){
				str = str + "+ ";
			}
			if(consts[2] == 1 || consts[2] == -1){
				str = str + "ye2 ";
			} else {
				str = str + (int)Math.abs(consts[2]) + "ye2 ";
			}
		}
		
		if(consts[1] != 0){
			if(consts[1] < 0){
				str = str + "- ";
			} else if(!(consts[0] == 0 && consts[2] == 0)){
				str = str + "+ ";
			}
			if(consts[1] == 1 || consts[1] == -1){
				str = str + "xy ";
			} else {
				str = str + (int)Math.abs(consts[1]) + "xy ";
			}
		}
		
		if(consts[3] != 0){
			if(consts[3] < 0){
				str = str + "- ";
			} else if(!(consts[0] == 0 && consts[2] == 0 && consts[1] == 0)){
				str = str + "+ ";
			}
			if(consts[3] == 1 || consts[3] == -1){
				str = str + "x ";
			} else {
				str = str + (int)Math.abs(consts[3]) + "x ";
			}
		}
		
		if(consts[4] != 0){
			if(consts[4] < 0){
				str = str + "- ";
			} else if(!(consts[0] == 0 && consts[2] == 0 && consts[1] == 0 && consts[3] == 0)){
				str = str + "+ ";
			}
			if(consts[4] == 1 || consts[4] == -1){
				str = str + "y ";
			} else {
				str = str + (int)Math.abs(consts[4]) + "y ";
			}
		}
		
		if(consts[5] != 0){
			if(consts[5] < 0){
				str = str + "- ";
			} else {
				str = str + "+ ";
			}
			str = str + (int)Math.abs(consts[5]) + " ";
		}
		
		str = str + "= 0";
		return str;
	}
}
