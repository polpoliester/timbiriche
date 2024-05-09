
package filtros;

import dominio.Cuadro;
import dominio.Jugador;
import dominio.Linea;
import dominio.DTO.CuadroDTO;
import tuberias.EnvioCuadro;
import interfaces.IFiltro;

/**
 *
 * @author brawun
 */
public class FiltroCuadro implements IFiltro<CuadroDTO, Cuadro, EnvioCuadro>{

    @Override
    public void procesar(CuadroDTO objeto) {
        Jugador jugador = new Jugador(objeto.getJugador().getNombreJugador(), objeto.getJugador().getRutaAvatar());
        
        Cuadro cuadro = new Cuadro(jugador, objeto.getIndice());
        EnvioCuadro pc = new EnvioCuadro();
        pc.pasar(cuadro);
    } 
}
