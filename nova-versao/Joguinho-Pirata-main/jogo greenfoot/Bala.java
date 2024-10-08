import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bala here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

public class Bala extends Actor {
    private int dano = 1;
    private Actor atirador;

    // Construtor para Pirata3
    public Bala(Pirata3 pirataAtirador) {
        this.atirador = pirataAtirador;
        setImage("bala.png");
    }

    // Construtor para Inimigo1
    public Bala(Inimigo1 inimigoAtirador) {
        this.atirador = inimigoAtirador;
        setImage("bala.png");
    }

    public void act() {
        move(10);
        verificarColisao();
       
    }

    private void verificarColisao() {
        if (atirador == null) {
        return; // Se o atirador é nulo, não faz nada
    }
        
        // Verifica colisão com Inimigo1
        if (atirador instanceof Pirata3) {
            Actor alvo = getOneIntersectingObject(Inimigo1.class);
            if (alvo != null) {
                Inimigo1 inimigo = (Inimigo1) alvo;
                inimigo.receberDano(dano);
                getWorld().removeObject(this);
                return;
            }
        }

        // Verifica colisão com Pirata3
        if (atirador instanceof Inimigo1) {
            Actor alvo = getOneIntersectingObject(Pirata3.class);
            if (alvo != null) {
                Pirata3 pirata = (Pirata3) alvo;
                pirata.receberDano(dano);
                getWorld().removeObject(this);
                return;
            }
        }
    }
}








