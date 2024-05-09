
package dominio.DTO;

import java.io.Serializable;

/**
 *
 * @author brawun
 */
public class RespuestaDTO implements Serializable{
    private MovimientoDTO movimiento;
    private MarcadorDTO marcador;

    public RespuestaDTO(MovimientoDTO movimiento, MarcadorDTO marcador) {
        this.movimiento = movimiento;
        this.marcador = marcador;
    }

    public MovimientoDTO getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }

    public MarcadorDTO getMarcador() {
        return marcador;
    }

    public void setMarcador(MarcadorDTO marcador) {
        this.marcador = marcador;
    }
}