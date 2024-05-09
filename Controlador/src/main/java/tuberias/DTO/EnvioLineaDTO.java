
package tuberias.DTO;

import dominio.DTO.LineaDTO;
import filtros.FiltroLinea;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioLineaDTO implements IEnvio<LineaDTO, FiltroLinea>{

    @Override
    public void pasar(LineaDTO objeto) {
        FiltroLinea fl = new FiltroLinea();
        fl.procesar(objeto);
    }   
}