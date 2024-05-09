
package tuberias;

import dominio.Cuadro;
import acceso.Sincronizacion;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioCuadro implements IEnvio<Cuadro, Sincronizacion> {

    @Override
    public void pasar(Cuadro objeto) {
        Sincronizacion sr = Sincronizacion.getInstance();
        sr.asignarCuadro(objeto);
    }   
}