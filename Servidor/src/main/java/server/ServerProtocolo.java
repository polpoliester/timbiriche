package server;

import acceso.Acceso;
import enumeradores.MensajeSockets;
import dominio.DTO.MovimientoDTO;
import dominio.DTO.JugadorDTO;
import dominio.DTO.CuadroDTO;
import dominio.DTO.RespuestaDTO;
import java.util.List;
import interfaces.IPAF;
import interfaces.IRepo;
import observer.Invocador;

/**
 *
 * @author brawun
 */
public class ServerProtocolo {

    private IPAF ipaf;
    private IRepo repo;

    public ServerProtocolo() {
        this.ipaf = new Invocador();
        this.repo = new Acceso();
    }

    public Object procesarEntrada(Object mensajeEntrante) {

        //Si despues de realizada la conexion, el socket del cliente manda los
        //datos del jugador, le avisa al Thread que efectivamente son los datos
        if (mensajeEntrante instanceof JugadorDTO) {
            return MensajeSockets.JUGADOR_NUEVO;

            //Si se reciben los datos de un MovimientoDTO, se manda al componente 
            //PipesAndFilters para realizar la conversion correspondiente, asignar 
            //y obtener respuesta
        } else if (mensajeEntrante instanceof MovimientoDTO) {
            MovimientoDTO movimiento = (MovimientoDTO) mensajeEntrante;

            if (movimiento.getLinea() != null) {
                ipaf.asignarLinea(movimiento.getLinea());
            } else if (movimiento.getCuadros() != null) {
                for (CuadroDTO cuadro : movimiento.getCuadros()) {
                    ipaf.asignarCuadro(cuadro);
                }
            }

            RespuestaDTO respuesta = new RespuestaDTO(movimiento, repo.obtenerMarcador());
            return respuesta;

            //Si un cliente vota, se verifica y se manda respuesta
        } else if (mensajeEntrante == MensajeSockets.VOTO) {
            return MensajeSockets.VOTO;
        } else if (mensajeEntrante == MensajeSockets.TURNO_TERMINADO) {
            return repo.obtenerTurnoSiguiente();
        } else if (mensajeEntrante == MensajeSockets.MARCADOR) {
            return repo.obtenerMarcador();
        }

        return null;
    }

    public Object empezarPartida(List<JugadorDTO> jugadoresDTO) {
        ipaf.crearSala(jugadoresDTO);
        return repo.obtenerMarcador();
    }
}
