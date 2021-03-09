package co.edu.utp.gia.sms.beans.estadisticas.util;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SerieDatos {
    @Getter
    @NonNull
    private String etiqueta;
    @NonNull
    @Getter
    private List<DatoDTO> datos;

    public Float get(String etiqueta){
        Optional<DatoDTO> datoDTO = datos.stream().filter( d->d.getEtiqueta().equals(etiqueta) ).findFirst();
        if( datoDTO.isPresent() ){
            return datoDTO.get().getValor();
        }
        return null;
    }


}
