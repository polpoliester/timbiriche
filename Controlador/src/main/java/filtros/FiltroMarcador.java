
package filtros;

import dominio.Jugador;
import dominio.Marcador;
import java.util.List;
import tuberias.EnvioMarcador;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroMarcador implements IFiltro<List<Jugador>, Marcador, EnvioMarcador>{

    @Override
    public void procesar(List<Jugador> objeto) {
        Marcador marcador = new Marcador(objeto);
        EnvioMarcador pm = new EnvioMarcador();
        pm.pasar(marcador);
    }   
}