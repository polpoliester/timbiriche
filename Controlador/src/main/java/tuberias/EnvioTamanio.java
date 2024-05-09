
package tuberias;

import filtros.FiltroTablero;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioTamanio implements IEnvio<Integer, FiltroTablero>{

    @Override
    public void pasar(Integer objeto) {
        FiltroTablero ft = new FiltroTablero();
        ft.procesar(objeto);
    }
}
