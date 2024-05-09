
package filtros;

import dominio.Jugador;
import dominio.Linea;
import enumeradores.Posicion;
import dominio.DTO.LineaDTO;
import tuberias.EnvioLinea;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroLinea implements IFiltro<LineaDTO, Linea, EnvioLinea>{

    @Override
    public void procesar(LineaDTO objeto) {
        Jugador jugador = new Jugador(objeto.getJugador().getNombreJugador(), objeto.getJugador().getRutaAvatar());
        Linea linea = new Linea(Posicion.valueOf(objeto.getPosicion()), jugador, objeto.getIndice());
        EnvioLinea pl = new EnvioLinea();
        pl.pasar(linea);
    }
}
