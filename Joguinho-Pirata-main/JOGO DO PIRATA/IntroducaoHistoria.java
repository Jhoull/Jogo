import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;

public class IntroducaoHistoria extends World {
    /*Era uma vez um pirata que navegava pelos sete mares em busca de tesouros...
Ele tinha um fiel companheiro, um papagaio chamado Pepe...
Pepe era um papagaio especial, capaz de entender mapas e interpretar pistas como ninguém...
Um dia, o pirata encontrou um mapa antigo que estava no meio de uma ilha enterrado na areia...
Ele desenrolou o mapa e percebeu que estava repleto de símbolos estranhos e enigmas que ele não conseguia decifrar...
Frustrado, ele mostrou o mapa a Pepe...
Que imediatamente começou a bicar e apontar com o bico para vários pontos no mapa, como se estivesse tentando comunicar algo...
O pirata observou Pepe com atenção e percebeu que o papagaio estava formando um padrão...
Mas enquanto tudo isso ocorria...
Ele não podia contar com outro pirata querendo roubar seu incrível mapa!!!
Enquanto os piratas lutavam entre si pelo mapa do tesouro...
Pepe tentava mais e mais decifrar o Mapa do tesouro durante essa batalha épica...
Onde somente um pode vencer...
E essa é a historia que voce vai vivenciar.
             
       */
    
    private String[] historia = {
        "Era uma vez um pirata que navegava pelos sete mares em busca de tesouros...",
        "Ele tinha um fiel companheiro, um papagaio chamado Pepe...",
        "Pepe era um papagaio especial, capaz de entender mapas e interpretar pistas como ninguém...",
        "Um dia, o pirata encontrou um mapa antigo que estava no meio de uma ilha enterrado na areia...",
        "Ele desenrolou o mapa e percebeu uma coisa...",
        "Estava repleto de símbolos estranhos e enigmas que ele não conseguia decifrar...",
        "Frustrado, ele mostrou o mapa a Pepe...",
        "Que imediatamente começou a bicar e apontar com o bico para vários pontos no mapa...",
        "Como se estivesse tentando comunicar algo...",
        "O pirata observou Pepe com atenção e percebeu que o papagaio estava formando um padrão...",
        "Mas enquanto tudo isso ocorria...",
        "Ele não podia contar com outro pirata querendo roubar seu incrível mapa!!!",
        "Enquanto os piratas lutavam entre si pelo mapa do tesouro...",
        "Pepe tentava mais e mais decifrar o Mapa do tesouro durante essa batalha épica...",
        "Onde somente um pode vencer...",
        "E essa é a historia que voce vai vivenciar.",
        "A jornada está prestes a começar!"
    };
    
    private String aperteEspaco="Aperte ESPAÇO para continuar.";
    private String aperteIparaPular="Aperte i para pular a historia.";
    
    
        private int indiceFrase = 0;  // Controla qual frase estamos mostrando no momento
    private String fraseAtual = "";  // Armazena a frase atual
    private String textoParcial = "";  // Texto que será mostrado progressivamente
    private int indiceLetra = 0;  // Controla qual letra da frase está sendo exibida
    
    private int delayEscrita = 2;  // Delay para a escrita do texto (ajusta a velocidade)
    private int contadorDelay = 0; // Contador para o delay

    public IntroducaoHistoria() {    
        // Cria o mundo com as dimensões desejadas
        super(1200, 500, 1); 
        setBackground("imagemHistoriaJogo.jpg"); // Define o fundo da tela de introdução
        fraseAtual = historia[indiceFrase];  // Inicializa com a primeira frase
    }
    
    public void skipHistoriaAoPressionarI(){
        if(Greenfoot.isKeyDown("i")){
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    

    public void act() {
        showText(aperteEspaco, getWidth() / 2, getHeight() -150 ); //Aperte espaço para continuar
        showText(aperteIparaPular, getWidth()/2, getHeight()-110);
        
        skipHistoriaAoPressionarI();
        
        
        // Efeito de máquina de escrever: adiciona letras progressivamente
        if (contadorDelay == delayEscrita) {
            if (indiceLetra < fraseAtual.length()) {
                textoParcial += fraseAtual.charAt(indiceLetra); // Adiciona a próxima letra à frase
                indiceLetra++;
                showText(textoParcial, getWidth() / 2, getHeight() / 2); // Mostra o texto parcial
            }
            contadorDelay = 0; // Reseta o contador
        } else {
            contadorDelay++; // Incrementa o contador para o delay
        }
        
        // Avança para a próxima frase quando a tecla Espaço for pressionada e a frase atual terminou
        if (Greenfoot.isKeyDown("space") && indiceLetra >= fraseAtual.length()) {
            if (indiceFrase < historia.length - 1) {
                indiceFrase++;
                fraseAtual = historia[indiceFrase];  // Muda para a próxima frase
                textoParcial = "";  // Reseta o texto parcial
                indiceLetra = 0;  // Reseta o índice da letra
                Greenfoot.delay(10); // Pequeno atraso para evitar múltiplos avanços ao apertar espaço
            } else {
                Greenfoot.setWorld(new MyWorld()); // Vai para o mundo principal (jogo) após a última frase
            }
        }
    }
}