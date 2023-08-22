package InterfazGráfica;

import InterfazLogica.Nivel;

import javax.swing.*;
import java.awt.*;

public class PanelDeJuego extends JPanel implements Runnable {
    final int tamañoDeBaldosaOriginal = 16; // 16x16 pixeles
    final int escala = 3;
    public final int tamañoBaldosaEnPantalla = tamañoDeBaldosaOriginal * escala; // Con esto tendría 48x48 píxeles
    public final int cantidadBaldosasPorColumna = 15;
    public final int cantidadBaldosasPorFila = 13;
    public final int anchoDePantalla = tamañoBaldosaEnPantalla * cantidadBaldosasPorColumna; // 48x16 = 768 píxeles
    public final int alturaDePantalla = tamañoBaldosaEnPantalla * cantidadBaldosasPorFila; // 48x12 = 576 píxeles
    private final int FPS = 60; // Cantidad de frames por segundo

    AdministraciónDeBaldosa administraciónDeBaldosa = new AdministraciónDeBaldosa(this);
    MovimientoJugador movimientoJugador = new MovimientoJugador();
    Thread juegoHilo; // Cuando iniciamos este hilo, llama inmediatamente al método run.
                      // Uso de este hilo para tener en cuenta el tiempo en el que se ejecutará esta pantalla.
    Jugador jugador = new Jugador(this,movimientoJugador);

    public PanelDeJuego(){
        this.setPreferredSize(new Dimension(anchoDePantalla, alturaDePantalla)); // Tamaño de la pantalla con las medidas creadas
        this.setBackground(Color.black); //fondo negro
        this.setDoubleBuffered(true); // Uso de canvas. Mejor rendimiento de renderizado
        this.addKeyListener(movimientoJugador); // Instaciamos el teclado en el panel
        this.setFocusable(true);
    }

    public void empezarHiloDelJuego(){
        juegoHilo = new Thread(this);// Se realiza la instancia del hilo creado. El this es en referencia a esta clase PanelJuego
        juegoHilo.start(); // Da inicio al hilo
    }

    @Override
    public void run() {
        // Uso de metodo "delta" para lograr que los dibujos se actualicen y redibujen a 60 FPS
        double intervaloEntreDibujo = 1000000000/FPS; //1666666.67
        double delta = 0;
        long ultimoTiempo = System.nanoTime(); // Tiempo en nanosegundos
        long tiempoActual;

        // Aquí se creará un game loop o bucle del juego. Esta será el nucleo del juego
        while(hiloDelJuegoExista()){
            tiempoActual = System.nanoTime();
            delta += (tiempoActual - ultimoTiempo) / intervaloEntreDibujo;
            ultimoTiempo = tiempoActual;

            if(delta >= 1) {
                actualizarPantalla();// Aquí se actualiza la posición del jugador
                repaint(); // Forma de llamar a paintComponent. Aquí se redibuja al jugador en su nueva posición
                delta--;
            }
        }
    }
    public void actualizarPantalla(){
        jugador.actualizarPantalla();
    }

    // Método de java donde se redibujará la pantalla con coordenadas
    // Actuales y uso de la clase Graphics para dibujar objetos en pantalla
    public void paintComponent(Graphics graficos){
        super.paintComponent(graficos); // Cada vez que se use este métdoo, irá un super.
        Graphics2D graficos2D = (Graphics2D)graficos; //esta otra clase tiene más funciones para usar luego.
        administraciónDeBaldosa.dibujarBaldosa(graficos2D);//la baldosa siempre deberá ir antes de jugador
        // porque se dibuja en orden y el jugador siempre va encima de la baldosa
        jugador.dibujarJugador(graficos2D);
        graficos2D.dispose();
    }
    private boolean hiloDelJuegoExista() {
        return juegoHilo != null;
    }

}
