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

    public void act() {
        moveHorizontal(); // Movimentação lateral
        fall(); // Aplicar gravidade e controlar queda

        if (Greenfoot.isKeyDown("up") && onGround()) {
            jump(); // Permitir pulo se o ator estiver no chão
        }

        if (Greenfoot.isKeyDown("space")) {
             // Atirar se a tecla de espaço estiver pressionada
        }

        // Atualizar a posição vertical do ator
        setLocation(getX(), getY() + verticalSpeed);
    }

    private void moveHorizontal() {
        if (Greenfoot.isKeyDown("right")) {
            if (getX() + speed < getWorld().getWidth()) {
                setLocation(getX() + speed, getY());
            }
        }
        if (Greenfoot.isKeyDown("left")) {
            if (getX() - speed > 0) {
                setLocation(getX() - speed, getY());
            }
        }
    }

    private void fall() {
        // Aplicar gravidade apenas se o ator estiver acima do chão invisível
        if (getY() < groundLevel) {
            verticalSpeed += gravity;
        } else {
            // Se o ator alcançar o chão invisível, parar a queda
            verticalSpeed = 0;
            setLocation(getX(), groundLevel); // Garantir que o ator fique exatamente no "chão invisível"
        }
    }

    private void jump() {
        verticalSpeed = jumpStrength; // Aplicar a força do pulo
    }

    private boolean onGround() {
        // Verifica se o ator está no chão ou no "chão invisível"
        return getY() >= groundLevel;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida <= 0) {
            getWorld().removeObject(this);
        }
    }

}    
