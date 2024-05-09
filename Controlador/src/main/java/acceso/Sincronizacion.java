package acceso;

import dominio.*;
import enumeradores.Posicion;

/**
 *
 * @author brawun
 */
public class Sincronizacion {

    private volatile static Sincronizacion instance;
    private volatile Sala sala;
    private volatile Linea ultimaLinea;
    private volatile Cuadro ultimoCuadro;

    private Sincronizacion() {
        sala = new Sala();
    }

    public static synchronized Sincronizacion getInstance() {
        if (instance == null) {
            instance = new Sincronizacion();
        }
        return instance;
    }

    public synchronized void asignarMarcador(Marcador marcador) {
        this.sala.setMarcador(marcador);
    }

    public synchronized void asignarTablero(Tablero tablero) {
        this.sala.setTablero(tablero);
    }

    public synchronized void asignarLinea(Linea linea) {
        if (linea.getPosicion() == Posicion.HORIZONTAL) {
            this.sala.getTablero().getLineasHorizontales().get(linea.getIndice()).setJugador(linea.getJugador());
            this.ultimaLinea = this.sala.getTablero().getLineasHorizontales().get(linea.getIndice());
        } else if (linea.getPosicion() == Posicion.VERTICAL) {
            this.sala.getTablero().getLineasVerticales().get(linea.getIndice()).setJugador(linea.getJugador());
            this.ultimaLinea = this.sala.getTablero().getLineasVerticales().get(linea.getIndice());
        }
    }

    public synchronized void asignarCuadro(Cuadro cuadro) {
        for (Jugador jugador : this.sala.getMarcador().getJugadores()) {
            if (jugador.equals(cuadro.getJugador())) {
                this.sala.getTablero().getCuadros().get(cuadro.getIndice()).setJugador(jugador);
                jugador.setPuntaje(jugador.getPuntaje() + 1);
                this.ultimoCuadro = this.sala.getTablero().getCuadros().get(cuadro.getIndice());
            }
        }
    }

    public synchronized void retirarJugador(Jugador jugador) {
        for (Jugador jugObj : this.sala.getMarcador().getJugadores()) {
            if (jugObj.equals(jugador)) {
                this.sala.getMarcador().getJugadores().remove(jugObj);

                for (Linea vertical : this.sala.getTablero().getLineasVerticales()) {
                    if (vertical.getJugador().equals(jugObj)) {
                        vertical.setJugador(null);
                    }
                }

                for (Linea horizontal : this.sala.getTablero().getLineasHorizontales()) {
                    if (horizontal.getJugador().equals(jugObj)) {
                        horizontal.setJugador(null);
                    }
                }

                for (Cuadro cuadro : this.sala.getTablero().getCuadros()) {
                    if (cuadro.getJugador().equals(jugObj)) {
                        cuadro.setJugador(null);
                    }
                }
            }
        }
    }

    public synchronized Sala getSala() {
        return sala;
    }

    public synchronized Marcador obtenerMarcador() {
        return this.sala.getMarcador();
    }

    public synchronized Linea obtenerUltimaLinea() {
        return this.ultimaLinea;
    }

    public synchronized Cuadro obtenerUltimoCuadro() {
        return this.ultimoCuadro;
    }

    public synchronized int obtenerTurnoSiguiente() {
        return this.sala.getMarcador().getSiguiente();
    }
}
