
package cliente;

import dominio.DTO.LineaDTO;
import dominio.DTO.JugadorDTO;
import enumeradores.MensajeSockets;
import dominio.DTO.MovimientoDTO;
import dominio.DTO.CuadroDTO;
import dominio.FormaJuego;
import dominio.Jugador;
import dominio.Linea;
import dominio.Cuadro;
import java.io.IOException;
import java.util.List;
import interfaces.IActualizable;
import interfaces.ICliente;

/**
 *
 * @author brawun
 */
public class Cliente implements ICliente {

    private static Cliente instance;

    private SckClient sckCliente;

    public Cliente(Jugador jugador, IActualizable actualizable) {
        this.sckCliente = SckClient.getInstance(jugador, actualizable);
    }

    public boolean conectarAlServidor(String address, int port) {
        try {
            sckCliente.conectarAlServidor(address, port);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean enviarAlServidor(Object mensaje) {
        try {
            if (mensaje instanceof Jugador) {
                Jugador jugador = (Jugador) mensaje;
                JugadorDTO mensajeNuevo = new JugadorDTO(jugador.getNombre(), jugador.getRutaColor(), jugador.getPuntaje());
                sckCliente.enviarAlServidor(mensajeNuevo);
                return true;
            } else if (mensaje instanceof List) {
                MovimientoDTO movimiento = new MovimientoDTO();
                List<FormaJuego> formas = (List<FormaJuego>) mensaje;

                for (int i = 0; i < formas.size(); i++) {
                    if (i == 0) {
                        Linea linea = (Linea) formas.get(i);
                        LineaDTO formaNueva
                                = new LineaDTO(
                                        linea.getPosicion().toString(),
                                        linea.getIndice(),
                                        new JugadorDTO(
                                                linea.getJugador().getNombre(),
                                                linea.getJugador().getRutaColor(),
                                                linea.getJugador().getPuntaje()));
                        movimiento.setLinea(formaNueva);
                    } else {
                        Cuadro cuadro = (Cuadro) formas.get(i);
                        CuadroDTO formaNueva
                                = new CuadroDTO(
                                        cuadro.getIndice(),
                                        new JugadorDTO(
                                                cuadro.getJugador().getNombre(),
                                                cuadro.getJugador().getRutaColor(),
                                                cuadro.getJugador().getPuntaje()));
                        movimiento.setCuadro(formaNueva);
                    }
                }

                sckCliente.enviarAlServidor(movimiento);
                return true;
            } else if (mensaje instanceof String) {
                sckCliente.enviarAlServidor(mensaje);
                return true;
            } else if (mensaje instanceof MensajeSockets) {
                sckCliente.enviarAlServidor(mensaje);
                return true;
            }
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    public void escucharAlServidor() {
        try {
            sckCliente.escucharAlServidor();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Problemas al recibir la respuesta del servidor");
        }
    }
}
