package InterfazGráfica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovimientoJugador implements KeyListener { // Con KeyListener recibe eventos de teclado
    public boolean presionaDerecha, presionaIzquierda, presionaArriba, presionaAbajo;
    int usoDeTeclado;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        usoDeTeclado = e.getKeyCode();
        if(usoDeTeclado == KeyEvent.VK_W){
            presionaArriba = true;
        }
        if(usoDeTeclado == KeyEvent.VK_S){
            presionaAbajo = true;
        }
        if(usoDeTeclado == KeyEvent.VK_A){
            presionaIzquierda = true;
        }
        if(usoDeTeclado == KeyEvent.VK_D){
            presionaDerecha = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        usoDeTeclado = e.getKeyCode();
        if(usoDeTeclado == KeyEvent.VK_W){
            presionaArriba = false;
        }
        if(usoDeTeclado == KeyEvent.VK_S){
            presionaAbajo = false;
        }
        if(usoDeTeclado == KeyEvent.VK_A){
            presionaIzquierda = false;
        }
        if(usoDeTeclado == KeyEvent.VK_D){
            presionaDerecha = false;
        }
    }
}
