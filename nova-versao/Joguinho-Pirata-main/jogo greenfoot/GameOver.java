import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    private String aperteEspacoParaReiniciar="Aperte ESPACO para Reiniciar o Jogo";
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 500, 1);
        
        GreenfootImage gameOver= new GreenfootImage("gameOver.jpg");
        
        setBackground(gameOver);
    }
    
    public void mostrarTextoReinicio(){
        showText(aperteEspacoParaReiniciar, getWidth() / 2, getHeight() -150 );
        
        
    }
    
    public void act(){
        mostrarTextoReinicio();
        
        if(Greenfoot.isKeyDown("space")){
            //mudar para a tela inicial
            Greenfoot.setWorld(new TelaInicio());
        }
        
    }
}
