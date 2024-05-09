
package tuberias;

import dominio.Tablero;
import acceso.Sincronizacion;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioTablero implements IEnvio<Tablero, Sincronizacion>{

    @Override
    public void pasar(Tablero objeto) {
        Sincronizacion sr = Sincronizacion.getInstance();
        sr.asignarTablero(objeto);
    }
    
}
