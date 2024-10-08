import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe Papagaio que representa um papagaio que segue o pirata
 * e muda de imagem com base na direção em que o papagaio está virado.
 */
public class Papagaio extends Actor {
    private int velocidade = 2;  // Velocidade de movimento do papagaio
    private int distancia = 50;  // Distância a ser mantida atrás do pirata
    private double suavizacao = 0.06;  // Fator de suavização para o movimento do papagaio
    private boolean viradoParaDireita = true; // Direção do papagaio

    private GreenfootImage papagaioDireita = new GreenfootImage("papagaio2.png");
    private GreenfootImage papagaioEsquerda = new GreenfootImage("papagaio.png");
    
    // Construtor da classe
    public Papagaio(){
        this.viradoParaDireita = false; // Inicialmente, o papagaio está virado para a esquerda
    }

    // Método chamado a cada ato do mundo
    public void act() {
        seguirAtor();  // Faz o papagaio seguir o pirata
        atualizarImagemComBaseNaTecla();  // Atualiza a imagem do papagaio com base na tecla pressionada
    }

    // Método para seguir o pirata
    private void seguirAtor() {
        // Verifica se o pirata ainda está no mundo
        if (getWorld().getObjects(Pirata3.class).isEmpty()) {
            // Se o pirata foi removido, o papagaio também é removido do mundo
            getWorld().removeObject(this);
            return;
        }
        
        // Obtém a referência do pirata no mundo
        Pirata3 pirata = (Pirata3) getWorld().getObjects(Pirata3.class).get(0);

        if (pirata != null) { // Se o pirata existe
            // Obtém as coordenadas do pirata
            int atorX = pirata.getX();
            int atorY = pirata.getY();

            // Calcula a nova posição do papagaio com base na distância
            int novoX = atorX - distancia;
            int novoY = atorY;

            // Obtém a posição atual do papagaio
            int x = getX();
            int y = getY();
            int dx = novoX - x;
            int dy = novoY - y;

            // Atualiza a posição do papagaio suavemente para seguir o pirata
            setLocation(x + (int)(dx * suavizacao), y + (int)(dy * suavizacao));
        }
    }

    // Método para atualizar a imagem do papagaio com base na tecla pressionada
    private void atualizarImagemComBaseNaTecla() {
        // Se a tecla "right" é pressionada e o papagaio está virado para a esquerda
        if (Greenfoot.isKeyDown("right") && !viradoParaDireita) {
            setImage(papagaioDireita); // Define a imagem do papagaio virado para a direita
            viradoParaDireita = true;  // Atualiza a direção para a direita
        }

        // Se a tecla "left" é pressionada e o papagaio está virado para a direita
        if (Greenfoot.isKeyDown("left") && viradoParaDireita) {
            setImage(papagaioEsquerda); // Define a imagem do papagaio virado para a esquerda
            viradoParaDireita = false; // Atualiza a direção para a esquerda
        }
    }
}
