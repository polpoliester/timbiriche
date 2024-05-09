
package dominio.DTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brawun
 */
public class MarcadorDTO implements Serializable{
    private List<JugadorDTO> jugadores;
    private int siguiente;

    public MarcadorDTO(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
        this.siguiente = 0;
    }

    public MarcadorDTO(List<JugadorDTO> jugadores, int siguiente) {
        this.jugadores = jugadores;
        this.siguiente = siguiente;
    }

    public List<JugadorDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "MarcadorDTO{" + "jugadores=" + jugadores + ", siguiente=" + siguiente + '}';
    }
}