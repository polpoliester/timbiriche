
package tuberias;

import dominio.Linea;
import acceso.Sincronizacion;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioLinea implements IEnvio<Linea, Sincronizacion>{

    @Override
    public void pasar(Linea objeto) {
        Sincronizacion sr = Sincronizacion.getInstance();
        sr.asignarLinea(objeto);
    }
    
}
