# Give Me Points - Construindo Curvas por Pontos Especificados 

O programa "Give Me Points" para confecção de curvas por pontos especificados é uma aplicação resultante do projeto da cadeira de Álgebra Vetorial Linear para a computação (AVLC - MA531) no Centro de Informática da UFPE (Cin - Campus Recife).

## Introdução 

O projeto utiliza de uma técnica que usa como base o calculo de determinantes para definir equações de retas, circunferências e cônicas (sejam elas degeneradas ou não). A técnica se sustenta no seguinte teorema: 

> Um sistema linear homogêneo com o mesmo número de equações e de incógnitas tem uma solução não trivial, se somente se, a determinante da matriz de coeficientes é zero. 

## Sobre aplicação 

O programa possui a capacidade de plotar retas, circunferências e cônicas através de 2, 3 e 5 pontos, respectivamente. O aplicativo além de mostrar todas as curvas obtidas, também mostra a respectiva equação e indica a especificação do que se trata a equação, isso é, se corresponde a uma reta, circunferência, elipse, hipérbole, retas concorrentes...

![Inicio](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/1.png?raw=true)
![Plot de reta](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/2.png?raw=true)

## Funcionalidades

como algumas funcionalidades da aplicação podemos citar:

  - Apresentar a curva através de um gráfico, assim como especificar a curva e mostrar a suas equação.
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/3.png?raw=true)
  - Permitir entradas com expressões matemáticas, o que permite qualquer "input" de pontos de forma precisa.
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/4.png?raw=true)
  - Entradas de pontos através das caixas de texto laterais no modo "pontos", ou através de clicks do mouse sobre a imagem no modo "gráfico"
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/9.jpeg?raw=true)
  - Permite dar zoom sobre um fragmento do gráfico - Função derivada da biblioteca JFreeChart
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/5.jpeg?raw=true)
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/6.jpeg?raw=true)
  - Permite visualizar pontos expecificos do gráfico além dos especificados - Função derivada da biblioteca JFreeChart
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/7.jpeg?raw=true)
  - Através do menu, click com botão direito do mouse sobre mapa, é possível realizar outra função secundárias: imprimir, salvar...
![dados](https://github.com/Lucas-Rufino/Give-Me-Points/blob/master/doc/8.jpeg?raw=true)