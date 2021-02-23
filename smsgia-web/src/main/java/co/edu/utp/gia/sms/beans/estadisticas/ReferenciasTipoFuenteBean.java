package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.importutil.TipoFuente;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasTipoFuenteBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -5273832765304254823L;
    @Getter
    @Setter
    private TipoFuente tipo;

    public void inicializar() {
        setTitulo("Referencias por Tipo de Fuente");
        setEjeX("Fuente");
        setEjeY("# Referencias");

        if (getRevision() != null) {
            setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));

            crearModelo();

        }
    }


    public void onChangeTipoFuente() {
        if (tipo == null) {
            setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));
        } else {
            setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuenteNombre(getRevision().getId(), tipo));
        }
        crearModelo();
    }

    public TipoFuente[] getTiposFuente() {
        return TipoFuente.values();
    }

}
