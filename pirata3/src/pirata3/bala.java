package pirata3;

public class Bala extends Actor {
    private int dano = 5;
    private Pirata3 pirataAtirador;  // Referência ao pirata que disparou a bala

    // Construtor que aceita o pirata como argumento
    public Bala(Pirata3 pirataAtirador) {
        this.pirataAtirador = pirataAtirador;  // Armazena o pirata que disparou
        setImage("bala.png");  // Certifique-se de que o arquivo da imagem está no diretório correto
    }

    public void act() {
        move(10);  // Mova a bala para frente
        verificarColisao();
    }

    private void verificarColisao() {
        // Verifica colisão com inimigo1
        Actor alvo = getOneIntersectingObject(Inimigo1.class);
        if (alvo != null) {
            Inimigo1 inimigo = (Inimigo1) alvo;
            inimigo.receberDano(dano);
            getWorld().removeObject(this);  // Remove a bala após o acerto
            return;  // Não continue verificando colisões, a bala já acertou um inimigo
        }

        // Verifica colisão com outros piratas, mas ignora o pirata que disparou
        Actor pirataAlvo = getOneIntersectingObject(Pirata3.class);
        if (pirataAlvo != null && pirataAlvo != pirataAtirador) {  // Ignora o pirata que disparou
            Pirata3 pirata = (Pirata3) pirataAlvo;
            pirata.receberDano(dano);
            getWorld().removeObject(this);  // Remove a bala após o acerto
            return;  // Não continue verificando colisões, a bala já acertou um pirata
        }
    }
}
