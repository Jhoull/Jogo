import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class inimigo1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

public class Inimigo1 extends Actor {
    private int speed = 4;
    private int jumpStrength = -15;
    private int gravity = 1;
    private int verticalSpeed = 0;
    private int groundLevel = 380;
    private int vida = 30;
    private String direcao;
    private boolean comArma;

    private int tempoEntreDisparos = 30; // Intervalo entre disparos (ajuste conforme necessário)
    private int contadorDisparo = 0; // Contador para o temporizador

    private GreenfootImage inimigoDireitaComArma = new GreenfootImage("inimigo1Arma.png");
    private GreenfootImage inimigoEsquerdaComArma = new GreenfootImage("inimigo1Arma2.png");
    private GreenfootImage inimigoSemArma = new GreenfootImage("inimigo1.png");

    public Inimigo1() {
        this.direcao = "right";
        this.comArma = false;
        setImage(inimigoSemArma); // Inimigo começa sem arma
    }

    public void act() {
        moveHorizontal(); // Movimentação lateral
        fall(); // Aplicar gravidade e controlar queda
        pularNoChao(); // Permitir que o inimigo pule
        atirarComRoupa(); // Atirar se tiver com a arma
        verificarTrocaDeRoupa(); // Verificar se a roupa deve ser trocada
        setLocation(getX(), getY() + verticalSpeed); // Atualiza a posição vertical do inimigo
    }

    private void moveHorizontal() {
        if (Greenfoot.isKeyDown("d")) {
            if (getX() + speed < getWorld().getWidth()) {
                setLocation(getX() + speed, getY());
                direcao = "right"; // Atualiza a direção para a direita
                atualizarImagem(); // Atualiza a imagem do inimigo
            }
        }
        if (Greenfoot.isKeyDown("a")) {
            if (getX() - speed > 0) {
                setLocation(getX() - speed, getY());
                direcao = "left"; // Atualiza a direção para a esquerda
                atualizarImagem(); // Atualiza a imagem do inimigo
            }
        }
    }

    private void fall() {
        if (getY() < groundLevel) {
            verticalSpeed += gravity;
        } else {
            verticalSpeed = 0;
            setLocation(getX(), groundLevel);
        }
    }

    private void pularNoChao() {
        if (Greenfoot.isKeyDown("w") && onGround()) {
            jump();
        }
    }

    private void jump() {
        verticalSpeed = jumpStrength;
    }

    private boolean onGround() {
        return getY() >= groundLevel;
    }

    private void atirarComRoupa() {
        if (Greenfoot.isKeyDown("f") && comArma) { // "F" para atirar
            contadorDisparo++;
            if (contadorDisparo >= tempoEntreDisparos) {
                atirar();
                contadorDisparo = 0; // Reinicia o contador
            }
        }
    }

    private void verificarTrocaDeRoupa() {
        if (Greenfoot.isKeyDown("e")) { // "E" para trocar de roupa
            mudarRoupa();
        }
    }

    private void atirar() {
        Bala bala = new Bala(this); // Cria uma nova bala
        int balaX = getX();
        int balaY = getY();

        // Define a direção da bala com base na direção do inimigo
        if (direcao.equals("right")) {
            getWorld().addObject(bala, balaX + 20, balaY); // Adiciona a bala à direita
            bala.setRotation(0); // Define a rotação para a direita (0 graus)
        } else {
            getWorld().addObject(bala, balaX - 20, balaY); // Adiciona a bala à esquerda
            bala.setRotation(180); // Define a rotação para a esquerda (180 graus)
        }
    }

    private void mudarRoupa() {
        if (comArma) {
            setImage(inimigoSemArma); // Troca para a roupa sem arma
        } else {
            atualizarImagem(); // Define a roupa com arma com base na direção
        }
        comArma = !comArma; // Alterna o estado da variável
    }

    private void atualizarImagem() {
        if (comArma) {
            if (direcao.equals("right")) {
                setImage(inimigoDireitaComArma); // Define a imagem com a arma à direita
            } else {
                setImage(inimigoEsquerdaComArma); // Define a imagem com a arma à esquerda
            }
        } else {
            setImage(inimigoSemArma); // Define a imagem sem arma
        }
    }

    public int getVida() {
        return vida;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida <= 0) {
            getWorld().removeObject(this);
        }
    }
}

    



