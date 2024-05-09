package observer;

import dominio.DTO.CuadroDTO;
import dominio.DTO.JugadorDTO;
import dominio.DTO.LineaDTO;
import java.util.List;
import interfaces.IPAF;
import interfaces.IPAFAccion;

/**
 *
 * @author brawun
 */
public class Invocador implements IPAF {

    IPAFAccion comando;

    @Override
    public void crearSala(List<JugadorDTO> jugadores) {
        comando = new CrearSala(jugadores);
        comando.ejecutar();
    }

    @Override
    public void asignarLinea(LineaDTO linea) {
        comando = new AsignarLinea(linea);
        comando.ejecutar();
    }

    @Override
    public void asignarCuadro(CuadroDTO cuadro) {
        comando = new AsignarCuadro(cuadro);
        comando.ejecutar();
    }

    @Override
    public void retirarJugador(JugadorDTO jugador) {
        comando = new RetirarJugador(jugador);
        comando.ejecutar();
    }
}
