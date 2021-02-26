package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class RevisoresEJB implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;
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
