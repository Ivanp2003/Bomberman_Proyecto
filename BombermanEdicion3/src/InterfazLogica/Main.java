package InterfazLogica;

import InterfazGráfica.PanelDeJuego;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        // JFrame es una clase proporcionada por la biblioteca gráfica Swing
        // en Java que se utiliza para crear ventanas en las aplicaciones gráficas.

        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Bomberman");

        PanelDeJuego panelDeJuego = new PanelDeJuego();
        Bomberman bomberman = new Bomberman(panelDeJuego);

        ventana.add(panelDeJuego);
        ventana.pack();

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        panelDeJuego.empezarHiloDelJuego();

    }
}
