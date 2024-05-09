
package cliente;

import dominio.DTO.LineaDTO;
import dominio.DTO.JugadorDTO;
import dominio.DTO.MarcadorDTO;
import dominio.DTO.RespuestaDTO;
import dominio.DTO.MovimientoDTO;
import dominio.DTO.CuadroDTO;
import dominio.FormaJuego;
import dominio.Marcador;
import dominio.Jugador;
import dominio.Linea;
import dominio.Cuadro;
import enumeradores.Posicion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import interfaces.IActualizable;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author brawun
 */
public class SckClient implements Runnable {

    private Jugador jugador;
    private Socket socket;
    private ObjectInputStream clientInput;
    private ObjectOutputStream clientOutput;
    private IActualizable actualizable;
    private Object objeto;

    private static SckClient instance;

    private SckClient(Jugador jugador, IActualizable actualizable) {
        this.jugador = jugador;
        this.actualizable = actualizable;
    }

    public static SckClient getInstance(Jugador jugador, IActualizable actualizable) {
        if (instance == null) {
            instance = new SckClient(jugador, actualizable);
        } else {
            instance.actualizable = actualizable;
        }
        return instance;
    }

    public synchronized void conectarAlServidor(String address, int port) throws IOException {
        socket = new Socket(address, port);
        clientOutput = new ObjectOutputStream(socket.getOutputStream());
        clientOutput.flush();
        clientInput = new ObjectInputStream(socket.getInputStream());
    }

    public synchronized void enviarAlServidor(Object mensaje) throws IOException {
        clientOutput.writeObject(mensaje);
        clientOutput.flush();
    }

    public synchronized void escucharAlServidor() throws IOException, ClassNotFoundException {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                objeto = clientInput.readObject();

                if (objeto instanceof List) {
                    List<JugadorDTO> jugadoresDTO = (List<JugadorDTO>) objeto;
                    List<Jugador> jugadores = new ArrayList<>();

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getRutaAvatar()));
                    }

                    objeto = jugadores;
                } else if (objeto instanceof String) {
                    String string = (String) objeto;
                    objeto = string;
                } else if (objeto instanceof MarcadorDTO) {
                    MarcadorDTO marcadorDTO = (MarcadorDTO) objeto;
                    List<JugadorDTO> jugadoresDTO = marcadorDTO.getJugadores();
                    List<Jugador> jugadores = new ArrayList<>();

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getRutaAvatar(), jugador.getPuntaje()));
                    }

                    Marcador marcador = new Marcador(jugadores);

                    objeto = marcador;
                } else if (objeto instanceof RespuestaDTO) {
                    MovimientoDTO movimiento = ((RespuestaDTO) objeto).getMovimiento();
                    MarcadorDTO marcadorDTO = ((RespuestaDTO) objeto).getMarcador();
                    
                    List<FormaJuego> formas = new ArrayList<>();
                    List<JugadorDTO> jugadoresDTO = marcadorDTO.getJugadores();
                    List<Jugador> jugadores = new ArrayList<>();

                    if (movimiento.getLinea() != null) {
                        LineaDTO lineaDTO = movimiento.getLinea();

                        Linea linea = new Linea(
                                Posicion.valueOf(lineaDTO.getPosicion()),
                                new Jugador(
                                        lineaDTO.getJugador().getNombreJugador(),
                                        lineaDTO.getJugador().getRutaAvatar(),
                                        lineaDTO.getJugador().getPuntaje()),
                                lineaDTO.getIndice());
                        formas.add(linea);
                    }

                    for (CuadroDTO cuadroDTO : movimiento.getCuadros()) {
                        Cuadro cuadro = new Cuadro(
                                new Jugador(
                                        cuadroDTO.getJugador().getNombreJugador(),
                                        cuadroDTO.getJugador().getRutaAvatar(),
                                        cuadroDTO.getJugador().getPuntaje()),
                                cuadroDTO.getIndice());

                        formas.add(cuadro);
                    }
                    
                    System.out.println("MarcadorDTO " + marcadorDTO);

                    for (JugadorDTO jugador : jugadoresDTO) {
                        jugadores.add(new Jugador(jugador.getNombreJugador(), jugador.getRutaAvatar(), jugador.getPuntaje()));
                    }

                    Marcador marcador = new Marcador(jugadores);
                    System.out.println(marcador);
                    objeto = marcador;

                    //Solo para enviar el marcador
                    mostrarCambios();

                    objeto = formas;
                }

                mostrarCambios();

                System.out.println(objeto);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SckClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(SckClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SckClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void mostrarCambios() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                actualizable.actualizarSocket(objeto);
            }
        });
    }
}
