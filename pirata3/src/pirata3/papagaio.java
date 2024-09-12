package pirata3;

public class Papagaio extends Actor {
    private int velocidade = 2;  // Velocidade de movimento do papagaio
    private int distancia = 50;  // Distância a ser mantida atrás do pirata
    private double suavizacao = 0.06;  // Fator de suavização para o movimento do papagaio
    private boolean viradoParaDireita = true; // Direção do papagaio

    private GreenfootImage papagaioDireita = new GreenfootImage("papagaio2.png");
    private GreenfootImage papagaioEsquerda = new GreenfootImage("papagaio.png");

    public void act() {
        seguirAtor();
        atualizarImagemComBaseNaTecla(); // Atualiza a imagem com base na tecla pressionada
    }

    private void seguirAtor() {
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