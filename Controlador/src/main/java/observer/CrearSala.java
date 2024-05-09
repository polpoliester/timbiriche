package observer;

import dominio.DTO.JugadorDTO;
import java.util.List;
import tuberias.DTO.EnvioJugadoresDTO;
import tuberias.EnvioTamanio;
import interfaces.IPAFAccion;

/**
 *
 * @author brawun
 */
class CrearSala implements IPAFAccion {

    private List<JugadorDTO> jugadores;

    public CrearSala(List<JugadorDTO> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public void ejecutar() {
        EnvioJugadoresDTO pj = new EnvioJugadoresDTO();
        pj.pasar(jugadores);
        EnvioTamanio pt = new EnvioTamanio();
        pt.pasar(jugadores.size());
    }

}
