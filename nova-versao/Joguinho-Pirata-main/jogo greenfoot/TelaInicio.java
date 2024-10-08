import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TelaInicio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TelaInicio extends World
{
    /**
     * Constructor for objects of class TelaInicio.
     * 
     */
    
    public TelaInicio()
    {    
        // Create a new world with 1200x500 cells with a cell size of 1x1 pixels.
        super(1200, 500, 1);
        
        GreenfootImage imagemInicioJogo = new GreenfootImage("imagemInicioJogo.jpg");
        
        setBackground(imagemInicioJogo);
    }
    
    public void act(){
        // Se o usuário pressionar a tecla "Enter"
        if (Greenfoot.isKeyDown("enter")) {
            // Mude para o mundo principal do jogo
            Greenfoot.setWorld(new IntroducaoHistoria());
        }
    }
}
