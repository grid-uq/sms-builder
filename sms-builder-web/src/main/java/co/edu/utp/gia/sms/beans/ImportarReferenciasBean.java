package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.importutil.TipoFuente;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.model.file.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

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

    @Getter
    @Setter
    private UploadedFile file;
    @Getter
    @Setter
    private Fuente fuente;



    @Setter
    private TipoFuente tipoFuente;

    protected ImportarReferenciasBean() {
        super();
        this.tipoFuente = null;
    }

    public void upload() {
        if (file != null && file.getFileName() != null) {
            procesarArchivo();
        } else {
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        }
        referenciaEJB.avanzarReferecias(paso-1);
    }

    private void procesarArchivo() {
        try {
            FileMultipleRegisterParse parser = FileMultipleRegisterParseFactory.getInstance(fuente);
            List<Referencia> referencias = parser.parse(file.getInputStream());
            referenciaEJB.registrar(referencias, getRevision().getId(),paso);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA)+" "+referencias.size());
        } catch (IOException e) {
            log.log(Level.WARNING, "Error al procesar un archivo", e);
        }
    }

    /**
     * Arreglo de fuentes soportadas
     *
     * @return Arreglo de fuentes soportadas
     */
    public List<Fuente> getFuentes() {
        List<Fuente> lista;
        if( tipoFuente != null ){
            lista = Arrays.stream(Fuente.values()).filter(f->f.getTipo().equals(tipoFuente)).collect(Collectors.toList());
        } else {
            lista = Arrays.asList( Fuente.values() );
        }
        return lista;
    }

    @Override
    public void inicializar() {
        // No se requiere la inicialiación de ningún elemento
    }

}
