import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class papagaio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


   public class Papagaio extends Actor {
    private int velocidade = 2;  // Velocidade de movimento do papagaio
    private int distancia = 50;  // Distância a ser mantida atrás do pirata
    private double suavizacao = 0.06;  // Fator de suavização para o movimento do papagaio
    private boolean viradoParaDireita = true; // Direção do papagaio

    private GreenfootImage papagaioDireita = new GreenfootImage("papagaio2.png");
    private GreenfootImage papagaioEsquerda = new GreenfootImage("papagaio.png");
    
    // Constructor
    public Papagaio(){
        this.viradoParaDireita = false;
    }

    public void act() {
        seguirAtor();  // Seguir o pirata
        atualizarImagemComBaseNaTecla();  // Atualiza a imagem com base na tecla pressionada
    }

    private void seguirAtor() {
        // Verifica se o pirata ainda está no mundo
        if (getWorld().getObjects(Pirata3.class).isEmpty()) {
            // Se o pirata foi removido, o papagaio também é removido
            getWorld().removeObject(this);
            return;
        }
        
        Pirata3 pirata = (Pirata3) getWorld().getObjects(Pirata3.class).get(0);

        if (pirata != null) {
            int atorX = pirata.getX();
            int atorY = pirata.getY();

            int novoX = atorX - distancia;
            int novoY = atorY;

            int x = getX();
            int y = getY();
            int dx = novoX - x;
            int dy = novoY - y;

            setLocation(x + (int)(dx * suavizacao), y + (int)(dy * suavizacao));
        }
    }

    private void atualizarImagemComBaseNaTecla() {
        if (Greenfoot.isKeyDown("right") && !viradoParaDireita) {
            setImage(papagaioDireita);
            viradoParaDireita = true;  // Atualiza a direção para a direita
        }

        if (Greenfoot.isKeyDown("left") && viradoParaDireita) {
            setImage(papagaioEsquerda);
            viradoParaDireita = false; // Atualiza a direção para a esquerda
        }
    }
}


