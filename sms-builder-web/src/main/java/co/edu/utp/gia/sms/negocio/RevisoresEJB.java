package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del Revisor.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@Stateless
@LocalBean
public class RevisoresEJB implements Serializable {
    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
    @Inject
    private ExceptionMessage exceptionMessage;
    @Inject
    private RevisionEJB revisionEJB;

    public List<Usuario> listar(Integer id) {
        return obtenerRevision(id).getRevisores();
    }

    public void guardar(Integer id, List<Usuario> usuarios) {
        obtenerRevision(id).setRevisores(usuarios);
    }

    private Revision obtenerRevision(Integer id) {
        Revision revision = revisionEJB.obtener(id);
        if (revision == null) {
            throw new LogicException(exceptionMessage.getRegistroNoEncontrado());
        }
        return revision;
    }
}
