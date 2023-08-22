package InterfazGráfica;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugador extends PersonajeIG {
    private final PanelDeJuego panelDeJuego;
    private final MovimientoJugador movimientoJugador;

    public Jugador(PanelDeJuego panelDeJuego, MovimientoJugador movimientoJugador){
        this.panelDeJuego = panelDeJuego;
        this.movimientoJugador = movimientoJugador;
        obtenerValoresPorDefecto();
        obtenerImagenesDeJugador();
    }

    public void obtenerValoresPorDefecto(){
        posicionEnX = 48;
        posicionEnY = 48;
        velocidad = 1;
        direccion = "abajo";
    }
    public void obtenerImagenesDeJugador(){
        try{
            atras1 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-DerechaDetras.png"));
            atras2 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-IzquierdaDetras.png"));
            delante1 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-DerechaFrente.png"));
            delante2 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-IzquierdaFrente.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-PerfilDerechaMov1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-PerfilDerechaMov2.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-PerfilIzquierdaMov1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("/Enemigo/SnowMan-PerfilIzquierdaMov2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void actualizarPantalla(){
        if(algunaTeclaSeMantienePresionada()) {
            if (movimientoJugador.presionaArriba == true) {
                direccion = "arriba";
                posicionEnY -= velocidad;
            }
            if (movimientoJugador.presionaAbajo == true) {
                direccion = "abajo";
                posicionEnY += velocidad;
            }
            if (movimientoJugador.presionaDerecha == true) {
                direccion = "derecha";
                posicionEnX += velocidad;
            }
            if (movimientoJugador.presionaIzquierda == true) {
                direccion = "izquierda";
                posicionEnX -= velocidad;
            }
            contadorDeSprites++;
            if (contadorDeSprites > 12) {
                if (numeroDeSprite == 1) {
                    numeroDeSprite = 2;
                } else if (numeroDeSprite == 2) {
                    numeroDeSprite = 1;
                }
                contadorDeSprites = 0;
            }
        }
    }

    private boolean algunaTeclaSeMantienePresionada() {
        return movimientoJugador.presionaAbajo == true || movimientoJugador.presionaArriba == true ||
                movimientoJugador.presionaDerecha == true || movimientoJugador.presionaIzquierda == true;
    }

    public void dibujarJugador(Graphics2D graficos2D){

        BufferedImage image = null;
        switch (direccion){
            case "arriba":
                if(numeroDeSprite == 1) {
                    image = atras1;
                }
                if(numeroDeSprite == 2){
                    image = atras2;
                }
                break;
            case "abajo":
                if(numeroDeSprite == 1) {
                    image = delante1;
                }
                if(numeroDeSprite == 2){
                    image = delante2;
                }
                break;
            case "derecha":
                if(numeroDeSprite == 1) {
                    image = derecha1;
                }
                if(numeroDeSprite == 2){
                    image = derecha2;
                }
                break;
            case "izquierda":
                if(numeroDeSprite == 1) {
                    image = izquierda1;
                }
                if(numeroDeSprite == 2){
                    image = izquierda2;
                }
                break;
        }
        graficos2D.drawImage(image, posicionEnX, posicionEnY, panelDeJuego.tamañoBaldosaEnPantalla,
                panelDeJuego.tamañoBaldosaEnPantalla, null);
    }
}
