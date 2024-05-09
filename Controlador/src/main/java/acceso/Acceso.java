package acceso;

import interfaces.IRepo;
import dominio.*;
import dominio.DTO.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brawun
 */
public class Acceso implements IRepo {

    private Sincronizacion sr = Sincronizacion.getInstance();

    @Override
    public MarcadorDTO obtenerMarcador() {
        Marcador marcador = sr.obtenerMarcador();

        List<JugadorDTO> jugadoresDTO = new ArrayList<>();
        for (Jugador jugador : marcador.getJugadores()) {
            jugadoresDTO.add(new JugadorDTO(jugador.getNombre(), jugador.getRutaColor(), jugador.getPuntaje()));
        }
        MarcadorDTO marcadorDTO = new MarcadorDTO(jugadoresDTO);

        return marcadorDTO;
    }

    @Override
    public LineaDTO obtenerUltimaLinea() {
        Linea linea = sr.obtenerUltimaLinea();
        if (linea != null) {
            LineaDTO lineaDTO
                    = new LineaDTO(
                            linea.getPosicion().toString(),
                            linea.getIndice(),
                            new JugadorDTO(
                                    linea.getJugador().getNombre(),
                                    linea.getJugador().getRutaColor(),
                                    linea.getJugador().getPuntaje()));
            return lineaDTO;
        } else {
            return null;
        }
    }

    @Override
    public CuadroDTO obtenerUltimoCuadro() {
        Cuadro cuadro = sr.obtenerUltimoCuadro();
        CuadroDTO cuadroDTO
                = new CuadroDTO(cuadro.getIndice(),
                        new JugadorDTO(cuadro.getJugador().getNombre(),
                                cuadro.getJugador().getRutaColor(),
                                cuadro.getJugador().getPuntaje()));

        return cuadroDTO;
    }

    @Override
    public int obtenerTurnoSiguiente() {
        return sr.obtenerTurnoSiguiente();
    }
}
