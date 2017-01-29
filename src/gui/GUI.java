package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;

import dados.Forma;
import dados.Grafico;
import dados.ValorInvalidoException;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Classe da interface gráfica do projeto.
 * @author JOC, LAR, MRST e VRVS.
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private int count;
	private JPanel contentPane;
	private JPanel panelMenu;
	private JPanel panelMapa;
	private JRadioButton rdReta;
	private JRadioButton rdPontos;
	private JRadioButton rdGrafico;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JSeparator separator_1;
	private JLabel lblNewLabel;
	private JTextField textX1;
	private JTextField textY1;
	private JTextField textX2;
	private JTextField textY2;
	private JTextField textX3;
	private JTextField textY3;
	private JTextField textX4;
	private JTextField textY4;
	private JTextField textX5;
	private JTextField textY5;
	private JPanel panelFormula;
	private JLabel labelFormula;
	private JRadioButton rdCircunferencia;
	private JRadioButton rdConica;
	private JPanel panelCirc;
	private JPanel panelConi;
	private JLabel lblNewLabel_1;
	private JLabel lblGive;
	private JLabel lblPoints;
	private JPanel panelPoints;
	private JPanel panelBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
		setResizable(false);
		setTitle("Give Me Points");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(192, 30, 817, 687);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBorder(new LineBorder(new Color(255, 153, 0)));
		panelMenu.setBackground(new Color(255, 204, 102));
		panelMenu.setBounds(0, 0, 207, 661);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		rdReta = new JRadioButton("Reta");
		rdReta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdReta.isSelected()){
					textX1.setText("");
					textY1.setText("");
					textX2.setText("");
					textY2.setText("");
					textX3.setText("");
					textY3.setText("");
					textX4.setText("");
					textY4.setText("");
					textX5.setText("");
					textY5.setText("");
					count = 0;
				}
				
				rdReta.setSelected(true);
				rdCircunferencia.setSelected(false);
				rdConica.setSelected(false);
				
				panelCirc.setVisible(false);
				panelConi.setVisible(false);
				panelPoints.removeAll();
				panelPoints.repaint();
			}
		});
		rdReta.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdReta.setForeground(new Color(0, 0, 0));
		rdReta.setBackground(new Color(255, 204, 102));
		rdReta.setBounds(6, 7, 109, 23);
		panelMenu.add(rdReta);
		rdReta.setSelected(true);
		
		rdCircunferencia = new JRadioButton("Circunfer\u00EAncia");
		rdCircunferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdCircunferencia.isSelected()){
					textX1.setText("");
					textY1.setText("");
					textX2.setText("");
					textY2.setText("");
					textX3.setText("");
					textY3.setText("");
					textX4.setText("");
					textY4.setText("");
					textX5.setText("");
					textY5.setText("");
					count = 0;
				}
				
				rdReta.setSelected(false);
				rdCircunferencia.setSelected(true);
				rdConica.setSelected(false);
				
				panelCirc.setVisible(true);
				panelConi.setVisible(false);
				panelPoints.removeAll();
				panelPoints.repaint();
			}
		});
		rdCircunferencia.setForeground(Color.BLACK);
		rdCircunferencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdCircunferencia.setBackground(new Color(255, 204, 102));
		rdCircunferencia.setBounds(6, 33, 109, 23);
		panelMenu.add(rdCircunferencia);
		
		rdConica = new JRadioButton("C\u00F4nica");
		rdConica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdConica.isSelected()){
					textX1.setText("");
					textY1.setText("");
					textX2.setText("");
					textY2.setText("");
					textX3.setText("");
					textY3.setText("");
					textX4.setText("");
					textY4.setText("");
					textX5.setText("");
					textY5.setText("");
					count = 0;
				}
				
				rdReta.setSelected(false);
				rdCircunferencia.setSelected(false);
				rdConica.setSelected(true);
				
				panelCirc.setVisible(true);
				panelConi.setVisible(true);
				panelPoints.removeAll();
				panelPoints.repaint();
			}
		});
		rdConica.setForeground(Color.BLACK);
		rdConica.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdConica.setBackground(new Color(255, 204, 102));
		rdConica.setBounds(6, 59, 109, 23);
		panelMenu.add(rdConica);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 153, 102));
		separator.setBackground(new Color(255, 153, 102));
		separator.setBounds(0, 89, 207, 1);
		panelMenu.add(separator);
		
		rdPontos = new JRadioButton("Pontos");
		rdPontos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdPontos.isSelected()){
					textX1.setText("");
					textY1.setText("");
					textX2.setText("");
					textY2.setText("");
					textX3.setText("");
					textY3.setText("");
					textX4.setText("");
					textY4.setText("");
					textX5.setText("");
					textY5.setText("");
					count = 0;
				}
				
				if(!panelMapa.isShowing()){
					panelFormula.setVisible(false);
				}
				
				rdPontos.setSelected(true);
				rdGrafico.setSelected(false);
				
				textX1.setEnabled(true);
				textY1.setEnabled(true);
				textX2.setEnabled(true);
				textY2.setEnabled(true);
				textX3.setEnabled(true);
				textY3.setEnabled(true);
				textX4.setEnabled(true);
				textY4.setEnabled(true);
				textX5.setEnabled(true);
				textY5.setEnabled(true);
				
				panelPoints.setVisible(false);
				panelBase.setVisible(false);
				panelPoints.removeAll();
			}
		});
		rdPontos.setForeground(Color.BLACK);
		rdPontos.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdPontos.setBackground(new Color(255, 204, 102));
		rdPontos.setBounds(6, 98, 109, 23);
		panelMenu.add(rdPontos);
		rdPontos.setSelected(true);
		
		rdGrafico = new JRadioButton("Gr\u00E1fico");
		rdGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdGrafico.isSelected()){
					textX1.setText("");
					textY1.setText("");
					textX2.setText("");
					textY2.setText("");
					textX3.setText("");
					textY3.setText("");
					textX4.setText("");
					textY4.setText("");
					textX5.setText("");
					textY5.setText("");
					count = 0;
				}
				
				rdPontos.setSelected(false);
				rdGrafico.setSelected(true);
				
				textX1.setEnabled(false);
				textY1.setEnabled(false);
				textX2.setEnabled(false);
				textY2.setEnabled(false);
				textX3.setEnabled(false);
				textY3.setEnabled(false);
				textX4.setEnabled(false);
				textY4.setEnabled(false);
				textX5.setEnabled(false);
				textY5.setEnabled(false);
				
				panelMapa.setVisible(false);
				panelFormula.setVisible(false);
				panelPoints.setVisible(true);
				panelBase.setVisible(true);
			}
		});
		rdGrafico.setForeground(Color.BLACK);
		rdGrafico.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdGrafico.setBackground(new Color(255, 204, 102));
		rdGrafico.setBounds(6, 124, 109, 23);
		panelMenu.add(rdGrafico);
		
		btnNewButton = new JButton("Gerar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(rdReta.isSelected()){
						double[][] pts = new double[2][2];
						try {
							if(textX1.getText().equals("")){
								throw new ValorInvalidoException("X1");
							}
							pts[0][0] = getValor(textX1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X1");
						}
						try {
							if(textY1.getText().equals("")){
								throw new ValorInvalidoException("Y1");
							}	
							pts[0][1] = getValor(textY1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y1");
						}
						try {
							if(textX2.getText().equals("")){
								throw new ValorInvalidoException("X2");
							}
							pts[1][0] = getValor(textX2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X2");
						}
						try {
							if(textY2.getText().equals("")){
								throw new ValorInvalidoException("Y2");
							}
							pts[1][1] = getValor(textY2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y2");
						}
						
						ChartPanel panel = null;
						if(rdPontos.isSelected()){
							panel = new Grafico().plotReta(pts);
						} else {
							panel = new Grafico().fixPlotReta(pts);
						}
						panel.setVisible(true);
						panelMapa.removeAll();
						panelMapa.setVisible(false);
						panelMapa.add(panel);
						panelMapa.setVisible(true);
						
						Forma forma = new Forma();
						double[] consts = forma.getReta(pts[0][0], pts[0][1], pts[1][0], pts[1][1]);
						String formula = "<html>" + forma.getEquacaoReta(consts);
						formula = formula.replaceAll("x", "<I>x</I>");
						formula = formula.replaceAll("y", "<I>y</I>");
						labelFormula.setText(formula + "<BR><CENTER>(Reta)</CENTER>" + "<html>");
						panelFormula.setVisible(true);

					} else if(rdCircunferencia.isSelected()){
						
						double[][] pts = new double[3][2];
						try {
							if(textX1.getText().equals("")){
								throw new ValorInvalidoException("X1");
							}
							pts[0][0] = getValor(textX1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X1");
						}
						try {
							if(textY1.getText().equals("")){
								throw new ValorInvalidoException("Y1");
							}	
							pts[0][1] = getValor(textY1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y1");
						}
						try {
							if(textX2.getText().equals("")){
								throw new ValorInvalidoException("X2");
							}
							pts[1][0] = getValor(textX2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X2");
						}
						try {
							if(textY2.getText().equals("")){
								throw new ValorInvalidoException("Y2");
							}
							pts[1][1] = getValor(textY2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y2");
						}
						try {
							if(textX3.getText().equals("")){
								throw new ValorInvalidoException("X3");
							}
							pts[2][0] = getValor(textX3.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X3");
						}
						try {
							if(textY3.getText().equals("")){
								throw new ValorInvalidoException("Y3");
							}
							pts[2][1] = getValor(textY3.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y3");
						}
						
						ChartPanel panel = null;
						if(rdPontos.isSelected()){
							panel = new Grafico().plotCircunferencia(pts);
						} else {
							panel = new Grafico().fixPlot(pts);
						}
						panel.setVisible(true);
						panelMapa.removeAll();
						panelMapa.setVisible(false);
						panelMapa.add(panel);
						panelMapa.setVisible(true);
						
						Forma forma = new Forma();
						double[] consts = forma.getCircunferencia(pts[0][0], pts[0][1], pts[1][0], pts[1][1], pts[2][0], pts[2][1]);
						String formula = "<html>" + forma.getEquacaoCircunferencia(consts);
						formula = formula.replaceAll("e2", "<sup>2</sup>");
						formula = formula.replaceAll("e2", "<sup>2</sup>");
						formula = formula.replaceAll("x", "<I>x</I>");
						formula = formula.replaceAll("y", "<I>y</I>");
						labelFormula.setText(formula + "<BR><CENTER>(Circunferência)</CENTER>" + "<html>");
						panelFormula.setVisible(true);
					
					} else {
						double[][] pts = new double[5][2];
						try {
							if(textX1.getText().equals("")){
								throw new ValorInvalidoException("X1");
							}
							pts[0][0] = getValor(textX1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X1");
						}
						try {
							if(textY1.getText().equals("")){
								throw new ValorInvalidoException("Y1");
							}	
							pts[0][1] = getValor(textY1.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y1");
						}
						try {
							if(textX2.getText().equals("")){
								throw new ValorInvalidoException("X2");
							}
							pts[1][0] = getValor(textX2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X2");
						}
						try {
							if(textY2.getText().equals("")){
								throw new ValorInvalidoException("Y2");
							}
							pts[1][1] = getValor(textY2.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y2");
						}
						try {
							if(textX3.getText().equals("")){
								throw new ValorInvalidoException("X3");
							}
							pts[2][0] = getValor(textX3.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X3");
						}
						try {
							if(textY3.getText().equals("")){
								throw new ValorInvalidoException("Y3");
							}
							pts[2][1] = getValor(textY3.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y3");
						}
						try {
							if(textX4.getText().equals("")){
								throw new ValorInvalidoException("X4");
							}
							pts[3][0] = getValor(textX4.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X4");
						}
						try {
							if(textY4.getText().equals("")){
								throw new ValorInvalidoException("Y4");
							}
							pts[3][1] = getValor(textY4.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y4");
						}
						try {
							if(textX5.getText().equals("")){
								throw new ValorInvalidoException("X5");
							}
							pts[4][0] = getValor(textX5.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("X5");
						}
						try {
							if(textY5.getText().equals("")){
								throw new ValorInvalidoException("Y5");
							}
							pts[4][1] = getValor(textY5.getText());
						} catch (ScriptException e1) {
							throw new ValorInvalidoException("Y5");
						}
						
						ChartPanel panel = null;
						if(rdPontos.isSelected()){
							panel = new Grafico().plotConica(pts);
						} else {
							panel = new Grafico().fixPlot(pts);
						}
						panel.setVisible(true);
						panelMapa.removeAll();
						panelMapa.setVisible(false);
						panelMapa.add(panel);
						panelMapa.setVisible(true);
						
						Forma forma = new Forma();
						String[] tipos = {"(Circunferência)",
										  "(Elipse)",
										  "(Parábola)",
										  "(Hipérbole)",
										  "(Retas Concorrentes)",
										  "(Retas Paralelas)",
										  "(Reta)"};
						double[] consts = forma.getConica(pts[0][0], pts[0][1], pts[1][0], pts[1][1], pts[2][0], pts[2][1], pts[3][0], pts[3][1], pts[4][0], pts[4][1]);
						String formula = "<html>" + forma.getEquacaoConica(consts);
						formula = formula.replaceAll("e2", "<sup>2</sup>");
						formula = formula.replaceAll("e2", "<sup>2</sup>");
						formula = formula.replaceAll("x", "<I>x</I>");
						formula = formula.replaceAll("y", "<I>y</I>");
						labelFormula.setText(formula + "<BR><CENTER>" + tipos[new Grafico().tipoConica(consts)] + "</CENTER><html>");
						panelFormula.setVisible(true);
					}
				} catch (ValorInvalidoException e1){
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
				}
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(255, 102, 0)));
		btnNewButton.setBackground(new Color(255, 192, 80));
		btnNewButton.setBounds(6, 154, 89, 23);
		panelMenu.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textX1.setText("");
				textY1.setText("");
				textX2.setText("");
				textY2.setText("");
				textX3.setText("");
				textY3.setText("");
				textX4.setText("");
				textY4.setText("");
				textX5.setText("");
				textY5.setText("");
				
				panelMapa.setVisible(false);
				panelFormula.setVisible(false);
				if(rdGrafico.isSelected()){
					panelFormula.setVisible(true);
				} else {
					panelFormula.setVisible(false);
				}
				labelFormula.setText("");
				panelPoints.removeAll();
				panelPoints.repaint();
				count = 0;
			}
		});
		btnNewButton_1.setBorder(new LineBorder(new Color(255, 102, 0)));
		btnNewButton_1.setBackground(new Color(255, 192, 80));
		btnNewButton_1.setBounds(108, 154, 89, 23);
		panelMenu.add(btnNewButton_1);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 153, 102));
		separator_1.setBackground(new Color(255, 153, 102));
		separator_1.setBounds(0, 188, 207, 1);
		panelMenu.add(separator_1);
		
		lblNewLabel = new JLabel("X1:");
		lblNewLabel.setBounds(6, 204, 22, 14);
		panelMenu.add(lblNewLabel);
		
		textX1 = new JTextField();
		textX1.setDisabledTextColor(Color.BLACK);
		textX1.setBorder(new LineBorder(new Color(255, 102, 0)));
		textX1.setHorizontalAlignment(SwingConstants.LEFT);
		textX1.setBounds(29, 201, 168, 20);
		panelMenu.add(textX1);
		textX1.setColumns(10);
		
		JLabel lblY = new JLabel("Y1:");
		lblY.setBounds(6, 232, 22, 14);
		panelMenu.add(lblY);
		
		textY1 = new JTextField();
		textY1.setDisabledTextColor(Color.BLACK);
		textY1.setBorder(new LineBorder(new Color(255, 102, 0)));
		textY1.setHorizontalAlignment(SwingConstants.LEFT);
		textY1.setColumns(10);
		textY1.setBounds(29, 229, 168, 20);
		panelMenu.add(textY1);
		
		JLabel lblX = new JLabel("X2:");
		lblX.setBounds(6, 260, 22, 14);
		panelMenu.add(lblX);
		
		textX2 = new JTextField();
		textX2.setDisabledTextColor(Color.BLACK);
		textX2.setBorder(new LineBorder(new Color(255, 102, 0)));
		textX2.setHorizontalAlignment(SwingConstants.LEFT);
		textX2.setColumns(10);
		textX2.setBounds(29, 257, 168, 20);
		panelMenu.add(textX2);
		
		JLabel lblY_1 = new JLabel("Y2:");
		lblY_1.setBounds(6, 288, 22, 14);
		panelMenu.add(lblY_1);
		
		textY2 = new JTextField();
		textY2.setDisabledTextColor(Color.BLACK);
		textY2.setBorder(new LineBorder(new Color(255, 102, 0)));
		textY2.setHorizontalAlignment(SwingConstants.LEFT);
		textY2.setColumns(10);
		textY2.setBounds(29, 285, 168, 20);
		panelMenu.add(textY2);
		
		panelCirc = new JPanel();
		panelCirc.setBackground(new Color(255, 204, 102));
		panelCirc.setBounds(1, 305, 205, 61);
		panelMenu.add(panelCirc);
		panelCirc.setLayout(null);
		panelCirc.setVisible(false);
		
		JLabel lblX_1 = new JLabel("X3:");
		lblX_1.setBounds(5, 14, 22, 14);
		panelCirc.add(lblX_1);
		
		textX3 = new JTextField();
		textX3.setDisabledTextColor(Color.BLACK);
		textX3.setHorizontalAlignment(SwingConstants.LEFT);
		textX3.setColumns(10);
		textX3.setBorder(new LineBorder(new Color(255, 102, 0)));
		textX3.setBounds(28, 11, 168, 20);
		panelCirc.add(textX3);
		
		JLabel lblY_2 = new JLabel("Y3:");
		lblY_2.setBounds(5, 42, 22, 14);
		panelCirc.add(lblY_2);
		
		textY3 = new JTextField();
		textY3.setDisabledTextColor(Color.BLACK);
		textY3.setHorizontalAlignment(SwingConstants.LEFT);
		textY3.setColumns(10);
		textY3.setBorder(new LineBorder(new Color(255, 102, 0)));
		textY3.setBounds(28, 39, 168, 20);
		panelCirc.add(textY3);
		
		panelConi = new JPanel();
		panelConi.setBackground(new Color(255, 204, 102));
		panelConi.setBounds(1, 364, 205, 127);
		panelMenu.add(panelConi);
		panelConi.setLayout(null);
		panelConi.setVisible(false);
		
		JLabel lblX_2 = new JLabel("X4:");
		lblX_2.setBounds(5, 14, 22, 14);
		panelConi.add(lblX_2);
		
		textX4 = new JTextField();
		textX4.setDisabledTextColor(Color.BLACK);
		textX4.setHorizontalAlignment(SwingConstants.LEFT);
		textX4.setColumns(10);
		textX4.setBorder(new LineBorder(new Color(255, 102, 0)));
		textX4.setBounds(28, 11, 168, 20);
		panelConi.add(textX4);
		
		JLabel lblY_3 = new JLabel("Y4:");
		lblY_3.setBounds(5, 42, 22, 14);
		panelConi.add(lblY_3);
		
		textY4 = new JTextField();
		textY4.setDisabledTextColor(Color.BLACK);
		textY4.setHorizontalAlignment(SwingConstants.LEFT);
		textY4.setColumns(10);
		textY4.setBorder(new LineBorder(new Color(255, 102, 0)));
		textY4.setBounds(28, 39, 168, 20);
		panelConi.add(textY4);
		
		JLabel lblX_3 = new JLabel("X5:");
		lblX_3.setBounds(5, 70, 22, 14);
		panelConi.add(lblX_3);
		
		textX5 = new JTextField();
		textX5.setDisabledTextColor(Color.BLACK);
		textX5.setHorizontalAlignment(SwingConstants.LEFT);
		textX5.setColumns(10);
		textX5.setBorder(new LineBorder(new Color(255, 102, 0)));
		textX5.setBounds(28, 67, 168, 20);
		panelConi.add(textX5);
		
		JLabel lblY_4 = new JLabel("Y5:");
		lblY_4.setBounds(5, 98, 22, 14);
		panelConi.add(lblY_4);
		
		textY5 = new JTextField();
		textY5.setDisabledTextColor(Color.BLACK);
		textY5.setHorizontalAlignment(SwingConstants.LEFT);
		textY5.setColumns(10);
		textY5.setBorder(new LineBorder(new Color(255, 102, 0)));
		textY5.setBounds(28, 95, 168, 20);
		panelConi.add(textY5);
		
		panelMapa = new JPanel();
		panelMapa.setBackground(new Color(255, 255, 255));
		panelMapa.setBounds(206, 0, 608, 580);
		contentPane.add(panelMapa);
		panelMapa.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelMapa.setVisible(false);
		
		panelFormula = new JPanel();
		panelFormula.setBackground(new Color(255, 255, 255));
		panelFormula.setBounds(206, 579, 608, 82);
		contentPane.add(panelFormula);
		panelFormula.setLayout(null);
		panelFormula.setVisible(false);
		
		labelFormula = new JLabel("Test");
		labelFormula.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		labelFormula.setHorizontalTextPosition(SwingConstants.CENTER);
		labelFormula.setHorizontalAlignment(SwingConstants.CENTER);
		labelFormula.setBounds(3, 0, 605, 71);
		panelFormula.add(labelFormula);
		
		panelPoints = new JPanel();
		panelPoints.addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseMoved(MouseEvent mouse) {
				switch(count){
				case 0:	textX1.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
						textY1.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
						break;
				case 1: textX2.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
						textY2.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
						break;
				case 2:	textX3.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
						textY3.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
						break;
				case 3:	textX4.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
						textY4.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
						break;
				case 4:	textX5.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
						textY5.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
						break;
				}
			}
		});
		panelPoints.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouse) {
				boolean draw = true;
				if(rdReta.isSelected() && count == 2){
					draw = false;
				} else if(rdCircunferencia.isSelected() && count == 3){
					draw = false;
				} else if(count == 5){
					draw = false;
				}
				
				if(draw){
					switch(count){
						case 0:	textX1.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
								textY1.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
								break;
						case 1: textX2.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
								textY2.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
								break;
						case 2:	textX3.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
								textY3.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
								break;
						case 3:	textX4.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
								textY4.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
								break;
						case 4:	textX5.setText(Math.round(((mouse.getX() - 240)/480.0)*200)/10.0 + "");
								textY5.setText(Math.round((-(mouse.getY() - 238)/476.0)*200)/10.0 + "");
								break;
					}
					panelPoints.setVisible(true);
					JPanel Point = new JPanel();
					Point.setBounds(mouse.getX()-2, mouse.getY()-2, 5, 5);
					Point.setBackground(Color.BLUE);
					Point.setLayout(null);
					Point.setVisible(true);
					panelPoints.add(Point);
					panelPoints.repaint();
					count++;
				}
			}
			public void mouseExited(MouseEvent mouse) {
				switch(count){
				case 0:	textX1.setText("");
						textY1.setText("");
						break;
				case 1: textX2.setText("");
						textY2.setText("");
						break;
				case 2:	textX3.setText("");
						textY3.setText("");
						break;
				case 3:	textX4.setText("");
						textY4.setText("");
						break;
				case 4:	textX5.setText("");
						textY5.setText("");
						break;
				}
			}
		});
		panelPoints.setBounds(294, 36, 481, 476);
		contentPane.add(panelPoints);
		panelPoints.setOpaque(false);
		panelPoints.setBackground(Color.WHITE);
		panelPoints.setLayout(null);
		panelPoints.setVisible(false);
		
		panelBase = new JPanel();
		panelBase.setBounds(206, 0, 605, 658);
		contentPane.add(panelBase);
		panelBase.setLayout(null);
		panelBase.setVisible(false);
		JLabel labelGraph = new JLabel(new ImageIcon(getClass().getResource("/grafico.jpg")));
		labelGraph.setSize(605, 658);
		panelBase.add(labelGraph);
		
		lblGive = new JLabel("Give");
		lblGive.setVerticalAlignment(SwingConstants.TOP);
		lblGive.setHorizontalAlignment(SwingConstants.CENTER);
		lblGive.setForeground(new Color(255, 204, 102));
		lblGive.setFont(new Font("Calibri", Font.BOLD, 99));
		lblGive.setBorder(null);
		lblGive.setBounds(350, 173, 273, 95);
		contentPane.add(lblGive);
		
		lblPoints = new JLabel("Points");
		lblPoints.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setForeground(new Color(255, 204, 102));
		lblPoints.setFont(new Font("Calibri", Font.BOLD, 99));
		lblPoints.setBorder(null);
		lblPoints.setBounds(363, 339, 413, 102);
		contentPane.add(lblPoints);
		
		lblNewLabel_1 = new JLabel("Me");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setForeground(new Color(255, 204, 102));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 99));
		lblNewLabel_1.setBounds(206, 0, 608, 612);
		contentPane.add(lblNewLabel_1);
	}
	
	/**
	 * Metodo que recebe uma string respectiva a um valor numero e retorna o valor resultante da formula
	 * @param formula String - formula a ser execultada
	 * @return double - valor resultante da formula
	 * @throws ScriptException - erro de resolução da equação
	 */
	public double getValor(String formula) throws ScriptException{
		String[] functions = {"abs","acos","asin","atan","atan2","cbrt","ceil","copySign","cos","cosh","exp","E","expm1","floor",
							"getExponent","hypot","IEEEremainder","log","log10","log1p","max","min","nextAfter","nextUp","pow","PI",
							"random","rint","round","scalb","signum","sin","sinh","sqrt","tan","tanh","toDegrees","toRadians","ulp"};
		for(int i = 0, len = functions.length ; i<len ; i++){
			formula = formula.replaceAll(functions[i], "Math." + functions[i]);
		}
		
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    double valor = (double)engine.eval(formula + " + 0.0");
		return valor;
	}
}
