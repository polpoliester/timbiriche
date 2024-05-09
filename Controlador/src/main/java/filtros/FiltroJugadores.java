
package filtros;

import dominio.Jugador;
import dominio.DTO.JugadorDTO;
import java.util.ArrayList;
import java.util.List;
import tuberias.EnvioJugadores;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroJugadores implements IFiltro<List<JugadorDTO>, List<Jugador>, EnvioJugadores>{

    @Override
    public void procesar(List<JugadorDTO> objeto) {
        List<Jugador> jugadores = new ArrayList<>();
        for (JugadorDTO jugadorDTO : objeto) {
            Jugador jugador = new Jugador(jugadorDTO.getNombreJugador(), 
                    jugadorDTO.getRutaAvatar());
            jugadores.add(jugador);
        }
        EnvioJugadores pj = new EnvioJugadores();
        pj.pasar(jugadores);
    }
}