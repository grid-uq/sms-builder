package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ReferenciaPalabrasClaveBean extends AbstractRevisionBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3927845673735363457L;

    @Inject
    private EstadisticaEJB estadisticaEJB;

    @Getter
    @Setter
    private List<Referencia> referencias;

    @Getter
    @Setter
    private String keyword;

    @Getter
    private String listadoReferencias;

    @Getter
    @Setter
    private TipoMetadato[] metadatos;

    @Getter
    private Integer totalResultados;

    public void inicializar() {
        metadatos = new TipoMetadato[]{TipoMetadato.KEYWORD};
        if (getRevision() != null) {
            consultarReferencias();
        }
    }

    public void consultarReferencias() {
        referencias = estadisticaEJB.obtenerReferencias(getRevision().getId(), keyword, Arrays.asList(metadatos));

		listadoReferencias= referencias.stream()
				.map(Referencia::getSpsid)
				.collect(Collectors.joining(", "));
		totalResultados = referencias.size();
    }

    public TipoMetadato[] getTipoMetadatos() {
        return TipoMetadato.values();
    }

}
