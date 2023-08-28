package co.edu.utp.gia.sms.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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
@NoArgsConstructor
public class DatoDTO implements Serializable {
    @Getter
    @Setter
    @NonNull
    private String etiqueta;
    @Getter
    @Setter
    @NonNull
    private Float valor;

    public DatoDTO(Object etiqueta, Number valor) {
        this.etiqueta = etiqueta.toString();
        this.valor = valor.floatValue();
    }
//
//    public DatoDTO(Object etiqueta, Long valor) {
//        this.etiqueta = etiqueta.toString();
//        this.valor = valor.floatValue();
//    }
//
//    /**
//     * Metodo que permite inicializar los elementos de la clase DatoDTO
//     *
//     * @param etiqueta
//     * @param valor
//     */
//    public DatoDTO(String etiqueta, Long valor) {
//        this.etiqueta = etiqueta;
//        this.valor = valor.floatValue();
//    }
//
//    /**
//     * Metodo que permite inicializar los elementos de la clase DatoDTO
//     *
//     * @param etiqueta
//     * @param valor
//     */
//    public DatoDTO(String etiqueta, Float valor) {
//        this.etiqueta = etiqueta;
//        this.valor = valor;
//    }
//
//    /**
//     * Metodo que permite inicializar los elementos de la clase DatoDTO
//     *
//     * @param etiqueta
//     * @param valor
//     */
//    public DatoDTO(String etiqueta, Double valor) {
//        this.etiqueta = etiqueta;
//        this.valor = valor.floatValue();
//    }


}
