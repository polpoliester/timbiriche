
package filtros;

import dominio.Tablero;
import tuberias.EnvioTablero;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroTablero implements IFiltro<Integer, Tablero, EnvioTablero>{

    @Override
    public void procesar(Integer objeto) {
        Tablero tablero = new Tablero(objeto);
        tablero.generaInstanciasDeFormaJuego();
        EnvioTablero pt = new EnvioTablero();
        pt.pasar(tablero);
    } 
}
