
package server;

import enumeradores.MensajeSockets;
import dominio.DTO.JugadorDTO;
import dominio.DTO.RespuestaDTO;
import dominio.DTO.MarcadorDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brawun
 */
public class ServerHilo implements Runnable {

    private volatile JugadorDTO jugadorDTO;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile List<ServerHilo> threads;
    private ServerProtocolo ssp;
    private Socket s;
    private boolean votado;
    private int MAX;

    public ServerHilo(Socket s, List<ServerHilo> threads, int MAX) throws IOException {
        this.s = s;
        this.threads = threads;
        this.votado = false;
        this.ssp = new ServerProtocolo();
        this.output = new ObjectOutputStream(s.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(s.getInputStream());
        this.MAX = MAX;
    }

    @Override
    public void run() {
        Object mensajeEntrante;

        while (true) {
            try {

                //Lee entrada
                mensajeEntrante = input.readObject();

                //La procesa el protocolo
                Object mensajeSaliente = ssp.procesarEntrada(mensajeEntrante);

                //Si el jugador es nuevo
                if (mensajeSaliente == MensajeSockets.JUGADOR_NUEVO) {
                    System.out.println("Entro jugador: " + mensajeEntrante);
                    this.jugadorDTO = (JugadorDTO) mensajeEntrante;

                    //Crea una lista de Jugadores
                    List<JugadorDTO> jugadores = new ArrayList<>();
                    for (ServerHilo thread : threads) {
                        jugadores.add(thread.getJugadorDTO());
                    }

                    //La transmite a todos para actualizar
                    transmitirATodos(jugadores);

                    if (threads.size() == MAX) {
                        Object empezarPartida = ssp.empezarPartida(jugadores);
                        transmitirATodos(empezarPartida);
                    }

                    //Si es un voto
                } else if (mensajeSaliente == MensajeSockets.VOTO) {
                    //Si no voto
                    if (this.votado == false) {
                        this.votado = true;
                        mensajeSaliente = this.jugadorDTO.getNombreJugador() + " ha votado";
                        //Si ya habia votado
                    } else {
                        this.votado = false;
                        mensajeSaliente = this.jugadorDTO.getNombreJugador() + " ha cancelado el voto";
                    }

                    //Retorna accion
                    transmitirATodos(mensajeSaliente);
                } else if (mensajeSaliente instanceof MarcadorDTO) {
                    transmitirATodos(mensajeSaliente);
                } else if (mensajeSaliente instanceof RespuestaDTO) {
                    transmitirATodos(mensajeSaliente);
                }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public JugadorDTO getJugadorDTO() {
        return jugadorDTO;
    }

    public void setJugadorDTO(JugadorDTO jugadorDTO) {
        this.jugadorDTO = jugadorDTO;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public boolean isVotado() {
        return votado;
    }

    public void setVotado(boolean votado) {
        this.votado = votado;
    }

    public synchronized void transmitirASiMismo(Object mensaje) {
        try {
            this.output.writeObject(mensaje);
            this.output.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void transmitirATodos(Object mensaje) {
        for (ServerHilo thread : threads) {
            try {
                thread.output.writeObject(mensaje);
                thread.output.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
