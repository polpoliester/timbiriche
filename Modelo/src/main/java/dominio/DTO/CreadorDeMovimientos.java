package dominio.DTO;

/**
 *
 * @author brawun
 */
public abstract class CreadorDeMovimientos {

    protected MovimientoDTO movimiento;

    public MovimientoDTO getMovimientoDTO() {
        return movimiento;
    }

    public void creaMovimientoDTO() {
        movimiento = new MovimientoDTO();
    }

    public void agregarLinea(LineaDTO linea) {
    }

    ;
    
    public void agregarCuadro(CuadroDTO cuadro) {
    }
;
}
