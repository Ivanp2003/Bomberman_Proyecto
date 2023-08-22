package InterfazGráfica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdministraciónDeBaldosa {
    private PanelDeJuego panelDeJuego;
    private Baldosa[] baldosa;
    public int numeroDeObjetoEnMapa[][];
    public AdministraciónDeBaldosa(PanelDeJuego panelDeJuego){
        this.panelDeJuego = panelDeJuego;
        this.baldosa = new Baldosa[3]; //arreglo de baldosas para poder crear
        this.numeroDeObjetoEnMapa = new int[panelDeJuego.cantidadBaldosasPorColumna][panelDeJuego.cantidadBaldosasPorFila];
        obtenerImagenesDeBaldosa("/Baldosas/Piso1.png", "/Baldosas/BloqueDestructible1.png", "/Baldosas/BloqueIndestructible1.png");
        cargarMapa("/Mapas/mapa2.txt");
    }

    public void crearMapa(String piso, String objetoDestructible, String objetoIndestructible, String mapa) {
        obtenerImagenesDeBaldosa(piso, objetoDestructible, objetoIndestructible);
        cargarMapa(mapa);
    }

    public void obtenerImagenesDeBaldosa(String piso, String bloqueDestructible, String bloqueIndestructible){
        try{
            baldosa[0] = new Baldosa();
            baldosa[0].imagenDeBaldosa = ImageIO.read(getClass().getResourceAsStream(piso));
            baldosa[1] = new Baldosa();
            baldosa[1].imagenDeBaldosa = ImageIO.read(getClass().getResourceAsStream(bloqueDestructible));
            baldosa[2] = new Baldosa();
            baldosa[2].imagenDeBaldosa = ImageIO.read(getClass().getResourceAsStream(bloqueIndestructible));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cargarMapa(String nombreMapa){
        try{
            //InputStream es para importar el archivo txt donde se encuentra el mapa
            InputStream archivo = getClass().getResourceAsStream(nombreMapa);
            //BufferedReader leerá el contenido del archivo importado
            BufferedReader lectorArchivo = new BufferedReader(new InputStreamReader(archivo));

            int columna = 0;
            int fila = 0;

            while(columna < panelDeJuego.cantidadBaldosasPorColumna &&
                    fila < panelDeJuego.cantidadBaldosasPorFila){
                String linea = lectorArchivo.readLine(); // Aquí solo se leerá una linea del texto

                while(columna < panelDeJuego.cantidadBaldosasPorColumna){
                    String numeros[] = linea.split(","); //Se toma cada elemento separado por una coma y se almacena en el arreglo
                    int numero = Integer.parseInt(numeros[columna]); //Se ha convertido el String en enteros para poder usar
                    numeroDeObjetoEnMapa[columna][fila] = numero;
                    columna++;
                }

                if(columna == panelDeJuego.cantidadBaldosasPorColumna){
                    columna = 0;
                    fila++;
                }
            }
            lectorArchivo.close();
        }catch (Exception e){
        }
    }

    public void dibujarBaldosa(Graphics2D graficos2D){
        int columna = 0;
        int fila = 0;
        int posicionX = 0;
        int posicionY = 0;

        while(columna < panelDeJuego.cantidadBaldosasPorColumna &&
                fila < panelDeJuego.cantidadBaldosasPorFila){

           int numeroDeBaldosa = numeroDeObjetoEnMapa[columna][fila];

            graficos2D.drawImage(baldosa[numeroDeBaldosa].imagenDeBaldosa,
                    posicionX, posicionY, panelDeJuego.tamañoBaldosaEnPantalla,
                    panelDeJuego.tamañoBaldosaEnPantalla, null);

            columna++;
            posicionX += panelDeJuego.tamañoBaldosaEnPantalla;

            if(columna == panelDeJuego.cantidadBaldosasPorColumna){
                columna = 0;
                posicionX = 0;
                fila++;
                posicionY += panelDeJuego.tamañoBaldosaEnPantalla;
            }
        }
    }

}
