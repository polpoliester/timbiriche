
package tuberias.DTO;

import dominio.DTO.JugadorDTO;
import java.util.List;
import filtros.FiltroJugadores;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioJugadoresDTO implements IEnvio<List<JugadorDTO>, FiltroJugadores> {

    @Override
    public void pasar(List<JugadorDTO> objeto) {
        FiltroJugadores fj = new FiltroJugadores();
        fj.procesar(objeto);
    }
    
}
