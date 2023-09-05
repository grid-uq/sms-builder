package co.edu.utp.gia.sms.beans.estadisticas.util;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
/**
 * Clase utilitaria encargada de modelar serie de datos estadísticos
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@RequiredArgsConstructor
public class SerieDatos {
    @Getter
    @NonNull
    private String etiqueta;
    @NonNull
    @Getter
    private List<DatoDTO> datos;

    public Float get(String etiqueta){
        return datos.stream()
                .filter( d->d.getEtiqueta().equals(etiqueta) )
                .map(DatoDTO::getValor)
                .findFirst()
                .orElse(null);
    }


}
