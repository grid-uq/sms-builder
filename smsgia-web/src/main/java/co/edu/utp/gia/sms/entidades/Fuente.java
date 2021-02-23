package co.edu.utp.gia.sms.entidades;

import co.edu.utp.gia.sms.importutil.TipoFuente;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Fuente implements Entidad<co.edu.utp.gia.sms.importutil.Fuente> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nombre de la fuente
     */
    @Id
    @Enumerated(EnumType.STRING)
    @Getter
    @NonNull
    private co.edu.utp.gia.sms.importutil.Fuente nombre;

    /**
     * Tipo de la fuente
     */
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private TipoFuente tipo;

    /**
     * Permite inicializar los elementos de la fuente
     *
     * @param nombre Nombre a ser agisnado
     */
    public Fuente(co.edu.utp.gia.sms.importutil.Fuente nombre) {
        super();
        this.nombre = nombre;
        tipo = nombre.getTipo();
    }

    /**
     * Metodo que permite obtener el valor del Id de la entidad
     *
     * @return El valor del Id de la entidad
     */
    public co.edu.utp.gia.sms.importutil.Fuente getId() {
        return nombre;
    }

    /**
     * Metodo que permite asignar un valor al atributo nombre
     *
     * @param nombre Valor a ser asignado al atributo nombre
     */
    public void setNombre(co.edu.utp.gia.sms.importutil.Fuente nombre) {
        this.nombre = nombre;
        tipo = nombre.getTipo();
    }


}
