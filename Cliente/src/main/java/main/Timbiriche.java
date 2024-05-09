package main;

import vista.jugador.FrmConfiguracion;

/**
 * La clase Timbiriche es la clase principal que contiene el método main para la
 * ejecución del programa. Representa el punto de entrada de la aplicación o
 * juego Timbiriche.
 *
 * @Author Equipo 5
 */
public class Timbiriche {

    /**
     * El método main es el punto de entrada de la aplicación o juego
     * Timbiriche.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        new FrmConfiguracion().setVisible(true);
    }
}
