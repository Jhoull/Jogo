import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class pirata3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pirata3 extends Actor {
    private int speed = 4;
    private int jumpStrength = -15;
    private int gravity = 1;
    private int verticalSpeed = 0;
    private int groundLevel = 380;
    private int vida = 30;
    private String direcao; // Direção inicial
    private boolean comArma; // Variável que indica se o pirata está com a roupa de arma
    private int tempoEntreDisparos = 20; // Intervalo entre disparos
    private int contadorDisparo = 0; // Contador para o temporizador
    private Mapa mapa;
    private boolean mapaAberto = false;

    private boolean paisagemExibida = false;  // // Indica se a imagem da paisagem está exibida
    private boolean teclaPressionada = false; // Indica se a tecla para abrir/fechar o mapa foi pressionada
    
    private boolean estaVivo;

    private GreenfootImage pirataDireitaComArma = new GreenfootImage("piratinhaArma.png");
    private GreenfootImage pirataEsquerdaComArma = new GreenfootImage("piratinhaArma2.png");
    private GreenfootImage pirataSemArma = new GreenfootImage("piratinha2.png");

    
    // Método para definir o mapa coletado
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;  // Atribui o mapa coletado à variável de instância
    }

    // Método para verificar e coletar o mapa se estiver tocando-o
    public void checarMapaColetavel() {
        Mapa mapinha = (Mapa) getOneIntersectingObject(Mapa.class);  // Verifica se há um objeto do tipo Mapa no mesmo local
        if (mapinha != null) {  // Se um mapa foi encontrado
            getWorld().removeObject(mapinha);  // Remove o mapa do mundo
            this.mapa = mapinha;  // Atribui o mapa coletado à variável de instância
        }
    }

    public void abrirMapa() { //Função para abrir o mapa
        if (this.mapa != null) {
            GreenfootImage mapImage = new GreenfootImage("mapaAberto.png");
            getWorld().getBackground().drawImage(mapImage, 95, 0);
            this.mapaAberto = true;
            paisagemExibida = false;
        }
    }

    public void fecharMapa() { //Função para fechar o mapa
        if (mapaAberto) {
            desenharPaisagem();
            this.mapaAberto = false;
        }
    }

    public void desenharPaisagem() { //Função para desenhar a paisagem
        if (!paisagemExibida) {
            GreenfootImage paisagemImage = new GreenfootImage("paisagem.jpeg");
            getWorld().getBackground().drawImage(paisagemImage, 0, 0);
            paisagemExibida = true;
        }
    }

    public void setMapaColetado(boolean coletado) {
        // Define se o mapa foi coletado
        if (coletado) {
            this.mapa = new Mapa(); // Se o mapa foi coletado, cria uma nova instância de Mapa
        } else {
            this.mapa = null; // Se o mapa não foi coletado, define o mapa como nulo
        }
    }

    public boolean isMapaColetado() {
        return this.mapa != null; // Retorna true se o mapa foi coletado
    }

    public boolean isMapaAberto() {
        return this.mapaAberto;
    }

    public void checkMapaETeclaPressionada() {
    if (this.mapa != null) {  // Se o mapa foi coletado
        if (Greenfoot.isKeyDown("m")) {  // Verifica se a tecla 'm' está pressionada
            if (!teclaPressionada) {  // Se a tecla não estava pressionada na última verificação
                teclaPressionada = true;  // Marca a tecla como pressionada
                if (!mapaAberto) {  // Se o mapa não está aberto
                    abrirMapa();  // Abre o mapa
                } else {  // Se o mapa está aberto
                    fecharMapa();  // Fecha o mapa
                }
            }
        } else {
            teclaPressionada = false;  // Se a tecla 'm' não está pressionada, redefine o estado da tecla
        }
    }
    }

    public void atividadesMapa() {
        checarMapaColetavel(); // verificar e coletar o mapa se estiver tocando nele
        checkMapaETeclaPressionada(); //verifica se a tecla para abrir e fechar o mapa foi pressionada
    }

    
    public Pirata3() {
        this.direcao = "right";
        this.comArma = false;
        this.estaVivo=true;
    }

    public void pularNoChao() {
        if (Greenfoot.isKeyDown("up") && onGround()) {
            jump(); // Permitir pulo se o ator estiver no chão
        }
    }

    public void verificarTrocaDeRoupa() {
        if (Greenfoot.isKeyDown("c")) { // Tecla 'c' para mudar de roupa
            mudarRoupa();
        }
    }

    public void verificarAberturaMapa() {
        if (this.mapa != null && Greenfoot.isKeyDown("m")) { // Verifica se o pirata já tem o mapa e se a tecla 'M' foi pressionada.
            if (!mapaAberto) {
                abrirMapa(); // Se o mapa não está aberto, abre o mapa
            } else {
                fecharMapa(); // Se o mapa está aberto, fecha o mapa
            }
        }
    }

    public void movimentacaoPirata() {
        moveHorizontal(); // Movimentação lateral
        fall(); // Aplicar gravidade e controlar queda

        pularNoChao();
        verificarTrocaDeRoupa();
        atirarComRoupa(); // Mudei o local da chamada para que o disparo e o controle de taxa sejam gerenciados

        // Atualizar a posição vertical do ator
        setLocation(getX(), getY() + verticalSpeed);
        
        atividadesMapa();
        //checarMapaColetavel();
        //checkMapaETeclaPressionada();
    }

    public void act() {
        movimentacaoPirata();
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

    public int getVida() {
        return vida;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida <= 0) {
            estaVivo=false;//SE A VIDA É MENOR QUE 0 ELE NAO ESTA VIVO
            getWorld().removeObject(this);
        }
    }
    
    public boolean estaVivo() {
    return estaVivo; // Retorna o estado de vida do pirata
    }

    private void disparar() {
        if (contadorDisparo >= tempoEntreDisparos) {
            // Cria uma nova bala e adiciona ao mundo
            Bala bala = new Bala(this);
            getWorld().addObject(bala, getX(), getY());
            int balaX = getX();
            int balaY = getY();

            
            if (direcao.equals("right")) {
            getWorld().addObject(bala, balaX + 20, balaY); // Adiciona a bala à direita
            bala.setRotation(0); // Define a rotação para a direita (0 graus)
        } else {
            getWorld().addObject(bala, balaX - 20, balaY); // Adiciona a bala à esquerda
            bala.setRotation(180); // Define a rotação para a esquerda (180 graus)
        }
            contadorDisparo = 0; // Reinicia o contador
        }
    }

    private void atirarComRoupa() {
        if (Greenfoot.isKeyDown("space") && comArma) {
            disparar(); // Muda de atirar() para disparar()
        }
        contadorDisparo++; // Incrementa o contador após cada ato
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




