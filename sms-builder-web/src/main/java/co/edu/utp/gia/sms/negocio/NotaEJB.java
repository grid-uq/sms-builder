package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetNotas;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Nota}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
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
