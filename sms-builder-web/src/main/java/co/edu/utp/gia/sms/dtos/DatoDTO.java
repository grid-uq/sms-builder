package co.edu.utp.gia.sms.dtos;

import lombok.Getter;

import java.io.Serializable;
/**
 * Clase que encapsula datos estadísticos a ser mostrados al usuario.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Getter
public class DatoDTO implements Serializable {
    private final String etiqueta;
    private final Float valor;

    public DatoDTO(Object etiqueta, Number valor) {
        this.etiqueta = etiqueta.toString();
        this.valor = valor.floatValue();
    }
}
