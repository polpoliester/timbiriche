
package filtros;

import dominio.Jugador;
import dominio.DTO.JugadorDTO;
import tuberias.EnvioJugador;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroJugador implements IFiltro<JugadorDTO, Jugador, EnvioJugador> {

    @Override
    public void procesar(JugadorDTO objeto) {
        Jugador jugador = new Jugador(objeto.getNombreJugador(), objeto.getRutaAvatar());
        EnvioJugador pj = new EnvioJugador();
        pj.pasar(jugador);
    }
}
