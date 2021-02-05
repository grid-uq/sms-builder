package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.TecnicalException;

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
    protected EntityManager entityManager;
    @Inject
    private RevisionEJB revisionEJB;

    public List<Usuario> listar(Integer id) {
        Revision revision = revisionEJB.obtener(id);
        if( revision == null ){
            throw new TecnicalException("La revisión no existe");
        }
        return revision.getRevisores();
    }

    public void guardar(Integer id, List<Usuario> usuarios) {
        Revision revision = revisionEJB.obtener(id);
        if( revision == null ){
            throw new TecnicalException("La revisión no existe");
        }
        revision.setRevisores( usuarios );
    }
}
