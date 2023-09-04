package co.edu.utp.gia.sms.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriterioSeleccion implements Entidad<String>{
    private String nombre;
    private String descripcion;
    private boolean paraExclusion;

    @Override
    public String getId() {
        return nombre.toUpperCase();
    }
}
