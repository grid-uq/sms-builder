package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.negocio.EstadisticaService;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase controladora de interfaz web que se encarga de presentar las referencias asociadas a una determinada palabra.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class ReferenciaPalabrasClaveBean extends AbstractRevisionBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 3927845673735363457L;

    @Inject
    private EstadisticaService estadisticaService;

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
        referencias = estadisticaService.obtenerReferencias(keyword, Arrays.asList(metadatos));

		listadoReferencias= referencias.stream()
				.map(Referencia::getSpsid)
				.collect(Collectors.joining(", "));
		totalResultados = referencias.size();
    }

    public TipoMetadato[] getTipoMetadatos() {
        return TipoMetadato.values();
    }

}
