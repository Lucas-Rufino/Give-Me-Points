package dados;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Classe que define o funcionamento do objeto Grafico.
 * @author JOC, LAR, MRST e VRVS.
 */
public class Grafico {

	/**
	 * Metodo que recebe dois pontos e plota em um gráfico o intervalo da equação definida
	 * @param pts double[] - conjunto de pontos.
	 * @return ChartPanel - grafico para ser adicionado no panel do frame.
	 * @throws ValorInvalidoException exceção para tratamento de valores invalidos.
	 */
	public ChartPanel plotReta(double[][] pts) throws ValorInvalidoException{
	    Forma forma = new Forma();		//inicia a classe forma
	    double[] consts = forma.getReta(pts[0][0], pts[0][1], pts[1][0], pts[1][1]); //recebe os coeficientes
		
		final XYSeries series1 = new XYSeries("1");
	    final XYSeries series2 = new XYSeries("2");
	    
	    //Adiciona os pontos no grafico.
	    series1.add(pts[0][0], pts[0][1]);
	    series1.add(pts[0][0], null);
	    series1.add(pts[1][0], pts[1][1]);
	    
	    final XYSeriesCollection data = new XYSeriesCollection(series1);
	    
	    //Recebe os valores limites do grafico
	    double[] limites = new double[4];
	    limites[0] = data.getDomainUpperBound(true);
	    limites[1] = data.getRangeUpperBound(true);
	    limites[2] = data.getDomainLowerBound(true);
	    limites[3] = data.getRangeLowerBound(true);
	    
	    //determina a variação de 
	    double[] deltas = {(limites[0]-limites[2])/2, (limites[1]-limites[3])/2};
	    double[] centro = {(limites[0]+limites[2])/2, (limites[1]+limites[3])/2};
	    
	    //Redefine os limites maximos com base no centro e o maior delta
	    if(deltas[0] > deltas[1]){
	    	limites[0] = centro[0]+deltas[0];
	    	limites[1] = centro[1]+deltas[0];
	    	limites[2] = centro[0]-deltas[0];
	    	limites[3] = centro[1]-deltas[0];
	    } else {
	    	limites[0] = centro[0]+deltas[1];
	    	limites[1] = centro[1]+deltas[1];
	    	limites[2] = centro[0]-deltas[1];
	    	limites[3] = centro[1]-deltas[1];
	    }
	    
	    //Carrega os valores da serie gráfica
	    if(consts[1] != 0){
	    	for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0] ; i += passo){
	    		double y = -(consts[2] + consts[0]*i)/consts[1];
	    		if(y <= limites[1] && y >= limites[3]){
	    			series2.add(i, -(consts[2] + consts[0]*i)/consts[1]);
	    		} else {
	    			series2.add(i, null);
	    		}
	    	}
		} else {
			for(double i = limites[3], passo = 0.001; i <= limites[1] ; i += passo){
	    		series2.add(pts[0][0], i);
	    	}
		}
	    
	    data.addSeries(series2);
	    final XYSeries series3 = new XYSeries("3");
	    
	    //fixa os limites no grafico, para garantir regularidade.
	    series3.add(limites[2], limites[3]);
		series3.add(limites[2], null);
		series3.add(limites[0], limites[1]);
	    data.addSeries(series3);
	    
	    //Criação do chart e personalizacao do grafico.
	    final JFreeChart chart = ChartFactory.createXYLineChart( null, "X", "Y", data, PlotOrientation.VERTICAL, false, true, false);
	    NumberAxis yaxis = (NumberAxis)chart.getXYPlot().getRangeAxis();
	    yaxis.setAutoRangeIncludesZero(false);
	    chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.BLUE);
	    chart.getXYPlot().getRenderer().setSeriesPaint(1, Color.BLUE);
	    chart.getXYPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setBackgroundPaint(Color.WHITE);
	    chart.setBackgroundPaint(Color.WHITE);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 580));
	    
	    return chartPanel;
	}
	
	public ChartPanel fixPlotReta(double[][] pts) throws ValorInvalidoException{
	    Forma forma = new Forma();		//inicia a classe forma
	    double[] consts = forma.getReta(pts[0][0], pts[0][1], pts[1][0], pts[1][1]); //recebe os coeficientes
		
		final XYSeries series1 = new XYSeries("1");
	    final XYSeries series2 = new XYSeries("2");
	    
	    //Adiciona os pontos no grafico.
	    series1.add(-10, -10);
	    series1.add(-10, null);
	    series1.add(10, 10);
	    
	    final XYSeriesCollection data = new XYSeriesCollection(series1);
	    
	    //Recebe os valores limites do grafico
	    double[] limites = new double[4];
	    limites[0] = 10;
	    limites[1] = 10;
	    limites[2] = -10;
	    limites[3] = -10;
	    
	    
	    //Carrega os valores da serie gráfica
	    if(consts[1] != 0){
	    	for(double i = limites[2], passo = 0.001; i <= limites[0] ; i += passo){
	    		double y = -(consts[2] + consts[0]*i)/consts[1];
	    		if(y <= limites[1] && y >= limites[3]){
	    			series2.add(i, -(consts[2] + consts[0]*i)/consts[1]);
	    		} else {
	    			series2.add(i-passo, null);
	    		}
	    	}
		} else {
			for(double i = limites[3], passo = 0.001; i <= limites[1] ; i += passo){
	    		series2.add(pts[0][0], i);
	    	}
		}
	    
	    data.addSeries(series2);
	    
	    //Criação do chart e personalizacao do grafico.
	    final JFreeChart chart = ChartFactory.createXYLineChart( null, "X", "Y", data, PlotOrientation.VERTICAL, false, true, false);
	    NumberAxis yaxis = (NumberAxis)chart.getXYPlot().getRangeAxis();
	    yaxis.setAutoRangeIncludesZero(false);
	    chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.BLUE);
	    chart.getXYPlot().getRenderer().setSeriesPaint(1, Color.BLUE);
	    chart.getXYPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setBackgroundPaint(Color.WHITE);
	    chart.setBackgroundPaint(Color.WHITE);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 580));
	    
	    return chartPanel;
	}
	
	/**
	 * Metodo que recebe 3 pontos e plota em um gráfico a circunferencia da equação definida.
	 * @param pts double[] - conjunto de pontos.
	 * @return ChartPanel - grafico para ser adicionado no panel do frame.
	 * @throws ValorInvalidoException exceção para tratamento de valores invalidos.
	 */
	public ChartPanel plotCircunferencia(double[][] pts) throws ValorInvalidoException{
		Forma forma = new Forma();		//inicia a classe forma
	    double[] consts = forma.getCircunferencia(pts[0][0], pts[0][1], pts[1][0], pts[1][1], pts[2][0], pts[2][1]);
		
		final XYSeries series1 = new XYSeries("1");
	    final XYSeries series2 = new XYSeries("2");
	    final XYSeries series3 = new XYSeries("3");
	    
	    //Realiza a plotagem dos valores do ponto inicial pela direita ate o limite.
	    double y1 = 0, y2 = 0, delta = 0;
	    for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i += passo){
	    	double valor = consts[3] + consts[0]*i*i + consts[1]*i;
	    	delta = consts[2]*consts[2] - 4*consts[0]*valor;
	    	if(delta >= 0){
	    		y1 = (-consts[2] + Math.sqrt(delta))/(2*consts[0]);
	    		y2 = (-consts[2] - Math.sqrt(delta))/(2*consts[0]);
	    		series1.add(i, y1);
	    		series2.add(i, y2);
	    	} else {
	    		series1.add(i-passo, y2);
	    	}
	    }
	    
	    y1 = 0;
	    y2 = 0;
	    delta = 0;
	    //Realiza a plotagem dos valores do ponto inicial pela esquerda ate o limite.
	    for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i -= passo){
	    	double valor = consts[3] + consts[0]*i*i + consts[1]*i;
	    	delta = consts[2]*consts[2] - 4*consts[0]*valor;
	    	if(delta >= 0){
	    		y1 = (-consts[2] + Math.sqrt(delta))/(2*consts[0]);
	    		y2 = (-consts[2] - Math.sqrt(delta))/(2*consts[0]);
	    		series1.add(i, y1);
	    		series2.add(i, y2);
	    	} else {
	    		series1.add(i+passo, y2);
	    		series1.add(i+passo, y1);
	    	}
	    }
	    
	    final XYSeriesCollection data = new XYSeriesCollection(series1);
	    data.addSeries(series2);
	    
	    double[] limites = new double[4];
	    limites[0] = data.getDomainUpperBound(true);
	    limites[1] = data.getRangeUpperBound(true);
	    limites[2] = data.getDomainLowerBound(true);
	    limites[3] = data.getRangeLowerBound(true);
	    
	    double[] deltas = {(limites[0]-limites[2])/2,(limites[1]-limites[3])/2};
	    double[] centro = {(limites[0]+limites[2])/2,(limites[1]+limites[3])/2};
	    
	    if(deltas[0] > deltas[1]){
	    	limites[0] = centro[0]+deltas[0];
	    	limites[1] = centro[1]+deltas[0];
	    	limites[2] = centro[0]-deltas[0];
	    	limites[3] = centro[1]-deltas[0];
	    } else {
	    	limites[0] = centro[0]+deltas[1];
	    	limites[1] = centro[1]+deltas[1];
	    	limites[2] = centro[0]-deltas[1];
	    	limites[3] = centro[1]-deltas[1];
	    }
	    
	    series3.add(limites[2], limites[3]);
		series3.add(limites[2], null);
		series3.add(limites[0], limites[1]);
	    data.addSeries(series3);
	    
	    
	    //Criação do chart e personalizacao do grafico.
	    final JFreeChart chart = ChartFactory.createXYLineChart( null, "X", "Y", data, PlotOrientation.VERTICAL, false, true, false);
	    NumberAxis yaxis = (NumberAxis)chart.getXYPlot().getRangeAxis();
	    yaxis.setAutoRangeIncludesZero(false);
	    chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.BLUE);
	    chart.getXYPlot().getRenderer().setSeriesPaint(1, Color.BLUE);
	    chart.getXYPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setBackgroundPaint(Color.WHITE);
	    chart.setBackgroundPaint(Color.WHITE);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 580));
	    return chartPanel;
	    
	}
	
	/**
	 * Metodo que recebe 5 pontos e plota em um gráfico a conica da equação definida.
	 * @param pts double[] - conjunto de pontos.
	 * @return ChartPanel - grafico para ser adicionado no panel do frame.
	 * @throws ValorInvalidoException exceção para tratamento de valores invalidos.
	 */
	public ChartPanel plotConica(double[][] pts) throws ValorInvalidoException{
	    Forma forma = new Forma();		//inicia a classe forma
	    double[] consts = forma.getConica(pts[0][0], pts[0][1], pts[1][0], pts[1][1], pts[2][0], pts[2][1], pts[3][0], pts[3][1], pts[4][0], pts[4][1]);
	    
	    final XYSeries series1 = new XYSeries("1");
	    final XYSeries series2 = new XYSeries("2");
	    final XYSeries series3 = new XYSeries("3");
	    final XYSeries series4 = new XYSeries("4");
	    final XYSeries series5 = new XYSeries("5");
	    final XYSeries series6 = new XYSeries("6");
	    final XYSeries series7 = new XYSeries("7");
	    
	    for(int i=0 ; i<5 ; i++){
	    	series1.add(pts[i][0], pts[i][1]);
	    	series1.add(pts[i][0], null);
	    }
	    
	    final XYSeriesCollection data = new XYSeriesCollection(series1);
	    
	    double[] limites = new double[4];
	    limites[0] = data.getDomainUpperBound(true);
	    limites[1] = data.getRangeUpperBound(true);
	    limites[2] = data.getDomainLowerBound(true);
	    limites[3] = data.getRangeLowerBound(true);
	    
	    int tipo = this.tipoConica(consts);
	    if(tipo == 0){			//Circunferência
	    	//Realiza a plotagem dos valores do ponto inicial pela direita ate o limite.
		    double y1 = 0, y2 = 0, delta = 0;
		    for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i += passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	delta = valor2*valor2 - 4*consts[2]*valor1;
	        	if(delta >= 0){
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    		series2.add(i, y1);
		    		series3.add(i, y2);
		    	} else {
		    		series2.add(i-passo, y2);
		    	}
		    }
		    
		    y1 = 0;
		    y2 = 0;
		    delta = 0;
		    //Realiza a plotagem dos valores do ponto inicial pela esquerda ate o limite.
		    for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i -= passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	delta = valor2*valor2 - 4*consts[2]*valor1;
	        	if(delta >= 0){
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    		series2.add(i, y1);
		    		series3.add(i, y2);
		    	} else {
		    		series2.add(i+passo, y2);
		    		series2.add(i+passo, y1);
		    	}
		    }
		    
		    data.addSeries(series2);
		    data.addSeries(series3);
		    
	    } else if(tipo == 1){	//Elipse
	    	double y1 = 0;
	        double y2 = 0;
	        double delta = 0;
	        for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i += passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	delta = valor2*valor2 - 4*consts[2]*valor1;
	        	if(delta >= 0){
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	        		series2.add(i, y1);
	        		series3.add(i, y2);
	        	} else {
	        		series2.add(i-passo, y2);
	        	}
	        }
	        
	        y1 = 0;
	        y2 = 0;
	        delta = 0;
	        for(double i = pts[0][0], passo = 0.001; delta >= 0 ; i -= passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	delta = valor2*valor2 - 4*consts[2]*valor1;
	        	if(delta >= 0){
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	        		series2.add(i, y1);
	        		series3.add(i, y2);
	        	} else {
	        		series2.add(i+passo, y2);
	        		series2.add(i+passo, y1);
	        	}
	        }
	        
	        data.addSeries(series2);
		    data.addSeries(series3);
	    } else if(tipo == 2){	//Parabola
	    	//Realiza a plotagem dos valores do ponto inicial pela direita ate o limite.
	    	double y1 = 0, y2 = 0, delta = 0;
	        for(double i = pts[0][0], passo = 0.001; delta >= 0 && i <= limites[0]; i += passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	if(consts[2] != 0){
	        		delta = valor2*valor2 - 4*consts[2]*valor1;
	        		if(delta >= 0){
	        			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	        			series2.add(i, y1);
	        			series3.add(i, y2);
	        		} else {
	        			series2.add(i-passo, y2);
	        		}
	        	} else {
	        		series2.add(i, -valor1/valor2);
	        	}
	        }
	        
	        y1 = 0;
	        y2 = 0;
	        delta = 0;
	        //Realiza a plotagem dos valores do ponto inicial pela esquerda ate o limite.
	        for(double i = pts[0][0], passo = 0.001; delta >= 0 && i >= limites[2]; i -= passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	if(consts[2] != 0){
	            	delta = valor2*valor2 - 4*consts[2]*valor1;
	            	if(delta >= 0){
	            		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	            		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	            		series2.add(i, y1);
	        			series3.add(i, y2);
	            	} else {
	            		series2.add(i+passo, y2);
	            		series2.add(i+passo, y1);
	            	}
	        	} else {
	        		series2.add(i, -valor1/valor2);
	        	}
	        }
	        
	        data.addSeries(series2);
		    data.addSeries(series3);
		    
		    limites[0] = data.getDomainUpperBound(true);
		    limites[1] = data.getRangeUpperBound(true);
		    limites[2] = data.getDomainLowerBound(true);
		    limites[3] = data.getRangeLowerBound(true);
		    
		    double[] deltas = {(limites[0]-limites[2])/2, (limites[1]-limites[3])/2};
		    double[] centro = {(limites[0]+limites[2])/2, (limites[1]+limites[3])/2};
		    
		    if(deltas[0] > deltas[1]){
		    	limites[0] = centro[0]+deltas[0];
		    	limites[1] = centro[1]+deltas[0];
		    	limites[2] = centro[0]-deltas[0];
		    	limites[3] = centro[1]-deltas[0];
		    } else {
		    	limites[0] = centro[0]+deltas[1];
		    	limites[1] = centro[1]+deltas[1];
		    	limites[2] = centro[0]-deltas[1];
		    	limites[3] = centro[1]-deltas[1];
		    }
		    
		    if(consts[2] != 0){
		    	double delta1, delta2,valor1d, valor2d, passo;
		    	valor1d = consts[5] + consts[0]*(pts[0][0]+0.001)*(pts[0][0]+0.001) + consts[3]*(pts[0][0]+0.001);
	        	valor2d = consts[1]*(pts[0][0]+0.001) + consts[4];
	        	delta1 = valor2d*valor2d - 4*consts[2]*valor1d;
	        	
	        	valor1d = consts[5] + consts[0]*(pts[0][0]-0.001)*(pts[0][0]-0.001) + consts[3]*(pts[0][0]-0.001);
	        	valor2d = consts[1]*(pts[0][0]-0.001) + consts[4];
	        	delta2 = valor2d*valor2d - 4*consts[2]*valor1d;
	        	
	        	if(delta1 > delta2){
	        		passo = -0.001;
	        	} else {
	        		passo = 0.001;
	        	}
	        	y1 = 0;
	        	y2 = 0;
	        	delta = 0;
	        	for(double i = pts[0][0]; delta >= 0; i += passo){
		        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
		        	double valor2 = consts[1]*i + consts[4];
		        	delta = valor2*valor2 - 4*consts[2]*valor1;
		        	if(delta >= 0){
		        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
		        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		        		series4.add(i, y1);
		       		} else {
		       			series4.add(i-passo, y2);
		       		}
		        }
	        	
	        	data.addSeries(series4);
	        	
	        	limites[0] = data.getDomainUpperBound(true);
			    limites[1] = data.getRangeUpperBound(true);
			    limites[2] = data.getDomainLowerBound(true);
			    limites[3] = data.getRangeLowerBound(true);
			    
			    deltas[0] = (limites[0]-limites[2])/2;
			    deltas[1] = (limites[1]-limites[3])/2;
			    centro[0] = (limites[0]+limites[2])/2;
			    centro[1] = (limites[1]+limites[3])/2;
			    
			    if(deltas[0] > deltas[1]){
			    	limites[0] = centro[0]+deltas[0];
			    	limites[1] = centro[1]+deltas[0];
			    	limites[2] = centro[0]-deltas[0];
			    	limites[3] = centro[1]-deltas[0];
			    } else {
			    	limites[0] = centro[0]+deltas[1];
			    	limites[1] = centro[1]+deltas[1];
			    	limites[2] = centro[0]-deltas[1];
			    	limites[3] = centro[1]-deltas[1];
			    }
		    }
		    
		    y1 = 0;
		    y2 = 0;
		    delta = 0;
		    boolean end = false, run = false;
		    for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0] ; i += passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
		    	double valor2 = consts[1]*i + consts[4];
		    	if(consts[2] != 0){
		    		delta = valor2*valor2 - 4*consts[2]*valor1;
		    		if(delta >= 0){
		    			end = false;
		    			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
		    			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    			if(run){
		    				run = false;
		    				double media = (y1+y2)/2;
		    				if(media <= limites[1] && media >= limites[3]){
		    					series5.add(i, media);
		    					series6.add(i, media);
		    				}
		    			}
		    			if(y1 <= limites[1] && y1 >= limites[3]){
		    				series5.add(i, y1);
		    			}
		    			if(y2 <= limites[1] && y2 >= limites[3]){
		    				series6.add(i, y2);
		    			}
		    		} else if(!end){
		    			end = true;
		    			run = true;
		    			double media = (y1+y2)/2;
		    			if(media <= limites[1] && media >= limites[3]){
		    				series5.add(i+passo, media);
		    				series6.add(i+passo, media);
		    			}
		    			series5.add(i, null);
		    			series6.add(i, null);
		    		} else {
		    			run = true;
		    			series5.add(i, null);
		    			series6.add(i, null);
		    		}
		    	} else {
		    		y1 = -valor1/valor2;
		    		if(y1 <= limites[1] && y1 >= limites[3]){
		    			series5.add(i, y1);
		    		} else {
		    			series5.add(i, null);
		    		}
		    	}
		    }
		  
	    	data.addSeries(series5);
	    	data.addSeries(series6);
	    	
	    } else if(tipo == 3){	//Hiperbole
	    	double y1 = 0, y2 = 0, delta = 0;
		    boolean end = false, run = false;
		    for(double i = pts[0][0], passo = 0.001; i <= limites[0]; i += passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
		    	double valor2 = consts[1]*i + consts[4];
		    	if(consts[2] != 0){
		    		delta = valor2*valor2 - 4*consts[2]*valor1;
		    		if(delta >= 0){
		    			end = false;
		    			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
		    			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    			if(run){
		    				run = false;
		    				series2.add(i, (y1+y2)/2);
		        			series3.add(i, (y1+y2)/2);
		    			}
		    			series2.add(i, y1);
		    			series3.add(i, y2);
		    		} else if(!end){
		    			end = true;
		    			run = true;
		    			series2.add(i-passo, (y1+y2)/2);
		    			series3.add(i-passo, (y1+y2)/2);
		    			series2.add(i, null);
		    			series3.add(i, null);
		    		} else {
		    			run = true;
		    			series2.add(i, null);
		    			series3.add(i, null);
		    		}
		    	} else if(valor2 != 0){
		    		y1 = -valor1/valor2;
		    		if(y1 <= limites[1] && y1 >= limites[3]){
		    			series2.add(i, y1);
		    		} else {
		        		series2.add(i, null);
		        	}
		    	} else {
		    		series2.add(i, null);
		    	}
		    }
		    
		    y1 = 0;
		    y2 = 0;
		    delta = 0;
		    end = false;
		    run = false;
		    for(double i = pts[0][0], passo = 0.001; i >= limites[2]; i -= passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
		    	double valor2 = consts[1]*i + consts[4];
		    	if(consts[2] != 0){
		    		delta = valor2*valor2 - 4*consts[2]*valor1;
		    		if(delta >= 0){
		    			end = false;
		    			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
		    			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    			if(run){
		    				run = false;
		    				series2.add(i, (y1+y2)/2);
		        			series3.add(i, (y1+y2)/2);
		    			}
		    			series2.add(i, y1);
		    			series3.add(i, y2);
		    		} else if(!end){
		    			end = true;
		    			run = true;
		    			series2.add(i+passo, (y1+y2)/2);
		    			series3.add(i+passo, (y1+y2)/2);
		    			series2.add(i, null);
		    			series3.add(i, null);
		    		} else {
		    			run = true;
		    			series2.add(i, null);
		    			series3.add(i, null);
		    		}
		    	} else if(valor2 != 0){
		    		y1 = -valor1/valor2;
		    		if(y1 <= limites[1] && y1 >= limites[3]){
		    			series2.add(i, y1);
		    		} else {
		        		series2.add(i, null);
		        	}
		    	} else {
		    		series2.add(i, null);
		    	}
		    }
		    
		    data.addSeries(series2);
		    data.addSeries(series3);
		    
		    if(consts[2] != 0){
		    	double delta1, delta2,valor1d, valor2d, passo;
		    	valor1d = consts[5] + consts[0]*(pts[0][0]+0.001)*(pts[0][0]+0.001) + consts[3]*(pts[0][0]+0.001);
	        	valor2d = consts[1]*(pts[0][0]+0.001) + consts[4];
	        	delta1 = valor2d*valor2d - 4*consts[2]*valor1d;
	        	
	        	valor1d = consts[5] + consts[0]*(pts[0][0]-0.001)*(pts[0][0]-0.001) + consts[3]*(pts[0][0]-0.001);
	        	valor2d = consts[1]*(pts[0][0]-0.001) + consts[4];
	        	delta2 = valor2d*valor2d - 4*consts[2]*valor1d;
	        	
	        	if(delta1 > delta2){
	        		passo = -0.001;
	        		delta = delta2;
	        	} else {
	        		passo = 0.001;
	        		delta = delta1;
	        	}
		    	
		    	y1 = 0;
			    y2 = 0;
			    double deltaLast = delta;
			    for(double i = pts[0][0] + passo; delta <= deltaLast || delta < 0 ; i += passo){
			    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
			    	double valor2 = consts[1]*i + consts[4];
			    	deltaLast = delta;
			    	delta = valor2*valor2 - 4*consts[2]*valor1;
			   		if(delta >= 0){
			    		end = false;
			    		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
			    		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
			    		if(run){
		    				run = false;
			   				double media = (y1+y2)/2;
			   				series4.add(i, media);
			    		}
			    		series4.add(i, y1);
			   		} else if(!end){
			   			end = true;
			   			run = true;
			    		double media = (y1+y2)/2;
			    		series4.add(i+passo, media);
		    			series4.add(i, null);
			   		} else {
			   			run = true;
			   			series4.add(i, null);
			    	}
			    }
			    
			    data.addSeries(series4);
		    } else if(consts[0] != 0){
		    	double delta1, delta2,valor1d, valor2d, passo;
		    	valor1d = consts[5] + consts[4]*(pts[0][1]+0.001);
	        	valor2d = consts[1]*(pts[0][1]+0.001) + consts[3];
	        	delta1 = valor2d*valor2d - 4*consts[0]*valor1d;
	        	
	        	valor1d = consts[5] + consts[4]*(pts[0][1]-0.001);
	        	valor2d = consts[1]*(pts[0][1]-0.001) + consts[3];
	        	delta2 = valor2d*valor2d - 4*consts[0]*valor1d;
	        	
	        	if(delta1 > delta2){
	        		passo = -0.001;
	        		delta = delta2;
	        	} else {
	        		passo = 0.001;
	        		delta = delta1;
	        	}
		    	
		    	y1 = 0;
			    y2 = 0;
			    double deltaLast = delta;
			    double x = pts[0][0];
			    for(double i = pts[0][1] + passo; delta <= deltaLast || delta < 0 ; i += passo){
			    	double valor1 = consts[5] + consts[4]*i;
			    	double valor2 = consts[1]*i + consts[3];
			    	deltaLast = delta;
			    	delta = valor2*valor2 - 4*consts[0]*valor1;
			    	x = i;
			    }
			    
			    valor1d = consts[5] + consts[4]*x;
		    	valor2d = consts[1]*x + consts[3];
		    	series4.add(x, -valor1d/valor2d);
		    	series4.add(x, null);
			    
			    data.addSeries(series4);
		    } else {
		    	double valor1d, valor2d;
		    	double x = -consts[4]/consts[1];
		    	
		    	valor1d = consts[5] + consts[3]*(x - 0.1);
	        	valor2d = consts[1]*(x - 0.1) + consts[4];
		    	series4.add(x-0.1, -valor1d/valor2d);
		    	series4.add(x-0.1, null);
		    	valor1d = consts[5] + consts[3]*(x + 0.1);
	        	valor2d = consts[1]*(x + 0.1) + consts[4];
		    	series4.add(x+0.1, -valor1d/valor2d);
	        	
		    	data.addSeries(series4);
		    }
		    
		    limites[0] = data.getDomainUpperBound(true);
		    limites[1] = data.getRangeUpperBound(true);
		    limites[2] = data.getDomainLowerBound(true);
		    limites[3] = data.getRangeLowerBound(true);
		    
		    double[] deltas = {(limites[0]-limites[2])/2, (limites[1]-limites[3])/2};
		    double[] centro = {(limites[0]+limites[2])/2, (limites[1]+limites[3])/2};
		    
		    if(deltas[0] > deltas[1]){
		    	limites[0] = centro[0]+deltas[0];
		    	limites[1] = centro[1]+deltas[0];
		    	limites[2] = centro[0]-deltas[0];
		    	limites[3] = centro[1]-deltas[0];
		    } else {
		    	limites[0] = centro[0]+deltas[1];
		    	limites[1] = centro[1]+deltas[1];
		    	limites[2] = centro[0]-deltas[1];
		    	limites[3] = centro[1]-deltas[1];
		    }
		    
		    y1 = 0;
		    y2 = 0;
		    delta = 0;
		    for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0] ; i += passo){
		    	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
		    	double valor2 = consts[1]*i + consts[4];
		    	if(consts[2] != 0){
		    		delta = valor2*valor2 - 4*consts[2]*valor1;
		    		if(delta >= 0){
		    			end = false;
		    			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
		    			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
		    			if(run){
		    				run = false;
		    				double media = (y1+y2)/2;
		    				if(media <= limites[1] && media >= limites[3]){
		    					series5.add(i, media);
		    					series6.add(i, media);
		    				}
		    			}
		    			if(y1 <= limites[1] && y1 >= limites[3]){
		    				series5.add(i, y1);
		    			}
		    			if(y2 <= limites[1] && y2 >= limites[3]){
		    				series6.add(i, y2);
		    			}
		    		} else if(!end){
		    			end = true;
		    			run = true;
		    			double media = (y1+y2)/2;
		    			if(media <= limites[1] && media >= limites[3]){
		    				series5.add(i+passo, media);
		    				series6.add(i+passo, media);
		    			}
		    			series5.add(i, null);
		    			series6.add(i, null);
		    		} else {
		    			run = true;
		    			series5.add(i, null);
		    			series6.add(i, null);
		    		}
		    	} else {
		    		y1 = -valor1/valor2;
		    		if(y1 <= limites[1] && y1 >= limites[3]){
		    			series5.add(i, y1);
		    		} else {
		    			series5.add(i, null);
		    		}
		    	}
		    }
		    
		    data.addSeries(series5);
		    data.addSeries(series6);
		    
	    } else if(tipo == 4){	//Hiperbole degenerada - Par de retas concorrentes
	    	
	    	//Realiza a plotagem dos valores do ponto inicial pela direita ate o limite.
	    	double y1 = 0, y2 = 0, delta = 0;
	        for(double i = pts[0][0], passo = 0.001; i <= limites[0]; i += passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	if(consts[2] != 0){
	        		delta = valor2*valor2 - 4*consts[2]*valor1;
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	        		series2.add(i, y1);
	        		series3.add(i, y2);
	        	} else {
	        		if(valor2 != 0){
	        			series2.add(i, -valor1/valor2);
	        		}
	        	}
	        }
	        
	        y1 = 0;
	        y2 = 0;
	        delta = 0;
	        //Realiza a plotagem dos valores do ponto inicial pela esquerda ate o limite.
	        for(double i = pts[0][0], passo = 0.001; delta >= 0 && i >= limites[2]; i -= passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	if(consts[2] != 0){
	            	delta = valor2*valor2 - 4*consts[2]*valor1;
	            	y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	            	y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	            	series2.add(i, y1);
	        		series3.add(i, y2);
	        	} else {
	        		if(valor2 != 0){
	        			series2.add(i, -valor1/valor2);
	        		}
	        	}
	        }
	        
	        data.addSeries(series2);
		    data.addSeries(series3);
		    
		    limites[0] = data.getDomainUpperBound(true);
		    limites[1] = data.getRangeUpperBound(true);
		    limites[2] = data.getDomainLowerBound(true);
		    limites[3] = data.getRangeLowerBound(true);
		    
		    double[] deltas = {(limites[0]-limites[2])/2, (limites[1]-limites[3])/2};
		    double[] centro = {(limites[0]+limites[2])/2, (limites[1]+limites[3])/2};
		    
		    if(deltas[0] > deltas[1]){
		    	limites[0] = centro[0]+deltas[0];
		    	limites[1] = centro[1]+deltas[0];
		    	limites[2] = centro[0]-deltas[0];
		    	limites[3] = centro[1]-deltas[0];
		    } else {
		    	limites[0] = centro[0]+deltas[1];
		    	limites[1] = centro[1]+deltas[1];
		    	limites[2] = centro[0]-deltas[1];
		    	limites[3] = centro[1]-deltas[1];
		    }
		    
		    y1 = 0;
		    y2 = 0;
		    delta = 0;
	        for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0]; i += passo){
	        	double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	        	double valor2 = consts[1]*i + consts[4];
	        	if(consts[2] != 0){
	        		delta = valor2*valor2 - 4*consts[2]*valor1;
	        		y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	        		y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	        		if(y1 <= limites[1] && y1 >= limites[3]){
	    				series4.add(i, y1);
	    			}
	    			if(y2 <= limites[1] && y2 >= limites[3]){
	    				series5.add(i, y2);
	    			}
	        	} else {
	        		if(valor2 != 0){
	        			y1 = -valor1/valor2;
			    		if(y1 <= limites[1] && y1 >= limites[3]){
			    			series4.add(i, y1);
			    		} else {
			    			series4.add(i, null);
			    		}
	        		} else {
	        			series4.add(i, null);
	        		}
	        	}
	        }
	        
	        data.addSeries(series4);
		    data.addSeries(series5);
		    
		    if(consts[2] == 0){
		    	limites[0] = data.getDomainUpperBound(true);
			    limites[1] = data.getRangeUpperBound(true);
			    limites[2] = data.getDomainLowerBound(true);
			    limites[3] = data.getRangeLowerBound(true);
			    
			    deltas[0] = (limites[0]-limites[2])/2;
			    deltas[1] = (limites[1]-limites[3])/2;
			    centro[0] = (limites[0]+limites[2])/2;
			    centro[1] = (limites[1]+limites[3])/2;
			    
			    if(deltas[0] > deltas[1]){
			    	limites[0] = centro[0]+deltas[0];
			    	limites[1] = centro[1]+deltas[0];
			    	limites[2] = centro[0]-deltas[0];
			    	limites[3] = centro[1]-deltas[0];
			    } else {
			    	limites[0] = centro[0]+deltas[1];
			    	limites[1] = centro[1]+deltas[1];
			    	limites[2] = centro[0]-deltas[1];
			    	limites[3] = centro[1]-deltas[1];
			    }
			    
			    double x = -consts[4]/consts[1];
			   	for(double i = limites[3], passo = 0.001; i <= limites[1] ; i += passo){
			   		series6.add(x, i);
			   	}
			   	data.addSeries(series6);
		    }
	    } else if(tipo == 5){	//Parabola degenerada - Par de retas paralelas
	    	if(consts[1] == 0 && consts[2] == 0 && consts[4] == 0){
	    		boolean run = true;
	    		for(int i=0 ; run ; i++){
	    			if(pts[0][0] != pts[i][0]){
	    				for(double j = limites[3], passo = 0.001; j <= limites[1] ; j += passo){
	    		    		series5.add(pts[0][0], j);
	    		    		series6.add(pts[i][0], j);
	    		    	}
	    				run = false;
	    			}
	    		}
	    	} else {
	    		double y1 = 0, y2 = 0, delta = 0;
	    		for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0] ; i += passo){
	    			double valor1 = consts[5] + consts[0]*i*i + consts[3]*i;
	    			double valor2 = consts[1]*i + consts[4];
	    			delta = valor2*valor2 - 4*consts[2]*valor1;
	    			y1 = (-valor2 + Math.sqrt(delta))/(2*consts[2]);
	    			y2 = (-valor2 - Math.sqrt(delta))/(2*consts[2]);
	    			if(y1 <= limites[1] && y1 >= limites[3]){
	    				series5.add(i, y1);
	    			}
	    			if(y2 <= limites[1] && y2 >= limites[3]){
	    				series6.add(i, y2);
	    			}
	    		}
	    	}
		    
		    data.addSeries(series5);
		    data.addSeries(series6);
	    } else if(tipo == 6){	//Reta
		    limites[0] = data.getDomainUpperBound(true);
		    limites[1] = data.getRangeUpperBound(true);
		    limites[2] = data.getDomainLowerBound(true);
		    limites[3] = data.getRangeLowerBound(true);
		    
		    //determina a variação de 
		    double[] deltas = {(limites[0]-limites[2])/2, (limites[1]-limites[3])/2};
		    double[] centro = {(limites[0]+limites[2])/2, (limites[1]+limites[3])/2};
		    
		    //Redefine os limites maximos com base no centro e o maior delta
		    if(deltas[0] > deltas[1]){
		    	limites[0] = centro[0]+deltas[0];
		    	limites[1] = centro[1]+deltas[0];
		    	limites[2] = centro[0]-deltas[0];
		    	limites[3] = centro[1]-deltas[0];
		    } else {
		    	limites[0] = centro[0]+deltas[1];
		    	limites[1] = centro[1]+deltas[1];
		    	limites[2] = centro[0]-deltas[1];
		    	limites[3] = centro[1]-deltas[1];
		    }
		    
		    //Carrega os valores da serie gráfica
		    if(consts[4] != 0){
		    	for(double i = Math.round(limites[2]*1000)/1000.0, passo = 0.001; i <= limites[0] ; i += passo){
		    		double y = -(consts[5] + consts[3]*i)/consts[4];
		    		if(y <= limites[1] && y >= limites[3]){
		    			series2.add(i, -(consts[5] + consts[3]*i)/consts[4]);
		    		} else {
		    			series2.add(i, null);
		    		}
		    	}
			} else {
				for(double i = limites[3], passo = 0.001; i <= limites[1] ; i += passo){
		    		series2.add(pts[0][0], i);
		    	}
			}
		    
		    data.addSeries(series2);
	    }
	    
	    limites[0] = data.getDomainUpperBound(true);
	    limites[1] = data.getRangeUpperBound(true);
	    limites[2] = data.getDomainLowerBound(true);
	    limites[3] = data.getRangeLowerBound(true);
	    
	    double[] deltas = {(limites[0]-limites[2])/2,(limites[1]-limites[3])/2};
	    double[] centro = {(limites[0]+limites[2])/2,(limites[1]+limites[3])/2};
	    
	    if(deltas[0] > deltas[1]){
	    	limites[0] = centro[0]+deltas[0];
	    	limites[1] = centro[1]+deltas[0];
	    	limites[2] = centro[0]-deltas[0];
	    	limites[3] = centro[1]-deltas[0];
	    } else {
	    	limites[0] = centro[0]+deltas[1];
	    	limites[1] = centro[1]+deltas[1];
	    	limites[2] = centro[0]-deltas[1];
	    	limites[3] = centro[1]-deltas[1];
	    }
	    
	    series7.add(limites[2], limites[3]);
		series7.add(limites[2], null);
		series7.add(limites[0], limites[1]);
	    data.addSeries(series7);
	    
	    //Criação do chart e personalizacao do grafico.
	    final JFreeChart chart = ChartFactory.createXYLineChart( null, "X", "Y", data, PlotOrientation.VERTICAL, false, true, false);
	    NumberAxis yaxis = (NumberAxis)chart.getXYPlot().getRangeAxis();
	    yaxis.setAutoRangeIncludesZero(false);
	    for(int i=1, max = data.getSeriesCount(); i<max ; i++){
	    	chart.getXYPlot().getRenderer().setSeriesPaint(i, Color.BLUE);
	    }
	    chart.getXYPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setBackgroundPaint(Color.WHITE);
	    chart.setBackgroundPaint(Color.WHITE);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 580));
	    return chartPanel;
	}
	
	public ChartPanel fixPlot(double[][] pts) throws ValorInvalidoException{
	    ChartPanel chartPanel = null;
	    if(pts.length == 5){
	    	chartPanel = this.plotConica(pts);
	    } else if(pts.length == 3){
	    	chartPanel = this.plotCircunferencia(pts);
	    } else {
	    	chartPanel = this.plotReta(pts);
	    }
	    chartPanel.getChart().getXYPlot().getDomainAxis().setRange(-11, 11);
	    chartPanel.getChart().getXYPlot().getRangeAxis().setRange(-11, 11);
	    return chartPanel;
	}
	
	/**
	 * Metodo que identifica o tipo da conica e retorna um indice respectivo ao tipo.
	 * 0 - Circunferência
	 * 1 - Elipse
	 * 2 - Parabola
	 * 3 - Hiperbole 
	 * 4 - Hiperbole degenerada - Par de retas concorrentes
	 * 5 - Parabola degenerada - Par de retas paralelas
	 * 6 - Reta
	 * @param consts double[] - conjunto de constantes da conica.
	 * @return int - tipo da conica segundo determinado.
	 */
	public int tipoConica(double[] consts){
		int tipo = 0;
		
		//determina o tipo da conica.
		double valor = consts[1]*consts[1] - 4*consts[0]*consts[2];
		
		//determina a regularidade ou degeneração.
		double linha1 = 8*consts[0]*consts[2]*consts[5] - (2*consts[3]*consts[3]*consts[2]);
		double linha2 = consts[1]*consts[4]*consts[3] - (2*consts[4]*consts[4]*consts[0]);
		double linha3 = consts[3]*consts[1]*consts[4] - (2*consts[5]*consts[1]*consts[1]);
		double det = linha1 + linha2 + linha3;
		
		//Seleção para determinação de valores.
		if(valor == 0){		//Caso uma parabola
			if(det != 0){
				tipo = 2;	//Parabola regular
			} else if(consts[0] == 0 && consts[1] == 0 && consts[2] == 0){
				tipo = 6;	//Reta
			} else {
				tipo = 5;	//Parabola degenerada - Par de retas paralelas
			}
		} else if(valor > 0){	//Caso uma hiperbole
			if(det != 0){
				tipo = 3;	//Hiperbole Regular
			} else {
				tipo = 4;	//Hiperbole degenerada - Par de retas concorrentes
			}
		} else {
			if(consts[1] == 0 && consts[0] == consts[2] ){
				tipo = 0;	//Circunferencia
			} else {
				tipo = 1;	//Elipse
			}
		}
		
		return tipo;
	}
	
	/**
	 * Metodo que inicia um gráfico padrão
	 * @return ChartPanel - grafico para ser adicionado no panel do frame.
	 * @throws ValorInvalidoException exceção para tratamento de valores invalidos.
	 */
	public ChartPanel graficoBasico() throws ValorInvalidoException{
	    final XYSeries series1 = new XYSeries("1");
	    series1.add(-10, -10);
		series1.add(-10, null);
		series1.add(10, 10);
	    final XYSeriesCollection data = new XYSeriesCollection(series1);
	    
	    //Criação do chart e personalizacao do grafico.
	    final JFreeChart chart = ChartFactory.createXYLineChart( null, "X", "Y", data, PlotOrientation.VERTICAL, false, true, false);
	    NumberAxis yaxis = (NumberAxis)chart.getXYPlot().getRangeAxis();
	    yaxis.setAutoRangeIncludesZero(false);
	    chart.getXYPlot().setDomainGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
	    chart.getXYPlot().setBackgroundPaint(Color.WHITE);
	    chart.setBackgroundPaint(Color.WHITE);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 580));
	    
	    return chartPanel;
	}
	
	
}
