
package tuberias;

import dominio.Marcador;
import acceso.Sincronizacion;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioMarcador implements IEnvio<Marcador, Sincronizacion> {

    @Override
    public void pasar(Marcador objeto) {
        Sincronizacion sr = Sincronizacion.getInstance();
        sr.asignarMarcador(objeto);
    }   
}
