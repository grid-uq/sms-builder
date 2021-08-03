package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetNotas;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NotaEJB extends AbstractEJB<Nota, Integer> {
    @Inject
    private ReferenciaEJB referenciaEJB;

    public NotaEJB() {
        super(Nota.class);
    }

    public Nota registrar(Integer etapa, String descripcion, Integer idReferencia) {
        Nota nota = null;

        Referencia referencia = referenciaEJB.obtener(idReferencia);
        if (referencia != null) {
            nota = new Nota(etapa, descripcion, referencia);
            entityManager.persist(nota);
        }
        return nota;
    }

    public List<Nota> obtenerNotas(Integer id) {
        return ReferenciaGetNotas.createQuery(entityManager,id).getResultList();
    }

    public void actualizar(Integer id, String descripcion) {
        Nota nota = obtener(id);
        if (nota != null) {
            nota.setDescripcion(descripcion);
        }
    }



}
