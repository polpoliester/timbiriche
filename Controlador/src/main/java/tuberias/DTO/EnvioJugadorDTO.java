package tuberias.DTO;

import dominio.DTO.JugadorDTO;
import filtros.FiltroJugador;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioJugadorDTO implements IEnvio<JugadorDTO, FiltroJugador> {

    @Override
    public void pasar(JugadorDTO objeto) {
        FiltroJugador fj = new FiltroJugador();
        fj.procesar(objeto);
    }
}
