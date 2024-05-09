
package tuberias.DTO;

import dominio.DTO.CuadroDTO;
import filtros.FiltroCuadro;
import interfaces.IEnvio;

/**
 *
 * @author brawun
 */
public class EnvioCuadroDTO implements IEnvio<CuadroDTO, FiltroCuadro>{

    @Override
    public void pasar(CuadroDTO objeto) {
        FiltroCuadro fc = new FiltroCuadro();
        fc.procesar(objeto);
    }
}
