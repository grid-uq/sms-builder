package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.ReferenceParse;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import co.edu.utp.gia.sms.negocio.FuenteService;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.model.file.UploadedFile;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
/**
 * Clase controladora de interfaz web que se encarga de la importación de referencias.
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
@Log
public class ImportarReferenciasBean extends GenericBean<Referencia> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1107564281230780705L;

    @Inject
    private ReferenciaEJB referenciaEJB;
    @Inject
    private FuenteService fuenteService;
    @Getter @Setter
    private UploadedFile file;
    @Getter @Setter
    private Fuente fuente;
    @Getter @Setter
    private TipoArchivo tipoArchivo;
    @Getter
    private Collection<Fuente> fuentes;



    @Setter
    private TipoFuente tipoFuente;

    protected ImportarReferenciasBean() {
        super();
        this.tipoFuente = null;
        this.tipoArchivo = TipoArchivo.RIS;
    }

    public void upload() {
        if (file != null && file.getFileName() != null) {
            procesarArchivo();
        } else {
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        }
        referenciaEJB.avanzarReferecias(getPasoAnterior().getId());
    }

    private void procesarArchivo() {
        try {
            ReferenceParse parser = FileMultipleRegisterParseFactory
                    .getInstance(tipoArchivo,fuente.getNombre(),fuente.getTipo().toString());
            List<Referencia> referencias = parser.parse(file.getInputStream());
            referenciaEJB.save(referencias, paso);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA)+" "+referencias.size());
        } catch (IOException e) {
            log.log(Level.WARNING, "Error al procesar un archivo", e);
        }
    }

    @Override
    public void inicializar() {
        if( tipoFuente != null ){
            fuentes = fuenteService.getByTipoFuente(tipoFuente);
        } else {
            fuentes = fuenteService.get();
        }
    }

    public TipoArchivo[] getTiposArchivo(){
        return TipoArchivo.values();
    }

}
