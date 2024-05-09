
package tuberias;

import dominio.Jugador;
import acceso.Sincronizacion;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioJugador implements IEnvio<Jugador, Sincronizacion> {

    @Override
    public void pasar(Jugador objeto) {
        Sincronizacion sr = Sincronizacion.getInstance();
        sr.retirarJugador(objeto);
        
    }
    
}
