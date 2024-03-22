package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.MetadatoServices;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.PrimeFaces;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de la edición de una referencia.
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
public class EditarReferenciaBean extends GenericBeanNew<Metadato,String> {
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private MetadatoServices metadatoServices;
    @Getter
    private ReferenciaDTO referencia;

    public void inicializar() {
        var idReferencia = getFacesContext().getExternalContext().getRequestParameterMap().get("referencia");
        referencia = referenciaService.find(idReferencia).stream().map(ReferenciaDTO::new).findFirst().orElse(null);
        if( referencia != null ) {
            setRecords(referencia.getMetadatos());
            setRecord(newRecord());
        }
    }

    public String editarReferencia(ReferenciaDTO referencia){
        this.referencia = referencia;
        inicializar();
        return "/referencia/editar.xhtml";
    }

    public void guardar() {

//        getAndRemoveFromSession("referenciaDTO");
        PrimeFaces.current().dialog().closeDynamic(referencia);
    }

    @Override
    protected Metadato newRecord() {
        var metadato = new Metadato();
        if( referencia != null ) {
            metadato.setReferencia(referencia.getReferencia());
        }
        return metadato;
    }

    @Override
    protected AbstractGenericService<Metadato, String> getServices() {
        return metadatoServices;
    }

    public TipoMetadato[] getTipos() {
        return TipoMetadato.values();
    }

    public TipoMetadato getTipoMetadatoFuente(){
        return TipoMetadato.FUENTE;
    }

    public TipoMetadato getTipoMetadatoTipoFuente(){
        return TipoMetadato.TIPO_FUENTE;
    }
}
