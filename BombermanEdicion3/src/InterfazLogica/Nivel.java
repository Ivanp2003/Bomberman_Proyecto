package InterfazLogica;

import InterfazGráfica.AdministraciónDeBaldosa;
import InterfazGráfica.PanelDeJuego;

import java.awt.*;
import java.util.ArrayList;
public class Nivel {
    private ArrayList mapas;
    private AdministraciónDeBaldosa administradorDeMapa;

    public Nivel(PanelDeJuego panelDeJuego){
        administradorDeMapa = new AdministraciónDeBaldosa(panelDeJuego);
        mapas = new ArrayList();
    }

    public void construirNivel() {
      administradorDeMapa.crearMapa("/Baldosas/Piso2.png",
              "/Baldosas/BloqueDestructible2.png",
              "/Baldosas/BloqueIndestructible2.png",
              "/Mapas/mapa2.txt");
    }

    

}
