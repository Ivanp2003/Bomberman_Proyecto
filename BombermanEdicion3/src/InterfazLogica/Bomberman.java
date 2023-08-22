package InterfazLogica;

import InterfazGráfica.PanelDeJuego;

public class Bomberman {
    public static int NUMERO_NIVEL = 0;
    private Personaje jugador;
    private Personaje enemigo;
    private Nivel nivel;
    private boolean gameOver;

    public Bomberman(PanelDeJuego panelDeJuego) {
        nivel = new Nivel(panelDeJuego);
        gameOver = false;
        nivel.construirNivel();
    }
    private void empezarAJugar() {
        while (gameOver == false){
           // nivel.crearNivel(NUMERO_NIVEL++);
            //gameOver = nivel.verificarVictoria();
        }
    }





}
