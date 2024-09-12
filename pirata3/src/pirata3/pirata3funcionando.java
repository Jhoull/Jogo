package pirata3;

public class pirata3funcionando {

	public class Pirata3 extends Actor {
	    private int speed = 4;
	    private int jumpStrength = -15;
	    private int gravity = 1;
	    private int verticalSpeed = 0;
	    private int groundLevel = 380;
	    private int vida = 30;
	    private String direcao = "right"; // Direção inicial
	    private boolean comArma = false; // Variável que indica se o pirata está com a roupa de arma

	    private GreenfootImage pirataDireitaComArma = new GreenfootImage("piratinhaArma.png");
	    private GreenfootImage pirataEsquerdaComArma = new GreenfootImage("piratinhaArma2.png");
	    private GreenfootImage pirataSemArma = new GreenfootImage("piratinha2.png");

	    public void act() {
	        moveHorizontal(); // Movimentação lateral
	        fall(); // Aplicar gravidade e controlar queda

	        if (Greenfoot.isKeyDown("up") && onGround()) {
	            jump(); // Permitir pulo se o ator estiver no chão
	        }

	        if (Greenfoot.isKeyDown("space") && comArma) {
	            atirar(); // Só atira se o pirata estiver com a roupa de arma
	        }

	        if (Greenfoot.isKeyDown("c")) { // Tecla 'c' para mudar de roupa
	            mudarRoupa();
	        }

	        // Atualizar a posição vertical do ator
	        setLocation(getX(), getY() + verticalSpeed);
	    }

	    private void moveHorizontal() {
	        if (Greenfoot.isKeyDown("right")) {
	            if (getX() + speed < getWorld().getWidth()) {
	                setLocation(getX() + speed, getY());
	                direcao = "right"; // Atualiza a direção para a direita
	                atualizarImagem(); // Atualiza a imagem do pirata
	            }
	        }
	        if (Greenfoot.isKeyDown("left")) {
	            if (getX() - speed > 0) {
	                setLocation(getX() - speed, getY());
	                direcao = "left"; // Atualiza a direção para a esquerda
	                atualizarImagem(); // Atualiza a imagem do pirata
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

	    private void jump() {
	        verticalSpeed = jumpStrength;
	    }

	    private boolean onGround() {
	        return getY() >= groundLevel;
	    }

	    public void receberDano(int dano) {
	        vida -= dano;
	        if (vida <= 0) {
	            getWorld().removeObject(this);
	        }
	    }

	    private void atirar() {
	        Bala bala = new Bala(this);

	        int balaX = getX();
	        int balaY = getY();

	        // Define a direção da bala com base na direção do pirata
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
	            setImage(pirataSemArma); // Troca para a roupa sem arma
	        } else {
	            atualizarImagem(); // Define a roupa com arma com base na direção
	        }
	        comArma = !comArma; // Alterna o estado da variável
	    }

	    private void atualizarImagem() {
	        if (comArma) {
	            if (direcao.equals("right")) {
	                setImage(pirataDireitaComArma); // Define a imagem com a arma à direita
	            } else {
	                setImage(pirataEsquerdaComArma); // Define a imagem com a arma à esquerda
	            }
	        } else {
	            setImage(pirataSemArma); // Define a imagem sem arma
	        }
	    }
	}
