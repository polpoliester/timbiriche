
package tuberias;

import dominio.Jugador;
import java.util.List;
import filtros.FiltroMarcador;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioJugadores implements IEnvio<List<Jugador>, FiltroMarcador>{

    @Override
    public void pasar(List<Jugador> objeto) {
        FiltroMarcador fm = new FiltroMarcador();
        fm.procesar(objeto);
    }
}